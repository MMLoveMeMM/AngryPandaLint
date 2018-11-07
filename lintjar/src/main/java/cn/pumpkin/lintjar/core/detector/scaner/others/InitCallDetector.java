package cn.pumpkin.lintjar.core.detector.scaner.others;

import com.android.annotations.NonNull;
import com.android.tools.lint.client.api.JavaParser;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.Collections;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.MethodDeclaration;
import lombok.ast.MethodInvocation;
import lombok.ast.Node;
/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/7 19:37
 * @des:
 * @see {@link }
 */

public class InitCallDetector extends Detector implements Detector.JavaScanner{

    public static Issue ISSUE = Issue.create(
            "InitNotCall",
            "init not call in onCreate",
            "please call init method in onCreate method",
            // 这个主要是用于对问题的分类，不同的问题就可以集中在一起显示。
            Category.MESSAGES,
            // 优先级
            9,
            // 定义查找问题的严重级别
            Severity.WARNING,
            // 提供处理该问题的Detector和该Detector所关心的资源范围。当系统生成了抽象语法树（Abstract syntax tree，简称AST），或者遍历xml资源时，就会调用对应Issue的处理器Detector。
            new Implementation(InitCallDetector.class,
                    Scope.JAVA_FILE_SCOPE)
    );

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        return Collections.<Class<? extends Node>>singletonList(MethodDeclaration.class);
    }

    @Override
    public AstVisitor createJavaVisitor(final JavaContext context) {
        return new ForwardingAstVisitor() {
            @Override
            public boolean visitMethodDeclaration(MethodDeclaration node) {
                JavaParser.ResolvedNode resolved = context.resolve(node);
                if (resolved instanceof JavaParser.ResolvedMethod) {
                    JavaParser.ResolvedMethod method = (JavaParser.ResolvedMethod) resolved;
                    if(method.getContainingClass().getSuperClass().getSimpleName().equals("BaseActivity") && method.getName().equals("onCreate")){
                        checkCallInit(context, node, method);
                    }
                }
                return super.visitMethodDeclaration(node);
            }
        };
    }

    private void checkCallInit(@NonNull JavaContext context,
                               @NonNull MethodDeclaration declaration,
                               @NonNull JavaParser.ResolvedMethod method){
        if (!InitCallVisitor.callInit(context, declaration)) {
            String message = "onCreate method should call init method";
            Location location = context.getLocation(declaration.astMethodName());
            context.report(ISSUE, declaration, location, message);
        }
    }

    private static class InitCallVisitor extends ForwardingAstVisitor {
        private final JavaContext mContext;
        private boolean mCallInit;

        public static boolean callInit(
                @NonNull JavaContext context,
                @NonNull MethodDeclaration methodDeclaration) {
            InitCallVisitor visitor = new InitCallVisitor(context);
            methodDeclaration.accept(visitor);
            return visitor.mCallInit;
        }

        private InitCallVisitor(@NonNull JavaContext context) {
            mContext = context;
        }

        @Override
        public boolean visitMethodInvocation(MethodInvocation node) {
            JavaParser.ResolvedNode resolved = mContext.resolve(node);
            if(resolved instanceof JavaParser.ResolvedMethod){
                JavaParser.ResolvedMethod resolvedMethod = (JavaParser.ResolvedMethod)resolved;
                if (resolvedMethod.getName().equals("init")) {
                    mCallInit = true;
                    return true;
                }
            }
            return super.visitMethodInvocation(node);
        }

        @Override
        public boolean visitNode(Node node) {
            return mCallInit || super.visitNode(node);
        }
    }

}

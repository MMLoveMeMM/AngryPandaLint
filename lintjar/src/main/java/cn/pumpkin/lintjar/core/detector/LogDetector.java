package cn.pumpkin.lintjar.core.detector;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import java.util.Arrays;
import java.util.List;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/6 10:25
 * @des:
 * @see {@link }
 */

public class LogDetector extends Detector implements Detector.JavaPsiScanner{

    public static final Issue ISSUE = Issue.create(
            "LogUsage",
            "dun't use android.util.Log liuzhibao",
            "dun't use android.util.Log,using uniform tool class liuzhibao",
            Category.SECURITY, 5, Severity.ERROR,
            new Implementation(LogDetector.class, Scope.JAVA_FILE_SCOPE));

    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("v", "d", "i", "w", "e", "wtf");
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression call, PsiMethod method) {
        if (context.getEvaluator().isMemberInClass(method, "android.util.Log")) {
            context.report(ISSUE, call, context.getLocation(call.getMethodExpression()), "dun't use android.util.Log,using uniform tool class liuzhibao");
        }
    }

}

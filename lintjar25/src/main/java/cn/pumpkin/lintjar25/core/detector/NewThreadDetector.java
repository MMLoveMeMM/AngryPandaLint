package cn.pumpkin.lintjar25.core.detector;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNewExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/6 10:25
 * @des: 如果开发创建新的
 * @see {@link }
 */

public class NewThreadDetector extends Detector implements Detector.JavaPsiScanner {

    public static final Issue ISSUE = Issue.create(
            "Construct Check",
            "dont't create Thread by yourself liuzhibao",
            "don't create new Thread()，advice to use AsyncTask to replace current liuzhibao",
            Category.PERFORMANCE, 5, Severity.ERROR,
            new Implementation(NewThreadDetector.class, Scope.JAVA_FILE_SCOPE));

    @Override
    public List<String> getApplicableConstructorTypes() {
        List<String> list=new ArrayList<>();
        list.add("java.lang.Thread");
        list.add("cn.pumpkin.angrypandalint.Panda");
        return list;// Collections.singletonList("java.lang.Thread");
    }

    @Override
    public void visitConstructor(@NonNull JavaContext context, @Nullable JavaElementVisitor visitor,
                                 @NonNull PsiNewExpression node, @NonNull PsiMethod constructor) {
        context.report(ISSUE, node, context.getLocation(node), "don't create new Thread()，advice to use AsyncTask to replace current liuzhibao");
    }

}

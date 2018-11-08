package cn.pumpkin.lintjar25.core.meituan;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.JavaContext;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import java.util.Arrays;
import java.util.List;

import cn.pumpkin.lintjar25.core.config.LintConfig;

import static cn.pumpkin.lintjar25.core.config.LintConfig.ISSUE;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/8 10:12
 * @des:
 * @see {@link }
 */

public class LogUsageDetector extends Detector implements Detector.JavaPsiScanner {
    // ...

    private LintConfig mLintConfig;

    @Override
    public void beforeCheckProject(@NonNull Context context) {
        // 读取配置
        mLintConfig = new LintConfig(context);
    }

    @Override
    public void beforeCheckLibraryProject(@NonNull Context context) {
        // 读取配置
        mLintConfig = new LintConfig(context);
    }

    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("v", "d", "i", "w", "e", "wtf");
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression call, PsiMethod method) {
        if (context.getEvaluator().isMemberInClass(method, "android.util.Log")) {
            // 从配置文件获取Message
            String msg = mLintConfig.getMessageConfig("log-usage-message");
            context.report(ISSUE, call, context.getLocation(call.getMethodExpression()), msg);
        }
    }
}
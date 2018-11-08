package cn.pumpkin.lintjar25.core.config;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.io.File;
import java.util.EnumSet;

import cn.pumpkin.lintjar25.core.meituan.LogUsageDetector;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/8 09:48
 * @des: json 规则:
 * DeprecatedApi：禁止直接调用指定API
 * HandleException：调用指定API时，需要加try-catch处理指定类型的异常
 * @see {@link }
 */

public class LintConfig {

    private static final Class<? extends Detector> DETECTOR_CLASS = LogUsageDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;

    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "XTC_LogUseError";
    private static final String ISSUE_DESCRIPTION = "警告:你应该使用我们团队自定义的Log打印工具类工具类{com.xtc.log.LogUtil}";
    private static final String ISSUE_EXPLANATION = "为了能够更好的控制Log打印的开关，你不能直接使用{android.util.Log}或者{System.out.println}直接打印日志，你应该使用我们团队自定义的Log打印工具类工具类{com.xtc.log.LogUtil}";

    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.WARNING;

    public static final Issue ISSUE = Issue.create(
            ISSUE_ID,
            ISSUE_DESCRIPTION,
            ISSUE_EXPLANATION,
            ISSUE_CATEGORY,
            ISSUE_PRIORITY,
            ISSUE_SEVERITY,
            IMPLEMENTATION
    );

    public LintConfig(Context context) {
        File projectDir = context.getProject().getDir();
        File configFile = new File(projectDir, "custom-lint-config.json");
        if (configFile.exists() && configFile.isFile()) {
            // 读取配置文件...
        }
    }

    public String getConfig(String path){

        return "";
    }

}

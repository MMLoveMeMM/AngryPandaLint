package cn.pumpkin.lintjar25.core.config;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Iterator;

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

    private String mJsonConfig = "";

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
            InputStream in = null;
            try {
                in = new FileInputStream(configFile);
                int tempByte;
                while ((tempByte = in.read()) != -1) {
                    System.out.println(tempByte);
                    mJsonConfig += tempByte;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getMessageConfig(String jsonKey) {
        return getConfig(jsonKey,"message");
    }

    public String getPrioConfig(String jsonKey) {
        return getConfig(jsonKey,"severity");
    }

    private String getConfig(String jsonKey,String field){
        try {
            JSONObject rootJson = new JSONObject(mJsonConfig);
            if (rootJson != null) {
                JSONObject jsonLintRule = rootJson.optJSONObject("lint-rules");
                JSONArray jsonApiArr = jsonLintRule.optJSONArray("deprecated-api");
                for (int i = 0; i < jsonApiArr.length(); i++) {
                    JSONObject json = (JSONObject) jsonApiArr.get(i);
                    Iterator it = json.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        String value = json.getString(key);
                        if(value.contains(jsonKey)){
                            return json.optString(field);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}

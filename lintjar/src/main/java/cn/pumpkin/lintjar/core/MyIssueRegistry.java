package cn.pumpkin.lintjar.core;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

import cn.pumpkin.lintjar.core.detector.scaner.HashMapForJDK7Detector;
import cn.pumpkin.lintjar.core.detector.scaner.LoggerUsageDetector;
import cn.pumpkin.lintjar.core.detector.scaner.XsfActivityFragmentLayoutNameDetector;
import cn.pumpkin.lintjar.core.detector.scaner.XsfCustomToastDetector;
import cn.pumpkin.lintjar.core.detector.scaner.XsfLogDetector;
import cn.pumpkin.lintjar.core.detector.scaner.XsfMessageObtainDetector;
import cn.pumpkin.lintjar.core.detector.scaner.XsfViewIdNameDetector;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/6 10:14
 * @des:
 * @see {@link }
 */

public class MyIssueRegistry extends IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        System.out.println("==== my lint start ====");
        return Arrays.asList(LoggerUsageDetector.ISSUE
                , XsfActivityFragmentLayoutNameDetector.ACTIVITY_LAYOUT_NAME_ISSUE
                , XsfActivityFragmentLayoutNameDetector.FRAGMENT_LAYOUT_NAME_ISSUE
                , XsfMessageObtainDetector.ISSUE
                , XsfCustomToastDetector.ISSUE
                , XsfLogDetector.ISSUE
                , XsfViewIdNameDetector.ISSUE
        , HashMapForJDK7Detector.ISSUE);
    }
}

package cn.pumpkin.lintjar.core;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

import cn.pumpkin.lintjar.core.detector.LogDetector;
import cn.pumpkin.lintjar.core.detector.NewThreadDetector;

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
        return Arrays.asList(LogDetector.ISSUE, NewThreadDetector.ISSUE);
    }
}

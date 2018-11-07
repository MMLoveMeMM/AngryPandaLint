package cn.pumpkin.lintjar.core.detector.scaner.others;

import com.android.annotations.NonNull;
import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.LintUtils;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import static com.android.SdkConstants.ATTR_NAME;
import static com.android.SdkConstants.TAG_ATTR;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/7 19:41
 * @des: 资源attr的值名前缀以ct开头
 * @see {@link }
 */

public class AttrPrefixDetector extends ResourceXmlDetector {

    public static final Issue ISSUE = Issue.create("AttrNotPrefixed",
            "You must prefix your custom attr by `ct`",
            "We prefix all our attrs to avoid clashes.",
            Category.TYPOGRAPHY, 5, Severity.WARNING,
            new Implementation(AttrPrefixDetector.class, Scope.RESOURCE_FILE_SCOPE)); // Only XML files

    @Override
    public boolean appliesTo(@NonNull Context context,
                             @NonNull File file) {
        return LintUtils.isXmlFile(file);
    }

    // Only values folder
    @Override
    public boolean appliesTo(ResourceFolderType folderType) {
        return ResourceFolderType.VALUES == folderType;
    }

    // Only attr tag
    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList(TAG_ATTR);
    }

    // Only name attribute
    @Override
    public Collection<String> getApplicableAttributes() {
        return Collections.singletonList(ATTR_NAME);
    }

    @Override
    public void visitElement(XmlContext context, Element element) {
        final Attr attributeNode = element.getAttributeNode(ATTR_NAME);
        if (attributeNode != null) {
            final String val = attributeNode.getValue();
            if (!val.startsWith("android:") && !val.startsWith("ct")) {
                context.report(ISSUE, attributeNode, context.getLocation(attributeNode),
                        "You must prefix your custom attr by `ct`");
            }
        }
    }
}


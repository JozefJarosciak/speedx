package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSProperty;
import com.facebook.stetho.inspector.protocol.module.CSS.GetMatchedStylesForNodeRequest;
import com.facebook.stetho.inspector.protocol.module.CSS.RuleMatch;

class CSS$2 implements Runnable {
    final /* synthetic */ CSS this$0;
    final /* synthetic */ RuleMatch val$accessibilityMatch;
    final /* synthetic */ RuleMatch val$match;
    final /* synthetic */ GetMatchedStylesForNodeRequest val$request;

    /* renamed from: com.facebook.stetho.inspector.protocol.module.CSS$2$1 */
    class C31741 implements StyleAccumulator {
        C31741() {
        }

        public void store(String str, String str2, boolean z) {
            if (!z) {
                CSSProperty cSSProperty = new CSSProperty(null);
                cSSProperty.name = str;
                cSSProperty.value = str2;
                CSS$2.this.val$match.rule.style.cssProperties.add(cSSProperty);
            }
        }
    }

    /* renamed from: com.facebook.stetho.inspector.protocol.module.CSS$2$2 */
    class C31752 implements StyleAccumulator {
        C31752() {
        }

        public void store(String str, String str2, boolean z) {
            if (!z) {
                CSSProperty cSSProperty = new CSSProperty(null);
                cSSProperty.name = str;
                cSSProperty.value = str2;
                CSS$2.this.val$accessibilityMatch.rule.style.cssProperties.add(cSSProperty);
            }
        }
    }

    CSS$2(CSS css, GetMatchedStylesForNodeRequest getMatchedStylesForNodeRequest, RuleMatch ruleMatch, RuleMatch ruleMatch2) {
        this.this$0 = css;
        this.val$request = getMatchedStylesForNodeRequest;
        this.val$match = ruleMatch;
        this.val$accessibilityMatch = ruleMatch2;
    }

    public void run() {
        Object elementForNodeId = CSS.access$200(this.this$0).getElementForNodeId(this.val$request.nodeId);
        if (elementForNodeId == null) {
            LogUtil.m15288w("Failed to get style of an element that does not exist, nodeid=" + this.val$request.nodeId);
            return;
        }
        CSS.access$200(this.this$0).getElementStyles(elementForNodeId, new C31741());
        CSS.access$200(this.this$0).getElementAccessibilityStyles(elementForNodeId, new C31752());
    }
}

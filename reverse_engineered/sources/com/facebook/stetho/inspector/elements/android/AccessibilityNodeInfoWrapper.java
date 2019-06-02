package com.facebook.stetho.inspector.elements.android;

import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import com.facebook.stetho.common.android.AccessibilityUtil;

public final class AccessibilityNodeInfoWrapper {
    public static boolean getIgnored(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2 || importantForAccessibility == 4) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return true;
            }
        }
        if (!accessibilityNodeInfoCompat.isVisibleToUser()) {
            return true;
        }
        if (AccessibilityUtil.isAccessibilityFocusable(accessibilityNodeInfoCompat, view)) {
            if (accessibilityNodeInfoCompat.getChildCount() <= 0) {
                return false;
            }
            if (AccessibilityUtil.isSpeakingNode(accessibilityNodeInfoCompat, view)) {
                return false;
            }
            return true;
        } else if (AccessibilityUtil.hasFocusableAncestor(accessibilityNodeInfoCompat, view) || !AccessibilityUtil.hasText(accessibilityNodeInfoCompat)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getIgnoredReasons(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2) {
            return "View has importantForAccessibility set to 'NO'.";
        }
        if (importantForAccessibility == 4) {
            return "View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return "An ancestor View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
            }
        }
        if (!accessibilityNodeInfoCompat.isVisibleToUser()) {
            return "View is not visible.";
        }
        if (AccessibilityUtil.isAccessibilityFocusable(accessibilityNodeInfoCompat, view)) {
            return "View is actionable, but has no description.";
        }
        if (AccessibilityUtil.hasText(accessibilityNodeInfoCompat)) {
            return "View is not actionable, and an ancestor View has co-opted its description.";
        }
        return "View is not actionable and has no description.";
    }

    @Nullable
    public static String getFocusableReasons(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        boolean hasText = AccessibilityUtil.hasText(accessibilityNodeInfoCompat);
        boolean isCheckable = accessibilityNodeInfoCompat.isCheckable();
        boolean hasNonActionableSpeakingDescendants = AccessibilityUtil.hasNonActionableSpeakingDescendants(accessibilityNodeInfoCompat, view);
        if (AccessibilityUtil.isActionableForAccessibility(accessibilityNodeInfoCompat)) {
            if (accessibilityNodeInfoCompat.getChildCount() <= 0) {
                return "View is actionable and has no children.";
            }
            if (hasText) {
                return "View is actionable and has a description.";
            }
            if (isCheckable) {
                return "View is actionable and checkable.";
            }
            if (hasNonActionableSpeakingDescendants) {
                return "View is actionable and has non-actionable descendants with descriptions.";
            }
        }
        if (AccessibilityUtil.isTopLevelScrollItem(accessibilityNodeInfoCompat, view)) {
            if (hasText) {
                return "View is a direct child of a scrollable container and has a description.";
            }
            if (isCheckable) {
                return "View is a direct child of a scrollable container and is checkable.";
            }
            if (hasNonActionableSpeakingDescendants) {
                return "View is a direct child of a scrollable container and has non-actionable descendants with descriptions.";
            }
        }
        if (hasText) {
            return "View has a description and is not actionable, but has no actionable ancestor.";
        }
        return null;
    }

    @Nullable
    public static String getActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        StringBuilder stringBuilder = new StringBuilder();
        String str = ", ";
        for (AccessibilityActionCompat accessibilityActionCompat : accessibilityNodeInfoCompat.getActionList()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            switch (accessibilityActionCompat.getId()) {
                case 1:
                    stringBuilder.append("focus");
                    break;
                case 2:
                    stringBuilder.append("clear-focus");
                    break;
                case 4:
                    stringBuilder.append("select");
                    break;
                case 8:
                    stringBuilder.append("clear-selection");
                    break;
                case 16:
                    stringBuilder.append("click");
                    break;
                case 32:
                    stringBuilder.append("long-click");
                    break;
                case 64:
                    stringBuilder.append("accessibility-focus");
                    break;
                case 128:
                    stringBuilder.append("clear-accessibility-focus");
                    break;
                case 256:
                    stringBuilder.append("next-at-movement-granularity");
                    break;
                case 512:
                    stringBuilder.append("previous-at-movement-granularity");
                    break;
                case 1024:
                    stringBuilder.append("next-html-element");
                    break;
                case 2048:
                    stringBuilder.append("previous-html-element");
                    break;
                case 4096:
                    stringBuilder.append("scroll-forward");
                    break;
                case 8192:
                    stringBuilder.append("scroll-backward");
                    break;
                case 16384:
                    stringBuilder.append("copy");
                    break;
                case 32768:
                    stringBuilder.append("paste");
                    break;
                case 65536:
                    stringBuilder.append("cut");
                    break;
                case 131072:
                    stringBuilder.append("set-selection");
                    break;
                default:
                    CharSequence label = accessibilityActionCompat.getLabel();
                    if (label == null) {
                        stringBuilder.append(EnvironmentCompat.MEDIA_UNKNOWN);
                        break;
                    }
                    stringBuilder.append(label);
                    break;
            }
        }
        return stringBuilder.length() > 0 ? stringBuilder.toString() : null;
    }

    @Nullable
    public static CharSequence getDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        int i = 0;
        CharSequence contentDescription = accessibilityNodeInfoCompat.getContentDescription();
        CharSequence text = accessibilityNodeInfoCompat.getText();
        int i2 = !TextUtils.isEmpty(text) ? 1 : 0;
        boolean z = view instanceof EditText;
        if (!TextUtils.isEmpty(contentDescription) && (!z || i2 == 0)) {
            return contentDescription;
        }
        if (i2 != 0) {
            return text;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String str = ", ";
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (i < childCount) {
            CharSequence description;
            View childAt = viewGroup.getChildAt(i);
            AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
            ViewCompat.onInitializeAccessibilityNodeInfo(childAt, obtain);
            if (AccessibilityUtil.isSpeakingNode(obtain, childAt)) {
                description = getDescription(obtain, childAt);
            } else {
                description = null;
            }
            if (!TextUtils.isEmpty(description)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(description);
            }
            obtain.recycle();
            i++;
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.toString();
        }
        return null;
    }
}

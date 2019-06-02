package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import java.util.List;

final class ActivityDescriptor extends AbstractChainedDescriptor<Activity> implements HighlightableDescriptor {
    ActivityDescriptor() {
    }

    protected String onGetNodeName(Activity activity) {
        return StringUtil.removePrefix(activity.getClass().getName(), "android.app.");
    }

    protected void onGetChildren(Activity activity, Accumulator<Object> accumulator) {
        getDialogFragments(FragmentCompat.getSupportLibInstance(), activity, accumulator);
        getDialogFragments(FragmentCompat.getFrameworkInstance(), activity, accumulator);
        Window window = activity.getWindow();
        if (window != null) {
            accumulator.store(window);
        }
    }

    public View getViewForHighlighting(Object obj) {
        Host host = getHost();
        if (!(host instanceof AndroidDescriptorHost)) {
            return null;
        }
        return ((AndroidDescriptorHost) host).getHighlightingView(((Activity) obj).getWindow());
    }

    private static void getDialogFragments(FragmentCompat fragmentCompat, Activity activity, Accumulator<Object> accumulator) {
        if (fragmentCompat != null && fragmentCompat.getFragmentActivityClass().isInstance(activity)) {
            Object fragmentManager = fragmentCompat.forFragmentActivity().getFragmentManager(activity);
            if (fragmentManager != null) {
                List addedFragments = fragmentCompat.forFragmentManager().getAddedFragments(fragmentManager);
                if (addedFragments != null) {
                    int size = addedFragments.size();
                    for (int i = 0; i < size; i++) {
                        Object obj = addedFragments.get(i);
                        if (fragmentCompat.getDialogFragmentClass().isInstance(obj)) {
                            accumulator.store(obj);
                        }
                    }
                }
            }
        }
    }
}

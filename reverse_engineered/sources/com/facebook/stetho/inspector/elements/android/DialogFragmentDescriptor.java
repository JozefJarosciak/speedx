package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.DialogFragmentAccessor;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.ChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.StyleAccumulator;

final class DialogFragmentDescriptor extends Descriptor implements ChainedDescriptor, HighlightableDescriptor {
    private final DialogFragmentAccessor mAccessor;
    private Descriptor mSuper;

    public static DescriptorMap register(DescriptorMap descriptorMap) {
        maybeRegister(descriptorMap, FragmentCompat.getSupportLibInstance());
        maybeRegister(descriptorMap, FragmentCompat.getFrameworkInstance());
        return descriptorMap;
    }

    private static void maybeRegister(DescriptorMap descriptorMap, FragmentCompat fragmentCompat) {
        if (fragmentCompat != null) {
            LogUtil.m15273d("Adding support for %s", fragmentCompat.getDialogFragmentClass());
            descriptorMap.register(r0, new DialogFragmentDescriptor(fragmentCompat));
        }
    }

    private DialogFragmentDescriptor(FragmentCompat fragmentCompat) {
        this.mAccessor = fragmentCompat.forDialogFragment();
    }

    public void setSuper(Descriptor descriptor) {
        Util.throwIfNull(descriptor);
        if (descriptor == this.mSuper) {
            return;
        }
        if (this.mSuper != null) {
            throw new IllegalStateException();
        }
        this.mSuper = descriptor;
    }

    public void hook(Object obj) {
        this.mSuper.hook(obj);
    }

    public void unhook(Object obj) {
        this.mSuper.unhook(obj);
    }

    public NodeType getNodeType(Object obj) {
        return this.mSuper.getNodeType(obj);
    }

    public String getNodeName(Object obj) {
        return this.mSuper.getNodeName(obj);
    }

    public String getLocalName(Object obj) {
        return this.mSuper.getLocalName(obj);
    }

    public String getNodeValue(Object obj) {
        return this.mSuper.getNodeValue(obj);
    }

    public void getChildren(Object obj, Accumulator<Object> accumulator) {
        accumulator.store(this.mAccessor.getDialog(obj));
    }

    public void getAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
        this.mSuper.getAttributes(obj, attributeAccumulator);
    }

    public void setAttributesAsText(Object obj, String str) {
        this.mSuper.setAttributesAsText(obj, str);
    }

    public View getViewForHighlighting(Object obj) {
        Host host = getHost();
        if (!(host instanceof AndroidDescriptorHost)) {
            return null;
        }
        return ((AndroidDescriptorHost) host).getHighlightingView(this.mAccessor.getDialog(obj));
    }

    public void getStyles(Object obj, StyleAccumulator styleAccumulator) {
    }

    public void getAccessibilityStyles(Object obj, StyleAccumulator styleAccumulator) {
    }
}

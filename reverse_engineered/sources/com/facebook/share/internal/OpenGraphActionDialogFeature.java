package com.facebook.share.internal;

import com.facebook.internal.C3011e;

public enum OpenGraphActionDialogFeature implements C3011e {
    OG_ACTION_DIALOG(20130618);
    
    private int minVersion;

    private OpenGraphActionDialogFeature(int i) {
        this.minVersion = i;
    }

    public String getAction() {
        return "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    }

    public int getMinVersion() {
        return this.minVersion;
    }
}

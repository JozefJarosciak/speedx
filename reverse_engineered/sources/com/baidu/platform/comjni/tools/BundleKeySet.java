package com.baidu.platform.comjni.tools;

import android.os.Bundle;

public class BundleKeySet {
    public String[] getBundleKeys(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return null;
        }
        String[] strArr = new String[bundle.size()];
        int i = 0;
        for (Object obj : bundle.keySet()) {
            strArr[i] = obj.toString();
            i++;
        }
        return strArr;
    }
}

package com.beastbikes.android.modules.user.ui;

import android.content.Context;
import android.view.OrientationEventListener;
import com.alibaba.fastjson.asm.Opcodes;

class WatermarkCameraActivity$a extends OrientationEventListener {
    /* renamed from: a */
    private int f11792a;
    /* renamed from: b */
    private int f11793b;

    public WatermarkCameraActivity$a(Context context) {
        super(context, 3);
    }

    public void onOrientationChanged(int i) {
        if (i != -1) {
            this.f11792a = m12548a(i);
        }
    }

    /* renamed from: a */
    private int m12548a(int i) {
        if (i > 315 || i <= 45) {
            return 0;
        }
        if (i > 45 && i <= 135) {
            return 90;
        }
        if (i > 135 && i <= 225) {
            return Opcodes.GETFIELD;
        }
        if (i > 225 && i <= 315) {
            return 270;
        }
        throw new RuntimeException("The physics as we know them are no more. Watch out for anomalies.");
    }

    /* renamed from: a */
    public void m12549a() {
        this.f11793b = this.f11792a;
    }

    /* renamed from: b */
    public int m12550b() {
        return this.f11793b;
    }
}

package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint({"ParcelCreator"})
public class PieEntry extends Entry {
    /* renamed from: a */
    private String f7006a;

    /* renamed from: a */
    public String m8253a() {
        return this.f7006a;
    }

    @Deprecated
    /* renamed from: i */
    public float m8254i() {
        Log.i("DEPRECATED", "Pie entries do not have x values");
        return super.i();
    }
}

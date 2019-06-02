package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.p089e.p090b.C3227b;
import java.util.List;

/* compiled from: BarLineScatterCandleBubbleDataSet */
/* renamed from: com.github.mikephil.charting.data.d */
public abstract class C3228d<T extends Entry> extends DataSet<T> implements C3227b<T> {
    /* renamed from: a */
    protected int f14036a = Color.rgb(255, Opcodes.NEW, AVException.PUSH_MISCONFIGURED);

    public C3228d(List<T> list, String str) {
        super(list, str);
    }

    /* renamed from: a */
    public void m15602a(int i) {
        this.f14036a = i;
    }

    /* renamed from: h */
    public int mo3961h() {
        return this.f14036a;
    }
}

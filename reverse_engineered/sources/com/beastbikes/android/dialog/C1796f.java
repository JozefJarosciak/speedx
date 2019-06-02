package com.beastbikes.android.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.beastbikes.android.C1373R;
import java.util.ArrayList;

/* compiled from: DoublePickerDialog */
/* renamed from: com.beastbikes.android.dialog.f */
public class C1796f extends C1788c {
    /* renamed from: b */
    private DoublePicker f8188b;
    /* renamed from: c */
    private C1795a f8189c;

    /* compiled from: DoublePickerDialog */
    /* renamed from: com.beastbikes.android.dialog.f$a */
    public interface C1795a {
        /* renamed from: a */
        void mo3507a(int i, String str, int i2, String str2);
    }

    public C1796f(Context context, C1795a c1795a) {
        super(context);
        this.f8189c = c1795a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.dialog_double_picker, null);
        this.f8188b = (DoublePicker) inflate.findViewById(C1373R.id.dialog_double_picker_view);
        m9478a(inflate);
    }

    /* renamed from: a */
    public void m9501a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        this.f8188b.m9403a((ArrayList) arrayList, (ArrayList) arrayList2);
    }

    /* renamed from: a */
    public void m9500a(String str, String str2) {
        this.f8188b.m9402a(str, str2);
    }

    /* renamed from: a */
    public void mo3244a() {
        super.mo3244a();
        if (this.f8189c != null) {
            this.f8189c.mo3507a(this.f8188b.getLeftSelectIndex(), this.f8188b.getLeftSelectVaule(), this.f8188b.getLeftSelectIndex(), this.f8188b.getRightSelectValue());
        }
        dismiss();
    }
}

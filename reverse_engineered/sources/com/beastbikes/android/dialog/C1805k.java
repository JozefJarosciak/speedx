package com.beastbikes.android.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.beastbikes.android.C1373R;
import java.util.ArrayList;

/* compiled from: SinglePickerDialog */
/* renamed from: com.beastbikes.android.dialog.k */
public class C1805k extends C1788c {
    /* renamed from: b */
    private PickerView f8210b;
    /* renamed from: c */
    private C1804a f8211c;

    /* compiled from: SinglePickerDialog */
    /* renamed from: com.beastbikes.android.dialog.k$a */
    public interface C1804a {
        /* renamed from: a */
        void mo3284a(int i, String str);
    }

    public C1805k(Context context, C1804a c1804a) {
        super(context);
        this.f8211c = c1804a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.dialog_single_picker, null);
        this.f8210b = (PickerView) inflate.findViewById(C1373R.id.dialog_single_picker_view);
        m9478a(inflate);
    }

    /* renamed from: a */
    public void m9510a(ArrayList<String> arrayList) {
        this.f8210b.setData(arrayList);
    }

    /* renamed from: a */
    public void m9509a(String str) {
        this.f8210b.setUnit(str);
    }

    /* renamed from: b */
    public void m9511b(String str) {
        this.f8210b.setDefault(str);
    }

    /* renamed from: a */
    public void m9508a(int i) {
        this.f8210b.setDefault(i);
    }

    /* renamed from: a */
    public void mo3244a() {
        super.mo3244a();
        if (this.f8211c != null) {
            this.f8211c.mo3284a(this.f8210b.getSelected(), this.f8210b.getSelectedText());
        }
        dismiss();
    }
}

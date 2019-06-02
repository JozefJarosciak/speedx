package com.beastbikes.android.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import java.util.ArrayList;

public class DoublePicker extends LinearLayout {
    /* renamed from: a */
    private PickerView f8081a;
    /* renamed from: b */
    private PickerView f8082b;

    public DoublePicker(Context context) {
        super(context);
        m9401a(context);
    }

    public DoublePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9401a(context);
    }

    public DoublePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9401a(context);
    }

    /* renamed from: a */
    private void m9401a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(C1373R.layout.double_picker_view, null);
        addView(inflate);
        this.f8081a = (PickerView) inflate.findViewById(C1373R.id.double_picker_view_left);
        this.f8082b = (PickerView) inflate.findViewById(C1373R.id.double_picker_view_right);
    }

    /* renamed from: a */
    public void m9403a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (!(arrayList == null || arrayList.isEmpty())) {
            this.f8081a.setData(arrayList);
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            this.f8082b.setData(arrayList2);
        }
    }

    /* renamed from: a */
    public void m9402a(String str, String str2) {
        this.f8081a.setDefault(str);
        this.f8082b.setDefault(str2);
    }

    public int getLeftSelectIndex() {
        return this.f8081a.getSelected();
    }

    public String getLeftSelectVaule() {
        return this.f8081a.getSelectedText();
    }

    public int getRightSelectIndex() {
        return this.f8082b.getSelected();
    }

    public String getRightSelectValue() {
        return this.f8082b.getSelectedText();
    }
}

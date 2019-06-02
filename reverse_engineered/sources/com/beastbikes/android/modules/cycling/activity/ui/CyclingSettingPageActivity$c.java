package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.MaterialCheckBox;
import com.beastbikes.android.widget.MaterialCheckBox.C1930a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

class CyclingSettingPageActivity$c extends ViewHolder<String> {
    /* renamed from: a */
    public final MaterialCheckBox f8681a;
    /* renamed from: b */
    public final TextView f8682b;
    /* renamed from: c */
    public final View f8683c;
    /* renamed from: d */
    private int f8684d;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.CyclingSettingPageActivity$c$1 */
    class C19281 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ CyclingSettingPageActivity$c f8678a;

        C19281(CyclingSettingPageActivity$c cyclingSettingPageActivity$c) {
            this.f8678a = cyclingSettingPageActivity$c;
        }

        public void onClick(View view) {
            if (this.f8678a.f8681a.m12956a() || CyclingSettingPageActivity.f4639a.size() < 2 || CyclingSettingPageActivity.f4639a.contains(Integer.valueOf(this.f8678a.f8684d))) {
                this.f8678a.f8681a.setChecked(!this.f8678a.f8681a.m12956a());
            } else {
                Toasts.show(this.f8678a.getContext(), (int) C1373R.string.cycling_setting_more_data_msg);
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.CyclingSettingPageActivity$c$2 */
    class C19292 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ CyclingSettingPageActivity$c f8679a;

        C19292(CyclingSettingPageActivity$c cyclingSettingPageActivity$c) {
            this.f8679a = cyclingSettingPageActivity$c;
        }

        public void onClick(View view) {
            if (this.f8679a.f8681a.m12956a() || CyclingSettingPageActivity.f4639a.size() < 2 || CyclingSettingPageActivity.f4639a.contains(Integer.valueOf(this.f8679a.f8684d))) {
                this.f8679a.f8681a.setChecked(!this.f8679a.f8681a.m12956a());
            } else {
                Toasts.show(this.f8679a.getContext(), (int) C1373R.string.cycling_setting_more_data_msg);
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.CyclingSettingPageActivity$c$3 */
    class C19313 implements C1930a {
        /* renamed from: a */
        final /* synthetic */ CyclingSettingPageActivity$c f8680a;

        C19313(CyclingSettingPageActivity$c cyclingSettingPageActivity$c) {
            this.f8680a = cyclingSettingPageActivity$c;
        }

        /* renamed from: a */
        public void mo3291a(View view, boolean z) {
            if (z && CyclingSettingPageActivity.f4639a.size() >= 2 && !CyclingSettingPageActivity.f4639a.contains(Integer.valueOf(this.f8680a.f8684d))) {
                boolean z2;
                MaterialCheckBox materialCheckBox = this.f8680a.f8681a;
                if (z) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                materialCheckBox.setChecked(z2);
            } else if (z) {
                CyclingSettingPageActivity.f4639a.add(Integer.valueOf(this.f8680a.f8684d));
            } else if (CyclingSettingPageActivity.f4639a.contains(Integer.valueOf(this.f8680a.f8684d))) {
                CyclingSettingPageActivity.f4639a.remove(Integer.valueOf(this.f8680a.f8684d));
            }
        }
    }

    public /* synthetic */ void bind(Object obj) {
        m9961a((String) obj);
    }

    public CyclingSettingPageActivity$c(View view, int i) {
        super(view);
        this.f8683c = view;
        this.f8681a = (MaterialCheckBox) view.findViewById(C1373R.id.cycling_setting_dialog_item_check);
        this.f8682b = (TextView) view.findViewById(C1373R.id.cycling_setting_dialog_item_label);
        this.f8684d = i;
    }

    /* renamed from: a */
    public void m9961a(String str) {
        this.f8682b.setText(str);
        this.f8683c.setOnClickListener(new C19281(this));
        this.f8681a.setOnClickListener(new C19292(this));
        this.f8681a.setOnCheckedChangedListener(new C19313(this));
    }
}

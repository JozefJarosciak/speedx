package com.beastbikes.android.ble.ui.p098a;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.Toasts;

/* compiled from: SpeedxHeartRateSettingDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.q */
public class C1743q extends DialogFragment implements OnClickListener {
    /* renamed from: a */
    private EditText f7921a;
    /* renamed from: b */
    private ImageView f7922b;
    /* renamed from: c */
    private C1742a f7923c;

    /* compiled from: SpeedxHeartRateSettingDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.q$1 */
    class C17411 implements TextWatcher {
        /* renamed from: a */
        final /* synthetic */ C1743q f7920a;

        C17411(C1743q c1743q) {
            this.f7920a = c1743q;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.length() > 0) {
                this.f7920a.f7922b.setVisibility(0);
            } else {
                this.f7920a.f7922b.setVisibility(8);
            }
        }
    }

    /* compiled from: SpeedxHeartRateSettingDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.q$a */
    public interface C1742a {
        /* renamed from: a */
        void m9303a(int i);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        getDialog().requestWindowFeature(1);
        setCancelable(false);
        View inflate = layoutInflater.inflate(C1373R.layout.fragment_heart_rate_setting_dialog, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels - C2801d.m13756a(getActivity(), 80.0f), C2801d.m13756a(getActivity(), 40.0f));
        int a = C2801d.m13756a(getActivity(), 15.0f);
        layoutParams.setMargins(a, a, a, 0);
        this.f7921a = (EditText) inflate.findViewById(C1373R.id.dialog_heart_rate_setting_edittext);
        ImageView imageView = (ImageView) inflate.findViewById(C1373R.id.dialog_heart_rate_setting_close);
        Button button = (Button) inflate.findViewById(C1373R.id.dialog_heart_rate_setting_commit_btn);
        this.f7922b = (ImageView) inflate.findViewById(C1373R.id.dialog_heart_rate_setting_clear);
        button.setLayoutParams(layoutParams);
        TextView textView = (TextView) inflate.findViewById(C1373R.id.dialog_heart_rate_setting_default_tv);
        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
        textView.setOnClickListener(this);
        this.f7922b.setOnClickListener(this);
        this.f7921a.addTextChangedListener(new C17411(this));
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.dialog_heart_rate_setting_close:
                dismiss();
                return;
            case C1373R.id.dialog_heart_rate_setting_clear:
                this.f7921a.setText("");
                return;
            case C1373R.id.dialog_heart_rate_setting_commit_btn:
                Object obj = this.f7921a.getText().toString();
                if (!TextUtils.isEmpty(obj)) {
                    int intValue = Integer.valueOf(obj).intValue();
                    if (intValue < 55 || intValue > 249) {
                        Toasts.show(getActivity(), getString(C1373R.string.label_heart_rate_setting_overrun));
                        return;
                    }
                    if (this.f7923c != null) {
                        this.f7923c.m9303a(intValue);
                    }
                    dismiss();
                    return;
                }
                return;
            case C1373R.id.dialog_heart_rate_setting_default_tv:
                if (this.f7923c != null) {
                    this.f7923c.m9303a(-1);
                }
                dismiss();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m9305a(C1742a c1742a) {
        this.f7923c = c1742a;
    }
}

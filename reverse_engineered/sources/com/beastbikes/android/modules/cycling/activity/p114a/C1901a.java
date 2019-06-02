package com.beastbikes.android.modules.cycling.activity.p114a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1789d;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.sections.dto.UseRouteLineDTO;
import com.beastbikes.android.modules.cycling.stage.ui.StageAndRouteActivity;
import com.beastbikes.android.modules.cycling.stage.ui.StageCollectionActivity;
import com.beastbikes.android.utils.C2574s;
import rx.p208a.C5694b;
import rx.subscriptions.CompositeSubscription;

/* compiled from: CyclingSettingDialog */
/* renamed from: com.beastbikes.android.modules.cycling.activity.a.a */
public class C1901a extends C1789d implements OnClickListener {
    /* renamed from: b */
    private ImageView f8482b;
    /* renamed from: c */
    private ImageView f8483c;
    /* renamed from: d */
    private RelativeLayout f8484d;
    /* renamed from: e */
    private TextView f8485e;
    /* renamed from: f */
    private ImageView f8486f;
    /* renamed from: g */
    private TextView f8487g;
    /* renamed from: h */
    private BeastBikes f8488h = ((BeastBikes) BeastBikes.j());
    /* renamed from: i */
    private C1398a f8489i;
    /* renamed from: j */
    private SharedPreferences f8490j;

    /* compiled from: CyclingSettingDialog */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.a.a$1 */
    class C19001 implements C5694b<UseRouteLineDTO> {
        /* renamed from: a */
        final /* synthetic */ C1901a f8481a;

        C19001(C1901a c1901a) {
            this.f8481a = c1901a;
        }

        public /* synthetic */ void call(Object obj) {
            m9797a((UseRouteLineDTO) obj);
        }

        /* renamed from: a */
        public void m9797a(UseRouteLineDTO useRouteLineDTO) {
            if (useRouteLineDTO != null) {
                this.f8481a.f8485e.setText(useRouteLineDTO.getRouteName());
                this.f8481a.f8486f.setImageResource(C1373R.drawable.ic_cycling_setting_close);
                this.f8481a.f8484d.setOnClickListener(null);
                this.f8481a.f8486f.setTag(this.f8481a);
                this.f8481a.dismiss();
            }
        }
    }

    public C1901a(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(C1373R.layout.fragment_cycling_setting_dialog_layout, null);
        setContentView(inflate);
        this.f8482b = (ImageView) inflate.findViewById(C1373R.id.cycling_setting_screen_iv);
        this.f8483c = (ImageView) inflate.findViewById(C1373R.id.cycling_setting_auto_pause_iv);
        this.f8484d = (RelativeLayout) inflate.findViewById(C1373R.id.cycling_setting_select_route_rl);
        this.f8485e = (TextView) inflate.findViewById(C1373R.id.cycling_setting_select_route_label);
        this.f8486f = (ImageView) inflate.findViewById(C1373R.id.cycling_setting_route_delete);
        this.f8487g = (TextView) inflate.findViewById(C1373R.id.cycling_setting_dialog_cancel);
        this.f8489i = new C1398a(context);
        this.f8490j = context.getSharedPreferences(context.getPackageName(), 0);
        m9801b();
        new CompositeSubscription().add(C2574s.m12893a().m12894a(UseRouteLineDTO.class).a(new C19001(this)));
    }

    public void onClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case C1373R.id.cycling_setting_screen_iv:
                boolean z2;
                boolean g = this.f8488h.g();
                BeastBikes beastBikes = this.f8488h;
                if (g) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                beastBikes.d(z2);
                ImageView imageView = this.f8482b;
                if (g) {
                    z = false;
                }
                imageView.setSelected(z);
                return;
            case C1373R.id.cycling_setting_auto_pause_iv:
                m9803c();
                return;
            case C1373R.id.cycling_setting_select_route_rl:
                m9804d();
                return;
            case C1373R.id.cycling_setting_route_delete:
                if (this.f8486f.getTag() == null) {
                    m9804d();
                    return;
                } else {
                    m9805e();
                    return;
                }
            case C1373R.id.cycling_setting_dialog_cancel:
                dismiss();
                return;
            default:
                return;
        }
    }

    public void show() {
        super.show();
        m9799a();
    }

    /* renamed from: a */
    private void m9799a() {
        this.f8482b.setSelected(this.f8488h.g());
        this.f8483c.setSelected(this.f8488h.b());
        CharSequence string = this.f8490j.getString("use_route_name", "");
        if (TextUtils.isEmpty(string)) {
            this.f8485e.setText(C1373R.string.str_cycling_use_route_un_selected);
            this.f8486f.setImageResource(C1373R.drawable.ic_arrow_right);
            this.f8486f.setTag(null);
            this.f8484d.setOnClickListener(this);
            return;
        }
        this.f8485e.setText(string);
        this.f8486f.setImageResource(C1373R.drawable.ic_cycling_setting_close);
        this.f8486f.setTag(this);
        this.f8484d.setOnClickListener(null);
    }

    /* renamed from: b */
    private void m9801b() {
        this.f8482b.setOnClickListener(this);
        this.f8483c.setOnClickListener(this);
        this.f8486f.setOnClickListener(this);
        this.f8487g.setOnClickListener(this);
    }

    /* renamed from: c */
    private void m9803c() {
        boolean z = true;
        boolean b = this.f8488h.b();
        this.f8488h.a(!b);
        ImageView imageView = this.f8483c;
        if (b) {
            z = false;
        }
        imageView.setSelected(z);
        if (b) {
            LocalActivity a = this.f8489i.a();
            if (a != null && a.getState() == 3) {
                Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
                intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_RESUME");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setPackage(getContext().getPackageName());
                getContext().startService(intent);
            }
        }
    }

    /* renamed from: d */
    private void m9804d() {
        Intent intent;
        if (C1849a.m9641a()) {
            intent = new Intent(getContext(), StageAndRouteActivity.class);
        } else {
            intent = new Intent(getContext(), StageCollectionActivity.class);
            intent.putExtra("enter_source", 0);
        }
        getContext().startActivity(intent);
    }

    /* renamed from: e */
    private void m9805e() {
        this.f8485e.setText(C1373R.string.str_cycling_use_route_un_selected);
        this.f8486f.setImageResource(C1373R.drawable.ic_arrow_right);
        this.f8484d.setOnClickListener(this);
        this.f8490j.edit().remove("use_route_name").remove("use_route_id").remove("use_stage_route_id").remove("use_stage_route_hint").commit();
        this.f8486f.setTag(null);
    }
}

package com.beastbikes.android.widget.p168e;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.p168e.p169a.C2640a;
import com.beastbikes.android.widget.p168e.p169a.C2641b;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;

/* compiled from: CommonSharePopupWindow */
/* renamed from: com.beastbikes.android.widget.e.d */
public class C2655d extends PopupWindow implements OnClickListener {
    /* renamed from: a */
    private Activity f12379a;
    /* renamed from: b */
    private View f12380b;
    /* renamed from: c */
    private ImageView f12381c;
    /* renamed from: d */
    private TextView f12382d;
    /* renamed from: e */
    private ImageView f12383e;
    /* renamed from: f */
    private TextView f12384f;
    /* renamed from: g */
    private ImageView f12385g;
    /* renamed from: h */
    private TextView f12386h;
    /* renamed from: i */
    private ImageView f12387i;
    /* renamed from: j */
    private TextView f12388j;
    /* renamed from: k */
    private ImageView f12389k;
    /* renamed from: l */
    private TextView f12390l;
    /* renamed from: m */
    private ImageView f12391m;
    /* renamed from: n */
    private TextView f12392n;
    /* renamed from: o */
    private C2640a f12393o;
    /* renamed from: p */
    private C2643a f12394p;
    /* renamed from: q */
    private boolean f12395q;
    /* renamed from: r */
    private String f12396r;
    /* renamed from: s */
    private TextView f12397s;

    /* compiled from: CommonSharePopupWindow */
    /* renamed from: com.beastbikes.android.widget.e.d$1 */
    class C26541 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2655d f12378a;

        C26541(C2655d c2655d) {
            this.f12378a = c2655d;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int top = this.f12378a.f12380b.findViewById(C1373R.id.share_menu_root).getTop();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == 1 && y < top) {
                this.f12378a.dismiss();
            }
            return true;
        }
    }

    public C2655d(BaseFragmentActivity baseFragmentActivity, C2641b c2641b, String str) {
        super(baseFragmentActivity);
        this.f12379a = baseFragmentActivity;
        this.f12393o = c2641b;
        this.f12396r = str;
        this.f12394p = new C2643a(baseFragmentActivity, c2641b);
        this.f12395q = true;
        m13190a();
    }

    public C2655d(BaseFragmentActivity baseFragmentActivity, C2642c c2642c, String str) {
        super(baseFragmentActivity);
        this.f12379a = baseFragmentActivity;
        this.f12393o = c2642c;
        this.f12396r = str;
        this.f12394p = new C2643a(baseFragmentActivity, c2642c);
        m13190a();
    }

    /* renamed from: a */
    private void m13190a() {
        LayoutInflater from = LayoutInflater.from(this.f12379a);
        this.f12380b = from.inflate(C1373R.layout.popopwindow_common_share, null);
        this.f12397s = (TextView) this.f12380b.findViewById(C1373R.id.share_menu_title);
        LinearLayout linearLayout = (LinearLayout) this.f12380b.findViewById(C1373R.id.common_share_popupwindow_content);
        ViewGroup viewGroup = (ViewGroup) from.inflate(C1373R.layout.share_menu_item, null);
        viewGroup.setId(C1373R.id.share_item_wechat_moments);
        this.f12381c = (ImageView) viewGroup.findViewById(C1373R.id.share_menu_icon);
        this.f12382d = (TextView) viewGroup.findViewById(C1373R.id.share_menu_name);
        ViewGroup viewGroup2 = (ViewGroup) from.inflate(C1373R.layout.share_menu_item, null);
        viewGroup2.setId(C1373R.id.share_item_wechat);
        this.f12383e = (ImageView) viewGroup2.findViewById(C1373R.id.share_menu_icon);
        this.f12384f = (TextView) viewGroup2.findViewById(C1373R.id.share_menu_name);
        ViewGroup viewGroup3 = (ViewGroup) from.inflate(C1373R.layout.share_menu_item, null);
        viewGroup3.setId(C1373R.id.share_item_qq);
        this.f12385g = (ImageView) viewGroup3.findViewById(C1373R.id.share_menu_icon);
        this.f12386h = (TextView) viewGroup3.findViewById(C1373R.id.share_menu_name);
        ViewGroup viewGroup4 = (ViewGroup) from.inflate(C1373R.layout.share_menu_item, null);
        viewGroup4.setId(C1373R.id.share_item_weibo);
        this.f12387i = (ImageView) viewGroup4.findViewById(C1373R.id.share_menu_icon);
        this.f12388j = (TextView) viewGroup4.findViewById(C1373R.id.share_menu_name);
        ViewGroup viewGroup5 = (ViewGroup) from.inflate(C1373R.layout.share_menu_item, null);
        viewGroup5.setId(C1373R.id.share_item_facebook);
        this.f12389k = (ImageView) viewGroup5.findViewById(C1373R.id.share_menu_icon);
        this.f12390l = (TextView) viewGroup5.findViewById(C1373R.id.share_menu_name);
        ViewGroup viewGroup6 = (ViewGroup) from.inflate(C1373R.layout.share_menu_item, null);
        viewGroup6.setId(C1373R.id.share_item_twitter);
        this.f12391m = (ImageView) viewGroup6.findViewById(C1373R.id.share_menu_icon);
        this.f12392n = (TextView) viewGroup6.findViewById(C1373R.id.share_menu_name);
        m13191b();
        if (C1849a.m9641a()) {
            linearLayout.addView(viewGroup);
            linearLayout.addView(viewGroup2);
            linearLayout.addView(viewGroup3);
            linearLayout.addView(viewGroup4);
            linearLayout.addView(viewGroup5);
            linearLayout.addView(viewGroup6);
        } else {
            linearLayout.addView(viewGroup5);
            linearLayout.addView(viewGroup6);
            linearLayout.addView(viewGroup);
            linearLayout.addView(viewGroup2);
            linearLayout.addView(viewGroup3);
            linearLayout.addView(viewGroup4);
        }
        if (this.f12395q) {
            ViewGroup viewGroup7 = (ViewGroup) from.inflate(C1373R.layout.share_menu_item, null);
            viewGroup7.setId(C1373R.id.share_item_sdcard);
            ((ImageView) viewGroup7.findViewById(C1373R.id.share_menu_icon)).setImageResource(C1373R.drawable.share_icon_download);
            ((TextView) viewGroup7.findViewById(C1373R.id.share_menu_name)).setText(C1373R.string.activity_finished_menu_save);
            viewGroup7.setOnClickListener(this);
            linearLayout.addView(viewGroup7);
        }
        viewGroup6.setOnClickListener(this);
        viewGroup.setOnClickListener(this);
        viewGroup2.setOnClickListener(this);
        viewGroup3.setOnClickListener(this);
        viewGroup4.setOnClickListener(this);
        viewGroup5.setOnClickListener(this);
        setContentView(this.f12380b);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(C1373R.style.AnimBottom);
        setBackgroundDrawable(new ColorDrawable(0));
        setInputMethodMode(1);
        setSoftInputMode(16);
        this.f12380b.setOnTouchListener(new C26541(this));
    }

    /* renamed from: b */
    private void m13191b() {
        this.f12381c.setImageResource(C1373R.drawable.share_icon_moments);
        this.f12382d.setText(C1373R.string.activity_finished_menu_wechat_friend);
        this.f12383e.setImageResource(C1373R.drawable.share_icon_wechat);
        this.f12384f.setText(C1373R.string.activity_finished_menu_wechat);
        this.f12385g.setImageResource(C1373R.drawable.share_icon_qq);
        this.f12386h.setText(C1373R.string.activity_finished_menu_qq);
        this.f12387i.setImageResource(C1373R.drawable.share_icon_weibo);
        this.f12388j.setText(C1373R.string.activity_finished_menu_weibo);
        this.f12389k.setImageResource(C1373R.drawable.ic_account_facebook_share);
        this.f12390l.setText(C1373R.string.activity_account_management_facebook_str);
        this.f12391m.setImageResource(C1373R.drawable.ic_account_twitter_share);
        this.f12392n.setText(C1373R.string.activity_account_management_twitter_str);
    }

    /* renamed from: a */
    public void m13192a(String str) {
        this.f12397s.setText(str);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.share_item_facebook:
                if (!TextUtils.isEmpty(this.f12396r)) {
                    C2580w.m12905a(this.f12379a, this.f12396r + "—分享到Facebook", null);
                }
                this.f12394p.m13168a(this.f12393o);
                break;
            case C1373R.id.share_item_qq:
                if (!TextUtils.isEmpty(this.f12396r)) {
                    C2580w.m12905a(this.f12379a, this.f12396r + "—分享到QQ好友", null);
                }
                this.f12394p.m13172e(this.f12393o);
                break;
            case C1373R.id.share_item_sdcard:
                if (!TextUtils.isEmpty(this.f12396r)) {
                    C2580w.m12905a(this.f12379a, this.f12396r + "—图片保存到本地", null);
                }
                this.f12394p.m13167a();
                break;
            case C1373R.id.share_item_twitter:
                if (!TextUtils.isEmpty(this.f12396r)) {
                    C2580w.m12905a(this.f12379a, this.f12396r + "—分享到Twitter", null);
                }
                this.f12394p.m13169b(this.f12393o);
                break;
            case C1373R.id.share_item_wechat:
                if (!TextUtils.isEmpty(this.f12396r)) {
                    C2580w.m12905a(this.f12379a, this.f12396r + "—分享到微信朋友", null);
                }
                this.f12394p.m13170c(this.f12393o);
                break;
            case C1373R.id.share_item_wechat_moments:
                if (!TextUtils.isEmpty(this.f12396r)) {
                    C2580w.m12905a(this.f12379a, this.f12396r + "—分享到微信朋友圈", null);
                }
                this.f12394p.m13171d(this.f12393o);
                break;
            case C1373R.id.share_item_weibo:
                if (!TextUtils.isEmpty(this.f12396r)) {
                    C2580w.m12905a(this.f12379a, this.f12396r + "—分享到新浪微博", null);
                }
                this.f12394p.m13173f(this.f12393o);
                break;
        }
        dismiss();
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
    }

    public void dismiss() {
        super.dismiss();
    }
}

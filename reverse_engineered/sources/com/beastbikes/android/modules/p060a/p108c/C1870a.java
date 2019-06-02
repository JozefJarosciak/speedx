package com.beastbikes.android.modules.p060a.p108c;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.p060a.p061a.C1866a;
import com.beastbikes.android.modules.p060a.p107b.C1867a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import com.beastbikes.framework.ui.android.WebActivity;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.squareup.picasso.Picasso;

/* compiled from: AdBannerView */
/* renamed from: com.beastbikes.android.modules.a.c.a */
public class C1870a extends LinearLayout {
    /* renamed from: a */
    SessionFragmentActivity f8398a;
    /* renamed from: b */
    private double f8399b;
    /* renamed from: c */
    private double f8400c;
    /* renamed from: d */
    private ImageView f8401d;

    /* compiled from: AdBannerView */
    /* renamed from: com.beastbikes.android.modules.a.c.a$1 */
    class C18681 extends AsyncTask<Void, Void, C1867a> {
        /* renamed from: a */
        final /* synthetic */ C1870a f8395a;

        C18681(C1870a c1870a) {
            this.f8395a = c1870a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9714a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9715a((C1867a) obj);
        }

        /* renamed from: a */
        protected C1867a m9714a(Void... voidArr) {
            try {
                return new C1866a(this.f8395a.f8398a).m9711a();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m9715a(C1867a c1867a) {
            super.onPostExecute(c1867a);
            if (c1867a != null) {
                this.f8395a.m9717a(c1867a);
            }
        }
    }

    public C1870a(Context context, double d) {
        super(context);
        this.f8399b = d;
        m9716a();
    }

    public C1870a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9716a();
    }

    /* renamed from: a */
    private void m9716a() {
        if (getContext() instanceof BaseFragmentActivity) {
            this.f8398a = (SessionFragmentActivity) getContext();
        }
        setVisibility(8);
        getAdInfo();
    }

    private void getAdInfo() {
        this.f8398a.getAsyncTaskQueue().m13740a(new C18681(this), new Void[0]);
    }

    /* renamed from: a */
    private void m9717a(final C1867a c1867a) {
        this.f8401d = new ImageView(getContext());
        if (TextUtils.isEmpty(c1867a.m9713b())) {
            setVisibility(8);
        } else {
            setVisibility(0);
            int dip2px = DensityUtil.dip2px(getContext(), 20.0f);
            int dip2px2 = DensityUtil.dip2px(getContext(), 10.0f);
            this.f8399b = (this.f8399b - ((double) dip2px2)) - ((double) dip2px2);
            setPadding(dip2px2, dip2px, dip2px2, dip2px);
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.f8400c = (double) ((int) (this.f8399b / 3.9285714626312256d));
            this.f8401d.setLayoutParams(new LayoutParams((int) this.f8399b, (int) this.f8400c));
            this.f8401d.setScaleType(ScaleType.CENTER_INSIDE);
            addView(this.f8401d);
            Picasso.with(getContext()).load(c1867a.m9713b()).fit().error(C1373R.drawable.transparent).placeholder(C1373R.drawable.transparent).centerInside().into(this.f8401d);
        }
        setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1870a f8397b;

            public void onClick(View view) {
                if (!TextUtils.isEmpty(c1867a.m9712a()) && !TextUtils.isEmpty(c1867a.m9713b())) {
                    C2580w.m12905a(this.f8397b.f8398a, "", "click_more_banner");
                    Uri parse = Uri.parse(c1867a.m9712a());
                    Intent intent = new Intent(this.f8397b.f8398a, BrowserActivity.class);
                    intent.setData(parse);
                    intent.setPackage(this.f8397b.f8398a.getPackageName());
                    intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
                    intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
                    intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
                    this.f8397b.f8398a.startActivity(intent);
                }
            }
        });
    }
}

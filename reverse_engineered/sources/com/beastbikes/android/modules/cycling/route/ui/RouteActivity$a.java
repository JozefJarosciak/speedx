package com.beastbikes.android.modules.cycling.route.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

final class RouteActivity$a extends PagerAdapter {
    /* renamed from: a */
    final /* synthetic */ RouteActivity f10290a;
    /* renamed from: b */
    private List<View> f10291b = new ArrayList();

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RouteActivity$a$1 */
    class C21911 implements Callback {
        /* renamed from: a */
        final /* synthetic */ RouteActivity$a f10289a;

        C21911(RouteActivity$a routeActivity$a) {
            this.f10289a = routeActivity$a;
        }

        public void onSuccess() {
            RouteActivity.b(this.f10289a.f10290a).setVisibility(8);
        }

        public void onError() {
            RouteActivity.b(this.f10289a.f10290a).setVisibility(8);
        }
    }

    public RouteActivity$a(RouteActivity routeActivity, List<View> list) {
        this.f10290a = routeActivity;
        this.f10291b = list;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (i >= this.f10291b.size()) {
            return null;
        }
        ImageView imageView = (ImageView) this.f10291b.get(i);
        if (TextUtils.isEmpty((CharSequence) RouteActivity.r(this.f10290a).get(i))) {
            RouteActivity.w(this.f10290a).setImageResource(C1373R.drawable.transparent);
        } else {
            Picasso.with(this.f10290a).load((String) RouteActivity.r(this.f10290a).get(i)).fit().centerCrop().error(C1373R.drawable.transparent).placeholder(C1373R.drawable.transparent).into(RouteActivity.w(this.f10290a), new C21911(this));
        }
        ((ViewPager) viewGroup).addView((View) this.f10291b.get(i));
        return imageView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView((View) this.f10291b.get(i));
    }

    public int getCount() {
        return this.f10291b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}

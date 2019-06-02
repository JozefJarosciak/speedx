package com.beastbikes.android.modules.cycling.route.dto;

import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* compiled from: RouteCommentAdapter */
final class c$a extends ViewHolder<C2190d> {
    /* renamed from: a */
    final /* synthetic */ C2189c f5435a;
    @C1458a(a = 2131757492)
    /* renamed from: b */
    private CircleImageView f5436b;
    @C1458a(a = 2131757493)
    /* renamed from: c */
    private TextView f5437c;
    @C1458a(a = 2131757494)
    /* renamed from: d */
    private TextView f5438d;
    @C1458a(a = 2131757496)
    /* renamed from: e */
    private TextView f5439e;

    public /* synthetic */ void bind(Object obj) {
        m6745a((C2190d) obj);
    }

    protected c$a(C2189c c2189c, View view) {
        this.f5435a = c2189c;
        super(view);
    }

    /* renamed from: a */
    public void m6745a(C2190d c2190d) {
        String f = c2190d.f();
        if (TextUtils.isEmpty(f)) {
            this.f5436b.setImageResource(C1373R.drawable.ic_avatar);
        } else {
            Picasso.with(getContext()).load(f).fit().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f5436b);
        }
        this.f5437c.setText(c2190d.b());
        CharSequence relativeTimeSpanString = DateUtils.getRelativeTimeSpanString(c2190d.d().getTime());
        if (System.currentTimeMillis() - c2190d.d().getTime() < ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            this.f5438d.setText(simpleDateFormat.format(Long.valueOf(c2190d.d().getTime())));
        } else {
            this.f5438d.setText(relativeTimeSpanString);
        }
        this.f5439e.setText(c2190d.c());
    }
}

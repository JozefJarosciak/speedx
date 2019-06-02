package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ImageInfo;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedImageDetailsActivity;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.beastbikes.framework.ui.android.lib.view.AutoWrapViewGroup;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Stack;

/* compiled from: FeedItemImage */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.f */
public class C2130f extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    public static final int f9960a = ((DensityUtil.getWidth(BeastBikes.j()) * 9) / 22);
    /* renamed from: b */
    public static final int f9961b = ((DensityUtil.getWidth(BeastBikes.j()) * 9) / 38);
    /* renamed from: c */
    public static final int f9962c = ((DensityUtil.getWidth(BeastBikes.j()) * 9) / 31);
    /* renamed from: d */
    private static final Stack<WeakReference<ImageView>> f9963d = new Stack();
    /* renamed from: e */
    private ArrayList<String> f9964e = new ArrayList();
    /* renamed from: f */
    private Context f9965f;
    /* renamed from: g */
    private int f9966g;
    /* renamed from: h */
    private int f9967h;
    /* renamed from: i */
    private int f9968i;
    /* renamed from: j */
    private int f9969j;
    /* renamed from: k */
    private int f9970k;
    /* renamed from: l */
    private int f9971l;
    /* renamed from: m */
    private final AutoWrapViewGroup f9972m;

    public C2130f(Context context) {
        super(context);
        this.f9965f = context;
        setOrientation(1);
        this.f9972m = new AutoWrapViewGroup(context);
        addView(this.f9972m);
    }

    /* renamed from: a */
    public void m10996a(ArrayList<ImageInfo> arrayList) {
        this.f9968i = f9961b;
        this.f9966g = 3;
        this.f9970k = this.f9968i / 20;
        this.f9971l = this.f9968i / 20;
        if (arrayList != null) {
            this.f9967h = arrayList.size() / 3;
            if (arrayList.size() == 4 || arrayList.size() == 2) {
                this.f9968i = f9962c;
                this.f9966g = 2;
                this.f9967h = 2;
            } else if (arrayList.size() == 1) {
                this.f9968i = f9960a;
                this.f9966g = 1;
                this.f9967h = 1;
            }
            m10995a(this.f9966g, this.f9967h, this.f9968i, this.f9970k, this.f9971l);
            this.f9972m.removeAllViews();
            this.f9964e.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                m10994a(((ImageInfo) arrayList.get(i)).getUrl(), i);
                this.f9964e.add(((ImageInfo) arrayList.get(i)).getUrl());
            }
        }
    }

    public View getView() {
        return this;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.f9965f, ClubFeedImageDetailsActivity.class);
        intent.putStringArrayListExtra("images", this.f9964e);
        intent.putExtra("position", view.getId());
        intent.putExtra("canDel", false);
        this.f9965f.startActivity(intent);
    }

    /* renamed from: a */
    public void m10995a(int i, int i2, int i3, int i4, int i5) {
        this.f9969j = 2;
        setPadding(0, this.f9969j, this.f9969j, this.f9969j);
        this.f9972m.setHorizontalInterval(i4);
        this.f9972m.setVerticalInterval(i5);
        this.f9972m.setChildSize(i3);
        this.f9972m.setChildRowCount(i);
    }

    /* renamed from: a */
    private void m10994a(String str, int i) {
        View reusedImageView = getReusedImageView();
        reusedImageView.setTag(str);
        reusedImageView.setId(i);
        reusedImageView.setOnClickListener(this);
        Object obj = str.startsWith("http://") ? str + "?imageView2/2/w/" + ((this.f9968i * 4) / 5) : "file://" + str;
        if (!TextUtils.isEmpty(obj)) {
            Picasso.with(this.f9965f).load(obj).fit().error(C1373R.drawable.bg_1b1b1b).placeholder(C1373R.drawable.bg_1b1b1b).centerCrop().into(reusedImageView);
        }
        this.f9972m.addView(reusedImageView, i);
    }

    private ImageView getReusedImageView() {
        if (!(f9963d == null || f9963d.isEmpty())) {
            WeakReference weakReference = (WeakReference) f9963d.pop();
            while (weakReference.get() == null && !f9963d.isEmpty()) {
                weakReference = (WeakReference) f9963d.pop();
            }
            if (weakReference.get() != null) {
                return (ImageView) weakReference.get();
            }
        }
        ImageView imageView = new ImageView(this.f9965f);
        imageView.setScaleType(ScaleType.CENTER_CROP);
        imageView.setOnClickListener(this);
        return imageView;
    }
}

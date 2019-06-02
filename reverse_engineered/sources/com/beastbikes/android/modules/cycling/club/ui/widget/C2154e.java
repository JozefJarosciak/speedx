package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedImageDetailsActivity;
import com.beastbikes.android.widget.multiimageselector.MultiImageSelectorActivity;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.beastbikes.framework.ui.android.lib.view.AutoWrapViewGroup;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/* compiled from: EditPhotoView */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.e */
public class C2154e extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    public static final int f10097a = ((DensityUtil.getWidth(BeastBikes.j()) * 9) / 38);
    /* renamed from: b */
    private static final Stack<WeakReference<ImageView>> f10098b = new Stack();
    /* renamed from: c */
    private ArrayList<String> f10099c = new ArrayList();
    /* renamed from: d */
    private Activity f10100d;
    /* renamed from: e */
    private int f10101e;
    /* renamed from: f */
    private int f10102f;
    /* renamed from: g */
    private int f10103g;
    /* renamed from: h */
    private int f10104h;
    /* renamed from: i */
    private int f10105i;
    /* renamed from: j */
    private int f10106j;
    /* renamed from: k */
    private final AutoWrapViewGroup f10107k;
    /* renamed from: l */
    private final ImageView f10108l;
    /* renamed from: m */
    private int f10109m = C1373R.drawable.ic_add_image;
    /* renamed from: n */
    private OnCreateContextMenuListener f10110n;
    /* renamed from: o */
    private C2153a f10111o;

    /* compiled from: EditPhotoView */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.e$a */
    public interface C2153a {
        /* renamed from: d */
        int m11054d();
    }

    public C2154e(Activity activity, C2153a c2153a) {
        super(activity);
        this.f10100d = activity;
        this.f10103g = f10097a;
        this.f10101e = 3;
        this.f10102f = 3;
        this.f10111o = c2153a;
        setOrientation(1);
        this.f10107k = new AutoWrapViewGroup(activity);
        addView(this.f10107k);
        this.f10105i = this.f10103g / 9;
        this.f10106j = this.f10103g / 9;
        m11058a(this.f10101e, this.f10102f, this.f10103g, this.f10105i, this.f10106j);
        this.f10108l = new ImageView(activity);
        this.f10108l.setBackgroundColor(activity.getResources().getColor(C1373R.color.common_bg_color));
        this.f10108l.setImageResource(this.f10109m);
        this.f10108l.setScaleType(ScaleType.CENTER);
        this.f10108l.setOnClickListener(this);
        this.f10107k.addView(this.f10108l);
    }

    /* renamed from: a */
    public void m11058a(int i, int i2, int i3, int i4, int i5) {
        setPadding(i4, 0, 0, i4);
        this.f10107k.setHorizontalInterval(i4);
        this.f10107k.setVerticalInterval(i5);
        this.f10107k.setChildSize(i3);
        this.f10107k.setChildRowCount(i);
    }

    public void setAddResource(int i) {
        this.f10109m = i;
        if (this.f10108l != null) {
            this.f10108l.setImageDrawable(this.f10100d.getResources().getDrawable(i));
        }
    }

    public void setMenuListener(OnCreateContextMenuListener onCreateContextMenuListener) {
        this.f10110n = onCreateContextMenuListener;
    }

    public int getContentHeight() {
        return ((this.f10102f * this.f10103g) + ((this.f10102f - 1) * this.f10106j)) + (this.f10104h * 2);
    }

    /* renamed from: a */
    public void m11057a() {
        this.f10107k.removeAllViews();
        this.f10107k.addView(this.f10108l);
    }

    public ArrayList<String> getSelectedFiles() {
        int childCount = this.f10107k.getChildCount();
        if (childCount <= 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) this.f10107k.getChildAt(i);
            if (!imageView.equals(this.f10108l)) {
                arrayList.add((String) imageView.getTag());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m11055a(ImageView imageView) {
        this.f10107k.addView(imageView, this.f10107k.getChildCount() - 1);
        this.f10108l.setVisibility(this.f10107k.getChildCount() > this.f10101e * this.f10102f ? 8 : 0);
    }

    /* renamed from: a */
    public void m11059a(int i, int i2, Intent intent) {
        switch (i) {
            case 1009:
                if (intent != null) {
                    this.f10099c = intent.getStringArrayListExtra("select_result");
                    m11057a();
                    Iterator it = this.f10099c.iterator();
                    int i3 = 0;
                    while (it.hasNext()) {
                        m11056a((String) it.next(), i3);
                        i3++;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m11056a(String str, int i) {
        ImageView reusedImageView = getReusedImageView();
        reusedImageView.setTag(str);
        reusedImageView.setId(i);
        reusedImageView.setOnCreateContextMenuListener(this.f10110n);
        if (TextUtils.isEmpty(str)) {
            reusedImageView.setImageResource(C1373R.drawable.multi_image_selector_default_error);
        } else {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i2 = this.f10103g;
            if (options.outWidth > 0) {
                i2 = (int) ((((float) options.outHeight) / ((float) options.outWidth)) * ((float) this.f10103g));
            }
            Picasso.with(getContext()).load("file://" + str).resize(this.f10103g, i2).error(C1373R.drawable.multi_image_selector_default_error).placeholder(C1373R.drawable.multi_image_selector_default_error).into(reusedImageView);
        }
        m11055a(reusedImageView);
    }

    public void onClick(View view) {
        int i = 9;
        if (view == this.f10108l) {
            Intent intent = new Intent(this.f10100d, MultiImageSelectorActivity.class);
            if (this.f10111o.m11054d() < 9) {
                i = this.f10111o.m11054d();
                intent.putExtra("gallery_full", true);
            }
            intent.putExtra("max_select_count", i);
            intent.putStringArrayListExtra("default_list", this.f10099c);
            this.f10100d.startActivityForResult(intent, 1009);
            return;
        }
        Intent intent2 = new Intent(this.f10100d, ClubFeedImageDetailsActivity.class);
        intent2.putStringArrayListExtra("images", this.f10099c);
        intent2.putExtra("position", view.getId());
        this.f10100d.startActivityForResult(intent2, 1009);
    }

    private ImageView getReusedImageView() {
        if (!(f10098b == null || f10098b.isEmpty())) {
            WeakReference weakReference = (WeakReference) f10098b.pop();
            while (weakReference.get() == null && !f10098b.isEmpty()) {
                weakReference = (WeakReference) f10098b.pop();
            }
            if (weakReference.get() != null) {
                return (ImageView) weakReference.get();
            }
        }
        ImageView imageView = new ImageView(this.f10100d);
        imageView.setScaleType(ScaleType.CENTER_CROP);
        imageView.setOnClickListener(this);
        return imageView;
    }
}

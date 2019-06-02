package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.multiimageselector.utils.TouchImageView;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ClubFeedImageDetailsActivity$a extends PagerAdapter {
    /* renamed from: b */
    static final /* synthetic */ boolean f9621b = (!ClubFeedImageDetailsActivity.class.desiredAssertionStatus());
    /* renamed from: a */
    public List<String> f9622a = null;
    /* renamed from: c */
    final /* synthetic */ ClubFeedImageDetailsActivity f9623c;
    /* renamed from: d */
    private int f9624d = ((DensityUtil.getWidth(BeastBikes.j()) * 9) / 15);
    /* renamed from: e */
    private LayoutInflater f9625e;

    public ClubFeedImageDetailsActivity$a(ClubFeedImageDetailsActivity clubFeedImageDetailsActivity, Context context, List<String> list) {
        this.f9623c = clubFeedImageDetailsActivity;
        this.f9622a = list;
        this.f9625e = LayoutInflater.from(context);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public int getCount() {
        return this.f9622a.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = this.f9625e.inflate(C1373R.layout.clubfeed_image_deatils, viewGroup, false);
        if (f9621b || inflate != null) {
            TouchImageView touchImageView = (TouchImageView) inflate.findViewById(C1373R.id.image);
            final ProgressBar progressBar = (ProgressBar) inflate.findViewById(C1373R.id.loading);
            if (this.f9622a.size() <= 0) {
                return null;
            }
            String str;
            if (((String) this.f9622a.get(i)).contains("http://") || ((String) this.f9622a.get(i)).contains("https://")) {
                str = ((String) this.f9622a.get(i)) + (ClubFeedImageDetailsActivity.j(this.f9623c) ? "?imageView2/2/w/" + this.f9624d : "");
            } else {
                str = "file://" + ((String) this.f9622a.get(i));
            }
            if (TextUtils.isEmpty(str)) {
                touchImageView.setImageResource(C1373R.drawable.multi_image_selector_default_error);
            } else {
                progressBar.setVisibility(0);
                Picasso.with(this.f9623c.getApplicationContext()).load(str).error(C1373R.drawable.multi_image_selector_default_error).placeholder(C1373R.drawable.multi_image_selector_default_error).into(touchImageView, new Callback(this) {
                    /* renamed from: b */
                    final /* synthetic */ ClubFeedImageDetailsActivity$a f9618b;

                    public void onSuccess() {
                        progressBar.setVisibility(8);
                    }

                    public void onError() {
                        progressBar.setVisibility(8);
                    }
                });
            }
            touchImageView.setOnLongClickListener(new OnLongClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ ClubFeedImageDetailsActivity$a f9620b;

                public boolean onLongClick(View view) {
                    ClubFeedImageDetailsActivity.a(this.f9620b.f9623c, str);
                    return false;
                }
            });
            viewGroup.addView(inflate, 0);
            return inflate;
        }
        throw new AssertionError();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }
}

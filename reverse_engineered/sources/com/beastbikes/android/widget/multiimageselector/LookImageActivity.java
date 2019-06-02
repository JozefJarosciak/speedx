package com.beastbikes.android.widget.multiimageselector;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.multiimageselector.utils.TouchImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

public class LookImageActivity extends Activity implements OnClickListener {
    /* renamed from: a */
    private ArrayList<String> f12623a = null;
    /* renamed from: b */
    private ViewPager f12624b;
    /* renamed from: c */
    private int f12625c = 0;
    /* renamed from: d */
    private C2701a f12626d;
    /* renamed from: e */
    private double f12627e;
    /* renamed from: f */
    private double f12628f;
    /* renamed from: g */
    private ImageView f12629g;

    /* renamed from: com.beastbikes.android.widget.multiimageselector.LookImageActivity$1 */
    class C26991 implements OnPageChangeListener {
        /* renamed from: a */
        final /* synthetic */ LookImageActivity f12617a;

        C26991(LookImageActivity lookImageActivity) {
            this.f12617a = lookImageActivity;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            this.f12617a.f12625c = i;
            this.f12617a.setTitle((i + 1) + "/" + this.f12617a.f12623a.size());
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.LookImageActivity$2 */
    class C27002 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ LookImageActivity f12618a;

        C27002(LookImageActivity lookImageActivity) {
            this.f12618a = lookImageActivity;
        }

        public void onClick(View view) {
            this.f12618a.finish();
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.LookImageActivity$a */
    public class C2701a extends PagerAdapter {
        /* renamed from: b */
        static final /* synthetic */ boolean f12619b = (!LookImageActivity.class.desiredAssertionStatus());
        /* renamed from: a */
        public List<String> f12620a = null;
        /* renamed from: c */
        final /* synthetic */ LookImageActivity f12621c;
        /* renamed from: d */
        private LayoutInflater f12622d;

        public C2701a(LookImageActivity lookImageActivity, Context context, List<String> list) {
            this.f12621c = lookImageActivity;
            this.f12620a = list;
            this.f12622d = LayoutInflater.from(context);
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public int getCount() {
            return this.f12620a.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = this.f12622d.inflate(C1373R.layout.multi_image_selector_image_deatils, viewGroup, false);
            if (f12619b || inflate != null) {
                TouchImageView touchImageView = (TouchImageView) inflate.findViewById(C1373R.id.iv_image);
                if (this.f12620a.size() <= 0) {
                    return null;
                }
                if (((String) this.f12620a.get(i)).contains(HttpHost.DEFAULT_SCHEME_NAME)) {
                    Picasso.with(viewGroup.getContext()).load((String) this.f12620a.get(i)).into(touchImageView);
                } else {
                    new Builder(viewGroup.getContext()).build().load(new File((String) this.f12620a.get(i))).placeholder(C1373R.drawable.multi_image_selector_default_error).error(C1373R.drawable.multi_image_selector_default_error).centerCrop().resize((int) this.f12621c.f12627e, (int) this.f12621c.f12628f).into(touchImageView);
                }
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

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.multi_image_selector_image_pager);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f12624b = (ViewPager) findViewById(C1373R.id.pager);
        Bundle extras = getIntent().getExtras();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.f12627e = (double) displayMetrics.widthPixels;
        this.f12628f = (double) displayMetrics.heightPixels;
        if (extras != null) {
            this.f12623a = extras.getStringArrayList("images");
            this.f12625c = extras.getInt("position", 0);
            ViewPager viewPager = this.f12624b;
            PagerAdapter c2701a = new C2701a(this, this, this.f12623a);
            this.f12626d = c2701a;
            viewPager.setAdapter(c2701a);
            this.f12624b.setCurrentItem(this.f12625c);
            setTitle((this.f12625c + 1) + "/" + this.f12623a.size());
            this.f12624b.setOnPageChangeListener(new C26991(this));
        }
        this.f12629g = (ImageView) findViewById(C1373R.id.btn_back);
        this.f12629g.setOnClickListener(new C27002(this));
        ((Button) findViewById(C1373R.id.commit)).setVisibility(4);
    }

    public void onClick(View view) {
    }

    public void finish() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra("select_result", this.f12623a);
        setResult(-1, intent);
        super.finish();
    }
}

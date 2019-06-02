package com.beastbikes.android.modules.cycling.stage.p136e;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.utils.C2553b;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: StageDetailShareView */
/* renamed from: com.beastbikes.android.modules.cycling.stage.e.a */
public class C2266a extends LinearLayout {
    /* renamed from: a */
    private ScrollView f10758a;
    /* renamed from: b */
    private TextView f10759b;
    /* renamed from: c */
    private ImageView f10760c;
    /* renamed from: d */
    private ImageView f10761d;
    /* renamed from: e */
    private ImageView f10762e;
    /* renamed from: f */
    private boolean f10763f;
    /* renamed from: g */
    private int f10764g;

    /* compiled from: StageDetailShareView */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.e.a$a */
    public interface C2248a {
        /* renamed from: a */
        void mo3437a(Bitmap bitmap);
    }

    public C2266a(Context context, boolean z, int i) {
        super(context);
        this.f10763f = z;
        this.f10764g = i;
        m11611a(context);
    }

    /* renamed from: a */
    private void m11611a(Context context) {
        LayoutInflater.from(context).inflate(C1373R.layout.layout_stage_detail_share_view, this);
        this.f10758a = (ScrollView) findViewById(C1373R.id.scrollView_share_stage_detail);
        this.f10759b = (TextView) findViewById(C1373R.id.tv_share_title_date);
        this.f10760c = (ImageView) findViewById(C1373R.id.img_share_stage_content_map);
        this.f10761d = (ImageView) findViewById(C1373R.id.img_share_stage_base);
        this.f10762e = (ImageView) findViewById(C1373R.id.img_share_stage_content);
        LayoutParams layoutParams = (LayoutParams) this.f10761d.getLayoutParams();
        if (this.f10763f) {
            layoutParams.topMargin = this.f10764g;
            this.f10761d.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: a */
    public void m11615a(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, C2248a c2248a) {
        if (c2248a == null) {
            throw new IllegalArgumentException("ShareBuildListener may not be null");
        }
        this.f10759b.setText(new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA).format(new Date()));
        this.f10760c.setImageBitmap(bitmap3);
        this.f10761d.setImageBitmap(bitmap);
        this.f10762e.setImageBitmap(bitmap2);
        final C2248a c2248a2 = c2248a;
        final Bitmap bitmap4 = bitmap3;
        final Bitmap bitmap5 = bitmap;
        final Bitmap bitmap6 = bitmap2;
        this.f10758a.postDelayed(new Runnable(this) {
            /* renamed from: e */
            final /* synthetic */ C2266a f10757e;

            public void run() {
                Bitmap a = C2553b.m12780a(this.f10757e.f10758a);
                c2248a2.mo3437a(a);
                this.f10757e.f10760c.setImageBitmap(null);
                this.f10757e.f10761d.setImageBitmap(null);
                this.f10757e.f10762e.setImageBitmap(null);
                if (!(bitmap4 == null || bitmap4.isRecycled())) {
                    bitmap4.recycle();
                }
                if (!(bitmap5 == null || bitmap5.isRecycled())) {
                    bitmap5.recycle();
                }
                if (!(bitmap6 == null || bitmap6.isRecycled())) {
                    bitmap6.recycle();
                }
                if (!(a == null || a.isRecycled())) {
                    a.recycle();
                }
                System.gc();
            }
        }, 0);
    }
}

package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.BaseActivity;
import com.squareup.picasso.Picasso;

@C1457a(a = "查看头像大图")
@C1459b(a = 2130903231)
public class AvatarViewer extends BaseActivity {
    @C1458a(a = 2131756222)
    /* renamed from: a */
    private ImageView f6292a;
    /* renamed from: b */
    private float f6293b = 0.0f;
    /* renamed from: c */
    private float f6294c = 0.0f;
    /* renamed from: d */
    private float f6295d = 0.0f;
    /* renamed from: e */
    private float f6296e = 0.0f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            this.f6292a.setLayoutParams(new LayoutParams(-1, displayMetrics.widthPixels));
            this.f6292a.setScaleType(ScaleType.CENTER_CROP);
            Object stringExtra = intent.getStringExtra("user_avatar_url");
            if (TextUtils.isEmpty(stringExtra)) {
                this.f6292a.setImageResource(C1373R.drawable.ic_avatar);
                return;
            }
            StringBuilder stringBuilder = new StringBuilder(stringExtra);
            stringBuilder.append("?imageView/2/w/").append(displayMetrics.widthPixels).append("/h/");
            stringBuilder.append(displayMetrics.widthPixels).append("/q/100/format/png");
            C1802i c1802i = new C1802i(this, getString(C1373R.string.str_loading), true);
            c1802i.show();
            if (TextUtils.isEmpty(stringBuilder.toString())) {
                this.f6292a.setImageResource(C1373R.drawable.ic_avatar);
                if (c1802i != null && this != null && !isFinishing()) {
                    c1802i.dismiss();
                    return;
                }
                return;
            }
            Picasso.with(this).load(stringBuilder.toString()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f6292a, new AvatarViewer$1(this, c1802i));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f6293b = motionEvent.getX();
            this.f6295d = motionEvent.getY();
        } else if (motionEvent.getAction() == 1) {
            this.f6294c = motionEvent.getX();
            this.f6296e = motionEvent.getY();
            if (Math.sqrt(Math.pow((double) (this.f6293b - this.f6294c), 2.0d) + Math.pow((double) (this.f6295d - this.f6296e), 2.0d)) < 20.0d) {
                finish();
            }
        }
        return true;
    }
}

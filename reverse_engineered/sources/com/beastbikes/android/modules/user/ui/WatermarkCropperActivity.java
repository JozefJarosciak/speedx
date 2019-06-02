package com.beastbikes.android.modules.user.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.filter.other.ImageCropView;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "水印裁剪页")
@C1459b(a = 2130903214)
public class WatermarkCropperActivity extends BaseFragmentActivity implements OnClickListener {
    /* renamed from: b */
    private static final Logger f6641b = LoggerFactory.getLogger(WatermarkCropperActivity.class);
    /* renamed from: c */
    private static final SimpleDateFormat f6642c = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());
    /* renamed from: a */
    private final DisplayMetrics f6643a = new DisplayMetrics();
    /* renamed from: d */
    private String f6644d;
    @C1458a(a = 2131756128)
    /* renamed from: e */
    private ImageCropView f6645e;
    @C1458a(a = 2131756129)
    /* renamed from: f */
    private ImageView f6646f;
    /* renamed from: g */
    private Bitmap f6647g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        getWindowManager().getDefaultDisplay().getMetrics(this.f6643a);
        this.f6645e.setLayoutParams(new LayoutParams(-1, this.f6643a.widthPixels));
        System.gc();
        this.f6644d = m7873a();
        this.f6646f.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent == null) {
            f6641b.debug("the bundle is null", getIntent().toString());
            finish();
            return;
        }
        if (!intent.hasExtra("path")) {
            C2621c c2621c = new C2621c(this);
            c2621c.b(C1373R.string.user_setting_activity_file_is_null);
            c2621c.b(C1373R.string.label_i_know, new WatermarkCropperActivity$1(this, c2621c)).a();
        }
        Uri uri = (Uri) intent.getParcelableExtra("path");
        Options options = new Options();
        ContentResolver contentResolver = getContentResolver();
        options.inSampleSize = 2;
        try {
            this.f6647g = BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, options);
        } catch (FileNotFoundException e) {
            this.f6647g = null;
        }
        this.f6645e.setImageBitmap(this.f6647g);
        this.f6645e.a();
    }

    /* renamed from: a */
    private String m7873a() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), String.format("Beast_%s.png", new Object[]{f6642c.format(new Date())})).getAbsolutePath();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cut_avatar_btn_ok:
                m7874a(this.f6645e.b());
                Intent intent = new Intent(this, WatermarkGalleryActivity.class);
                intent.putExtra("path", this.f6644d);
                setResult(4, intent);
                finish();
                return;
            case C1373R.id.cut_avatar_btn_cancle:
                finish();
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private boolean m7874a(Bitmap bitmap) {
        OutputStream fileOutputStream;
        boolean compress;
        Throwable th;
        CompressFormat compressFormat = CompressFormat.PNG;
        try {
            fileOutputStream = new FileOutputStream(this.f6644d);
            try {
                compress = bitmap.compress(compressFormat, 100, fileOutputStream);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e2) {
                compress = false;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return compress;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            fileOutputStream = null;
            compress = false;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return compress;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileOutputStream = null;
            th = th4;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
        return compress;
    }
}

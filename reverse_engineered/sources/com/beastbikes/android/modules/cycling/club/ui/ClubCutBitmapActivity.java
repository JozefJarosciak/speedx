package com.beastbikes.android.modules.cycling.club.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.ui.widget.ImageCut;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "图片按裁剪页")
@C1459b(a = 2130903090)
public class ClubCutBitmapActivity extends BaseFragmentActivity implements OnClickListener {
    /* renamed from: a */
    private static final Logger f5010a = LoggerFactory.getLogger(ClubCutBitmapActivity.class);
    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: b */
    private static final SimpleDateFormat f5011b = new SimpleDateFormat("yyyyMMddhhmmss");
    /* renamed from: c */
    private String f5012c;
    @C1458a(a = 2131755447)
    /* renamed from: d */
    private ImageCut f5013d;
    @C1458a(a = 2131755448)
    /* renamed from: e */
    private Button f5014e;
    @C1458a(a = 2131755449)
    /* renamed from: f */
    private Button f5015f;
    /* renamed from: g */
    private Bitmap f5016g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        System.gc();
        this.f5012c = m6343a();
        this.f5014e.setOnClickListener(this);
        this.f5015f.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            f5010a.debug("the bundle is null", getIntent().toString());
            finish();
            return;
        }
        String string = extras.getString("path");
        if (TextUtils.isEmpty(string)) {
            new Builder(this).setMessage(C1373R.string.user_setting_activity_file_is_null).setNegativeButton(C1373R.string.label_i_know, new ClubCutBitmapActivity$1(this)).show();
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(string, options);
        options.inJustDecodeBounds = false;
        options.inJustDecodeBounds = false;
        int i = options.outWidth;
        int i2 = options.outHeight;
        i = (i <= i2 || ((float) i) <= 800.0f) ? (i >= i2 || ((float) i2) <= 1280.0f) ? 1 : (int) (((float) options.outHeight) / 1280.0f) : (int) (((float) options.outWidth) / 800.0f);
        if (i <= 0) {
            i = 1;
        }
        options.inSampleSize = i;
        i = m6342a(string);
        this.f5016g = BitmapFactory.decodeFile(string, options);
        if (i == 90) {
            Matrix matrix = new Matrix();
            matrix.reset();
            matrix.setRotate(90.0f);
            Bitmap createBitmap = Bitmap.createBitmap(this.f5016g, 0, 0, this.f5016g.getWidth(), this.f5016g.getHeight(), matrix, true);
            if (this.f5016g != null) {
                this.f5016g.recycle();
                this.f5016g = null;
            }
            this.f5013d.setImageBitmap(createBitmap);
        } else {
            this.f5013d.setImageBitmap(this.f5016g);
        }
        this.f5013d.b();
    }

    /* renamed from: a */
    private String m6343a() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), String.format("Beast_%s.png", new Object[]{f5011b.format(new Date())})).getAbsolutePath();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cut_avatar_btn_ok:
                m6344a(this.f5013d.a());
                Intent intent = new Intent();
                intent.putExtra("path", this.f5012c);
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

    /* renamed from: a */
    private int m6342a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return Opcodes.GETFIELD;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (IOException e) {
            AVAnalytics.onError(this, "get ExifInterface error.");
            f5010a.debug("the ExifInterface info is :", (Object) str);
            return 0;
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private boolean m6344a(Bitmap bitmap) {
        OutputStream fileOutputStream;
        boolean compress;
        Throwable th;
        CompressFormat compressFormat = CompressFormat.PNG;
        try {
            fileOutputStream = new FileOutputStream(this.f5012c);
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

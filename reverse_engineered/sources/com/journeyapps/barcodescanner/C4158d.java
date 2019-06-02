package com.journeyapps.barcodescanner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.C4087R;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.Intents.Scan;
import com.journeyapps.barcodescanner.C4116c.C4119a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/* compiled from: CaptureManager */
/* renamed from: com.journeyapps.barcodescanner.d */
public class C4158d {
    /* renamed from: a */
    private static final String f14791a = C4158d.class.getSimpleName();
    /* renamed from: b */
    private static int f14792b = 250;
    /* renamed from: c */
    private Activity f14793c;
    /* renamed from: d */
    private DecoratedBarcodeView f14794d;
    /* renamed from: e */
    private int f14795e = -1;
    /* renamed from: f */
    private boolean f14796f = false;
    /* renamed from: g */
    private boolean f14797g = false;
    /* renamed from: h */
    private InactivityTimer f14798h;
    /* renamed from: i */
    private BeepManager f14799i;
    /* renamed from: j */
    private Handler f14800j;
    /* renamed from: k */
    private C1693a f14801k = new C41521(this);
    /* renamed from: l */
    private final C4119a f14802l = new C41532(this);
    /* renamed from: m */
    private boolean f14803m = false;

    /* compiled from: CaptureManager */
    /* renamed from: com.journeyapps.barcodescanner.d$1 */
    class C41521 implements C1693a {
        /* renamed from: a */
        final /* synthetic */ C4158d f14785a;

        C41521(C4158d c4158d) {
            this.f14785a = c4158d;
        }

        /* renamed from: a */
        public void mo3211a(final C4121b c4121b) {
            this.f14785a.f14794d.m16527a();
            this.f14785a.f14799i.playBeepSoundAndVibrate();
            this.f14785a.f14800j.postDelayed(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C41521 f14784b;

                public void run() {
                    this.f14784b.f14785a.m16661a(c4121b);
                }
            }, 150);
        }

        /* renamed from: a */
        public void mo3212a(List<ResultPoint> list) {
        }
    }

    /* compiled from: CaptureManager */
    /* renamed from: com.journeyapps.barcodescanner.d$2 */
    class C41532 implements C4119a {
        /* renamed from: a */
        final /* synthetic */ C4158d f14786a;

        C41532(C4158d c4158d) {
            this.f14786a = c4158d;
        }

        /* renamed from: a */
        public void mo5925a() {
        }

        /* renamed from: b */
        public void mo5927b() {
        }

        /* renamed from: c */
        public void mo5928c() {
        }

        /* renamed from: a */
        public void mo5926a(Exception exception) {
            this.f14786a.m16667g();
        }
    }

    /* compiled from: CaptureManager */
    /* renamed from: com.journeyapps.barcodescanner.d$3 */
    class C41543 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4158d f14787a;

        C41543(C4158d c4158d) {
            this.f14787a = c4158d;
        }

        public void run() {
            Log.d(C4158d.f14791a, "Finishing due to inactivity");
            this.f14787a.m16656j();
        }
    }

    /* compiled from: CaptureManager */
    /* renamed from: com.journeyapps.barcodescanner.d$4 */
    class C41554 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4158d f14788a;

        C41554(C4158d c4158d) {
            this.f14788a = c4158d;
        }

        public void run() {
            this.f14788a.m16666f();
        }
    }

    /* compiled from: CaptureManager */
    /* renamed from: com.journeyapps.barcodescanner.d$5 */
    class C41565 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4158d f14789a;

        C41565(C4158d c4158d) {
            this.f14789a = c4158d;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f14789a.m16656j();
        }
    }

    /* compiled from: CaptureManager */
    /* renamed from: com.journeyapps.barcodescanner.d$6 */
    class C41576 implements OnCancelListener {
        /* renamed from: a */
        final /* synthetic */ C4158d f14790a;

        C41576(C4158d c4158d) {
            this.f14790a = c4158d;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.f14790a.m16656j();
        }
    }

    public C4158d(Activity activity, DecoratedBarcodeView decoratedBarcodeView) {
        this.f14793c = activity;
        this.f14794d = decoratedBarcodeView;
        decoratedBarcodeView.getBarcodeView().m16500a(this.f14802l);
        this.f14800j = new Handler();
        this.f14798h = new InactivityTimer(activity, new C41543(this));
        this.f14799i = new BeepManager(activity);
    }

    /* renamed from: a */
    public void m16659a(Intent intent, Bundle bundle) {
        this.f14793c.getWindow().addFlags(128);
        if (bundle != null) {
            this.f14795e = bundle.getInt("SAVED_ORIENTATION_LOCK", -1);
        }
        if (intent != null) {
            if (this.f14795e == -1 && intent.getBooleanExtra(Scan.ORIENTATION_LOCKED, true)) {
                m16657a();
            }
            if (Scan.ACTION.equals(intent.getAction())) {
                this.f14794d.m16528a(intent);
            }
            if (!intent.getBooleanExtra(Scan.BEEP_ENABLED, true)) {
                this.f14799i.setBeepEnabled(false);
                this.f14799i.updatePrefs();
            }
            if (intent.hasExtra(Scan.TIMEOUT)) {
                this.f14800j.postDelayed(new C41554(this), intent.getLongExtra(Scan.TIMEOUT, 0));
            }
            if (intent.getBooleanExtra(Scan.BARCODE_IMAGE_ENABLED, false)) {
                this.f14796f = true;
            }
        }
    }

    /* renamed from: a */
    protected void m16657a() {
        int i = 0;
        if (this.f14795e == -1) {
            int rotation = this.f14793c.getWindowManager().getDefaultDisplay().getRotation();
            int i2 = this.f14793c.getResources().getConfiguration().orientation;
            if (i2 == 2) {
                if (!(rotation == 0 || rotation == 1)) {
                    i = 8;
                }
            } else if (i2 == 1) {
                i = (rotation == 0 || rotation == 3) ? 1 : 9;
            }
            this.f14795e = i;
        }
        this.f14793c.setRequestedOrientation(this.f14795e);
    }

    /* renamed from: b */
    public void m16662b() {
        this.f14794d.m16529a(this.f14801k);
    }

    /* renamed from: c */
    public void m16663c() {
        if (VERSION.SDK_INT >= 23) {
            m16655i();
        } else {
            this.f14794d.m16530b();
        }
        this.f14799i.updatePrefs();
        this.f14798h.start();
    }

    @TargetApi(23)
    /* renamed from: i */
    private void m16655i() {
        if (ContextCompat.checkSelfPermission(this.f14793c, "android.permission.CAMERA") == 0) {
            this.f14794d.m16530b();
        } else if (!this.f14803m) {
            ActivityCompat.requestPermissions(this.f14793c, new String[]{"android.permission.CAMERA"}, f14792b);
            this.f14803m = true;
        }
    }

    /* renamed from: a */
    public void m16658a(int i, String[] strArr, int[] iArr) {
        if (i != f14792b) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            m16667g();
        } else {
            this.f14794d.m16530b();
        }
    }

    /* renamed from: d */
    public void m16664d() {
        this.f14794d.m16527a();
        this.f14798h.cancel();
        this.f14799i.close();
    }

    /* renamed from: e */
    public void m16665e() {
        this.f14797g = true;
        this.f14798h.cancel();
    }

    /* renamed from: a */
    public void m16660a(Bundle bundle) {
        bundle.putInt("SAVED_ORIENTATION_LOCK", this.f14795e);
    }

    /* renamed from: a */
    public static Intent m16648a(C4121b c4121b, String str) {
        Intent intent = new Intent(Scan.ACTION);
        intent.addFlags(524288);
        intent.putExtra(Scan.RESULT, c4121b.toString());
        intent.putExtra(Scan.RESULT_FORMAT, c4121b.m16545d().toString());
        byte[] c = c4121b.m16544c();
        if (c != null && c.length > 0) {
            intent.putExtra(Scan.RESULT_BYTES, c);
        }
        Map e = c4121b.m16546e();
        if (e != null) {
            if (e.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra(Scan.RESULT_UPC_EAN_EXTENSION, e.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Number number = (Number) e.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent.putExtra(Scan.RESULT_ORIENTATION, number.intValue());
            }
            String str2 = (String) e.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str2 != null) {
                intent.putExtra(Scan.RESULT_ERROR_CORRECTION_LEVEL, str2);
            }
            Iterable<byte[]> iterable = (Iterable) e.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                int i = 0;
                for (byte[] c2 : iterable) {
                    intent.putExtra(Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, c2);
                    i++;
                }
            }
        }
        if (str != null) {
            intent.putExtra(Scan.RESULT_BARCODE_IMAGE_PATH, str);
        }
        return intent;
    }

    /* renamed from: b */
    private String m16651b(C4121b c4121b) {
        String str = null;
        if (this.f14796f) {
            Bitmap a = c4121b.m16542a();
            try {
                File createTempFile = File.createTempFile("barcodeimage", ".jpg", this.f14793c.getCacheDir());
                OutputStream fileOutputStream = new FileOutputStream(createTempFile);
                a.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                str = createTempFile.getAbsolutePath();
            } catch (IOException e) {
                Log.w(f14791a, "Unable to create temporary file and store bitmap! " + e);
            }
        }
        return str;
    }

    /* renamed from: j */
    private void m16656j() {
        this.f14793c.finish();
    }

    /* renamed from: f */
    protected void m16666f() {
        Intent intent = new Intent(Scan.ACTION);
        intent.putExtra(Scan.TIMEOUT, true);
        this.f14793c.setResult(0, intent);
        m16656j();
    }

    /* renamed from: a */
    protected void m16661a(C4121b c4121b) {
        this.f14793c.setResult(-1, C4158d.m16648a(c4121b, m16651b(c4121b)));
        m16656j();
    }

    /* renamed from: g */
    protected void m16667g() {
        if (!this.f14793c.isFinishing() && !this.f14797g) {
            Builder builder = new Builder(this.f14793c);
            builder.setTitle(this.f14793c.getString(C4087R.string.zxing_app_name));
            builder.setMessage(this.f14793c.getString(C4087R.string.zxing_msg_camera_framework_bug));
            builder.setPositiveButton(C4087R.string.zxing_button_ok, new C41565(this));
            builder.setOnCancelListener(new C41576(this));
            builder.show();
        }
    }
}

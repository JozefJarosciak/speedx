package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.alibaba.fastjson.asm.Opcodes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.filter.other.SaveImageView;
import com.beastbikes.android.modules.user.filter.other.SquareCameraPreview;
import com.beastbikes.android.utils.C2561i;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@C1459b(a = 2130903213)
public class WatermarkCameraActivity extends SessionFragmentActivity implements PictureCallback, Callback, OnClickListener {
    /* renamed from: a */
    private static final SimpleDateFormat f6619a = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());
    /* renamed from: b */
    private final DisplayMetrics f6620b = new DisplayMetrics();
    @C1458a(a = 2131756114)
    /* renamed from: c */
    private SquareCameraPreview f6621c;
    @C1458a(a = 2131756118)
    /* renamed from: d */
    private ImageView f6622d;
    @C1458a(a = 2131756123)
    /* renamed from: e */
    private ImageView f6623e;
    @C1458a(a = 2131756115)
    /* renamed from: f */
    private SaveImageView f6624f;
    @C1458a(a = 2131756120)
    /* renamed from: g */
    private ImageView f6625g;
    @C1458a(a = 2131756121)
    /* renamed from: h */
    private ImageView f6626h;
    @C1458a(a = 2131756124)
    /* renamed from: i */
    private ImageView f6627i;
    /* renamed from: j */
    private Uri f6628j;
    /* renamed from: k */
    private ActivityDTO f6629k;
    @C1458a(a = 2131756119)
    /* renamed from: l */
    private RelativeLayout f6630l;
    /* renamed from: m */
    private WatermarkCameraActivity$a f6631m;
    /* renamed from: n */
    private RelativeLayout f6632n;
    /* renamed from: o */
    private SurfaceHolder f6633o;
    /* renamed from: p */
    private Camera f6634p;
    /* renamed from: q */
    private String f6635q;
    /* renamed from: r */
    private int f6636r;
    /* renamed from: s */
    private int f6637s;
    /* renamed from: t */
    private int f6638t;
    /* renamed from: u */
    private int f6639u;
    /* renamed from: v */
    private int f6640v;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        getWindowManager().getDefaultDisplay().getMetrics(this.f6620b);
        this.f6631m = new WatermarkCameraActivity$a(this);
        this.f6631m.enable();
        if (bundle == null) {
            this.f6638t = m7870i();
            this.f6635q = "auto";
        } else {
            this.f6638t = bundle.getInt("camera_id");
            this.f6635q = bundle.getString("flash_mode");
            this.f6637s = bundle.getInt("preview_height");
        }
        this.f6621c.getHolder().addCallback(this);
        this.f6632n = (RelativeLayout) findViewById(C1373R.id.activity_watermark_surfaceView_root);
        View findViewById = findViewById(C1373R.id.activity_watermark_cover_top_view);
        View findViewById2 = findViewById(C1373R.id.activity_watermark_cover_bottom_view);
        if (this.f6636r == 0) {
            this.f6621c.getViewTreeObserver().addOnGlobalLayoutListener(new WatermarkCameraActivity$1(this, findViewById, findViewById2, this));
        } else {
            findViewById.getLayoutParams().height = this.f6636r;
            findViewById2.getLayoutParams().height = this.f6636r;
        }
        this.f6622d.setOnClickListener(this);
        this.f6623e.setOnClickListener(this);
        this.f6627i.setOnClickListener(this);
        this.f6626h.setOnClickListener(this);
        this.f6625g.setOnClickListener(this);
        m7858b();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("activity_dto")) {
            this.f6629k = (ActivityDTO) intent.getSerializableExtra("activity_dto");
        }
    }

    protected void onResume() {
        this.f6631m.enable();
        try {
            m7852a(this.f6638t);
            m7860c();
        } catch (Exception e) {
        }
        if (this.f6634p == null) {
            Toasts.show(this, C1373R.string.toast_show_open_camera);
            finish();
        }
        this.f6623e.setClickable(true);
        this.f6621c.setClickable(true);
        this.f6621c.setAutoFocus(true);
        super.onResume();
    }

    protected void onPause() {
        this.f6631m.disable();
        if (this.f6634p == null) {
            super.onPause();
            return;
        }
        m7862d();
        this.f6634p.release();
        this.f6634p = null;
        super.onPause();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_watermark_back:
                finish();
                return;
            case C1373R.id.activity_watermark_switch_camera:
                if (this.f6638t == 1) {
                    this.f6638t = m7870i();
                } else {
                    this.f6638t = m7869h();
                }
                m7868g();
                return;
            case C1373R.id.activity_watermark_switch_flash:
                if (this.f6635q.equalsIgnoreCase("auto")) {
                    this.f6635q = "on";
                    this.f6626h.setImageResource(C1373R.drawable.ic_activity_watermark_switch_flash_on);
                } else if (this.f6635q.equalsIgnoreCase("on")) {
                    this.f6635q = "off";
                    this.f6626h.setImageResource(C1373R.drawable.ic_activity_watermark_switch_flash_off);
                } else if (this.f6635q.equalsIgnoreCase("off")) {
                    this.f6635q = "auto";
                    this.f6626h.setImageResource(C1373R.drawable.ic_activity_watermark_switch_flash_auto);
                }
                m7866f();
                return;
            case C1373R.id.activity_watermark_capture:
                this.f6621c.setClickable(false);
                this.f6621c.setAutoFocus(false);
                this.f6623e.setClickable(false);
                m7871j();
                return;
            case C1373R.id.activity_watermark_gallery:
                Intent intent = new Intent("android.intent.action.PICK");
                intent.setType("image/*");
                startActivityForResult(intent, 2);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private String m7851a() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), String.format("Beast_Watermark_Camera_%s.png", new Object[]{f6619a.format(new Date())})).getAbsolutePath();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (!Build.BRAND.equals("Meizu")) {
            m7853a(i, i2, intent);
        } else if (i2 != 0) {
            m7853a(i, i2, intent);
        } else {
            finish();
        }
    }

    /* renamed from: a */
    private void m7853a(int i, int i2, Intent intent) {
        if (i2 != 0) {
            switch (i) {
                case 2:
                    if (-1 == i2) {
                        m7872a(intent.getData());
                        return;
                    }
                    return;
                case 4:
                    if (-1 == i2 && this.f6628j != null) {
                        Intent intent2 = new Intent(this, WatermarkGalleryActivity.class);
                        intent2.putExtra("path", this.f6628j);
                        intent2.putExtra("dto", this.f6629k);
                        startActivityForResult(intent2, 6);
                        return;
                    }
                    return;
                case 6:
                    if (-1 == i2) {
                        setResult(-1, intent);
                        finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m7872a(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 750);
        intent.putExtra("outputY", 750);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        this.f6628j = Uri.parse("file:///" + m7851a());
        intent.putExtra("output", this.f6628j);
        intent.putExtra("outputFormat", CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, 4);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f6633o = surfaceHolder;
        m7852a(this.f6638t);
        m7860c();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    /* renamed from: b */
    private void m7858b() {
        getAsyncTaskQueue().a(new WatermarkCameraActivity$2(this), new Void[0]);
    }

    /* renamed from: a */
    private void m7852a(int i) {
        try {
            this.f6634p = Camera.open(i);
            this.f6621c.setCamera(this.f6634p);
        } catch (Exception e) {
        }
    }

    /* renamed from: c */
    private void m7860c() {
        m7864e();
        m7866f();
        try {
            this.f6634p.setPreviewDisplay(this.f6633o);
            this.f6634p.startPreview();
        } catch (IOException e) {
            finish();
        }
    }

    /* renamed from: d */
    private void m7862d() {
        if (this.f6633o != null) {
            this.f6633o.removeCallback(this);
        }
        this.f6634p.setPreviewCallback(null);
        this.f6634p.stopPreview();
        this.f6621c.setCamera(null);
    }

    /* renamed from: e */
    private void m7864e() {
        int i = 0;
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(this.f6638t, cameraInfo);
        switch (getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i = 90;
                break;
            case 2:
                i = Opcodes.GETFIELD;
                break;
            case 3:
                i = 270;
                break;
        }
        int i2;
        if (cameraInfo.facing == 1) {
            i2 = (360 - ((cameraInfo.orientation + i) % 360)) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i) + 360) % 360;
        }
        int i3 = ((cameraInfo.orientation - i) + 360) % 360;
        this.f6640v = i;
        if (this.f6634p == null) {
            m7852a(this.f6638t);
        }
        if (this.f6634p == null) {
            finish();
        }
        this.f6634p.setDisplayOrientation(i3);
    }

    /* renamed from: f */
    private void m7866f() {
        Parameters parameters = this.f6634p.getParameters();
        Size a = m7848a(parameters);
        Size b = m7857b(parameters);
        parameters.setPreviewSize(a.width, a.height);
        parameters.setPictureSize(b.width, b.height);
        if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
            parameters.setFocusMode("continuous-picture");
        }
        List supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes == null || !supportedFlashModes.contains(this.f6635q)) {
            this.f6626h.setImageResource(C1373R.drawable.ic_activity_watermark_switch_flash_disable);
            this.f6626h.setEnabled(false);
        } else {
            parameters.setFlashMode(this.f6635q);
            this.f6626h.setVisibility(0);
            this.f6626h.setEnabled(true);
            if (this.f6635q.equalsIgnoreCase("auto")) {
                this.f6626h.setImageResource(C1373R.drawable.ic_activity_watermark_switch_flash_auto);
            } else if (this.f6635q.equalsIgnoreCase("on")) {
                this.f6626h.setImageResource(C1373R.drawable.ic_activity_watermark_switch_flash_on);
            } else if (this.f6635q.equalsIgnoreCase("off")) {
                this.f6626h.setImageResource(C1373R.drawable.ic_activity_watermark_switch_flash_off);
            }
        }
        this.f6634p.setDisplayOrientation(90);
        parameters.set("rotation", 90);
        if (m7869h() == this.f6638t) {
            parameters.set("rotation", 270);
        }
        this.f6634p.setParameters(parameters);
    }

    /* renamed from: a */
    private Size m7848a(Parameters parameters) {
        return m7849a(parameters.getSupportedPreviewSizes(), 640);
    }

    /* renamed from: b */
    private Size m7857b(Parameters parameters) {
        return m7849a(parameters.getSupportedPictureSizes(), 1280);
    }

    /* renamed from: a */
    private Size m7849a(List<Size> list, int i) {
        Size size = null;
        int size2 = list.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj;
            Size size3 = (Size) list.get(i2);
            if (size3.width / 4 == size3.height / 3) {
                obj = 1;
            } else {
                obj = null;
            }
            Object obj2;
            if (size == null || size3.width > size.width) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj == null || r2 == null) {
                size3 = size;
            }
            i2++;
            size = size3;
        }
        if (size == null) {
            return (Size) list.get(list.size() - 1);
        }
        return size;
    }

    /* renamed from: g */
    private void m7868g() {
        m7862d();
        this.f6634p.release();
        m7852a(this.f6638t);
        m7860c();
    }

    /* renamed from: h */
    private int m7869h() {
        if (getPackageManager().hasSystemFeature("android.hardware.camera.front")) {
            return 1;
        }
        return m7870i();
    }

    /* renamed from: i */
    private int m7870i() {
        return 0;
    }

    /* renamed from: j */
    private void m7871j() {
        this.f6631m.a();
        this.f6634p.takePicture(null, null, null, this);
    }

    public void finish() {
        super.finish();
    }

    public void onPictureTaken(byte[] bArr, Camera camera) {
        Parcelable a;
        try {
            m7854a(((this.f6639u + this.f6631m.b()) + this.f6640v) % 360, bArr, this.f6624f);
            a = C2561i.a(this, ((BitmapDrawable) this.f6624f.getDrawable()).getBitmap());
        } catch (Throwable th) {
            a = null;
        }
        Intent intent = new Intent(this, WatermarkGalleryActivity.class);
        intent.putExtra("path", a);
        intent.putExtra("dto", this.f6629k);
        startActivityForResult(intent, 6);
    }

    /* renamed from: a */
    private void m7854a(int i, byte[] bArr, ImageView imageView) {
        Bitmap a = C2561i.a(this, bArr);
        if (i != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate((float) i);
            Bitmap createBitmap = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, false);
            a.recycle();
            a = createBitmap;
        }
        this.f6624f.setVisibility(0);
        this.f6624f.setImageBitmap(a);
    }
}

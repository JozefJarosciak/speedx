package cn.sharesdk.sina.weibo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.UIHandler;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/* compiled from: SinaActivity */
/* renamed from: cn.sharesdk.sina.weibo.a */
public class C0659a extends FakeActivity implements Callback {
    /* renamed from: a */
    private long f1549a = 2097152;
    /* renamed from: b */
    private boolean f1550b;
    /* renamed from: c */
    private String f1551c;
    /* renamed from: d */
    private ShareParams f1552d;
    /* renamed from: e */
    private AuthorizeListener f1553e;

    /* compiled from: SinaActivity */
    /* renamed from: cn.sharesdk.sina.weibo.a$1 */
    class C06581 implements Callback {
        /* renamed from: a */
        final /* synthetic */ C0659a f1548a;

        C06581(C0659a c0659a) {
            this.f1548a = c0659a;
        }

        public boolean handleMessage(Message message) {
            this.f1548a.m2510a();
            return true;
        }
    }

    public void onCreate() {
        try {
            Intent intent = new Intent();
            intent.setAction("com.sina.weibo.action.browser.share");
            startActivity(intent);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
        if (m2518c()) {
            UIHandler.sendEmptyMessageDelayed(1, 700, new C06581(this));
        }
    }

    /* renamed from: a */
    public void m2523a(String str) {
        this.f1551c = str;
    }

    /* renamed from: a */
    public void m2521a(ShareParams shareParams) {
        this.f1552d = shareParams;
    }

    /* renamed from: a */
    public void m2522a(AuthorizeListener authorizeListener) {
        this.f1553e = authorizeListener;
    }

    /* renamed from: a */
    private void m2510a() {
        Bundle bundle = new Bundle();
        bundle.putInt("_weibo_command_type", 1);
        bundle.putString("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        if (!TextUtils.isEmpty(this.f1552d.getText())) {
            bundle.putParcelable("_weibo_message_text", m2519d());
            bundle.putString("_weibo_message_text_extra", "");
        }
        if (!(this.f1552d.getImageData() == null && TextUtils.isEmpty(this.f1552d.getImagePath()))) {
            this.f1549a = 2097152;
            Parcelable e = m2520e();
            if (e.checkArgs()) {
                bundle.putParcelable("_weibo_message_image", e);
                bundle.putString("_weibo_message_image_extra", "");
            }
        }
        try {
            m2513a(this.activity, C0667e.m2569a(this.activity).m2576a(), this.f1551c, bundle);
        } catch (Throwable th) {
            if (this.f1553e != null) {
                this.f1553e.onError(new Throwable(th));
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        C0621d.m2279a().d("sina activity requestCode = %s, resultCode = %s", new Object[]{Integer.valueOf(i), Integer.valueOf(i)});
        m2515b();
    }

    public void onNewIntent(Intent intent) {
        this.f1550b = true;
        Bundle extras = intent.getExtras();
        C0621d.m2279a().i("onNewIntent ==>>", new Object[]{extras.toString()});
        String stringExtra = intent.getStringExtra("_weibo_appPackage");
        CharSequence stringExtra2 = intent.getStringExtra("_weibo_transaction");
        if (TextUtils.isEmpty(stringExtra)) {
            C0621d.m2279a().e("handleWeiboResponse faild appPackage is null", new Object[0]);
            return;
        }
        C0621d.m2279a().d("handleWeiboResponse getCallingPackage : " + this.activity.getCallingPackage(), new Object[0]);
        if (TextUtils.isEmpty(stringExtra2)) {
            C0621d.m2279a().e("handleWeiboResponse faild intent _weibo_transaction is null", new Object[0]);
        } else if (C0667e.m2570a(this.activity, stringExtra) || stringExtra.equals(this.activity.getPackageName())) {
            m2511a(extras.getInt("_weibo_resp_errcode"), extras.getString("_weibo_resp_errstr"));
        } else {
            C0621d.m2279a().e("handleWeiboResponse faild appPackage validateSign faild", new Object[0]);
        }
    }

    /* renamed from: a */
    private void m2511a(int i, String str) {
        switch (i) {
            case 0:
                if (this.f1553e != null) {
                    this.f1553e.onComplete(null);
                    break;
                }
                break;
            case 1:
                if (this.f1553e != null) {
                    this.f1553e.onCancel();
                    break;
                }
                break;
            case 2:
                if (this.f1553e != null) {
                    this.f1553e.onError(new Throwable(str));
                    break;
                }
                break;
        }
        finish();
    }

    /* renamed from: b */
    private void m2515b() {
        UIHandler.sendEmptyMessageDelayed(1, 200, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            if (!(this.f1550b || this.f1553e == null)) {
                this.f1553e.onCancel();
            }
            finish();
        }
        return false;
    }

    /* renamed from: a */
    private boolean m2513a(Activity activity, String str, String str2, Bundle bundle) {
        Object obj = "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY";
        if (activity == null || TextUtils.isEmpty(obj) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C0621d.m2279a().e("launchWeiboActivity fail, invalid arguments", new Object[0]);
            return false;
        }
        String packageName = activity.getPackageName();
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction(obj);
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", str2);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", m2509a((Context) activity, packageName));
        intent.putExtra("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            C0621d.m2279a().d("launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
            activity.startActivityForResult(intent, 765);
            return true;
        } catch (ActivityNotFoundException e) {
            C0621d.m2279a().e(e.getMessage(), new Object[0]);
            return false;
        }
    }

    /* renamed from: c */
    private boolean m2518c() {
        Intent intent = new Intent();
        intent.setAction("com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER");
        String packageName = this.activity.getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", this.f1551c);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", m2509a(this.activity, packageName));
        C0621d.m2279a().d("intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
        this.activity.sendBroadcast(intent, "com.sina.weibo.permission.WEIBO_SDK_PERMISSION");
        return true;
    }

    /* renamed from: a */
    private String m2509a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature toByteArray : packageInfo.signatures) {
                byte[] toByteArray2 = toByteArray.toByteArray();
                if (toByteArray2 != null) {
                    return Data.MD5(toByteArray2);
                }
            }
            return null;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /* renamed from: d */
    private TextObject m2519d() {
        TextObject textObject = new TextObject();
        textObject.text = this.f1552d.getText();
        return textObject;
    }

    /* renamed from: e */
    private ImageObject m2520e() {
        ImageObject imageObject = new ImageObject();
        try {
            if (this.f1552d.getImageData() != null) {
                imageObject.imageData = m2514a(this.activity, this.f1552d.getImageData());
            } else if (!TextUtils.isEmpty(this.f1552d.getImagePath())) {
                DeviceHelper instance = DeviceHelper.getInstance(this.activity);
                if (instance.getSdcardState() && this.f1552d.getImagePath().startsWith(instance.getSdcardPath())) {
                    File file = new File(this.f1552d.getImagePath());
                    if (file.exists() && file.length() != 0 && file.length() < SizeBasedTriggeringPolicy.DEFAULT_MAX_FILE_SIZE) {
                        imageObject.imagePath = this.f1552d.getImagePath();
                    }
                }
                imageObject.imageData = m2517b(this.activity, this.f1552d.getImagePath());
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
        return imageObject;
    }

    /* renamed from: a */
    private byte[] m2514a(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (!bitmap.isRecycled()) {
            return m2516b(context, bitmap);
        } else {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
    }

    /* renamed from: b */
    private byte[] m2517b(Context context, String str) throws Throwable {
        if (new File(str).exists()) {
            return m2516b(context, BitmapHelper.getBitmap(str));
        }
        throw new FileNotFoundException();
    }

    /* renamed from: b */
    private byte[] m2516b(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        } else {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            int length = toByteArray.length;
            while (((long) length) > this.f1549a) {
                bitmap = m2508a(bitmap, ((double) length) / ((double) this.f1549a));
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                toByteArray = byteArrayOutputStream.toByteArray();
                length = toByteArray.length;
            }
            return toByteArray;
        }
    }

    /* renamed from: a */
    private Bitmap m2508a(Bitmap bitmap, double d) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double sqrt = Math.sqrt(d);
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) width) / sqrt), (int) (((double) height) / sqrt), true);
    }
}

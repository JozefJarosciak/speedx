package cn.sharesdk.wechat.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.core.joran.action.Action;
import cn.sharesdk.framework.utils.C0621d;
import com.j256.ormlite.field.FieldType;
import com.mob.tools.utils.C4275R;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.tencent.mm.sdk.constants.ConstantsAPI.WXApp;
import java.security.MessageDigest;

/* compiled from: WechatCore */
/* renamed from: cn.sharesdk.wechat.utils.h */
public class C0702h {
    /* renamed from: a */
    private Context f1685a;
    /* renamed from: b */
    private String f1686b;

    /* renamed from: a */
    public boolean m2750a(Context context, String str) {
        this.f1685a = context;
        this.f1686b = str;
        if (m2747d()) {
            String str2 = ConstantsAPI.ACTION_HANDLE_APP_REGISTER;
            String str3 = "weixin://registerapp?appid=" + str;
            String str4 = WXApp.WXAPP_BROADCAST_PERMISSION;
            String packageName = context.getPackageName();
            Intent intent = new Intent(str2);
            intent.putExtra(ConstantsAPI.SDK_VERSION, Build.MUSIC_DATA_URL_SUPPORTED_SDK_INT);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, str3);
            intent.putExtra(ConstantsAPI.CHECK_SUM, m2746a(str3, packageName, Build.MUSIC_DATA_URL_SUPPORTED_SDK_INT));
            context.sendBroadcast(intent, str4);
            C0621d.m2279a().d("sending broadcast, intent=" + str2 + ", perm=" + str4, new Object[0]);
            return true;
        }
        C0621d.m2279a().d("register app failed for wechat app signature check failed", new Object[0]);
        return false;
    }

    /* renamed from: a */
    public void m2748a(C0692j c0692j) throws Throwable {
        if (!m2752b()) {
            throw new WechatClientNotExistException();
        } else if (c0692j.mo2304b()) {
            String packageName = this.f1685a.getPackageName();
            String str = "weixin://sendreq?appid=" + this.f1686b;
            Intent intent = new Intent();
            intent.setClassName(WXApp.WXAPP_PACKAGE_NAME, WXApp.WXAPP_MSG_ENTRY_CLASSNAME);
            Bundle bundle = new Bundle();
            c0692j.mo2303a(bundle);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra(ConstantsAPI.SDK_VERSION, Build.EMOJI_SUPPORTED_SDK_INT);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, str);
            intent.putExtra(ConstantsAPI.CHECK_SUM, m2746a(str, packageName, Build.EMOJI_SUPPORTED_SDK_INT));
            intent.addFlags(268435456);
            intent.addFlags(134217728);
            this.f1685a.startActivity(intent);
            C0621d.m2279a().d("starting activity, packageName=com.tencent.mm, className=com.tencent.mm.plugin.base.stub.WXEntryActivity", new Object[0]);
        } else {
            throw new Throwable("sendReq checkArgs fail");
        }
    }

    /* renamed from: a */
    public boolean m2749a() {
        String str;
        try {
            str = this.f1685a.getPackageManager().getPackageInfo(WXApp.WXAPP_PACKAGE_NAME, 0).versionName;
            C0621d.m2279a().i("wechat versionName ==>> " + str, new Object[0]);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            str = "0";
        }
        String[] split = str.split("_")[0].split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                iArr[i] = C4275R.parseInt(split[i]);
            } catch (Throwable th2) {
                C0621d.m2279a().d(th2);
                iArr[i] = 0;
            }
        }
        if (iArr.length >= 4 && iArr[0] == 6 && iArr[1] == 0 && iArr[2] == 2 && iArr[3] <= 56) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public boolean m2752b() {
        if (m2747d()) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    private boolean m2747d() {
        C0621d.m2279a().d("checking signature of wechat client...", new Object[0]);
        try {
            for (Signature toCharsString : this.f1685a.getPackageManager().getPackageInfo(WXApp.WXAPP_PACKAGE_NAME, 64).signatures) {
                if ("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499".equals(toCharsString.toCharsString())) {
                    C0621d.m2279a().d("pass!", new Object[0]);
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return false;
        }
    }

    /* renamed from: a */
    private byte[] m2746a(String str, String str2, int i) {
        String str3;
        int i2 = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        byte[] bytes = stringBuffer.toString().substring(1, 9).getBytes();
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            char[] cArr2 = new char[(digest.length * 2)];
            int i3 = 0;
            while (i2 < digest.length) {
                byte b = digest[i2];
                int i4 = i3 + 1;
                cArr2[i3] = cArr[(b >>> 4) & 15];
                i3 = i4 + 1;
                cArr2[i4] = cArr[b & 15];
                i2++;
            }
            str3 = new String(cArr2);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            str3 = null;
        }
        if (str3 != null) {
            return str3.getBytes();
        }
        return null;
    }

    /* renamed from: a */
    private String m2744a(String str) {
        try {
            Cursor query = this.f1685a.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref"), new String[]{FieldType.FOREIGN_ID_FIELD_SUFFIX, Action.KEY_ATTRIBUTE, "type", "value"}, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            String string;
            if (query.moveToFirst()) {
                string = query.getString(query.getColumnIndex("value"));
            } else {
                string = null;
            }
            query.close();
            return string;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return null;
        }
    }

    /* renamed from: c */
    public boolean m2753c() {
        int parseInt;
        try {
            parseInt = C4275R.parseInt(m2744a("_build_info_sdk_int_"));
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            parseInt = -1;
        }
        return parseInt >= Build.TIMELINE_SUPPORTED_SDK_INT;
    }

    /* renamed from: a */
    public boolean m2751a(WechatHandlerActivity wechatHandlerActivity, C0703i c0703i) {
        Intent intent = wechatHandlerActivity.getIntent();
        if (intent == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra(Token.WX_TOKEN_KEY);
        if (stringExtra == null || !stringExtra.equals(Token.WX_TOKEN_VALUE_MSG)) {
            C0621d.m2279a().d("invalid argument, \"wx_token_key\" is empty or does not equals \"com.tencent.mm.openapi.token\"", new Object[0]);
            return false;
        }
        Object stringExtra2 = intent.getStringExtra(ConstantsAPI.APP_PACKAGE);
        if (TextUtils.isEmpty(stringExtra2)) {
            C0621d.m2279a().d("invalid argument, \"_mmessage_appPackage\" is empty", new Object[0]);
            return false;
        }
        if (m2745a(intent.getByteArrayExtra(ConstantsAPI.CHECK_SUM), m2746a(intent.getStringExtra(ConstantsAPI.CONTENT), stringExtra2, intent.getIntExtra(ConstantsAPI.SDK_VERSION, 0)))) {
            Bundle extras = intent.getExtras();
            switch (extras.getInt("_wxapi_command_type", 0)) {
                case 1:
                    c0703i.m2757a(new C0694b(extras));
                    break;
                case 2:
                    c0703i.m2757a(new C0697e(extras));
                    break;
                case 3:
                    wechatHandlerActivity.onGetMessageFromWXReq(new C0695c(extras).f1671a);
                    break;
                case 4:
                    wechatHandlerActivity.onShowMessageFromWXReq(new C0698f(extras).f1674a);
                    break;
                default:
                    return false;
            }
            return true;
        }
        C0621d.m2279a().d("checksum fail", new Object[0]);
        return false;
    }

    /* renamed from: a */
    private boolean m2745a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            C0621d.m2279a().d("checkSumConsistent fail, invalid arguments, \"_mmessage_checksum\" is empty", new Object[0]);
            return false;
        } else if (bArr2 == null || bArr2.length == 0) {
            C0621d.m2279a().d("checkSumConsistent fail, invalid arguments, checksum is empty", new Object[0]);
            return false;
        } else if (bArr.length != bArr2.length) {
            C0621d.m2279a().d("checkSumConsistent fail, length is different", new Object[0]);
            return false;
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    C0621d.m2279a().d("checkSumConsistent fail, not match", new Object[0]);
                    return false;
                }
            }
            return true;
        }
    }
}

package com.pingplusplus.android.crypto;

import android.text.TextUtils;
import ch.qos.logback.core.joran.action.Action;
import com.alipay.sdk.packet.C0861d;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.InputStream;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

public class CryptoUtils {
    public static String aesDecrypt(String str, byte[] bArr) {
        return TextUtils.isEmpty(str) ? null : C4284a.m16978a(bArr, C4285b.m16983a(str));
    }

    public static String aesEncrypt(String str, byte[] bArr) {
        return C4285b.m16982a(C4284a.m16979a(bArr, str));
    }

    public static String decryptData(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(C0861d.f2139k);
            String rsaDecrypt = rsaDecrypt(str2, jSONObject.optString(Action.KEY_ATTRIBUTE));
            return aesDecrypt(optString, new Crypt().encryptData(rsaDecrypt.substring(0, 32), rsaDecrypt.substring(32)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String decryptData(String str, String str2, String str3) {
        try {
            return aesDecrypt(str3, new Crypt().encryptData(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptData(String str, String str2) {
        String randomString = getRandomString(16);
        String randomString2 = getRandomString(16);
        try {
            Crypt crypt = new Crypt();
            String aesEncrypt = aesEncrypt(str, crypt.encryptData(randomString, randomString2));
            randomString = crypt.encryptKey(str2, randomString + randomString2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C0861d.f2139k, aesEncrypt);
            jSONObject.put(Action.KEY_ATTRIBUTE, randomString);
            jSONObject.put(MapboxEvent.ATTRIBUTE_VERSION, "PE_v2");
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptData(String str, String str2, String str3) {
        try {
            return aesEncrypt(str3, new Crypt().encryptData(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getAesKey() {
        return new Crypt().encryptData(getRandomString(32), getRandomString(32));
    }

    public static String getRandomString(int i) {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(str.charAt(random.nextInt(str.length())));
        }
        return stringBuffer.toString();
    }

    public static byte[] hashPassword(String str, String str2, String str3, int i, int i2) {
        return SecretKeyFactory.getInstance(str).generateSecret(new PBEKeySpec(str2.toCharArray(), str3.getBytes(), i, i2)).getEncoded();
    }

    public static String rsaDecrypt(InputStream inputStream, String str) {
        if (str == null || inputStream == null) {
            throw new IllegalArgumentException("public or data is null");
        }
        return new String(C4286c.m16989b(C4285b.m16983a(str), C4286c.m16985a(inputStream)));
    }

    public static String rsaDecrypt(String str, String str2) {
        if (str2 == null || str == null) {
            throw new IllegalArgumentException("public or data is null");
        }
        return new String(C4286c.m16989b(C4285b.m16983a(str2), C4286c.m16986a(str)));
    }

    public static String rsaEncrypt(InputStream inputStream, String str) {
        if (str == null || inputStream == null) {
            throw new IllegalArgumentException("public or data is null");
        }
        return C4285b.m16982a(C4286c.m16987a(str.getBytes("utf-8"), C4286c.m16985a(inputStream)));
    }

    public static String rsaEncrypt(String str, String str2) {
        if (str2 == null || str == null) {
            throw new IllegalArgumentException("public or data is null");
        }
        return C4285b.m16982a(C4286c.m16987a(str2.getBytes("utf-8"), C4286c.m16986a(str)));
    }
}

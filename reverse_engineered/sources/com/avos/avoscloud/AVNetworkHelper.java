package com.avos.avoscloud;

import android.os.Looper;
import com.alipay.sdk.util.C0880h;
import com.avos.avoscloud.LogUtil.avlog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import org.apache.http.Header;

public class AVNetworkHelper {
    static final String AVOS_SERVER_HOST_ZONE = "avoscloud_server_host_zone";
    public static final String EXPIRE_TIME = ".expireTime";
    static final long TEN_MIN_IN_NANO = 600000000000L;
    static final long TWENTY_MIN_IN_MILLS = 1200000;

    public interface NetworkResourceCallback {
        void done(String str, AVException aVException);
    }

    public static abstract class DNSUpdateCallback extends AVCallback<Void> {
        public abstract void done(AVException aVException);

        protected final void internalDone0(Void voidR, AVException aVException) {
            done(aVException);
        }
    }

    public static void amendDNS(final String str, final DNSUpdateCallback dNSUpdateCallback) {
        getDNSResource(str, new NetworkResourceCallback() {
            public void done(String str, AVException aVException) {
                if (aVException == null) {
                    InetAddress[] access$000 = AVNetworkHelper.getIPAddress(str, str);
                    if (access$000 != null) {
                        try {
                            AVNetworkHelper.bindDNS(str, access$000);
                            dNSUpdateCallback.done(null);
                            return;
                        } catch (Throwable e) {
                            dNSUpdateCallback.done(new AVException(e));
                            return;
                        }
                    }
                    dNSUpdateCallback.done(new AVException(-1, "address parse failure"));
                    return;
                }
                dNSUpdateCallback.done(aVException);
            }
        });
    }

    private static void bindDNS(String str, InetAddress[] inetAddressArr) throws Exception {
        InetAddress.class.getDeclaredConstructor(new Class[]{Integer.TYPE, byte[].class, String.class});
        Field declaredField = InetAddress.class.getDeclaredField("addressCache");
        declaredField.setAccessible(true);
        Field declaredField2 = Class.forName("java.net.AddressCache").getDeclaredField("cache");
        declaredField2.setAccessible(true);
        Object obj = declaredField2.get(declaredField.get(null));
        Class cls = Class.forName("libcore.util.BasicLruCache");
        Class cls2 = Class.forName("java.net.AddressCache$AddressCacheEntry");
        Field declaredField3 = cls2.getDeclaredField("expiryNanos");
        declaredField3.setAccessible(true);
        Constructor declaredConstructor = cls2.getDeclaredConstructor(new Class[]{Object.class});
        declaredConstructor.setAccessible(true);
        Method declaredMethod = cls.getDeclaredMethod("put", new Class[]{Object.class, Object.class});
        declaredMethod.setAccessible(true);
        declaredField3.set(declaredConstructor.newInstance(new Object[]{inetAddressArr}), Long.valueOf(System.nanoTime() + TEN_MIN_IN_NANO));
        try {
            Constructor declaredConstructor2 = Class.forName("java.net.AddressCache$AddressCacheKey").getDeclaredConstructor(new Class[]{String.class, Integer.TYPE});
            declaredConstructor2.setAccessible(true);
            Object newInstance = declaredConstructor2.newInstance(new Object[]{str, Integer.valueOf(0)});
            declaredMethod.invoke(obj, new Object[]{newInstance, r2});
        } catch (Exception e) {
            if (AVOSCloud.showInternalDebugLog()) {
                avlog.m3506d(e.getMessage());
            }
            declaredMethod.invoke(obj, new Object[]{str, r2});
        }
    }

    private static void getDNSResource(String str, final NetworkResourceCallback networkResourceCallback) {
        String persistentSettingString = AVPersistenceUtils.sharedInstance().getPersistentSettingString(AVOS_SERVER_HOST_ZONE, str, null);
        String persistentSettingString2 = AVPersistenceUtils.sharedInstance().getPersistentSettingString(AVOS_SERVER_HOST_ZONE, str + EXPIRE_TIME, "0");
        if (AVUtils.isBlankString(persistentSettingString) || System.currentTimeMillis() >= Long.parseLong(persistentSettingString2)) {
            RequestParams requestParams = new RequestParams();
            requestParams.put("dn", str);
            AVUtils.getDirectlyClientForUse().get("http://119.29.29.29/d", requestParams, new AsyncHttpResponseHandler(Looper.getMainLooper()) {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        networkResourceCallback.done(AVUtils.stringFromBytes(bArr), null);
                    } else {
                        networkResourceCallback.done(null, new AVException(i, AVUtils.stringFromBytes(bArr)));
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    networkResourceCallback.done(null, new AVException(th));
                }
            });
            return;
        }
        networkResourceCallback.done(persistentSettingString, null);
    }

    private static InetAddress[] getIPAddress(String str, String str2) {
        try {
            String[] split = str2.split(C0880h.f2220b);
            InetAddress[] inetAddressArr = new InetAddress[split.length];
            Constructor declaredConstructor = InetAddress.class.getDeclaredConstructor(new Class[]{Integer.TYPE, byte[].class, String.class});
            declaredConstructor.setAccessible(true);
            for (int i = 0; i < split.length; i++) {
                if (split[i].split("\\.").length == 4) {
                    byte[] bArr = new byte[]{(byte) Integer.parseInt(split[i].split("\\.")[0]), (byte) Integer.parseInt(split[i].split("\\.")[1]), (byte) Integer.parseInt(split[i].split("\\.")[2]), (byte) Integer.parseInt(split[i].split("\\.")[3])};
                    inetAddressArr[i] = (InetAddress) declaredConstructor.newInstance(new Object[]{Integer.valueOf(2), bArr, str});
                } else if (AVOSCloud.showInternalDebugLog()) {
                    avlog.m3506d("wrong IP Address");
                }
            }
            AVPersistenceUtils.sharedInstance().savePersistentSettingString(AVOS_SERVER_HOST_ZONE, str, str2);
            AVPersistenceUtils.sharedInstance().savePersistentSettingString(AVOS_SERVER_HOST_ZONE, str + EXPIRE_TIME, String.valueOf(System.currentTimeMillis() + TWENTY_MIN_IN_MILLS));
            return inetAddressArr;
        } catch (Exception e) {
            return null;
        }
    }
}

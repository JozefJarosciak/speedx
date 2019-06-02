package cn.sharesdk.framework;

import android.content.Context;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

public abstract class Service {
    /* renamed from: a */
    private Context f1221a;
    /* renamed from: b */
    private String f1222b;

    public static abstract class ServiceEvent {
        private final int PLATFORM = 1;
        protected Service service;

        public ServiceEvent(Service service) {
            this.service = service;
        }

        protected HashMap<String, Object> toMap() {
            HashMap<String, Object> hashMap = new HashMap();
            DeviceHelper instance = DeviceHelper.getInstance(this.service.f1221a);
            hashMap.put("deviceid", instance.getDeviceKey());
            hashMap.put("appkey", this.service.getAppKey());
            hashMap.put("apppkg", instance.getPackageName());
            hashMap.put("appver", Integer.valueOf(instance.getAppVersion()));
            hashMap.put("sdkver", Integer.valueOf(this.service.getServiceVersionInt()));
            hashMap.put("plat", Integer.valueOf(1));
            hashMap.put("networktype", instance.getDetailNetworkTypeForStatic());
            hashMap.put("deviceData", instance.getDeviceDataNotAES());
            return hashMap;
        }

        public final String toString() {
            return new Hashon().fromHashMap(toMap());
        }

        protected HashMap<String, Object> filterShareContent(int i, ShareParams shareParams, HashMap<String, Object> hashMap) {
            C0596a filterShareContent = ShareSDK.getPlatform(ShareSDK.platformIdToName(i)).filterShareContent(shareParams, hashMap);
            HashMap<String, Object> hashMap2 = new HashMap();
            hashMap2.put("shareID", filterShareContent.f1300a);
            hashMap2.put("shareContent", new Hashon().fromJson(filterShareContent.toString()));
            C0621d.m2279a().i("filterShareContent ==>>%s", new Object[]{hashMap2});
            return hashMap2;
        }
    }

    protected abstract int getServiceVersionInt();

    public abstract String getServiceVersionName();

    /* renamed from: a */
    void m1975a(Context context) {
        this.f1221a = context;
    }

    public Context getContext() {
        return this.f1221a;
    }

    /* renamed from: a */
    void m1976a(String str) {
        this.f1222b = str;
    }

    public String getAppKey() {
        return this.f1222b;
    }

    public String getDeviceKey() {
        return DeviceHelper.getInstance(this.f1221a).getDeviceKey();
    }

    public void onBind() {
    }

    public void onUnbind() {
    }
}

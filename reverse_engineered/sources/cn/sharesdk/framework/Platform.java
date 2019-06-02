package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import java.util.HashMap;

public abstract class Platform {
    public static final int ACTION_AUTHORIZING = 1;
    protected static final int ACTION_CUSTOMER = 655360;
    public static final int ACTION_FOLLOWING_USER = 6;
    protected static final int ACTION_GETTING_BILATERAL_LIST = 10;
    protected static final int ACTION_GETTING_FOLLOWER_LIST = 11;
    public static final int ACTION_GETTING_FRIEND_LIST = 2;
    public static final int ACTION_SENDING_DIRECT_MESSAGE = 5;
    public static final int ACTION_SHARE = 9;
    public static final int ACTION_TIMELINE = 7;
    public static final int ACTION_USER_INFOR = 8;
    public static final int CUSTOMER_ACTION_MASK = 65535;
    public static final int SHARE_APPS = 7;
    public static final int SHARE_EMOJI = 9;
    public static final int SHARE_FILE = 8;
    public static final int SHARE_IMAGE = 2;
    public static final int SHARE_MUSIC = 5;
    public static final int SHARE_TEXT = 1;
    public static final int SHARE_VIDEO = 6;
    public static final int SHARE_WEBPAGE = 4;
    /* renamed from: a */
    private C0604c f1175a;
    protected final Context context;
    protected final PlatformDb db = this.f1175a.m2199g();
    protected PlatformActionListener listener = this.f1175a.m2201i();

    public static class ShareParams extends InnerShareParams {
        @Deprecated
        public String imagePath;
        @Deprecated
        public String text;

        public ShareParams(HashMap<String, Object> hashMap) {
            super((HashMap) hashMap);
        }

        public ShareParams(String str) {
            super(str);
        }
    }

    protected abstract boolean checkAuthorize(int i, Object obj);

    protected abstract void doAuthorize(String[] strArr);

    protected abstract void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2);

    protected abstract void doShare(ShareParams shareParams);

    protected abstract HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap);

    protected abstract C0596a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap);

    protected abstract void follow(String str);

    protected abstract HashMap<String, Object> getBilaterals(int i, int i2, String str);

    protected abstract HashMap<String, Object> getFollowers(int i, int i2, String str);

    protected abstract HashMap<String, Object> getFollowings(int i, int i2, String str);

    protected abstract void getFriendList(int i, int i2, String str);

    public abstract String getName();

    protected abstract int getPlatformId();

    public abstract int getVersion();

    public abstract boolean hasShareCallback();

    protected abstract void initDevInfo(String str);

    protected abstract void setNetworkDevinfo();

    protected abstract void timeline(int i, int i2, String str);

    protected abstract void userInfor(String str);

    public Platform(Context context) {
        this.context = context;
        this.f1175a = new C0604c(this, context);
    }

    /* renamed from: a */
    void m1909a() {
        this.f1175a.m2187a(false);
        this.f1175a.m2184a(getName());
    }

    protected void copyDevinfo(String str, String str2) {
        ShareSDK.m1982a(str, str2);
    }

    protected void copyNetworkDevinfo(int i, int i2) {
        ShareSDK.m1981a(i, i2);
    }

    public String getDevinfo(String str) {
        return getDevinfo(getName(), str);
    }

    public String getDevinfo(String str, String str2) {
        return ShareSDK.m1985b(str, str2);
    }

    protected String getNetworkDevinfo(String str, String str2) {
        return getNetworkDevinfo(getPlatformId(), str, str2);
    }

    protected String getNetworkDevinfo(int i, String str, String str2) {
        return this.f1175a.m2177a(i, str, str2);
    }

    public Context getContext() {
        return this.context;
    }

    public int getId() {
        return this.f1175a.m2176a();
    }

    public int getSortId() {
        return this.f1175a.m2189b();
    }

    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
        this.f1175a.m2183a(platformActionListener);
    }

    public PlatformActionListener getPlatformActionListener() {
        return this.f1175a.m2192c();
    }

    public boolean isAuthValid() {
        return this.f1175a.m2196d();
    }

    public boolean isClientValid() {
        return false;
    }

    @Deprecated
    public boolean isValid() {
        return this.f1175a.m2196d();
    }

    public void SSOSetting(boolean z) {
        this.f1175a.m2187a(z);
    }

    public boolean isSSODisable() {
        return this.f1175a.m2197e();
    }

    /* renamed from: b */
    boolean m1910b() {
        return this.f1175a.m2198f();
    }

    public void authorize() {
        authorize(null);
    }

    public void authorize(String[] strArr) {
        this.f1175a.m2188a(strArr);
    }

    protected void innerAuthorize(int i, Object obj) {
        this.f1175a.m2181a(i, obj);
    }

    public void share(ShareParams shareParams) {
        this.f1175a.m2182a(shareParams);
    }

    public void followFriend(String str) {
        this.f1175a.m2191b(str);
    }

    public void getTimeLine(String str, int i, int i2) {
        this.f1175a.m2185a(str, i, i2);
    }

    public void showUser(String str) {
        this.f1175a.m2194c(str);
    }

    public void listFriend(int i, int i2, String str) {
        this.f1175a.m2180a(i, i2, str);
    }

    public void customerProtocol(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        this.f1175a.m2186a(str, str2, s, hashMap, hashMap2);
    }

    protected void afterRegister(int i, Object obj) {
        this.f1175a.m2190b(i, obj);
    }

    public PlatformDb getDb() {
        return this.db;
    }

    @Deprecated
    public void removeAccount() {
        this.f1175a.m2200h();
    }

    public void removeAccount(boolean z) {
        this.f1175a.m2200h();
        ShareSDK.removeCookieOnAuthorize(z);
    }

    public String getShortLintk(String str, boolean z) {
        return this.f1175a.m2179a(str, z);
    }

    protected String uploadImageToFileServer(String str) {
        return this.f1175a.m2195d(str);
    }

    protected String uploadImageToFileServer(Bitmap bitmap) {
        return this.f1175a.m2178a(bitmap);
    }
}

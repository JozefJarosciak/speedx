package cn.sharesdk.framework;

import android.content.Context;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import java.util.HashMap;

public abstract class CustomPlatform extends Platform {
    protected abstract boolean checkAuthorize(int i, Object obj);

    public abstract String getName();

    public CustomPlatform(Context context) {
        super(context);
    }

    protected final void initDevInfo(String str) {
    }

    public int getVersion() {
        return 0;
    }

    protected final int getPlatformId() {
        return -Math.abs(getCustomPlatformId());
    }

    protected int getCustomPlatformId() {
        return 1;
    }

    protected final void setNetworkDevinfo() {
    }

    protected void doAuthorize(String[] strArr) {
    }

    protected void doShare(ShareParams shareParams) {
    }

    protected void follow(String str) {
    }

    protected void timeline(int i, int i2, String str) {
    }

    protected void userInfor(String str) {
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
    }

    protected final C0596a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap) {
        return null;
    }

    protected void getFriendList(int i, int i2, String str) {
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    public boolean hasShareCallback() {
        return false;
    }
}

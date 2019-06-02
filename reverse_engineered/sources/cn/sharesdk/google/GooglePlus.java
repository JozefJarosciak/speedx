package cn.sharesdk.google;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.cons.C0844a;
import com.avos.avoscloud.AVStatus;
import com.baidu.mapapi.SDKInitializer;
import com.beastbikes.framework.ui.android.WebActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import io.rong.imlib.statistics.UserData;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.HttpHost;

public class GooglePlus extends Platform {
    public static final String NAME = GooglePlus.class.getSimpleName();
    /* renamed from: a */
    private C0640a f1415a = C0640a.m2397a((Platform) this);
    /* renamed from: b */
    private String f1416b;
    /* renamed from: c */
    private String f1417c;
    /* renamed from: d */
    private boolean f1418d;

    /* renamed from: cn.sharesdk.google.GooglePlus$1 */
    class C06271 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ GooglePlus f1411a;

        C06271(GooglePlus googlePlus) {
            this.f1411a = googlePlus;
        }

        public void onError(Platform platform, int i, Throwable th) {
            if (this.f1411a.listener != null) {
                this.f1411a.listener.onError(platform, i, th);
            }
            this.f1411a.f1415a.m2406b();
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            String str = hashMap.containsKey("DisplayName") ? (String) hashMap.get("DisplayName") : "";
            String str2 = hashMap.containsKey(AVStatus.IMAGE_TAG) ? (String) hashMap.get(AVStatus.IMAGE_TAG) : "";
            String valueOf = hashMap.containsKey(UserData.GENDER_KEY) ? String.valueOf(hashMap.get(UserData.GENDER_KEY)) : "";
            String str3 = hashMap.containsKey("url") ? (String) hashMap.get("url") : "";
            String str4 = hashMap.containsKey("birthday") ? (String) hashMap.get("birthday") : "";
            this.f1411a.db.putUserId((String) hashMap.get("id"));
            this.f1411a.db.put("nickname", str);
            this.f1411a.db.put("icon", str2);
            this.f1411a.db.put(UserData.GENDER_KEY, valueOf);
            this.f1411a.db.put("snsUserUrl", str3);
            this.f1411a.db.put("birthday", str4);
            this.f1411a.db.put("isSigin", "true");
            if (this.f1411a.listener != null) {
                this.f1411a.listener.onComplete(platform, i, hashMap);
            }
            this.f1411a.f1415a.m2406b();
        }

        public void onCancel(Platform platform, int i) {
            if (this.f1411a.listener != null) {
                this.f1411a.listener.onCancel(platform, i);
            }
            this.f1411a.f1415a.m2406b();
        }
    }

    /* renamed from: cn.sharesdk.google.GooglePlus$2 */
    class C06282 implements AuthorizeListener {
        /* renamed from: a */
        final /* synthetic */ GooglePlus f1412a;

        C06282(GooglePlus googlePlus) {
            this.f1412a = googlePlus;
        }

        public void onComplete(Bundle bundle) {
            long parseLong;
            String string = bundle.getString("access_token");
            String string2 = bundle.getString("expires_in");
            String string3 = bundle.getString("token_type");
            this.f1412a.db.putToken(string);
            this.f1412a.db.put("token_type", string3);
            try {
                parseLong = C4275R.parseLong(string2);
            } catch (Throwable th) {
                parseLong = 0;
            }
            this.f1412a.db.putExpiresIn(parseLong);
            this.f1412a.f1415a.m2408c(string);
            this.f1412a.afterRegister(1, null);
        }

        public void onError(Throwable th) {
            if (this.f1412a.listener != null) {
                this.f1412a.listener.onError(this.f1412a, 1, th);
            }
        }

        public void onCancel() {
            if (this.f1412a.listener != null) {
                this.f1412a.listener.onCancel(this.f1412a, 1);
            }
        }
    }

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
    }

    public GooglePlus(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.f1416b = getDevinfo("ClientID");
        this.f1417c = getDevinfo("RedirectUrl");
        this.f1418d = "true".equals(getDevinfo("ShareByAppClient"));
    }

    public int getPlatformId() {
        return 14;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    @Deprecated
    public boolean isValid() {
        return C0648c.m2437b(this.context) == 0 && this.db.get("isSigin").equals("true");
    }

    public boolean isAuthValid() {
        if (super.isAuthValid()) {
            return true;
        }
        if (isClientValid() && this.db.get("isSigin").equals("true")) {
            return true;
        }
        return false;
    }

    public boolean isClientValid() {
        return C0648c.m2437b(this.context) == 0;
    }

    protected void setNetworkDevinfo() {
    }

    public void removeAccount(boolean z) {
        super.removeAccount(z);
        try {
            this.db.put("isSigin", "false");
            this.f1415a.m2399a();
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }

    protected void doAuthorize(String[] strArr) {
        if (!isClientValid() || isSSODisable()) {
            doWebAuthorize(strArr);
            return;
        }
        this.f1415a.m2405a(strArr, new C06271(this), this.db);
    }

    protected void doWebAuthorize(String[] strArr) {
        this.f1415a.m2401a(this.f1416b);
        this.f1415a.m2407b(this.f1417c);
        this.f1415a.m2404a(strArr);
        this.f1415a.m2400a(new C06282(this));
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (i == 9) {
            return true;
        }
        if (super.isAuthValid()) {
            this.f1415a.m2401a(this.f1416b);
            this.f1415a.m2407b(this.f1417c);
            this.f1415a.m2408c(this.db.getToken());
            Object token = this.db.getToken();
            if (!TextUtils.isEmpty(token)) {
                this.f1415a.m2408c(token);
                return true;
            }
        } else if (isClientValid() && this.db.get("isSigin").equals("true")) {
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doShare(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        String str = null;
        String text = shareParams.getText();
        String imagePath = shareParams.getImagePath();
        String imageUrl = shareParams.getImageUrl();
        String url = shareParams.getUrl();
        if (!TextUtils.isEmpty(text)) {
            try {
                if (!TextUtils.isEmpty(text) && text.contains(HttpHost.DEFAULT_SCHEME_NAME)) {
                    imagePath = null;
                } else if (!TextUtils.isEmpty(imagePath) && new File(imagePath).exists()) {
                    str = imagePath;
                    imagePath = null;
                } else if (TextUtils.isEmpty(imageUrl) || !imageUrl.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    imagePath = null;
                } else {
                    File file = new File(BitmapHelper.downloadBitmap(getContext(), imageUrl));
                    if (file.exists()) {
                        imagePath = file.getAbsolutePath();
                    }
                    str = imagePath;
                    imagePath = null;
                }
                PlatformActionListener c06293 = new PlatformActionListener(this) {
                    /* renamed from: b */
                    final /* synthetic */ GooglePlus f1414b;

                    public void onError(Platform platform, int i, Throwable th) {
                        if (this.f1414b.listener != null) {
                            this.f1414b.listener.onError(this.f1414b, 9, th);
                        }
                    }

                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        HashMap hashMap2 = new HashMap();
                        if (hashMap != null) {
                            hashMap2.putAll(hashMap);
                        }
                        hashMap2.put("ShareParams", shareParams);
                        if (this.f1414b.listener != null) {
                            this.f1414b.listener.onComplete(this.f1414b, 9, hashMap2);
                        }
                    }

                    public void onCancel(Platform platform, int i) {
                        if (this.f1414b.listener != null) {
                            this.f1414b.listener.onCancel(this.f1414b, 9);
                        }
                    }
                };
                if (isClientValid() && this.f1418d) {
                    this.f1415a.m2403a(text, imagePath, str, c06293);
                } else {
                    this.f1415a.m2402a(text, url, c06293);
                }
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 9, new Throwable("share text should not null or empty!"));
        }
    }

    protected void userInfor(String str) {
        HashMap hashMap;
        if (isClientValid() && this.db.get("isSigin").equals("true")) {
            hashMap = new HashMap();
            hashMap.put("DisplayName", this.db.get("nickname"));
            hashMap.put(AVStatus.IMAGE_TAG, this.db.get("icon"));
            hashMap.put(UserData.GENDER_KEY, this.db.get(UserData.GENDER_KEY));
            hashMap.put("url", this.db.get("snsUserUrl"));
            hashMap.put("birthday", this.db.get("birthday"));
            hashMap.put("id", this.db.getUserId());
            if (this.listener != null) {
                this.listener.onComplete(this, 8, hashMap);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = "me";
        }
        try {
            HashMap d = this.f1415a.m2409d(str);
            if (d != null && d.size() > 0) {
                this.db.putUserId(String.valueOf(d.get("id")));
                this.db.put("nickname", String.valueOf(d.get("displayName")));
                hashMap = d.containsKey(AVStatus.IMAGE_TAG) ? (HashMap) d.get(AVStatus.IMAGE_TAG) : null;
                if (hashMap != null) {
                    this.db.put("icon", String.valueOf(hashMap.get("url")));
                }
                this.db.put(UserData.GENDER_KEY, "male".equals(String.valueOf(d.get(UserData.GENDER_KEY))) ? "0" : C0844a.f2048d);
                this.db.put("snsUserUrl", String.valueOf(d.get("url")));
                this.db.put("resume", String.valueOf(d.get("aboutMe")));
                if (String.valueOf(d.get("verified")).equals("true")) {
                    this.db.put("secretType", C0844a.f2048d);
                } else {
                    this.db.put("secretType", "0");
                }
                if (d.containsKey("birthday") && d.get("birthday") != null) {
                    try {
                        String[] split = String.valueOf(d.get("birthday")).split(HelpFormatter.DEFAULT_OPT_PREFIX);
                        Calendar instance = Calendar.getInstance();
                        instance.set(1, C4275R.parseInt(split[0]));
                        instance.set(2, C4275R.parseInt(split[1]) - 1);
                        instance.set(5, C4275R.parseInt(split[2]));
                        this.db.put("birthday", String.valueOf(instance.getTimeInMillis()));
                    } catch (Throwable th) {
                        C0621d.m2279a().d(th);
                    }
                }
                if (d.containsKey("organizations")) {
                    try {
                        ArrayList arrayList = (ArrayList) d.get("organizations");
                        if (arrayList != null && arrayList.size() > 0) {
                            String fromHashMap;
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList arrayList3 = new ArrayList();
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                hashMap = (HashMap) it.next();
                                String str2 = (String) hashMap.get("type");
                                HashMap hashMap2;
                                if (Pattern.compile("school|college|university").matcher(str2.toLowerCase()).find()) {
                                    hashMap2 = new HashMap();
                                    hashMap2.put("school_type", Integer.valueOf(0));
                                    hashMap2.put("school", String.valueOf(hashMap.get("name")));
                                    hashMap2.put("background", Integer.valueOf(0));
                                    arrayList2.add(hashMap2);
                                } else if (Pattern.compile("work|company|firm|enterprise").matcher(str2.toLowerCase()).find()) {
                                    hashMap2 = new HashMap();
                                    hashMap2.put("company", String.valueOf(hashMap.get("name")));
                                    hashMap2.put("dept", String.valueOf(hashMap.get("department")));
                                    hashMap2.put("position", String.valueOf(hashMap.get(WebActivity.EXTRA_TITLE)));
                                    arrayList3.add(hashMap2);
                                }
                            }
                            if (arrayList2.size() > 0) {
                                hashMap = new HashMap();
                                hashMap.put("list", arrayList2);
                                fromHashMap = new Hashon().fromHashMap(hashMap);
                                this.db.put("educationJSONArrayStr", fromHashMap.substring(8, fromHashMap.length() - 1));
                            }
                            if (arrayList3.size() > 0) {
                                hashMap = new HashMap();
                                hashMap.put("list", arrayList3);
                                fromHashMap = new Hashon().fromHashMap(hashMap);
                                this.db.put("workJSONArrayStr", fromHashMap.substring(8, fromHashMap.length() - 1));
                            }
                        }
                    } catch (Throwable th2) {
                        C0621d.m2279a().d(th2);
                    }
                }
                if (this.listener != null) {
                    this.listener.onComplete(this, 8, d);
                }
            }
        } catch (Throwable th22) {
            if (this.listener != null) {
                this.listener.onError(this, 8, th22);
            }
        }
    }

    protected C0596a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        C0596a c0596a = new C0596a();
        c0596a.f1301b = shareParams.getText();
        CharSequence imagePath = shareParams.getImagePath();
        CharSequence imageUrl = shareParams.getImageUrl();
        if (!TextUtils.isEmpty(imagePath)) {
            c0596a.f1304e.add(imagePath);
        } else if (!TextUtils.isEmpty(imageUrl)) {
            c0596a.f1303d.add(imageUrl);
        }
        return c0596a;
    }

    protected void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    protected void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    protected void getFriendList(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = "me";
        }
        try {
            HashMap e = this.f1415a.m2410e(str);
            if (e == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable());
                }
            } else if (!e.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || ((Integer) e.get(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, 2, e);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(e)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, 2, th);
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
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
        HashMap<String, Object> hashMap2 = new HashMap();
        switch (i) {
            case 2:
                hashMap2.put("type", "FOLLOWING");
                break;
            case 10:
                hashMap2.put("type", "FRIENDS");
                break;
            case 11:
                hashMap2.put("type", "FOLLOWERS");
                break;
            default:
                return null;
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        if (Integer.parseInt(String.valueOf(hashMap.get("totalItems"))) == 0) {
            return null;
        }
        Object obj = hashMap.get("items");
        if (obj == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put("snsuid", String.valueOf(hashMap3.get("id")));
                hashMap4.put("nickname", String.valueOf(hashMap3.get("displayName")));
                HashMap hashMap5 = hashMap3.containsKey(AVStatus.IMAGE_TAG) ? (HashMap) hashMap3.get(AVStatus.IMAGE_TAG) : null;
                if (hashMap5 != null) {
                    hashMap4.put("icon", String.valueOf(hashMap5.get("url")));
                }
                if (String.valueOf(hashMap3.get("verified")).equals("true")) {
                    hashMap4.put("secretType", C0844a.f2048d);
                } else {
                    hashMap4.put("secretType", "0");
                }
                if (String.valueOf(hashMap3.get(UserData.GENDER_KEY)).equals("male")) {
                    hashMap4.put(UserData.GENDER_KEY, "0");
                } else {
                    hashMap4.put(UserData.GENDER_KEY, C0844a.f2048d);
                }
                hashMap4.put("snsUserUrl", String.valueOf(hashMap3.get("url")));
                hashMap4.put("resume", String.valueOf(hashMap3.get("aboutMe")));
                if (hashMap3.containsKey("birthday")) {
                    try {
                        String[] split = String.valueOf(hashMap3.get("birthday")).split(HelpFormatter.DEFAULT_OPT_PREFIX);
                        Calendar instance = Calendar.getInstance();
                        instance.set(1, C4275R.parseInt(split[0]));
                        instance.set(2, C4275R.parseInt(split[1]) - 1);
                        instance.set(5, C4275R.parseInt(split[2]));
                        hashMap4.put("birthday", String.valueOf(instance.getTimeInMillis()));
                    } catch (Throwable th) {
                        C0621d.m2279a().d(th);
                    }
                }
                if (hashMap3.containsKey("organizations")) {
                    arrayList2 = (ArrayList) hashMap3.get("organizations");
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        String fromHashMap;
                        ArrayList arrayList3 = new ArrayList();
                        ArrayList arrayList4 = new ArrayList();
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            hashMap3 = (HashMap) it2.next();
                            String str = (String) hashMap3.get("type");
                            if (Pattern.compile("school|college|university").matcher(str.toLowerCase()).find()) {
                                hashMap5 = new HashMap();
                                hashMap5.put("school_type", Integer.valueOf(0));
                                hashMap5.put("school", String.valueOf(hashMap3.get("name")));
                                hashMap5.put("background", Integer.valueOf(0));
                                arrayList3.add(hashMap5);
                            } else if (Pattern.compile("work|company|firm|enterprise").matcher(str.toLowerCase()).find()) {
                                hashMap5 = new HashMap();
                                hashMap5.put("company", String.valueOf(hashMap3.get("name")));
                                hashMap5.put("dept", String.valueOf(hashMap3.get("department")));
                                hashMap5.put("position", String.valueOf(hashMap3.get(WebActivity.EXTRA_TITLE)));
                                arrayList4.add(hashMap5);
                            }
                        }
                        if (arrayList3.size() > 0) {
                            hashMap3 = new HashMap();
                            hashMap3.put("list", arrayList3);
                            fromHashMap = new Hashon().fromHashMap(hashMap3);
                            hashMap4.put("educationJSONArrayStr", fromHashMap.substring(8, fromHashMap.length() - 1));
                        }
                        if (arrayList4.size() > 0) {
                            hashMap3 = new HashMap();
                            hashMap3.put("list", arrayList4);
                            fromHashMap = new Hashon().fromHashMap(hashMap3);
                            hashMap4.put("workJSONArrayStr", fromHashMap.substring(8, fromHashMap.length() - 1));
                        }
                    }
                }
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        if (2 == i) {
            hashMap2.put("nextPageToken", hashMap.get("nextPageToken"));
        }
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return false;
    }
}

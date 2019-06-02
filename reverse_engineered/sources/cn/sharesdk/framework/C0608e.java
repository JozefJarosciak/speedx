package cn.sharesdk.framework;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;
import cn.sharesdk.BuildConfig;
import cn.sharesdk.framework.p011b.C0590a;
import cn.sharesdk.framework.p011b.C0601d;
import cn.sharesdk.framework.p011b.p013b.C0591c;
import cn.sharesdk.framework.p011b.p013b.C0592a;
import cn.sharesdk.framework.p011b.p013b.C0594d;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.ReflectHelper;
import dalvik.system.DexFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: ShareSDKCore */
/* renamed from: cn.sharesdk.framework.e */
public class C0608e {
    /* renamed from: a */
    private static boolean f1362a = true;
    /* renamed from: b */
    private Context f1363b;
    /* renamed from: c */
    private String f1364c;

    /* compiled from: ShareSDKCore */
    /* renamed from: cn.sharesdk.framework.e$1 */
    class C06071 implements Comparator<Platform> {
        /* renamed from: a */
        final /* synthetic */ C0608e f1361a;

        C06071(C0608e c0608e) {
            this.f1361a = c0608e;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2202a((Platform) obj, (Platform) obj2);
        }

        /* renamed from: a */
        public int m2202a(Platform platform, Platform platform2) {
            if (platform.getSortId() != platform2.getSortId()) {
                return platform.getSortId() - platform2.getSortId();
            }
            return platform.getPlatformId() - platform2.getPlatformId();
        }
    }

    public C0608e(Context context, String str) {
        this.f1363b = context;
        this.f1364c = str;
    }

    /* renamed from: a */
    public ArrayList<Platform> m2210a() {
        ArrayList e;
        if (f1362a) {
            e = m2205e();
        } else {
            PackageInfo d = m2204d();
            if (d == null) {
                return null;
            }
            e = m2203a(d);
        }
        m2216a(e);
        return e;
    }

    /* renamed from: d */
    private PackageInfo m2204d() {
        try {
            PackageManager packageManager = this.f1363b.getPackageManager();
            String packageName = this.f1363b.getPackageName();
            for (PackageInfo packageInfo : (List) ReflectHelper.invokeInstanceMethod(packageManager, "getInstalledPackages", new Object[]{Integer.valueOf(8192)})) {
                if (packageName.equals(packageInfo.packageName)) {
                    return packageInfo;
                }
            }
            return null;
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return null;
        }
    }

    /* renamed from: a */
    private ArrayList<Platform> m2203a(PackageInfo packageInfo) {
        ArrayList<Platform> arrayList = new ArrayList();
        try {
            DexFile dexFile = new DexFile(packageInfo.applicationInfo.sourceDir);
            Enumeration entries = dexFile.entries();
            dexFile.close();
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return null;
        }
        while (entries != null && entries.hasMoreElements()) {
            String str = (String) entries.nextElement();
            if (str.startsWith(BuildConfig.APPLICATION_ID) && !str.contains("$")) {
                try {
                    Class cls = Class.forName(str);
                    if (!(cls == null || !Platform.class.isAssignableFrom(cls) || CustomPlatform.class.isAssignableFrom(cls))) {
                        Constructor constructor = cls.getConstructor(new Class[]{Context.class});
                        constructor.setAccessible(true);
                        arrayList.add((Platform) constructor.newInstance(new Object[]{this.f1363b}));
                    }
                } catch (Throwable th2) {
                    C0621d.m2279a().w(th2);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    private ArrayList<Platform> m2205e() {
        String[] strArr = new String[]{"cn.sharesdk.douban.Douban", "cn.sharesdk.evernote.Evernote", "cn.sharesdk.facebook.Facebook", "cn.sharesdk.renren.Renren", "cn.sharesdk.sina.weibo.SinaWeibo", "cn.sharesdk.kaixin.KaiXin", "cn.sharesdk.linkedin.LinkedIn", "cn.sharesdk.system.email.Email", "cn.sharesdk.system.text.ShortMessage", "cn.sharesdk.tencent.qq.QQ", "cn.sharesdk.tencent.qzone.QZone", "cn.sharesdk.tencent.weibo.TencentWeibo", "cn.sharesdk.twitter.Twitter", "cn.sharesdk.wechat.friends.Wechat", "cn.sharesdk.wechat.moments.WechatMoments", "cn.sharesdk.wechat.favorite.WechatFavorite", "cn.sharesdk.youdao.YouDao", "cn.sharesdk.google.GooglePlus", "cn.sharesdk.foursquare.FourSquare", "cn.sharesdk.pinterest.Pinterest", "cn.sharesdk.flickr.Flickr", "cn.sharesdk.tumblr.Tumblr", "cn.sharesdk.dropbox.Dropbox", "cn.sharesdk.vkontakte.VKontakte", "cn.sharesdk.instagram.Instagram", "cn.sharesdk.yixin.friends.Yixin", "cn.sharesdk.yixin.moments.YixinMoments", "cn.sharesdk.mingdao.Mingdao", "cn.sharesdk.line.Line", "cn.sharesdk.kakao.story.KakaoStory", "cn.sharesdk.kakao.talk.KakaoTalk", "cn.sharesdk.whatsapp.WhatsApp", "cn.sharesdk.pocket.Pocket", "cn.sharesdk.instapaper.Instapaper", "cn.sharesdk.facebookmessenger.FacebookMessenger", "cn.sharesdk.alipay.friends.Alipay", "cn.sharesdk.alipay.moments.AlipayMoments"};
        ArrayList<Platform> arrayList = new ArrayList();
        for (String cls : strArr) {
            try {
                Constructor constructor = Class.forName(cls).getConstructor(new Class[]{Context.class});
                constructor.setAccessible(true);
                arrayList.add((Platform) constructor.newInstance(new Object[]{this.f1363b}));
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m2216a(ArrayList<Platform> arrayList) {
        if (arrayList != null) {
            Collections.sort(arrayList, new C06071(this));
        }
    }

    /* renamed from: a */
    public void m2213a(Handler handler, boolean z, int i) {
        C0601d a = C0601d.m2163a(this.f1363b, this.f1364c);
        if (a != null) {
            a.m2169a(handler);
            a.m2171a(z);
            a.m2168a(i);
            a.startThread();
        }
    }

    /* renamed from: b */
    public void m2218b() {
        C0601d a = C0601d.m2163a(this.f1363b, this.f1364c);
        if (a != null) {
            a.stopThread();
        }
    }

    /* renamed from: a */
    public void m2212a(int i, Platform platform) {
        C0591c c0594d = new C0594d();
        switch (i) {
            case 1:
                c0594d.f1294a = "SHARESDK_ENTER_SHAREMENU";
                break;
            case 2:
                c0594d.f1294a = "SHARESDK_CANCEL_SHAREMENU";
                break;
            case 3:
                c0594d.f1294a = "SHARESDK_EDIT_SHARE";
                break;
            case 4:
                c0594d.f1294a = "SHARESDK_FAILED_SHARE";
                break;
            case 5:
                c0594d.f1294a = "SHARESDK_CANCEL_SHARE";
                break;
        }
        if (platform != null) {
            c0594d.f1295b = platform.getPlatformId();
        }
        C0601d a = C0601d.m2163a(this.f1363b, this.f1364c);
        if (a != null) {
            a.m2170a(c0594d);
        }
    }

    /* renamed from: a */
    public void m2215a(String str, int i) {
        C0601d a = C0601d.m2163a(this.f1363b, this.f1364c);
        if (a != null) {
            C0591c c0592a = new C0592a();
            c0592a.f1285b = str;
            c0592a.f1284a = i;
            a.m2170a(c0592a);
        }
    }

    /* renamed from: a */
    public void m2214a(C0612f c0612f) {
    }

    /* renamed from: a */
    public String m2206a(int i, String str, HashMap<Integer, HashMap<String, Object>> hashMap) {
        HashMap hashMap2 = (HashMap) hashMap.get(Integer.valueOf(i));
        if (hashMap2 == null) {
            return null;
        }
        Object obj = hashMap2.get(str);
        return obj == null ? null : String.valueOf(obj);
    }

    /* renamed from: a */
    public boolean m2217a(HashMap<String, Object> hashMap, HashMap<Integer, HashMap<String, Object>> hashMap2) {
        if (hashMap == null || hashMap.size() <= 0) {
            return false;
        }
        ArrayList arrayList = (ArrayList) hashMap.get("fakelist");
        if (arrayList == null) {
            return false;
        }
        EventRecorder.addBegin("ShareSDK", "parseDevInfo");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                int parseInt;
                try {
                    parseInt = C4275R.parseInt(String.valueOf(hashMap3.get("snsplat")));
                } catch (Throwable th) {
                    C0621d.m2279a().w(th);
                    parseInt = -1;
                }
                if (parseInt != -1) {
                    hashMap2.put(Integer.valueOf(parseInt), hashMap3);
                }
            }
        }
        EventRecorder.addEnd("ShareSDK", "parseDevInfo");
        return true;
    }

    /* renamed from: a */
    public String m2209a(String str, boolean z, int i, String str2) {
        return C0590a.m2063a(this.f1363b, this.f1364c).m2074a(str, i, z, str2);
    }

    /* renamed from: a */
    public String m2208a(String str) {
        return C0590a.m2063a(this.f1363b, this.f1364c).m2073a(str);
    }

    /* renamed from: a */
    public String m2207a(Bitmap bitmap) {
        return C0590a.m2063a(this.f1363b, this.f1364c).m2072a(bitmap);
    }

    /* renamed from: c */
    public String m2219c() {
        return "2.7.10";
    }

    /* renamed from: a */
    public void m2211a(int i, int i2, HashMap<Integer, HashMap<String, Object>> hashMap) {
        hashMap.put(Integer.valueOf(i2), (HashMap) hashMap.get(Integer.valueOf(i)));
    }
}

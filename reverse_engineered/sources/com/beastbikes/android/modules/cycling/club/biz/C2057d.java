package com.beastbikes.android.modules.cycling.club.biz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedPost;
import com.beastbikes.android.modules.cycling.club.dto.ImageInfo;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ClubFeedService */
/* renamed from: com.beastbikes.android.modules.cycling.club.biz.d */
public class C2057d extends Thread {
    /* renamed from: a */
    private static final Logger f9343a = LoggerFactory.getLogger(C2057d.class);
    /* renamed from: b */
    private static C2057d f9344b;
    /* renamed from: c */
    private List<ClubFeed> f9345c;
    /* renamed from: d */
    private Handler f9346d;
    /* renamed from: e */
    private C2052c f9347e;
    /* renamed from: f */
    private final BroadcastReceiver f9348f = new C2055b();
    /* renamed from: g */
    private C2054a f9349g;
    /* renamed from: h */
    private C2056c f9350h;

    /* compiled from: ClubFeedService */
    /* renamed from: com.beastbikes.android.modules.cycling.club.biz.d$a */
    public interface C2054a {
        /* renamed from: a */
        void m10596a(String str);
    }

    /* compiled from: ClubFeedService */
    /* renamed from: com.beastbikes.android.modules.cycling.club.biz.d$b */
    private final class C2055b extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C2057d f9342a;

        private C2055b(C2057d c2057d) {
            this.f9342a = c2057d;
        }

        public void onReceive(Context context, Intent intent) {
            if (!TextUtils.isEmpty(intent.getAction())) {
                switch (C2799c.m13754b(context)) {
                    case 1:
                    case 9:
                        if (AVUser.getCurrentUser() != null) {
                            this.f9342a.m10605a(BeastBikes.j());
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* compiled from: ClubFeedService */
    /* renamed from: com.beastbikes.android.modules.cycling.club.biz.d$c */
    public interface C2056c {
        /* renamed from: a */
        void m10597a(int i, int i2, String str);
    }

    /* renamed from: a */
    public void m10606a(C2054a c2054a) {
        this.f9349g = c2054a;
    }

    /* renamed from: a */
    public void m10607a(C2056c c2056c) {
        this.f9350h = c2056c;
    }

    private C2057d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        BeastBikes.j().registerReceiver(this.f9348f, intentFilter);
        start();
    }

    /* renamed from: a */
    public static C2057d m10598a() {
        if (f9344b == null) {
            C2057d.m10601c();
        }
        return f9344b;
    }

    /* renamed from: b */
    public void m10609b() {
        this.f9347e = null;
    }

    /* renamed from: b */
    private C2052c m10600b(Context context) {
        if (this.f9347e == null) {
            this.f9347e = new C2052c(context);
        }
        return this.f9347e;
    }

    /* renamed from: c */
    public static void m10601c() {
        if (f9344b == null) {
            synchronized (C2057d.class) {
                if (f9344b == null) {
                    f9344b = new C2057d();
                }
            }
        }
    }

    /* renamed from: a */
    public void m10608a(ClubFeed clubFeed, String str) {
        if (clubFeed == null) {
            throw new RuntimeException("ClubFeed is null");
        }
        this.f9347e = m10600b(BeastBikes.j());
        this.f9345c = this.f9347e.m10575a(str, "clubfeed_cache_post");
        if (this.f9345c == null) {
            this.f9345c = new ArrayList();
        }
        this.f9345c.add(0, clubFeed);
        f9343a.info("clubFeedPostQueueIn clubId =" + str + ",queueSize=" + this.f9345c.size());
        this.f9347e.m10580a(this.f9345c, "clubfeed_cache_post", str);
        m10610d();
    }

    /* renamed from: a */
    public void m10604a(int i, String str) {
        int i2 = 0;
        while (this.f9345c != null && i2 < this.f9345c.size()) {
            if (((ClubFeed) this.f9345c.get(i2)).getFid() == i) {
                this.f9345c.remove(i2);
                break;
            }
            i2++;
        }
        this.f9347e = m10600b(BeastBikes.j());
        this.f9347e.m10580a(this.f9345c, "clubfeed_cache_post", str);
    }

    /* renamed from: a */
    public void m10605a(Context context) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null && context != null) {
            String string = context.getSharedPreferences(currentUser.getObjectId(), 0).getString("beast.club.id", null);
            if (!TextUtils.isEmpty(string)) {
                this.f9347e = m10600b(BeastBikes.j());
                this.f9345c = this.f9347e.m10575a(string, "clubfeed_cache_post");
            }
            m10610d();
        }
    }

    /* renamed from: d */
    public void m10610d() {
        if (this.f9346d != null) {
            this.f9346d.sendEmptyMessage(1);
        }
    }

    /* renamed from: f */
    private void m10603f() {
        C2060g c2060g = new C2060g();
        int i = 0;
        while (this.f9345c != null && i < this.f9345c.size()) {
            String str = "";
            ClubFeed clubFeed = (ClubFeed) this.f9345c.get(i);
            Object clubId = clubFeed.getPost().getClubId();
            if (clubFeed.getState() == 1) {
                List list;
                int size;
                ClubFeedPost post = clubFeed.getPost();
                if (clubFeed.getImageTxt() != null) {
                    List imageList = clubFeed.getImageTxt().getImageList();
                    list = imageList;
                    size = imageList.size();
                } else {
                    size = 0;
                    list = null;
                }
                int i2 = 0;
                while (list != null && i2 < list.size()) {
                    ImageInfo imageInfo = (ImageInfo) list.get(i2);
                    if (!(imageInfo == null || TextUtils.isEmpty(imageInfo.getUrl()) || imageInfo.getUrl().contains("http://"))) {
                        File file = new File(imageInfo.getUrl());
                        if (file.exists()) {
                            try {
                                ImageInfo a = c2060g.m10617a(file, C2798a.m13751a(UUID.randomUUID().toString()), 3).m10618a();
                                if (a != null) {
                                    imageInfo.setUrl(a.getUrl());
                                    imageInfo.setId(a.getId());
                                }
                                if (this.f9350h != null) {
                                    f9343a.info("uploading progress =" + i2 + "size =" + size);
                                    this.f9350h.m10597a(i2 + 1, size, clubId);
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                    i2++;
                }
                post.setPostImageList(list);
                try {
                    this.f9347e = m10600b(BeastBikes.j());
                    if (post.getType() == 1) {
                        this.f9347e.m10594c(post.getCompleteListJsonStr(), post.getContent());
                        this.f9345c.remove(clubFeed);
                        this.f9347e.m10580a(this.f9345c, "clubfeed_cache_post", post.getClubId());
                    } else if (this.f9347e.m10569a(post.getClubId(), post.getContent(), post.getSportIdentify(), post.getCompleteListJsonStr(), post.isNeedSync()) != null) {
                        this.f9345c.remove(clubFeed);
                        this.f9347e.m10580a(this.f9345c, "clubfeed_cache_post", post.getClubId());
                    } else {
                        f9343a.error(C2057d.class.getSimpleName(), "clubfeed post error feed is null");
                    }
                } catch (BusinessException e2) {
                    f9343a.error(C2057d.class.getSimpleName(), "schedule excute error" + e2);
                }
            }
            if (!(this.f9349g == null || TextUtils.isEmpty(clubId))) {
                this.f9349g.m10596a(clubId);
                f9343a.info("schedule notify to ui");
            }
            i++;
        }
    }

    public void run() {
        Looper.prepare();
        f9343a.info("schedule run begin  !");
        this.f9346d = new Handler(this, Looper.myLooper()) {
            /* renamed from: a */
            final /* synthetic */ C2057d f9341a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        C2057d.f9343a.info("schedule start !");
                        this.f9341a.m10603f();
                        return;
                    case 2:
                        C2057d.f9343a.info("schedule stop !");
                        Looper.myLooper().quit();
                        return;
                    default:
                        return;
                }
            }
        };
        Looper.loop();
        f9343a.info("schedule run end !");
    }
}

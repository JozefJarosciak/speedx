package com.beastbikes.android.modules.cycling.club.biz;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.club.dto.C2066e;
import com.beastbikes.android.modules.cycling.club.dto.ClubCache;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.android.utils.C2563k;
import com.beastbikes.android.utils.C2578u;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ClubFeedManager */
/* renamed from: com.beastbikes.android.modules.cycling.club.biz.c */
public class C2052c extends C1397a implements C1371a {
    /* renamed from: a */
    private static final Logger f9337a = LoggerFactory.getLogger(C2052c.class);
    /* renamed from: b */
    private C1403e f9338b;
    /* renamed from: c */
    private Activity f9339c;
    /* renamed from: d */
    private Context f9340d;

    /* compiled from: ClubFeedManager */
    /* renamed from: com.beastbikes.android.modules.cycling.club.biz.c$a */
    public interface C2050a {
        /* renamed from: a */
        void m10566a(List<ClubFeed> list);
    }

    /* compiled from: ClubFeedManager */
    /* renamed from: com.beastbikes.android.modules.cycling.club.biz.c$b */
    public interface C2051b {
        /* renamed from: a */
        void m10567a(List<ClubPhotoDTO> list);
    }

    public C2052c(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f9340d = context;
        this.f9338b = (C1403e) new C1772d(context).m9399a(C1403e.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    public C2052c(Activity activity) {
        this((Context) activity);
        this.f9339c = activity;
    }

    /* renamed from: a */
    public List<ClubFeed> m10573a(String str, int i, C2050a c2050a) throws BusinessException {
        List a;
        Collection a2 = m10575a(str, "clubfeed_cache_post");
        if (c2050a != null) {
            a = m10572a(str);
            if (a == null) {
                a = new ArrayList();
            }
            if (a2 != null) {
                a.addAll(0, a2);
            }
            c2050a.m10566a(a);
        }
        try {
            JSONObject a3 = this.f9338b.a(str, 0, 0, i);
            if (C2563k.m12869a(a3)) {
                return null;
            }
            String optString = a3.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a3.optInt("code") != 0) {
                return null;
            }
            a = m10577a(a3);
            m10579a(a, str);
            if (a2 == null) {
                return a;
            }
            a.addAll(0, a2);
            return a;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<ClubFeed> m10574a(String str, long j, long j2, int i, C2050a c2050a) throws BusinessException {
        try {
            JSONObject a = this.f9338b.a(str, j, j2, i);
            if (C2563k.m12869a(a)) {
                return null;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a.optInt("code") == 0) {
                return m10577a(a);
            }
            return null;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<ClubFeed> m10577a(JSONObject jSONObject) {
        List<ClubFeed> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(C0882j.f2229c);
        for (int i = 0; i < optJSONArray.length(); i++) {
            ClubFeed clubFeed = new ClubFeed(optJSONArray.optJSONObject(i));
            if (clubFeed != null) {
                arrayList.add(clubFeed);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m10580a(List<ClubFeed> list, String str, String str2) {
        if (list != null) {
            try {
                C2557f.m12835a(C2578u.m12903a(new ClubCache(list)), C2557f.m12829a(this.f9340d, str, str2));
            } catch (Exception e) {
                f9337a.error("clubFeedCache error e=" + e.getMessage());
            }
        }
    }

    /* renamed from: a */
    public void m10579a(List<ClubFeed> list, String str) {
        m10580a((List) list, "clubfeed_cache", str);
    }

    /* renamed from: a */
    public List<ClubFeed> m10572a(String str) {
        return m10575a(str, "clubfeed_cache");
    }

    /* renamed from: a */
    public List<ClubFeed> m10575a(String str, String str2) {
        byte[] a = C2557f.m12836a(C2557f.m12829a(this.f9340d, str2, str));
        if (a == null) {
            return null;
        }
        try {
            ClubCache clubCache = (ClubCache) C2578u.m12902a(a);
            if (clubCache != null) {
                return clubCache.getClubFeeds();
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: b */
    public void m10587b(String str) {
        List a = m10572a(str);
        if (a != null) {
            a.clear();
            m10579a(a, str);
        }
    }

    /* renamed from: b */
    public void m10588b(List<ClubPhotoDTO> list, String str, String str2) {
        if (list != null) {
            Serializable clubCache = new ClubCache();
            clubCache.setClubPhotoDTOList(list);
            try {
                C2557f.m12835a(C2578u.m12903a(clubCache), C2557f.m12829a(this.f9340d, str, str2));
            } catch (Exception e) {
                f9337a.error("clubPhotoCache error e=" + e.getMessage());
            }
        }
    }

    /* renamed from: b */
    public List<ClubPhotoDTO> m10586b(String str, String str2) {
        byte[] a = C2557f.m12836a(C2557f.m12829a(this.f9340d, str2, str));
        if (a == null) {
            return null;
        }
        try {
            ClubCache clubCache = (ClubCache) C2578u.m12902a(a);
            if (clubCache != null) {
                return clubCache.getClubPhotoDTOList();
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: a */
    public ClubFeed m10568a(int i) throws BusinessException {
        ClubFeed clubFeed = null;
        try {
            JSONObject a = this.f9338b.a(i);
            if (a != null) {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9339c, optString);
                }
                if (a.optInt("code") == 0) {
                    a = a.optJSONObject(C0882j.f2229c);
                    if (!C2563k.m12869a(a)) {
                        clubFeed = new ClubFeed(a);
                    }
                }
            }
            return clubFeed;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public ClubFeed m10569a(String str, String str2, String str3, String str4, int i) throws BusinessException {
        try {
            f9337a.info("postClubFeed");
            JSONObject a = this.f9338b.a(str, str2, str3, str4, i);
            if (a == null) {
                return null;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a.optInt("code") == 0) {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                if (!C2563k.m12869a(optJSONObject)) {
                    return new ClubFeed(optJSONObject);
                }
            }
            return null;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m10582a(int i, int i2) throws BusinessException {
        boolean z = false;
        try {
            JSONObject a = this.f9338b.a(i, i2);
            if (a != null) {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9339c, optString);
                }
                if (a.optInt("code") == 0) {
                    z = a.optBoolean(C0882j.f2229c);
                }
            }
            return z;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<ClubUser> m10571a(int i, int i2, int i3) throws BusinessException {
        List<ClubUser> list = null;
        try {
            JSONObject a = this.f9338b.a(i, i2, i3);
            if (!C2563k.m12869a(a)) {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9339c, optString);
                }
                if (a.optInt("code") == 0) {
                    JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                    if (!C2563k.m12868a(optJSONArray)) {
                        list = new ArrayList();
                        for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                            list.add(new ClubUser(optJSONArray.optJSONObject(i4)));
                        }
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public ClubFeedComment m10570a(int i, String str, int i2) throws BusinessException {
        ClubFeedComment clubFeedComment = null;
        try {
            JSONObject a = this.f9338b.a(i, str, i2);
            if (!C2563k.m12869a(a)) {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9339c, optString);
                }
                if (a.optInt("code") == 0) {
                    a = a.optJSONObject(C0882j.f2229c);
                    if (!C2563k.m12869a(a)) {
                        clubFeedComment = new ClubFeedComment(a);
                    }
                }
            }
            return clubFeedComment;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public List<ClubFeedComment> m10585b(int i, int i2, int i3) throws BusinessException {
        List<ClubFeedComment> list = null;
        try {
            JSONObject b = this.f9338b.b(i, i2, i3);
            if (!C2563k.m12869a(b)) {
                String optString = b.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9339c, optString);
                }
                if (b.optInt("code") == 0) {
                    JSONArray optJSONArray = b.optJSONArray(C0882j.f2229c);
                    if (!C2563k.m12868a(optJSONArray)) {
                        list = new ArrayList();
                        for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                            list.add(new ClubFeedComment(optJSONArray.optJSONObject(i4)));
                        }
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m10583a(int i, int i2, String str) throws BusinessException {
        if (i2 == 1) {
            try {
                C2057d.m10598a().m10604a(i, str);
                return true;
            } catch (Throwable e) {
                throw new BusinessException(e);
            }
        }
        m10587b(str);
        JSONObject b = this.f9338b.b(i);
        String optString = b.optString(AVStatus.MESSAGE_TAG);
        if (!TextUtils.isEmpty(optString)) {
            Toasts.showOnUiThread(this.f9339c, optString);
        }
        if (b.optInt("code") == 0) {
            return b.optBoolean(C0882j.f2229c);
        }
        return false;
    }

    /* renamed from: b */
    public boolean m10589b(int i) throws BusinessException {
        try {
            JSONObject c = this.f9338b.c(i);
            String optString = c.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (c.optInt("code") == 0) {
                return c.optBoolean(C0882j.f2229c);
            }
            return false;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<ClubPhotoDTO> m10576a(String str, String str2, String str3, int i, C2051b c2051b, boolean z) throws BusinessException {
        if (c2051b != null && z) {
            c2051b.m10567a(m10586b(str, "clubphoto_cache"));
        }
        try {
            JSONObject a = this.f9338b.a(str, str2, str3, i);
            if (a == null) {
                return null;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a.optInt("code") != 0) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            Map treeMap = new TreeMap();
            List arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                ClubPhotoDTO clubPhotoDTO = new ClubPhotoDTO(optJSONArray.optJSONObject(i2));
                long headerId = clubPhotoDTO.getHeaderId();
                if (!treeMap.containsKey(Long.valueOf(headerId))) {
                    treeMap.put(Long.valueOf(clubPhotoDTO.getHeaderId()), Integer.valueOf(0));
                    int d = C2555d.m12809d(headerId);
                    int e = C2555d.m12812e(headerId);
                    try {
                        JSONObject a2 = this.f9338b.a(str, C2555d.m12801b(d, e), C2555d.m12795a(d, e));
                        if (a2 != null && a2.optInt("code") == 0) {
                            d = a2.optInt(C0882j.f2229c);
                            clubPhotoDTO.setHeaderCount(d);
                            treeMap.put(Long.valueOf(clubPhotoDTO.getHeaderId()), Integer.valueOf(d));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                arrayList.add(clubPhotoDTO);
            }
            if (!z) {
                return arrayList;
            }
            m10588b(arrayList, "clubphoto_cache", str);
            return arrayList;
        } catch (Throwable e3) {
            throw new BusinessException(e3);
        }
    }

    /* renamed from: c */
    public boolean m10594c(String str, String str2) throws BusinessException {
        try {
            JSONObject a = this.f9338b.a(str, str2);
            if (a == null) {
                return false;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a.optInt("code") == 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: c */
    public List<Integer> m10592c(String str) {
        try {
            JSONObject a = this.f9338b.a(str);
            if (a == null) {
                return null;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a.optInt("code") != 0) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONObject(C0882j.f2229c).optJSONArray("success");
            List<Integer> arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(Integer.valueOf(optJSONArray.optInt(i)));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public boolean m10593c(int i) {
        try {
            JSONObject a = this.f9338b.a(String.valueOf(i));
            if (a == null) {
                return false;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a.optInt("code") != 0) {
                return false;
            }
            JSONArray optJSONArray = a.optJSONObject(C0882j.f2229c).optJSONArray("success");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                if (i == optJSONArray.getInt(i2)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public boolean m10590b(int i, int i2) {
        try {
            JSONObject b = this.f9338b.b(i, i2);
            if (b == null) {
                return false;
            }
            if (b.optInt("code") == 0) {
                return b.optBoolean(C0882j.f2229c);
            }
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                return false;
            }
            Toasts.showOnUiThread(this.f9339c, optString);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public ClubFeedComment m10584b(int i, String str, int i2) {
        try {
            JSONObject b = this.f9338b.b(i, str, i2);
            if (b == null) {
                return null;
            }
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (b.optInt("code") != 0) {
                return null;
            }
            JSONObject optJSONObject = b.optJSONObject(C0882j.f2229c);
            if (C2563k.m12869a(optJSONObject)) {
                return null;
            }
            return new ClubFeedComment(optJSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public List<ClubFeedComment> m10591c(int i, int i2, int i3) {
        try {
            JSONObject c = this.f9338b.c(i, i2, i3);
            if (c == null) {
                return null;
            }
            String optString = c.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (c.optInt("code") != 0) {
                return null;
            }
            JSONArray optJSONArray = c.optJSONArray(C0882j.f2229c);
            List<ClubFeedComment> arrayList = new ArrayList();
            for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                arrayList.add(new ClubFeedComment(optJSONArray.optJSONObject(i4)));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public List<ClubUser> m10595d(int i, int i2, int i3) throws BusinessException {
        List<ClubUser> list = null;
        try {
            JSONObject d = this.f9338b.d(i, i2, i3);
            if (!C2563k.m12869a(d)) {
                String optString = d.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9339c, optString);
                }
                if (d.optInt("code") == 0) {
                    JSONArray optJSONArray = d.optJSONArray(C0882j.f2229c);
                    if (!C2563k.m12868a(optJSONArray)) {
                        list = new ArrayList();
                        for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                            list.add(new ClubUser(optJSONArray.optJSONObject(i4)));
                        }
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<C2066e> m10578a(boolean z, Long l, int i) throws BusinessException {
        int i2 = 0;
        List<C2066e> list = null;
        if (!z) {
            l = null;
        }
        try {
            JSONObject a = this.f9338b.a(z ? 1 : 0, l, i);
            if (a != null) {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9339c, optString);
                }
                if (a.optInt("code") == 0) {
                    JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        list = new ArrayList();
                        while (i2 < optJSONArray.length()) {
                            list.add(new C2066e(optJSONArray.getJSONObject(i2)));
                            i2++;
                        }
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m10581a() throws BusinessException {
        try {
            JSONObject a = this.f9338b.a();
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9339c, optString);
            }
            if (a.optInt("code") == 0) {
                return a.optBoolean(C0882j.f2229c);
            }
            return false;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }
}

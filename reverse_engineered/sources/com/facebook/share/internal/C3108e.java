package com.facebook.share.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import com.alipay.sdk.packet.C0861d;
import com.facebook.FacebookException;
import com.facebook.internal.C3028n;
import com.facebook.internal.C3028n.C3027a;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3048s.C3046d;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.C2992a;
import com.facebook.share.internal.C3097c.C3096a;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ShareInternalUtility */
/* renamed from: com.facebook.share.internal.e */
public final class C3108e {

    /* compiled from: ShareInternalUtility */
    /* renamed from: com.facebook.share.internal.e$3 */
    static class C31043 implements C3046d<C3027a, String> {
        C31043() {
        }

        /* renamed from: a */
        public String m15106a(C3027a c3027a) {
            return c3027a.m14636a();
        }
    }

    /* compiled from: ShareInternalUtility */
    /* renamed from: com.facebook.share.internal.e$6 */
    static class C31076 implements C3096a {
        C31076() {
        }

        /* renamed from: a */
        public JSONObject mo3723a(SharePhoto sharePhoto) {
            Uri d = sharePhoto.m15204d();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", d.toString());
                return jSONObject;
            } catch (Throwable e) {
                throw new FacebookException("Unable to attach images", e);
            }
        }
    }

    /* renamed from: a */
    public static void m15120a(final int i) {
        CallbackManagerImpl.m14522a(i, new C2992a() {
        });
    }

    /* renamed from: a */
    public static List<String> m15115a(SharePhotoContent sharePhotoContent, final UUID uuid) {
        if (sharePhotoContent != null) {
            List a = sharePhotoContent.m15212a();
            if (a != null) {
                List a2 = C3048s.m14742a(a, new C3046d<SharePhoto, C3027a>() {
                    /* renamed from: a */
                    public C3027a m15103a(SharePhoto sharePhoto) {
                        return C3108e.m15121b(uuid, sharePhoto);
                    }
                });
                List<String> a3 = C3048s.m14742a(a2, new C31043());
                C3028n.m14645a(a2);
                return a3;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m15113a(ShareVideoContent shareVideoContent, UUID uuid) {
        if (shareVideoContent == null || shareVideoContent.m15228d() == null) {
            return null;
        }
        C3027a a = C3028n.m14638a(uuid, shareVideoContent.m15228d().m15222c());
        Collection arrayList = new ArrayList(1);
        arrayList.add(a);
        C3028n.m14645a(arrayList);
        return a.m14636a();
    }

    /* renamed from: a */
    public static List<Bundle> m15114a(ShareMediaContent shareMediaContent, final UUID uuid) {
        if (shareMediaContent != null) {
            List a = shareMediaContent.m15162a();
            if (a != null) {
                final Collection arrayList = new ArrayList();
                List<Bundle> a2 = C3048s.m14742a(a, new C3046d<ShareMedia, Bundle>() {
                    /* renamed from: a */
                    public Bundle m15107a(ShareMedia shareMedia) {
                        C3027a a = C3108e.m15121b(uuid, shareMedia);
                        arrayList.add(a);
                        Bundle bundle = new Bundle();
                        bundle.putString("type", shareMedia.b().name());
                        bundle.putString("uri", a.m14636a());
                        return bundle;
                    }
                });
                C3028n.m14645a(arrayList);
                return a2;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static JSONObject m15118a(final UUID uuid, ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        ShareOpenGraphAction a = shareOpenGraphContent.m15180a();
        final Collection arrayList = new ArrayList();
        JSONObject a2 = C3097c.m15054a(a, new C3096a() {
            /* renamed from: a */
            public JSONObject mo3723a(SharePhoto sharePhoto) {
                C3027a a = C3108e.m15121b(uuid, sharePhoto);
                if (a == null) {
                    return null;
                }
                arrayList.add(a);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", a.m14636a());
                    if (!sharePhoto.m15205e()) {
                        return jSONObject;
                    }
                    jSONObject.put("user_generated", true);
                    return jSONObject;
                } catch (Throwable e) {
                    throw new FacebookException("Unable to attach images", e);
                }
            }
        });
        C3028n.m14645a(arrayList);
        if (shareOpenGraphContent.m15028j() != null && C3048s.m14761a(a2.optString(GeocodingCriteria.TYPE_PLACE))) {
            a2.put(GeocodingCriteria.TYPE_PLACE, shareOpenGraphContent.m15028j());
        }
        if (shareOpenGraphContent.m15027i() != null) {
            JSONArray optJSONArray = a2.optJSONArray("tags");
            if (optJSONArray == null) {
                arrayList = new HashSet();
            } else {
                Object b = C3048s.m14768b(optJSONArray);
            }
            for (String add : shareOpenGraphContent.m15027i()) {
                arrayList.add(add);
            }
            a2.put("tags", new ArrayList(arrayList));
        }
        return a2;
    }

    /* renamed from: a */
    public static JSONObject m15117a(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return C3097c.m15054a(shareOpenGraphContent.m15180a(), new C31076());
    }

    /* renamed from: a */
    public static JSONArray m15116a(JSONArray jSONArray, boolean z) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = C3108e.m15116a((JSONArray) obj, z);
            } else if (obj instanceof JSONObject) {
                obj = C3108e.m15119a((JSONObject) obj, z);
            }
            jSONArray2.put(obj);
        }
        return jSONArray2;
    }

    /* renamed from: a */
    public static JSONObject m15119a(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONArray names = jSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                Object a;
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    a = C3108e.m15119a((JSONObject) obj, true);
                } else if (obj instanceof JSONArray) {
                    JSONArray a2 = C3108e.m15116a((JSONArray) obj, true);
                } else {
                    a = obj;
                }
                Pair a3 = C3108e.m15111a(string);
                String str = (String) a3.first;
                String str2 = (String) a3.second;
                if (z) {
                    if (str != null && str.equals("fbsdk")) {
                        jSONObject2.put(string, a);
                    } else if (str == null || str.equals("og")) {
                        jSONObject2.put(str2, a);
                    } else {
                        jSONObject3.put(str2, a);
                    }
                } else if (str == null || !str.equals("fb")) {
                    jSONObject2.put(str2, a);
                } else {
                    jSONObject2.put(string, a);
                }
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put(C0861d.f2139k, jSONObject3);
            }
            return jSONObject2;
        } catch (JSONException e) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    /* renamed from: a */
    public static Pair<String, String> m15111a(String str) {
        Object obj = null;
        int indexOf = str.indexOf(58);
        if (indexOf != -1 && str.length() > indexOf + 1) {
            obj = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        }
        return new Pair(obj, str);
    }

    /* renamed from: b */
    private static C3027a m15121b(UUID uuid, ShareMedia shareMedia) {
        Bitmap c;
        Uri d;
        if (shareMedia instanceof SharePhoto) {
            SharePhoto sharePhoto = (SharePhoto) shareMedia;
            c = sharePhoto.m15203c();
            d = sharePhoto.m15204d();
        } else if (shareMedia instanceof ShareVideo) {
            d = ((ShareVideo) shareMedia).m15222c();
            c = null;
        } else {
            d = null;
            c = null;
        }
        if (c != null) {
            return C3028n.m14637a(uuid, c);
        }
        if (d != null) {
            return C3028n.m14638a(uuid, d);
        }
        return null;
    }
}

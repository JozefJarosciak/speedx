package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.C3048s;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imlib.statistics.UserData;
import org.json.JSONObject;

/* compiled from: WebDialogParameters */
/* renamed from: com.facebook.share.internal.f */
public class C3109f {
    /* renamed from: a */
    public static Bundle m15124a(ShareLinkContent shareLinkContent) {
        Bundle a = C3109f.m15123a((ShareContent) shareLinkContent);
        C3048s.m14747a(a, "href", shareLinkContent.m15026h());
        C3048s.m14748a(a, "quote", shareLinkContent.m15159d());
        return a;
    }

    /* renamed from: a */
    public static Bundle m15125a(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle a = C3109f.m15123a((ShareContent) shareOpenGraphContent);
        C3048s.m14748a(a, "action_type", shareOpenGraphContent.m15180a().m15177a());
        try {
            JSONObject a2 = C3108e.m15119a(C3108e.m15117a(shareOpenGraphContent), false);
            if (a2 != null) {
                C3048s.m14748a(a, "action_properties", a2.toString());
            }
            return a;
        } catch (Throwable e) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", e);
        }
    }

    /* renamed from: a */
    public static Bundle m15123a(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        ShareHashtag l = shareContent.m15030l();
        if (l != null) {
            C3048s.m14748a(bundle, "hashtag", l.m15146a());
        }
        return bundle;
    }

    /* renamed from: b */
    public static Bundle m15126b(ShareLinkContent shareLinkContent) {
        Bundle bundle = new Bundle();
        C3048s.m14748a(bundle, "name", shareLinkContent.m15157b());
        C3048s.m14748a(bundle, "description", shareLinkContent.m15156a());
        C3048s.m14748a(bundle, "link", C3048s.m14733a(shareLinkContent.m15026h()));
        C3048s.m14748a(bundle, UserData.PICTURE_KEY, C3048s.m14733a(shareLinkContent.m15158c()));
        C3048s.m14748a(bundle, "quote", shareLinkContent.m15159d());
        if (shareLinkContent.m15030l() != null) {
            C3048s.m14748a(bundle, "hashtag", shareLinkContent.m15030l().m15146a());
        }
        return bundle;
    }

    /* renamed from: a */
    public static Bundle m15122a(ShareFeedContent shareFeedContent) {
        Bundle bundle = new Bundle();
        C3048s.m14748a(bundle, "to", shareFeedContent.m15031a());
        C3048s.m14748a(bundle, "link", shareFeedContent.m15032b());
        C3048s.m14748a(bundle, UserData.PICTURE_KEY, shareFeedContent.m15036f());
        C3048s.m14748a(bundle, MapboxEvent.ATTRIBUTE_SOURCE, shareFeedContent.m15037g());
        C3048s.m14748a(bundle, "name", shareFeedContent.m15033c());
        C3048s.m14748a(bundle, "caption", shareFeedContent.m15034d());
        C3048s.m14748a(bundle, "description", shareFeedContent.m15035e());
        return bundle;
    }
}

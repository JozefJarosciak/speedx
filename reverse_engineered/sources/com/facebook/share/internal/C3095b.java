package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeDialogParameters */
/* renamed from: com.facebook.share.internal.b */
public class C3095b {
    /* renamed from: a */
    public static Bundle m15050a(UUID uuid, ShareContent shareContent, boolean z) {
        C3049t.m14790a((Object) shareContent, "shareContent");
        C3049t.m14790a((Object) uuid, "callId");
        if (shareContent instanceof ShareLinkContent) {
            return C3095b.m15045a((ShareLinkContent) shareContent, z);
        }
        if (shareContent instanceof SharePhotoContent) {
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            return C3095b.m15048a(sharePhotoContent, C3108e.m15115a(sharePhotoContent, uuid), z);
        } else if (shareContent instanceof ShareVideoContent) {
            ShareVideoContent shareVideoContent = (ShareVideoContent) shareContent;
            return C3095b.m15049a(shareVideoContent, C3108e.m15113a(shareVideoContent, uuid), z);
        } else if (shareContent instanceof ShareOpenGraphContent) {
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                return C3095b.m15047a(shareOpenGraphContent, C3108e.m15119a(C3108e.m15118a(uuid, shareOpenGraphContent), false), z);
            } catch (JSONException e) {
                throw new FacebookException("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + e.getMessage());
            }
        } else if (!(shareContent instanceof ShareMediaContent)) {
            return null;
        } else {
            ShareMediaContent shareMediaContent = (ShareMediaContent) shareContent;
            return C3095b.m15046a(shareMediaContent, C3108e.m15114a(shareMediaContent, uuid), z);
        }
    }

    /* renamed from: a */
    private static Bundle m15045a(ShareLinkContent shareLinkContent, boolean z) {
        Bundle a = C3095b.m15044a((ShareContent) shareLinkContent, z);
        C3048s.m14748a(a, "TITLE", shareLinkContent.m15157b());
        C3048s.m14748a(a, "DESCRIPTION", shareLinkContent.m15156a());
        C3048s.m14747a(a, "IMAGE", shareLinkContent.m15158c());
        C3048s.m14748a(a, "QUOTE", shareLinkContent.m15159d());
        return a;
    }

    /* renamed from: a */
    private static Bundle m15048a(SharePhotoContent sharePhotoContent, List<String> list, boolean z) {
        Bundle a = C3095b.m15044a((ShareContent) sharePhotoContent, z);
        a.putStringArrayList("PHOTOS", new ArrayList(list));
        return a;
    }

    /* renamed from: a */
    private static Bundle m15049a(ShareVideoContent shareVideoContent, String str, boolean z) {
        Bundle a = C3095b.m15044a((ShareContent) shareVideoContent, z);
        C3048s.m14748a(a, "TITLE", shareVideoContent.m15226b());
        C3048s.m14748a(a, "DESCRIPTION", shareVideoContent.m15225a());
        C3048s.m14748a(a, "VIDEO", str);
        return a;
    }

    /* renamed from: a */
    private static Bundle m15046a(ShareMediaContent shareMediaContent, List<Bundle> list, boolean z) {
        Bundle a = C3095b.m15044a((ShareContent) shareMediaContent, z);
        a.putParcelableArrayList("MEDIA", new ArrayList(list));
        return a;
    }

    /* renamed from: a */
    private static Bundle m15047a(ShareOpenGraphContent shareOpenGraphContent, JSONObject jSONObject, boolean z) {
        Bundle a = C3095b.m15044a((ShareContent) shareOpenGraphContent, z);
        C3048s.m14748a(a, "PREVIEW_PROPERTY_NAME", (String) C3108e.m15111a(shareOpenGraphContent.m15181b()).second);
        C3048s.m14748a(a, "ACTION_TYPE", shareOpenGraphContent.m15180a().m15177a());
        C3048s.m14748a(a, "ACTION", jSONObject.toString());
        return a;
    }

    /* renamed from: a */
    private static Bundle m15044a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        C3048s.m14747a(bundle, "LINK", shareContent.m15026h());
        C3048s.m14748a(bundle, "PLACE", shareContent.m15028j());
        C3048s.m14748a(bundle, "REF", shareContent.m15029k());
        bundle.putBoolean("DATA_FAILURES_FATAL", z);
        Collection i = shareContent.m15027i();
        if (!C3048s.m14762a(i)) {
            bundle.putStringArrayList("FRIENDS", new ArrayList(i));
        }
        ShareHashtag l = shareContent.m15030l();
        if (l != null) {
            C3048s.m14748a(bundle, "HASHTAG", l.m15146a());
        }
        return bundle;
    }
}

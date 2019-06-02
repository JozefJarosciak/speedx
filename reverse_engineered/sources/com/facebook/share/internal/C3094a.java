package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LegacyNativeDialogParameters */
/* renamed from: com.facebook.share.internal.a */
public class C3094a {
    /* renamed from: a */
    public static Bundle m15043a(UUID uuid, ShareContent shareContent, boolean z) {
        C3049t.m14790a((Object) shareContent, "shareContent");
        C3049t.m14790a((Object) uuid, "callId");
        if (shareContent instanceof ShareLinkContent) {
            return C3094a.m15039a((ShareLinkContent) shareContent, z);
        }
        if (shareContent instanceof SharePhotoContent) {
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            return C3094a.m15041a(sharePhotoContent, C3108e.m15115a(sharePhotoContent, uuid), z);
        } else if (shareContent instanceof ShareVideoContent) {
            return C3094a.m15042a((ShareVideoContent) shareContent, z);
        } else {
            if (!(shareContent instanceof ShareOpenGraphContent)) {
                return null;
            }
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                return C3094a.m15040a(shareOpenGraphContent, C3108e.m15118a(uuid, shareOpenGraphContent), z);
            } catch (JSONException e) {
                throw new FacebookException("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + e.getMessage());
            }
        }
    }

    /* renamed from: a */
    private static Bundle m15039a(ShareLinkContent shareLinkContent, boolean z) {
        Bundle a = C3094a.m15038a((ShareContent) shareLinkContent, z);
        C3048s.m14748a(a, "com.facebook.platform.extra.TITLE", shareLinkContent.m15157b());
        C3048s.m14748a(a, "com.facebook.platform.extra.DESCRIPTION", shareLinkContent.m15156a());
        C3048s.m14747a(a, "com.facebook.platform.extra.IMAGE", shareLinkContent.m15158c());
        return a;
    }

    /* renamed from: a */
    private static Bundle m15041a(SharePhotoContent sharePhotoContent, List<String> list, boolean z) {
        Bundle a = C3094a.m15038a((ShareContent) sharePhotoContent, z);
        a.putStringArrayList("com.facebook.platform.extra.PHOTOS", new ArrayList(list));
        return a;
    }

    /* renamed from: a */
    private static Bundle m15042a(ShareVideoContent shareVideoContent, boolean z) {
        return null;
    }

    /* renamed from: a */
    private static Bundle m15040a(ShareOpenGraphContent shareOpenGraphContent, JSONObject jSONObject, boolean z) {
        Bundle a = C3094a.m15038a((ShareContent) shareOpenGraphContent, z);
        C3048s.m14748a(a, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", shareOpenGraphContent.m15181b());
        C3048s.m14748a(a, "com.facebook.platform.extra.ACTION_TYPE", shareOpenGraphContent.m15180a().m15177a());
        C3048s.m14748a(a, "com.facebook.platform.extra.ACTION", jSONObject.toString());
        return a;
    }

    /* renamed from: a */
    private static Bundle m15038a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        C3048s.m14747a(bundle, "com.facebook.platform.extra.LINK", shareContent.m15026h());
        C3048s.m14748a(bundle, "com.facebook.platform.extra.PLACE", shareContent.m15028j());
        C3048s.m14748a(bundle, "com.facebook.platform.extra.REF", shareContent.m15029k());
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", z);
        Collection i = shareContent.m15027i();
        if (!C3048s.m14762a(i)) {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", new ArrayList(i));
        }
        return bundle;
    }
}

package com.facebook.share.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.C1472c;
import com.facebook.FacebookException;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import java.util.List;
import java.util.Locale;

/* compiled from: ShareContentValidation */
/* renamed from: com.facebook.share.internal.d */
public class C3101d {
    /* renamed from: a */
    private static C3099a f13778a;
    /* renamed from: b */
    private static C3099a f13779b;

    /* compiled from: ShareContentValidation */
    /* renamed from: com.facebook.share.internal.d$a */
    private static class C3099a {
        /* renamed from: a */
        private boolean f13777a;

        private C3099a() {
            this.f13777a = false;
        }

        /* renamed from: a */
        public void m15056a(ShareLinkContent shareLinkContent) {
            C3101d.m15090b(shareLinkContent, this);
        }

        /* renamed from: a */
        public void mo3720a(SharePhotoContent sharePhotoContent) {
            C3101d.m15097b(sharePhotoContent, this);
        }

        /* renamed from: a */
        public void mo3721a(ShareVideoContent shareVideoContent) {
            C3101d.m15099b(shareVideoContent, this);
        }

        /* renamed from: a */
        public void mo3718a(ShareMediaContent shareMediaContent) {
            C3101d.m15091b(shareMediaContent, this);
        }

        /* renamed from: a */
        public void m15060a(ShareOpenGraphContent shareOpenGraphContent) {
            this.f13777a = true;
            C3101d.m15093b(shareOpenGraphContent, this);
        }

        /* renamed from: a */
        public void m15059a(ShareOpenGraphAction shareOpenGraphAction) {
            C3101d.m15092b(shareOpenGraphAction, this);
        }

        /* renamed from: a */
        public void m15061a(ShareOpenGraphObject shareOpenGraphObject) {
            C3101d.m15094b(shareOpenGraphObject, this);
        }

        /* renamed from: a */
        public void m15062a(ShareOpenGraphValueContainer shareOpenGraphValueContainer, boolean z) {
            C3101d.m15095b(shareOpenGraphValueContainer, this, z);
        }

        /* renamed from: a */
        public void mo3719a(SharePhoto sharePhoto) {
            C3101d.m15101d(sharePhoto, this);
        }

        /* renamed from: a */
        public void m15065a(ShareVideo shareVideo) {
            C3101d.m15098b(shareVideo, this);
        }

        /* renamed from: a */
        public void m15057a(ShareMedia shareMedia) {
            C3101d.m15076a(shareMedia, this);
        }

        /* renamed from: a */
        public boolean m15067a() {
            return this.f13777a;
        }
    }

    /* compiled from: ShareContentValidation */
    /* renamed from: com.facebook.share.internal.d$b */
    private static class C3100b extends C3099a {
        private C3100b() {
            super();
        }

        /* renamed from: a */
        public void mo3720a(SharePhotoContent sharePhotoContent) {
            throw new FacebookException("Cannot share SharePhotoContent via web sharing dialogs");
        }

        /* renamed from: a */
        public void mo3721a(ShareVideoContent shareVideoContent) {
            throw new FacebookException("Cannot share ShareVideoContent via web sharing dialogs");
        }

        /* renamed from: a */
        public void mo3718a(ShareMediaContent shareMediaContent) {
            throw new FacebookException("Cannot share ShareMediaContent via web sharing dialogs");
        }

        /* renamed from: a */
        public void mo3719a(SharePhoto sharePhoto) {
            C3101d.m15102e(sharePhoto, this);
        }
    }

    /* renamed from: a */
    public static void m15073a(ShareContent shareContent) {
        C3101d.m15074a(shareContent, C3101d.m15072a());
    }

    /* renamed from: b */
    public static void m15089b(ShareContent shareContent) {
        C3101d.m15074a(shareContent, C3101d.m15088b());
    }

    /* renamed from: a */
    private static C3099a m15072a() {
        if (f13779b == null) {
            f13779b = new C3099a();
        }
        return f13779b;
    }

    /* renamed from: b */
    private static C3099a m15088b() {
        if (f13778a == null) {
            f13778a = new C3100b();
        }
        return f13778a;
    }

    /* renamed from: a */
    private static void m15074a(ShareContent shareContent, C3099a c3099a) throws FacebookException {
        if (shareContent == null) {
            throw new FacebookException("Must provide non-null content to share");
        } else if (shareContent instanceof ShareLinkContent) {
            c3099a.m15056a((ShareLinkContent) shareContent);
        } else if (shareContent instanceof SharePhotoContent) {
            c3099a.mo3720a((SharePhotoContent) shareContent);
        } else if (shareContent instanceof ShareVideoContent) {
            c3099a.mo3721a((ShareVideoContent) shareContent);
        } else if (shareContent instanceof ShareOpenGraphContent) {
            c3099a.m15060a((ShareOpenGraphContent) shareContent);
        } else if (shareContent instanceof ShareMediaContent) {
            c3099a.mo3718a((ShareMediaContent) shareContent);
        }
    }

    /* renamed from: b */
    private static void m15090b(ShareLinkContent shareLinkContent, C3099a c3099a) {
        Uri c = shareLinkContent.m15158c();
        if (c != null && !C3048s.m14771b(c)) {
            throw new FacebookException("Image Url must be an http:// or https:// url");
        }
    }

    /* renamed from: b */
    private static void m15097b(SharePhotoContent sharePhotoContent, C3099a c3099a) {
        List<SharePhoto> a = sharePhotoContent.m15212a();
        if (a == null || a.isEmpty()) {
            throw new FacebookException("Must specify at least one Photo in SharePhotoContent.");
        } else if (a.size() > 6) {
            throw new FacebookException(String.format(Locale.ROOT, "Cannot add more than %d photos.", new Object[]{Integer.valueOf(6)}));
        } else {
            for (SharePhoto a2 : a) {
                c3099a.mo3719a(a2);
            }
        }
    }

    /* renamed from: c */
    private static void m15100c(SharePhoto sharePhoto, C3099a c3099a) {
        if (sharePhoto == null) {
            throw new FacebookException("Cannot share a null SharePhoto");
        }
        Bitmap c = sharePhoto.m15203c();
        Uri d = sharePhoto.m15204d();
        if (c != null) {
            return;
        }
        if (d == null) {
            throw new FacebookException("SharePhoto does not have a Bitmap or ImageUrl specified");
        } else if (C3048s.m14771b(d) && !c3099a.m15067a()) {
            throw new FacebookException("Cannot set the ImageUrl of a SharePhoto to the Uri of an image on the web when sharing SharePhotoContent");
        }
    }

    /* renamed from: d */
    private static void m15101d(SharePhoto sharePhoto, C3099a c3099a) {
        C3101d.m15100c(sharePhoto, c3099a);
        if (sharePhoto.m15203c() != null || !C3048s.m14771b(sharePhoto.m15204d())) {
            C3049t.m14800d(C1472c.f());
        }
    }

    /* renamed from: e */
    private static void m15102e(SharePhoto sharePhoto, C3099a c3099a) {
        if (sharePhoto == null) {
            throw new FacebookException("Cannot share a null SharePhoto");
        }
        Uri d = sharePhoto.m15204d();
        if (d == null || !C3048s.m14771b(d)) {
            throw new FacebookException("SharePhoto must have a non-null imageUrl set to the Uri of an image on the web");
        }
    }

    /* renamed from: b */
    private static void m15099b(ShareVideoContent shareVideoContent, C3099a c3099a) {
        c3099a.m15065a(shareVideoContent.m15228d());
        SharePhoto c = shareVideoContent.m15227c();
        if (c != null) {
            c3099a.mo3719a(c);
        }
    }

    /* renamed from: b */
    private static void m15098b(ShareVideo shareVideo, C3099a c3099a) {
        if (shareVideo == null) {
            throw new FacebookException("Cannot share a null ShareVideo");
        }
        Uri c = shareVideo.m15222c();
        if (c == null) {
            throw new FacebookException("ShareVideo does not have a LocalUrl specified");
        } else if (!C3048s.m14775c(c) && !C3048s.m14779d(c)) {
            throw new FacebookException("ShareVideo must reference a video that is on the device");
        }
    }

    /* renamed from: b */
    private static void m15091b(ShareMediaContent shareMediaContent, C3099a c3099a) {
        List<ShareMedia> a = shareMediaContent.m15162a();
        if (a == null || a.isEmpty()) {
            throw new FacebookException("Must specify at least one medium in ShareMediaContent.");
        } else if (a.size() > 6) {
            throw new FacebookException(String.format(Locale.ROOT, "Cannot add more than %d media.", new Object[]{Integer.valueOf(6)}));
        } else {
            for (ShareMedia a2 : a) {
                c3099a.m15057a(a2);
            }
        }
    }

    /* renamed from: a */
    public static void m15076a(ShareMedia shareMedia, C3099a c3099a) {
        if (shareMedia instanceof SharePhoto) {
            c3099a.mo3719a((SharePhoto) shareMedia);
        } else if (shareMedia instanceof ShareVideo) {
            c3099a.m15065a((ShareVideo) shareMedia);
        } else {
            throw new FacebookException(String.format(Locale.ROOT, "Invalid media type: %s", new Object[]{shareMedia.getClass().getSimpleName()}));
        }
    }

    /* renamed from: b */
    private static void m15093b(ShareOpenGraphContent shareOpenGraphContent, C3099a c3099a) {
        c3099a.m15059a(shareOpenGraphContent.m15180a());
        String b = shareOpenGraphContent.m15181b();
        if (C3048s.m14761a(b)) {
            throw new FacebookException("Must specify a previewPropertyName.");
        } else if (shareOpenGraphContent.m15180a().m15173a(b) == null) {
            throw new FacebookException("Property \"" + b + "\" was not found on the action. " + "The name of the preview property must match the name of an " + "action property.");
        }
    }

    /* renamed from: b */
    private static void m15092b(ShareOpenGraphAction shareOpenGraphAction, C3099a c3099a) {
        if (shareOpenGraphAction == null) {
            throw new FacebookException("Must specify a non-null ShareOpenGraphAction");
        } else if (C3048s.m14761a(shareOpenGraphAction.m15177a())) {
            throw new FacebookException("ShareOpenGraphAction must have a non-empty actionType");
        } else {
            c3099a.m15062a(shareOpenGraphAction, false);
        }
    }

    /* renamed from: b */
    private static void m15094b(ShareOpenGraphObject shareOpenGraphObject, C3099a c3099a) {
        if (shareOpenGraphObject == null) {
            throw new FacebookException("Cannot share a null ShareOpenGraphObject");
        }
        c3099a.m15062a(shareOpenGraphObject, true);
    }

    /* renamed from: b */
    private static void m15095b(ShareOpenGraphValueContainer shareOpenGraphValueContainer, C3099a c3099a, boolean z) {
        for (String str : shareOpenGraphValueContainer.m15176c()) {
            C3101d.m15087a(str, z);
            Object a = shareOpenGraphValueContainer.m15173a(str);
            if (a instanceof List) {
                for (Object next : (List) a) {
                    if (next == null) {
                        throw new FacebookException("Cannot put null objects in Lists in ShareOpenGraphObjects and ShareOpenGraphActions");
                    }
                    C3101d.m15086a(next, c3099a);
                }
                continue;
            } else {
                C3101d.m15086a(a, c3099a);
            }
        }
    }

    /* renamed from: a */
    private static void m15087a(String str, boolean z) {
        if (z) {
            String[] split = str.split(":");
            if (split.length < 2) {
                throw new FacebookException("Open Graph keys must be namespaced: %s", str);
            }
            for (String isEmpty : split) {
                if (isEmpty.isEmpty()) {
                    throw new FacebookException("Invalid key found in Open Graph dictionary: %s", str);
                }
            }
        }
    }

    /* renamed from: a */
    private static void m15086a(Object obj, C3099a c3099a) {
        if (obj instanceof ShareOpenGraphObject) {
            c3099a.m15061a((ShareOpenGraphObject) obj);
        } else if (obj instanceof SharePhoto) {
            c3099a.mo3719a((SharePhoto) obj);
        }
    }
}

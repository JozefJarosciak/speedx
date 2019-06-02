package com.facebook.share.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.os.EnvironmentCompat;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.C2996a;
import com.facebook.internal.C3011e;
import com.facebook.internal.C3013f;
import com.facebook.internal.C3013f.C3012a;
import com.facebook.internal.C3015g;
import com.facebook.internal.C3015g.C3014a;
import com.facebook.internal.C3048s;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.share.internal.C3094a;
import com.facebook.share.internal.C3095b;
import com.facebook.share.internal.C3101d;
import com.facebook.share.internal.C3108e;
import com.facebook.share.internal.C3109f;
import com.facebook.share.internal.OpenGraphActionDialogFeature;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.List;

public final class ShareDialog extends C3015g<ShareContent, Object> {
    /* renamed from: b */
    private static final int f13846b = RequestCodeOffset.Share.toRequestCode();
    /* renamed from: c */
    private boolean f13847c = false;
    /* renamed from: d */
    private boolean f13848d = true;

    public enum Mode {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    /* renamed from: com.facebook.share.widget.ShareDialog$a */
    private class C3132a extends C3014a {
        /* renamed from: b */
        final /* synthetic */ ShareDialog f13839b;

        private C3132a(ShareDialog shareDialog) {
            this.f13839b = shareDialog;
            super(shareDialog);
        }

        /* renamed from: a */
        public Object mo3726a() {
            return Mode.FEED;
        }

        /* renamed from: a */
        public boolean m15232a(ShareContent shareContent, boolean z) {
            return (shareContent instanceof ShareLinkContent) || (shareContent instanceof ShareFeedContent);
        }

        /* renamed from: a */
        public C2996a m15229a(ShareContent shareContent) {
            Bundle b;
            this.f13839b.m15249a(this.f13839b.m14581b(), shareContent, Mode.FEED);
            C2996a d = this.f13839b.mo3731d();
            if (shareContent instanceof ShareLinkContent) {
                ShareLinkContent shareLinkContent = (ShareLinkContent) shareContent;
                C3101d.m15089b(shareLinkContent);
                b = C3109f.m15126b(shareLinkContent);
            } else {
                b = C3109f.m15122a((ShareFeedContent) shareContent);
            }
            C3013f.m14568a(d, "feed", b);
            return d;
        }
    }

    /* renamed from: com.facebook.share.widget.ShareDialog$b */
    private class C3134b extends C3014a {
        /* renamed from: b */
        final /* synthetic */ ShareDialog f13844b;

        private C3134b(ShareDialog shareDialog) {
            this.f13844b = shareDialog;
            super(shareDialog);
        }

        /* renamed from: a */
        public Object mo3726a() {
            return Mode.NATIVE;
        }

        /* renamed from: a */
        public boolean m15239a(ShareContent shareContent, boolean z) {
            boolean z2 = true;
            if (shareContent == null) {
                return false;
            }
            int i;
            if (z) {
                i = 1;
            } else {
                if (shareContent.m15030l() != null) {
                    i = C3013f.m14569a(ShareDialogFeature.HASHTAG);
                } else {
                    i = 1;
                }
                if ((shareContent instanceof ShareLinkContent) && !C3048s.m14761a(((ShareLinkContent) shareContent).m15159d())) {
                    i &= C3013f.m14569a(ShareDialogFeature.LINK_SHARE_QUOTES);
                }
            }
            if (i == 0 || !ShareDialog.m15256d(shareContent.getClass())) {
                z2 = false;
            }
            return z2;
        }

        /* renamed from: a */
        public C2996a m15236a(final ShareContent shareContent) {
            this.f13844b.m15249a(this.f13844b.m14581b(), shareContent, Mode.NATIVE);
            C3101d.m15073a(shareContent);
            final C2996a d = this.f13844b.mo3731d();
            final boolean e = this.f13844b.mo3732e();
            C3013f.m14566a(d, new C3012a(this) {
                /* renamed from: d */
                final /* synthetic */ C3134b f13843d;

                /* renamed from: a */
                public Bundle mo3728a() {
                    return C3095b.m15050a(d.m14535c(), shareContent, e);
                }

                /* renamed from: b */
                public Bundle mo3729b() {
                    return C3094a.m15043a(d.m14535c(), shareContent, e);
                }
            }, ShareDialog.m15258f(shareContent.getClass()));
            return d;
        }
    }

    /* renamed from: com.facebook.share.widget.ShareDialog$c */
    private class C3135c extends C3014a {
        /* renamed from: b */
        final /* synthetic */ ShareDialog f13845b;

        private C3135c(ShareDialog shareDialog) {
            this.f13845b = shareDialog;
            super(shareDialog);
        }

        /* renamed from: a */
        public Object mo3726a() {
            return Mode.WEB;
        }

        /* renamed from: a */
        public boolean m15245a(ShareContent shareContent, boolean z) {
            return shareContent != null && ShareDialog.m15257e(shareContent.getClass());
        }

        /* renamed from: a */
        public C2996a m15242a(ShareContent shareContent) {
            Bundle a;
            this.f13845b.m15249a(this.f13845b.m14581b(), shareContent, Mode.WEB);
            C2996a d = this.f13845b.mo3731d();
            C3101d.m15089b(shareContent);
            if (shareContent instanceof ShareLinkContent) {
                a = C3109f.m15124a((ShareLinkContent) shareContent);
            } else {
                a = C3109f.m15125a((ShareOpenGraphContent) shareContent);
            }
            C3013f.m14568a(d, m15241b(shareContent), a);
            return d;
        }

        /* renamed from: b */
        private String m15241b(ShareContent shareContent) {
            if (shareContent instanceof ShareLinkContent) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return "share_open_graph";
            }
            return null;
        }
    }

    /* renamed from: a */
    public static void m15248a(Activity activity, ShareContent shareContent) {
        new ShareDialog(activity).m14579a(shareContent);
    }

    /* renamed from: d */
    private static boolean m15256d(Class<? extends ShareContent> cls) {
        C3011e f = m15258f(cls);
        return f != null && C3013f.m14569a(f);
    }

    /* renamed from: e */
    private static boolean m15257e(Class<? extends ShareContent> cls) {
        return ShareLinkContent.class.isAssignableFrom(cls) || ShareOpenGraphContent.class.isAssignableFrom(cls);
    }

    public ShareDialog(Activity activity) {
        super(activity, f13846b);
        C3108e.m15120a(f13846b);
    }

    /* renamed from: e */
    public boolean mo3732e() {
        return this.f13847c;
    }

    /* renamed from: d */
    protected C2996a mo3731d() {
        return new C2996a(m14578a());
    }

    /* renamed from: c */
    protected List<C3014a> mo3730c() {
        List arrayList = new ArrayList();
        arrayList.add(new C3134b());
        arrayList.add(new C3132a());
        arrayList.add(new C3135c());
        return arrayList;
    }

    /* renamed from: f */
    private static C3011e m15258f(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(cls)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        if (ShareMediaContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.MULTIMEDIA;
        }
        return null;
    }

    /* renamed from: a */
    private void m15249a(Context context, ShareContent shareContent, Mode mode) {
        String str;
        String str2;
        if (this.f13848d) {
            mode = Mode.AUTOMATIC;
        }
        switch (mode) {
            case AUTOMATIC:
                str = "automatic";
                break;
            case WEB:
                str = "web";
                break;
            case NATIVE:
                str = "native";
                break;
            default:
                str = EnvironmentCompat.MEDIA_UNKNOWN;
                break;
        }
        C3011e f = m15258f(shareContent.getClass());
        if (f == ShareDialogFeature.SHARE_DIALOG) {
            str2 = "status";
        } else if (f == ShareDialogFeature.PHOTOS) {
            str2 = "photo";
        } else if (f == ShareDialogFeature.VIDEO) {
            str2 = "video";
        } else if (f == OpenGraphActionDialogFeature.OG_ACTION_DIALOG) {
            str2 = "open_graph";
        } else {
            str2 = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        AppEventsLogger a = AppEventsLogger.m14408a(context);
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", str);
        bundle.putString("fb_share_dialog_content_type", str2);
        a.m14416a("fb_share_dialog_show", null, bundle);
    }
}

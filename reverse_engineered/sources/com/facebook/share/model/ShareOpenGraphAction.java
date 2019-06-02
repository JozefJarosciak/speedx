package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareOpenGraphValueContainer.C3120a;

public final class ShareOpenGraphAction extends ShareOpenGraphValueContainer<ShareOpenGraphAction, C3121a> {
    public static final Creator<ShareOpenGraphAction> CREATOR = new C31191();

    /* renamed from: com.facebook.share.model.ShareOpenGraphAction$1 */
    static class C31191 implements Creator<ShareOpenGraphAction> {
        C31191() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15163a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15164a(i);
        }

        /* renamed from: a */
        public ShareOpenGraphAction m15163a(Parcel parcel) {
            return new ShareOpenGraphAction(parcel);
        }

        /* renamed from: a */
        public ShareOpenGraphAction[] m15164a(int i) {
            return new ShareOpenGraphAction[i];
        }
    }

    /* renamed from: com.facebook.share.model.ShareOpenGraphAction$a */
    public static final class C3121a extends C3120a<ShareOpenGraphAction, C3121a> {
        /* renamed from: a */
        public C3121a m15170a(String str) {
            m15167a("og:type", str);
            return this;
        }

        /* renamed from: a */
        public ShareOpenGraphAction m15171a() {
            return new ShareOpenGraphAction();
        }

        /* renamed from: a */
        public C3121a m15169a(ShareOpenGraphAction shareOpenGraphAction) {
            return shareOpenGraphAction == null ? this : ((C3121a) super.mo3724a((ShareOpenGraphValueContainer) shareOpenGraphAction)).m15170a(shareOpenGraphAction.m15177a());
        }

        /* renamed from: a */
        C3121a m15168a(Parcel parcel) {
            return m15169a((ShareOpenGraphAction) parcel.readParcelable(ShareOpenGraphAction.class.getClassLoader()));
        }
    }

    private ShareOpenGraphAction(C3121a c3121a) {
        super((C3120a) c3121a);
    }

    ShareOpenGraphAction(Parcel parcel) {
        super(parcel);
    }

    @Nullable
    /* renamed from: a */
    public String m15177a() {
        return m15175b("og:type");
    }
}

package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareContent.C3113a;
import com.facebook.share.model.SharePhoto.C3125a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SharePhotoContent extends ShareContent<SharePhotoContent, C3127a> {
    public static final Creator<SharePhotoContent> CREATOR = new C31261();
    /* renamed from: a */
    private final List<SharePhoto> f13831a;

    /* renamed from: com.facebook.share.model.SharePhotoContent$1 */
    static class C31261 implements Creator<SharePhotoContent> {
        C31261() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15207a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15208a(i);
        }

        /* renamed from: a */
        public SharePhotoContent m15207a(Parcel parcel) {
            return new SharePhotoContent(parcel);
        }

        /* renamed from: a */
        public SharePhotoContent[] m15208a(int i) {
            return new SharePhotoContent[i];
        }
    }

    /* renamed from: com.facebook.share.model.SharePhotoContent$a */
    public static class C3127a extends C3113a<SharePhotoContent, C3127a> {
        /* renamed from: a */
        private final List<SharePhoto> f13830a = new ArrayList();

        /* renamed from: a */
        public C3127a m15210a(@Nullable SharePhoto sharePhoto) {
            if (sharePhoto != null) {
                this.f13830a.add(new C3125a().m15196a(sharePhoto).m15201c());
            }
            return this;
        }

        /* renamed from: a */
        public SharePhotoContent m15211a() {
            return new SharePhotoContent();
        }
    }

    private SharePhotoContent(C3127a c3127a) {
        super((C3113a) c3127a);
        this.f13831a = Collections.unmodifiableList(c3127a.f13830a);
    }

    SharePhotoContent(Parcel parcel) {
        super(parcel);
        this.f13831a = Collections.unmodifiableList(C3125a.m15189c(parcel));
    }

    @Nullable
    /* renamed from: a */
    public List<SharePhoto> m15212a() {
        return this.f13831a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        C3125a.m15187a(parcel, i, this.f13831a);
    }
}

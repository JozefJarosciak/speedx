package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareMedia.C1473a;
import java.util.ArrayList;
import java.util.List;

public final class SharePhoto extends ShareMedia {
    public static final Creator<SharePhoto> CREATOR = new C31241();
    /* renamed from: a */
    private final Bitmap f13826a;
    /* renamed from: b */
    private final Uri f13827b;
    /* renamed from: c */
    private final boolean f13828c;
    /* renamed from: d */
    private final String f13829d;

    /* renamed from: com.facebook.share.model.SharePhoto$1 */
    static class C31241 implements Creator<SharePhoto> {
        C31241() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15184a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15185a(i);
        }

        /* renamed from: a */
        public SharePhoto m15184a(Parcel parcel) {
            return new SharePhoto(parcel);
        }

        /* renamed from: a */
        public SharePhoto[] m15185a(int i) {
            return new SharePhoto[i];
        }
    }

    /* renamed from: com.facebook.share.model.SharePhoto$a */
    public static final class C3125a extends C1473a<SharePhoto, C3125a> {
        /* renamed from: a */
        private Bitmap f13822a;
        /* renamed from: b */
        private Uri f13823b;
        /* renamed from: c */
        private boolean f13824c;
        /* renamed from: d */
        private String f13825d;

        /* renamed from: a */
        public C3125a m15194a(@Nullable Bitmap bitmap) {
            this.f13822a = bitmap;
            return this;
        }

        /* renamed from: a */
        public C3125a m15195a(@Nullable Uri uri) {
            this.f13823b = uri;
            return this;
        }

        /* renamed from: a */
        public C3125a m15198a(boolean z) {
            this.f13824c = z;
            return this;
        }

        /* renamed from: a */
        public C3125a m15197a(@Nullable String str) {
            this.f13825d = str;
            return this;
        }

        /* renamed from: a */
        Uri m15192a() {
            return this.f13823b;
        }

        /* renamed from: b */
        Bitmap m15199b() {
            return this.f13822a;
        }

        /* renamed from: c */
        public SharePhoto m15201c() {
            return new SharePhoto();
        }

        /* renamed from: a */
        public C3125a m15196a(SharePhoto sharePhoto) {
            return sharePhoto == null ? this : ((C3125a) super.a(sharePhoto)).m15194a(sharePhoto.m15203c()).m15195a(sharePhoto.m15204d()).m15198a(sharePhoto.m15205e()).m15197a(sharePhoto.m15206f());
        }

        /* renamed from: b */
        C3125a m15200b(Parcel parcel) {
            return m15196a((SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }

        /* renamed from: a */
        static void m15187a(Parcel parcel, int i, List<SharePhoto> list) {
            Parcelable[] parcelableArr = new ShareMedia[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                parcelableArr[i2] = (ShareMedia) list.get(i2);
            }
            parcel.writeParcelableArray(parcelableArr, i);
        }

        /* renamed from: c */
        static List<SharePhoto> m15189c(Parcel parcel) {
            List<ShareMedia> a = C3125a.a(parcel);
            List<SharePhoto> arrayList = new ArrayList();
            for (ShareMedia shareMedia : a) {
                if (shareMedia instanceof SharePhoto) {
                    arrayList.add((SharePhoto) shareMedia);
                }
            }
            return arrayList;
        }
    }

    private SharePhoto(C3125a c3125a) {
        super(c3125a);
        this.f13826a = c3125a.f13822a;
        this.f13827b = c3125a.f13823b;
        this.f13828c = c3125a.f13824c;
        this.f13829d = c3125a.f13825d;
    }

    SharePhoto(Parcel parcel) {
        super(parcel);
        this.f13826a = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.f13827b = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.f13828c = parcel.readByte() != (byte) 0;
        this.f13829d = parcel.readString();
    }

    @Nullable
    /* renamed from: c */
    public Bitmap m15203c() {
        return this.f13826a;
    }

    @Nullable
    /* renamed from: d */
    public Uri m15204d() {
        return this.f13827b;
    }

    /* renamed from: e */
    public boolean m15205e() {
        return this.f13828c;
    }

    /* renamed from: f */
    public String m15206f() {
        return this.f13829d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f13826a, 0);
        parcel.writeParcelable(this.f13827b, 0);
        if (this.f13828c) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.f13829d);
    }

    /* renamed from: b */
    public ShareMedia$Type m15202b() {
        return ShareMedia$Type.PHOTO;
    }
}

package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareContent;

public class ShareFeedContent extends ShareContent<ShareFeedContent, Object> {
    public static final Creator<ShareFeedContent> CREATOR = new C30931();
    /* renamed from: a */
    private final String f13770a;
    /* renamed from: b */
    private final String f13771b;
    /* renamed from: c */
    private final String f13772c;
    /* renamed from: d */
    private final String f13773d;
    /* renamed from: e */
    private final String f13774e;
    /* renamed from: f */
    private final String f13775f;
    /* renamed from: g */
    private final String f13776g;

    /* renamed from: com.facebook.share.internal.ShareFeedContent$1 */
    static class C30931 implements Creator<ShareFeedContent> {
        C30931() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15023a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15024a(i);
        }

        /* renamed from: a */
        public ShareFeedContent m15023a(Parcel parcel) {
            return new ShareFeedContent(parcel);
        }

        /* renamed from: a */
        public ShareFeedContent[] m15024a(int i) {
            return new ShareFeedContent[i];
        }
    }

    ShareFeedContent(Parcel parcel) {
        super(parcel);
        this.f13770a = parcel.readString();
        this.f13771b = parcel.readString();
        this.f13772c = parcel.readString();
        this.f13773d = parcel.readString();
        this.f13774e = parcel.readString();
        this.f13775f = parcel.readString();
        this.f13776g = parcel.readString();
    }

    /* renamed from: a */
    public String m15031a() {
        return this.f13770a;
    }

    /* renamed from: b */
    public String m15032b() {
        return this.f13771b;
    }

    /* renamed from: c */
    public String m15033c() {
        return this.f13772c;
    }

    /* renamed from: d */
    public String m15034d() {
        return this.f13773d;
    }

    /* renamed from: e */
    public String m15035e() {
        return this.f13774e;
    }

    /* renamed from: f */
    public String m15036f() {
        return this.f13775f;
    }

    /* renamed from: g */
    public String m15037g() {
        return this.f13776g;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f13770a);
        parcel.writeString(this.f13771b);
        parcel.writeString(this.f13772c);
        parcel.writeString(this.f13773d);
        parcel.writeString(this.f13774e);
        parcel.writeString(this.f13775f);
        parcel.writeString(this.f13776g);
    }
}

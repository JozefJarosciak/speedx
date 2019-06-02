package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

public final class GameRequestContent implements ShareModel {
    public static final Creator<GameRequestContent> CREATOR = new C31121();
    /* renamed from: a */
    private final String f13794a;
    /* renamed from: b */
    private final List<String> f13795b;
    /* renamed from: c */
    private final String f13796c;
    /* renamed from: d */
    private final String f13797d;
    /* renamed from: e */
    private final ActionType f13798e;
    /* renamed from: f */
    private final String f13799f;
    /* renamed from: g */
    private final Filters f13800g;
    /* renamed from: h */
    private final List<String> f13801h;

    /* renamed from: com.facebook.share.model.GameRequestContent$1 */
    static class C31121 implements Creator<GameRequestContent> {
        C31121() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15131a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15132a(i);
        }

        /* renamed from: a */
        public GameRequestContent m15131a(Parcel parcel) {
            return new GameRequestContent(parcel);
        }

        /* renamed from: a */
        public GameRequestContent[] m15132a(int i) {
            return new GameRequestContent[i];
        }
    }

    public enum ActionType {
        SEND,
        ASKFOR,
        TURN
    }

    public enum Filters {
        APP_USERS,
        APP_NON_USERS
    }

    GameRequestContent(Parcel parcel) {
        this.f13794a = parcel.readString();
        this.f13795b = parcel.createStringArrayList();
        this.f13796c = parcel.readString();
        this.f13797d = parcel.readString();
        this.f13798e = (ActionType) parcel.readSerializable();
        this.f13799f = parcel.readString();
        this.f13800g = (Filters) parcel.readSerializable();
        this.f13801h = parcel.createStringArrayList();
        parcel.readStringList(this.f13801h);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13794a);
        parcel.writeStringList(this.f13795b);
        parcel.writeString(this.f13796c);
        parcel.writeString(this.f13797d);
        parcel.writeSerializable(this.f13798e);
        parcel.writeString(this.f13799f);
        parcel.writeSerializable(this.f13800g);
        parcel.writeStringList(this.f13801h);
    }
}

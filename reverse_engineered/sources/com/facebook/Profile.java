package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3048s.C2953c;
import com.facebook.internal.C3049t;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new C29552();
    /* renamed from: a */
    private final String f13423a;
    /* renamed from: b */
    private final String f13424b;
    /* renamed from: c */
    private final String f13425c;
    /* renamed from: d */
    private final String f13426d;
    /* renamed from: e */
    private final String f13427e;
    /* renamed from: f */
    private final Uri f13428f;

    /* renamed from: com.facebook.Profile$1 */
    static class C29541 implements C2953c {
        C29541() {
        }

        /* renamed from: a */
        public void mo3690a(JSONObject jSONObject) {
            String optString = jSONObject.optString("id");
            if (optString != null) {
                String optString2 = jSONObject.optString("link");
                Profile.m14395a(new Profile(optString, jSONObject.optString("first_name"), jSONObject.optString("middle_name"), jSONObject.optString("last_name"), jSONObject.optString("name"), optString2 != null ? Uri.parse(optString2) : null));
            }
        }

        /* renamed from: a */
        public void mo3689a(FacebookException facebookException) {
        }
    }

    /* renamed from: com.facebook.Profile$2 */
    static class C29552 implements Creator {
        C29552() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14392a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14393a(i);
        }

        /* renamed from: a */
        public Profile m14392a(Parcel parcel) {
            return new Profile(parcel);
        }

        /* renamed from: a */
        public Profile[] m14393a(int i) {
            return new Profile[i];
        }
    }

    /* renamed from: a */
    public static Profile m14394a() {
        return C2990i.m14512a().m14516b();
    }

    /* renamed from: a */
    public static void m14395a(Profile profile) {
        C2990i.m14512a().m14515a(profile);
    }

    /* renamed from: b */
    public static void m14396b() {
        AccessToken a = AccessToken.m14270a();
        if (a == null) {
            m14395a(null);
        } else {
            C3048s.m14752a(a.m14277b(), new C29541());
        }
    }

    public Profile(String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Uri uri) {
        C3049t.m14791a(str, "id");
        this.f13423a = str;
        this.f13424b = str2;
        this.f13425c = str3;
        this.f13426d = str4;
        this.f13427e = str5;
        this.f13428f = uri;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) obj;
        if (this.f13423a.equals(profile.f13423a) && this.f13424b == null) {
            if (profile.f13424b != null) {
                return false;
            }
            return true;
        } else if (this.f13424b.equals(profile.f13424b) && this.f13425c == null) {
            if (profile.f13425c != null) {
                return false;
            }
            return true;
        } else if (this.f13425c.equals(profile.f13425c) && this.f13426d == null) {
            if (profile.f13426d != null) {
                return false;
            }
            return true;
        } else if (this.f13426d.equals(profile.f13426d) && this.f13427e == null) {
            if (profile.f13427e != null) {
                return false;
            }
            return true;
        } else if (!this.f13427e.equals(profile.f13427e) || this.f13428f != null) {
            return this.f13428f.equals(profile.f13428f);
        } else {
            if (profile.f13428f != null) {
                return false;
            }
            return true;
        }
    }

    public int hashCode() {
        int hashCode = this.f13423a.hashCode() + 527;
        if (this.f13424b != null) {
            hashCode = (hashCode * 31) + this.f13424b.hashCode();
        }
        if (this.f13425c != null) {
            hashCode = (hashCode * 31) + this.f13425c.hashCode();
        }
        if (this.f13426d != null) {
            hashCode = (hashCode * 31) + this.f13426d.hashCode();
        }
        if (this.f13427e != null) {
            hashCode = (hashCode * 31) + this.f13427e.hashCode();
        }
        if (this.f13428f != null) {
            return (hashCode * 31) + this.f13428f.hashCode();
        }
        return hashCode;
    }

    /* renamed from: c */
    JSONObject m14397c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.f13423a);
            jSONObject.put("first_name", this.f13424b);
            jSONObject.put("middle_name", this.f13425c);
            jSONObject.put("last_name", this.f13426d);
            jSONObject.put("name", this.f13427e);
            if (this.f13428f == null) {
                return jSONObject;
            }
            jSONObject.put("link_uri", this.f13428f.toString());
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    Profile(JSONObject jSONObject) {
        Uri uri = null;
        this.f13423a = jSONObject.optString("id", null);
        this.f13424b = jSONObject.optString("first_name", null);
        this.f13425c = jSONObject.optString("middle_name", null);
        this.f13426d = jSONObject.optString("last_name", null);
        this.f13427e = jSONObject.optString("name", null);
        String optString = jSONObject.optString("link_uri", null);
        if (optString != null) {
            uri = Uri.parse(optString);
        }
        this.f13428f = uri;
    }

    private Profile(Parcel parcel) {
        this.f13423a = parcel.readString();
        this.f13424b = parcel.readString();
        this.f13425c = parcel.readString();
        this.f13426d = parcel.readString();
        this.f13427e = parcel.readString();
        String readString = parcel.readString();
        this.f13428f = readString == null ? null : Uri.parse(readString);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13423a);
        parcel.writeString(this.f13424b);
        parcel.writeString(this.f13425c);
        parcel.writeString(this.f13426d);
        parcel.writeString(this.f13427e);
        parcel.writeString(this.f13428f == null ? null : this.f13428f.toString());
    }
}

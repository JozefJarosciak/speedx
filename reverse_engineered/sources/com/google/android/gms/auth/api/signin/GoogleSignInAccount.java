package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends AbstractSafeParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR = new zza();
    public static zze dF = zzh.zzavi();
    private static Comparator<Scope> dO = new C32891();
    List<Scope> cx;
    private String dG;
    private String dH;
    private Uri dI;
    private String dJ;
    private long dK;
    private String dL;
    private String dM;
    private String dN;
    private String dd;
    final int versionCode;
    private String zzbgk;

    /* renamed from: com.google.android.gms.auth.api.signin.GoogleSignInAccount$1 */
    class C32891 implements Comparator<Scope> {
        C32891() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return zza((Scope) obj, (Scope) obj2);
        }

        public int zza(Scope scope, Scope scope2) {
            return scope.zzaoh().compareTo(scope2.zzaoh());
        }
    }

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List<Scope> list, String str7, String str8) {
        this.versionCode = i;
        this.zzbgk = str;
        this.dd = str2;
        this.dG = str3;
        this.dH = str4;
        this.dI = uri;
        this.dJ = str5;
        this.dK = j;
        this.dL = str6;
        this.cx = list;
        this.dM = str7;
        this.dN = str8;
    }

    public static GoogleSignInAccount zza(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Uri uri, @Nullable Long l, @NonNull String str7, @NonNull Set<Scope> set) {
        if (l == null) {
            l = Long.valueOf(dF.currentTimeMillis() / 1000);
        }
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l.longValue(), zzab.zzhs(str7), new ArrayList((Collection) zzab.zzaa(set)), str5, str6);
    }

    private JSONObject zzafp() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.dK);
            jSONObject.put("obfuscatedIdentifier", zzafm());
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.cx, dO);
            for (Scope zzaoh : this.cx) {
                jSONArray.put(zzaoh.zzaoh());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInAccount zzfp(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Object optString = jSONObject.optString("photoUrl", null);
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        Set hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        return zza(jSONObject.optString("id"), jSONObject.optString("tokenId", null), jSONObject.optString("email", null), jSONObject.optString("displayName", null), jSONObject.optString("givenName", null), jSONObject.optString("familyName", null), parse, Long.valueOf(parseLong), jSONObject.getString("obfuscatedIdentifier"), hashSet).zzfq(jSONObject.optString("serverAuthCode", null));
    }

    public boolean equals(Object obj) {
        return !(obj instanceof GoogleSignInAccount) ? false : ((GoogleSignInAccount) obj).zzafn().equals(zzafn());
    }

    @Nullable
    public String getDisplayName() {
        return this.dH;
    }

    @Nullable
    public String getEmail() {
        return this.dG;
    }

    @Nullable
    public String getFamilyName() {
        return this.dN;
    }

    @Nullable
    public String getGivenName() {
        return this.dM;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.cx);
    }

    @Nullable
    public String getId() {
        return this.zzbgk;
    }

    @Nullable
    public String getIdToken() {
        return this.dd;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.dI;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.dJ;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public boolean zza() {
        return dF.currentTimeMillis() / 1000 >= this.dK - 300;
    }

    public long zzafl() {
        return this.dK;
    }

    @NonNull
    public String zzafm() {
        return this.dL;
    }

    public String zzafn() {
        return zzafp().toString();
    }

    public String zzafo() {
        JSONObject zzafp = zzafp();
        zzafp.remove("serverAuthCode");
        return zzafp.toString();
    }

    public GoogleSignInAccount zzfq(String str) {
        this.dJ = str;
        return this;
    }
}

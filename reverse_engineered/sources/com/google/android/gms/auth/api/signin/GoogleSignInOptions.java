package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional {
    public static final Creator<GoogleSignInOptions> CREATOR = new zzb();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    private static Comparator<Scope> dO = new C32901();
    public static final Scope dP = new Scope(Scopes.PROFILE);
    public static final Scope dQ = new Scope("email");
    public static final Scope dR = new Scope("openid");
    private Account aP;
    private final ArrayList<Scope> dS;
    private boolean dT;
    private final boolean dU;
    private final boolean dV;
    private String dW;
    private String dX;
    final int versionCode;

    /* renamed from: com.google.android.gms.auth.api.signin.GoogleSignInOptions$1 */
    class C32901 implements Comparator<Scope> {
        C32901() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return zza((Scope) obj, (Scope) obj2);
        }

        public int zza(Scope scope, Scope scope2) {
            return scope.zzaoh().compareTo(scope2.zzaoh());
        }
    }

    public static final class Builder {
        private Account aP;
        private boolean dT;
        private boolean dU;
        private boolean dV;
        private String dW;
        private String dX;
        private Set<Scope> dY = new HashSet();

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            zzab.zzaa(googleSignInOptions);
            this.dY = new HashSet(googleSignInOptions.dS);
            this.dU = googleSignInOptions.dU;
            this.dV = googleSignInOptions.dV;
            this.dT = googleSignInOptions.dT;
            this.dW = googleSignInOptions.dW;
            this.aP = googleSignInOptions.aP;
            this.dX = googleSignInOptions.dX;
        }

        private String zzfs(String str) {
            zzab.zzhs(str);
            boolean z = this.dW == null || this.dW.equals(str);
            zzab.zzb(z, (Object) "two different server client ids provided");
            return str;
        }

        public GoogleSignInOptions build() {
            if (this.dT && (this.aP == null || !this.dY.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(this.dY, this.aP, this.dT, this.dU, this.dV, this.dW, this.dX);
        }

        public Builder requestEmail() {
            this.dY.add(GoogleSignInOptions.dQ);
            return this;
        }

        public Builder requestId() {
            this.dY.add(GoogleSignInOptions.dR);
            return this;
        }

        public Builder requestIdToken(String str) {
            this.dT = true;
            this.dW = zzfs(str);
            return this;
        }

        public Builder requestProfile() {
            this.dY.add(GoogleSignInOptions.dP);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.dY.add(scope);
            this.dY.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public Builder requestServerAuthCode(String str, boolean z) {
            this.dU = true;
            this.dW = zzfs(str);
            this.dV = z;
            return this;
        }

        public Builder setAccountName(String str) {
            this.aP = new Account(zzab.zzhs(str), "com.google");
            return this;
        }

        public Builder setHostedDomain(String str) {
            this.dX = zzab.zzhs(str);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this.versionCode = i;
        this.dS = arrayList;
        this.aP = account;
        this.dT = z;
        this.dU = z2;
        this.dV = z3;
        this.dW = str;
        this.dX = str2;
    }

    private GoogleSignInOptions(Set<Scope> set, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this(2, new ArrayList(set), account, z, z2, z3, str, str2);
    }

    private JSONObject zzafp() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.dS, dO);
            Iterator it = this.dS.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Scope) it.next()).zzaoh());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.aP != null) {
                jSONObject.put("accountName", this.aP.name);
            }
            jSONObject.put("idTokenRequested", this.dT);
            jSONObject.put("forceCodeForRefreshToken", this.dV);
            jSONObject.put("serverAuthRequested", this.dU);
            if (!TextUtils.isEmpty(this.dW)) {
                jSONObject.put("serverClientId", this.dW);
            }
            if (!TextUtils.isEmpty(this.dX)) {
                jSONObject.put("hostedDomain", this.dX);
            }
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInOptions zzfr(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Set hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        Object optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(hashSet, !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.dS.size() != googleSignInOptions.zzafq().size() || !this.dS.containsAll(googleSignInOptions.zzafq())) {
                return false;
            }
            if (this.aP == null) {
                if (googleSignInOptions.getAccount() != null) {
                    return false;
                }
            } else if (!this.aP.equals(googleSignInOptions.getAccount())) {
                return false;
            }
            if (TextUtils.isEmpty(this.dW)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzafu())) {
                    return false;
                }
            } else if (!this.dW.equals(googleSignInOptions.zzafu())) {
                return false;
            }
            return this.dV == googleSignInOptions.zzaft() && this.dT == googleSignInOptions.zzafr() && this.dU == googleSignInOptions.zzafs();
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Account getAccount() {
        return this.aP;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.dS.toArray(new Scope[this.dS.size()]);
    }

    public int hashCode() {
        List arrayList = new ArrayList();
        Iterator it = this.dS.iterator();
        while (it.hasNext()) {
            arrayList.add(((Scope) it.next()).zzaoh());
        }
        Collections.sort(arrayList);
        return new zze().zzr(arrayList).zzr(this.aP).zzr(this.dW).zzba(this.dV).zzba(this.dT).zzba(this.dU).zzagc();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public String zzafn() {
        return zzafp().toString();
    }

    public ArrayList<Scope> zzafq() {
        return new ArrayList(this.dS);
    }

    public boolean zzafr() {
        return this.dT;
    }

    public boolean zzafs() {
        return this.dU;
    }

    public boolean zzaft() {
        return this.dV;
    }

    public String zzafu() {
        return this.dW;
    }

    public String zzafv() {
        return this.dX;
    }
}

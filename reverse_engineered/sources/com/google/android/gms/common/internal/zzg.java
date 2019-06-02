package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzvy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzg {
    private final Account aP;
    private final String cb;
    private final Set<Scope> rN;
    private final int rP;
    private final View rQ;
    private final String rR;
    private final Set<Scope> xY;
    private final Map<Api<?>, zzg$zza> xZ;
    private final zzvy ya;
    private Integer yb;

    public zzg(Account account, Set<Scope> set, Map<Api<?>, zzg$zza> map, int i, View view, String str, String str2, zzvy zzvy) {
        Map map2;
        this.aP = account;
        this.rN = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        if (map == null) {
            map2 = Collections.EMPTY_MAP;
        }
        this.xZ = map2;
        this.rQ = view;
        this.rP = i;
        this.cb = str;
        this.rR = str2;
        this.ya = zzvy;
        Set hashSet = new HashSet(this.rN);
        for (zzg$zza zzg_zza : this.xZ.values()) {
            hashSet.addAll(zzg_zza.dY);
        }
        this.xY = Collections.unmodifiableSet(hashSet);
    }

    public static zzg zzcd(Context context) {
        return new Builder(context).zzaoe();
    }

    public Account getAccount() {
        return this.aP;
    }

    @Deprecated
    public String getAccountName() {
        return this.aP != null ? this.aP.name : null;
    }

    public Account zzaru() {
        return this.aP != null ? this.aP : new Account("<<default account>>", "com.google");
    }

    public int zzase() {
        return this.rP;
    }

    public Set<Scope> zzasf() {
        return this.rN;
    }

    public Set<Scope> zzasg() {
        return this.xY;
    }

    public Map<Api<?>, zzg$zza> zzash() {
        return this.xZ;
    }

    public String zzasi() {
        return this.cb;
    }

    public String zzasj() {
        return this.rR;
    }

    public View zzask() {
        return this.rQ;
    }

    public zzvy zzasl() {
        return this.ya;
    }

    public Integer zzasm() {
        return this.yb;
    }

    public Set<Scope> zzb(Api<?> api) {
        zzg$zza zzg_zza = (zzg$zza) this.xZ.get(api);
        if (zzg_zza == null || zzg_zza.dY.isEmpty()) {
            return this.rN;
        }
        Set<Scope> hashSet = new HashSet(this.rN);
        hashSet.addAll(zzg_zza.dY);
        return hashSet;
    }

    public void zzc(Integer num) {
        this.yb = num;
    }
}

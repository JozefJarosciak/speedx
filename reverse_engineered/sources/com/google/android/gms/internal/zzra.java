package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzra extends Fragment implements zzqp {
    private static WeakHashMap<FragmentActivity, WeakReference<zzra>> vb = new WeakHashMap();
    private Map<String, zzqo> vc = new ArrayMap();
    private Bundle vd;
    private int zzblz = 0;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzra zza(android.support.v4.app.FragmentActivity r3) {
        /*
        r0 = vb;
        r0 = r0.get(r3);
        r0 = (java.lang.ref.WeakReference) r0;
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r0.get();
        r0 = (com.google.android.gms.internal.zzra) r0;
        if (r0 == 0) goto L_0x0013;
    L_0x0012:
        return r0;
    L_0x0013:
        r0 = r3.getSupportFragmentManager();	 Catch:{ ClassCastException -> 0x0048 }
        r1 = "SupportLifecycleFragmentImpl";
        r0 = r0.findFragmentByTag(r1);	 Catch:{ ClassCastException -> 0x0048 }
        r0 = (com.google.android.gms.internal.zzra) r0;	 Catch:{ ClassCastException -> 0x0048 }
        if (r0 == 0) goto L_0x0027;
    L_0x0021:
        r1 = r0.isRemoving();
        if (r1 == 0) goto L_0x003d;
    L_0x0027:
        r0 = new com.google.android.gms.internal.zzra;
        r0.<init>();
        r1 = r3.getSupportFragmentManager();
        r1 = r1.beginTransaction();
        r2 = "SupportLifecycleFragmentImpl";
        r1 = r1.add(r0, r2);
        r1.commitAllowingStateLoss();
    L_0x003d:
        r1 = vb;
        r2 = new java.lang.ref.WeakReference;
        r2.<init>(r0);
        r1.put(r3, r2);
        goto L_0x0012;
    L_0x0048:
        r0 = move-exception;
        r1 = new java.lang.IllegalStateException;
        r2 = "Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl";
        r1.<init>(r2, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzra.zza(android.support.v4.app.FragmentActivity):com.google.android.gms.internal.zzra");
    }

    private void zzb(final String str, @NonNull final zzqo zzqo) {
        if (this.zzblz > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ zzra vj;

                public void run() {
                    if (this.vj.zzblz >= 1) {
                        zzqo.onCreate(this.vj.vd != null ? this.vj.vd.getBundle(str) : null);
                    }
                    if (this.vj.zzblz >= 2) {
                        zzqo.onStart();
                    }
                    if (this.vj.zzblz >= 3) {
                        zzqo.onStop();
                    }
                }
            });
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (zzqo dump : this.vc.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (zzqo onActivityResult : this.vc.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzblz = 1;
        this.vd = bundle;
        for (Entry entry : this.vc.entrySet()) {
            ((zzqo) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.vc.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((zzqo) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public void onStart() {
        super.onStop();
        this.zzblz = 2;
        for (zzqo onStart : this.vc.values()) {
            onStart.onStart();
        }
    }

    public void onStop() {
        super.onStop();
        this.zzblz = 3;
        for (zzqo onStop : this.vc.values()) {
            onStop.onStop();
        }
    }

    public <T extends zzqo> T zza(String str, Class<T> cls) {
        return (zzqo) cls.cast(this.vc.get(str));
    }

    public void zza(String str, @NonNull zzqo zzqo) {
        if (this.vc.containsKey(str)) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
        }
        this.vc.put(str, zzqo);
        zzb(str, zzqo);
    }

    public /* synthetic */ Activity zzaqp() {
        return zzaqr();
    }

    public FragmentActivity zzaqr() {
        return getActivity();
    }
}

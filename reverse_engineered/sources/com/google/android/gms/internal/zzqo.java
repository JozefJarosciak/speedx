package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzqo {
    protected final zzqp va;

    protected zzqo(zzqp zzqp) {
        this.va = zzqp;
    }

    protected static zzqp zzc(zzqn zzqn) {
        return zzqn.zzaqm() ? zzra.zza(zzqn.zzaqo()) : zzqq.zzt(zzqn.zzaqn());
    }

    protected static zzqp zzs(Activity activity) {
        return zzc(new zzqn(activity));
    }

    @MainThread
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public Activity getActivity() {
        return this.va.zzaqp();
    }

    @MainThread
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @MainThread
    public void onCreate(Bundle bundle) {
    }

    @MainThread
    public void onSaveInstanceState(Bundle bundle) {
    }

    @MainThread
    public void onStart() {
    }

    @MainThread
    public void onStop() {
    }
}

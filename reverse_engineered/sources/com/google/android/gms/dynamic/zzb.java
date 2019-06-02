package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.zzc.zza;

@SuppressLint({"NewApi"})
public final class zzb extends zza {
    private Fragment Ma;

    private zzb(Fragment fragment) {
        this.Ma = fragment;
    }

    public static zzb zza(Fragment fragment) {
        return fragment != null ? new zzb(fragment) : null;
    }

    public Bundle getArguments() {
        return this.Ma.getArguments();
    }

    public int getId() {
        return this.Ma.getId();
    }

    public boolean getRetainInstance() {
        return this.Ma.getRetainInstance();
    }

    public String getTag() {
        return this.Ma.getTag();
    }

    public int getTargetRequestCode() {
        return this.Ma.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.Ma.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzae(this.Ma.getView());
    }

    public boolean isAdded() {
        return this.Ma.isAdded();
    }

    public boolean isDetached() {
        return this.Ma.isDetached();
    }

    public boolean isHidden() {
        return this.Ma.isHidden();
    }

    public boolean isInLayout() {
        return this.Ma.isInLayout();
    }

    public boolean isRemoving() {
        return this.Ma.isRemoving();
    }

    public boolean isResumed() {
        return this.Ma.isResumed();
    }

    public boolean isVisible() {
        return this.Ma.isVisible();
    }

    public void setHasOptionsMenu(boolean z) {
        this.Ma.setHasOptionsMenu(z);
    }

    public void setMenuVisibility(boolean z) {
        this.Ma.setMenuVisibility(z);
    }

    public void setRetainInstance(boolean z) {
        this.Ma.setRetainInstance(z);
    }

    public void setUserVisibleHint(boolean z) {
        this.Ma.setUserVisibleHint(z);
    }

    public void startActivity(Intent intent) {
        this.Ma.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.Ma.startActivityForResult(intent, i);
    }

    public void zzab(zzd zzd) {
        this.Ma.registerForContextMenu((View) zze.zzad(zzd));
    }

    public void zzac(zzd zzd) {
        this.Ma.unregisterForContextMenu((View) zze.zzad(zzd));
    }

    public zzd zzbcs() {
        return zze.zzae(this.Ma.getActivity());
    }

    public zzc zzbct() {
        return zza(this.Ma.getParentFragment());
    }

    public zzd zzbcu() {
        return zze.zzae(this.Ma.getResources());
    }

    public zzc zzbcv() {
        return zza(this.Ma.getTargetFragment());
    }
}

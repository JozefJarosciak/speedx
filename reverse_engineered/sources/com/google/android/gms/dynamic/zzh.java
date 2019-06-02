package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.google.android.gms.dynamic.zzc.zza;

public final class zzh extends zza {
    private Fragment Md;

    private zzh(Fragment fragment) {
        this.Md = fragment;
    }

    public static zzh zza(Fragment fragment) {
        return fragment != null ? new zzh(fragment) : null;
    }

    public Bundle getArguments() {
        return this.Md.getArguments();
    }

    public int getId() {
        return this.Md.getId();
    }

    public boolean getRetainInstance() {
        return this.Md.getRetainInstance();
    }

    public String getTag() {
        return this.Md.getTag();
    }

    public int getTargetRequestCode() {
        return this.Md.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.Md.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzae(this.Md.getView());
    }

    public boolean isAdded() {
        return this.Md.isAdded();
    }

    public boolean isDetached() {
        return this.Md.isDetached();
    }

    public boolean isHidden() {
        return this.Md.isHidden();
    }

    public boolean isInLayout() {
        return this.Md.isInLayout();
    }

    public boolean isRemoving() {
        return this.Md.isRemoving();
    }

    public boolean isResumed() {
        return this.Md.isResumed();
    }

    public boolean isVisible() {
        return this.Md.isVisible();
    }

    public void setHasOptionsMenu(boolean z) {
        this.Md.setHasOptionsMenu(z);
    }

    public void setMenuVisibility(boolean z) {
        this.Md.setMenuVisibility(z);
    }

    public void setRetainInstance(boolean z) {
        this.Md.setRetainInstance(z);
    }

    public void setUserVisibleHint(boolean z) {
        this.Md.setUserVisibleHint(z);
    }

    public void startActivity(Intent intent) {
        this.Md.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.Md.startActivityForResult(intent, i);
    }

    public void zzab(zzd zzd) {
        this.Md.registerForContextMenu((View) zze.zzad(zzd));
    }

    public void zzac(zzd zzd) {
        this.Md.unregisterForContextMenu((View) zze.zzad(zzd));
    }

    public zzd zzbcs() {
        return zze.zzae(this.Md.getActivity());
    }

    public zzc zzbct() {
        return zza(this.Md.getParentFragment());
    }

    public zzd zzbcu() {
        return zze.zzae(this.Md.getResources());
    }

    public zzc zzbcv() {
        return zza(this.Md.getTargetFragment());
    }
}

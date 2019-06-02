package com.beastbikes.android.modules.cycling.activity.ui;

class BleSensorManagerActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f8600a;
    /* renamed from: b */
    final /* synthetic */ BleSensorManagerActivity f8601b;

    BleSensorManagerActivity$1(BleSensorManagerActivity bleSensorManagerActivity, int i) {
        this.f8601b = bleSensorManagerActivity;
        this.f8600a = i;
    }

    public void run() {
        BleSensorManagerActivity.a(this.f8601b, this.f8600a, null);
    }
}

package com.beastbikes.android.ble.biz;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.content.SharedPreferences;
import com.beastbikes.android.ble.C1602a;

/* compiled from: BaseCentralInvocation */
/* renamed from: com.beastbikes.android.ble.biz.b */
public abstract class C1613b implements C1602a {
    /* renamed from: a */
    public abstract void mo3138a(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    /* renamed from: a */
    public abstract void mo3139a(Context context, SharedPreferences sharedPreferences);

    /* renamed from: a */
    public abstract void mo3154a(Object obj, int i);

    /* renamed from: a */
    public abstract void mo3155a(String str, String str2, byte b);

    /* renamed from: b */
    public abstract void mo3164b(Object obj, int i);

    /* renamed from: h */
    public abstract void mo3175h();

    /* renamed from: i */
    public abstract void mo3176i();

    C1613b() {
    }
}

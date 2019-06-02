package com.beastbikes.android.ble.protocol.v1;

public class LocaleConfigCharcateristic extends ConfigCharacteristic {
    private int locale;

    public int getLocale() {
        return this.locale;
    }

    public void setLocale(int i) {
        this.locale = i;
    }
}

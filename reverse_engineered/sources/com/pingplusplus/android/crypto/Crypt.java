package com.pingplusplus.android.crypto;

public class Crypt {
    static {
        try {
            System.loadLibrary("pingpp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public native byte[] encryptData(String str, String str2);

    public native String encryptKey(String str, String str2);
}

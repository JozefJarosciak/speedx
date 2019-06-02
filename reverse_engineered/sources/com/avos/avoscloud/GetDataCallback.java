package com.avos.avoscloud;

public abstract class GetDataCallback extends AVCallback<byte[]> {
    public abstract void done(byte[] bArr, AVException aVException);

    protected final void internalDone0(byte[] bArr, AVException aVException) {
        done(bArr, aVException);
    }
}

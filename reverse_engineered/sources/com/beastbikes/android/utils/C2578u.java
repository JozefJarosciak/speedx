package com.beastbikes.android.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/* compiled from: SerializeUtil */
/* renamed from: com.beastbikes.android.utils.u */
public class C2578u {
    /* renamed from: a */
    public static Object m12902a(byte[] bArr) throws Exception {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
    }

    /* renamed from: a */
    public static byte[] m12903a(Serializable serializable) throws Exception {
        if (serializable == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ObjectOutputStream(byteArrayOutputStream).writeObject(serializable);
        return byteArrayOutputStream.toByteArray();
    }
}

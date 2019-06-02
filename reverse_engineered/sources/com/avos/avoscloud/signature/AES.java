package com.avos.avoscloud.signature;

import android.util.Log;
import com.google.common.base.Ascii;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private final String CIPHERMODEPADDING = "AES/CBC/PKCS7Padding";
    private final int HASH_ITERATIONS = 10000;
    private IvParameterSpec IV;
    private final String KEY_GENERATION_ALG = "PBKDF2WithHmacSHA1";
    private final int KEY_LENGTH = 256;
    private char[] humanPassphrase = "QxciDjdHjuAIf8VCsqhmGK3OZV7pBQTZ".toCharArray();
    private byte[] iv = new byte[]{(byte) 10, (byte) 1, Ascii.VT, (byte) 5, (byte) 4, Ascii.SI, (byte) 7, (byte) 9, Ascii.ETB, (byte) 3, (byte) 1, (byte) 6, (byte) 8, Ascii.FF, (byte) 13, (byte) 91};
    private SecretKeyFactory keyfactory = null;
    private PBEKeySpec myKeyspec = new PBEKeySpec(this.humanPassphrase, this.salt, 10000, 256);
    private byte[] salt = new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, Ascii.VT, Ascii.FF, (byte) 13, Ascii.SO, Ascii.SI};
    private SecretKey sk = null;
    private SecretKeySpec skforAES = null;

    public AES() {
        try {
            this.keyfactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            this.sk = this.keyfactory.generateSecret(this.myKeyspec);
        } catch (NoSuchAlgorithmException e) {
            Log.e("AESdemo", "no key factory support for PBEWITHSHAANDTWOFISH-CBC");
        } catch (InvalidKeySpecException e2) {
            Log.e("AESdemo", "invalid key spec for PBEWITHSHAANDTWOFISH-CBC");
        }
        this.skforAES = new SecretKeySpec(this.sk.getEncoded(), "AES");
        this.IV = new IvParameterSpec(this.iv);
    }

    public String encrypt(byte[] bArr) {
        return Base64Encoder.encode(encrypt("AES/CBC/PKCS7Padding", this.skforAES, this.IV, bArr));
    }

    public String decrypt(String str) {
        return new String(decrypt("AES/CBC/PKCS7Padding", this.skforAES, this.IV, Base64Decoder.decodeToBytes(str)));
    }

    private byte[] addPadding(byte[] bArr) {
        int i;
        int length = 16 - (bArr.length % 16);
        if (length == 0) {
            length = 16;
        }
        byte[] bArr2 = new byte[(bArr.length + length)];
        for (i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[i];
        }
        for (i = bArr.length; i < bArr.length + length; i++) {
            bArr2[i] = (byte) length;
        }
        return bArr2;
    }

    private byte[] dropPadding(byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length - bArr[bArr.length - 1])];
        for (int i = 0; i < bArr2.length; i++) {
            bArr2[i] = bArr[i];
            bArr[i] = (byte) 0;
        }
        return bArr2;
    }

    private byte[] encrypt(String str, SecretKey secretKey, IvParameterSpec ivParameterSpec, byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance(str);
            instance.init(1, secretKey, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            Log.e("AESdemo", "no cipher getinstance support for " + str);
            return null;
        } catch (NoSuchPaddingException e2) {
            Log.e("AESdemo", "no cipher getinstance support for padding " + str);
            return null;
        } catch (InvalidKeyException e3) {
            Log.e("AESdemo", "invalid key exception");
            return null;
        } catch (InvalidAlgorithmParameterException e4) {
            Log.e("AESdemo", "invalid algorithm parameter exception");
            return null;
        } catch (IllegalBlockSizeException e5) {
            Log.e("AESdemo", "illegal block size exception");
            return null;
        } catch (BadPaddingException e6) {
            Log.e("AESdemo", "bad padding exception");
            return null;
        }
    }

    private byte[] decrypt(String str, SecretKey secretKey, IvParameterSpec ivParameterSpec, byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance(str);
            instance.init(2, secretKey, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            Log.e("AESdemo", "no cipher getinstance support for " + str);
            return null;
        } catch (NoSuchPaddingException e2) {
            Log.e("AESdemo", "no cipher getinstance support for padding " + str);
            return null;
        } catch (InvalidKeyException e3) {
            Log.e("AESdemo", "invalid key exception");
            return null;
        } catch (InvalidAlgorithmParameterException e4) {
            Log.e("AESdemo", "invalid algorithm parameter exception");
            return null;
        } catch (IllegalBlockSizeException e5) {
            Log.e("AESdemo", "illegal block size exception");
            return null;
        } catch (BadPaddingException e6) {
            Log.e("AESdemo", "bad padding exception");
            e6.printStackTrace();
            return null;
        }
    }
}

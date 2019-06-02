package com.google.zxing.datamatrix.encoder;

import android.support.v4.media.TransportMediator;
import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;

public final class ErrorCorrection {
    private static final int[] ALOG = new int[255];
    private static final int[][] FACTORS = new int[][]{new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, SyslogConstants.LOG_LOCAL2, 134, 240, 92, 254}, new int[]{28, 24, Opcodes.INVOKEINTERFACE, Opcodes.IF_ACMPNE, 223, 248, AVException.OBJECT_TOO_LARGE, 255, 110, 61}, new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, Opcodes.IFLE, 91, 61, 42, AVException.VALIDATION_ERROR, AVException.USER_WITH_MOBILEPHONE_NOT_FOUND, 97, Opcodes.GETSTATIC, 100, 242}, new int[]{Opcodes.IFGE, 97, Opcodes.CHECKCAST, AVException.UNSUPPORTED_SERVICE, 95, 9, Opcodes.IFGT, AVException.OPERATION_FORBIDDEN, 138, 45, 18, 186, 83, Opcodes.INVOKEINTERFACE}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, AVException.USER_WITH_MOBILEPHONE_NOT_FOUND, 109, 129, 94, 254, 225, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, Opcodes.I2B, AVException.FILE_DOWNLOAD_INCONSISTENT_FAILURE, 79, 108, 82, 27, 174, 186, Opcodes.IRETURN}, new int[]{52, 190, 88, 205, 109, 39, 176, 21, Opcodes.IFLT, 197, AVException.INVALID_LINKED_SESSION, 223, Opcodes.IFLT, 21, 5, Opcodes.IRETURN, 254, AVException.TIMEOUT, 12, Opcodes.PUTFIELD, 184, 96, 50, Opcodes.INSTANCEOF}, new int[]{AVException.USER_DOESNOT_EXIST, 231, 43, 97, 71, 96, 103, 174, 37, Opcodes.DCMPL, 170, 53, 75, 34, 249, AVException.INVALID_NESTED_KEY, 17, 138, 110, AVException.USER_WITH_MOBILEPHONE_NOT_FOUND, AVException.SCRIPT_ERROR, SyslogConstants.LOG_LOCAL1, 120, Opcodes.DCMPL, 233, 168, 93, 255}, new int[]{245, 127, 242, 218, TransportMediator.KEYCODE_MEDIA_RECORD, 250, 162, Opcodes.PUTFIELD, 102, 120, 84, Opcodes.PUTSTATIC, 220, AVException.INVALID_LINKED_SESSION, 80, Opcodes.INVOKEVIRTUAL, 229, 18, 2, 4, 68, 33, 101, AVException.DUPLICATE_VALUE, 95, AVException.OPERATION_FORBIDDEN, AVException.PUSH_MISCONFIGURED, 44, 175, 184, 59, 25, 225, 98, 81, 112}, new int[]{77, Opcodes.INSTANCEOF, AVException.DUPLICATE_VALUE, 31, 19, 38, 22, 153, 247, 105, AVException.INVALID_FILE_NAME, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, AVException.USER_MOBILE_PHONENUMBER_TAKEN, 111, 57, AVException.INVALID_NESTED_KEY, 21, 1, AVException.FILE_DOWNLOAD_INCONSISTENT_FAILURE, 57, 54, 101, 248, 202, 69, 50, 150, Opcodes.RETURN, 226, 5, 9, 5}, new int[]{245, Opcodes.IINC, Opcodes.IRETURN, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, Opcodes.I2S, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, Opcodes.NEW, Opcodes.I2S, 160, 175, 69, AVException.USER_WITH_MOBILEPHONE_NOT_FOUND, 92, AVException.FILE_DOWNLOAD_INCONSISTENT_FAILURE, 225, 19}, new int[]{175, 9, 223, 238, 12, 17, 220, AVException.ACCOUNT_ALREADY_LINKED, 100, 29, 175, 170, 230, Opcodes.CHECKCAST, AVException.USER_MOBILEPHONE_NOT_VERIFIED, 235, 150, Opcodes.IF_ICMPEQ, 36, 223, 38, 200, Opcodes.IINC, 54, 228, 146, 218, 234, 117, 203, 29, 232, SyslogConstants.LOG_LOCAL2, 238, 22, 150, 201, 117, 62, 207, Opcodes.IF_ICMPLE, 13, AVException.DUPLICATE_VALUE, 245, 127, 67, 247, 28, Opcodes.IFLT, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, Opcodes.RET, 50, SyslogConstants.LOG_LOCAL2, AVException.USERNAME_PASSWORD_MISMATCH, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, Opcodes.INVOKEINTERFACE, 112, 134, 230, 245, 63, 197, 190, 250, 106, Opcodes.INVOKEINTERFACE, 221, 175, 64, 114, 71, 161, 44, Opcodes.I2S, 6, 27, 218, 51, 63, 87, 10, 40, TransportMediator.KEYCODE_MEDIA_RECORD, 188, 17, Opcodes.IF_ICMPGT, 31, 176, 170, 4, 107, 232, 7, 94, Opcodes.IF_ACMPNE, 224, AVException.TIMEOUT, 86, 47, 11, 204}, new int[]{220, 228, 173, 89, AVException.INVALID_LINKED_SESSION, Opcodes.FCMPL, Opcodes.IF_ICMPEQ, 56, 89, 33, Opcodes.I2S, 244, Opcodes.IFNE, 36, 73, 127, AVException.USER_WITH_MOBILEPHONE_NOT_FOUND, SyslogConstants.LOG_LOCAL1, 248, Opcodes.GETFIELD, 234, 197, Opcodes.IFLE, Opcodes.RETURN, 68, AVException.INVALID_FILE_NAME, 93, AVException.USER_WITH_MOBILEPHONE_NOT_FOUND, 15, 160, 227, 236, 66, AVException.INVALID_ROLE_NAME, 153, Opcodes.INVOKEINTERFACE, 202, 167, Opcodes.PUTSTATIC, 25, 220, 232, 96, AVException.USERNAME_PASSWORD_MISMATCH, 231, SyslogConstants.LOG_LOCAL1, 223, 239, Opcodes.PUTFIELD, 241, 59, 52, Opcodes.IRETURN, 25, 49, 232, AVException.USER_DOESNOT_EXIST, 189, 64, 54, 108, 153, Opcodes.IINC, 63, 96, 103, 82, 186}};
    private static final int[] FACTOR_SETS = new int[]{5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[] LOG = new int[256];
    private static final int MODULO_VALUE = 301;

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            ALOG[i2] = i;
            LOG[i] = i2;
            i *= 2;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder stringBuilder = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        stringBuilder.append(str);
        int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
        if (interleavedBlockCount == 1) {
            stringBuilder.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
        } else {
            int i;
            stringBuilder.setLength(stringBuilder.capacity());
            int[] iArr = new int[interleavedBlockCount];
            int[] iArr2 = new int[interleavedBlockCount];
            int[] iArr3 = new int[interleavedBlockCount];
            for (i = 0; i < interleavedBlockCount; i++) {
                iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i + 1);
                iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i + 1);
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
            }
            for (int i2 = 0; i2 < interleavedBlockCount; i2++) {
                StringBuilder stringBuilder2 = new StringBuilder(iArr[i2]);
                for (i = i2; i < symbolInfo.getDataCapacity(); i += interleavedBlockCount) {
                    stringBuilder2.append(str.charAt(i));
                }
                String createECCBlock = createECCBlock(stringBuilder2.toString(), iArr2[i2]);
                i = i2;
                int i3 = 0;
                while (i < iArr2[i2] * interleavedBlockCount) {
                    int i4 = i3 + 1;
                    stringBuilder.setCharAt(symbolInfo.getDataCapacity() + i, createECCBlock.charAt(i3));
                    i += interleavedBlockCount;
                    i3 = i4;
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }

    private static String createECCBlock(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (i5 < FACTOR_SETS.length) {
            if (FACTOR_SETS[i5] == i3) {
                break;
            }
            i5++;
        }
        i5 = -1;
        if (i5 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i3);
        }
        int[] iArr = FACTORS[i5];
        char[] cArr = new char[i3];
        for (i5 = 0; i5 < i3; i5++) {
            cArr[i5] = '\u0000';
        }
        for (int i6 = i; i6 < i + i2; i6++) {
            int charAt = charSequence.charAt(i6) ^ cArr[i3 - 1];
            i5 = i3 - 1;
            while (i5 > 0) {
                if (charAt == 0 || iArr[i5] == 0) {
                    cArr[i5] = cArr[i5 - 1];
                } else {
                    cArr[i5] = (char) (cArr[i5 - 1] ^ ALOG[(LOG[charAt] + LOG[iArr[i5]]) % 255]);
                }
                i5--;
            }
            if (charAt == 0 || iArr[0] == 0) {
                cArr[0] = '\u0000';
            } else {
                cArr[0] = (char) ALOG[(LOG[charAt] + LOG[iArr[0]]) % 255];
            }
        }
        char[] cArr2 = new char[i3];
        while (i4 < i3) {
            cArr2[i4] = cArr[(i3 - i4) - 1];
            i4++;
        }
        return String.valueOf(cArr2);
    }
}

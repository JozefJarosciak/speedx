package com.alibaba.fastjson.parser;

import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;

public final class CharTypes {
    public static final char[] ASCII_CHARS = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
    public static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final boolean[] firstIdentifierFlags = new boolean[256];
    public static final boolean[] identifierFlags = new boolean[256];
    public static final char[] replaceChars = new char[128];
    public static final byte[] specicalFlags_doubleQuotes = new byte[256];
    public static final byte[] specicalFlags_singleQuotes = new byte[256];

    static {
        int i = 0;
        while (i < firstIdentifierFlags.length) {
            if (i >= 65 && i <= 90) {
                firstIdentifierFlags[i] = true;
            } else if (i >= 97 && i <= AVException.INVALID_FILE_NAME) {
                firstIdentifierFlags[i] = true;
            } else if (i == 95) {
                firstIdentifierFlags[i] = true;
            }
            i = (char) (i + 1);
        }
        i = 0;
        while (i < identifierFlags.length) {
            if (i >= 65 && i <= 90) {
                identifierFlags[i] = true;
            } else if (i >= 97 && i <= AVException.INVALID_FILE_NAME) {
                identifierFlags[i] = true;
            } else if (i == 95) {
                identifierFlags[i] = true;
            } else if (i >= 48 && i <= 57) {
                identifierFlags[i] = true;
            }
            i = (char) (i + 1);
        }
        specicalFlags_doubleQuotes[0] = (byte) 1;
        specicalFlags_doubleQuotes[1] = (byte) 1;
        specicalFlags_doubleQuotes[2] = (byte) 1;
        specicalFlags_doubleQuotes[3] = (byte) 1;
        specicalFlags_doubleQuotes[4] = (byte) 1;
        specicalFlags_doubleQuotes[5] = (byte) 1;
        specicalFlags_doubleQuotes[6] = (byte) 1;
        specicalFlags_doubleQuotes[7] = (byte) 1;
        specicalFlags_doubleQuotes[8] = (byte) 1;
        specicalFlags_doubleQuotes[9] = (byte) 1;
        specicalFlags_doubleQuotes[10] = (byte) 1;
        specicalFlags_doubleQuotes[11] = (byte) 1;
        specicalFlags_doubleQuotes[12] = (byte) 1;
        specicalFlags_doubleQuotes[13] = (byte) 1;
        specicalFlags_doubleQuotes[34] = (byte) 1;
        specicalFlags_doubleQuotes[92] = (byte) 1;
        specicalFlags_singleQuotes[0] = (byte) 1;
        specicalFlags_singleQuotes[1] = (byte) 1;
        specicalFlags_singleQuotes[2] = (byte) 1;
        specicalFlags_singleQuotes[3] = (byte) 1;
        specicalFlags_singleQuotes[4] = (byte) 1;
        specicalFlags_singleQuotes[5] = (byte) 1;
        specicalFlags_singleQuotes[6] = (byte) 1;
        specicalFlags_singleQuotes[7] = (byte) 1;
        specicalFlags_singleQuotes[8] = (byte) 1;
        specicalFlags_singleQuotes[9] = (byte) 1;
        specicalFlags_singleQuotes[10] = (byte) 1;
        specicalFlags_singleQuotes[11] = (byte) 1;
        specicalFlags_singleQuotes[12] = (byte) 1;
        specicalFlags_singleQuotes[13] = (byte) 1;
        specicalFlags_singleQuotes[14] = (byte) 4;
        specicalFlags_singleQuotes[15] = (byte) 4;
        specicalFlags_singleQuotes[16] = (byte) 4;
        specicalFlags_singleQuotes[17] = (byte) 4;
        specicalFlags_singleQuotes[18] = (byte) 4;
        specicalFlags_singleQuotes[19] = (byte) 4;
        specicalFlags_singleQuotes[20] = (byte) 4;
        specicalFlags_singleQuotes[21] = (byte) 4;
        specicalFlags_singleQuotes[22] = (byte) 4;
        specicalFlags_singleQuotes[23] = (byte) 4;
        specicalFlags_singleQuotes[24] = (byte) 4;
        specicalFlags_singleQuotes[25] = (byte) 4;
        specicalFlags_singleQuotes[32] = (byte) 4;
        specicalFlags_singleQuotes[92] = (byte) 1;
        specicalFlags_singleQuotes[39] = (byte) 1;
        for (i = 127; i <= 160; i++) {
            specicalFlags_doubleQuotes[i] = (byte) 4;
            specicalFlags_singleQuotes[i] = (byte) 4;
        }
        replaceChars[0] = '0';
        replaceChars[1] = '1';
        replaceChars[2] = '2';
        replaceChars[3] = '3';
        replaceChars[4] = '4';
        replaceChars[5] = '5';
        replaceChars[6] = '6';
        replaceChars[7] = '7';
        replaceChars[8] = 'b';
        replaceChars[9] = 't';
        replaceChars[10] = 'n';
        replaceChars[11] = 'v';
        replaceChars[12] = 'f';
        replaceChars[13] = 'r';
        replaceChars[34] = CoreConstants.DOUBLE_QUOTE_CHAR;
        replaceChars[39] = CoreConstants.SINGLE_QUOTE_CHAR;
        replaceChars[47] = '/';
        replaceChars[92] = CoreConstants.ESCAPE_CHAR;
    }
}

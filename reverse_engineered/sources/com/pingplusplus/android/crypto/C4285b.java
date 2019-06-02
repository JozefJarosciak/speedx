package com.pingplusplus.android.crypto;

import com.alibaba.fastjson.asm.Opcodes;
import com.google.common.base.Ascii;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.UnsupportedEncodingException;

/* renamed from: com.pingplusplus.android.crypto.b */
public class C4285b {
    /* renamed from: a */
    private static char[] f14957a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    /* renamed from: b */
    private static byte[] f14958b = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, Ascii.VT, Ascii.FF, (byte) 13, Ascii.SO, Ascii.SI, Ascii.DLE, (byte) 17, Ascii.DC2, (byte) 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, Ascii.US, (byte) 32, Framer.ENTER_FRAME_PREFIX, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, Framer.STDIN_FRAME_PREFIX, (byte) 46, (byte) 47, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};

    /* renamed from: a */
    public static String m16982a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(f14957a[i3 >>> 2]);
                stringBuffer.append(f14957a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(f14957a[i3 >>> 2]);
                stringBuffer.append(f14957a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
                stringBuffer.append(f14957a[(i2 & 15) << 2]);
                stringBuffer.append(SimpleComparison.EQUAL_TO_OPERATION);
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & 255;
            stringBuffer.append(f14957a[i3 >>> 2]);
            stringBuffer.append(f14957a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
            stringBuffer.append(f14957a[((i2 & 15) << 2) | ((i4 & Opcodes.CHECKCAST) >>> 6)]);
            stringBuffer.append(f14957a[i4 & 63]);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static byte[] m16983a(String str) {
        try {
            return C4285b.m16984b(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /* renamed from: b */
    private static byte[] m16984b(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = str.getBytes("US-ASCII");
        int length = bytes.length;
        int i = 0;
        while (i < length) {
            while (true) {
                int i2 = i + 1;
                byte b = f14958b[bytes[i]];
                byte b2;
                byte b3;
                byte b4;
                if (i2 >= length || b != (byte) -1) {
                    if (b != (byte) -1) {
                        while (true) {
                            i = i2 + 1;
                            b2 = f14958b[bytes[i2]];
                            if (i >= length || b2 != (byte) -1) {
                                if (b2 == (byte) -1) {
                                    break;
                                }
                                stringBuffer.append((char) ((b << 2) | ((b2 & 48) >>> 4)));
                                while (true) {
                                    i2 = i + 1;
                                    b3 = bytes[i];
                                    if (b3 != (byte) 61) {
                                        return stringBuffer.toString().getBytes("iso8859-1");
                                    }
                                    b = f14958b[b3];
                                    if (i2 >= length || b != (byte) -1) {
                                        if (b == (byte) -1) {
                                            break;
                                        }
                                        stringBuffer.append((char) (((b2 & 15) << 4) | ((b & 60) >>> 2)));
                                        while (true) {
                                            i = i2 + 1;
                                            b4 = bytes[i2];
                                            if (b4 == (byte) 61) {
                                                return stringBuffer.toString().getBytes("iso8859-1");
                                            }
                                            b4 = f14958b[b4];
                                            if (i >= length || b4 != (byte) -1) {
                                                if (b4 == (byte) -1) {
                                                    break;
                                                }
                                                stringBuffer.append((char) (b4 | ((b & 3) << 6)));
                                            } else {
                                                i2 = i;
                                            }
                                        }
                                        if (b4 == (byte) -1) {
                                            break;
                                        }
                                        stringBuffer.append((char) (b4 | ((b & 3) << 6)));
                                    } else {
                                        i = i2;
                                    }
                                }
                                if (b == (byte) -1) {
                                    stringBuffer.append((char) (((b2 & 15) << 4) | ((b & 60) >>> 2)));
                                    while (true) {
                                        i = i2 + 1;
                                        b4 = bytes[i2];
                                        if (b4 == (byte) 61) {
                                            b4 = f14958b[b4];
                                            if (i >= length) {
                                                break;
                                            }
                                            break;
                                        }
                                        return stringBuffer.toString().getBytes("iso8859-1");
                                        i2 = i;
                                    }
                                    if (b4 == (byte) -1) {
                                        break;
                                    }
                                    stringBuffer.append((char) (b4 | ((b & 3) << 6)));
                                } else {
                                    break;
                                }
                            }
                            i2 = i;
                        }
                        if (b2 == (byte) -1) {
                            stringBuffer.append((char) ((b << 2) | ((b2 & 48) >>> 4)));
                            while (true) {
                                i2 = i + 1;
                                b3 = bytes[i];
                                if (b3 != (byte) 61) {
                                    b = f14958b[b3];
                                    if (i2 >= length) {
                                        break;
                                    }
                                    break;
                                }
                                return stringBuffer.toString().getBytes("iso8859-1");
                                i = i2;
                            }
                            if (b == (byte) -1) {
                                break;
                            }
                            stringBuffer.append((char) (((b2 & 15) << 4) | ((b & 60) >>> 2)));
                            while (true) {
                                i = i2 + 1;
                                b4 = bytes[i2];
                                if (b4 == (byte) 61) {
                                    b4 = f14958b[b4];
                                    if (i >= length) {
                                        break;
                                    }
                                    break;
                                }
                                return stringBuffer.toString().getBytes("iso8859-1");
                                i2 = i;
                            }
                            if (b4 == (byte) -1) {
                                break;
                            }
                            stringBuffer.append((char) (b4 | ((b & 3) << 6)));
                        } else {
                            break;
                        }
                    }
                    break;
                }
                i = i2;
            }
            if (b != (byte) -1) {
                break;
            }
            while (true) {
                i = i2 + 1;
                b2 = f14958b[bytes[i2]];
                if (i >= length) {
                    break;
                }
                break;
                i2 = i;
            }
            if (b2 == (byte) -1) {
                break;
            }
            stringBuffer.append((char) ((b << 2) | ((b2 & 48) >>> 4)));
            while (true) {
                i2 = i + 1;
                b3 = bytes[i];
                if (b3 != (byte) 61) {
                    b = f14958b[b3];
                    if (i2 >= length) {
                        break;
                    }
                    break;
                }
                return stringBuffer.toString().getBytes("iso8859-1");
                i = i2;
            }
            if (b == (byte) -1) {
                stringBuffer.append((char) (((b2 & 15) << 4) | ((b & 60) >>> 2)));
                while (true) {
                    i = i2 + 1;
                    b4 = bytes[i2];
                    if (b4 == (byte) 61) {
                        b4 = f14958b[b4];
                        if (i >= length) {
                            break;
                        }
                        break;
                    }
                    return stringBuffer.toString().getBytes("iso8859-1");
                    i2 = i;
                }
                if (b4 == (byte) -1) {
                    break;
                }
                stringBuffer.append((char) (b4 | ((b & 3) << 6)));
            } else {
                break;
            }
        }
        return stringBuffer.toString().getBytes("iso8859-1");
    }
}

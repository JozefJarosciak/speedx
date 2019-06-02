package com.alipay.p029b.p030a.p031a.p032a.p033a;

import com.facebook.stetho.dumpapp.Framer;
import com.google.common.base.Ascii;

/* renamed from: com.alipay.b.a.a.a.a.a */
public final class C0786a {
    /* renamed from: a */
    private static char[] f1851a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    /* renamed from: b */
    private static byte[] f1852b = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, Ascii.VT, Ascii.FF, (byte) 13, Ascii.SO, Ascii.SI, Ascii.DLE, (byte) 17, Ascii.DC2, (byte) 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, Ascii.US, (byte) 32, Framer.ENTER_FRAME_PREFIX, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, Framer.STDIN_FRAME_PREFIX, (byte) 46, (byte) 47, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};

    /* renamed from: a */
    public static byte[] m3010a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = str.getBytes("US-ASCII");
        int length = bytes.length;
        int i = 0;
        while (i < length) {
            byte b;
            byte b2;
            while (true) {
                int i2 = i + 1;
                byte b3 = f1852b[bytes[i]];
                byte b4;
                if (i2 >= length || b3 != (byte) -1) {
                    if (b3 == (byte) -1) {
                        break;
                    }
                    while (true) {
                        i = i2 + 1;
                        b = f1852b[bytes[i2]];
                        if (i >= length || b != (byte) -1) {
                            if (b == (byte) -1) {
                                break;
                            }
                            stringBuffer.append((char) ((b3 << 2) | ((b & 48) >>> 4)));
                            while (true) {
                                i2 = i + 1;
                                b4 = bytes[i];
                                if (b4 != (byte) 61) {
                                    return stringBuffer.toString().getBytes("iso8859-1");
                                }
                                b3 = f1852b[b4];
                                if (i2 >= length || b3 != (byte) -1) {
                                    if (b3 == (byte) -1) {
                                        break;
                                    }
                                    stringBuffer.append((char) (((b & 15) << 4) | ((b3 & 60) >>> 2)));
                                    while (true) {
                                        i = i2 + 1;
                                        b2 = bytes[i2];
                                        if (b2 == (byte) 61) {
                                            return stringBuffer.toString().getBytes("iso8859-1");
                                        }
                                        b2 = f1852b[b2];
                                        if (i >= length || b2 != (byte) -1) {
                                            if (b2 == (byte) -1) {
                                                break;
                                            }
                                            stringBuffer.append((char) (b2 | ((b3 & 3) << 6)));
                                        } else {
                                            i2 = i;
                                        }
                                    }
                                    if (b2 == (byte) -1) {
                                        break;
                                    }
                                    stringBuffer.append((char) (b2 | ((b3 & 3) << 6)));
                                } else {
                                    i = i2;
                                }
                            }
                            if (b3 == (byte) -1) {
                                stringBuffer.append((char) (((b & 15) << 4) | ((b3 & 60) >>> 2)));
                                while (true) {
                                    i = i2 + 1;
                                    b2 = bytes[i2];
                                    if (b2 == (byte) 61) {
                                        b2 = f1852b[b2];
                                        if (i >= length) {
                                            break;
                                        }
                                        break;
                                    }
                                    return stringBuffer.toString().getBytes("iso8859-1");
                                    i2 = i;
                                }
                                if (b2 == (byte) -1) {
                                    break;
                                }
                                stringBuffer.append((char) (b2 | ((b3 & 3) << 6)));
                            } else {
                                break;
                            }
                        }
                        i2 = i;
                    }
                    if (b == (byte) -1) {
                        stringBuffer.append((char) ((b3 << 2) | ((b & 48) >>> 4)));
                        while (true) {
                            i2 = i + 1;
                            b4 = bytes[i];
                            if (b4 != (byte) 61) {
                                b3 = f1852b[b4];
                                if (i2 >= length) {
                                    break;
                                }
                                break;
                            }
                            return stringBuffer.toString().getBytes("iso8859-1");
                            i = i2;
                        }
                        if (b3 == (byte) -1) {
                            break;
                        }
                        stringBuffer.append((char) (((b & 15) << 4) | ((b3 & 60) >>> 2)));
                        while (true) {
                            i = i2 + 1;
                            b2 = bytes[i2];
                            if (b2 == (byte) 61) {
                                b2 = f1852b[b2];
                                if (i >= length) {
                                    break;
                                }
                                break;
                            }
                            return stringBuffer.toString().getBytes("iso8859-1");
                            i2 = i;
                        }
                        if (b2 == (byte) -1) {
                            break;
                        }
                        stringBuffer.append((char) (b2 | ((b3 & 3) << 6)));
                    } else {
                        break;
                    }
                }
                i = i2;
            }
            if (b3 == (byte) -1) {
                while (true) {
                    i = i2 + 1;
                    b = f1852b[bytes[i2]];
                    if (i >= length) {
                        break;
                    }
                    break;
                    i2 = i;
                }
                if (b == (byte) -1) {
                    break;
                }
                stringBuffer.append((char) ((b3 << 2) | ((b & 48) >>> 4)));
                while (true) {
                    i2 = i + 1;
                    b4 = bytes[i];
                    if (b4 != (byte) 61) {
                        b3 = f1852b[b4];
                        if (i2 >= length) {
                            break;
                        }
                        break;
                    }
                    return stringBuffer.toString().getBytes("iso8859-1");
                    i = i2;
                }
                if (b3 == (byte) -1) {
                    stringBuffer.append((char) (((b & 15) << 4) | ((b3 & 60) >>> 2)));
                    while (true) {
                        i = i2 + 1;
                        b2 = bytes[i2];
                        if (b2 == (byte) 61) {
                            b2 = f1852b[b2];
                            if (i >= length) {
                                break;
                            }
                            break;
                        }
                        return stringBuffer.toString().getBytes("iso8859-1");
                        i2 = i;
                    }
                    if (b2 == (byte) -1) {
                        break;
                    }
                    stringBuffer.append((char) (b2 | ((b3 & 3) << 6)));
                } else {
                    break;
                }
            }
            break;
        }
        return stringBuffer.toString().getBytes("iso8859-1");
    }
}

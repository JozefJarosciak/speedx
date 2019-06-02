package com.google.common.net;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.Ints;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

@Beta
public final class InetAddresses {
    private static final Inet4Address ANY4 = ((Inet4Address) forString("0.0.0.0"));
    private static final int IPV4_PART_COUNT = 4;
    private static final int IPV6_PART_COUNT = 8;
    private static final Inet4Address LOOPBACK4 = ((Inet4Address) forString("127.0.0.1"));

    @Beta
    public static final class TeredoInfo {
        private final Inet4Address client;
        private final int flags;
        private final int port;
        private final Inet4Address server;

        public TeredoInfo(Inet4Address inet4Address, Inet4Address inet4Address2, int i, int i2) {
            boolean z = i >= 0 && i <= 65535;
            Preconditions.checkArgument(z, "port '%s' is out of range (0 <= port <= 0xffff)", Integer.valueOf(i));
            if (i2 < 0 || i2 > 65535) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkArgument(z, "flags '%s' is out of range (0 <= flags <= 0xffff)", Integer.valueOf(i2));
            this.server = (Inet4Address) MoreObjects.firstNonNull(inet4Address, InetAddresses.ANY4);
            this.client = (Inet4Address) MoreObjects.firstNonNull(inet4Address2, InetAddresses.ANY4);
            this.port = i;
            this.flags = i2;
        }

        public Inet4Address getServer() {
            return this.server;
        }

        public Inet4Address getClient() {
            return this.client;
        }

        public int getPort() {
            return this.port;
        }

        public int getFlags() {
            return this.flags;
        }
    }

    private InetAddresses() {
    }

    private static Inet4Address getInet4Address(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 4, "Byte array has invalid length for an IPv4 address: %s != 4.", Integer.valueOf(bArr.length));
        return (Inet4Address) bytesToInetAddress(bArr);
    }

    public static InetAddress forString(String str) {
        byte[] ipStringToBytes = ipStringToBytes(str);
        if (ipStringToBytes != null) {
            return bytesToInetAddress(ipStringToBytes);
        }
        throw new IllegalArgumentException(String.format("'%s' is not an IP string literal.", new Object[]{str}));
    }

    public static boolean isInetAddress(String str) {
        return ipStringToBytes(str) != null;
    }

    private static byte[] ipStringToBytes(String str) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == CoreConstants.DOT) {
                i2 = 1;
            } else if (charAt == CoreConstants.COLON_CHAR) {
                if (i2 != 0) {
                    return null;
                }
                i3 = 1;
            } else if (Character.digit(charAt, 16) == -1) {
                return null;
            }
            i++;
        }
        if (i3 == 0) {
            return i2 != 0 ? textToNumericFormatV4(str) : null;
        } else {
            if (i2 != 0) {
                str = convertDottedQuadToHex(str);
                if (str == null) {
                    return null;
                }
            }
            return textToNumericFormatV6(str);
        }
    }

    private static byte[] textToNumericFormatV4(String str) {
        String[] split = str.split("\\.", 5);
        if (split.length != 4) {
            return null;
        }
        byte[] bArr = new byte[4];
        int i = 0;
        while (i < bArr.length) {
            try {
                bArr[i] = parseOctet(split[i]);
                i++;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return bArr;
    }

    private static byte[] textToNumericFormatV6(String str) {
        String[] split = str.split(":", 10);
        if (split.length < 3 || split.length > 9) {
            return null;
        }
        int i;
        int length;
        int i2 = -1;
        for (i = 1; i < split.length - 1; i++) {
            if (split[i].length() == 0) {
                if (i2 >= 0) {
                    return null;
                }
                i2 = i;
            }
        }
        if (i2 >= 0) {
            length = (split.length - i2) - 1;
            if (split[0].length() == 0) {
                i = i2 - 1;
                if (i != 0) {
                    return null;
                }
            }
            i = i2;
            if (split[split.length - 1].length() == 0) {
                length--;
                if (length != 0) {
                    return null;
                }
            }
            int i3 = length;
            length = i;
            i = i3;
        } else {
            length = split.length;
            i = 0;
        }
        int i4 = 8 - (length + i);
        if (!i2 < 0 ? i4 >= 1 : i4 == 0) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(16);
        int i5 = 0;
        while (i5 < length) {
            try {
                allocate.putShort(parseHextet(split[i5]));
                i5++;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        for (i5 = 0; i5 < i4; i5++) {
            allocate.putShort((short) 0);
        }
        while (i > 0) {
            allocate.putShort(parseHextet(split[split.length - i]));
            i--;
        }
        return allocate.array();
    }

    private static String convertDottedQuadToHex(String str) {
        int lastIndexOf = str.lastIndexOf(58);
        String substring = str.substring(0, lastIndexOf + 1);
        byte[] textToNumericFormatV4 = textToNumericFormatV4(str.substring(lastIndexOf + 1));
        if (textToNumericFormatV4 == null) {
            return null;
        }
        String toHexString = Integer.toHexString(((textToNumericFormatV4[0] & 255) << 8) | (textToNumericFormatV4[1] & 255));
        String toHexString2 = Integer.toHexString((textToNumericFormatV4[3] & 255) | ((textToNumericFormatV4[2] & 255) << 8));
        substring = String.valueOf(String.valueOf(substring));
        toHexString = String.valueOf(String.valueOf(toHexString));
        toHexString2 = String.valueOf(String.valueOf(toHexString2));
        return new StringBuilder(((substring.length() + 1) + toHexString.length()) + toHexString2.length()).append(substring).append(toHexString).append(":").append(toHexString2).toString();
    }

    private static byte parseOctet(String str) {
        int parseInt = Integer.parseInt(str);
        if (parseInt <= 255 && (!str.startsWith("0") || str.length() <= 1)) {
            return (byte) parseInt;
        }
        throw new NumberFormatException();
    }

    private static short parseHextet(String str) {
        int parseInt = Integer.parseInt(str, 16);
        if (parseInt <= 65535) {
            return (short) parseInt;
        }
        throw new NumberFormatException();
    }

    private static InetAddress bytesToInetAddress(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException e) {
            throw new AssertionError(e);
        }
    }

    public static String toAddrString(InetAddress inetAddress) {
        Preconditions.checkNotNull(inetAddress);
        if (inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
        }
        Preconditions.checkArgument(inetAddress instanceof Inet6Address);
        byte[] address = inetAddress.getAddress();
        int[] iArr = new int[8];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = Ints.fromBytes((byte) 0, (byte) 0, address[i * 2], address[(i * 2) + 1]);
        }
        compressLongestRunOfZeroes(iArr);
        return hextetsToIPv6String(iArr);
    }

    private static void compressLongestRunOfZeroes(int[] iArr) {
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        while (i < iArr.length + 1) {
            if (i >= iArr.length || iArr[i] != 0) {
                if (i2 >= 0) {
                    int i5 = i - i2;
                    if (i5 > i3) {
                        i3 = i5;
                    } else {
                        i2 = i4;
                    }
                    i4 = i2;
                    i2 = -1;
                }
            } else if (i2 < 0) {
                i2 = i;
            }
            i++;
        }
        if (i3 >= 2) {
            Arrays.fill(iArr, i4, i4 + i3, -1);
        }
    }

    private static String hextetsToIPv6String(int[] iArr) {
        StringBuilder stringBuilder = new StringBuilder(39);
        int i = 0;
        Object obj = null;
        while (i < iArr.length) {
            Object obj2;
            if (iArr[i] >= 0) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 != null) {
                if (obj != null) {
                    stringBuilder.append(CoreConstants.COLON_CHAR);
                }
                stringBuilder.append(Integer.toHexString(iArr[i]));
            } else if (i == 0 || obj != null) {
                stringBuilder.append("::");
            }
            i++;
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    public static String toUriString(InetAddress inetAddress) {
        if (!(inetAddress instanceof Inet6Address)) {
            return toAddrString(inetAddress);
        }
        String valueOf = String.valueOf(String.valueOf(toAddrString(inetAddress)));
        return new StringBuilder(valueOf.length() + 2).append("[").append(valueOf).append("]").toString();
    }

    public static InetAddress forUriString(String str) {
        String substring;
        Preconditions.checkNotNull(str);
        int i;
        if (str.startsWith("[") && str.endsWith("]")) {
            substring = str.substring(1, str.length() - 1);
            i = 16;
        } else {
            i = 4;
            substring = str;
        }
        byte[] ipStringToBytes = ipStringToBytes(substring);
        if (ipStringToBytes != null && ipStringToBytes.length == r0) {
            return bytesToInetAddress(ipStringToBytes);
        }
        throw new IllegalArgumentException(String.format("Not a valid URI IP literal: '%s'", new Object[]{str}));
    }

    public static boolean isUriInetAddress(String str) {
        try {
            forUriString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isCompatIPv4Address(Inet6Address inet6Address) {
        if (!inet6Address.isIPv4CompatibleAddress()) {
            return false;
        }
        byte[] address = inet6Address.getAddress();
        if (address[12] == (byte) 0 && address[13] == (byte) 0 && address[14] == (byte) 0 && (address[15] == (byte) 0 || address[15] == (byte) 1)) {
            return false;
        }
        return true;
    }

    public static Inet4Address getCompatIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isCompatIPv4Address(inet6Address), "Address '%s' is not IPv4-compatible.", toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static boolean is6to4Address(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        if (address[0] == (byte) 32 && address[1] == (byte) 2) {
            return true;
        }
        return false;
    }

    public static Inet4Address get6to4IPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(is6to4Address(inet6Address), "Address '%s' is not a 6to4 address.", toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 2, 6));
    }

    public static boolean isTeredoAddress(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        if (address[0] == (byte) 32 && address[1] == (byte) 1 && address[2] == (byte) 0 && address[3] == (byte) 0) {
            return true;
        }
        return false;
    }

    public static TeredoInfo getTeredoInfo(Inet6Address inet6Address) {
        int i = 0;
        Preconditions.checkArgument(isTeredoAddress(inet6Address), "Address '%s' is not a Teredo address.", toAddrString(inet6Address));
        byte[] address = inet6Address.getAddress();
        Inet4Address inet4Address = getInet4Address(Arrays.copyOfRange(address, 4, 8));
        int readShort = ByteStreams.newDataInput(address, 8).readShort() & 65535;
        int readShort2 = (ByteStreams.newDataInput(address, 10).readShort() ^ -1) & 65535;
        address = Arrays.copyOfRange(address, 12, 16);
        while (i < address.length) {
            address[i] = (byte) (address[i] ^ -1);
            i++;
        }
        return new TeredoInfo(inet4Address, getInet4Address(address), readShort2, readShort);
    }

    public static boolean isIsatapAddress(Inet6Address inet6Address) {
        if (isTeredoAddress(inet6Address)) {
            return false;
        }
        byte[] address = inet6Address.getAddress();
        if ((address[8] | 3) == 3 && address[9] == (byte) 0 && address[10] == (byte) 94 && address[11] == (byte) -2) {
            return true;
        }
        return false;
    }

    public static Inet4Address getIsatapIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isIsatapAddress(inet6Address), "Address '%s' is not an ISATAP address.", toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static boolean hasEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        return isCompatIPv4Address(inet6Address) || is6to4Address(inet6Address) || isTeredoAddress(inet6Address);
    }

    public static Inet4Address getEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        if (isCompatIPv4Address(inet6Address)) {
            return getCompatIPv4Address(inet6Address);
        }
        if (is6to4Address(inet6Address)) {
            return get6to4IPv4Address(inet6Address);
        }
        if (isTeredoAddress(inet6Address)) {
            return getTeredoInfo(inet6Address).getClient();
        }
        throw new IllegalArgumentException(String.format("'%s' has no embedded IPv4 address.", new Object[]{toAddrString(inet6Address)}));
    }

    public static boolean isMappedIPv4Address(String str) {
        int i = 10;
        byte[] ipStringToBytes = ipStringToBytes(str);
        if (ipStringToBytes == null || ipStringToBytes.length != 16) {
            return false;
        }
        for (int i2 = 0; i2 < 10; i2++) {
            if (ipStringToBytes[i2] != (byte) 0) {
                return false;
            }
        }
        while (i < 12) {
            if (ipStringToBytes[i] != (byte) -1) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static Inet4Address getCoercedIPv4Address(InetAddress inetAddress) {
        if (inetAddress instanceof Inet4Address) {
            return (Inet4Address) inetAddress;
        }
        int i;
        byte[] address = inetAddress.getAddress();
        for (i = 0; i < 15; i++) {
            if (address[i] != (byte) 0) {
                i = 0;
                break;
            }
        }
        i = 1;
        if (i != 0 && address[15] == (byte) 1) {
            return LOOPBACK4;
        }
        if (i != 0 && address[15] == (byte) 0) {
            return ANY4;
        }
        long hashCode;
        Inet6Address inet6Address = (Inet6Address) inetAddress;
        if (hasEmbeddedIPv4ClientAddress(inet6Address)) {
            hashCode = (long) getEmbeddedIPv4ClientAddress(inet6Address).hashCode();
        } else {
            hashCode = ByteBuffer.wrap(inet6Address.getAddress(), 0, 8).getLong();
        }
        i = Hashing.murmur3_32().hashLong(hashCode).asInt() | -536870912;
        if (i == -1) {
            i = -2;
        }
        return getInet4Address(Ints.toByteArray(i));
    }

    public static int coerceToInteger(InetAddress inetAddress) {
        return ByteStreams.newDataInput(getCoercedIPv4Address(inetAddress).getAddress()).readInt();
    }

    public static Inet4Address fromInteger(int i) {
        return getInet4Address(Ints.toByteArray(i));
    }

    public static InetAddress fromLittleEndianByteArray(byte[] bArr) throws UnknownHostException {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[(bArr.length - i) - 1];
        }
        return InetAddress.getByAddress(bArr2);
    }

    public static InetAddress decrement(InetAddress inetAddress) {
        boolean z;
        byte[] address = inetAddress.getAddress();
        int length = address.length - 1;
        while (length >= 0 && address[length] == (byte) 0) {
            address[length] = (byte) -1;
            length--;
        }
        if (length >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Decrementing %s would wrap.", inetAddress);
        address[length] = (byte) (address[length] - 1);
        return bytesToInetAddress(address);
    }

    public static InetAddress increment(InetAddress inetAddress) {
        boolean z;
        byte[] address = inetAddress.getAddress();
        int length = address.length - 1;
        while (length >= 0 && address[length] == (byte) -1) {
            address[length] = (byte) 0;
            length--;
        }
        if (length >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Incrementing %s would wrap.", inetAddress);
        address[length] = (byte) (address[length] + 1);
        return bytesToInetAddress(address);
    }

    public static boolean isMaximum(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        for (byte b : address) {
            if (b != (byte) -1) {
                return false;
            }
        }
        return true;
    }
}

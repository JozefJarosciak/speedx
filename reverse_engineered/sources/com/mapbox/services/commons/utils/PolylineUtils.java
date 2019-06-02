package com.mapbox.services.commons.utils;

import com.mapbox.services.commons.models.Position;
import java.util.ArrayList;
import java.util.List;

public class PolylineUtils {
    private static final boolean SIMPLIFY_DEFAULT_HIGHEST_QUALITY = false;
    private static final double SIMPLIFY_DEFAULT_TOLERANCE = 1.0d;

    public static List<Position> decode(String str, int i) {
        int length = str.length();
        double pow = Math.pow(10.0d, (double) i);
        List<Position> arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int i5 = 1;
            int i6 = 0;
            while (true) {
                int i7 = i2 + 1;
                i2 = (str.charAt(i2) - 63) - 1;
                i5 += i2 << i6;
                i6 += 5;
                if (i2 < 31) {
                    break;
                }
                i2 = i7;
            }
            i4 += (i5 & 1) != 0 ? (i5 >> 1) ^ -1 : i5 >> 1;
            i5 = 1;
            i6 = 0;
            while (true) {
                i2 = i7 + 1;
                i7 = (str.charAt(i7) - 63) - 1;
                i5 += i7 << i6;
                i6 += 5;
                if (i7 < 31) {
                    break;
                }
                i7 = i2;
            }
            i6 = ((i5 & 1) != 0 ? (i5 >> 1) ^ -1 : i5 >> 1) + i3;
            arrayList.add(Position.fromCoordinates(((double) i6) / pow, ((double) i4) / pow));
            i3 = i6;
        }
        return arrayList;
    }

    public static String encode(List<Position> list, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        double pow = Math.pow(10.0d, (double) i);
        long j = 0;
        long j2 = 0;
        for (Position position : list) {
            long round = Math.round(position.getLatitude() * pow);
            long round2 = Math.round(position.getLongitude() * pow);
            j = round2 - j;
            encode(round - j2, stringBuffer);
            encode(j, stringBuffer);
            j = round2;
            j2 = round;
        }
        return stringBuffer.toString();
    }

    private static void encode(long j, StringBuffer stringBuffer) {
        long j2 = j < 0 ? (j << 1) ^ -1 : j << 1;
        while (j2 >= 32) {
            stringBuffer.append(Character.toChars((int) (((31 & j2) | 32) + 63)));
            j2 >>= 5;
        }
        stringBuffer.append(Character.toChars((int) (j2 + 63)));
    }

    private static double getSqDist(Position position, Position position2) {
        double longitude = position.getLongitude() - position2.getLongitude();
        double latitude = position.getLatitude() - position2.getLatitude();
        return (longitude * longitude) + (latitude * latitude);
    }

    private static double getSqSegDist(Position position, Position position2, Position position3) {
        double longitude = position2.getLongitude();
        double latitude = position2.getLatitude();
        double longitude2 = position3.getLongitude() - longitude;
        double latitude2 = position3.getLatitude() - latitude;
        if (!(longitude2 == 0.0d && latitude2 == 0.0d)) {
            double longitude3 = (((position.getLongitude() - longitude) * longitude2) + ((position.getLatitude() - latitude) * latitude2)) / ((longitude2 * longitude2) + (latitude2 * latitude2));
            if (longitude3 > 1.0d) {
                longitude = position3.getLongitude();
                latitude = position3.getLatitude();
            } else if (longitude3 > 0.0d) {
                longitude += longitude2 * longitude3;
                latitude += latitude2 * longitude3;
            }
        }
        longitude = position.getLongitude() - longitude;
        latitude = position.getLatitude() - latitude;
        return (latitude * latitude) + (longitude * longitude);
    }

    private static Position[] simplifyRadialDist(Position[] positionArr, double d) {
        Position position = positionArr[0];
        ArrayList arrayList = new ArrayList();
        arrayList.add(position);
        Position position2 = null;
        int length = positionArr.length;
        Position position3 = position;
        for (int i = 1; i < length; i++) {
            position2 = positionArr[i];
            if (getSqDist(position2, position3) > d) {
                arrayList.add(position2);
                position3 = position2;
            }
        }
        if (position3 != position2) {
            arrayList.add(position2);
        }
        return (Position[]) arrayList.toArray(new Position[arrayList.size()]);
    }

    private static List<Position> simplifyDpStep(Position[] positionArr, int i, int i2, double d, List<Position> list) {
        int i3 = 0;
        List arrayList = new ArrayList();
        int i4 = i + 1;
        double d2 = d;
        while (i4 < i2) {
            double sqSegDist = getSqSegDist(positionArr[i4], positionArr[i], positionArr[i2]);
            if (sqSegDist > d2) {
                i3 = i4;
            } else {
                sqSegDist = d2;
            }
            i4++;
            d2 = sqSegDist;
        }
        if (d2 > d) {
            if (i3 - i > 1) {
                arrayList.addAll(simplifyDpStep(positionArr, i, i3, d, list));
            }
            arrayList.add(positionArr[i3]);
            if (i2 - i3 > 1) {
                arrayList.addAll(simplifyDpStep(positionArr, i3, i2, d, list));
            }
        }
        return arrayList;
    }

    private static Position[] simplifyDouglasPeucker(Position[] positionArr, double d) {
        int length = positionArr.length - 1;
        ArrayList arrayList = new ArrayList();
        arrayList.add(positionArr[0]);
        arrayList.addAll(simplifyDpStep(positionArr, 0, length, d, arrayList));
        arrayList.add(positionArr[length]);
        return (Position[]) arrayList.toArray(new Position[arrayList.size()]);
    }

    public static Position[] simplify(Position[] positionArr) {
        return simplify(positionArr, 1.0d, false);
    }

    public static Position[] simplify(Position[] positionArr, double d) {
        return simplify(positionArr, d, false);
    }

    public static Position[] simplify(Position[] positionArr, boolean z) {
        return simplify(positionArr, 1.0d, z);
    }

    public static Position[] simplify(Position[] positionArr, double d, boolean z) {
        if (positionArr.length <= 2) {
            return positionArr;
        }
        double d2 = d * d;
        if (!z) {
            positionArr = simplifyRadialDist(positionArr, d2);
        }
        return simplifyDouglasPeucker(positionArr, d2);
    }
}

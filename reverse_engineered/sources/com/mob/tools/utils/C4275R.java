package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.sys.C0869a;
import com.avos.avoscloud.AVException;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.mob.tools.utils.R */
public class C4275R {
    private static float density;
    private static int deviceWidth;
    private static Object rp;

    public static int dipToPx(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) * density) + 0.5f);
    }

    public static int pxToDip(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) / density) + 0.5f);
    }

    public static int designToDevice(Context context, int i, int i2) {
        if (deviceWidth == 0) {
            int[] screenSize = C4275R.getScreenSize(context);
            deviceWidth = screenSize[0] < screenSize[1] ? screenSize[0] : screenSize[1];
        }
        return (int) (((((float) i2) * ((float) deviceWidth)) / ((float) i)) + 0.5f);
    }

    public static int designToDevice(Context context, float f, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) (((((float) i) * density) / f) + 0.5f);
    }

    public static int[] getScreenSize(Context context) {
        WindowManager windowManager;
        try {
            windowManager = (WindowManager) context.getSystemService("window");
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            windowManager = null;
        }
        if (windowManager == null) {
            return new int[]{0, 0};
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            defaultDisplay.getMetrics(new DisplayMetrics());
            return new int[]{r1.widthPixels, r1.heightPixels};
        }
        try {
            Point point = new Point();
            Method method = defaultDisplay.getClass().getMethod("getRealSize", new Class[]{Point.class});
            method.setAccessible(true);
            method.invoke(defaultDisplay, new Object[]{point});
            return new int[]{point.x, point.y};
        } catch (Throwable th2) {
            MobLog.getInstance().m16946w(th2);
            return new int[]{0, 0};
        }
    }

    public static int getScreenWidth(Context context) {
        return C4275R.getScreenSize(context)[0];
    }

    public static int getScreenHeight(Context context) {
        return C4275R.getScreenSize(context)[1];
    }

    public static void setResourceProvider(Object obj) {
        try {
            if (obj.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class}) != null) {
                rp = obj;
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
        }
    }

    public static int getResId(Context context, String str, String str2) {
        int i = 0;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return i;
        }
        int intValue;
        String packageName;
        if (rp != null) {
            try {
                Method method = rp.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class});
                method.setAccessible(true);
                intValue = ((Integer) method.invoke(rp, new Object[]{context, str, str2})).intValue();
            } catch (Throwable th) {
                MobLog.getInstance().m16934d(th);
            }
            if (intValue <= 0) {
                return intValue;
            }
            packageName = context.getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                return intValue;
            }
            if (intValue <= 0) {
                intValue = context.getResources().getIdentifier(str2, str, packageName);
                if (intValue <= 0) {
                    intValue = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
                }
            }
            if (intValue <= 0) {
                return intValue;
            }
            System.err.println("failed to parse " + str + " resource \"" + str2 + "\"");
            return intValue;
        }
        intValue = i;
        if (intValue <= 0) {
            return intValue;
        }
        packageName = context.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            return intValue;
        }
        if (intValue <= 0) {
            intValue = context.getResources().getIdentifier(str2, str, packageName);
            if (intValue <= 0) {
                intValue = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
            }
        }
        if (intValue <= 0) {
            return intValue;
        }
        System.err.println("failed to parse " + str + " resource \"" + str2 + "\"");
        return intValue;
    }

    public static int getBitmapRes(Context context, String str) {
        return C4275R.getResId(context, "drawable", str);
    }

    public static int getStringRes(Context context, String str) {
        return C4275R.getResId(context, "string", str);
    }

    public static int getStringArrayRes(Context context, String str) {
        return C4275R.getResId(context, "array", str);
    }

    public static int getLayoutRes(Context context, String str) {
        return C4275R.getResId(context, "layout", str);
    }

    public static int getStyleRes(Context context, String str) {
        return C4275R.getResId(context, "style", str);
    }

    public static int getIdRes(Context context, String str) {
        return C4275R.getResId(context, "id", str);
    }

    public static int getColorRes(Context context, String str) {
        return C4275R.getResId(context, "color", str);
    }

    public static int getRawRes(Context context, String str) {
        return C4275R.getResId(context, "raw", str);
    }

    public static int getPluralsRes(Context context, String str) {
        return C4275R.getResId(context, "plurals", str);
    }

    public static int getAnimRes(Context context, String str) {
        return C4275R.getResId(context, "anim", str);
    }

    public static String getCacheRoot(Context context) {
        String str = context.getFilesDir().getAbsolutePath() + "/Mob/";
        DeviceHelper instance = DeviceHelper.getInstance(context);
        if (instance.getSdcardState()) {
            str = instance.getSdcardPath() + "/Mob/";
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String getCachePath(Context context, String str) {
        String str2 = context.getFilesDir().getAbsolutePath() + "/Mob/cache/";
        DeviceHelper instance = DeviceHelper.getInstance(context);
        try {
            if (instance.getSdcardState()) {
                str2 = instance.getSdcardPath() + "/Mob/" + instance.getPackageName() + "/cache/";
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + str + "/";
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String getImageCachePath(Context context) {
        return C4275R.getCachePath(context, "images");
    }

    public static void clearCache(Context context) throws Throwable {
        C4275R.deleteFileAndFolder(new File(C4275R.getCachePath(context, null)));
    }

    public static void deleteFilesInFolder(File file) throws Throwable {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String file2 : list) {
                    File file3 = new File(file, file2);
                    if (file3.isDirectory()) {
                        C4275R.deleteFilesInFolder(file3);
                    } else {
                        file3.delete();
                    }
                }
            }
        }
    }

    public static void deleteFileAndFolder(File file) throws Throwable {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null || list.length <= 0) {
                file.delete();
                return;
            }
            for (String file2 : list) {
                File file3 = new File(file, file2);
                if (file3.isDirectory()) {
                    C4275R.deleteFileAndFolder(file3);
                } else {
                    file3.delete();
                }
            }
            file.delete();
        }
    }

    public static String toWordText(String str, int i) {
        char[] toCharArray = str.toCharArray();
        int i2 = i * 2;
        StringBuilder stringBuilder = new StringBuilder();
        int i3 = i2;
        for (char c : toCharArray) {
            i3 -= c < 'Ā' ? 1 : 2;
            if (i3 < 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static int getTextLengthInWord(String str) {
        int i = 0;
        char[] toCharArray = str == null ? new char[0] : str.toCharArray();
        int i2 = 0;
        while (i < toCharArray.length) {
            i2 += toCharArray[i] < 'Ā' ? 1 : 2;
            i++;
        }
        return i2;
    }

    public static long strToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0)).getTime();
    }

    public static long dateStrToLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str, new ParsePosition(0)).getTime();
    }

    public static Date longToDate(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.getTime();
    }

    public static String longToTime(long j, int i) {
        String str = "yyyy-MM-dd kk:mm:ss";
        switch (i) {
            case 1:
                str = "yyyy";
                break;
            case 2:
                str = "yyyy-MM";
                break;
            case 5:
                str = "yyyy-MM-dd";
                break;
            case 10:
                str = "yyyy-MM-dd kk";
                break;
            case 12:
                str = "yyyy-MM-dd kk:mm";
                break;
        }
        return new SimpleDateFormat(str).format(Long.valueOf(j));
    }

    public static long dateToLong(String str) {
        try {
            Date date = new Date(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return instance.getTimeInMillis();
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            return 0;
        }
    }

    public static int[] covertTimeInYears(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis <= 0) {
            return new int[]{0, 0};
        }
        long j2 = currentTimeMillis / 1000;
        if (j2 < 60) {
            return new int[]{(int) j2, 0};
        }
        j2 /= 60;
        if (j2 < 60) {
            return new int[]{(int) j2, 1};
        }
        j2 /= 60;
        if (j2 < 24) {
            return new int[]{(int) j2, 2};
        }
        j2 /= 24;
        if (j2 < 30) {
            return new int[]{(int) j2, 3};
        }
        if (j2 / 30 < 12) {
            return new int[]{(int) (j2 / 30), 4};
        }
        return new int[]{(int) ((j2 / 30) / 12), 5};
    }

    public static Uri pathToContentUri(Context context, String str) {
        Cursor query = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{FieldType.FOREIGN_ID_FIELD_SUFFIX}, "_data=? ", new String[]{str}, null);
        if (query != null && query.moveToFirst()) {
            return Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + query.getInt(query.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX)));
        } else if (!new File(str).exists()) {
            return null;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", str);
            return context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
        }
    }

    public static String contentUriToPath(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        if (new File(uri.getPath()).exists()) {
            return uri.getPath();
        }
        String str;
        try {
            Cursor query;
            Cursor query2;
            if (VERSION.SDK_INT >= 19) {
                Class cls = Class.forName("android.provider.DocumentsContract");
                Method method = cls.getMethod("isDocumentUri", new Class[]{Context.class, Uri.class});
                method.setAccessible(true);
                if (Boolean.TRUE.equals(method.invoke(null, new Object[]{context, uri}))) {
                    Method method2 = cls.getMethod("getDocumentId", new Class[]{Uri.class});
                    method2.setAccessible(true);
                    str = String.valueOf(method2.invoke(null, new Object[]{uri})).split(":")[1];
                    String[] strArr = new String[]{"_data"};
                    String[] strArr2 = new String[]{str};
                    query = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, strArr, "_id=?", strArr2, null);
                    if (query != null) {
                        query2 = context.getContentResolver().query(uri, null, null, null, null);
                    } else {
                        query2 = query;
                    }
                    if (query2 == null) {
                        if (query2.moveToFirst()) {
                            str = null;
                        } else {
                            str = query2.getString(query2.getColumnIndex("_data"));
                        }
                        query2.close();
                    } else {
                        str = null;
                    }
                    return str;
                }
            }
            query = null;
            if (query != null) {
                query2 = query;
            } else {
                query2 = context.getContentResolver().query(uri, null, null, null, null);
            }
            if (query2 == null) {
                str = null;
            } else {
                if (query2.moveToFirst()) {
                    str = null;
                } else {
                    str = query2.getString(query2.getColumnIndex("_data"));
                }
                query2.close();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            str = null;
        }
        return str;
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : bundle.keySet()) {
            Object obj2 = bundle.get(str);
            if (obj2 == null) {
                obj2 = "";
            }
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(C0869a.f2161b);
            }
            stringBuilder.append(Data.urlEncode(str) + SimpleComparison.EQUAL_TO_OPERATION + Data.urlEncode(String.valueOf(obj2)));
        }
        return stringBuilder.toString();
    }

    public static String encodeUrl(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            if (i > 0) {
                stringBuilder.append('&');
            }
            String str = kVPair.name;
            String str2 = (String) kVPair.value;
            if (str != null) {
                if (str2 == null) {
                    str2 = "";
                }
                stringBuilder.append(Data.urlEncode(str) + SimpleComparison.EQUAL_TO_OPERATION + Data.urlEncode(str2));
                i++;
            }
        }
        return stringBuilder.toString();
    }

    public static Bundle urlToBundle(String str) {
        String str2;
        int indexOf = str.indexOf("://");
        if (indexOf >= 0) {
            str2 = "http://" + str.substring(indexOf + 1);
        } else {
            str2 = "http://" + str;
        }
        try {
            URL url = new URL(str2);
            Bundle decodeUrl = C4275R.decodeUrl(url.getQuery());
            decodeUrl.putAll(C4275R.decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            return new Bundle();
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split(C0869a.f2161b)) {
                String[] split2 = split.split(SimpleComparison.EQUAL_TO_OPERATION);
                if (split2.length < 2 || split2[1] == null) {
                    bundle.putString(URLDecoder.decode(split2[0]), "");
                } else {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
        }
        return bundle;
    }

    public static int parseInt(String str) throws Throwable {
        return C4275R.parseInt(str, 10);
    }

    public static int parseInt(String str, int i) throws Throwable {
        int i2 = 1;
        if (i < 2 || i > 36) {
            throw new Throwable("Invalid radix: " + i);
        } else if (str == null) {
            throw C4275R.invalidInt(str);
        } else {
            int length = str.length();
            if (length == 0) {
                throw C4275R.invalidInt(str);
            }
            boolean z;
            if (str.charAt(0) == CoreConstants.DASH_CHAR) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                i2 = 0;
            } else if (1 == length) {
                throw C4275R.invalidInt(str);
            }
            return C4275R.parseInt(str, i2, i, z);
        }
    }

    private static int parseInt(String str, int i, int i2, boolean z) throws Throwable {
        int i3 = Integer.MIN_VALUE / i2;
        int i4 = 0;
        int length = str.length();
        while (i < length) {
            int i5 = i + 1;
            int digit = C4275R.digit(str.charAt(i), i2);
            if (digit == -1) {
                throw C4275R.invalidInt(str);
            } else if (i3 > i4) {
                throw C4275R.invalidInt(str);
            } else {
                digit = (i4 * i2) - digit;
                if (digit > i4) {
                    throw C4275R.invalidInt(str);
                }
                i4 = digit;
                i = i5;
            }
        }
        if (!z) {
            i4 = -i4;
            if (i4 < 0) {
                throw C4275R.invalidInt(str);
            }
        }
        return i4;
    }

    private static int digit(int i, int i2) {
        if (i2 < 2 || i2 > 36) {
            return -1;
        }
        int i3 = (48 > i || i > 57) ? (97 > i || i > AVException.INVALID_FILE_NAME) ? (65 > i || i > 90) ? -1 : (i - 65) + 10 : (i - 97) + 10 : i - 48;
        if (i3 >= i2) {
            return -1;
        }
        return i3;
    }

    private static Throwable invalidInt(String str) throws Throwable {
        throw new Throwable("Invalid int: \"" + str + "\"");
    }

    public static long parseLong(String str) throws Throwable {
        return C4275R.parseLong(str, 10);
    }

    public static long parseLong(String str, int i) throws Throwable {
        int i2 = 1;
        if (i < 2 || i > 36) {
            throw new Throwable("Invalid radix: " + i);
        } else if (str == null) {
            throw new Throwable("Invalid long: \"" + str + "\"");
        } else {
            int length = str.length();
            if (length == 0) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            }
            boolean z;
            if (str.charAt(0) == CoreConstants.DASH_CHAR) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                i2 = 0;
            } else if (1 == length) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            }
            return C4275R.parseLong(str, i2, i, z);
        }
    }

    private static long parseLong(String str, int i, int i2, boolean z) throws Throwable {
        long j = Long.MIN_VALUE / ((long) i2);
        long j2 = 0;
        long length = (long) str.length();
        while (((long) i) < length) {
            int i3 = i + 1;
            int digit = C4275R.digit(str.charAt(i), i2);
            if (digit == -1) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            } else if (j > j2) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            } else {
                long j3 = (((long) i2) * j2) - ((long) digit);
                if (j3 > j2) {
                    throw new Throwable("Invalid long: \"" + str + "\"");
                }
                j2 = j3;
                i = i3;
            }
        }
        if (!z) {
            j2 = -j2;
            if (j2 < 0) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            }
        }
        return j2;
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static <T> T forceCast(Object obj) {
        return C4275R.forceCast(obj, null);
    }

    public static <T> T forceCast(Object obj, T t) {
        boolean z = true;
        if (obj == null) {
            return t;
        }
        if (obj instanceof Byte) {
            byte byteValue = ((Byte) obj).byteValue();
            if (t instanceof Boolean) {
                return Boolean.valueOf(byteValue != (byte) 0);
            } else if (t instanceof Short) {
                return Short.valueOf((short) byteValue);
            } else {
                if (t instanceof Character) {
                    return Character.valueOf((char) byteValue);
                }
                if (t instanceof Integer) {
                    return Integer.valueOf(byteValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) byteValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) byteValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) byteValue);
                }
            }
        } else if (obj instanceof Character) {
            char charValue = ((Character) obj).charValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) charValue);
            }
            if (t instanceof Boolean) {
                if (charValue == '\u0000') {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Short) {
                return Short.valueOf((short) charValue);
            } else {
                if (t instanceof Integer) {
                    return Integer.valueOf(charValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) charValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) charValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) charValue);
                }
            }
        } else if (obj instanceof Short) {
            short shortValue = ((Short) obj).shortValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) shortValue);
            }
            if (t instanceof Boolean) {
                if (shortValue == (short) 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) shortValue);
            } else {
                if (t instanceof Integer) {
                    return Integer.valueOf(shortValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) shortValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) shortValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) shortValue);
                }
            }
        } else if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) intValue);
            }
            if (t instanceof Boolean) {
                if (intValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) intValue);
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) intValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) intValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) intValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) intValue);
                }
            }
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) floatValue));
            }
            if (t instanceof Boolean) {
                if (floatValue == 0.0f) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) floatValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) floatValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) floatValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) floatValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) floatValue);
                }
            }
        } else if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) longValue));
            }
            if (t instanceof Boolean) {
                if (longValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) longValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) longValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) longValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) longValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) longValue);
                }
            }
        } else if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) doubleValue));
            }
            if (t instanceof Boolean) {
                if (doubleValue == 0.0d) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) doubleValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) doubleValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) doubleValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) doubleValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) doubleValue);
                }
            }
        }
        return obj;
    }

    public static boolean copyFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !new File(str).exists()) {
            return false;
        }
        try {
            C4275R.copyFile(new FileInputStream(str), new FileOutputStream(str2));
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws Throwable {
        byte[] bArr = new byte[65536];
        int read = fileInputStream.read(bArr);
        while (read > 0) {
            fileOutputStream.write(bArr, 0, read);
            read = fileInputStream.read(bArr);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static long getFileSize(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return C4275R.getFileSize(new File(str));
    }

    public static long getFileSize(File file) throws Throwable {
        int i = 0;
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        String[] list = file.list();
        int i2 = 0;
        while (i < list.length) {
            i2 = (int) (((long) i2) + C4275R.getFileSize(new File(file, list[i])));
            i++;
        }
        return (long) i2;
    }

    public static boolean saveObjectToFile(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            File file;
            try {
                file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } catch (Throwable th) {
                th.printStackTrace();
                file = null;
            }
            if (file != null) {
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    return true;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
        return false;
    }

    public static Object readObjectFromFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file;
        try {
            file = new File(str);
            if (!file.exists()) {
                file = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            file = null;
        }
        if (file == null) {
            return null;
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }
}

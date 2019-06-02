package com.beastbikes.android.utils;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: Utils */
public class aa {
    /* renamed from: a */
    public static boolean m12772a(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = new int[]{0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int height = view.getHeight() + i2;
        int width = view.getWidth() + i;
        if (motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) width) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) height)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static long m12769a() {
        long j = 0;
        try {
            Date date = new Date();
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            j = instance.getTimeInMillis();
        } catch (Exception e) {
        }
        return j;
    }

    /* renamed from: a */
    public static int m12768a(String str) {
        if (!TextUtils.isEmpty(str) && Pattern.compile("[0-9]*").matcher(str).matches()) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    /* renamed from: a */
    public static Calendar m12771a(int i, int i2, int i3) {
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(i + HelpFormatter.DEFAULT_OPT_PREFIX + (i2 + 1) + HelpFormatter.DEFAULT_OPT_PREFIX + i3);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            return instance;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m12774a(short s) {
        return new byte[]{(byte) (s >>> 8), (byte) s};
    }

    /* renamed from: a */
    public static byte[] m12773a(int i) {
        return new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
    }

    /* renamed from: a */
    public static String m12770a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append("0x");
            stringBuilder.append(String.format("%x", new Object[]{Byte.valueOf(bArr[i])}));
            if (i == bArr.length - 1) {
                stringBuilder.append("]");
            } else {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static short m12775b(byte[] bArr) {
        return (short) (((short) (bArr[0] & 255)) | ((short) (((short) (bArr[1] & 255)) << 8)));
    }
}

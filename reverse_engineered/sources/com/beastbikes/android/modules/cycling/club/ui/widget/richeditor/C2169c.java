package com.beastbikes.android.modules.cycling.club.ui.widget.richeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: Utils */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.c */
public final class C2169c {
    /* renamed from: a */
    public static String m11125a(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    /* renamed from: a */
    public static Bitmap m11124a(Drawable drawable) {
        int i = 1;
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m11123a(Context context, int i) {
        return BitmapFactory.decodeResource(context.getResources(), i);
    }

    /* renamed from: a */
    public static String m11126a(String str) {
        if (str == null) {
            return null;
        }
        String str2 = "";
        try {
            str2 = Pattern.compile("<[^>]+>", 2).matcher(Pattern.compile("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", 2).matcher(Pattern.compile("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", 2).matcher(str).replaceAll("")).replaceAll("")).replaceAll("");
            return str2.replace("&nbsp;", "");
        } catch (Exception e) {
            Exception exception = e;
            String str3 = str2;
            exception.printStackTrace();
            return str3;
        }
    }

    /* renamed from: b */
    public static ArrayList<String> m11127b(String str) {
        ArrayList<String> arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", 2).matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            group = (group == null || group.trim().length() == 0) ? matcher.group(2).split("\\s+")[0] : matcher.group(2);
            if (new File(group).exists()) {
                arrayList.add(group);
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public static int m11128c(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", 2).matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            Object group2 = (group == null || group.trim().length() == 0) ? matcher.group(2).split("\\s+")[0] : matcher.group(2);
            arrayList.add(group2);
        }
        return arrayList.size();
    }
}

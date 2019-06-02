package vi.com.gdi.bgl.android.java;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import com.baidu.mapapi.common.SysOSUtil;

public class EnvDrawText {
    /* renamed from: a */
    private static final String f19127a = EnvDrawText.class.getSimpleName();
    public static boolean bBmpChange = false;
    public static Bitmap bmp = null;
    public static int[] buffer = null;
    public static Canvas canvasTemp = null;
    public static SparseArray<C5962a> fontCache = null;
    public static int iWordHightMax = 0;
    public static int iWordWidthMax = 0;
    public static Paint pt = null;

    public static synchronized int[] drawText(String str, int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) {
        int[] iArr2;
        synchronized (EnvDrawText.class) {
            int i7;
            if (pt == null) {
                pt = new Paint();
            } else {
                pt.reset();
            }
            String phoneType = SysOSUtil.getPhoneType();
            if (phoneType != null && phoneType.equals("vivo X3L")) {
                i2 = 0;
            }
            switch (i2) {
                case 1:
                    pt.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                    break;
                case 2:
                    pt.setTypeface(Typeface.create(Typeface.DEFAULT, 2));
                    break;
                default:
                    pt.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                    break;
            }
            pt.setSubpixelText(true);
            pt.setAntiAlias(true);
            if (!(i2 == 0 || fontCache == null)) {
                C5962a c5962a = (C5962a) fontCache.get(i2);
                if (c5962a != null) {
                    pt.setTypeface(c5962a.f19128a);
                }
            }
            pt.setTextSize((float) i);
            int indexOf = str.indexOf(92, 0);
            int ceil;
            if (indexOf == -1) {
                FontMetrics fontMetrics = pt.getFontMetrics();
                indexOf = (int) pt.measureText(str);
                ceil = (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
                iArr[0] = indexOf;
                iArr[1] = ceil;
                indexOf = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log((double) indexOf) / Math.log(2.0d))));
                ceil = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log((double) ceil) / Math.log(2.0d))));
                if (iWordWidthMax < indexOf || iWordHightMax < ceil) {
                    bBmpChange = true;
                    iWordWidthMax = indexOf;
                    iWordHightMax = ceil;
                }
                iArr[2] = iWordWidthMax;
                iArr[3] = iWordHightMax;
                if (bBmpChange) {
                    if (!(bmp == null || bmp.isRecycled())) {
                        bmp.recycle();
                    }
                    bmp = Bitmap.createBitmap(iWordWidthMax, iWordHightMax, Config.ARGB_8888);
                    if (canvasTemp == null) {
                        canvasTemp = new Canvas();
                    }
                    canvasTemp.setBitmap(bmp);
                } else {
                    bmp.eraseColor(0);
                }
                if ((ViewCompat.MEASURED_STATE_MASK & i5) == 0) {
                    canvasTemp.drawColor(33554431);
                } else {
                    canvasTemp.drawColor(i5);
                }
                if (i6 != 0) {
                    pt.setStrokeWidth((float) i6);
                    pt.setStrokeCap(Cap.ROUND);
                    pt.setStrokeJoin(Join.ROUND);
                    pt.setStyle(Style.STROKE);
                    pt.setColor(i4);
                    canvasTemp.drawText(str, 0.0f, 0.0f - fontMetrics.ascent, pt);
                }
                pt.setStyle(Style.FILL);
                pt.setColor(i3);
                canvasTemp.drawText(str, 0.0f, 0.0f - fontMetrics.ascent, pt);
            } else {
                ceil = indexOf + 1;
                indexOf = (int) pt.measureText(str.substring(0, indexOf));
                i7 = ceil;
                ceil = 2;
                while (true) {
                    int indexOf2 = str.indexOf(92, i7);
                    if (indexOf2 > 0) {
                        i7 = (int) pt.measureText(str.substring(i7, indexOf2));
                        if (i7 <= indexOf) {
                            i7 = indexOf;
                        }
                        ceil++;
                        indexOf = i7;
                        i7 = indexOf2 + 1;
                    } else {
                        if (i7 != str.length()) {
                            i7 = (int) pt.measureText(str.substring(i7, str.length()));
                            if (i7 > indexOf) {
                                indexOf = i7;
                            }
                        }
                        FontMetrics fontMetrics2 = pt.getFontMetrics();
                        int ceil2 = (int) Math.ceil((double) (fontMetrics2.descent - fontMetrics2.ascent));
                        i7 = ceil2 * ceil;
                        iArr[0] = indexOf;
                        iArr[1] = i7;
                        indexOf = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log((double) indexOf) / Math.log(2.0d))));
                        i7 = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log((double) i7) / Math.log(2.0d))));
                        if (iWordWidthMax < indexOf || iWordHightMax < i7) {
                            bBmpChange = true;
                            iWordWidthMax = indexOf;
                            iWordHightMax = i7;
                        }
                        iArr[2] = iWordWidthMax;
                        iArr[3] = iWordHightMax;
                        if (bBmpChange) {
                            if (!(bmp == null || bmp.isRecycled())) {
                                bmp.recycle();
                            }
                            bmp = Bitmap.createBitmap(iWordWidthMax, iWordHightMax, Config.ARGB_8888);
                            if (canvasTemp == null) {
                                canvasTemp = new Canvas();
                            }
                            canvasTemp.setBitmap(bmp);
                        } else {
                            bmp.eraseColor(0);
                        }
                        if ((ViewCompat.MEASURED_STATE_MASK & i5) == 0) {
                            canvasTemp.drawColor(33554431);
                        } else {
                            canvasTemp.drawColor(i5);
                        }
                        indexOf = 0;
                        i7 = 0;
                        while (true) {
                            ceil = str.indexOf(92, indexOf);
                            if (ceil > 0) {
                                String substring = str.substring(indexOf, ceil);
                                int measureText = (int) pt.measureText(substring);
                                indexOf = ceil + 1;
                                if (i6 != 0) {
                                    pt.setStrokeWidth((float) i6);
                                    pt.setStrokeCap(Cap.ROUND);
                                    pt.setStrokeJoin(Join.ROUND);
                                    pt.setStyle(Style.STROKE);
                                    pt.setColor(i4);
                                    canvasTemp.drawText(substring, (float) ((iArr[0] - measureText) / 2), ((float) (i7 * ceil2)) - fontMetrics2.ascent, pt);
                                }
                                pt.setStyle(Style.FILL);
                                pt.setColor(i3);
                                canvasTemp.drawText(substring, (float) ((iArr[0] - measureText) / 2), ((float) (i7 * ceil2)) - fontMetrics2.ascent, pt);
                                i7++;
                            } else if (indexOf != str.length()) {
                                String substring2 = str.substring(indexOf, str.length());
                                ceil = (int) pt.measureText(substring2);
                                if (i6 != 0) {
                                    pt.setStrokeWidth((float) i6);
                                    pt.setStrokeCap(Cap.ROUND);
                                    pt.setStrokeJoin(Join.ROUND);
                                    pt.setStyle(Style.STROKE);
                                    pt.setColor(i4);
                                    canvasTemp.drawText(substring2, (float) ((iArr[0] - ceil) / 2), ((float) (i7 * ceil2)) - fontMetrics2.ascent, pt);
                                }
                                pt.setStyle(Style.FILL);
                                pt.setColor(i3);
                                canvasTemp.drawText(substring2, (float) ((iArr[0] - ceil) / 2), ((float) (i7 * ceil2)) - fontMetrics2.ascent, pt);
                            }
                        }
                    }
                }
            }
            i7 = iWordWidthMax * iWordHightMax;
            if (bBmpChange) {
                buffer = new int[i7];
            }
            bmp.getPixels(buffer, 0, iWordWidthMax, 0, 0, iWordWidthMax, iWordHightMax);
            bBmpChange = false;
            iArr2 = buffer;
        }
        return iArr2;
    }

    public static short[] getTextSize(String str, int i, int i2) {
        int length = str.length();
        if (length == 0) {
            return null;
        }
        Paint paint = new Paint();
        paint.setSubpixelText(true);
        paint.setAntiAlias(true);
        paint.setTextSize((float) i);
        switch (i2) {
            case 1:
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                break;
            case 2:
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, 2));
                break;
            default:
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                break;
        }
        short[] sArr = new short[length];
        for (int i3 = 0; i3 < length; i3++) {
            sArr[i3] = (short) ((int) paint.measureText(str.substring(0, i3 + 1)));
        }
        return sArr;
    }

    public static synchronized void registFontCache(int i, Typeface typeface) {
        synchronized (EnvDrawText.class) {
            if (!(i == 0 || typeface == null)) {
                if (fontCache == null) {
                    fontCache = new SparseArray();
                }
                C5962a c5962a = (C5962a) fontCache.get(i);
                if (c5962a == null) {
                    c5962a = new C5962a();
                    c5962a.f19128a = typeface;
                    c5962a.f19129b++;
                    fontCache.put(i, c5962a);
                } else {
                    c5962a.f19129b++;
                }
            }
        }
    }

    public static synchronized void removeFontCache(int i) {
        synchronized (EnvDrawText.class) {
            C5962a c5962a = (C5962a) fontCache.get(i);
            if (c5962a != null) {
                c5962a.f19129b--;
                if (c5962a.f19129b == 0) {
                    fontCache.remove(i);
                }
            }
        }
    }
}

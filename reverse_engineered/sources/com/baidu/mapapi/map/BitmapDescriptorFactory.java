package com.baidu.mapapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.view.View.MeasureSpec;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.platform.comapi.commonutils.C1221a;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitmapDescriptorFactory {
    /* renamed from: a */
    static final /* synthetic */ boolean f2882a = (!BitmapDescriptorFactory.class.desiredAssertionStatus());

    public static BitmapDescriptor fromAsset(String str) {
        Context context = BMapManager.getContext();
        if (context == null) {
            return null;
        }
        try {
            Bitmap a = C1221a.m4570a(str, context);
            BitmapDescriptor fromBitmap = fromBitmap(a);
            if (f2882a || a != null) {
                a.recycle();
                return fromBitmap;
            }
            throw new AssertionError();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BitmapDescriptor fromAssetWithDpi(String str) {
        Context context = BMapManager.getContext();
        if (context == null) {
            return null;
        }
        try {
            Bitmap a = C1221a.m4570a(str, context);
            if (a == null) {
                return null;
            }
            Bitmap createBitmap;
            BitmapDescriptor fromBitmap;
            int densityDpi = SysOSUtil.getDensityDpi();
            Matrix matrix;
            if (densityDpi > 480) {
                matrix = new Matrix();
                matrix.postScale(2.0f, 2.0f);
                createBitmap = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
                fromBitmap = fromBitmap(createBitmap);
            } else if (densityDpi <= 320 || densityDpi > 480) {
                fromBitmap = fromBitmap(a);
                createBitmap = null;
            } else {
                matrix = new Matrix();
                matrix.postScale(1.5f, 1.5f);
                createBitmap = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
                fromBitmap = fromBitmap(createBitmap);
            }
            a.recycle();
            if (createBitmap != null) {
                createBitmap.recycle();
            }
            return fromBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDescriptor(bitmap);
    }

    public static BitmapDescriptor fromFile(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        try {
            Context context = BMapManager.getContext();
            if (context != null) {
                InputStream openFileInput = context.openFileInput(str);
                Bitmap decodeStream = BitmapFactory.decodeStream(openFileInput);
                openFileInput.close();
                if (decodeStream != null) {
                    BitmapDescriptor fromBitmap = fromBitmap(decodeStream);
                    decodeStream.recycle();
                    return fromBitmap;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static BitmapDescriptor fromPath(String str) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        if (decodeFile == null || decodeFile == null) {
            return null;
        }
        BitmapDescriptor fromBitmap = fromBitmap(decodeFile);
        decodeFile.recycle();
        return fromBitmap;
    }

    public static BitmapDescriptor fromResource(int i) {
        Context context = BMapManager.getContext();
        if (context == null) {
            return null;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        if (decodeResource == null) {
            return null;
        }
        BitmapDescriptor fromBitmap = fromBitmap(decodeResource);
        decodeResource.recycle();
        return fromBitmap;
    }

    public static BitmapDescriptor fromView(View view) {
        if (view == null) {
            return null;
        }
        view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        BitmapDescriptor fromBitmap = fromBitmap(drawingCache);
        if (drawingCache != null) {
            drawingCache.recycle();
        }
        view.destroyDrawingCache();
        return fromBitmap;
    }
}

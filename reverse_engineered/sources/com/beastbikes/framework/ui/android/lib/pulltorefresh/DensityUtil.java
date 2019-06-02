package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

public class DensityUtil {
    private static int STATUS_HEIGHT = 0;
    private static Screen screen = null;

    public static class Screen {
        public int heightPixels;
        public int widthPixels;

        public Screen(int i, int i2) {
            this.widthPixels = i;
            this.heightPixels = i2;
        }

        public String toString() {
            return "(" + this.widthPixels + "," + this.heightPixels + ")";
        }
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px(float f, Context context) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int px2dip(float f, Context context) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(float f, Context context) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f, Context context) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int getWidth(Context context) {
        initScreen(context);
        if (screen != null) {
            return screen.widthPixels;
        }
        return 0;
    }

    public static int getHeight(Context context) {
        initScreen(context);
        if (screen != null) {
            return screen.heightPixels;
        }
        return 0;
    }

    private static void initScreen(Context context) {
        if (screen == null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            screen = new Screen(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
    }

    public static final void setTextSize(TextView textView, int i) {
    }

    public static int getStatusHeight(Context context) {
        if (STATUS_HEIGHT <= 0) {
            try {
                int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (identifier > 0) {
                    STATUS_HEIGHT = context.getResources().getDimensionPixelSize(identifier);
                }
            } catch (Exception e) {
            }
        }
        return STATUS_HEIGHT;
    }
}

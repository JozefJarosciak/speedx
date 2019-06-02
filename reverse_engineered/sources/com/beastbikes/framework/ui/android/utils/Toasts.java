package com.beastbikes.framework.ui.android.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.Toast;

public final class Toasts {
    private static Toast instance;

    private Toasts() {
    }

    public static void show(Context context, int i, int i2) {
        show(context, context.getString(i), i2);
    }

    public static synchronized void show(Context context, CharSequence charSequence, int i) {
        synchronized (Toasts.class) {
            if (instance == null) {
                instance = Toast.makeText(context, charSequence, i);
            } else {
                instance.setText(charSequence);
            }
            instance.show();
        }
    }

    public static void show(Context context, int i) {
        show(context, i, 0);
    }

    public static void show(Context context, CharSequence charSequence) {
        show(context, charSequence, 0);
    }

    public static void showOnUiThread(final Activity activity, final int i) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toasts.show(activity, i);
                }
            });
        }
    }

    public static void showOnUiThreadWithResId(final Context context, final int i) {
        if (context != null) {
            new Handler(context.getMainLooper(), new Callback() {
                public boolean handleMessage(Message message) {
                    Toasts.show(context, i);
                    return false;
                }
            }).sendEmptyMessage(0);
        }
    }

    public static void showOnUiThreadWithText(final Context context, final String str) {
        if (context != null) {
            new Handler(context.getMainLooper(), new Callback() {
                public boolean handleMessage(Message message) {
                    Toasts.show(context, str);
                    return false;
                }
            }).sendEmptyMessage(0);
        }
    }

    public static void showOnUiThread(final Activity activity, final String str) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toasts.show(activity, str);
                }
            });
        }
    }

    public static void show(final Activity activity, final Character ch) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toasts.show(activity, ch);
                }
            });
        }
    }
}

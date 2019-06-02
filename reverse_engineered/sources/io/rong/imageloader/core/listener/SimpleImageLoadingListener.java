package io.rong.imageloader.core.listener;

import android.graphics.Bitmap;
import android.view.View;
import io.rong.imageloader.core.assist.FailReason;

public class SimpleImageLoadingListener implements ImageLoadingListener {
    public void onLoadingStarted(String str, View view) {
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
    }

    public void onLoadingCancelled(String str, View view) {
    }
}

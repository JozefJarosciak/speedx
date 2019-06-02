package io.rong.imageloader.core.listener;

import android.graphics.Bitmap;
import android.view.View;
import io.rong.imageloader.core.assist.FailReason;

public interface ImageLoadingListener {
    void onLoadingCancelled(String str, View view);

    void onLoadingComplete(String str, View view, Bitmap bitmap);

    void onLoadingFailed(String str, View view, FailReason failReason);

    void onLoadingStarted(String str, View view);
}

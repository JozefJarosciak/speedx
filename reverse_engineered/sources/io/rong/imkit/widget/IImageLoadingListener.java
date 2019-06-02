package io.rong.imkit.widget;

import android.net.Uri;

public interface IImageLoadingListener {
    void onLoadingComplete(Uri uri);

    void onLoadingFail();
}

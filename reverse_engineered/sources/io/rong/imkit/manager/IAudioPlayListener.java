package io.rong.imkit.manager;

import android.net.Uri;

public interface IAudioPlayListener {
    void onComplete(Uri uri);

    void onStart(Uri uri);

    void onStop(Uri uri);
}

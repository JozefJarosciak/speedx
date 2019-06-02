package io.rong.imlib.navigation;

public interface NavigationObserver {
    void onError(String str, int i);

    void onReconnect(String str, NavigationCallback navigationCallback);

    void onSuccess(String str);
}

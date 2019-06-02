package com.baidu.location;

public interface BDLocationListener {
    void onConnectHotSpotMessage(String str, int i);

    void onReceiveLocation(BDLocation bDLocation);
}

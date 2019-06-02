package io.rong.imlib;

import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationErrorCode;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationStatus;

public interface RongIMClient$RealTimeLocationListener {
    void onError(RealTimeLocationErrorCode realTimeLocationErrorCode);

    void onParticipantsJoin(String str);

    void onParticipantsQuit(String str);

    void onReceiveLocation(double d, double d2, String str);

    void onStatusChange(RealTimeLocationStatus realTimeLocationStatus);
}

package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.view.View;

public interface PullableView {
    long getUpdateTime();

    View getView();

    void pullToRefresh();

    void refreshing();

    void releaseToRefresh();

    void reset();

    void setPullLabel(String str);

    void setRefreshingLabel(String str);

    void setReleaseLabel(String str);

    void setTextColor(int i);

    void setUpdateTime(long j);

    void updateRefresh(int i, int i2);

    void updateTimeLabel();
}

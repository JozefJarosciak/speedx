package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.beastbikes.framework.ui.android.C2824R;

public class LoadingLayout extends FrameLayout implements PullableView {
    static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;
    private int emptyTxtColor = -10066330;
    private final TextView headerText;
    private String pullLabel;
    private String refreshingLabel;
    private String releaseLabel;
    private long time;

    public LoadingLayout(Context context) {
        super(context);
        this.headerText = (TextView) ((ViewGroup) LayoutInflater.from(context).inflate(C2824R.layout.pull_to_refresh_header, this)).findViewById(C2824R.id.pull_to_refresh_text);
        this.releaseLabel = context.getString(C2824R.string.pull_down_to_refresh_pull_label);
        this.pullLabel = context.getString(C2824R.string.pull_to_refresh_release_label);
        this.refreshingLabel = context.getString(C2824R.string.pull_to_refresh_refreshing_label);
        this.headerText.setTextColor(this.emptyTxtColor);
    }

    public LoadingLayout(Context context, int i, String str, String str2, String str3) {
        super(context);
        this.headerText = (TextView) ((ViewGroup) LayoutInflater.from(context).inflate(C2824R.layout.pull_to_refresh_header, this)).findViewById(C2824R.id.pull_to_refresh_text);
        this.headerText.setTextColor(this.emptyTxtColor);
        this.releaseLabel = str;
        this.pullLabel = str2;
        this.refreshingLabel = str3;
        switch (i) {
        }
    }

    public void reset() {
        this.headerText.setText(this.pullLabel);
        this.headerText.setTextColor(this.emptyTxtColor);
    }

    public void releaseToRefresh() {
        this.headerText.setText(this.releaseLabel);
    }

    public void setPullLabel(String str) {
        this.pullLabel = str;
        this.headerText.setText(str);
        this.headerText.setTextColor(this.emptyTxtColor);
    }

    public void refreshing() {
        this.headerText.setText(this.refreshingLabel);
        this.headerText.setTextColor(this.emptyTxtColor);
    }

    public void setRefreshingLabel(String str) {
        this.refreshingLabel = str;
    }

    public void setReleaseLabel(String str) {
        this.releaseLabel = str;
    }

    public void pullToRefresh() {
        this.headerText.setText(this.pullLabel);
        this.headerText.setTextColor(this.emptyTxtColor);
    }

    public void setTextColor(int i) {
        this.headerText.setTextColor(this.emptyTxtColor);
    }

    public void setUpdateTime(long j) {
        this.time = j;
    }

    public long getUpdateTime() {
        return this.time;
    }

    public void updateTimeLabel() {
    }

    public View getView() {
        return this;
    }

    public void updateRefresh(int i, int i2) {
    }
}

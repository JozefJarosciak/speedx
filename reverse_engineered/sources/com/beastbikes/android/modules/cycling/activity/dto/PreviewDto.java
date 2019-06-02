package com.beastbikes.android.modules.cycling.activity.dto;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.cons.C0844a;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import java.io.Serializable;
import java.util.Arrays;
import org.json.JSONObject;

public class PreviewDto implements C1371a, Serializable {
    public static final int PREVIEW_ALTITUDE_KEY = 2;
    public static final int PREVIEW_CALORIES_KEY = 5;
    public static final int PREVIEW_MAX_SPEED_KEY = 1;
    public static final int PREVIEW_SVG_SPEED_KEY = 0;
    public static final int PREVIEW_TIME_KEY = 4;
    public static final int PREVIEW_UPHILL_DISTANCE_KEY = 3;
    private LocalActivity activity;
    private boolean edit;
    private boolean isChineseTimeZone = false;
    private JSONObject json;
    private int key1;
    private int key2;
    private String label1;
    private String label2;
    private String[] source;
    private String value1;
    private String value2;

    public PreviewDto(String str, String str2) {
        this.label1 = str;
        this.value1 = str2;
    }

    public PreviewDto(String str, String str2, String str3, String str4) {
        this.label1 = str;
        this.value1 = str2;
        this.label2 = str3;
        this.value2 = str4;
    }

    public PreviewDto(Context context, JSONObject jSONObject) {
        if (context != null) {
            int optInt;
            this.json = jSONObject;
            if (this.source == null || this.source.length <= 0) {
                this.source = context.getResources().getStringArray(C1373R.array.cycling_setting_array);
            }
            if (jSONObject.has("0")) {
                optInt = jSONObject.optInt("0");
                this.label1 = this.source[optInt];
                this.value1 = getValue(optInt);
            }
            if (jSONObject.has(C0844a.f2048d)) {
                optInt = jSONObject.optInt(C0844a.f2048d);
                this.label2 = this.source[optInt];
                this.value2 = getValue(optInt);
            }
        }
    }

    public PreviewDto(Context context, JSONObject jSONObject, LocalActivity localActivity, boolean z) {
        if (context != null) {
            int optInt;
            this.isChineseTimeZone = z;
            this.activity = localActivity;
            if (this.source == null || this.source.length <= 0) {
                this.source = context.getResources().getStringArray(C1373R.array.cycling_setting_array);
            }
            if (jSONObject.has("0")) {
                optInt = jSONObject.optInt("0");
                this.label1 = this.source[optInt];
                this.value1 = getValue(optInt);
            }
            if (jSONObject.has(C0844a.f2048d)) {
                optInt = jSONObject.optInt(C0844a.f2048d);
                this.label2 = this.source[optInt];
                this.value2 = getValue(optInt);
            }
        }
    }

    public String getValue(int i) {
        double d = 0.0d;
        switch (i) {
            case 0:
                if (this.activity == null) {
                    return "0.0";
                }
                if (this.activity.getTotalDistance() > 0.0d && this.activity.getTotalElapsedTime() > 0.0d) {
                    d = (this.activity.getTotalDistance() / this.activity.getTotalElapsedTime()) * 3.6d;
                }
                if (this.isChineseTimeZone) {
                    return String.format("%.1f", new Object[]{Double.valueOf(d)});
                }
                return String.format("%.1f", new Object[]{Double.valueOf(C1849a.m9648d(d))});
            case 1:
                if (this.activity == null) {
                    return "0.0";
                }
                if (this.isChineseTimeZone) {
                    return String.format("%.1f", new Object[]{Double.valueOf(this.activity.getMaxVelocity())});
                }
                return String.format("%.1f", new Object[]{Double.valueOf(C1849a.m9648d(this.activity.getMaxVelocity()))});
            case 2:
                if (this.activity == null) {
                    return "0";
                }
                if (this.isChineseTimeZone) {
                    return String.format("%.0f", new Object[]{Double.valueOf(this.activity.getMaxAltitude())});
                }
                return String.format("%.0f", new Object[]{Double.valueOf(C1849a.m9646c(this.activity.getMaxAltitude()))});
            case 3:
                if (this.activity == null) {
                    return "0.0";
                }
                if (this.isChineseTimeZone) {
                    return String.format("%.1f", new Object[]{Double.valueOf(this.activity.getTotalUphillDistance())});
                }
                return String.format("%.1f", new Object[]{Double.valueOf(C1849a.m9646c(this.activity.getTotalUphillDistance()))});
            case 4:
                if (this.activity == null) {
                    return "00:00:00";
                }
                double totalElapsedTime = this.activity.getTotalElapsedTime();
                int i2 = (int) (0.0d == totalElapsedTime ? 0.0d : totalElapsedTime / 3600.0d);
                int i3 = (int) (0.0d == totalElapsedTime ? 0.0d : (totalElapsedTime % 3600.0d) / 60.0d);
                if (0.0d != totalElapsedTime) {
                    d = totalElapsedTime % 60.0d;
                }
                int i4 = (int) d;
                return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
            case 5:
                if (this.activity == null) {
                    return "0.0";
                }
                return String.format("%.0f", new Object[]{Double.valueOf(this.activity.getTotalCalorie())});
            default:
                return "";
        }
    }

    public String getLabel1() {
        return this.label1;
    }

    public void setLabel1(String str) {
        this.label1 = str;
    }

    public String getValue1() {
        return this.value1;
    }

    public void setValue1(String str) {
        this.value1 = str;
    }

    public String getLabel2() {
        return this.label2;
    }

    public void setLabel2(String str) {
        this.label2 = str;
    }

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(String str) {
        this.value2 = str;
    }

    public int getKey1() {
        return this.key1;
    }

    public void setKey1(int i) {
        this.key1 = i;
    }

    public int getKey2() {
        return this.key2;
    }

    public void setKey2(int i) {
        this.key2 = i;
    }

    public boolean isEdit() {
        return this.edit;
    }

    public void setEdit(boolean z) {
        this.edit = z;
    }

    public JSONObject getJson() {
        return this.json;
    }

    public void setJson(JSONObject jSONObject) {
        this.json = jSONObject;
    }

    public LocalActivity getActivity() {
        return this.activity;
    }

    public void setActivity(LocalActivity localActivity) {
        this.activity = localActivity;
    }

    public String toString() {
        return "PreviewDto{key1=" + this.key1 + ", key2=" + this.key2 + ", label1='" + this.label1 + CoreConstants.SINGLE_QUOTE_CHAR + ", value1='" + this.value1 + CoreConstants.SINGLE_QUOTE_CHAR + ", label2='" + this.label2 + CoreConstants.SINGLE_QUOTE_CHAR + ", value2='" + this.value2 + CoreConstants.SINGLE_QUOTE_CHAR + ", activity=" + this.activity + ", source=" + Arrays.toString(this.source) + CoreConstants.CURLY_RIGHT;
    }
}

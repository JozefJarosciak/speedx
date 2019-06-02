package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:CsEva")
public class CSEvaluateMessage extends MessageContent {
    public static final Creator<CSEvaluateMessage> CREATOR = new CSEvaluateMessage$1();
    private static final String TAG = "CSEvaluateMessage";
    private boolean isRobotResolved;
    private String pid;
    private String sid;
    private int source;
    private String suggest;
    private int type;
    private String uid;

    private CSEvaluateMessage() {
    }

    public CSEvaluateMessage(byte[] bArr) {
    }

    public CSEvaluateMessage(Parcel parcel) {
        boolean z = true;
        this.sid = ParcelUtils.readFromParcel(parcel);
        this.uid = ParcelUtils.readFromParcel(parcel);
        this.pid = ParcelUtils.readFromParcel(parcel);
        this.source = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.suggest = ParcelUtils.readFromParcel(parcel);
        if (ParcelUtils.readIntFromParcel(parcel).intValue() != 1) {
            z = false;
        }
        this.isRobotResolved = z;
        this.type = ParcelUtils.readIntFromParcel(parcel).intValue();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.sid);
        ParcelUtils.writeToParcel(parcel, this.uid);
        ParcelUtils.writeToParcel(parcel, this.pid);
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.source));
        ParcelUtils.writeToParcel(parcel, this.suggest);
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.isRobotResolved ? 1 : 0));
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.type));
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", this.uid);
            jSONObject.put("sid", this.sid);
            jSONObject.put("pid", this.pid);
            jSONObject.put(MapboxEvent.ATTRIBUTE_SOURCE, this.source);
            jSONObject.put("suggest", this.suggest);
            jSONObject.put("isresolve", this.isRobotResolved ? 1 : 0);
            jSONObject.put("type", this.type);
        } catch (JSONException e) {
            RLog.e(TAG, "JSONException " + e.getMessage());
        }
        try {
            return jSONObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

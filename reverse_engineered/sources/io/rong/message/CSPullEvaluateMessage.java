package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:CsPullEva")
public class CSPullEvaluateMessage extends MessageContent {
    public static final Creator<CSPullEvaluateMessage> CREATOR = new CSPullEvaluateMessage$1();
    private static final String TAG = "CSPullEvaluateMessage";
    private String msgId;

    public CSPullEvaluateMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            this.msgId = new JSONObject(str).getString("msgId");
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException" + e2.getMessage());
        }
    }

    public static CSPullEvaluateMessage obtain() {
        return new CSPullEvaluateMessage();
    }

    public CSPullEvaluateMessage(Parcel parcel) {
        this.msgId = ParcelUtils.readFromParcel(parcel);
    }

    public String getMsgId() {
        return this.msgId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.msgId);
    }

    public byte[] encode() {
        return null;
    }
}

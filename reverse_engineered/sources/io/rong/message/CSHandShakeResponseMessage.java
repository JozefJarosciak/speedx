package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.CSGroupItem;
import io.rong.imlib.model.CustomServiceMode;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:CsHsR")
public class CSHandShakeResponseMessage extends MessageContent {
    public static final Creator<CSHandShakeResponseMessage> CREATOR = new CSHandShakeResponseMessage$1();
    private static final String TAG = "CSHandShakeResponseMessage";
    private String companyIcon;
    private String companyName;
    private ArrayList<CSGroupItem> groupList = new ArrayList();
    private ArrayList<CSHumanEvaluateItem> humanEvaluateList = new ArrayList();
    private boolean isBlack;
    private int mode;
    private String msg;
    private String pid;
    private boolean requiredChangMode;
    private String robotHelloWord;
    private String robotLogo;
    private String robotName;
    private String robotSessionNoEva;
    private String sid;
    private int status;
    private String uid;

    public CSHandShakeResponseMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject(C0861d.f2139k);
            this.status = jSONObject.optInt("status");
            this.msg = jSONObject.optString("msg");
            if (this.status != 0) {
                int i;
                this.uid = jSONObject2.optString("uid");
                this.sid = jSONObject2.optString("sid");
                this.pid = jSONObject2.optString("pid");
                str = (String) jSONObject2.opt("serviceType");
                if (!TextUtils.isEmpty(str)) {
                    this.mode = Integer.parseInt(str);
                }
                Object optString = jSONObject2.optString("isblack");
                if (!TextUtils.isEmpty(optString)) {
                    this.isBlack = Integer.parseInt(optString) == 1;
                }
                str = (String) jSONObject2.opt("notAutoCha");
                if (!TextUtils.isEmpty(str)) {
                    boolean z;
                    if (Integer.parseInt(str) == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.requiredChangMode = z;
                }
                this.robotName = jSONObject2.optString("robotName");
                this.robotLogo = jSONObject2.optString("robotIcon");
                this.robotHelloWord = jSONObject2.optString("robotWelcome");
                this.companyIcon = jSONObject2.optString("companyIcon");
                this.companyName = jSONObject2.optString("companyName");
                this.robotSessionNoEva = jSONObject2.optString("robotSessionNoEva");
                JSONArray optJSONArray = jSONObject2.optJSONArray("evaluateList");
                this.humanEvaluateList.clear();
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        optString = optJSONArray.optJSONObject(i2).optString("value");
                        String optString2 = optJSONArray.optJSONObject(i2).optString("description");
                        if (TextUtils.isEmpty(optString)) {
                            i = 0;
                        } else {
                            i = Integer.parseInt(optString);
                        }
                        this.humanEvaluateList.add(new CSHumanEvaluateItem(i, optString2));
                    }
                }
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("groups");
                this.groupList.clear();
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    for (i = 0; i < optJSONArray2.length(); i++) {
                        this.groupList.add(new CSGroupItem(optJSONArray2.optJSONObject(i).optString("id"), optJSONArray2.optJSONObject(i).optString("name"), optJSONArray2.optJSONObject(i).optBoolean("online")));
                    }
                }
            }
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException " + e2.getMessage());
        }
    }

    public static CSHandShakeResponseMessage obtain() {
        return new CSHandShakeResponseMessage();
    }

    public CSHandShakeResponseMessage(Parcel parcel) {
        boolean z = true;
        this.status = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.msg = ParcelUtils.readFromParcel(parcel);
        this.sid = ParcelUtils.readFromParcel(parcel);
        this.uid = ParcelUtils.readFromParcel(parcel);
        this.pid = ParcelUtils.readFromParcel(parcel);
        this.mode = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.companyName = ParcelUtils.readFromParcel(parcel);
        this.isBlack = ParcelUtils.readIntFromParcel(parcel).intValue() == 1;
        if (ParcelUtils.readIntFromParcel(parcel).intValue() != 1) {
            z = false;
        }
        this.requiredChangMode = z;
        this.robotName = ParcelUtils.readFromParcel(parcel);
        this.robotLogo = ParcelUtils.readFromParcel(parcel);
        this.robotHelloWord = ParcelUtils.readFromParcel(parcel);
        this.companyIcon = ParcelUtils.readFromParcel(parcel);
        this.robotSessionNoEva = ParcelUtils.readFromParcel(parcel);
        this.humanEvaluateList = ParcelUtils.readListFromParcel(parcel, CSHumanEvaluateItem.class);
        this.groupList = ParcelUtils.readListFromParcel(parcel, CSGroupItem.class);
    }

    public boolean isRequiredChangMode() {
        return this.requiredChangMode;
    }

    public String getUid() {
        return this.uid;
    }

    public String getSid() {
        return this.sid;
    }

    public String getPid() {
        return this.pid;
    }

    public int getCode() {
        return this.status;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean isBlack() {
        return this.isBlack;
    }

    public CustomServiceMode getMode() {
        return CustomServiceMode.valueOf(this.mode);
    }

    public String getRobotName() {
        return this.robotName;
    }

    public String getRobotLogo() {
        return this.robotLogo;
    }

    public String getRobotHelloWord() {
        return this.robotHelloWord;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getCompanyIcon() {
        return this.companyIcon;
    }

    public String getRobotSessionNoEva() {
        return this.robotSessionNoEva;
    }

    public ArrayList<CSHumanEvaluateItem> getHumanEvaluateList() {
        return this.humanEvaluateList;
    }

    public ArrayList<CSGroupItem> getGroupList() {
        return this.groupList;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.status));
        ParcelUtils.writeToParcel(parcel, this.msg);
        ParcelUtils.writeToParcel(parcel, this.sid);
        ParcelUtils.writeToParcel(parcel, this.uid);
        ParcelUtils.writeToParcel(parcel, this.pid);
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.mode));
        ParcelUtils.writeToParcel(parcel, this.companyName);
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.isBlack ? 1 : 0));
        if (!this.requiredChangMode) {
            i2 = 0;
        }
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(i2));
        ParcelUtils.writeToParcel(parcel, this.robotName);
        ParcelUtils.writeToParcel(parcel, this.robotLogo);
        ParcelUtils.writeToParcel(parcel, this.robotHelloWord);
        ParcelUtils.writeToParcel(parcel, this.companyIcon);
        ParcelUtils.writeToParcel(parcel, this.robotSessionNoEva);
        ParcelUtils.writeToParcel(parcel, this.humanEvaluateList);
        ParcelUtils.writeToParcel(parcel, this.groupList);
    }

    public byte[] encode() {
        return null;
    }
}

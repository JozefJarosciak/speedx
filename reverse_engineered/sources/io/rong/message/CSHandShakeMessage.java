package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVUser.AVThirdPartyUserAuth;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.statistics.UserData;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:CsHs")
public class CSHandShakeMessage extends MessageContent {
    public static final Creator<CSHandShakeMessage> CREATOR = new CSHandShakeMessage$1();
    private CSCustomServiceInfo customServiceInfo;

    public void setCustomInfo(CSCustomServiceInfo cSCustomServiceInfo) {
        this.customServiceInfo = cSCustomServiceInfo;
    }

    public CSHandShakeMessage(byte[] bArr) {
    }

    public static CSHandShakeMessage obtain() {
        return new CSHandShakeMessage();
    }

    public CSHandShakeMessage(Parcel parcel) {
        this.customServiceInfo = (CSCustomServiceInfo) ParcelUtils.readFromParcel(parcel, CSCustomServiceInfo.class);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.customServiceInfo);
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject2.put("userId", this.customServiceInfo.getUserId());
            jSONObject2.put("nickName", this.customServiceInfo.getNickName());
            jSONObject2.put("loginName", this.customServiceInfo.getLoginName());
            jSONObject2.put("name", this.customServiceInfo.getName());
            jSONObject2.put("grade", this.customServiceInfo.getGrade());
            jSONObject2.put(UserData.GENDER_KEY, this.customServiceInfo.getGender());
            jSONObject2.put("birthday", this.customServiceInfo.getBirthday());
            jSONObject2.put("age", this.customServiceInfo.getAge());
            jSONObject2.put("profession", this.customServiceInfo.getProfession());
            jSONObject2.put("portraitUrl", this.customServiceInfo.getPortraitUrl());
            jSONObject2.put("province", this.customServiceInfo.getProvince());
            jSONObject2.put("city", this.customServiceInfo.getCity());
            jSONObject2.put(C0882j.f2228b, this.customServiceInfo.getMemo());
            jSONObject.putOpt("userInfo", jSONObject2);
            jSONObject3.put("mobileNo", this.customServiceInfo.getMobileNo());
            jSONObject3.put("email", this.customServiceInfo.getEmail());
            jSONObject3.put(GeocodingCriteria.TYPE_ADDRESS, this.customServiceInfo.getAddress());
            jSONObject3.put("QQ", this.customServiceInfo.getQQ());
            jSONObject3.put(AVThirdPartyUserAuth.SNS_SINA_WEIBO, this.customServiceInfo.getWeibo());
            jSONObject3.put(AVThirdPartyUserAuth.SNS_TENCENT_WEIXIN, this.customServiceInfo.getWeixin());
            jSONObject.putOpt("contactInfo", jSONObject3);
            jSONObject4.put("page", this.customServiceInfo.getPage());
            jSONObject4.put("referrer", this.customServiceInfo.getReferrer());
            jSONObject4.put("enterUrl", this.customServiceInfo.getEnterUrl());
            jSONObject4.put("skillId", this.customServiceInfo.getSkillId());
            JSONArray jSONArray = new JSONArray();
            List<String> listUrl = this.customServiceInfo.getListUrl();
            if (listUrl != null && listUrl.size() > 0) {
                int i = 0;
                for (String put : listUrl) {
                    jSONArray.put(i, put);
                    i++;
                }
            }
            jSONObject4.put("listUrl", jSONArray);
            jSONObject4.put("define", this.customServiceInfo.getDefine());
            jSONObject.put("requestInfo", jSONObject4);
        } catch (JSONException e) {
        }
        try {
            return jSONObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return new byte[0];
        }
    }
}

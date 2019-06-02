package io.rong.imlib.model;

import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import io.rong.common.RLog;
import io.rong.imlib.model.MentionedInfo.MentionedType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MessageContent implements Parcelable {
    private static final String TAG = "MessageContent";
    private MentionedInfo mentionedInfo;
    private UserInfo userInfo;

    public abstract byte[] encode();

    protected MessageContent() {
    }

    public MessageContent(byte[] bArr) {
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public MentionedInfo getMentionedInfo() {
        return this.mentionedInfo;
    }

    public void setMentionedInfo(MentionedInfo mentionedInfo) {
        this.mentionedInfo = mentionedInfo;
    }

    public JSONObject getJSONUserInfo() {
        if (getUserInfo() == null || getUserInfo().getUserId() == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", getUserInfo().getUserId());
            if (!TextUtils.isEmpty(getUserInfo().getName())) {
                jSONObject.put("name", getUserInfo().getName());
            }
            if (getUserInfo().getPortraitUri() == null) {
                return jSONObject;
            }
            jSONObject.put("portrait", getUserInfo().getPortraitUri());
            return jSONObject;
        } catch (JSONException e) {
            RLog.e(TAG, "JSONException " + e.getMessage());
            return jSONObject;
        }
    }

    public UserInfo parseJsonToUserInfo(JSONObject jSONObject) {
        Object optString = jSONObject.optString("id");
        Object optString2 = jSONObject.optString("name");
        String optString3 = jSONObject.optString("portrait");
        if (TextUtils.isEmpty(optString3)) {
            optString3 = jSONObject.optString("icon");
        }
        if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
            return null;
        }
        Uri parse;
        if (optString3 != null) {
            parse = Uri.parse(optString3);
        } else {
            parse = null;
        }
        return new UserInfo(optString, optString2, parse);
    }

    public JSONObject getJsonMentionInfo() {
        if (getMentionedInfo() == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", getMentionedInfo().getType().getValue());
            if (getMentionedInfo().getMentionedUserIdList() == null) {
                jSONObject.put("userIdList", null);
            } else {
                JSONArray jSONArray = new JSONArray();
                for (String put : getMentionedInfo().getMentionedUserIdList()) {
                    jSONArray.put(put);
                }
                jSONObject.put("userIdList", jSONArray);
            }
            jSONObject.put("mentionedContent", getMentionedInfo().getMentionedContent());
        } catch (JSONException e) {
            RLog.e(TAG, "JSONException " + e.getMessage());
        }
        return jSONObject;
    }

    public MentionedInfo parseJsonToMentionInfo(JSONObject jSONObject) {
        MentionedType valueOf = MentionedType.valueOf(jSONObject.optInt("type"));
        JSONArray optJSONArray = jSONObject.optJSONArray("userIdList");
        String optString = jSONObject.optString("mentionedContent");
        if (valueOf.equals(MentionedType.ALL)) {
            return new MentionedInfo(valueOf, null, optString);
        }
        List arrayList = new ArrayList();
        int i = 0;
        while (i < optJSONArray.length()) {
            try {
                arrayList.add((String) optJSONArray.get(i));
                i++;
            } catch (JSONException e) {
            }
        }
        return new MentionedInfo(valueOf, arrayList, optString);
    }
}

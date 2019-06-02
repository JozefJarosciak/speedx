package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.RongIMClient;
import java.util.ArrayList;
import java.util.List;

public class CSCustomServiceInfo implements Parcelable {
    public static final Creator<CSCustomServiceInfo> CREATOR = new C53701();
    private static final String TAG = "CSCustomServiceInfo";
    private String QQ = "";
    private String address = "";
    private String age = "";
    private String birthday = "";
    private String city = "";
    private String define = "";
    private String email = "";
    private String enterUrl = "";
    private String gender = "";
    private String grade = "";
    public List<String> listUrl = new ArrayList();
    private String loginName = "";
    private String memo = "";
    private String mobileNo = "";
    private String name = "";
    private String nickName = "";
    private String page = "";
    private String portraitUrl = "";
    private String profession = "";
    private String province = "";
    private String referrer = "";
    private String skillId = "";
    private String userId = "";
    private String weibo = "";
    private String weixin = "";

    /* renamed from: io.rong.imlib.model.CSCustomServiceInfo$1 */
    static class C53701 implements Creator<CSCustomServiceInfo> {
        C53701() {
        }

        public CSCustomServiceInfo createFromParcel(Parcel parcel) {
            return new CSCustomServiceInfo(parcel);
        }

        public CSCustomServiceInfo[] newArray(int i) {
            return new CSCustomServiceInfo[i];
        }
    }

    public static class Builder {
        private String QQ;
        private String address;
        private String age;
        private String birthday;
        private String city;
        private String define = "";
        private String email;
        private String enterUrl = "";
        private String gender;
        private String grade;
        private List<String> listUrl = new ArrayList();
        private String loginName;
        private String memo;
        private String mobileNo;
        private String name;
        private String nickName;
        private String page = "";
        private String portraitUrl;
        private String profession;
        private String province;
        private String referrer = "";
        private String skillId = "";
        private String userId;
        private String weibo;
        private String weixin;

        public CSCustomServiceInfo build() {
            if (RongIMClient.getInstance() == null) {
                return null;
            }
            CSCustomServiceInfo cSCustomServiceInfo = new CSCustomServiceInfo();
            cSCustomServiceInfo.userId = this.userId != null ? this.userId : "";
            cSCustomServiceInfo.nickName = this.nickName != null ? this.nickName : RongIMClient.getInstance().getCurrentUserId();
            cSCustomServiceInfo.loginName = this.loginName != null ? this.loginName : "";
            cSCustomServiceInfo.name = this.name != null ? this.name : "";
            cSCustomServiceInfo.grade = this.grade != null ? this.grade : "";
            cSCustomServiceInfo.gender = this.gender != null ? this.gender : "";
            cSCustomServiceInfo.birthday = this.birthday != null ? this.birthday : "";
            cSCustomServiceInfo.age = this.age != null ? this.age : "";
            cSCustomServiceInfo.profession = this.profession != null ? this.profession : "";
            cSCustomServiceInfo.portraitUrl = this.portraitUrl != null ? this.portraitUrl : "";
            cSCustomServiceInfo.province = this.province != null ? this.province : "";
            cSCustomServiceInfo.city = this.city != null ? this.city : "";
            cSCustomServiceInfo.memo = this.memo != null ? this.memo : "";
            cSCustomServiceInfo.mobileNo = this.mobileNo != null ? this.mobileNo : "";
            cSCustomServiceInfo.email = this.email != null ? this.email : "";
            cSCustomServiceInfo.address = this.address != null ? this.address : "";
            cSCustomServiceInfo.QQ = this.QQ != null ? this.QQ : "";
            cSCustomServiceInfo.weibo = this.weibo != null ? this.weibo : "";
            cSCustomServiceInfo.weixin = this.weixin != null ? this.weixin : "";
            cSCustomServiceInfo.page = this.page != null ? this.page : "";
            cSCustomServiceInfo.referrer = this.referrer != null ? this.referrer : "";
            cSCustomServiceInfo.enterUrl = this.enterUrl != null ? this.enterUrl : "";
            cSCustomServiceInfo.skillId = this.skillId != null ? this.skillId : "";
            cSCustomServiceInfo.listUrl = this.listUrl;
            cSCustomServiceInfo.define = this.define != null ? this.define : "";
            return cSCustomServiceInfo;
        }

        public Builder page(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.page = str;
            }
            return this;
        }

        public Builder nickName(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.nickName = str;
            }
            return this;
        }

        public Builder gender(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.gender = str;
            }
            return this;
        }

        public Builder mobileNo(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mobileNo = str;
            }
            return this;
        }

        public Builder memo(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.memo = str;
            }
            return this;
        }

        public Builder name(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.name = str;
            }
            return this;
        }

        public Builder grade(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.grade = str;
            }
            return this;
        }

        public Builder skillId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.skillId = str;
            }
            return this;
        }

        public Builder userId(String str) {
            if (!TextUtils.isEmpty(this.skillId)) {
                this.userId = str;
            }
            return this;
        }

        public Builder city(String str) {
            this.city = str;
            return this;
        }

        public Builder referrer(String str) {
            this.referrer = str;
            return this;
        }

        public Builder enterUrl(String str) {
            this.enterUrl = str;
            return this;
        }

        public Builder province(String str) {
            this.province = str;
            return this;
        }

        public Builder loginName(String str) {
            this.loginName = str;
            return this;
        }

        public Builder define(String str) {
            this.define = str;
            return this;
        }

        public Builder birthday(String str) {
            this.birthday = str;
            return this;
        }

        public Builder age(String str) {
            this.age = str;
            return this;
        }

        public Builder profession(String str) {
            this.profession = str;
            return this;
        }

        public Builder portraitUrl(String str) {
            this.portraitUrl = str;
            return this;
        }

        public Builder email(String str) {
            this.email = str;
            return this;
        }

        public Builder address(String str) {
            this.address = str;
            return this;
        }

        public Builder QQ(String str) {
            this.QQ = str;
            return this;
        }

        public Builder weibo(String str) {
            this.weibo = str;
            return this;
        }

        public Builder weixin(String str) {
            this.weixin = str;
            return this;
        }

        public Builder listUrl(List<String> list) {
            if (!(list == null || list.isEmpty())) {
                for (String add : list) {
                    this.listUrl.add(add);
                }
            }
            return this;
        }
    }

    public CSCustomServiceInfo() {
        if (RongIMClient.getInstance() != null) {
            this.nickName = RongIMClient.getInstance().getCurrentUserId();
        } else {
            RLog.m19420e(TAG, "JSONException CSCustomServiceInfo: RongIMClient.getInstance() is null");
        }
    }

    public CSCustomServiceInfo(Parcel parcel) {
        this.userId = ParcelUtils.readFromParcel(parcel);
        this.nickName = ParcelUtils.readFromParcel(parcel);
        this.loginName = ParcelUtils.readFromParcel(parcel);
        this.name = ParcelUtils.readFromParcel(parcel);
        this.grade = ParcelUtils.readFromParcel(parcel);
        this.gender = ParcelUtils.readFromParcel(parcel);
        this.birthday = ParcelUtils.readFromParcel(parcel);
        this.age = ParcelUtils.readFromParcel(parcel);
        this.profession = ParcelUtils.readFromParcel(parcel);
        this.portraitUrl = ParcelUtils.readFromParcel(parcel);
        this.province = ParcelUtils.readFromParcel(parcel);
        this.city = ParcelUtils.readFromParcel(parcel);
        this.memo = ParcelUtils.readFromParcel(parcel);
        this.mobileNo = ParcelUtils.readFromParcel(parcel);
        this.email = ParcelUtils.readFromParcel(parcel);
        this.address = ParcelUtils.readFromParcel(parcel);
        this.QQ = ParcelUtils.readFromParcel(parcel);
        this.weibo = ParcelUtils.readFromParcel(parcel);
        this.weixin = ParcelUtils.readFromParcel(parcel);
        this.page = ParcelUtils.readFromParcel(parcel);
        this.referrer = ParcelUtils.readFromParcel(parcel);
        this.enterUrl = ParcelUtils.readFromParcel(parcel);
        this.skillId = ParcelUtils.readFromParcel(parcel);
        this.listUrl = ParcelUtils.readListFromParcel(parcel, String.class);
        this.define = ParcelUtils.readFromParcel(parcel);
    }

    public String getUserId() {
        return this.userId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public String getName() {
        return this.name;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getGender() {
        return this.gender;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getAge() {
        return this.age;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getPortraitUrl() {
        return this.portraitUrl;
    }

    public String getProvince() {
        return this.province;
    }

    public String getCity() {
        return this.city;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getQQ() {
        return this.QQ;
    }

    public String getWeibo() {
        return this.weibo;
    }

    public String getWeixin() {
        return this.weixin;
    }

    public String getPage() {
        return this.page;
    }

    public String getReferrer() {
        return this.referrer;
    }

    public String getEnterUrl() {
        return this.enterUrl;
    }

    public String getSkillId() {
        return this.skillId;
    }

    public String getDefine() {
        return this.define;
    }

    public List<String> getListUrl() {
        return this.listUrl;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.userId);
        ParcelUtils.writeToParcel(parcel, this.nickName);
        ParcelUtils.writeToParcel(parcel, this.loginName);
        ParcelUtils.writeToParcel(parcel, this.name);
        ParcelUtils.writeToParcel(parcel, this.grade);
        ParcelUtils.writeToParcel(parcel, this.gender);
        ParcelUtils.writeToParcel(parcel, this.birthday);
        ParcelUtils.writeToParcel(parcel, this.age);
        ParcelUtils.writeToParcel(parcel, this.profession);
        ParcelUtils.writeToParcel(parcel, this.portraitUrl);
        ParcelUtils.writeToParcel(parcel, this.province);
        ParcelUtils.writeToParcel(parcel, this.city);
        ParcelUtils.writeToParcel(parcel, this.memo);
        ParcelUtils.writeToParcel(parcel, this.mobileNo);
        ParcelUtils.writeToParcel(parcel, this.email);
        ParcelUtils.writeToParcel(parcel, this.address);
        ParcelUtils.writeToParcel(parcel, this.QQ);
        ParcelUtils.writeToParcel(parcel, this.weibo);
        ParcelUtils.writeToParcel(parcel, this.weixin);
        ParcelUtils.writeToParcel(parcel, this.page);
        ParcelUtils.writeToParcel(parcel, this.referrer);
        ParcelUtils.writeToParcel(parcel, this.enterUrl);
        ParcelUtils.writeToParcel(parcel, this.skillId);
        ParcelUtils.writeToParcel(parcel, this.listUrl);
        ParcelUtils.writeToParcel(parcel, this.define);
    }
}

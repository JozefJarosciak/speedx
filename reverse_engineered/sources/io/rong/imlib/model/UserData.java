package io.rong.imlib.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.telephony.TelephonyManager;
import io.rong.common.ParcelUtils;

public final class UserData implements Parcelable {
    public static final Creator<UserData> CREATOR = new C53851();
    AccountInfo accountInfo;
    String appVersion;
    ClientInfo clientInfo;
    ContactInfo contactInfo;
    String extra;
    PersonalInfo personalInfo;

    /* renamed from: io.rong.imlib.model.UserData$1 */
    static class C53851 implements Creator<UserData> {
        C53851() {
        }

        public UserData createFromParcel(Parcel parcel) {
            return new UserData(parcel);
        }

        public UserData[] newArray(int i) {
            return new UserData[i];
        }
    }

    public static class AccountInfo implements Parcelable {
        public static final Creator<AccountInfo> CREATOR = new C53861();
        String appUserId;
        String nickName;
        String userName;

        /* renamed from: io.rong.imlib.model.UserData$AccountInfo$1 */
        static class C53861 implements Creator<AccountInfo> {
            C53861() {
            }

            public AccountInfo createFromParcel(Parcel parcel) {
                return new AccountInfo(parcel);
            }

            public AccountInfo[] newArray(int i) {
                return new AccountInfo[i];
            }
        }

        public AccountInfo(Parcel parcel) {
            setAppUserId(ParcelUtils.readFromParcel(parcel));
            setUserName(ParcelUtils.readFromParcel(parcel));
            setNickName(ParcelUtils.readFromParcel(parcel));
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getAppUserId() {
            return this.appUserId;
        }

        public void setAppUserId(String str) {
            this.appUserId = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            ParcelUtils.writeToParcel(parcel, this.appUserId);
            ParcelUtils.writeToParcel(parcel, this.userName);
            ParcelUtils.writeToParcel(parcel, this.nickName);
        }
    }

    public static class ClientInfo implements Parcelable {
        public static final Creator<ClientInfo> CREATOR = new C53871();
        String carrier;
        String device;
        String mobilePhoneManufacturers;
        String network;
        String os = "Android";
        String systemVersion;

        /* renamed from: io.rong.imlib.model.UserData$ClientInfo$1 */
        static class C53871 implements Creator<ClientInfo> {
            C53871() {
            }

            public ClientInfo createFromParcel(Parcel parcel) {
                return new ClientInfo(parcel);
            }

            public ClientInfo[] newArray(int i) {
                return new ClientInfo[i];
            }
        }

        public ClientInfo(Context context) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(io.rong.imlib.statistics.UserData.PHONE_KEY);
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                    this.network = connectivityManager.getActiveNetworkInfo().getTypeName();
                }
                if (telephonyManager != null) {
                    this.carrier = telephonyManager.getNetworkOperator();
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            this.mobilePhoneManufacturers = Build.MANUFACTURER;
            this.device = Build.MODEL;
            this.systemVersion = String.valueOf(VERSION.SDK_INT);
        }

        public ClientInfo(Parcel parcel) {
            setNetwork(ParcelUtils.readFromParcel(parcel));
            setCarrier(ParcelUtils.readFromParcel(parcel));
            setSystemVersion(ParcelUtils.readFromParcel(parcel));
            setOs(ParcelUtils.readFromParcel(parcel));
            setDevice(ParcelUtils.readFromParcel(parcel));
            setMobilePhoneManufacturers(ParcelUtils.readFromParcel(parcel));
        }

        public String getNetwork() {
            return this.network;
        }

        public void setNetwork(String str) {
            this.network = str;
        }

        public String getCarrier() {
            return this.carrier;
        }

        public void setCarrier(String str) {
            this.carrier = str;
        }

        public String getSystemVersion() {
            return this.systemVersion;
        }

        public void setSystemVersion(String str) {
            this.systemVersion = str;
        }

        public String getOs() {
            return this.os;
        }

        public void setOs(String str) {
            this.os = str;
        }

        public String getDevice() {
            return this.device;
        }

        public void setDevice(String str) {
            this.device = str;
        }

        public String getMobilePhoneManufacturers() {
            return this.mobilePhoneManufacturers;
        }

        public void setMobilePhoneManufacturers(String str) {
            this.mobilePhoneManufacturers = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            ParcelUtils.writeToParcel(parcel, this.network);
            ParcelUtils.writeToParcel(parcel, this.carrier);
            ParcelUtils.writeToParcel(parcel, this.systemVersion);
            ParcelUtils.writeToParcel(parcel, this.os);
            ParcelUtils.writeToParcel(parcel, this.device);
            ParcelUtils.writeToParcel(parcel, this.mobilePhoneManufacturers);
        }
    }

    public static class ContactInfo implements Parcelable {
        public static final Creator<ContactInfo> CREATOR = new C53881();
        String address;
        String email;
        String qq;
        String tel;
        String weibo;
        String weixin;

        /* renamed from: io.rong.imlib.model.UserData$ContactInfo$1 */
        static class C53881 implements Creator<ContactInfo> {
            C53881() {
            }

            public ContactInfo createFromParcel(Parcel parcel) {
                return new ContactInfo(parcel);
            }

            public ContactInfo[] newArray(int i) {
                return new ContactInfo[i];
            }
        }

        public ContactInfo(Parcel parcel) {
            setTel(ParcelUtils.readFromParcel(parcel));
            setEmail(ParcelUtils.readFromParcel(parcel));
            setAddress(ParcelUtils.readFromParcel(parcel));
            setQQ(ParcelUtils.readFromParcel(parcel));
            setWeibo(ParcelUtils.readFromParcel(parcel));
            setWeixin(ParcelUtils.readFromParcel(parcel));
        }

        public String getTel() {
            return this.tel;
        }

        public void setTel(String str) {
            this.tel = str;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public String getQQ() {
            return this.qq;
        }

        public void setQQ(String str) {
            this.qq = str;
        }

        public String getWeibo() {
            return this.weibo;
        }

        public void setWeibo(String str) {
            this.weibo = str;
        }

        public String getWeixin() {
            return this.weixin;
        }

        public void setWeixin(String str) {
            this.weixin = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            ParcelUtils.writeToParcel(parcel, this.tel);
            ParcelUtils.writeToParcel(parcel, this.email);
            ParcelUtils.writeToParcel(parcel, this.address);
            ParcelUtils.writeToParcel(parcel, this.qq);
            ParcelUtils.writeToParcel(parcel, this.weibo);
            ParcelUtils.writeToParcel(parcel, this.weixin);
        }
    }

    public static class PersonalInfo implements Parcelable {
        public static final Creator<PersonalInfo> CREATOR = new C53891();
        String age;
        String birthday;
        String comment;
        String job;
        String portraitUri;
        String realName;
        String sex;

        /* renamed from: io.rong.imlib.model.UserData$PersonalInfo$1 */
        static class C53891 implements Creator<PersonalInfo> {
            C53891() {
            }

            public PersonalInfo createFromParcel(Parcel parcel) {
                return new PersonalInfo(parcel);
            }

            public PersonalInfo[] newArray(int i) {
                return new PersonalInfo[i];
            }
        }

        public PersonalInfo(Parcel parcel) {
            setRealName(ParcelUtils.readFromParcel(parcel));
            setSex(ParcelUtils.readFromParcel(parcel));
            setBirthday(ParcelUtils.readFromParcel(parcel));
            setAge(ParcelUtils.readFromParcel(parcel));
            setJob(ParcelUtils.readFromParcel(parcel));
            setPortraitUri(ParcelUtils.readFromParcel(parcel));
            setComment(ParcelUtils.readFromParcel(parcel));
        }

        public String getRealName() {
            return this.realName;
        }

        public void setRealName(String str) {
            this.realName = str;
        }

        public String getSex() {
            return this.sex;
        }

        public void setSex(String str) {
            this.sex = str;
        }

        public String getBirthday() {
            return this.birthday;
        }

        public void setBirthday(String str) {
            this.birthday = str;
        }

        public String getAge() {
            return this.age;
        }

        public void setAge(String str) {
            this.age = str;
        }

        public String getJob() {
            return this.job;
        }

        public void setJob(String str) {
            this.job = str;
        }

        public String getPortraitUri() {
            return this.portraitUri;
        }

        public void setPortraitUri(String str) {
            this.portraitUri = str;
        }

        public String getComment() {
            return this.comment;
        }

        public void setComment(String str) {
            this.comment = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            ParcelUtils.writeToParcel(parcel, this.realName);
            ParcelUtils.writeToParcel(parcel, this.sex);
            ParcelUtils.writeToParcel(parcel, this.birthday);
            ParcelUtils.writeToParcel(parcel, this.age);
            ParcelUtils.writeToParcel(parcel, this.job);
            ParcelUtils.writeToParcel(parcel, this.portraitUri);
            ParcelUtils.writeToParcel(parcel, this.comment);
        }
    }

    public UserData(Context context) {
        this.clientInfo = new ClientInfo(context);
    }

    public UserData(Parcel parcel) {
        setPersonalInfo((PersonalInfo) ParcelUtils.readFromParcel(parcel, PersonalInfo.class));
        setAccountInfo((AccountInfo) ParcelUtils.readFromParcel(parcel, AccountInfo.class));
        setContactInfo((ContactInfo) ParcelUtils.readFromParcel(parcel, ContactInfo.class));
        this.clientInfo = (ClientInfo) ParcelUtils.readFromParcel(parcel, ClientInfo.class);
        setAppVersion(ParcelUtils.readFromParcel(parcel));
        setExtra(ParcelUtils.readFromParcel(parcel));
    }

    public PersonalInfo getPersonalInfo() {
        return this.personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public AccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public ContactInfo getContactInfo() {
        return this.contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.personalInfo);
        ParcelUtils.writeToParcel(parcel, this.accountInfo);
        ParcelUtils.writeToParcel(parcel, this.contactInfo);
        ParcelUtils.writeToParcel(parcel, this.clientInfo);
        ParcelUtils.writeToParcel(parcel, this.appVersion);
        ParcelUtils.writeToParcel(parcel, this.extra);
    }
}

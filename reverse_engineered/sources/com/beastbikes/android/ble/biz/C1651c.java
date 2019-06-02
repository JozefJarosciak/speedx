package com.beastbikes.android.ble.biz;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.dao.C1665a;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.android.ble.protocol.v1.CyclingActivityCharacteristic;
import com.beastbikes.android.ble.protocol.v1.CyclingSampleCharacteristic;
import com.beastbikes.android.ble.protocol.v1.PreviewDataCharacteristic;
import com.beastbikes.android.ble.protocol.v1.SynchronizationDataCharacteristic;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.C1917a;
import com.beastbikes.android.modules.cycling.activity.dao.C1918b;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.modules.cycling.activity.util.ActivityType;
import com.beastbikes.android.modules.cycling.activity.util.C2041a;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.p057b.C1381a;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.android.utils.C2563k;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BleManager */
/* renamed from: com.beastbikes.android.ble.biz.c */
public class C1651c extends C1397a {
    /* renamed from: a */
    private static final Logger f7452a = LoggerFactory.getLogger(C1651c.class);
    /* renamed from: b */
    private C1917a f7453b;
    /* renamed from: c */
    private C1918b f7454c;
    /* renamed from: d */
    private C1665a f7455d;
    /* renamed from: e */
    private C1398a f7456e;
    /* renamed from: f */
    private C1387d f7457f;
    /* renamed from: g */
    private C1389k f7458g;
    /* renamed from: h */
    private Context f7459h;
    /* renamed from: i */
    private boolean f7460i = false;
    /* renamed from: j */
    private int f7461j = 10;
    /* renamed from: k */
    private double f7462k = 0.0d;
    /* renamed from: l */
    private double f7463l = 0.0d;
    /* renamed from: m */
    private double f7464m = 0.0d;
    /* renamed from: n */
    private double f7465n = 0.0d;
    /* renamed from: o */
    private long f7466o = 0;
    /* renamed from: p */
    private long f7467p = 0;
    /* renamed from: q */
    private int f7468q = 0;
    /* renamed from: r */
    private int f7469r = 0;
    /* renamed from: s */
    private double f7470s = 0.0d;

    public C1651c(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f7459h = activity;
        C1381a d = ((BeastBikes) BeastBikes.j().getApplicationContext()).d();
        C1772d c1772d = new C1772d(activity);
        this.f7453b = new C1917a(d);
        this.f7454c = new C1918b(d);
        this.f7455d = new C1665a(d);
        this.f7457f = (C1387d) c1772d.m9399a(C1387d.class, C1768c.f8075a, C1768c.m9391a(activity));
        this.f7456e = new C1398a(activity);
        if (C1849a.m9641a()) {
            this.f7458g = (C1389k) c1772d.m9398a(C1389k.class, "http://api.map.baidu.com/");
        } else {
            this.f7458g = (C1389k) c1772d.m9398a(C1389k.class, "https://maps.googleapis.com/maps/api/place/");
        }
    }

    public C1651c(Context context) {
        super((C1372b) context.getApplicationContext());
        C1381a d = ((BeastBikes) BeastBikes.j().getApplicationContext()).d();
        C1772d c1772d = new C1772d(context);
        this.f7453b = new C1917a(d);
        this.f7454c = new C1918b(d);
        this.f7455d = new C1665a(d);
        this.f7457f = (C1387d) c1772d.m9399a(C1387d.class, C1768c.f8075a, C1768c.m9391a(context));
        this.f7456e = new C1398a(context);
        this.f7459h = context;
    }

    /* renamed from: a */
    public boolean m8891a(PreviewDataCharacteristic previewDataCharacteristic, String str, String str2) {
        if (previewDataCharacteristic == null || TextUtils.isEmpty(str)) {
            return false;
        }
        CyclingActivityCharacteristic activity = previewDataCharacteristic.getActivity();
        if (activity == null) {
            f7452a.error("savePreviewActivity cycling activity characteristic is null");
            return false;
        }
        LocalActivity a = this.f7453b.m9903a(((long) activity.getStopTime()) * 1000, str);
        if (a != null) {
            f7452a.info("保存预览数据 savePreviewActivity = " + a.toString());
            return true;
        }
        a = new LocalActivity();
        String str3 = "ble_" + UUID.randomUUID().toString();
        a.setId(str3);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            a.setUserId(currentUser.getObjectId());
            a.setUsername(currentUser.getUsername());
            BleDevice c = m8896c(str2, currentUser.getObjectId());
            if (c != null) {
                a.setSource(BleDevice.brandType2String(c.getBrandType()));
                a.setCentralName(c.getDeviceName());
            }
        }
        a.setDeviceId(str);
        a.setCentralId(str2);
        a.setType(ActivityType.CYCLING.ordinal());
        a.setStartTime(((long) activity.getStartTime()) * 1000);
        a.setFinishTime(((long) activity.getStopTime()) * 1000);
        a.setSampleRate(activity.getSampleRate());
        double totalDistance = (double) activity.getTotalDistance();
        a.setTotalDistance(totalDistance);
        a.setTotalElapsedTime((double) activity.getTotalTime());
        a.setSampleCount(activity.getSampleCount());
        a.setBleDataType(activity.getSyncDataType());
        double climbHeight = (double) activity.getClimbHeight();
        a.setTotalRisenAltitude(climbHeight);
        a.setTotalUphillDistance(Math.sqrt((totalDistance * totalDistance) + (climbHeight * climbHeight)));
        a.setSynced(false);
        a.setCoordinate("GPS");
        a.setState(0);
        try {
            CyclingSampleCharacteristic[] samples = previewDataCharacteristic.getSamples();
            if (samples != null && samples.length > 0) {
                a.setMaxCadence((double) samples[samples.length - 1].getMaxCadence());
                a.setMaxCardiacRate((double) samples[samples.length - 1].getMaxHeartRate());
            }
            this.f7453b.mo3187a(a);
            f7452a.info("保存预览数据 Create or update ble cycling activityId = " + str3 + " success, LocalActivity = " + a.toString());
            return true;
        } catch (PersistenceException e) {
            f7452a.error("保存预览数据 Create or update ble cycling activityId = " + str3 + " error, " + e);
            return false;
        }
    }

    /* renamed from: a */
    public boolean m8892a(String str, SynchronizationDataCharacteristic synchronizationDataCharacteristic) {
        if (TextUtils.isEmpty(str)) {
            f7452a.info("保存同步数据Sample点activityId为null ");
            return false;
        } else if (synchronizationDataCharacteristic == null) {
            f7452a.info("保存同步数据Sample点characteristic为null");
            return false;
        } else {
            CyclingSampleCharacteristic[] samples = synchronizationDataCharacteristic.getSamples();
            if (samples == null || samples.length <= 0) {
                return false;
            }
            LocalActivity localActivity;
            int currentPacketIndex = synchronizationDataCharacteristic.getCurrentPacketIndex();
            int totalPacketCount = synchronizationDataCharacteristic.getTotalPacketCount();
            if (currentPacketIndex == 0) {
                this.f7462k = 0.0d;
                this.f7463l = 0.0d;
                this.f7464m = 0.0d;
                this.f7465n = 0.0d;
                this.f7466o = 0;
                this.f7467p = 0;
                this.f7470s = 0.0d;
                try {
                    localActivity = (LocalActivity) this.f7453b.mo3189c(str);
                    if (localActivity == null) {
                        return false;
                    }
                    if (localActivity.getBleDataType() == 2) {
                        return true;
                    }
                    this.f7461j = localActivity.getSampleRate();
                    this.f7467p = localActivity.getFinishTime();
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }
            }
            int length = currentPacketIndex * samples.length;
            List arrayList = new ArrayList();
            int i = 0;
            while (i < samples.length) {
                if (!((i == 0 && currentPacketIndex == 0) || (i == samples.length - 1 && currentPacketIndex == totalPacketCount - 1))) {
                    CyclingSampleCharacteristic cyclingSampleCharacteristic = samples[i];
                    if (cyclingSampleCharacteristic == null) {
                        return false;
                    }
                    LocalActivitySample localActivitySample = new LocalActivitySample();
                    AVUser currentUser = AVUser.getCurrentUser();
                    if (currentUser != null) {
                        localActivitySample.setUserId(currentUser.getObjectId());
                    }
                    localActivitySample.setId(UUID.randomUUID().toString());
                    localActivitySample.setActivityId(str);
                    localActivitySample.setLatitude0("0");
                    localActivitySample.setLongitude0("0");
                    double latitude = cyclingSampleCharacteristic.getLatitude();
                    double longitude = cyclingSampleCharacteristic.getLongitude();
                    if (latitude <= 1000.0d && latitude >= -1000.0d && longitude <= 1000.0d && longitude >= -1000.0d) {
                        localActivitySample.setLatitude1(String.valueOf(latitude));
                        localActivitySample.setLongitude1(String.valueOf(longitude));
                        localActivitySample.setAltitude(String.valueOf(cyclingSampleCharacteristic.getAltitude()));
                        localActivitySample.setVelocity(((double) cyclingSampleCharacteristic.getSpeed()) * 3.6d);
                        localActivitySample.setMaxSpeed(((double) cyclingSampleCharacteristic.getMaxSpeed()) * 3.6d);
                        localActivitySample.setDistance((double) cyclingSampleCharacteristic.getDistance());
                        localActivitySample.setCurrTime(((long) cyclingSampleCharacteristic.getTimestamp()) * 1000);
                        localActivitySample.setCadence((double) cyclingSampleCharacteristic.getCadence());
                        this.f7469r = Math.max(this.f7469r, cyclingSampleCharacteristic.getCadence());
                        this.f7468q = Math.max(this.f7468q, cyclingSampleCharacteristic.getHeartRate());
                        localActivitySample.setMaxCadence((double) cyclingSampleCharacteristic.getMaxCadence());
                        localActivitySample.setCardiacRate((double) cyclingSampleCharacteristic.getHeartRate());
                        localActivitySample.setMaxCardiacRate((double) cyclingSampleCharacteristic.getMaxHeartRate());
                        localActivitySample.setSynced(false);
                        localActivitySample.setOrdinal(length + i);
                        localActivitySample.setTime((double) (length + i));
                        localActivitySample.setElapsedTime((long) cyclingSampleCharacteristic.getTimestamp());
                        localActivitySample.setCyclingStatus(0);
                        arrayList.add(localActivitySample);
                        f7452a.trace("中控同步数据打点：Sample = " + localActivitySample.toString());
                        latitude = ((double) cyclingSampleCharacteristic.getDistance()) - this.f7462k;
                        this.f7462k = (double) cyclingSampleCharacteristic.getDistance();
                        this.f7463l += m8875a(this.f7461j, latitude);
                        longitude = (double) cyclingSampleCharacteristic.getAltitude();
                        if (longitude > 0.0d && longitude < 2.5d) {
                            this.f7464m = Math.sqrt((latitude * latitude) + (longitude * longitude)) + this.f7464m;
                        }
                        this.f7470s += longitude;
                        this.f7465n = Math.max(localActivitySample.getMaxSpeed(), this.f7465n);
                        if (currentPacketIndex == 0 && i == 1) {
                            this.f7466o = ((long) cyclingSampleCharacteristic.getTimestamp()) * 1000;
                            f7452a.info("同步数据，记录开始时间：" + this.f7466o);
                        }
                        long timestamp = ((long) cyclingSampleCharacteristic.getTimestamp()) * 1000;
                        if (timestamp > this.f7467p && currentPacketIndex == totalPacketCount - 1) {
                            this.f7467p = timestamp;
                            f7452a.info("同步数据，记录结束时间：" + this.f7467p);
                        }
                    }
                }
                i++;
            }
            try {
                this.f7454c.m9026b(arrayList);
                f7452a.info("Create or update ble activity sample activityId = " + str + " success");
            } catch (Throwable e2) {
                f7452a.error("Create or update ble activity sample activityId = " + str + " error", e2);
            }
            if (currentPacketIndex == totalPacketCount - 1) {
                try {
                    localActivity = (LocalActivity) this.f7453b.mo3189c(str);
                    localActivity.setState(4);
                    localActivity.setTotalCalorie(this.f7463l);
                    localActivity.setMaxVelocity(this.f7465n);
                    localActivity.setMaxAltitude(0.0d);
                    localActivity.setMaxCadence((double) this.f7469r);
                    localActivity.setMaxCardiacRate((double) this.f7468q);
                    localActivity.setBleDataType(2);
                    localActivity.setTotalRisenAltitude(this.f7470s);
                    long currentTimeMillis = System.currentTimeMillis();
                    if (this.f7467p <= 1451577600000L || this.f7467p > currentTimeMillis) {
                        if (this.f7466o <= 1451577600000L || this.f7466o >= currentTimeMillis) {
                            this.f7467p = currentTimeMillis;
                            this.f7466o = this.f7467p - ((long) (localActivity.getTotalElapsedTime() * 1000.0d));
                            f7452a.info("开始时间无效，则使用当前时间为结束时间，开始时间为结束时间减骑行时间, stopTime = " + this.f7467p + ", startTime = " + this.f7466o);
                        } else {
                            this.f7467p = this.f7466o + ((long) (localActivity.getTotalElapsedTime() * 1000.0d));
                            f7452a.info("开始时间有效，纠正结束时间, stopTime = " + this.f7467p + ", startTime = " + this.f7466o);
                        }
                    } else if (this.f7466o <= 1451577600000L) {
                        this.f7466o = this.f7467p - ((long) (localActivity.getTotalElapsedTime() * 1000.0d));
                        f7452a.info("开始时间在2016年之前, 进行纠正, stopTime = " + this.f7467p + ", startTime = " + this.f7466o);
                    } else if (this.f7466o > this.f7467p) {
                        this.f7466o = this.f7467p - ((long) (localActivity.getTotalElapsedTime() * 1000.0d));
                        f7452a.info("开始时间大于结束时间认为是无效的开始时间，进行纠正, stopTime = " + this.f7467p + ", startTime = " + this.f7466o);
                    }
                    if (this.f7466o != 0) {
                        localActivity.setStartTime(this.f7466o);
                    }
                    if (this.f7467p != 0) {
                        localActivity.setFinishTime(this.f7467p);
                    }
                    localActivity.setTotalUphillDistance(this.f7464m);
                    this.f7453b.mo3187a(localActivity);
                    this.f7463l = 0.0d;
                    this.f7462k = 0.0d;
                    this.f7465n = 0.0d;
                    this.f7469r = 0;
                    this.f7468q = 0;
                    this.f7470s = 0.0d;
                    f7452a.info("Create or update ble activity ble data type success, LocalActivity = " + localActivity.toString());
                } catch (Throwable e22) {
                    f7452a.error("Create or update ble activity ble data type error, ", e22);
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    private double m8875a(int i, double d) {
        LocalUser a;
        double d2 = (d / 1000.0d) / ((((double) i) * 1.0d) / 3600.0d);
        try {
            a = new C2389c(this.f7459h).m12118a(AVUser.getCurrentUser().getObjectId());
        } catch (BusinessException e) {
            e.printStackTrace();
            a = null;
        }
        if (a == null) {
            return 0.0d;
        }
        Date parse;
        double weight = a.getWeight();
        double height = a.getHeight();
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd").parse(a.getBirthday());
        } catch (ParseException e2) {
            e2.printStackTrace();
            parse = null;
        }
        int i2 = 25;
        if (parse != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            int i3 = instance.get(1);
            instance.setTime(new Date());
            i2 = instance.get(1) - i3;
        }
        return C2041a.m10515a(a.getGender(), weight, height, i2, d2, (((double) i) * 1.0d) / 3600.0d);
    }

    /* renamed from: a */
    public List<LocalActivity> m8884a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return this.f7453b.m9907b(str, str2);
    }

    /* renamed from: b */
    public List<LocalActivity> m8894b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return this.f7453b.m9910c(str, str2);
    }

    /* renamed from: a */
    public void m8886a(C1614a c1614a) {
        if (c1614a != null) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                BluetoothDevice b = c1614a.m8734b();
                if (b != null) {
                    BleDevice bleDevice = new BleDevice();
                    bleDevice.setDeviceName(b.getName());
                    bleDevice.setDeviceId(b.getAddress());
                    bleDevice.setLastBindTime(System.currentTimeMillis() / 1000);
                    bleDevice.setUserId(currentUser.getObjectId());
                    bleDevice.setMacAddress(c1614a.m8728a());
                    bleDevice.setHardwareType(c1614a.m8740e());
                    BleDevice a = this.f7455d.m9033a(c1614a.m8728a(), currentUser.getObjectId());
                    if (a == null) {
                        bleDevice.setId(UUID.randomUUID().toString());
                    } else {
                        bleDevice.setId(a.getId());
                    }
                    bleDevice.setGuaranteeTime(c1614a.m8744i().m8746a());
                    try {
                        this.f7455d.mo3187a(bleDevice);
                        f7452a.info("Create or update to ble device " + b.getName() + ":" + b.getAddress() + " success");
                    } catch (PersistenceException e) {
                        f7452a.error("Create or update to ble device " + b.getName() + ":" + b.getAddress() + " error, " + e);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public List<BleDevice> m8882a() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            return this.f7455d.m9036b(currentUser.getObjectId());
        }
        return null;
    }

    /* renamed from: b */
    public ArrayList<BleDevice> m8893b() {
        JSONObject a = this.f7457f.a();
        int optInt = a.optInt("code");
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (optInt == 0) {
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            ArrayList<BleDevice> arrayList = new ArrayList();
            if (optJSONArray == null) {
                return arrayList;
            }
            optInt = 0;
            while (optInt < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(optInt);
                BleDevice bleDevice = new BleDevice();
                bleDevice.setDeviceName(optJSONObject.optString("central_name"));
                bleDevice.setMacAddress(optJSONObject.optString("central_id"));
                bleDevice.setUrl(optJSONObject.optString("bike_image"));
                bleDevice.setHardwareType(optJSONObject.optInt("hardware"));
                bleDevice.setBrandType(optJSONObject.optInt("bike_type"));
                bleDevice.setUserId(optJSONObject.optString("user_id"));
                bleDevice.setFrameId(optJSONObject.optString("frame_id"));
                bleDevice.setObjectId(optJSONObject.optInt("id"));
                bleDevice.setMileage(optJSONObject.optDouble("mileage"));
                bleDevice.setCycleTime(optJSONObject.optLong("cycle_time"));
                bleDevice.setCycleTimes(optJSONObject.optInt("cycle_times"));
                bleDevice.setDeviceId(C1614a.m8726b(bleDevice.getMacAddress()));
                try {
                    BleDevice a2 = this.f7455d.m9033a(bleDevice.getMacAddress(), bleDevice.getUserId());
                    if (a2 != null) {
                        bleDevice.setId(a2.getId());
                        bleDevice.setLastBindTime(a2.getLastBindTime());
                        bleDevice.setGuaranteeTime(a2.getGuaranteeTime());
                    } else {
                        bleDevice.setId(UUID.randomUUID().toString());
                    }
                    this.f7455d.mo3187a(bleDevice);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }
                try {
                    arrayList.add(bleDevice);
                    optInt++;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return arrayList;
        }
        Toasts.showOnUiThreadWithText(this.f7459h, optString);
        return null;
    }

    /* renamed from: c */
    public BleDevice m8896c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return this.f7455d.m9033a(str, str2);
    }

    /* renamed from: a */
    public BleDevice m8880a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f7455d.m9032a(str);
    }

    /* renamed from: a */
    public void m8888a(String str, String str2, int i, int i2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            this.f7455d.m9035a(str, str2, i, i2, str3, str4);
        }
    }

    /* renamed from: b */
    public Map<String, Object> m8895b(String str) throws BusinessException {
        boolean z;
        int i = 0;
        Map<String, Object> hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            z = false;
        } else {
            SharedPreferences sharedPreferences = this.f7459h.getSharedPreferences(this.f7459h.getPackageName(), 0);
            z = sharedPreferences.getBoolean("beast.ble.control.active" + str, false);
            i = sharedPreferences.getInt("beast.ble.control.active.owner" + str, 0);
            if (!z) {
                JSONObject a = this.f7457f.a(str);
                if (!C2563k.m12869a(a)) {
                    if (a.optInt("code") != 0) {
                        Object optString = a.optString(AVStatus.MESSAGE_TAG);
                        if (!TextUtils.isEmpty(optString)) {
                            Toasts.showOnUiThreadWithText(this.f7459h, optString);
                        }
                    } else {
                        a = a.optJSONObject(C0882j.f2229c);
                        if (a != null) {
                            z = a.optBoolean("isActive");
                            i = a.optInt("owner");
                        }
                    }
                }
            }
        }
        hashMap.put("isActive", Boolean.valueOf(z));
        hashMap.put("owner", Integer.valueOf(i));
        return hashMap;
    }

    /* renamed from: c */
    public boolean m8897c(String str) throws BusinessException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject b = this.f7457f.b(str);
            if (!C2563k.m12869a(b) && b.optInt("code") == 0) {
                SharedPreferences sharedPreferences = this.f7459h.getSharedPreferences(this.f7459h.getPackageName(), 0);
                sharedPreferences.edit().putBoolean("beast.ble.control.active" + str, true);
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser == null) {
                    return true;
                }
                sharedPreferences.edit().putInt("beast.ble.control.active.owner" + str, currentUser.getSpeedxId());
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void m8887a(String str, float f) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject a = this.f7457f.a(str, f);
            if (!C2563k.m12869a(a) && a.optInt("code") != 0) {
                Object optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThreadWithText(this.f7459h, optString);
                }
            }
        }
    }

    /* renamed from: a */
    public BleDevice m8881a(String str, String str2, int i, int i2, String str3, boolean z) {
        BleDevice bleDevice = null;
        f7452a.trace("saveDeviceToServer centralId=[" + str + "],centralName=[" + str2 + "],hardwareType=[" + i + "],brandType= [" + i2 + "] ,deviceId=[" + str3 + "],save=[" + z + "],lock =[" + this.f7460i + "]");
        if (!(this.f7460i || TextUtils.isEmpty(str) || !z)) {
            this.f7460i = true;
            JSONObject a = this.f7457f.a(str, str2, i, i2);
            this.f7460i = false;
            bleDevice = new BleDevice();
            bleDevice.setHardwareType(i);
            bleDevice.setBrandType(i2);
            bleDevice.setDeviceId(str3);
            bleDevice.setMacAddress(str);
            bleDevice.setDeviceName(str2);
            if (!C2563k.m12869a(a) && a.optInt("code") == 0) {
                a = a.optJSONObject(C0882j.f2229c);
                if (a != null) {
                    bleDevice.setUrl(a.optString("bike_image"));
                    bleDevice.setFrameId(a.optString("frame_id"));
                    bleDevice.setObjectId(a.optInt("id"));
                    bleDevice.setMileage(a.optDouble("mileage"));
                    bleDevice.setCycleTime(a.optLong("cycle_time"));
                    bleDevice.setCycleTimes(a.optInt("cycle_times"));
                    bleDevice.setUserId(a.optString("user_id"));
                    bleDevice.setLastBindTime(System.currentTimeMillis() / 1000);
                    try {
                        BleDevice a2 = this.f7455d.m9033a(str, bleDevice.getUserId());
                        if (a2 != null) {
                            bleDevice.setId(a2.getId());
                            bleDevice.setGuaranteeTime(a2.getGuaranteeTime());
                        } else {
                            bleDevice.setId(UUID.randomUUID().toString());
                        }
                        this.f7455d.mo3187a(bleDevice);
                    } catch (PersistenceException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bleDevice;
    }

    /* renamed from: a */
    public boolean m8890a(BleDevice bleDevice, String str) {
        if (bleDevice == null) {
            f7452a.error("Delete BleDevice is false, device is null");
            return false;
        }
        try {
            JSONObject c = this.f7457f.c(bleDevice.getMacAddress());
            if (c == null) {
                f7452a.error("deleteDevice(), result json is null");
                return false;
            } else if (c.optInt("code", -1) != 0) {
                return false;
            } else {
                try {
                    this.f7455d.mo3188b(bleDevice);
                    this.f7456e.d(bleDevice.getDeviceId(), str);
                } catch (PersistenceException e) {
                    f7452a.error("deleteBleDevice(), Delete Local BleDevice is error, e = " + e);
                }
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* renamed from: d */
    public ArrayList<NavigationLocation> m8899d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!TextUtils.equals(str2, "bd")) {
            return m8876a(this.f7458g.a(str, 500, "AIzaSyC6rtQRpblQN3RWVE3OCK_c8q4QhWVSnfg"));
        }
        return m8878c(this.f7458g.a(str, "json", "wgs84ll", 1, "cYN62WkIhrX1d5f8VouoXqg9yzlvWMGs", "2C:5F:D0:D0:9C:19:E7:81:2D:90:D0:F3:9C:90:EF:03:37:91:E7:7C;com.beastbikes.android"));
    }

    /* renamed from: d */
    public ArrayList<NavigationLocation> m8898d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!C1849a.m9641a()) {
            return m8877b(this.f7458g.a(str, "AIzaSyC6rtQRpblQN3RWVE3OCK_c8q4QhWVSnfg"));
        }
        return m8879d(this.f7458g.a(str, "全国", "json", "cYN62WkIhrX1d5f8VouoXqg9yzlvWMGs", "2C:5F:D0:D0:9C:19:E7:81:2D:90:D0:F3:9C:90:EF:03:37:91:E7:7C;com.beastbikes.android"));
    }

    /* renamed from: e */
    public LatLng m8900e(String str, String str2) {
        JSONObject b = this.f7458g.b(str, "AIzaSyC6rtQRpblQN3RWVE3OCK_c8q4QhWVSnfg");
        if (!TextUtils.equals(b.optString("status"), "OK")) {
            return null;
        }
        b = b.optJSONObject(C0882j.f2229c);
        if (b == null) {
            return null;
        }
        b = b.optJSONObject("geometry");
        if (b == null) {
            return null;
        }
        b = b.optJSONObject(MapboxEvent.TYPE_LOCATION);
        if (b != null) {
            return C2558g.m12850f(b.optDouble(MapboxEvent.KEY_LATITUDE), b.optDouble(MapboxEvent.KEY_LONGITUDE));
        }
        return null;
    }

    /* renamed from: a */
    private ArrayList<NavigationLocation> m8876a(JSONObject jSONObject) {
        ArrayList<NavigationLocation> arrayList = null;
        if (!C2563k.m12869a(jSONObject) && TextUtils.equals(jSONObject.optString("status"), "OK")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("results");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                arrayList = new ArrayList();
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    NavigationLocation navigationLocation = new NavigationLocation();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("geometry").optJSONObject(MapboxEvent.TYPE_LOCATION);
                    LatLng f = C2558g.m12850f(optJSONObject2.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject2.optDouble(MapboxEvent.KEY_LONGITUDE));
                    navigationLocation.setLatitude(f.getLatitude());
                    navigationLocation.setLongitude(f.getLongitude());
                    navigationLocation.setName(optJSONObject.optString("name"));
                    navigationLocation.setAddress(optJSONObject.optString("vicinity"));
                    arrayList.add(navigationLocation);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    /* renamed from: b */
    private ArrayList<NavigationLocation> m8877b(JSONObject jSONObject) {
        ArrayList<NavigationLocation> arrayList = null;
        if (!C2563k.m12869a(jSONObject) && TextUtils.equals(jSONObject.optString("status"), "OK")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("predictions");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                arrayList = new ArrayList();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("structured_formatting");
                    NavigationLocation navigationLocation = new NavigationLocation();
                    navigationLocation.setName(optJSONObject2.optString("main_text"));
                    navigationLocation.setAddress(optJSONObject2.optString("secondary_text"));
                    navigationLocation.setPlaceId(optJSONObject.optString("place_id"));
                    navigationLocation.setReference(optJSONObject.optString("reference"));
                    arrayList.add(navigationLocation);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    private ArrayList<NavigationLocation> m8878c(JSONObject jSONObject) {
        ArrayList<NavigationLocation> arrayList = null;
        if (!C2563k.m12869a(jSONObject) && jSONObject.optInt("status") == 0) {
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                arrayList = new ArrayList();
                NavigationLocation navigationLocation = new NavigationLocation();
                LatLng b = C2558g.m12845b(new com.baidu.mapapi.model.LatLng(optJSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION).optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION).optDouble(MapboxEvent.KEY_LONGITUDE)));
                navigationLocation.setLatitude(b.getLatitude());
                navigationLocation.setLongitude(b.getLongitude());
                navigationLocation.setAddress(optJSONObject.optString("formatted_address"));
                navigationLocation.setName(optJSONObject.optString("sematic_description"));
                arrayList.add(navigationLocation);
                JSONArray optJSONArray = optJSONObject.optJSONArray("pois");
                if (!(optJSONArray == null || optJSONArray.length() == 0)) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject(Property.SYMBOL_PLACEMENT_POINT);
                        NavigationLocation navigationLocation2 = new NavigationLocation();
                        LatLng b2 = C2558g.m12845b(new com.baidu.mapapi.model.LatLng(optJSONObject3.optDouble("y"), optJSONObject3.optDouble("x")));
                        navigationLocation2.setLatitude(b2.getLatitude());
                        navigationLocation2.setLongitude(b2.getLongitude());
                        navigationLocation2.setName(optJSONObject2.optString("name"));
                        navigationLocation2.setAddress(optJSONObject2.optString("addr"));
                        arrayList.add(navigationLocation2);
                    }
                }
            }
        }
        return arrayList;
    }

    @Nullable
    /* renamed from: d */
    private ArrayList<NavigationLocation> m8879d(JSONObject jSONObject) {
        ArrayList<NavigationLocation> arrayList = null;
        if (!C2563k.m12869a(jSONObject) && jSONObject.optInt("status") == 0) {
            JSONArray optJSONArray = jSONObject.optJSONArray(C0882j.f2229c);
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                arrayList = new ArrayList();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    NavigationLocation navigationLocation = new NavigationLocation();
                    navigationLocation.setName(optJSONObject.optString("name"));
                    navigationLocation.setAddress(optJSONObject.optString("city") + optJSONObject.optString("district"));
                    optJSONObject = optJSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION);
                    LatLng b = C2558g.m12845b(new com.baidu.mapapi.model.LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE)));
                    navigationLocation.setLatitude(b.getLatitude());
                    navigationLocation.setLongitude(b.getLongitude());
                    arrayList.add(navigationLocation);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public List<BleDevice> m8883a(int i) {
        return this.f7455d.m9034a(i);
    }

    /* renamed from: a */
    public boolean m8889a(BleDevice bleDevice) {
        try {
            this.f7455d.mo3188b(bleDevice);
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void m8885a(int i, BluetoothDevice bluetoothDevice) {
        BleDevice bleDevice = new BleDevice();
        bleDevice.setId(bluetoothDevice.getAddress());
        bleDevice.setDeviceName(bluetoothDevice.getName());
        bleDevice.setDeviceType(i);
        bleDevice.setDeviceId(bluetoothDevice.getAddress());
        bleDevice.setLastBindTime(System.currentTimeMillis());
        try {
            this.f7455d.mo3187a(bleDevice);
            f7452a.info("saveSensorBleDevice(), save sensor ble device is success");
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}

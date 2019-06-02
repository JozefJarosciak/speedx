package com.beastbikes.android.modules.cycling.activity.biz;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.AVException;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.platform.comapi.location.CoordinateType;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.BleNotificationService;
import com.beastbikes.android.ble.biz.p058c.C1386e;
import com.beastbikes.android.ble.biz.p058c.C1647h;
import com.beastbikes.android.ble.biz.p058c.C1648i;
import com.beastbikes.android.ble.biz.p058c.C1649j;
import com.beastbikes.android.modules.cycling.SyncService;
import com.beastbikes.android.modules.cycling.activity.dao.C1918b;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.modules.cycling.activity.ui.EmptyActivity;
import com.beastbikes.android.modules.cycling.activity.util.ActivityType;
import com.beastbikes.android.modules.cycling.activity.util.C2041a;
import com.beastbikes.android.modules.cycling.activity.util.C2044c;
import com.beastbikes.android.modules.cycling.activity.util.C2045d;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.utils.C2554c;
import com.beastbikes.android.utils.C2569o;
import com.beastbikes.android.utils.C2572q;
import com.beastbikes.android.utils.C2577t;
import com.beastbikes.android.utils.C2577t.C1912b;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.android.ormlite.C1380c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imlib.statistics.UserData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityService extends BleNotificationService implements LocationListener, OnInitListener, C1371a, C1647h, C1648i, C1649j, C1912b {
    /* renamed from: a */
    public static boolean f8509a = true;
    /* renamed from: b */
    private static final Logger f8510b = LoggerFactory.getLogger("ActivityService");
    /* renamed from: A */
    private ExecutorService f8511A;
    /* renamed from: B */
    private Runnable f8512B;
    /* renamed from: C */
    private int f8513C = 200;
    /* renamed from: D */
    private double f8514D = 170.0d;
    /* renamed from: E */
    private double f8515E = 65.0d;
    /* renamed from: F */
    private double f8516F = 200.0d;
    /* renamed from: G */
    private LocalActivitySample f8517G;
    /* renamed from: H */
    private boolean f8518H;
    /* renamed from: I */
    private int f8519I;
    /* renamed from: J */
    private boolean f8520J;
    /* renamed from: K */
    private int f8521K;
    /* renamed from: L */
    private boolean f8522L;
    /* renamed from: M */
    private double f8523M;
    /* renamed from: N */
    private Runnable f8524N = new C19041(this);
    /* renamed from: O */
    private ArrayList<Double> f8525O = new ArrayList();
    /* renamed from: P */
    private ArrayList<Double> f8526P = new ArrayList();
    /* renamed from: Q */
    private ArrayList<Double> f8527Q = new ArrayList();
    /* renamed from: R */
    private ArrayList<Double> f8528R = new ArrayList();
    /* renamed from: S */
    private double f8529S;
    /* renamed from: T */
    private double f8530T;
    /* renamed from: U */
    private ArrayList<Double> f8531U = new ArrayList();
    /* renamed from: V */
    private ArrayList<Double> f8532V = new ArrayList();
    /* renamed from: W */
    private double f8533W;
    /* renamed from: X */
    private double f8534X;
    /* renamed from: Y */
    private ArrayList<Integer> f8535Y = new ArrayList();
    /* renamed from: Z */
    private ArrayList<Integer> f8536Z = new ArrayList();
    private ArrayList<Integer> aa = new ArrayList();
    private int ab;
    private int ac;
    private int ad;
    private ArrayList<Double> ae = new ArrayList();
    private double af;
    private long ag = 0;
    /* renamed from: c */
    private final AtomicInteger f8537c = new AtomicInteger(0);
    /* renamed from: d */
    private final Handler f8538d = new Handler(Looper.getMainLooper());
    /* renamed from: e */
    private C1910c f8539e;
    /* renamed from: f */
    private final Timer f8540f = new Timer("SamplingTimer", true);
    /* renamed from: g */
    private final Timer f8541g = new Timer("ScreenObserverTimer", true);
    /* renamed from: h */
    private long f8542h;
    /* renamed from: i */
    private C1398a f8543i;
    /* renamed from: j */
    private LocalActivity f8544j;
    /* renamed from: k */
    private Location f8545k;
    /* renamed from: l */
    private C1918b f8546l;
    /* renamed from: m */
    private LocationManager f8547m;
    /* renamed from: n */
    private PowerManager f8548n;
    /* renamed from: o */
    private WakeLock f8549o;
    /* renamed from: p */
    private CoordinateConverter f8550p;
    /* renamed from: q */
    private NotificationManager f8551q;
    /* renamed from: r */
    private C2577t f8552r;
    /* renamed from: s */
    private boolean f8553s;
    /* renamed from: t */
    private LocalActivitySample f8554t;
    /* renamed from: u */
    private SharedPreferences f8555u;
    /* renamed from: v */
    private C1909b f8556v;
    /* renamed from: w */
    private int f8557w = 0;
    /* renamed from: x */
    private AVUser f8558x;
    /* renamed from: y */
    private C2044c f8559y;
    /* renamed from: z */
    private LinkedBlockingQueue<LocalActivitySample> f8560z = new LinkedBlockingQueue(10);

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$1 */
    class C19041 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ActivityService f8497a;

        C19041(ActivityService activityService) {
            this.f8497a = activityService;
        }

        public void run() {
            this.f8497a.m9852i();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$2 */
    class C19052 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ActivityService f8498a;

        C19052(ActivityService activityService) {
            this.f8498a = activityService;
        }

        public void run() {
            while (!this.f8498a.f8560z.isEmpty()) {
                try {
                    if (((LocalActivitySample) this.f8498a.f8560z.poll()) != null) {
                        this.f8498a.f8546l.mo3187a(r0);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    ActivityService.f8510b.error("Persist activity sample error", e);
                }
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$3 */
    class C19063 extends AsyncTask<Void, Void, LocalActivity> {
        /* renamed from: a */
        final /* synthetic */ ActivityService f8499a;

        C19063(ActivityService activityService) {
            this.f8499a = activityService;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9814a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9815a((LocalActivity) obj);
        }

        /* renamed from: a */
        protected LocalActivity m9814a(Void... voidArr) {
            if (this.f8499a.f8544j == null) {
                this.f8499a.f8544j = this.f8499a.f8543i.a();
                if (this.f8499a.f8544j == null) {
                    ActivityService.f8510b.info("获取当前正在骑行的记录localActivity为null");
                    return null;
                }
            }
            C1915b c1915b = new C1915b(this.f8499a.f8544j.getState());
            try {
                c1915b.m9899c();
                if (this.f8499a.f8554t != null) {
                    try {
                        this.f8499a.f8546l.mo3187a(this.f8499a.f8554t);
                    } catch (PersistenceException e) {
                        ActivityService.f8510b.info("CreateOrUpdate maxSpeedActivitySample failed, " + e);
                        e.printStackTrace();
                    }
                }
                this.f8499a.f8544j.setSpeed(this.f8499a.f8543i.a(this.f8499a.f8544j.getId(), this.f8499a.f8544j.getTotalElapsedTime(), this.f8499a.f8544j.getTotalDistance()));
                this.f8499a.f8544j.setState(c1915b.m9894e());
                this.f8499a.f8544j.setFinishTime(System.currentTimeMillis());
                this.f8499a.f8543i.c(this.f8499a.f8544j);
                C1398a.a(this.f8499a, null);
            } catch (Exception e2) {
                ActivityService.f8510b.info("updateStateAndFinishTime failed, " + e2);
            }
            return this.f8499a.f8544j;
        }

        /* renamed from: a */
        protected void m9815a(LocalActivity localActivity) {
            this.f8499a.m9854j();
            Intent intent = new Intent();
            intent.setAction("com.beastbikes.intent.action.ACTIVITY_COMPLETE");
            Object activityDTO = new ActivityDTO(localActivity);
            intent.putExtra("activity", localActivity);
            intent.putExtra("activity_dto", activityDTO);
            intent.putExtra("sport_identify", activityDTO.getActivityIdentifier());
            intent.addCategory("android.intent.category.DEFAULT");
            if (this.f8499a.f8558x != null) {
                intent.putExtra("user_id", this.f8499a.f8558x.getObjectId());
                intent.putExtra("avatar_url", this.f8499a.f8558x.getAvatar());
                intent.putExtra("nick_name", this.f8499a.f8558x.getDisplayName());
            }
            this.f8499a.sendBroadcast(intent);
            this.f8499a.m9864o();
            if (localActivity.getTotalDistance() <= 10.0d) {
                ActivityService.f8510b.info("Complete activity " + localActivity.getId() + "\r\n\r\n");
                this.f8499a.stopSelf();
                return;
            }
            intent.addFlags(268435456);
            this.f8499a.startActivity(intent);
            try {
                this.f8499a.startService(new Intent(this.f8499a, SyncService.class));
            } catch (Exception e) {
                ActivityService.f8510b.info("OPPO Service SecurityException, " + e);
            }
            this.f8499a.m9863n();
            ActivityService.f8510b.info("Complete activity " + localActivity.getId() + "\r\n\r\n");
            this.f8499a.stopSelf();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$4 */
    class C19074 extends TimerTask {
        /* renamed from: a */
        final /* synthetic */ ActivityService f8500a;

        C19074(ActivityService activityService) {
            this.f8500a = activityService;
        }

        public void run() {
            if (ActivityService.f8509a) {
                ActivityService.f8510b.info("ActivityService: screen ScreenOn is on");
            } else if (System.currentTimeMillis() - this.f8500a.ag > 600000 && !this.f8500a.f8553s) {
                Runtime.getRuntime().gc();
                this.f8500a.ag = System.currentTimeMillis();
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$a */
    public class C1908a extends Binder {
        /* renamed from: a */
        final /* synthetic */ ActivityService f8501a;

        public C1908a(ActivityService activityService) {
            this.f8501a = activityService;
        }

        /* renamed from: a */
        public ActivityService m9816a() {
            return this.f8501a;
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$b */
    public interface C1909b {
        /* renamed from: a */
        void mo3287a(LocalActivity localActivity);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$c */
    private final class C1910c extends TimerTask {
        /* renamed from: a */
        final /* synthetic */ ActivityService f8502a;
        /* renamed from: b */
        private Location f8503b;
        /* renamed from: c */
        private int f8504c;
        /* renamed from: d */
        private int f8505d;
        /* renamed from: e */
        private Location f8506e;
        /* renamed from: f */
        private final AtomicLong f8507f;

        private C1910c(ActivityService activityService) {
            this.f8502a = activityService;
            this.f8504c = 25;
            this.f8505d = 1;
            this.f8507f = new AtomicLong(0);
            if (activityService.f8558x == null || TextUtils.isEmpty(activityService.f8558x.getObjectId())) {
                ActivityService.f8510b.error("No authenticated user found");
                return;
            }
            this.f8505d = activityService.f8558x.getGender();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                if (!TextUtils.isEmpty(activityService.f8558x.getBirthDay())) {
                    Date parse = simpleDateFormat.parse(activityService.f8558x.getBirthDay());
                    if (parse != null) {
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(parse);
                        this.f8504c = C2554c.m12789a(instance.get(1), instance.get(2), instance.get(5));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            long andIncrement = this.f8507f.getAndIncrement();
            if (this.f8502a.f8544j == null) {
                this.f8502a.f8544j = this.f8502a.f8543i.a();
                if (this.f8502a.f8544j == null) {
                    ActivityService.f8510b.warn("No activity found...");
                    this.f8502a.f8540f.cancel();
                    this.f8502a.stopSelf();
                    return;
                }
            }
            switch (this.f8502a.f8544j.getState()) {
                case 0:
                case 4:
                    ActivityService.f8510b.warn("Activity " + this.f8502a.f8544j.getId() + " COMPLETE");
                    this.f8502a.stopSelf();
                    return;
                case 2:
                    ActivityService.f8510b.warn("Activity " + this.f8502a.f8544j.getId() + " PAUSED, Location: " + this.f8502a.f8545k);
                    if (this.f8502a.f8556v != null) {
                        this.f8502a.f8556v.mo3287a(this.f8502a.f8544j);
                        return;
                    }
                    return;
                case 3:
                    double d;
                    if (this.f8502a.f8556v != null) {
                        this.f8502a.f8556v.mo3287a(this.f8502a.f8544j);
                    }
                    ActivityService.f8510b.warn("Activity  AUTO_PAUSED, Location: " + this.f8502a.f8545k);
                    float f = 0.0f;
                    double distance;
                    if (this.f8502a.f8545k != null) {
                        double speed = (double) (this.f8502a.f8545k.getSpeed() * 3.6f);
                        if (this.f8503b != null) {
                            this.f8502a.f8550p.coord(new LatLng(this.f8503b.getLatitude(), this.f8503b.getLongitude()));
                            LatLng convert = this.f8502a.f8550p.convert();
                            this.f8502a.f8550p.coord(new LatLng(this.f8502a.f8545k.getLatitude(), this.f8502a.f8545k.getLongitude()));
                            distance = DistanceUtil.getDistance(convert, this.f8502a.f8550p.convert());
                            long abs = Math.abs(this.f8502a.f8545k.getTime() - this.f8503b.getTime()) / 1000;
                            if (distance > 0.0d && abs > 0) {
                                speed = Math.max((distance / ((double) abs)) * 3.6d, speed);
                            }
                            double d2 = distance;
                            distance = speed;
                            speed = d2;
                        } else {
                            ActivityService.f8510b.warn("Activity  AUTO_PAUSED, tmpGpsLocation is null");
                            distance = speed;
                            speed = 0.0d;
                        }
                        float accuracy = this.f8502a.f8545k.getAccuracy();
                        ActivityService.f8510b.info("AUTO_PAUSED GpsSpeed = [" + (this.f8502a.f8545k.getSpeed() * 3.6f) + "] speed=[" + distance + "]\ndiffDistance=" + speed + "\nacc=" + accuracy);
                        float f2 = accuracy;
                        d = distance;
                        distance = speed;
                        f = f2;
                    } else {
                        distance = 0.0d;
                        d = 0.0d;
                    }
                    if (this.f8502a.f8522L && this.f8502a.f8523M > 0.0d) {
                        d = this.f8502a.f8523M;
                    }
                    if ((d >= 1.7999999523162842d || r2 >= 50.0d) && r0 <= 80.0f) {
                        try {
                            this.f8502a.m9839b(true);
                            return;
                        } catch (BusinessException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    this.f8502a.f8545k = null;
                    return;
                default:
                    m9819a(andIncrement, this.f8502a.f8544j);
                    return;
            }
        }

        /* renamed from: a */
        private void m9819a(long j, LocalActivity localActivity) {
            double d;
            Object obj;
            double totalCalorie = localActivity.getTotalCalorie();
            double speed = localActivity.getSpeed();
            double p = this.f8502a.f8523M;
            double d2 = 0.0d;
            double totalDistance = localActivity.getTotalDistance();
            double totalElapsedTime = localActivity.getTotalElapsedTime() + 1.0d;
            double totalUphillDistance = localActivity.getTotalUphillDistance();
            double totalRisenAltitude = localActivity.getTotalRisenAltitude();
            boolean z = true;
            if (this.f8502a.f8545k == null) {
                z = false;
                d = p;
            } else {
                double d3;
                if (!this.f8502a.f8545k.hasSpeed() || this.f8502a.f8545k.getAccuracy() > 50.0f) {
                    d = p;
                    d3 = 0.0d;
                } else {
                    p = ((double) this.f8502a.f8545k.getSpeed()) * 3.6d;
                    d = p;
                    d3 = p;
                }
                if (this.f8503b != null) {
                    boolean z2;
                    p = this.f8503b.getLatitude();
                    double longitude = this.f8503b.getLongitude();
                    double latitude = this.f8502a.f8545k.getLatitude();
                    double longitude2 = this.f8502a.f8545k.getLongitude();
                    if (p == 0.0d || longitude == 0.0d || p == Double.MIN_VALUE || longitude == Double.MIN_VALUE) {
                        ActivityService.f8510b.error("Location is error, latitude = " + p + ", longitude = " + longitude);
                        z2 = false;
                        longitude = totalDistance;
                        latitude = totalCalorie;
                    } else if (p == latitude && longitude == longitude2) {
                        z2 = false;
                        ActivityService.f8510b.info("tmpGpsLocation = gpsLocation tempGpsLocation: " + this.f8503b + "  gpsLocation: " + this.f8502a.f8545k);
                        longitude = totalDistance;
                        latitude = totalCalorie;
                    } else {
                        double a = C2569o.m12882a(p, longitude, latitude, longitude2);
                        p = (double) (Math.abs(this.f8502a.f8545k.getTime() - this.f8503b.getTime()) / 1000);
                        if (a > 0.0d && p >= 1.0d) {
                            d3 = (a / p) * 3.6d;
                        }
                        if (this.f8502a.f8522L && this.f8502a.f8523M > 0.0d) {
                            d3 = this.f8502a.f8523M;
                        }
                        double max = Math.max(d, d3);
                        if (a > 0.0d) {
                            d3 = totalDistance + a;
                            totalDistance = totalCalorie + C2041a.m10515a(this.f8505d, this.f8502a.f8515E, this.f8502a.f8514D, this.f8504c, (a / 1000.0d) / 2.777777777777778E-4d, 2.777777777777778E-4d);
                            if (this.f8506e != null) {
                                latitude = this.f8502a.f8545k.getAltitude();
                                p = this.f8506e.getAltitude();
                                if (latitude > p) {
                                    p = latitude - p;
                                    ActivityService.f8510b.info("diffAltitude = " + p);
                                    if (p > 0.0d && p < 2.5d) {
                                        longitude2 = p + totalRisenAltitude;
                                        d = Math.sqrt((a * a) + (p * p)) + totalUphillDistance;
                                        p = latitude;
                                    }
                                }
                                longitude2 = totalRisenAltitude;
                                d = totalUphillDistance;
                                p = latitude;
                            } else {
                                longitude2 = totalRisenAltitude;
                                d = totalUphillDistance;
                                p = 0.0d;
                            }
                            if (max > localActivity.getMaxVelocity() && max < 160.0d) {
                                localActivity.setMaxVelocity(max);
                                this.f8502a.f8554t = this.f8502a.m9833a(localActivity, this.f8502a.f8545k, 0);
                                if (this.f8502a.f8554t != null) {
                                    ActivityService.f8510b.info("Collect max speed sample: " + this.f8502a.f8554t.toString());
                                }
                            }
                            totalRisenAltitude = ((double) this.f8503b.getSpeed()) * 3.6d;
                            totalUphillDistance = this.f8503b.getAltitude();
                            if (max > 0.0d || this.f8502a.f8545k == null) {
                                latitude = max;
                            } else {
                                latitude = ((double) this.f8502a.f8545k.getSpeed()) * 3.6d;
                            }
                            if (p > 0.0d || this.f8502a.f8545k == null) {
                                longitude = p;
                            } else {
                                longitude = this.f8502a.f8545k.getAltitude();
                            }
                            if (latitude > 9.0d + totalRisenAltitude || latitude < totalRisenAltitude - 32.4d || longitude > 1.0d + totalUphillDistance || longitude < totalUphillDistance - 2.0d) {
                                ActivityService.f8510b.info("当前打点超出有效范围，lastSpeed = " + totalRisenAltitude + ", speed = " + latitude + "; lastAltitude = " + totalUphillDistance + ", altitude = " + longitude);
                                if (this.f8502a.f8517G != null) {
                                    localActivity.setRealTimePower((int) this.f8502a.f8517G.getPower());
                                }
                                localActivity.setAltitude(totalUphillDistance);
                            } else {
                                if (longitude < totalUphillDistance) {
                                    localActivity.setTotalDescent(localActivity.getTotalDescent() + Math.abs(totalUphillDistance - longitude));
                                }
                                localActivity = this.f8502a.m9838b(this.f8502a.m9828a(localActivity, longitude, latitude), longitude, latitude);
                            }
                            this.f8503b = this.f8502a.f8545k;
                            z2 = true;
                            totalRisenAltitude = longitude2;
                            totalUphillDistance = d;
                            d2 = longitude;
                            longitude = d3;
                            d = latitude;
                            latitude = totalDistance;
                        } else {
                            ActivityService.f8510b.info("Distance: " + a);
                            z2 = false;
                            longitude = totalDistance;
                            d = max;
                            latitude = totalCalorie;
                        }
                    }
                    z = z2;
                    totalDistance = longitude;
                    totalCalorie = latitude;
                } else {
                    ActivityService.f8510b.info("tempGpsLocation is null");
                }
            }
            if (totalDistance <= 0.0d || totalElapsedTime <= 0.0d) {
                p = speed;
            } else {
                p = (totalDistance / totalElapsedTime) * 3.6d;
            }
            localActivity.setSpeed(p);
            localActivity.setInstantaneousVelocity(d);
            localActivity.setTotalCalorie(totalCalorie);
            localActivity.setTotalDistance(totalDistance);
            localActivity.setTotalElapsedTime(totalElapsedTime);
            localActivity.setTotalUphillDistance(totalUphillDistance);
            localActivity.setTotalRisenAltitude(totalRisenAltitude);
            localActivity.setAltitude(d2);
            localActivity.setMaxAltitude(Math.max(localActivity.getMaxAltitude(), d2));
            this.f8502a.f8517G = this.f8502a.m9833a(localActivity, this.f8502a.f8545k, this.f8502a.f8557w);
            if (this.f8502a.f8517G != null) {
                try {
                    this.f8502a.f8560z.put(this.f8502a.f8517G);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.f8502a.f8556v != null) {
                this.f8502a.f8556v.mo3287a(localActivity);
            }
            if (d <= 1.0d || !z) {
                ActivityService.f8510b.info("Velocity is to low or gps not changed! velocity: " + d);
                this.f8502a.f8542h = Math.min(this.f8502a.f8542h, SystemClock.uptimeMillis());
            } else {
                this.f8502a.f8542h = Long.MAX_VALUE;
            }
            if (((BeastBikes) this.f8502a.getApplication()).b()) {
                long uptimeMillis = SystemClock.uptimeMillis() - this.f8502a.f8542h;
                if (uptimeMillis >= 5000) {
                    ActivityService.f8510b.info("Activity has been paused for " + uptimeMillis + " ms");
                    try {
                        this.f8502a.m9830a(true);
                        obj = 1;
                    } catch (Throwable e2) {
                        ActivityService.f8510b.error("Auto pause failed", e2);
                    }
                    if (0 == j % 10) {
                        if (obj == null) {
                            this.f8502a.m9857k();
                        }
                        synchronized (this.f8502a) {
                            this.f8502a.f8537c.incrementAndGet();
                        }
                    }
                    if (this.f8503b == null && this.f8502a.f8545k != null) {
                        this.f8503b = this.f8502a.f8545k;
                    }
                    if (this.f8502a.f8545k != null && this.f8502a.f8545k.hasAltitude()) {
                        this.f8506e = this.f8502a.f8545k;
                    }
                    ActivityService.f8510b.info("updateActivity: changed=" + z + ", totalDistance = " + localActivity.getTotalDistance() + " ,totalTime = " + localActivity.getTotalElapsedTime());
                }
            }
            obj = null;
            if (0 == j % 10) {
                if (obj == null) {
                    this.f8502a.m9857k();
                }
                synchronized (this.f8502a) {
                    this.f8502a.f8537c.incrementAndGet();
                }
            }
            this.f8503b = this.f8502a.f8545k;
            this.f8506e = this.f8502a.f8545k;
            ActivityService.f8510b.info("updateActivity: changed=" + z + ", totalDistance = " + localActivity.getTotalDistance() + " ,totalTime = " + localActivity.getTotalElapsedTime());
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.biz.ActivityService$d */
    private final class C1911d extends PhoneStateListener {
        /* renamed from: a */
        final /* synthetic */ ActivityService f8508a;

        private C1911d(ActivityService activityService) {
            this.f8508a = activityService;
        }

        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            switch (i) {
                case 0:
                    ActivityService.f8510b.trace("手机空闲起来了");
                    this.f8508a.f8553s = false;
                    return;
                case 1:
                    ActivityService.f8510b.trace("来电");
                    this.f8508a.f8553s = true;
                    return;
                case 2:
                    ActivityService.f8510b.trace("电话挂断...");
                    this.f8508a.f8553s = true;
                    return;
                default:
                    return;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return new C1908a(this);
    }

    public void onCreate() {
        super.onCreate();
        f8510b.info("Creating service " + getClass().getName());
        BeastBikes beastBikes = (BeastBikes) getApplication();
        C1380c d = beastBikes.d();
        this.f8547m = (LocationManager) getSystemService(MapboxEvent.TYPE_LOCATION);
        this.f8548n = (PowerManager) getSystemService("power");
        this.f8551q = (NotificationManager) getSystemService("notification");
        ((TelephonyManager) getSystemService(UserData.PHONE_KEY)).listen(new C1911d(), 32);
        this.f8552r = new C2577t(this);
        this.f8552r.m12901a((C1912b) this);
        this.f8550p = new CoordinateConverter();
        this.f8550p.from(CoordType.GPS);
        this.f8543i = beastBikes.c();
        this.f8544j = this.f8543i.a();
        this.f8546l = new C1918b(d);
        this.f8542h = Long.MAX_VALUE;
        this.f8559y = new C2044c();
        if (AVUser.getCurrentUser() != null) {
            this.f8555u = getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0);
        }
        this.f8558x = AVUser.getCurrentUser();
        if (this.f8544j != null) {
            switch (this.f8544j.getState()) {
                case 0:
                case 4:
                    try {
                        m9861m();
                        break;
                    } catch (Throwable e) {
                        f8510b.error(e.getMessage(), e);
                        break;
                    }
                case 1:
                case 3:
                    f8510b.info("Restore activity " + this.f8544j.getId());
                    this.f8557w = 2;
                    m9851h();
                    m9845d(this.f8544j.getState());
                    break;
                case 2:
                    f8510b.info("Activity " + this.f8544j.getId() + " has been paused");
                    break;
            }
        }
        this.f8539e = new C1910c();
        this.f8540f.scheduleAtFixedRate(this.f8539e, 0, 1000);
        f8510b.info("Service " + getClass().getName() + " created");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent == null) {
            return 1;
        }
        Object stringExtra = intent.getStringExtra(C0861d.f2143o);
        if (TextUtils.isEmpty(stringExtra)) {
            return super.onStartCommand(intent, i, i2);
        }
        TrainCourseDTO trainCourseDTO = (TrainCourseDTO) intent.getSerializableExtra("train_course");
        f8510b.info("Start command " + stringExtra + "#" + i2);
        if (C1454a.a().a(this, "PREF.SENSOR.CADENCE", false) || C1454a.a().a(this, "PREF.SENSOR.POWER", false) || C1454a.a().a(this, "PREF.SENSOR.HR", false) || C1454a.a().a(this, "PREF.SENSOR.SPEED", false) || C1454a.a().a(this, "PREF.SENSOR.SPEED.AND.CADENCE", false)) {
            C1386e.b().c();
            C1386e.b().a(this);
            C1386e.b().a(this);
            C1386e.b().a(this);
        }
        try {
            if ("com.beastbikes.intent.action.ACTIVITY_START".equals(stringExtra)) {
                m9829a(trainCourseDTO);
            } else if ("com.beastbikes.intent.action.ACTIVITY_PAUSE".equals(stringExtra)) {
                m9830a(false);
            } else if ("com.beastbikes.intent.action.ACTIVITY_RESUME".equals(stringExtra)) {
                m9839b(false);
            } else if ("com.beastbikes.intent.action.ACTIVITY_COMPLETE".equals(stringExtra)) {
                m9861m();
            } else if ("com.beastbikes.intent.action.ACTIVITY_PAUSE_OR_RESUME".equals(stringExtra)) {
                m9859l();
            } else if (this.f8544j == null) {
                stopSelf();
            }
        } catch (Throwable e) {
            f8510b.error("Operation error", e);
        }
        return 1;
    }

    public void onDestroy() {
        if (this.f8544j != null) {
            m9854j();
        }
        this.f8540f.cancel();
        this.f8541g.cancel();
        this.f8552r.m12900a();
        super.onDestroy();
    }

    public void onLocationChanged(Location location) {
        f8510b.info("onLocationChanged " + location.toString());
        if (location.getLongitude() != 0.0d && location.getLatitude() != 0.0d) {
            this.f8545k = location;
        }
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        switch (i) {
            case 0:
                f8510b.info("Location provider " + str + " is out of service");
                return;
            case 1:
                f8510b.info("Location provider " + str + " is temporatily unavailable");
                return;
            case 2:
                f8510b.info("Location provider " + str + " is available");
                return;
            default:
                return;
        }
    }

    public void onProviderEnabled(String str) {
        f8510b.info("Location provider " + str + " is enabled");
    }

    public void onProviderDisabled(String str) {
        f8510b.info("Location provider " + str + " is disabled");
    }

    public void onInit(int i) {
        switch (i) {
            case -1:
                f8510b.error("Initialize TTS engine error");
                return;
            case 0:
                f8510b.info("Initialize TTS engine success");
                return;
            default:
                f8510b.warn("Invalid status of TTS engine");
                return;
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        switch (i) {
            case 5:
                f8510b.info("onTrimMemory TRIM_MEMORY_RUNNING_MODERATE");
                return;
            case 10:
                f8510b.info("onTrimMemory TRIM_MEMORY_RUNNING_LOW");
                return;
            case 15:
                f8510b.info("onTrimMemory TRIM_MEMORY_RUNNING_CRITICAL");
                return;
            case 40:
                f8510b.info("onTrimMemory TRIM_MEMORY_BACKGROUND");
                return;
            case 60:
                f8510b.info("onTrimMemory TRIM_MEMORY_MODERATE");
                return;
            case 80:
                f8510b.info("onTrimMemory TRIM_MEMORY_COMPLETE");
                Runtime.getRuntime().gc();
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    public void mo3274b() {
        this.f8518H = false;
        if (this.f8544j != null) {
            this.f8544j.setRealTimeHeartRate(-1);
            this.f8544j.setHeartRateZone(-1);
            this.f8544j.setAvgReserveHeartRate(-1.0d);
            this.f8544j.setMaxHeartRatePercent(-1.0d);
            this.f8544j.setReserveHeartRate(-1.0d);
        }
    }

    /* renamed from: b */
    public void mo3275b(int i) {
        int i2 = 1;
        this.f8518H = true;
        this.f8519I = i;
        if (this.f8544j != null) {
            this.f8544j.setRealTimeHeartRate(i);
            double d = (((double) i) / this.f8516F) * 100.0d;
            this.f8544j.setReserveHeartRate(d / 100.0d);
            if (d >= 66.0d) {
                if (d < 73.0d) {
                    i2 = 2;
                } else if (d < 84.0d) {
                    i2 = 3;
                } else if (d < 91.0d) {
                    i2 = 4;
                } else {
                    i2 = 5;
                }
            }
            this.f8544j.setHeartRateZone(i2);
            double max = Math.max(this.f8544j.getMaxCardiacRate(), (double) i);
            this.f8544j.setMaxCardiacRate(max);
            this.f8544j.setMaxHeartRatePercent(max / this.f8516F);
            max = (this.f8544j.getAvgReserveHeartRate() + ((double) i)) / 2.0d;
            this.f8544j.setCardiacRate(max);
            this.f8544j.setAvgReserveHeartRate(max / this.f8516F);
        }
    }

    /* renamed from: c */
    public void mo3276c() {
        this.f8520J = false;
    }

    /* renamed from: c */
    public void mo3277c(int i) {
        this.f8520J = true;
        this.f8521K = i;
        if (this.f8544j != null) {
            if (i > 0) {
                this.f8544j.setVirtualPower(false);
            }
            this.f8544j.setRealTimePower(i);
        }
    }

    /* renamed from: a */
    public void mo3271a() {
        this.f8522L = false;
        if (this.f8544j != null) {
            this.f8544j.setRealTimeCadence(-1);
        }
    }

    /* renamed from: a */
    public void mo3273a(int i) {
        this.f8522L = true;
        if (this.f8544j != null) {
            this.f8544j.setRealTimeCadence(i);
            this.f8544j.setMaxCadence(Math.max((double) i, this.f8544j.getMaxCadence()));
            this.f8544j.setCadence((this.f8544j.getCadence() + ((double) i)) / 2.0d);
        }
    }

    /* renamed from: a */
    public void mo3272a(double d) {
        this.f8522L = true;
        this.f8523M = d;
        if (this.f8544j != null) {
            this.f8544j.setInstantaneousVelocity(d);
        }
    }

    /* renamed from: h */
    private synchronized void m9851h() {
        f8510b.info("Start sampling");
        if (this.f8549o == null) {
            this.f8549o = this.f8548n.newWakeLock(1, "ActivityService");
            this.f8549o.acquire();
        }
        m9852i();
    }

    /* renamed from: i */
    private void m9852i() {
        try {
            if (!this.f8547m.isProviderEnabled("gps")) {
                f8510b.warn("The device not support GPS_PROVIDER");
            } else if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                f8510b.warn("has no location permission");
            } else {
                this.f8547m.sendExtraCommand("gps", "force_xtra_injection", null);
                this.f8547m.sendExtraCommand("gps", "force_time_injection", null);
                f8510b.warn("gps requestLocationUpdates");
                this.f8547m.requestLocationUpdates("gps", 100, 3.0f, this);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: j */
    private synchronized void m9854j() {
        f8510b.info("Stop sampling");
        if (this.f8549o != null && this.f8549o.isHeld()) {
            this.f8549o.release();
            this.f8549o = null;
        }
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            f8510b.warn("has no location permission");
        } else {
            this.f8547m.removeUpdates(this);
            this.f8545k = null;
        }
    }

    /* renamed from: a */
    private LocalActivitySample m9833a(LocalActivity localActivity, Location location, int i) {
        String id = localActivity.getId();
        int i2 = this.f8537c.get();
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        if (location != null) {
            d2 = location.getAltitude();
            d3 = location.getLatitude();
            d4 = location.getLongitude();
            d = ((double) location.getSpeed()) * 3.6d;
        }
        if (d3 == 0.0d || d4 == 0.0d || d3 == Double.MIN_VALUE || d4 == Double.MIN_VALUE) {
            return null;
        }
        LocalActivitySample localActivitySample = new LocalActivitySample();
        localActivitySample.setLatitude1(String.valueOf(d3));
        localActivitySample.setLongitude1(String.valueOf(d4));
        localActivitySample.setVelocity(d);
        LocalActivitySample a = this.f8559y.m10518a(localActivitySample);
        if (a == null) {
            return null;
        }
        a.setDistance(localActivity.getTotalDistance());
        a.setActivityId(id);
        a.setOrdinal(i2);
        a.setCalorie(localActivity.getTotalCalorie());
        a.setCardiacRate(0.0d);
        a.setId(UUID.randomUUID().toString());
        a.setCurrTime(System.currentTimeMillis());
        a.setTime((double) (((long) i2) * 10));
        a.setElapsedTime(SystemClock.elapsedRealtime());
        a.setUserId(localActivity.getUserId());
        a.setAltitude(String.valueOf(d2));
        a.setCyclingStatus(i);
        a.setPower((double) localActivity.getRealTimePower());
        a.setVerticalSpeed(localActivity.getVerticalSpeed());
        a.setExtend(localActivity);
        return a;
    }

    /* renamed from: k */
    private void m9857k() {
        try {
            this.f8543i.d(this.f8544j);
        } catch (Throwable e) {
            f8510b.error("Update total distance error", e);
        }
        if (this.f8511A == null) {
            this.f8511A = Executors.newSingleThreadExecutor();
        }
        if (this.f8512B == null) {
            this.f8512B = new C19052(this);
        }
        this.f8511A.execute(this.f8512B);
    }

    /* renamed from: d */
    public void m9884d() {
        m9857k();
    }

    /* renamed from: a */
    private synchronized LocalActivity m9829a(TrainCourseDTO trainCourseDTO) throws BusinessException {
        LocalActivity localActivity = null;
        synchronized (this) {
            if (this.f8544j != null) {
                m9861m();
            }
            if (this.f8558x == null) {
                f8510b.error("startActivity error! because no authenticated user found! ");
            } else {
                this.f8513C = this.f8558x.getFtp();
                if (this.f8513C <= 0) {
                    this.f8513C = 200;
                }
                this.f8514D = this.f8558x.getHeight();
                this.f8515E = this.f8558x.getWeight();
                this.f8516F = (double) this.f8558x.getMaxHeartRate();
                String objectId = this.f8558x.getObjectId();
                String uuid = UUID.randomUUID().toString();
                C1915b c1915b = new C1915b();
                localActivity = new LocalActivity();
                if (TextUtils.isEmpty(this.f8558x.getEmail())) {
                    this.f8558x.setEmail("");
                }
                localActivity.setId(uuid);
                localActivity.setCoordinate(CoordinateType.GCJ02);
                localActivity.setEmail(this.f8558x.getEmail());
                localActivity.setStartTime(System.currentTimeMillis());
                localActivity.setFinishTime(0);
                localActivity.setState(1);
                localActivity.setType(ActivityType.CYCLING.ordinal());
                localActivity.setUserId(objectId);
                localActivity.setUsername(this.f8558x.getUsername());
                localActivity.setTotalCalorie(0.0d);
                localActivity.setTotalDistance(0.0d);
                localActivity.setTotalRisenAltitude(0.0d);
                localActivity.setTotalElapsedTime(0.0d);
                localActivity.setTotalUphillDistance(0.0d);
                localActivity.setIsPrivate(0);
                if (trainCourseDTO != null) {
                    localActivity.setCourseId(trainCourseDTO.getId());
                    localActivity.setTrainCourseDate(trainCourseDTO.getTrainCourseTime());
                    localActivity.setCourseTime(trainCourseDTO.getCourseTime());
                    localActivity.setCourseEnName(trainCourseDTO.getEnName());
                    localActivity.setCourseName(trainCourseDTO.getName());
                    localActivity.setCourseTss(trainCourseDTO.getTss());
                    localActivity.setCourseIf((int) trainCourseDTO.getWattsIf());
                }
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    localActivity.setTrainId(currentUser.getTrainingId());
                }
                f8510b.info("Start activity aid=" + uuid + " uid=" + objectId + "\r\n\r\n" + "\n######## ######## ######## ######## ########--------------------------------######## ######## ######## ######## ######## \n######## ######## ######## ######## ########\t\t\t Start Activity \t\t      ######## ######## ######## ######## ######## \n######## ######## ######## ######## ########--------------------------------######## ######## ######## ######## ########");
                if (C1398a.a(this, uuid)) {
                    try {
                        c1915b.m9896a();
                        this.f8543i.f(localActivity);
                        this.f8544j = localActivity;
                        this.f8537c.set(0);
                        m9851h();
                        Intent intent = new Intent();
                        intent.setAction("com.beastbikes.intent.action.ACTIVITY_START");
                        intent.putExtra("activity", localActivity);
                        intent.addCategory("android.intent.category.DEFAULT");
                        sendBroadcast(intent);
                        m9845d(localActivity.getState());
                    } catch (Throwable e) {
                        f8510b.error("Start activity " + uuid + " error", e);
                        try {
                            this.f8543i.e(localActivity);
                        } catch (Throwable e2) {
                            f8510b.error("Delete local activity error", e2);
                        }
                        this.f8544j = null;
                        Toasts.showOnUiThreadWithText(this, getString(C1373R.string.start_cycling_failed));
                        stopSelf();
                        throw new BusinessException(e);
                    }
                }
                f8510b.error("set activityId error aid " + uuid);
            }
        }
        return localActivity;
    }

    /* renamed from: a */
    private synchronized LocalActivity m9830a(boolean z) throws BusinessException {
        if (this.f8544j == null) {
            this.f8544j = this.f8543i.a();
            if (this.f8544j == null) {
                throw new BusinessException("No activity found");
            }
        }
        C1915b c1915b = new C1915b(this.f8544j.getState());
        try {
            c1915b.m9897a(z);
            this.f8544j.setState(c1915b.m9894e());
            this.f8543i.b(this.f8544j);
            this.f8545k = null;
            if (z) {
                m9884d();
                this.f8538d.post(this.f8524N);
            } else {
                m9854j();
                m9884d();
                this.f8539e.f8503b = null;
                this.f8539e.f8506e = null;
            }
            Intent intent = new Intent();
            intent.setAction("com.beastbikes.intent.action.ACTIVITY_PAUSE");
            intent.putExtra("activity", this.f8544j);
            intent.addCategory("android.intent.category.DEFAULT");
            sendBroadcast(intent);
            m9845d(this.f8544j.getState());
            f8510b.info("Pause activity " + this.f8544j.getId() + ", AutoPause = " + z + ", ThreadName = " + Thread.currentThread().getName());
        } catch (Throwable e) {
            f8510b.error("Pause activity " + this.f8544j.getId() + " error", e);
            throw new BusinessException(e);
        }
        return this.f8544j;
    }

    /* renamed from: b */
    private synchronized LocalActivity m9839b(boolean z) throws BusinessException {
        if (this.f8544j == null) {
            this.f8544j = this.f8543i.a();
            if (this.f8544j == null) {
                throw new BusinessException("No activity found");
            }
        }
        C1915b c1915b = new C1915b(this.f8544j.getState());
        try {
            c1915b.m9898b();
            this.f8544j.setState(c1915b.m9894e());
            this.f8543i.b(this.f8544j);
            this.f8542h = Long.MAX_VALUE;
            if (!z) {
                m9851h();
            }
            this.f8557w = z ? 4 : 2;
            Intent intent = new Intent();
            intent.setAction("com.beastbikes.intent.action.ACTIVITY_RESUME");
            intent.putExtra("activity", this.f8544j);
            intent.addCategory("android.intent.category.DEFAULT");
            sendBroadcast(intent);
            m9845d(this.f8544j.getState());
            f8510b.info("Resume activity " + this.f8544j.getId() + ", cyclingState = " + this.f8557w + ", ThreadName = " + Thread.currentThread().getName());
        } catch (Throwable e) {
            f8510b.error("Resume activity " + this.f8544j.getId() + " error", e);
            throw new BusinessException(e);
        }
        return this.f8544j;
    }

    /* renamed from: l */
    private synchronized LocalActivity m9859l() throws BusinessException {
        LocalActivity a;
        if (this.f8544j == null) {
            this.f8544j = this.f8543i.a();
            if (this.f8544j == null) {
                throw new BusinessException("No activity found");
            }
        }
        f8510b.info("Pause/Resume activity " + this.f8544j.getId() + " state =[" + this.f8544j.getState() + "], ThreadName = " + Thread.currentThread().getName());
        try {
            switch (this.f8544j.getState()) {
                case 1:
                    a = m9830a(false);
                    break;
                case 2:
                    a = m9839b(false);
                    break;
                case 3:
                    a = m9839b(false);
                    break;
                default:
                    throw new BusinessException("Invalid activity state");
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Throwable e2) {
            f8510b.error("Pause/Resume activity " + this.f8544j.getId() + " error", e2);
            throw new BusinessException(e2);
        }
        return a;
    }

    /* renamed from: m */
    private synchronized void m9861m() throws BusinessException {
        m9857k();
        C1386e.b().d();
        C1386e.b().b(this);
        C1386e.b().b(this);
        C1386e.b().b(this);
        new C19063(this).execute(new Void[0]);
    }

    /* renamed from: n */
    private void m9863n() {
        this.f8525O.clear();
        this.f8526P.clear();
        this.f8527Q.clear();
        this.f8528R.clear();
        this.f8531U.clear();
        this.f8532V.clear();
        this.f8535Y.clear();
        this.f8536Z.clear();
        this.aa.clear();
        this.ae.clear();
        this.f8517G = null;
    }

    /* renamed from: a */
    private LocalActivity m9828a(LocalActivity localActivity, double d, double d2) {
        double doubleValue;
        double d3 = 1000.0d * (d2 / 3.6d);
        this.f8527Q.add(Double.valueOf(d));
        this.f8528R.add(Double.valueOf(localActivity.getTotalDistance()));
        if (this.f8527Q.size() > 10) {
            doubleValue = (d - ((Double) this.f8527Q.remove(0)).doubleValue()) / (localActivity.getTotalDistance() - ((Double) this.f8528R.remove(0)).doubleValue());
            localActivity.setCurrentSlope(doubleValue);
            doubleValue *= d3;
            if (doubleValue < 0.0d) {
                doubleValue = 0.0d;
            }
            localActivity.setVerticalSpeed(doubleValue);
        }
        doubleValue = localActivity.getVerticalSpeed();
        this.ae.add(Double.valueOf(doubleValue));
        this.af = doubleValue + this.af;
        if (this.ae.size() > 30) {
            this.af -= ((Double) this.ae.remove(0)).doubleValue();
        }
        if (this.af > 0.0d) {
            localActivity.setVerticalSpeedPer30s(this.af / 30.0d);
        }
        return localActivity;
    }

    /* renamed from: b */
    private LocalActivity m9838b(LocalActivity localActivity, double d, double d2) {
        int a;
        if (!this.f8520J || this.f8521K <= 0) {
            this.f8525O.add(Double.valueOf(d));
            this.f8529S += d;
            if (this.f8525O.size() > 5) {
                this.f8529S -= ((Double) this.f8525O.remove(0)).doubleValue();
            }
            this.f8526P.add(Double.valueOf(d));
            this.f8530T += d;
            if (this.f8526P.size() > 10) {
                this.f8530T -= ((Double) this.f8526P.remove(0)).doubleValue();
            }
            this.f8531U.add(Double.valueOf(d2));
            this.f8533W += d2;
            if (this.f8531U.size() > 3) {
                this.f8533W -= ((Double) this.f8531U.remove(0)).doubleValue();
            }
            this.f8532V.add(Double.valueOf(d2));
            this.f8534X += d2;
            if (this.f8532V.size() > 6) {
                this.f8534X -= ((Double) this.f8532V.remove(0)).doubleValue();
            }
            if (this.f8526P.size() >= 10) {
                a = C2045d.m10522a(localActivity.getInstantaneousVelocity(), this.f8514D, this.f8515E, (this.f8529S - (this.f8530T - this.f8529S)) / 5.0d, (this.f8533W - (this.f8534X - this.f8533W)) / 3.0d);
                localActivity.setRealTimePower(a);
                f8510b.info("计算功率, power = " + a);
            }
        } else {
            localActivity.setRealTimePower(this.f8521K);
            this.f8525O.clear();
            this.f8526P.clear();
            this.f8531U.clear();
            this.f8532V.clear();
        }
        int realTimePower = localActivity.getRealTimePower();
        localActivity.setPowerWattsPerKG(((double) realTimePower) / this.f8515E);
        a = (int) ((((float) realTimePower) / ((float) this.f8513C)) * 100.0f);
        localActivity.setPowerFTP(a);
        if (a < 56) {
            a = 1;
        } else if (a < 76) {
            a = 2;
        } else if (a < 91) {
            a = 3;
        } else if (a < 106) {
            a = 4;
        } else if (a < AVException.INVALID_NESTED_KEY) {
            a = 5;
        } else if (a < 150) {
            a = 6;
        } else {
            a = 7;
        }
        localActivity.setPowerZone(a);
        localActivity.setMaxPower(Math.max(localActivity.getMaxPower(), (double) realTimePower));
        if (realTimePower > 0) {
            a = localActivity.getSampleCount();
            localActivity.setAvgPower((double) ((int) (((localActivity.getAvgPower() * ((double) a)) + ((double) realTimePower)) / ((double) (a + 1)))));
            localActivity.setSampleCount(a + 1);
        }
        f8510b.trace("LocalActivity Power, power = " + realTimePower + ", maxPower = " + Math.max(localActivity.getMaxPower(), (double) realTimePower) + ", avgPower = " + localActivity.getAvgPower());
        this.f8535Y.add(Integer.valueOf(realTimePower));
        this.ab += realTimePower;
        if (this.f8535Y.size() > 3) {
            this.ab -= ((Integer) this.f8535Y.remove(0)).intValue();
        }
        if (this.ab > 0) {
            localActivity.setAvgPowerPer3s(this.ab / 3);
        }
        this.f8536Z.add(Integer.valueOf(realTimePower));
        this.ac += realTimePower;
        if (this.f8536Z.size() > 10) {
            this.ac -= ((Integer) this.f8536Z.remove(0)).intValue();
        }
        if (this.ac > 0) {
            localActivity.setAvgPowerPer10s(this.ac / 10);
        }
        this.aa.add(Integer.valueOf(realTimePower));
        this.ad += realTimePower;
        if (this.aa.size() > 30) {
            this.ad -= ((Integer) this.aa.remove(0)).intValue();
        }
        if (this.ad > 0) {
            localActivity.setAvgPowerPer30s(this.ad / 30);
        }
        double totalAvgPower30s = localActivity.getTotalAvgPower30s() + Math.pow((double) this.ad, 4.0d);
        localActivity.setTotalAvgPower30s(totalAvgPower30s);
        localActivity.setStandardPower((int) Math.pow(totalAvgPower30s / localActivity.getTotalElapsedTime(), 0.0d));
        return localActivity;
    }

    /* renamed from: o */
    private void m9864o() {
        int hashCode = getClass().getName().hashCode();
        if (this.f8551q != null) {
            this.f8551q.cancel(hashCode);
        }
        stopForeground(true);
        if (this.f8555u != null) {
            this.f8555u.edit().putLong("beast.home.nav.cycling.state", System.currentTimeMillis()).apply();
        }
    }

    /* renamed from: d */
    private void m9845d(int i) {
        Notification notification = null;
        if (i == 3 || i == 2) {
            notification = C2572q.m12886a(C1373R.string.notification_riding_stop);
            notification.tickerText = getText(C1373R.string.notification_riding_stop);
        } else if (i == 1) {
            notification = C2572q.m12886a(C1373R.string.notification_riding);
            notification.tickerText = getText(C1373R.string.notification_riding);
        }
        if (notification != null) {
            int hashCode = getClass().getName().hashCode();
            this.f8551q.cancel(hashCode);
            startForeground(hashCode, notification);
            f8510b.info("showNotification startForeground, notifyId = " + hashCode);
            if (this.f8555u != null) {
                this.f8555u.edit().putLong("beast.home.nav.cycling.state", System.currentTimeMillis()).apply();
            }
        }
    }

    /* renamed from: e */
    public void mo3278e() {
        f8510b.info("ActivityService: screen onScreenOn");
        f8509a = true;
        this.ag = System.currentTimeMillis();
    }

    /* renamed from: f */
    public void mo3279f() {
        f8509a = true;
        Intent intent = new Intent(this, EmptyActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
        try {
            f8509a = false;
            this.ag = System.currentTimeMillis();
            BeastBikes beastBikes = (BeastBikes) getApplication();
            f8510b.info("ActivityService: screen onScreenOff, Background Enable is " + beastBikes.e());
            if (beastBikes.e()) {
                this.f8541g.scheduleAtFixedRate(new C19074(this), 0, 20000);
            } else {
                f8510b.info("ActivityService: screen the activity is foreground...");
            }
        } catch (Exception e) {
            f8510b.error("onScreenOff error");
        }
    }

    /* renamed from: a */
    public void m9879a(C1909b c1909b) {
        this.f8556v = c1909b;
    }
}

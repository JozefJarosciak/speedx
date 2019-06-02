package com.avos.avoscloud;

import android.content.Context;
import com.avos.avoscloud.LogUtil.log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class AnalyticsOnlineConfig {
    protected Map<String, String> config = new HashMap();
    private boolean enableStats = true;
    private final AnalyticsImpl parent;
    private ReportPolicy reportPolicy = ReportPolicy.SEND_INTERVAL;

    public AnalyticsOnlineConfig(AnalyticsImpl analyticsImpl) {
        this.parent = analyticsImpl;
    }

    public void update(Context context) {
        update(context, true);
    }

    public void update(Context context, final boolean z) {
        PaasClient.statistisInstance().getObject(String.format("statistics/apps/%s/sendPolicy", new Object[]{AVOSCloud.applicationId}), null, false, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                Object obj = null;
                try {
                    Map mapFromString = JSONHelper.mapFromString(str);
                    Object obj2 = mapFromString.get("parameters");
                    if (obj2 != null && (obj2 instanceof Map)) {
                        Map map = (Map) obj2;
                        if (!AnalyticsOnlineConfig.this.config.equals(map)) {
                            obj = 1;
                        }
                        AnalyticsOnlineConfig.this.config.clear();
                        AnalyticsOnlineConfig.this.config.putAll(map);
                        AnalyticsOnlineConfig.this.parent.notifyOnlineConfigListener(new JSONObject(AnalyticsOnlineConfig.this.config));
                    }
                    if (z) {
                        Boolean bool = (Boolean) mapFromString.get("enable");
                        if (bool != null) {
                            AnalyticsOnlineConfig.this.enableStats = bool.booleanValue();
                        }
                        Number number = (Number) mapFromString.get("policy");
                        if (number != null) {
                            ReportPolicy access$200 = AnalyticsOnlineConfig.this.reportPolicy;
                            ReportPolicy valueOf = ReportPolicy.valueOf(number.intValue());
                            if (access$200 != valueOf || r1 != null) {
                                AnalyticsOnlineConfig.this.parent.setReportPolicy(valueOf);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Throwable th, String str) {
                log.m3519e("Failed " + str);
            }
        });
    }

    public boolean isEnableStats() {
        return this.enableStats;
    }

    public void setEnableStats(boolean z) {
        this.enableStats = z;
    }

    public boolean setReportPolicy(ReportPolicy reportPolicy) {
        boolean z = this.reportPolicy.value() != reportPolicy.value();
        this.reportPolicy = reportPolicy;
        return z;
    }

    public ReportPolicy getReportPolicy() {
        return this.reportPolicy;
    }

    public String getConfigParams(String str) {
        Object obj = this.config.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }
}

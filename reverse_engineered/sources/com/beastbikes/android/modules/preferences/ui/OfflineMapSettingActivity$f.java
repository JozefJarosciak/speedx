package com.beastbikes.android.modules.preferences.ui;

import android.widget.ExpandableListAdapter;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class OfflineMapSettingActivity$f {
    /* renamed from: a */
    String f10946a;
    /* renamed from: b */
    private final MKOLSearchRecord f10947b;
    /* renamed from: c */
    private final OfflineMapSettingActivity f10948c;

    public OfflineMapSettingActivity$f(OfflineMapSettingActivity offlineMapSettingActivity, MKOLSearchRecord mKOLSearchRecord) {
        this.f10947b = mKOLSearchRecord;
        this.f10948c = offlineMapSettingActivity;
        MKOLUpdateElement updateInfo;
        switch (mKOLSearchRecord.cityType) {
            case 1:
                Iterator it = this.f10947b.childCities.iterator();
                float f = 0.0f;
                float f2 = 0.0f;
                int i = 0;
                while (it.hasNext()) {
                    MKOLSearchRecord mKOLSearchRecord2 = (MKOLSearchRecord) it.next();
                    f2 += (float) mKOLSearchRecord2.size;
                    MKOLUpdateElement updateInfo2 = OfflineMapSettingActivity.c(offlineMapSettingActivity).getUpdateInfo(mKOLSearchRecord2.cityID);
                    if (updateInfo2 != null) {
                        int i2;
                        float f3 = ((float) (mKOLSearchRecord2.size * (updateInfo2.ratio / 100))) + f;
                        if (updateInfo2.ratio >= 100) {
                            i2 = i + 1;
                        } else {
                            i2 = i;
                        }
                        i = i2;
                        f = f3;
                    }
                }
                if (i >= this.f10947b.childCities.size()) {
                    this.f10946a = offlineMapSettingActivity.getString(C1373R.string.offline_map_setting_activity_already_downloaded);
                } else if (f > 0.0f) {
                    this.f10946a = String.format("%d%%", new Object[]{Integer.valueOf((int) ((f * 100.0f) / f2))});
                } else {
                    this.f10946a = offlineMapSettingActivity.getString(C1373R.string.offline_map_setting_activity_download);
                }
                updateInfo = OfflineMapSettingActivity.c(offlineMapSettingActivity).getUpdateInfo(mKOLSearchRecord.cityID);
                if (updateInfo != null && updateInfo.update) {
                    this.f10946a = offlineMapSettingActivity.getString(C1373R.string.offline_map_setting_activity_already_downloaded);
                    return;
                }
                return;
            default:
                if (offlineMapSettingActivity != null) {
                    updateInfo = OfflineMapSettingActivity.c(offlineMapSettingActivity).getUpdateInfo(mKOLSearchRecord.cityID);
                    if (updateInfo != null) {
                        if (updateInfo.ratio >= 100) {
                            this.f10946a = offlineMapSettingActivity.getString(C1373R.string.offline_map_setting_activity_already_downloaded);
                        } else if (updateInfo.ratio <= 0) {
                            this.f10946a = offlineMapSettingActivity.getString(C1373R.string.offline_map_setting_activity_download);
                        } else {
                            this.f10946a = String.format("%d%%", new Object[]{Integer.valueOf(updateInfo.ratio)});
                        }
                        if (updateInfo.update) {
                            this.f10946a = offlineMapSettingActivity.getString(C1373R.string.offline_map_setting_activity_already_downloaded);
                            return;
                        }
                        return;
                    }
                    this.f10946a = offlineMapSettingActivity.getString(C1373R.string.offline_map_setting_activity_download);
                    return;
                }
                return;
        }
    }

    /* renamed from: a */
    public boolean m11819a() {
        return 1 == this.f10947b.cityType;
    }

    /* renamed from: a */
    public MKOLSearchRecord m11817a(int i) {
        if (!m11819a()) {
            return null;
        }
        Iterator it = this.f10947b.childCities.iterator();
        while (it.hasNext()) {
            MKOLSearchRecord mKOLSearchRecord = (MKOLSearchRecord) it.next();
            if (mKOLSearchRecord.cityID == i) {
                return mKOLSearchRecord;
            }
        }
        return null;
    }

    public void onClick(MKOfflineMap mKOfflineMap, ExpandableListAdapter expandableListAdapter) {
        int i = 0;
        if (!m11819a()) {
            MKOLUpdateElement updateInfo = mKOfflineMap.getUpdateInfo(this.f10947b.cityID);
            if (updateInfo != null) {
                switch (updateInfo.status) {
                    case 0:
                        OfflineMapSettingActivity.a(this.f10948c, this, new int[]{this.f10947b.cityID});
                        break;
                    case 1:
                        mKOfflineMap.pause(this.f10947b.cityID);
                        Toasts.show(this.f10948c, (int) C1373R.string.offline_map_setting_activity_toast_pause_downloading);
                        break;
                    case 3:
                        OfflineMapSettingActivity.a(this.f10948c, this, new int[]{this.f10947b.cityID});
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
            }
            OfflineMapSettingActivity.a(this.f10948c, this, new int[]{this.f10947b.cityID});
            return;
        }
        Iterator it = this.f10947b.childCities.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            MKOLUpdateElement updateInfo2 = OfflineMapSettingActivity.c(this.f10948c).getUpdateInfo(((MKOLSearchRecord) it.next()).cityID);
            if (updateInfo2 != null) {
                int i4;
                if (updateInfo2.ratio >= 100) {
                    i4 = i2 + 1;
                } else {
                    i4 = i2;
                }
                switch (updateInfo2.status) {
                    case 1:
                    case 2:
                        i2 = i3 + 1;
                        break;
                    default:
                        i2 = i3;
                        break;
                }
                i3 = i2;
                i2 = i4;
            }
        }
        if (i2 >= this.f10947b.childCities.size()) {
            Toasts.show(this.f10948c, (int) C1373R.string.offline_map_setting_activity_already_downloaded);
            return;
        } else if (i3 > 0) {
            Toasts.show(this.f10948c, (int) C1373R.string.offline_map_setting_activity_toast_pause_downloading);
            Iterator it2 = this.f10947b.childCities.iterator();
            while (it2.hasNext()) {
                OfflineMapSettingActivity.c(this.f10948c).pause(((MKOLSearchRecord) it2.next()).cityID);
            }
        } else {
            List arrayList = new ArrayList();
            Iterator it3 = this.f10947b.childCities.iterator();
            while (it3.hasNext()) {
                MKOLSearchRecord mKOLSearchRecord = (MKOLSearchRecord) it3.next();
                MKOLUpdateElement updateInfo3 = OfflineMapSettingActivity.c(this.f10948c).getUpdateInfo(mKOLSearchRecord.cityID);
                if (updateInfo3 == null || updateInfo3.ratio < 100) {
                    arrayList.add(Integer.valueOf(mKOLSearchRecord.cityID));
                }
            }
            if (!arrayList.isEmpty()) {
                int[] iArr = new int[arrayList.size()];
                while (i < iArr.length) {
                    iArr[i] = ((Integer) arrayList.get(i)).intValue();
                    i++;
                }
                OfflineMapSettingActivity.a(this.f10948c, this, iArr);
            } else {
                return;
            }
        }
        ((OfflineMapSettingActivity$d) expandableListAdapter).notifyDataSetChanged();
    }

    /* renamed from: b */
    public void m11820b() {
        Iterator it = this.f10947b.childCities.iterator();
        float f = 0.0f;
        float f2 = 0.0f;
        while (it.hasNext()) {
            MKOLSearchRecord mKOLSearchRecord = (MKOLSearchRecord) it.next();
            f2 += (float) mKOLSearchRecord.size;
            MKOLUpdateElement updateInfo = OfflineMapSettingActivity.c(this.f10948c).getUpdateInfo(mKOLSearchRecord.cityID);
            if (updateInfo != null) {
                f = (((float) mKOLSearchRecord.size) * (((float) updateInfo.ratio) / 100.0f)) + f;
            }
        }
        if (f >= f2) {
            this.f10946a = this.f10948c.getString(C1373R.string.offline_map_setting_activity_already_downloaded);
        } else if (f > 0.0f) {
            this.f10946a = String.format("%d%%", new Object[]{Integer.valueOf((int) ((f / f2) * 100.0f))});
        } else {
            this.f10946a = this.f10948c.getString(C1373R.string.offline_map_setting_activity_download);
        }
    }

    /* renamed from: a */
    public void m11818a(MKOLUpdateElement mKOLUpdateElement) {
        if (mKOLUpdateElement.ratio >= 100) {
            this.f10946a = this.f10948c.getString(C1373R.string.offline_map_setting_activity_already_downloaded);
        } else if (mKOLUpdateElement.ratio <= 0) {
            this.f10946a = this.f10948c.getString(C1373R.string.offline_map_setting_activity_download);
        } else {
            this.f10946a = String.format("%d%%", new Object[]{Integer.valueOf(mKOLUpdateElement.ratio)});
        }
    }
}

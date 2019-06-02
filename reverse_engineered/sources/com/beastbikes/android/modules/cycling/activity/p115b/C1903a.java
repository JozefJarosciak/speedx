package com.beastbikes.android.modules.cycling.activity.p115b;

import android.os.AsyncTask;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.beastbikes.android.modules.cycling.activity.ui.MapFragment;
import com.beastbikes.android.modules.cycling.activity.view.C2046a;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.modules.cycling.stage.dto.StagePointDTO;
import com.beastbikes.android.modules.cycling.stage.p070a.C2236a;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.framework.android.p056e.C2794a;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: MapFragPresenter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.b.a */
public class C1903a {
    /* renamed from: a */
    private static final Logger f8493a = LoggerFactory.getLogger(C1903a.class);
    /* renamed from: b */
    private C2046a f8494b;
    /* renamed from: c */
    private C2794a f8495c;
    /* renamed from: d */
    private C2236a f8496d;

    public C1903a(C2046a c2046a) {
        this.f8494b = c2046a;
        MapFragment a = c2046a.m10527a();
        this.f8495c = a.getAsyncTaskQueue();
        this.f8496d = new C2236a(a.getActivity());
    }

    /* renamed from: a */
    public void m9812a(final long j) {
        this.f8495c.m13740a(new AsyncTask<Long, Void, StageDTO>(this) {
            /* renamed from: b */
            final /* synthetic */ C1903a f8492b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m9806a((Long[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m9807a((StageDTO) obj);
            }

            /* renamed from: a */
            protected StageDTO m9806a(Long... lArr) {
                return this.f8492b.f8496d.m11506b(j);
            }

            /* renamed from: a */
            protected void m9807a(StageDTO stageDTO) {
                if (stageDTO == null) {
                    C1903a.f8493a.error("获取使用赛段的缓存信息失败， stageId = " + j);
                } else {
                    this.f8492b.f8494b.m10528a(stageDTO);
                }
            }
        }, new Long[0]);
    }

    /* renamed from: a */
    public List<LatLng> m9811a(List<StagePointDTO> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.from(CoordType.GPS);
        List<LatLng> arrayList = new ArrayList();
        for (StagePointDTO stagePointDTO : list) {
            double latitude = stagePointDTO.getLatitude();
            double longitude = stagePointDTO.getLongitude();
            if (latitude <= 90.0d && latitude >= -90.0d && longitude <= 180.0d && longitude >= -180.0d) {
                coordinateConverter.coord(new LatLng(latitude, longitude));
                arrayList.add(coordinateConverter.convert());
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public List<com.google.android.gms.maps.model.LatLng> m9813b(List<StagePointDTO> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<com.google.android.gms.maps.model.LatLng> arrayList = new ArrayList();
        for (StagePointDTO stagePointDTO : list) {
            double latitude = stagePointDTO.getLatitude();
            double longitude = stagePointDTO.getLongitude();
            if (latitude <= 90.0d && latitude >= -90.0d && longitude <= 180.0d && longitude >= -180.0d) {
                com.google.android.gms.maps.model.LatLng a = C2558g.m12841a(latitude, longitude);
                arrayList.add(new com.google.android.gms.maps.model.LatLng(a.latitude, a.longitude));
            }
        }
        return arrayList;
    }
}

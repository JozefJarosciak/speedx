package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.baidu.mapapi.map.BaiduMap.SnapshotReadyCallback;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteNodeDTO;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.p062c.C1882d;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.business.BusinessException;
import java.io.File;
import java.io.IOException;
import java.util.List;

class RoutePlanActivity$9 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ RouteDTO f10327a;
    /* renamed from: b */
    final /* synthetic */ List f10328b;
    /* renamed from: c */
    final /* synthetic */ RoutePlanActivity f10329c;

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RoutePlanActivity$9$1 */
    class C21961 implements SnapshotReadyCallback {
        /* renamed from: a */
        final /* synthetic */ RoutePlanActivity$9 f10326a;

        /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RoutePlanActivity$9$1$1 */
        class C21951 implements C1882d {
            /* renamed from: a */
            final /* synthetic */ C21961 f10325a;

            /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RoutePlanActivity$9$1$1$1 */
            class C21941 extends AsyncTask<List<PoiInfoDTO>, Void, Boolean> {
                /* renamed from: a */
                final /* synthetic */ C21951 f10324a;

                C21941(C21951 c21951) {
                    this.f10324a = c21951;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m11253a((List[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m11254a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m11253a(List<PoiInfoDTO>... listArr) {
                    List list = listArr[0];
                    int i = 0;
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        long j = (long) i2;
                        PoiInfoDTO poiInfoDTO = (PoiInfoDTO) list.get(i2);
                        RouteNodeDTO routeNodeDTO = new RouteNodeDTO();
                        routeNodeDTO.setName(poiInfoDTO.getName());
                        routeNodeDTO.setKeyNode(j);
                        routeNodeDTO.setOrdinal((j << 48) | ((long) i));
                        routeNodeDTO.setAltitude(0.0d);
                        routeNodeDTO.setLatitude(poiInfoDTO.getLatitude());
                        routeNodeDTO.setLongitude(poiInfoDTO.getLongitude());
                        routeNodeDTO.setCoordinate("bd09ll");
                        i++;
                        this.f10324a.f10325a.f10326a.f10329c.f5564a.add(routeNodeDTO);
                    }
                    try {
                        if (TextUtils.isEmpty(RoutePlanActivity.o(this.f10324a.f10325a.f10326a.f10329c))) {
                            return Boolean.valueOf(RoutePlanActivity.l(this.f10324a.f10325a.f10326a.f10329c).m11202b(this.f10324a.f10325a.f10326a.f10327a, this.f10324a.f10325a.f10326a.f10329c.f5564a, this.f10324a.f10325a.f10326a.f10329c.f5565b));
                        }
                        this.f10324a.f10325a.f10326a.f10327a.setId(RoutePlanActivity.o(this.f10324a.f10325a.f10326a.f10329c));
                        return Boolean.valueOf(RoutePlanActivity.l(this.f10324a.f10325a.f10326a.f10329c).m11198a(this.f10324a.f10325a.f10326a.f10327a, this.f10324a.f10325a.f10326a.f10329c.f5564a, this.f10324a.f10325a.f10326a.f10329c.f5565b));
                    } catch (BusinessException e) {
                        return Boolean.valueOf(false);
                    }
                }

                /* renamed from: a */
                protected void m11254a(Boolean bool) {
                    RoutePlanActivity.p(this.f10324a.f10325a.f10326a.f10329c).setClickable(true);
                    RoutePlanActivity.q(this.f10324a.f10325a.f10326a.f10329c).setVisibility(8);
                    if (!(TextUtils.isEmpty(RoutePlanActivity.o(this.f10324a.f10325a.f10326a.f10329c)) || RoutePlanActivity.r(this.f10324a.f10325a.f10326a.f10329c) == null || !RoutePlanActivity.r(this.f10324a.f10325a.f10326a.f10329c).contains(RoutePlanActivity.o(this.f10324a.f10325a.f10326a.f10329c)))) {
                        RoutePlanActivity.r(this.f10324a.f10325a.f10326a.f10329c).edit().remove(RoutePlanActivity.o(this.f10324a.f10325a.f10326a.f10329c)).apply();
                    }
                    if (bool != null && bool.booleanValue()) {
                        this.f10324a.f10325a.f10326a.f10329c.setResult(-1, new Intent());
                        this.f10324a.f10325a.f10326a.f10329c.finish();
                    }
                }
            }

            C21951(C21961 c21961) {
                this.f10325a = c21961;
            }

            /* renamed from: a */
            public void mo3362a(String str) {
                if (this.f10325a.f10326a.f10329c != null) {
                    this.f10325a.f10326a.f10329c.getAsyncTaskQueue().m13740a(new C21941(this), this.f10325a.f10326a.f10328b);
                }
            }

            /* renamed from: a */
            public void mo3361a() {
            }
        }

        C21961(RoutePlanActivity$9 routePlanActivity$9) {
            this.f10326a = routePlanActivity$9;
        }

        public void onSnapshotReady(Bitmap bitmap) {
            String str = "";
            if (bitmap != null) {
                RoutePlanActivity.a(this.f10326a.f10329c, C2553b.m12782a(bitmap, this.f10326a.f10327a.getName()));
                C2798a.m13751a(RoutePlanActivity.n(this.f10326a.f10329c));
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
            File file = new File(RoutePlanActivity.n(this.f10326a.f10329c));
            try {
                this.f10326a.f10329c.f5565b = C2798a.m13750a(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            C1880a c1880a = new C1880a(this.f10326a.f10329c);
            String str2 = c1880a.m9743d() + this.f10326a.f10329c.f5565b;
            c1880a.m9737a(new C21951(this));
            c1880a.m9740a(str2, RoutePlanActivity.n(this.f10326a.f10329c), str2);
        }
    }

    RoutePlanActivity$9(RoutePlanActivity routePlanActivity, RouteDTO routeDTO, List list) {
        this.f10329c = routePlanActivity;
        this.f10327a = routeDTO;
        this.f10328b = list;
    }

    public void run() {
        RoutePlanActivity.h(this.f10329c).snapshot(new C21961(this));
    }
}

package com.beastbikes.android.modules.cycling.stage.p135c;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.beastbikes.android.modules.cycling.stage.dto.C2263b;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.modules.cycling.stage.p070a.C2236a;
import com.beastbikes.android.modules.cycling.stage.p136e.C2266a;
import com.beastbikes.android.modules.cycling.stage.p136e.C2266a.C2248a;
import com.beastbikes.android.modules.cycling.stage.p137d.C2259c;
import com.beastbikes.android.modules.map.C2296d.C1950e;
import com.beastbikes.android.modules.map.SpeedxMap;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.p168e.C2655d;
import com.beastbikes.android.widget.p168e.p169a.C2641b;
import java.util.ArrayList;

/* compiled from: StageMapPresenter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.c.c */
public class C2251c {
    /* renamed from: a */
    private C2259c f10715a;
    /* renamed from: b */
    private C2236a f10716b;
    /* renamed from: c */
    private AsyncTask<Void, Void, C2263b> f10717c;
    /* renamed from: d */
    private C2641b f10718d;
    /* renamed from: e */
    private C2655d f10719e;

    public C2251c(C2259c c2259c) {
        this.f10715a = c2259c;
        this.f10716b = new C2236a(c2259c.m11576g());
    }

    /* renamed from: a */
    public void m11538a(final int i) {
        if (this.f10717c != null) {
            this.f10717c.cancel(true);
        }
        this.f10717c = new AsyncTask<Void, Void, C2263b>(this) {
            /* renamed from: b */
            final /* synthetic */ C2251c f10695b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11520a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11521a((C2263b) obj);
            }

            protected void onPreExecute() {
            }

            /* renamed from: a */
            protected C2263b m11520a(Void... voidArr) {
                try {
                    return this.f10695b.f10716b.m11500a(i, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m11521a(C2263b c2263b) {
                if (c2263b == null) {
                    this.f10695b.f10715a.m11578j();
                } else {
                    this.f10695b.f10715a.m11568a(c2263b);
                }
            }
        };
        this.f10715a.m11576g().getAsyncTaskQueue().m13740a(this.f10717c, new Void[0]);
    }

    /* renamed from: a */
    public void m11537a() {
        final double c = this.f10715a.m11572c();
        final double d = this.f10715a.m11573d();
        final int e = this.f10715a.m11574e();
        this.f10715a.m11576g().getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, ArrayList<StageDTO>>(this) {
            /* renamed from: d */
            final /* synthetic */ C2251c f10699d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11522a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11523a((ArrayList) obj);
            }

            protected void onPreExecute() {
            }

            /* renamed from: a */
            protected ArrayList<StageDTO> m11522a(Void... voidArr) {
                try {
                    return this.f10699d.f10716b.m11502a(d + "," + c, e);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m11523a(ArrayList<StageDTO> arrayList) {
                if (arrayList == null || arrayList.size() == 0) {
                    this.f10699d.f10715a.m11577h();
                } else {
                    this.f10699d.f10715a.m11569a((ArrayList) arrayList);
                }
            }
        }, new Void[0]);
    }

    /* renamed from: b */
    public void m11542b() {
        final int f = this.f10715a.m11575f();
        this.f10715a.m11576g().getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, StageDTO>(this) {
            /* renamed from: b */
            final /* synthetic */ C2251c f10701b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11524a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11525a((StageDTO) obj);
            }

            protected void onPreExecute() {
                this.f10701b.f10715a.m11566a();
            }

            /* renamed from: a */
            protected StageDTO m11524a(Void... voidArr) {
                try {
                    return this.f10701b.f10716b.m11499a((long) f);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m11525a(StageDTO stageDTO) {
                this.f10701b.f10715a.m11571b();
                this.f10701b.f10715a.m11567a(stageDTO);
            }
        }, new Void[0]);
    }

    /* renamed from: a */
    public void m11539a(final int i, final boolean z) {
        this.f10715a.m11576g().getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, Integer>(this) {
            /* renamed from: c */
            final /* synthetic */ C2251c f10704c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11526a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11527a((Integer) obj);
            }

            /* renamed from: a */
            protected Integer m11526a(Void... voidArr) {
                return Integer.valueOf(this.f10704c.f10716b.m11498a(i, z));
            }

            /* renamed from: a */
            protected void m11527a(Integer num) {
                if (num.intValue() == 0) {
                    this.f10704c.f10715a.m11579k();
                } else if (num.intValue() == -1) {
                    this.f10704c.f10715a.m11570a(false, i);
                } else {
                    this.f10704c.f10715a.m11570a(true, num.intValue());
                }
            }
        }, new Void[0]);
    }

    /* renamed from: a */
    public void m11540a(SpeedxMap speedxMap, LinearLayout linearLayout, View view, View view2, View view3) {
        m11541a(speedxMap, linearLayout, view, view2, view3, false, 0);
    }

    /* renamed from: a */
    public void m11541a(SpeedxMap speedxMap, LinearLayout linearLayout, View view, View view2, View view3, boolean z, int i) {
        if (this.f10718d == null) {
            this.f10715a.m11566a();
            final View view4 = view;
            final View view5 = view2;
            final boolean z2 = z;
            final int i2 = i;
            final LinearLayout linearLayout2 = linearLayout;
            final View view6 = view3;
            speedxMap.m11665a(new C1950e(this) {
                /* renamed from: g */
                final /* synthetic */ C2251c f10714g;

                /* compiled from: StageMapPresenter */
                /* renamed from: com.beastbikes.android.modules.cycling.stage.c.c$5$1 */
                class C22491 implements C2248a {
                    /* renamed from: a */
                    final /* synthetic */ C22505 f10707a;

                    C22491(C22505 c22505) {
                        this.f10707a = c22505;
                    }

                    /* renamed from: a */
                    public void mo3437a(final Bitmap bitmap) {
                        if (bitmap != null) {
                            this.f10707a.f10714g.f10715a.m11576g().runOnUiThread(new Runnable(this) {
                                /* renamed from: b */
                                final /* synthetic */ C22491 f10706b;

                                public void run() {
                                    Object c = C2553b.m12787c(bitmap);
                                    if (!TextUtils.isEmpty(c)) {
                                        this.f10706b.f10707a.f10714g.f10718d = new C2641b();
                                        this.f10706b.f10707a.f10714g.f10718d.m13156a(c);
                                        this.f10706b.f10707a.f10714g.f10719e = new C2655d(this.f10706b.f10707a.f10714g.f10715a.m11576g(), this.f10706b.f10707a.f10714g.f10718d, "赛段详情");
                                        this.f10706b.f10707a.f10714g.f10719e.showAtLocation(view6, 81, 0, 0);
                                        this.f10706b.f10707a.f10714g.f10718d = null;
                                        this.f10706b.f10707a.f10714g.f10715a.m11571b();
                                    }
                                }
                            });
                        }
                    }
                }

                /* renamed from: a */
                public void mo3296a(Bitmap bitmap) {
                    if (bitmap != null) {
                        Bitmap a = C2553b.m12779a(view4);
                        Bitmap a2 = C2553b.m12779a(view5);
                        View c2266a = new C2266a(this.f10714g.f10715a.m11576g(), z2, i2);
                        linearLayout2.removeAllViews();
                        linearLayout2.addView(c2266a);
                        c2266a.m11615a(a, a2, bitmap, new C22491(this));
                    }
                }
            });
        } else {
            this.f10719e = new C2655d(this.f10715a.m11576g(), this.f10718d, "赛段详情");
            this.f10719e.showAtLocation(view3, 81, 0, 0);
        }
        C2580w.m12905a(this.f10715a.m11576g(), "分享赛段详情", "click_ridding_history_share_data_report");
    }
}

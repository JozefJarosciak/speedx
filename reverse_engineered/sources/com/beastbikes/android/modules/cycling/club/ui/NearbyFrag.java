package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.ListView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager.CLUB_ORDERBY;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.utils.C2562j;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.lib.frag.FragBaseList;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HttpRequestExecutor;

public class NearbyFrag extends FragBaseList<String, ClubInfoCompact, ListView> implements BDLocationListener {
    /* renamed from: a */
    private ClubManager f9816a;
    /* renamed from: b */
    private int f9817b = 1;
    /* renamed from: c */
    private final int f9818c = 20;
    /* renamed from: d */
    private String f9819d;
    /* renamed from: e */
    private String f9820e;
    /* renamed from: f */
    private C1802i f9821f;
    /* renamed from: g */
    private List<ClubInfoCompact> f9822g;
    /* renamed from: h */
    private C2085a f9823h;
    /* renamed from: i */
    private LocationClient f9824i;
    /* renamed from: j */
    private LocationClientOption f9825j;
    /* renamed from: k */
    private boolean f9826k = false;
    /* renamed from: l */
    private final Handler f9827l = new Handler(Looper.getMainLooper());

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.NearbyFrag$a */
    public interface C2085a {
        /* renamed from: c */
        void mo3377c();
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.NearbyFrag$1 */
    class C21021 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ NearbyFrag f9814a;

        C21021(NearbyFrag nearbyFrag) {
            this.f9814a = nearbyFrag;
        }

        public void run() {
            if (TextUtils.isEmpty(this.f9814a.f9819d) && TextUtils.isEmpty(this.f9814a.f9820e)) {
                this.f9814a.f9826k = true;
                this.f9814a.f9824i.stop();
                this.f9814a.m10887a();
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.NearbyFrag$2 */
    class C21032 extends AsyncTask<String, Void, List<ClubInfoCompact>> {
        /* renamed from: a */
        final /* synthetic */ NearbyFrag f9815a;

        C21032(NearbyFrag nearbyFrag) {
            this.f9815a = nearbyFrag;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m10884a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m10885a((List) obj);
        }

        /* renamed from: a */
        protected List<ClubInfoCompact> m10884a(String... strArr) {
            try {
                if (this.f9815a.f9826k) {
                    return this.f9815a.f9816a.m10538a(CLUB_ORDERBY.RECOMMEND, "", "", "", this.f9815a.f9817b, 20);
                }
                return this.f9815a.f9816a.m10538a(CLUB_ORDERBY.NONE, this.f9815a.f9819d, this.f9815a.f9820e, null, this.f9815a.f9817b, 20);
            } catch (BusinessException e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m10885a(List<ClubInfoCompact> list) {
            if (!(this.f9815a.getActivity() == null || this.f9815a.getActivity().isFinishing() || this.f9815a.f9821f == null)) {
                this.f9815a.f9821f.dismiss();
            }
            if (list != null && !list.isEmpty()) {
                this.f9815a.f9822g.addAll(list);
                this.f9815a.onLoadSucessfully(this.f9815a.f9822g);
            } else if (this.f9815a.getActivity() != null) {
                if (this.f9815a.f9817b == 1 && this.f9815a.f9823h != null) {
                    this.f9815a.f9823h.mo3377c();
                }
                if (this.f9815a.f9817b > 1) {
                    this.f9815a.onLoadFailed(this.f9815a.getActivity().getString(C1373R.string.club_discover_load_end));
                }
                this.f9815a.pullView.disablePull();
            }
        }
    }

    public /* synthetic */ void loadMore(Object obj) {
        m10903a((String) obj);
    }

    protected /* synthetic */ void onItemClick(Object obj) {
        m10901a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    public void m10902a(C2085a c2085a, String str, String str2) {
        this.f9823h = c2085a;
        this.f9819d = str;
        this.f9820e = str2;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f9816a = new ClubManager(getActivity());
        this.pullView.disablePullDown();
        this.f9821f = new C1802i(getActivity(), getString(C1373R.string.str_loading), true);
        this.f9821f.show();
        this.f9822g = new ArrayList();
        if (TextUtils.isEmpty(this.f9819d) || this.f9819d.equals("null")) {
            this.f9825j = new LocationClientOption();
            this.f9825j.setOpenGps(true);
            this.f9825j.setCoorType("bd09ll");
            this.f9825j.setAddrType("all");
            this.f9825j.setPriority(2);
            this.f9824i = new LocationClient(getActivity());
            this.f9824i.registerLocationListener(this);
            this.f9824i.setLocOption(this.f9825j);
            this.f9824i.start();
            this.f9824i.requestLocation();
            this.f9827l.postDelayed(new C21021(this), 3000);
            return;
        }
        m10887a();
    }

    /* renamed from: a */
    protected void m10901a(ClubInfoCompact clubInfoCompact) {
        super.onItemClick(clubInfoCompact);
        C2562j.m12866a(getContext(), clubInfoCompact);
    }

    public void loadNormal() {
    }

    /* renamed from: a */
    public void m10903a(String str) {
        super.loadMore(str);
        this.f9817b++;
        m10887a();
    }

    /* renamed from: a */
    private void m10887a() {
        getAsyncTaskQueue().m13740a(new C21032(this), new String[0]);
    }

    protected BaseListAdapter<ClubInfoCompact> adapterToDisplay(AbsListView absListView) {
        return new C2110a(null, absListView);
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if (bDLocation != null && !TextUtils.isEmpty(bDLocation.getCity())) {
            this.f9827l.removeMessages(HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
            this.f9819d = bDLocation.getCity();
            m10887a();
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }
}

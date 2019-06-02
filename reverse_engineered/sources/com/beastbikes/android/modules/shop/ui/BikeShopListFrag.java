package com.beastbikes.android.modules.shop.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.p073a.C2327a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.WebActivity;
import com.beastbikes.framework.ui.android.lib.frag.FragBaseList;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import com.beastbikes.framework.ui.android.lib.view.search.MySearchListener;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.List;

public class BikeShopListFrag extends FragBaseList<String, BikeShopListDTO, ListView> implements OnClickListener, MySearchListener {
    /* renamed from: a */
    private C2327a f11083a;
    /* renamed from: b */
    private List<BikeShopListDTO> f11084b = null;
    /* renamed from: c */
    private C1802i f11085c;
    /* renamed from: d */
    private double f11086d;
    /* renamed from: e */
    private double f11087e;
    /* renamed from: f */
    private String f11088f = MapboxEvent.TYPE_LOCATION;
    /* renamed from: g */
    private String f11089g;

    public /* synthetic */ void loadMore(Object obj) {
        m11919a((String) obj);
    }

    protected /* synthetic */ void onItemClick(Object obj) {
        m11918a((BikeShopListDTO) obj);
    }

    public /* synthetic */ void onItemLongClick(Object obj) {
        m11920b((BikeShopListDTO) obj);
    }

    public void onResume() {
        super.onResume();
        if ("mine".equals(this.f11088f)) {
            m11910b(null);
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getInt("type") == C1373R.id.bike_shop_tab_mine) {
            this.f11088f = "mine";
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (AVUser.getCurrentUser() != null) {
            this.f11089g = AVUser.getCurrentUser().getObjectId();
        }
        this.f11083a = new C2327a(getActivity());
        this.pullView.disablePullDown();
        this.pullView.enablePullUp();
        this.f11085c = new C1802i(getActivity(), getString(C1373R.string.pull_to_refresh_refreshing_label), true);
        this.f11085c.show();
        this.f11084b = new ArrayList();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(C1848b.m9630a().getClass().getName(), 0);
        this.f11086d = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
        this.f11087e = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
        this.pullView.setPullToRefreshEnabled(false);
        if ("mine".equals(this.f11088f)) {
            View inflate = View.inflate(getContext(), C1373R.layout.bike_shop_footer_view, null);
            inflate.findViewById(C1373R.id.bike_shop_add_tv).setOnClickListener(this);
            addFooterView(inflate);
            return;
        }
        m11910b(null);
    }

    /* renamed from: a */
    protected void m11918a(final BikeShopListDTO bikeShopListDTO) {
        super.onItemClick(bikeShopListDTO);
        if (bikeShopListDTO == null) {
            return;
        }
        if (bikeShopListDTO.getStatus() == 1 || bikeShopListDTO.getStatus() == 0) {
            C2580w.m12905a(getContext(), "查看车店详情", "open_bicycle_detail");
            Intent intent = new Intent(getContext(), BikeShopDetailActivity.class);
            intent.putExtra("bike_shop_id", bikeShopListDTO.getShopId());
            intent.putExtra("show_enter_club", true);
            intent.putExtra("type", this.f11088f);
            startActivity(intent);
        } else if (bikeShopListDTO.getStatus() == -1) {
            final C2621c c2621c = new C2621c(getContext());
            c2621c.m13058a((int) C1373R.string.dialog_bike_shop_fail_title);
            c2621c.m13067b(bikeShopListDTO.getReason());
            c2621c.m13059a((int) C1373R.string.club_release_activites_dialog_ok, new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ BikeShopListFrag f11070c;

                public void onClick(View view) {
                    c2621c.m13069b();
                    Uri parse = Uri.parse(new StringBuilder(a$c.f7200a).append("/app/shop/auth.html?shopId=" + bikeShopListDTO.getShopId() + "#shop").toString());
                    Intent intent = new Intent(this.f11070c.getContext(), BrowserActivity.class);
                    intent.setData(parse);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.addCategory("android.intent.category.BROWSABLE");
                    intent.setPackage(this.f11070c.getContext().getPackageName());
                    intent.putExtra(WebActivity.EXTRA_TITLE, this.f11070c.getString(C1373R.string.bike_shop_edit));
                    intent.putExtra(WebActivity.EXTRA_CAN_GOBACK, true);
                    intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
                    intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
                    intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
                    this.f11070c.startActivity(intent);
                }
            }).m13066b((int) C1373R.string.club_release_activites_dialog_cencle, new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ BikeShopListFrag f11067b;

                public void onClick(View view) {
                    c2621c.m13069b();
                }
            }).m13063a();
        }
    }

    /* renamed from: b */
    public void m11920b(final BikeShopListDTO bikeShopListDTO) {
        super.onItemLongClick(bikeShopListDTO);
        if (!MapboxEvent.TYPE_LOCATION.equals(this.f11088f) && bikeShopListDTO != null && bikeShopListDTO.getOwnerId().equals(this.f11089g)) {
            final C2621c c2621c = new C2621c(getContext());
            c2621c.m13068b(true);
            View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.activity_record_delete_dialog, null);
            ((TextView) inflate.findViewById(C1373R.id.delete_dialog_delete_item)).setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ BikeShopListFrag f11073c;

                public void onClick(View view) {
                    c2621c.m13069b();
                    this.f11073c.m11912c(bikeShopListDTO);
                }
            });
            c2621c.m13060a(inflate).m13063a();
        }
    }

    /* renamed from: c */
    private void m11912c(final BikeShopListDTO bikeShopListDTO) {
        final C2621c c2621c = new C2621c(getContext());
        c2621c.m13065b((int) C1373R.string.dialog_sure_bike_shop_delete);
        c2621c.m13059a((int) C1373R.string.delete, new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ BikeShopListFrag f11078c;

            public void onClick(View view) {
                c2621c.m13069b();
                this.f11078c.m11902a(bikeShopListDTO.getShopId());
            }
        }).m13066b((int) C1373R.string.cancel, new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ BikeShopListFrag f11075b;

            public void onClick(View view) {
                c2621c.m13069b();
            }
        }).m13063a();
    }

    public void loadNormal() {
    }

    /* renamed from: a */
    public void m11919a(String str) {
        super.loadMore(str);
    }

    /* renamed from: a */
    private void m11902a(final long j) {
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Boolean>(this) {
            /* renamed from: b */
            final /* synthetic */ BikeShopListFrag f11080b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11895a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11896a((Boolean) obj);
            }

            protected void onPreExecute() {
                super.onPreExecute();
                this.f11080b.f11085c = new C1802i(this.f11080b.getActivity(), this.f11080b.getString(C1373R.string.club_info_waiting), true);
                this.f11080b.f11085c.show();
            }

            /* renamed from: a */
            protected Boolean m11895a(String... strArr) {
                try {
                    return Boolean.valueOf(this.f11080b.f11083a.m11892a(j));
                } catch (BusinessException e) {
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m11896a(Boolean bool) {
                if (!(this.f11080b.f11085c == null || this.f11080b.getActivity() == null || this.f11080b.getActivity().isFinishing())) {
                    this.f11080b.f11085c.dismiss();
                }
                this.f11080b.m11910b("");
            }
        }, new String[0]);
    }

    /* renamed from: b */
    private void m11910b(final String str) {
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, List<BikeShopListDTO>>(this) {
            /* renamed from: b */
            final /* synthetic */ BikeShopListFrag f11082b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11897a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11898a((List) obj);
            }

            /* renamed from: a */
            protected List<BikeShopListDTO> m11897a(String... strArr) {
                try {
                    return this.f11082b.f11083a.m11890a(this.f11082b.f11087e, this.f11082b.f11086d, -1.0f, str, this.f11082b.f11086d, this.f11082b.f11087e, this.f11082b.f11088f);
                } catch (BusinessException e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m11898a(List<BikeShopListDTO> list) {
                if (!(this.f11082b.f11085c == null || this.f11082b.getActivity() == null || this.f11082b.getActivity().isFinishing())) {
                    this.f11082b.f11085c.dismiss();
                }
                if (list == null || list.isEmpty()) {
                    Activity activity = this.f11082b.getActivity();
                    if (activity != null) {
                        if ("mine".equals(this.f11082b.f11088f)) {
                            this.f11082b.m11901a();
                        } else {
                            this.f11082b.onLoadFailed(activity.getString(C1373R.string.bike_shop_load_fail));
                        }
                    }
                } else if ("mine".equals(this.f11082b.f11088f)) {
                    this.f11082b.onLoadSucessfully(list);
                } else if (TextUtils.isEmpty(str)) {
                    if (this.f11082b.f11084b.size() <= 0) {
                        this.f11082b.f11084b.addAll(list);
                    }
                    this.f11082b.onLoadSucessfully(this.f11082b.f11084b);
                } else {
                    this.f11082b.onLoadSucessfully(list);
                }
            }
        }, new String[0]);
    }

    /* renamed from: a */
    private void m11901a() {
        View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.bike_shop_empty_view, null);
        inflate.findViewById(C1373R.id.bike_shop_add).setOnClickListener(this);
        this.pullView.addEmptyView(inflate);
    }

    protected BaseListAdapter<BikeShopListDTO> adapterToDisplay(AbsListView absListView) {
        return new C2339a(null, absListView, this.f11088f);
    }

    public List<?> loadHistoryData(String str) {
        return null;
    }

    public List<?> loadIntelligenceData(String str, String str2) {
        return null;
    }

    public CharSequence getHistoryCharSequence(Object obj) {
        return null;
    }

    public CharSequence getIntelligenceCharSequence(Object obj) {
        return null;
    }

    public void recordHistory(String str, String str2) {
    }

    public void clearHistory(String str) {
        onLoadSucessfully(this.f11084b);
    }

    public String getSearchKey() {
        return null;
    }

    public void goSearch(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.f11085c = new C1802i(getActivity(), getString(C1373R.string.club_search_loading_msg), false);
            this.f11085c.show();
            m11910b(str2);
        }
    }

    public void onHistoryItemClicked(Object obj) {
    }

    public void onIntelligenceItemClicked(Object obj) {
    }

    public void onClick(View view) {
        if (view.getId() == C1373R.id.bike_shop_add || view.getId() == C1373R.id.bike_shop_add_tv) {
            Uri parse = Uri.parse(new StringBuilder(a$c.f7200a).append("/app/shop/auth.html#shop").toString());
            Intent intent = new Intent(getActivity(), BrowserActivity.class);
            intent.setData(parse);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setPackage(getContext().getPackageName());
            intent.putExtra(WebActivity.EXTRA_TITLE, getString(C1373R.string.bike_shop_add));
            intent.putExtra(WebActivity.EXTRA_CAN_GOBACK, true);
            intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
            intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
            intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
            startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i == -1) {
            m11910b("");
        }
    }
}

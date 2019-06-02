package com.beastbikes.android.modules.train.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.android.modules.train.p076a.C2349a;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.train.ui.p150a.C2375e;
import com.beastbikes.android.modules.train.ui.p151b.C2382a;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.subscriptions.CompositeSubscription;

@C1459b(a = 2130903382)
public class TrainDefaultFragment extends SessionFragment implements OnPageChangeListener, OnClickListener, OnTouchListener, OnItemClickListener, C2349a {
    /* renamed from: a */
    private static Logger f6271a = LoggerFactory.getLogger(TrainDefaultFragment.class);
    @C1458a(a = 2131756688)
    /* renamed from: b */
    private CircleImageView f6272b;
    @C1458a(a = 2131756689)
    /* renamed from: c */
    private ImageView f6273c;
    @C1458a(a = 2131756690)
    /* renamed from: d */
    private TextView f6274d;
    @C1458a(a = 2131756691)
    /* renamed from: e */
    private LinearLayout f6275e;
    @C1458a(a = 2131756692)
    /* renamed from: f */
    private ViewPager f6276f;
    @C1458a(a = 2131756693)
    /* renamed from: g */
    private TextView f6277g;
    @C1458a(a = 2131756694)
    /* renamed from: h */
    private ListView f6278h;
    /* renamed from: i */
    private TrainDefaultFragment$a f6279i;
    /* renamed from: j */
    private ArrayList<CourseDTO> f6280j = new ArrayList();
    /* renamed from: k */
    private ArrayList<View> f6281k = new ArrayList();
    /* renamed from: l */
    private C2382a f6282l;
    /* renamed from: m */
    private C2375e f6283m;
    /* renamed from: n */
    private ArrayList<CourseDTO> f6284n = new ArrayList();
    /* renamed from: o */
    private CompositeSubscription f6285o;
    /* renamed from: p */
    private C2350b f6286p;

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f6286p = new C2350b(getActivity());
        m7459c();
    }

    /* renamed from: c */
    private void m7459c() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (!(currentUser == null || TextUtils.isEmpty(currentUser.getAvatar()))) {
            Picasso.with(getActivity()).load(currentUser.getAvatar()).fit().placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f6272b);
        }
        this.f6273c.setFocusable(true);
        this.f6273c.setFocusableInTouchMode(true);
        this.f6273c.requestFocus();
        this.f6272b.setOnClickListener(this);
        this.f6273c.setOnClickListener(this);
        this.f6274d.setOnClickListener(this);
        this.f6275e.setOnTouchListener(this);
        this.f6277g.setOnClickListener(this);
        this.f6282l = new C2382a(getActivity(), 2);
        this.f6276f.setPageTransformer(true, m7460d());
        this.f6276f.addOnPageChangeListener(this);
        int a = C2801d.a(getActivity(), 300.0f);
        int a2 = C2801d.a(getActivity(), 128.0f);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a2);
        layoutParams.setMargins(0, 0, 0, a2 / 10);
        this.f6276f.setLayoutParams(layoutParams);
        this.f6278h.setOnItemClickListener(this);
        this.f6283m = new C2375e(this.f6284n);
        this.f6278h.setAdapter(this.f6283m);
        m7461a();
        this.f6285o = new CompositeSubscription();
        this.f6285o.add(C2574s.a().a(TrainResultDTO.class).a(new TrainDefaultFragment$1(this)));
    }

    public void onDestroy() {
        super.onDestroy();
        this.f6285o.clear();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.train_default_user_avatar:
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    intent.putExtra("user_id", currentUser.getObjectId());
                    getActivity().startActivity(intent);
                    return;
                }
                return;
            case C1373R.id.train_default_calendar_view:
                if (this.f6282l == null) {
                    this.f6282l = new C2382a(getActivity(), 2);
                }
                this.f6282l.show();
                return;
            case C1373R.id.train_default_single_course_view_all_tv:
                getActivity().startActivity(new Intent(getActivity(), SingleTrainListActivity.class));
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f6276f.dispatchTouchEvent(motionEvent);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i + 1 < this.f6279i.getCount()) {
            View view = (View) this.f6281k.get(i + 1);
            view.setTranslationY(((float) view.getHeight()) * 0.1f);
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    /* renamed from: a */
    public void m7462a(CourseDTO courseDTO) {
        Intent intent = new Intent(getActivity(), TrainCourseInfoActivity.class);
        intent.putExtra("train_type", "train_type_single");
        intent.putExtra("train_course_id", courseDTO.getId());
        intent.putExtra("train_type_info", true);
        startActivity(intent);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CourseDTO courseDTO = (CourseDTO) this.f6284n.get(i);
        if (courseDTO != null) {
            Intent intent = new Intent(getActivity(), CreateTrainLongTimePlanActivity.class);
            intent.putExtra("cycle", courseDTO.getCycle());
            startActivity(intent);
        }
    }

    /* renamed from: d */
    private PageTransformer m7460d() {
        return new TrainDefaultFragment$2(this);
    }

    /* renamed from: a */
    public void m7461a() {
        getAsyncTaskQueue().a(new TrainDefaultFragment$3(this), new String[0]);
    }

    /* renamed from: a */
    private void m7457a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
        if (optJSONObject == null) {
            f6271a.trace("getCourse(), JsonObject Result Is NULL");
            return;
        }
        int i;
        JSONArray optJSONArray = optJSONObject.optJSONArray("long_courses");
        if (optJSONArray != null) {
            for (i = 0; i < optJSONArray.length(); i++) {
                this.f6284n.add(new CourseDTO(optJSONArray.optJSONObject(i)));
            }
            this.f6283m.notifyDataSetChanged();
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("courses");
        if (optJSONArray2 != null) {
            for (i = 0; i < optJSONArray2.length(); i++) {
                this.f6280j.add(new CourseDTO(optJSONArray2.optJSONObject(i)));
                View inflate = LayoutInflater.from(getActivity()).inflate(C1373R.layout.train_single_course_item_view, null);
                inflate.setPadding(40, 0, 0, 0);
                this.f6281k.add(inflate);
            }
            this.f6276f.setOffscreenPageLimit(this.f6281k.size());
            this.f6279i = new TrainDefaultFragment$a(this, this.f6280j, this);
            this.f6276f.setAdapter(this.f6279i);
        }
    }
}

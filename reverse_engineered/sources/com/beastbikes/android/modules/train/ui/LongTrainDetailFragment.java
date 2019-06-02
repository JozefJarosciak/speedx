package com.beastbikes.android.modules.train.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseItemDTO;
import com.beastbikes.android.modules.train.dto.TrainInfoDTO;
import com.beastbikes.android.modules.train.ui.p150a.C2373c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

@C1459b(a = 2130903369)
public class LongTrainDetailFragment extends SessionFragment implements OnTabSelectedListener, OnClickListener, OnItemClickListener {
    @C1458a(a = 2131756655)
    /* renamed from: a */
    private TabLayout f6224a;
    @C1458a(a = 2131756656)
    /* renamed from: b */
    private ListView f6225b;
    @C1458a(a = 2131756657)
    /* renamed from: c */
    private ViewGroup f6226c;
    /* renamed from: d */
    private long f6227d;
    /* renamed from: e */
    private TreeMap<String, TreeMap<String, CourseDTO>> f6228e;
    /* renamed from: f */
    private List<TrainCourseItemDTO> f6229f = new ArrayList();
    /* renamed from: g */
    private C2373c f6230g;
    /* renamed from: h */
    private List<TextView> f6231h = new ArrayList();
    /* renamed from: i */
    private TrainInfoDTO f6232i;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            m7416a();
            this.f6232i = (TrainInfoDTO) arguments.getSerializable("train_type_long");
            m7421d();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.course_info_course_view_all:
                if (this.f6232i != null) {
                    Intent intent = new Intent(getActivity(), TrainCoursesActivity.class);
                    intent.putExtra("train_info", this.f6232i);
                    startActivity(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        TrainCourseItemDTO trainCourseItemDTO = (TrainCourseItemDTO) this.f6229f.get(i);
        if (trainCourseItemDTO != null) {
            Intent intent = new Intent(getActivity(), TrainCourseInfoActivity.class);
            intent.putExtra("train_type", "train_type_single");
            if (trainCourseItemDTO.getCourse() != null) {
                intent.putExtra("train_course_id", trainCourseItemDTO.getCourse().getId());
                intent.putExtra("train_course_time", trainCourseItemDTO.getTime());
            }
            intent.putExtra("train_type_info", true);
            startActivity(intent);
        }
    }

    public void onTabSelected(Tab tab) {
        m7418a((String) tab.getTag());
        m7417a(tab.getPosition());
    }

    public void onTabUnselected(Tab tab) {
    }

    public void onTabReselected(Tab tab) {
    }

    /* renamed from: a */
    private void m7416a() {
        this.f6230g = new C2373c(this.f6229f);
        this.f6225b.setAdapter(this.f6230g);
        this.f6226c.setOnClickListener(this);
        this.f6225b.setOnItemClickListener(this);
    }

    /* renamed from: a */
    private void m7418a(String str) {
        if (this.f6228e != null && this.f6228e.containsKey(str)) {
            TreeMap treeMap = (TreeMap) this.f6228e.get(str);
            if (treeMap != null && treeMap.size() > 0) {
                this.f6229f.clear();
                for (Entry entry : treeMap.entrySet()) {
                    this.f6229f.add(new TrainCourseItemDTO(Integer.valueOf(str).intValue(), (String) entry.getKey(), (CourseDTO) entry.getValue(), this.f6227d));
                }
                this.f6230g.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: b */
    private void m7419b(String str) {
        if (this.f6228e != null) {
            String[] stringArray = getResources().getStringArray(C1373R.array.week_num);
            for (Entry entry : this.f6228e.entrySet()) {
                CharSequence string;
                Object obj;
                int parseInt = Integer.parseInt((String) entry.getKey()) - 1;
                Object obj2 = 1;
                String str2 = "";
                if (parseInt < stringArray.length) {
                    str2 = stringArray[parseInt];
                }
                if (parseInt == Integer.parseInt(str) - 1) {
                    string = getString(C1373R.string.str_this_week);
                } else {
                    obj = str2;
                }
                View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.tab_train_week_view, null);
                TextView textView = (TextView) inflate.findViewById(C1373R.id.tab_train_week_view_title);
                textView.setText(string);
                this.f6231h.add(textView);
                ImageView imageView = (ImageView) inflate.findViewById(C1373R.id.tab_train_week_view_icon);
                for (Entry value : ((TreeMap) entry.getValue()).entrySet()) {
                    CourseDTO courseDTO = (CourseDTO) value.getValue();
                    if (courseDTO == null || !TextUtils.isEmpty(courseDTO.getSportRouteId())) {
                        obj = obj2;
                    } else {
                        obj = null;
                    }
                    obj2 = obj;
                }
                if (obj2 == null) {
                    imageView.setVisibility(8);
                }
                Tab newTab = this.f6224a.newTab();
                newTab.setCustomView(inflate);
                newTab.setTag(entry.getKey());
                this.f6224a.addTab(newTab);
            }
            m7420c();
            this.f6224a.addOnTabSelectedListener(this);
            int parseInt2 = Integer.parseInt(str) - 1;
            Tab tabAt = this.f6224a.getTabAt(parseInt2);
            if (tabAt != null) {
                tabAt.select();
                ((TextView) this.f6231h.get(parseInt2)).setTextSize(17.0f);
                ((TextView) this.f6231h.get(parseInt2)).setTextColor(Color.parseColor("#ffffff"));
            }
        }
    }

    /* renamed from: c */
    private void m7420c() {
        Class cls = this.f6224a.getClass();
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        try {
            Field declaredField = cls.getDeclaredField("mTabStrip");
            declaredField.setAccessible(true);
            LinearLayout linearLayout = (LinearLayout) declaredField.get(this.f6224a);
            width /= 4;
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View childAt = linearLayout.getChildAt(i);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 0.0f);
                layoutParams.width = width;
                childAt.setLayoutParams(layoutParams);
                childAt.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m7417a(int i) {
        for (int i2 = 0; i2 < this.f6231h.size(); i2++) {
            TextView textView = (TextView) this.f6231h.get(i2);
            if (i2 == i) {
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setTextSize(17.0f);
            } else {
                textView.setTextColor(Color.parseColor("#999999"));
                textView.setTextSize(14.0f);
            }
        }
    }

    /* renamed from: d */
    private void m7421d() {
        this.f6228e = this.f6232i.getCourses();
        if (this.f6228e != null && this.f6228e.size() > 0) {
            Calendar instance = Calendar.getInstance();
            int i = instance.get(3);
            this.f6227d = this.f6232i.getCreated();
            instance.setTimeInMillis(this.f6232i.getCreated());
            String valueOf = String.valueOf((i - instance.get(3)) + 1);
            m7419b(valueOf);
            m7418a(valueOf);
        }
    }
}

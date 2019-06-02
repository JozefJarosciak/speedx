package com.beastbikes.android.modules.train.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseItemDTO;
import com.beastbikes.android.modules.train.dto.TrainInfoDTO;
import com.beastbikes.android.modules.train.dto.TrainWeekDTO;
import com.beastbikes.android.utils.C2554c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

@C1459b(a = 2130903207)
public class TrainCoursesActivity extends SessionFragmentActivity implements OnChildClickListener {
    @C1458a(a = 2131756088)
    /* renamed from: a */
    private TextView f6265a;
    @C1458a(a = 2131756089)
    /* renamed from: b */
    private TextView f6266b;
    @C1458a(a = 2131756087)
    /* renamed from: c */
    private ImageView f6267c;
    @C1458a(a = 2131756090)
    /* renamed from: d */
    private ExpandableListView f6268d;
    /* renamed from: e */
    private ArrayList<TrainWeekDTO> f6269e = new ArrayList();
    /* renamed from: f */
    private int f6270f = -1;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator((int) C1373R.drawable.ic_close_icon);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f6265a.setFocusable(true);
        this.f6265a.setFocusableInTouchMode(true);
        this.f6265a.requestFocus();
        m7453a((TrainInfoDTO) intent.getSerializableExtra("train_info"));
    }

    /* renamed from: a */
    private void m7453a(TrainInfoDTO trainInfoDTO) {
        if (trainInfoDTO != null) {
            if (C1849a.c()) {
                this.f6265a.setText(trainInfoDTO.getName());
                this.f6266b.setText(trainInfoDTO.getDesc());
            } else {
                this.f6265a.setText(trainInfoDTO.getEnName());
                this.f6266b.setText(trainInfoDTO.getEnDesc());
            }
            if (!TextUtils.isEmpty(trainInfoDTO.getBgPicture())) {
                Picasso.with(this).load(trainInfoDTO.getBgPicture()).resize(C2801d.a(this), C2801d.b(this)).placeholder((int) C1373R.drawable.transparent).error((int) C1373R.drawable.transparent).centerCrop().into(this.f6267c);
            }
            Calendar instance = Calendar.getInstance();
            int i = instance.get(3);
            long created = trainInfoDTO.getCreated();
            instance.setTimeInMillis(trainInfoDTO.getCreated());
            String valueOf = String.valueOf((i - instance.get(3)) + 1);
            String[] stringArray = getResources().getStringArray(C1373R.array.week_num);
            TreeMap courses = trainInfoDTO.getCourses();
            if (courses != null) {
                for (Entry entry : courses.entrySet()) {
                    String str;
                    int parseInt = Integer.parseInt((String) entry.getKey()) - 1;
                    String str2 = "";
                    if (parseInt < stringArray.length) {
                        str = stringArray[parseInt];
                    } else {
                        str = str2;
                    }
                    courses = (TreeMap) entry.getValue();
                    if (courses != null) {
                        List arrayList = new ArrayList();
                        for (Entry entry2 : courses.entrySet()) {
                            CourseDTO courseDTO = (CourseDTO) entry2.getValue();
                            if (courseDTO != null) {
                                arrayList.add(new TrainCourseItemDTO(Integer.valueOf(valueOf).intValue(), (String) entry2.getKey(), courseDTO, created));
                            }
                        }
                        TrainWeekDTO trainWeekDTO = new TrainWeekDTO();
                        trainWeekDTO.setWeek(str);
                        trainWeekDTO.setWeekIndex(Integer.parseInt(valueOf));
                        trainWeekDTO.setDate(C2554c.c(created, (long) parseInt));
                        trainWeekDTO.setCourses(arrayList);
                        trainWeekDTO.setThisWeek(parseInt == Integer.parseInt(valueOf) + -1);
                        this.f6269e.add(trainWeekDTO);
                    }
                }
                Object trainCoursesActivity$a = new TrainCoursesActivity$a(this, this.f6269e);
                this.f6268d.setAdapter(trainCoursesActivity$a);
                m7452a();
                trainCoursesActivity$a.notifyDataSetChanged();
                this.f6268d.setOnChildClickListener(this);
                this.f6268d.setOnGroupClickListener(new TrainCoursesActivity$1(this));
            }
        }
    }

    /* renamed from: a */
    private void m7452a() {
        int i = 0;
        TrainCoursesActivity$a trainCoursesActivity$a = (TrainCoursesActivity$a) this.f6268d.getExpandableListAdapter();
        int a = C2801d.a(this, 80.0f);
        int i2 = 0;
        while (i < trainCoursesActivity$a.getGroupCount()) {
            if ((this.f6268d.isGroupExpanded(i) || this.f6270f == i) && !(this.f6268d.isGroupExpanded(i) && this.f6270f == i)) {
                i2 += trainCoursesActivity$a.getChildrenCount(i) * a;
            }
            i++;
        }
        LayoutParams layoutParams = this.f6268d.getLayoutParams();
        layoutParams.height = (trainCoursesActivity$a.getGroupCount() * a) + i2;
        this.f6268d.setLayoutParams(layoutParams);
        this.f6268d.requestLayout();
    }

    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        TrainWeekDTO trainWeekDTO = (TrainWeekDTO) this.f6269e.get(i);
        if (trainWeekDTO.getCourses() != null && trainWeekDTO.getCourses().size() > 0) {
            TrainCourseItemDTO trainCourseItemDTO = (TrainCourseItemDTO) trainWeekDTO.getCourses().get(i2);
            if (trainCourseItemDTO != null) {
                CourseDTO course = trainCourseItemDTO.getCourse();
                if (course == null) {
                    return false;
                }
                Intent intent = new Intent(this, TrainCourseInfoActivity.class);
                intent.putExtra("train_type", "train_type_single");
                intent.putExtra("train_course_id", course.getId());
                intent.putExtra("train_type_info", true);
                startActivity(intent);
            }
        }
        return true;
    }
}

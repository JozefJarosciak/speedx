package com.wdullaer.materialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.wdullaer.materialdatetimepicker.C4779R;
import com.wdullaer.materialdatetimepicker.date.C4789b.C4787a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: YearPickerView */
/* renamed from: com.wdullaer.materialdatetimepicker.date.i */
public class C4803i extends ListView implements OnItemClickListener, C4787a {
    /* renamed from: a */
    private final C4784a f16869a;
    /* renamed from: b */
    private C4802a f16870b;
    /* renamed from: c */
    private int f16871c;
    /* renamed from: d */
    private int f16872d;
    /* renamed from: e */
    private TextViewWithCircularIndicator f16873e;

    /* compiled from: YearPickerView */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.i$a */
    private class C4802a extends ArrayAdapter<String> {
        /* renamed from: a */
        final /* synthetic */ C4803i f16868a;

        public C4802a(C4803i c4803i, Context context, int i, List<String> list) {
            this.f16868a = c4803i;
            super(context, i, list);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TextViewWithCircularIndicator textViewWithCircularIndicator = (TextViewWithCircularIndicator) super.getView(i, view, viewGroup);
            textViewWithCircularIndicator.m18774a(this.f16868a.f16869a.mo6191c(), this.f16868a.f16869a.mo6189b());
            textViewWithCircularIndicator.requestLayout();
            boolean z = this.f16868a.f16869a.mo6185a().f16806a == C4803i.m18868b(textViewWithCircularIndicator);
            textViewWithCircularIndicator.m18775a(z);
            if (z) {
                this.f16868a.f16873e = textViewWithCircularIndicator;
            }
            return textViewWithCircularIndicator;
        }
    }

    public C4803i(Context context, C4784a c4784a) {
        super(context);
        this.f16869a = c4784a;
        this.f16869a.mo6188a((C4787a) this);
        setLayoutParams(new LayoutParams(-1, -2));
        Resources resources = context.getResources();
        this.f16871c = resources.getDimensionPixelOffset(C4779R.dimen.mdtp_date_picker_view_animator_height);
        this.f16872d = resources.getDimensionPixelOffset(C4779R.dimen.mdtp_year_label_height);
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(this.f16872d / 3);
        m18867a(context);
        setOnItemClickListener(this);
        setSelector(new StateListDrawable());
        setDividerHeight(0);
        mo6197a();
    }

    /* renamed from: a */
    private void m18867a(Context context) {
        List arrayList = new ArrayList();
        for (int f = this.f16869a.mo6194f(); f <= this.f16869a.mo6195g(); f++) {
            arrayList.add(String.format("%d", new Object[]{Integer.valueOf(f)}));
        }
        this.f16870b = new C4802a(this, context, C4779R.layout.mdtp_year_label_text_view, arrayList);
        setAdapter(this.f16870b);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f16869a.mo6196h();
        TextViewWithCircularIndicator textViewWithCircularIndicator = (TextViewWithCircularIndicator) view;
        if (textViewWithCircularIndicator != null) {
            if (textViewWithCircularIndicator != this.f16873e) {
                if (this.f16873e != null) {
                    this.f16873e.m18775a(false);
                    this.f16873e.requestLayout();
                }
                textViewWithCircularIndicator.m18775a(true);
                textViewWithCircularIndicator.requestLayout();
                this.f16873e = textViewWithCircularIndicator;
            }
            this.f16869a.mo6186a(C4803i.m18868b(textViewWithCircularIndicator));
            this.f16870b.notifyDataSetChanged();
        }
    }

    /* renamed from: b */
    private static int m18868b(TextView textView) {
        return Integer.valueOf(textView.getText().toString()).intValue();
    }

    /* renamed from: a */
    public void m18870a(int i) {
        m18871a(i, (this.f16871c / 2) - (this.f16872d / 2));
    }

    /* renamed from: a */
    public void m18871a(final int i, final int i2) {
        post(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C4803i f16867c;

            public void run() {
                this.f16867c.setSelectionFromTop(i, i2);
                this.f16867c.requestLayout();
            }
        });
    }

    public int getFirstPositionOffset() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        return childAt.getTop();
    }

    /* renamed from: a */
    public void mo6197a() {
        this.f16870b.notifyDataSetChanged();
        m18870a(this.f16869a.mo6185a().f16806a - this.f16869a.mo6194f());
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4096) {
            accessibilityEvent.setFromIndex(0);
            accessibilityEvent.setToIndex(0);
        }
    }
}

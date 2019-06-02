package com.beastbikes.android.modules.user.ui.binding.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.ui.binding.widget.C2520d.C2514a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: CountryAdapter */
/* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.b */
public class C2515b extends C2514a {
    /* renamed from: b */
    private HashMap<Character, ArrayList<String[]>> f11918b;
    /* renamed from: c */
    private ArrayList<String> f11919c;
    /* renamed from: d */
    private ArrayList<ArrayList<String[]>> f11920d;
    /* renamed from: e */
    private C2523e f11921e;

    /* renamed from: b */
    public /* synthetic */ Object mo3518b(int i, int i2) {
        return m12641a(i, i2);
    }

    public C2515b(Context context, C2520d c2520d) {
        super(c2520d);
        this.f11918b = C2516c.m12645a(context);
        m12634b();
        m12640a(null);
    }

    /* renamed from: b */
    private void m12634b() {
        this.f11921e = new C2523e();
        ArrayList arrayList = new ArrayList();
        for (Entry value : this.f11918b.entrySet()) {
            Iterator it = ((ArrayList) value.getValue()).iterator();
            while (it.hasNext()) {
                arrayList.add(((String[]) it.next())[0]);
            }
        }
        this.f11921e.m12670a(arrayList);
    }

    /* renamed from: a */
    public void m12640a(String str) {
        int i;
        ArrayList a = this.f11921e.m12669a(str);
        if (a == null || a.size() <= 0) {
            i = 1;
            a = new ArrayList();
        } else {
            i = 0;
        }
        HashMap hashMap = new HashMap();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            hashMap.put(str2, str2);
        }
        this.f11919c = new ArrayList();
        this.f11920d = new ArrayList();
        for (Entry entry : this.f11918b.entrySet()) {
            ArrayList arrayList = (ArrayList) entry.getValue();
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                String[] strArr = (String[]) it2.next();
                if (i != 0 || hashMap.containsKey(strArr[0])) {
                    arrayList2.add(strArr);
                }
            }
            if (arrayList2.size() > 0) {
                this.f11919c.add(String.valueOf(entry.getKey()));
                this.f11920d.add(arrayList2);
            }
        }
    }

    /* renamed from: a */
    public int mo3513a() {
        return this.f11919c == null ? 0 : this.f11919c.size();
    }

    /* renamed from: a */
    public int mo3514a(int i) {
        if (this.f11920d == null) {
            return 0;
        }
        ArrayList arrayList = (ArrayList) this.f11920d.get(i);
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    /* renamed from: b */
    public String mo3519b(int i) {
        if (this.f11919c.size() != 0) {
            return ((String) this.f11919c.get(i)).toString();
        }
        return null;
    }

    /* renamed from: a */
    public String[] m12641a(int i, int i2) {
        if (this.f11920d.size() == 0) {
            return null;
        }
        try {
            return (String[]) ((ArrayList) this.f11920d.get(i)).get(i2);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public View mo3516a(int i, View view, ViewGroup viewGroup) {
        View linearLayout;
        if (view == null) {
            linearLayout = new LinearLayout(viewGroup.getContext());
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(viewGroup.getContext().getResources().getColor(C1373R.color.bg_border_line_color));
            View textView = new TextView(viewGroup.getContext());
            textView.setTextSize(1, 11.0f);
            textView.setTextColor(viewGroup.getContext().getResources().getColor(C1373R.color.smssdk_white));
            int a = m12633a(viewGroup.getContext(), 6);
            textView.setPadding(0, a, 0, a);
            textView.setLayoutParams(new LayoutParams(-1, -2));
            linearLayout.addView(textView);
            textView = new View(viewGroup.getContext());
            textView.setBackgroundColor(viewGroup.getContext().getResources().getColor(C1373R.color.bg_border_line_color));
            linearLayout.addView(textView, new LayoutParams(-1, 1));
        } else {
            linearLayout = view;
        }
        ((TextView) ((LinearLayout) linearLayout).getChildAt(0)).setText(mo3519b(i));
        return linearLayout;
    }

    /* renamed from: a */
    public void mo3517a(View view, String str) {
        ((TextView) ((LinearLayout) view).getChildAt(0)).setText(str);
    }

    /* renamed from: a */
    public View mo3515a(int i, int i2, View view, ViewGroup viewGroup) {
        View linearLayout;
        if (view == null) {
            linearLayout = new LinearLayout(viewGroup.getContext());
            linearLayout.setBackgroundColor(viewGroup.getContext().getResources().getColor(C1373R.color.common_bg_color));
            View textView = new TextView(viewGroup.getContext());
            textView.setTextColor(viewGroup.getContext().getResources().getColor(C1373R.color.smssdk_white));
            textView.setTextSize(2, 16.0f);
            int a = m12633a(viewGroup.getContext(), 16);
            textView.setPadding(0, a, 0, a);
            linearLayout.addView(textView, new LayoutParams(-1, -2));
        } else {
            linearLayout = view;
        }
        String[] a2 = m12641a(i, i2);
        if (a2 != null) {
            ((TextView) ((LinearLayout) linearLayout).getChildAt(0)).setText(a2[0]);
        }
        return linearLayout;
    }

    /* renamed from: a */
    private int m12633a(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }
}

package com.beastbikes.android.modules.user.ui.p157a;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;

/* compiled from: AccountAndPwdAdapter */
/* renamed from: com.beastbikes.android.modules.user.ui.a.b */
public class C2493b extends BaseAdapter {
    /* renamed from: a */
    private SparseArray<AccountDTO> f11837a;
    /* renamed from: b */
    private LayoutInflater f11838b;
    /* renamed from: c */
    private C2491a f11839c;

    /* compiled from: AccountAndPwdAdapter */
    /* renamed from: com.beastbikes.android.modules.user.ui.a.b$a */
    public interface C2491a {
        /* renamed from: a */
        void m12563a(AccountDTO accountDTO);
    }

    /* compiled from: AccountAndPwdAdapter */
    /* renamed from: com.beastbikes.android.modules.user.ui.a.b$b */
    class C2492b {
        /* renamed from: a */
        TextView f11833a;
        /* renamed from: b */
        Switch f11834b;
        /* renamed from: c */
        RelativeLayout f11835c;
        /* renamed from: d */
        final /* synthetic */ C2493b f11836d;

        C2492b(C2493b c2493b) {
            this.f11836d = c2493b;
        }

        /* renamed from: a */
        void m12564a(AccountDTO accountDTO) {
            boolean z = true;
            if (accountDTO.getStatus() != 1) {
                z = false;
            }
            this.f11833a.setText(accountDTO.getAccountName());
            this.f11834b.setChecked(z);
            this.f11833a.setCompoundDrawablesWithIntrinsicBounds(accountDTO.getResID(), 0, 0, 0);
            this.f11833a.setEnabled(z);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return m12566a(i);
    }

    public C2493b(SparseArray<AccountDTO> sparseArray, Activity activity, C2491a c2491a) {
        this.f11839c = c2491a;
        this.f11837a = sparseArray.clone();
        this.f11837a.remove(2);
        this.f11837a.remove(1);
        this.f11838b = LayoutInflater.from(activity);
    }

    public int getCount() {
        return this.f11837a.size();
    }

    /* renamed from: a */
    public AccountDTO m12566a(int i) {
        return (AccountDTO) this.f11837a.valueAt(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C2492b c2492b;
        if (view == null) {
            C2492b c2492b2 = new C2492b(this);
            view = this.f11838b.inflate(C1373R.layout.activity_account_management_item, null);
            c2492b2.f11833a = (TextView) view.findViewById(C1373R.id.activity_account_management_item_account_name);
            c2492b2.f11834b = (Switch) view.findViewById(C1373R.id.activity_account_management_item_binding);
            c2492b2.f11835c = (RelativeLayout) view.findViewById(C1373R.id.account_management_item_layout);
            view.setTag(c2492b2);
            c2492b = c2492b2;
        } else {
            c2492b = (C2492b) view.getTag();
        }
        view.setOnClickListener(null);
        final AccountDTO a = m12566a(i);
        c2492b.f11834b.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2493b f11832b;

            public void onClick(View view) {
                this.f11832b.f11839c.m12563a(a);
            }
        });
        if (a != null) {
            c2492b.m12564a(a);
        }
        return view;
    }
}

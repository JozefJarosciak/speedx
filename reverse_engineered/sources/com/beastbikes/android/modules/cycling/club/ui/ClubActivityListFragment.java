package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.club.biz.C2049a;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityInfoList;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityListDTO;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903361)
public class ClubActivityListFragment extends SessionFragment implements C2534b, C2631b {
    /* renamed from: a */
    private C2049a f4857a;
    /* renamed from: b */
    private String f4858b;
    /* renamed from: c */
    private List<ClubActivityListDTO> f4859c = new ArrayList();
    @C1458a(a = 2131755444)
    /* renamed from: d */
    private LinearLayout f4860d;
    /* renamed from: e */
    private C2638d f4861e;
    /* renamed from: f */
    private ClubActivityListFragment$a f4862f;
    /* renamed from: g */
    private DisplayMetrics f4863g;
    /* renamed from: h */
    private double f4864h = 1.88d;
    /* renamed from: i */
    private int f4865i = 1;
    /* renamed from: j */
    private boolean f4866j = false;
    /* renamed from: k */
    private boolean f4867k = true;
    /* renamed from: l */
    private final String f4868l = "?imageView2/1/w/120/h/120";

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        this.f4858b = arguments.getString("club_id");
        this.f4859c.addAll(((ClubActivityInfoList) arguments.getSerializable(C0861d.f2139k)).getList());
        this.f4857a = new C2049a(getActivity());
        this.f4863g = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.f4863g);
        this.f4862f = new ClubActivityListFragment$a(this, getActivity(), this);
        this.f4861e = new C2638d(getActivity(), this.f4860d, this.f4859c, 2);
        this.f4861e.setAdapter(this.f4862f);
        this.f4861e.setRecyclerCallBack(this);
        if (!arguments.getBoolean("club_member_has_footer", true)) {
            this.f4861e.setHasFooter(false);
        }
    }

    /* renamed from: a */
    public void m6252a() {
        this.f4867k = true;
        this.f4865i = 1;
        this.f4861e.setCanLoadMore(true);
        this.f4866j = true;
        this.f4861e.setHasFooter(true);
        m6245c();
    }

    public void a_() {
        this.f4865i++;
        this.f4861e.setHasFooter(true);
        m6245c();
    }

    /* renamed from: c */
    private void m6245c() {
        getAsyncTaskQueue().a(new ClubActivityListFragment$1(this), new Void[0]);
    }

    /* renamed from: a */
    public void m6253a(ViewHolder viewHolder, int i) {
        ClubActivityListDTO clubActivityListDTO = (ClubActivityListDTO) this.f4859c.get(i);
        if (clubActivityListDTO != null) {
            Intent intent;
            if (clubActivityListDTO.isManager()) {
                intent = new Intent(getActivity(), ClubActivityManagerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(C0861d.f2139k, clubActivityListDTO);
                intent.putExtras(bundle);
                startActivityForResult(intent, 10);
                return;
            }
            Uri parse = Uri.parse(ClubActivityInfoBrowserActivity.a(clubActivityListDTO.getActId(), getContext()));
            intent = new Intent(getActivity(), ClubActivityInfoBrowserActivity.class);
            intent.setData(parse);
            intent.putExtra("activity_type", 1);
            intent.putExtra("activity_id", ((ClubActivityListDTO) this.f4859c.get(i)).getActId());
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setPackage(getActivity().getPackageName());
            startActivity(intent);
        }
    }

    /* renamed from: b */
    public void m6254b(ViewHolder viewHolder, int i) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 10:
                    m6252a();
                    return;
                default:
                    return;
            }
        }
    }
}

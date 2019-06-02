package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.sections.dto.UseRouteLineDTO;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.modules.cycling.stage.p135c.C2242b;
import com.beastbikes.android.modules.cycling.stage.p137d.C2258b;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903376)
public class StagesCollectionFrag extends SessionFragment implements OnItemClickListener, C2258b {
    @C1458a(a = 2131756668)
    /* renamed from: a */
    private ListView f5861a;
    /* renamed from: b */
    private StagesCollectionFrag$b f5862b;
    /* renamed from: c */
    private List<StageDTO> f5863c = new ArrayList();
    /* renamed from: d */
    private SharedPreferences f5864d;
    /* renamed from: e */
    private C2242b f5865e;
    /* renamed from: f */
    private int f5866f;

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5862b = new StagesCollectionFrag$b(this, this.f5863c);
        this.f5861a.setAdapter(this.f5862b);
        this.f5861a.setOnItemClickListener(this);
        this.f5864d = getActivity().getSharedPreferences(getActivity().getPackageName(), 0);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f5866f = arguments.getInt("stage_type");
        }
        this.f5865e = new C2242b(this);
        this.f5865e.a();
    }

    /* renamed from: a */
    public StagesCollectionFrag m7117a() {
        return this;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        StageDTO stageDTO = (StageDTO) adapterView.getItemAtPosition(i);
        if (stageDTO != null) {
            switch (this.f5866f) {
                case 0:
                    this.f5864d.edit().putLong("use_stage_route_id", (long) stageDTO.getStageId()).putString("use_route_name", stageDTO.getStageName()).apply();
                    C2574s.a().a(new UseRouteLineDTO(stageDTO.getStageName()));
                    this.f5865e.a((long) stageDTO.getStageId());
                    getActivity().finish();
                    return;
                case 1:
                    Intent intent = new Intent(getActivity(), StageMapListBaseActivity.class);
                    intent.putExtra("intent_type", 18);
                    intent.putExtra("stage_id", stageDTO.getStageId());
                    getActivity().startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m7118a(List<StageDTO> list) {
        if (list != null && list.size() > 0) {
            this.f5863c.clear();
            this.f5863c.addAll(list);
            this.f5862b.notifyDataSetChanged();
        }
    }
}

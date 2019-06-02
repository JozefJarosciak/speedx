package com.beastbikes.android.modules.cycling.stage.ui;

import com.beastbikes.android.modules.cycling.stage.dto.StagePointDTO;
import com.beastbikes.android.modules.map.SpeedxMap.C1959a;

class StageMapListBaseActivity$3 implements C1959a {
    /* renamed from: a */
    final /* synthetic */ StageMapListBaseActivity f10770a;

    StageMapListBaseActivity$3(StageMapListBaseActivity stageMapListBaseActivity) {
        this.f10770a = stageMapListBaseActivity;
    }

    /* renamed from: a */
    public void mo3330a() {
        if (StageMapListBaseActivity.b(this.f10770a) == null) {
            return;
        }
        if (StageMapListBaseActivity.b(this.f10770a).getPoints() == null || StageMapListBaseActivity.b(this.f10770a).getPoints().size() <= 1) {
            StageMapListBaseActivity.m().error("赛段信息不全： " + StageMapListBaseActivity.b(this.f10770a));
            return;
        }
        StageMapListBaseActivity.a(this.f10770a, StageMapListBaseActivity.b(this.f10770a).getStartPoint(), (StagePointDTO) StageMapListBaseActivity.b(this.f10770a).getPoints().get(StageMapListBaseActivity.b(this.f10770a).getPoints().size() - 1));
        this.f10770a.f5818a.m11656a(StageMapListBaseActivity.b(this.f10770a).getPoints(), 8, -835263);
        this.f10770a.f5818a.m11674b(StageMapListBaseActivity.b(this.f10770a).getPoints(), this.f10770a.f5818a.getWidth() - 100, StageMapListBaseActivity.c(this.f10770a) - 100);
        this.f10770a.f5818a.setOnMapChangeFinishedListener(this.f10770a);
        StageMapListBaseActivity.a(this.f10770a, true);
    }
}

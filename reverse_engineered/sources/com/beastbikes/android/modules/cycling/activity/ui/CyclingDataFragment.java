package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingPreviewLayoutForApp;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;

@C1459b(a = 2130903364)
public class CyclingDataFragment extends SessionFragment {
    @C1458a(a = 2131756633)
    /* renamed from: a */
    private CyclingPreviewLayoutForApp f4610a;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ArrayList integerArrayList = arguments.getIntegerArrayList("argument_page_values");
            if (integerArrayList != null) {
                this.f4610a.a(integerArrayList);
            }
        }
    }
}

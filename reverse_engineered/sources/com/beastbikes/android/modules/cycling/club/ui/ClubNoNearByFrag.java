package com.beastbikes.android.modules.cycling.club.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.beastbikes.android.C1373R;

public class ClubNoNearByFrag extends Fragment {
    /* renamed from: a */
    private C2100a f9783a;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubNoNearByFrag$1 */
    class C20991 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ ClubNoNearByFrag f9782a;

        C20991(ClubNoNearByFrag clubNoNearByFrag) {
            this.f9782a = clubNoNearByFrag;
        }

        public void onClick(View view) {
            this.f9782a.f9783a.m10860a();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubNoNearByFrag$a */
    interface C2100a {
        /* renamed from: a */
        void m10860a();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1373R.layout.frag_nonearby, viewGroup, false);
        ((Button) inflate.findViewById(C1373R.id.btn_create_club)).setOnClickListener(new C20991(this));
        return inflate;
    }
}

package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

class ClubGalleryActivity$b extends ViewHolder<ClubPhotoDTO> implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubGalleryActivity f9686a;
    /* renamed from: b */
    private LinearLayout f9687b;
    /* renamed from: c */
    private TextView f9688c;
    /* renamed from: d */
    private TextView f9689d;
    /* renamed from: e */
    private TextView f9690e;
    /* renamed from: f */
    private View f9691f;

    public /* synthetic */ void bind(Object obj) {
        m10813a((ClubPhotoDTO) obj);
    }

    public ClubGalleryActivity$b(ClubGalleryActivity clubGalleryActivity, View view) {
        this.f9686a = clubGalleryActivity;
        super(view);
        this.f9691f = view;
        this.f9687b = (LinearLayout) view.findViewById(C1373R.id.club_gallery_header_date);
        this.f9688c = (TextView) view.findViewById(C1373R.id.club_gallery_list_heard_item_year);
        this.f9689d = (TextView) view.findViewById(C1373R.id.club_gallery_list_heard_item_month);
        this.f9690e = (TextView) view.findViewById(C1373R.id.club_gallery_list_heard_item_count);
    }

    /* renamed from: a */
    public void m10813a(ClubPhotoDTO clubPhotoDTO) {
        if (clubPhotoDTO != null) {
            this.f9691f.setLayoutParams(new LayoutParams(-1, C2801d.m13756a(getContext(), 50.0f)));
            int d = C2555d.m12810d(clubPhotoDTO.getCreateDate());
            int e = C2555d.m12813e(clubPhotoDTO.getCreateDate());
            this.f9688c.setText(String.format(this.f9686a.getString(C1373R.string.label_year), new Object[]{Integer.valueOf(d)}));
            this.f9689d.setText(String.format(this.f9686a.getString(C1373R.string.label_month), new Object[]{Integer.valueOf(e)}));
            this.f9690e.setText(String.format(this.f9686a.getString(C1373R.string.club_gallery_header_count_label), new Object[]{Integer.valueOf(clubPhotoDTO.getHeaderCount())}));
            this.f9687b.setOnClickListener(this);
            if (ClubGalleryActivity.p(this.f9686a) != null) {
                ClubGalleryActivity.p(this.f9686a).m11040a(d);
                ClubGalleryActivity.p(this.f9686a).m11041b(e);
            }
        }
    }

    public void onClick(View view) {
        ClubGalleryActivity.q(this.f9686a);
    }
}

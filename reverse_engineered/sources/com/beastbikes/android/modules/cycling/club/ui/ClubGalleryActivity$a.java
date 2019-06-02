package com.beastbikes.android.modules.cycling.club.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.modules.cycling.club.ui.ClubGalleryActivity.C1407c;
import com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.C2095d;

class ClubGalleryActivity$a extends BaseAdapter implements C2095d<Long> {
    /* renamed from: a */
    final /* synthetic */ ClubGalleryActivity f9685a;

    /* renamed from: b */
    public /* synthetic */ Object mo3400b(int i) {
        return m10811a(i);
    }

    private ClubGalleryActivity$a(ClubGalleryActivity clubGalleryActivity) {
        this.f9685a = clubGalleryActivity;
    }

    public int getCount() {
        if (ClubGalleryActivity.a(this.f9685a) == null) {
            return 0;
        }
        return ClubGalleryActivity.a(this.f9685a).size();
    }

    public Object getItem(int i) {
        return ClubGalleryActivity.a(this.f9685a).get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1407c c1407c;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.club_gallery_list_item, null);
            C1407c c1407c2 = new C1407c(this.f9685a, view);
            view.setTag(c1407c2);
            c1407c = c1407c2;
        } else {
            c1407c = (C1407c) view.getTag();
        }
        if (i < ClubGalleryActivity.a(this.f9685a).size()) {
            c1407c.a((ClubPhotoDTO) ClubGalleryActivity.a(this.f9685a).get(i));
        }
        return view;
    }

    /* renamed from: a */
    public Long m10811a(int i) {
        return Long.valueOf(((ClubPhotoDTO) ClubGalleryActivity.a(this.f9685a).get(i)).getHeaderId());
    }

    /* renamed from: a */
    public View mo3399a(int i, View view, ViewGroup viewGroup) {
        ClubGalleryActivity$b clubGalleryActivity$b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.club_gallery_list_header_item, null);
            ClubGalleryActivity$b clubGalleryActivity$b2 = new ClubGalleryActivity$b(this.f9685a, view);
            view.setTag(clubGalleryActivity$b2);
            clubGalleryActivity$b = clubGalleryActivity$b2;
        } else {
            clubGalleryActivity$b = (ClubGalleryActivity$b) view.getTag();
        }
        if (ClubGalleryActivity.a(this.f9685a).size() > i) {
            clubGalleryActivity$b.m10813a((ClubPhotoDTO) ClubGalleryActivity.a(this.f9685a).get(i));
        }
        return view;
    }
}

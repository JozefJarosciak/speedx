package com.beastbikes.android.modules.cycling.club.dto;

import java.io.Serializable;
import java.util.List;

public class ClubCache implements Serializable {
    /* renamed from: a */
    List<ClubFeed> f9362a;
    /* renamed from: b */
    List<ClubPhotoDTO> f9363b;

    public ClubCache(List<ClubFeed> list) {
        this.f9362a = list;
    }

    public List<ClubPhotoDTO> getClubPhotoDTOList() {
        return this.f9363b;
    }

    public void setClubPhotoDTOList(List<ClubPhotoDTO> list) {
        this.f9363b = list;
    }

    public List<ClubFeed> getClubFeeds() {
        return this.f9362a;
    }

    public void setClubFeeds(List<ClubFeed> list) {
        this.f9362a = list;
    }
}

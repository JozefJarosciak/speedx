package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.modules.train.dto.FtpDTO;
import java.util.Comparator;

class FTPListActivity$a implements Comparator<FtpDTO> {
    /* renamed from: a */
    final /* synthetic */ FTPListActivity f11572a;

    private FTPListActivity$a(FTPListActivity fTPListActivity) {
        this.f11572a = fTPListActivity;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m12421a((FtpDTO) obj, (FtpDTO) obj2);
    }

    /* renamed from: a */
    public int m12421a(FtpDTO ftpDTO, FtpDTO ftpDTO2) {
        return ftpDTO.getDate() < ftpDTO2.getDate() ? 1 : -1;
    }
}

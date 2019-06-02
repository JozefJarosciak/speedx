package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.FtpDTO;
import com.beastbikes.android.widget.p081b.C2534b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

class FTPListActivity$b extends Adapter<FTPListActivity$c> {
    /* renamed from: a */
    private ArrayList<FtpDTO> f11576a;
    /* renamed from: b */
    private C2534b f11577b;
    /* renamed from: c */
    private SimpleDateFormat f11578c;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m12424a((FTPListActivity$c) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m12423a(viewGroup, i);
    }

    FTPListActivity$b(ArrayList<FtpDTO> arrayList, C2534b c2534b) {
        this.f11576a = arrayList;
        this.f11577b = c2534b;
        if (C1849a.m9647c()) {
            this.f11578c = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINESE);
        } else {
            this.f11578c = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        }
    }

    /* renamed from: a */
    public FTPListActivity$c m12423a(ViewGroup viewGroup, int i) {
        return new FTPListActivity$c(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_ftp_view, viewGroup, false));
    }

    /* renamed from: a */
    public void m12424a(final FTPListActivity$c fTPListActivity$c, final int i) {
        FtpDTO ftpDTO = (FtpDTO) this.f11576a.get(i);
        if (ftpDTO != null) {
            fTPListActivity$c.f11579a.setText(this.f11578c.format(new Date(ftpDTO.getDate() * 1000)));
            fTPListActivity$c.f11580b.setText(ftpDTO.getFtp() + "W");
            fTPListActivity$c.f11581c.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ FTPListActivity$b f11575c;

                public void onClick(View view) {
                    if (this.f11575c.f11577b != null) {
                        this.f11575c.f11577b.mo3520a(fTPListActivity$c, i);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.f11576a.size();
    }
}

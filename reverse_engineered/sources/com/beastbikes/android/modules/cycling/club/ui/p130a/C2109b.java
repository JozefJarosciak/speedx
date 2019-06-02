package com.beastbikes.android.modules.cycling.club.ui.p130a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedBase;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2111b;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2111b.C2090a;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2112a;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2131g;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2132h;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2148b;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.C2108d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ClubFeedAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.a.b */
public class C2109b extends BaseAdapter implements C2090a, C2108d {
    /* renamed from: b */
    private static final Logger f9853b = LoggerFactory.getLogger(C2109b.class);
    /* renamed from: a */
    List<ClubFeed> f9854a = new ArrayList();
    /* renamed from: c */
    private Context f9855c;
    /* renamed from: d */
    private ClubInfoCompact f9856d;
    /* renamed from: e */
    private LayoutInflater f9857e;
    /* renamed from: f */
    private C2148b f9858f;
    /* renamed from: g */
    private C2152d f9859g;
    /* renamed from: h */
    private AVUser f9860h;
    /* renamed from: i */
    private ClubUser f9861i;
    /* renamed from: j */
    private Map<Integer, View> f9862j = new HashMap();
    /* renamed from: k */
    private boolean f9863k = false;

    /* compiled from: ClubFeedAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.a.b$a */
    private class C2107a implements OnClickListener {
        /* renamed from: a */
        ClubFeed f9850a;
        /* renamed from: b */
        C2111b f9851b = null;
        /* renamed from: c */
        final /* synthetic */ C2109b f9852c;

        public C2107a(C2109b c2109b, View view, ClubFeed clubFeed) {
            this.f9852c = c2109b;
            this.f9850a = clubFeed;
            switch (clubFeed.getFeedType()) {
                case 1:
                    this.f9851b = new C2131g(c2109b.f9855c, view, c2109b.f9860h);
                    break;
                case 2:
                    this.f9851b = new C2112a(c2109b.f9855c, view, c2109b.f9860h);
                    break;
                case 3:
                    this.f9851b = new C2132h(c2109b.f9855c, view, c2109b.f9860h);
                    break;
                default:
                    this.f9851b = new C2131g(c2109b.f9855c, view, c2109b.f9860h);
                    break;
            }
            this.f9851b.setClubFeedListener(c2109b);
            this.f9851b.setCommentEditView(c2109b.f9859g);
            c2109b.f9859g.setListener(this.f9851b);
        }

        /* renamed from: a */
        public void m10931a() {
            switch (this.f9850a.getFeedType()) {
                case 1:
                    this.f9851b.mo3414a(this.f9850a.getImageTxt());
                    break;
                case 2:
                    this.f9851b.mo3414a(this.f9850a.getActivity());
                    break;
                case 3:
                    this.f9851b.mo3414a(this.f9850a.getNotice());
                    break;
            }
            this.f9851b.setMyClub(this.f9852c.f9863k);
        }

        public void onClick(View view) {
        }
    }

    public C2109b(Context context, ClubInfoCompact clubInfoCompact, C2152d c2152d, boolean z) {
        this.f9859g = c2152d;
        this.f9856d = clubInfoCompact;
        this.f9855c = context;
        this.f9857e = LayoutInflater.from(context);
        this.f9860h = AVUser.getCurrentUser();
        if (this.f9860h != null) {
            this.f9861i = new ClubUser(this.f9860h.getObjectId(), this.f9860h.getDisplayName(), this.f9860h.getAvatar());
            this.f9863k = z;
        }
    }

    /* renamed from: a */
    public void m10944a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            this.f9856d = clubInfoCompact;
        }
        if (this.f9858f != null) {
            this.f9858f.setClubInfoCompact(this.f9856d);
        }
    }

    /* renamed from: a */
    public void m10945a(List<ClubFeed> list) {
        if (list != null && list.size() > 0) {
            this.f9862j.clear();
            this.f9854a = list;
        }
    }

    /* renamed from: a */
    public void m10946a(List<ClubFeed> list, boolean z) {
        if (list != null && list.size() > 0) {
            this.f9854a.addAll(list);
        }
    }

    /* renamed from: b */
    public void m10948b(List<ClubFeed> list, boolean z) {
        if (z) {
            m10946a((List) list, z);
        } else {
            m10945a((List) list);
        }
        super.notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m10939a() {
        this.f9862j.clear();
        super.notifyDataSetChanged();
    }

    public int getCount() {
        return this.f9854a.size();
    }

    public Object getItem(int i) {
        return this.f9854a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ClubFeed clubFeed = (ClubFeed) getItem(i);
        if (clubFeed == null) {
            return view;
        }
        if (clubFeed.getFid() == -1) {
            View textView = new TextView(this.f9855c);
            textView.setLayoutParams(new LayoutParams(10, 10));
            return textView;
        }
        C2107a c2107a;
        textView = (View) this.f9862j.get(Integer.valueOf(i));
        if (textView == null) {
            View inflate = this.f9857e.inflate(C1373R.layout.clubfeed_item_base, null);
            C2107a c2107a2 = new C2107a(this, inflate, clubFeed);
            this.f9862j.put(Integer.valueOf(i), inflate);
            inflate.setTag(c2107a2);
            c2107a = c2107a2;
            textView = inflate;
        } else {
            c2107a = (C2107a) textView.getTag();
        }
        c2107a.m10931a();
        return textView;
    }

    /* renamed from: a */
    public View mo3410a(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            this.f9858f = new C2148b(this.f9855c, this.f9863k);
            view = this.f9858f;
        }
        if (this.f9858f != null) {
            this.f9858f.setClubInfoCompact(this.f9856d);
        }
        return view;
    }

    /* renamed from: b */
    public long mo3411b(int i) {
        return 0;
    }

    /* renamed from: a */
    public void mo3391a(int i) {
        for (int i2 = 0; i2 < this.f9854a.size(); i2++) {
            if (((ClubFeed) this.f9854a.get(i2)).getFid() == i) {
                this.f9854a.remove(i2);
                break;
            }
        }
        m10939a();
    }

    /* renamed from: a */
    public void mo3393a(int i, ClubFeedComment clubFeedComment) {
        ClubFeedBase c = m10949c(i);
        if (c != null) {
            c.addComment(clubFeedComment);
            m10939a();
        }
    }

    /* renamed from: a */
    public void mo3392a(int i, int i2) {
        ClubFeedBase c = m10949c(i);
        if (c != null) {
            c.removeComment(i2);
            m10939a();
        }
    }

    /* renamed from: a */
    public void mo3394a(int i, boolean z) {
        ClubFeedBase c = m10949c(i);
        if (c != null) {
            if (z) {
                c.addHasLiked(this.f9861i);
            } else {
                c.removeLiked(this.f9860h.getObjectId());
            }
            m10939a();
        }
    }

    /* renamed from: c */
    public ClubFeedBase m10949c(int i) {
        for (int i2 = 0; i2 < this.f9854a.size(); i2++) {
            ClubFeed clubFeed = (ClubFeed) this.f9854a.get(i2);
            if (clubFeed.getFid() == i) {
                switch (clubFeed.getFeedType()) {
                    case 1:
                        return clubFeed.getImageTxt();
                    case 2:
                        return clubFeed.getActivity();
                    case 3:
                        return clubFeed.getNotice();
                }
                return null;
            }
        }
        return null;
    }
}

package com.beastbikes.android.modules.cycling.club.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedBase;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2111b;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2111b.C2090a;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2112a;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2131g;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2132h;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d.C2091a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.BottomScrollView;
import com.beastbikes.android.widget.BottomScrollView.C2092a;
import com.beastbikes.framework.business.BusinessException;
import java.util.ArrayList;
import java.util.List;

public class ClubFeedDetailsActivity extends SessionFragmentActivity implements C2090a, C2091a, C2092a {
    /* renamed from: a */
    ClubFeedBase f9592a = null;
    /* renamed from: b */
    View f9593b;
    /* renamed from: c */
    AVUser f9594c;
    /* renamed from: d */
    boolean f9595d = false;
    /* renamed from: e */
    private C2111b f9596e = null;
    /* renamed from: f */
    private C2052c f9597f;
    /* renamed from: g */
    private int f9598g = 1;
    /* renamed from: h */
    private int f9599h = 5;
    /* renamed from: i */
    private List<ClubFeedComment> f9600i;
    /* renamed from: j */
    private C2152d f9601j;
    /* renamed from: k */
    private FrameLayout f9602k;
    /* renamed from: l */
    private int f9603l;
    /* renamed from: m */
    private boolean f9604m;
    /* renamed from: n */
    private BottomScrollView f9605n;
    /* renamed from: o */
    private boolean f9606o = false;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubFeedDetailsActivity$2 */
    class C20872 extends AsyncTask<Void, Void, List<ClubFeedComment>> {
        /* renamed from: a */
        final /* synthetic */ ClubFeedDetailsActivity f9586a;

        C20872(ClubFeedDetailsActivity clubFeedDetailsActivity) {
            this.f9586a = clubFeedDetailsActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m10751a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m10752a((List) obj);
        }

        /* renamed from: a */
        protected List<ClubFeedComment> m10751a(Void... voidArr) {
            try {
                return this.f9586a.f9597f.m10585b(this.f9586a.f9603l, this.f9586a.f9598g, this.f9586a.f9599h);
            } catch (BusinessException e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m10752a(List<ClubFeedComment> list) {
            if (list != null && list.size() != 0 && this.f9586a.f9592a != null) {
                this.f9586a.f9600i.addAll(list);
                this.f9586a.f9592a.setCommentList(this.f9586a.f9600i);
                this.f9586a.f9596e.mo3414a(this.f9586a.f9592a);
                this.f9586a.f9606o = false;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9594c = AVUser.getCurrentUser();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f9595d = intent.getBooleanExtra("is_my_club", false);
        this.f9603l = intent.getIntExtra("feed_id", 0);
        this.f9604m = intent.getBooleanExtra("club_show_input", false);
        this.f9597f = new C2052c((Activity) this);
        setContentView(C1373R.layout.activity_clubfeed_deatils);
        FrameLayout frameLayout = (FrameLayout) findViewById(C1373R.id.container);
        this.f9593b = LayoutInflater.from(this).inflate(C1373R.layout.clubfeed_item_base, null);
        frameLayout.addView(this.f9593b);
        this.f9602k = (FrameLayout) findViewById(C1373R.id.comment_post_container);
        this.f9601j = new C2152d(this);
        this.f9601j.setListener(this);
        this.f9602k.addView(this.f9601j);
        this.f9601j.m11052c();
        this.f9600i = new ArrayList();
        m10769b(this.f9603l);
        this.f9605n = (BottomScrollView) findViewById(C1373R.id.bottomScrollView);
        this.f9605n.setOnScrollToBottomLintener(this);
    }

    /* renamed from: a */
    public void mo3396a(boolean z) {
        if (!this.f9606o) {
            this.f9606o = true;
            this.f9598g++;
            m10764a();
        }
    }

    /* renamed from: a */
    private void m10765a(ClubFeed clubFeed) {
        switch (clubFeed.getFeedType()) {
            case 1:
                this.f9596e = new C2131g(this, this.f9593b, this.f9594c);
                this.f9592a = clubFeed.getImageTxt();
                break;
            case 2:
                this.f9596e = new C2112a(this, this.f9593b, this.f9594c);
                this.f9592a = clubFeed.getActivity();
                break;
            case 3:
                this.f9596e = new C2132h(this, this.f9593b, this.f9594c);
                this.f9592a = clubFeed.getNotice();
                break;
        }
        this.f9596e.setCommentEditView(this.f9601j);
        this.f9596e.mo3414a(this.f9592a);
        this.f9596e.setMyClub(this.f9595d);
        this.f9596e.setClubFeedListener(this);
    }

    /* renamed from: b */
    private void m10769b(final int i) {
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, ClubFeed>(this) {
            /* renamed from: b */
            final /* synthetic */ ClubFeedDetailsActivity f9585b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10749a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10750a((ClubFeed) obj);
            }

            /* renamed from: a */
            protected ClubFeed m10749a(String... strArr) {
                try {
                    return this.f9585b.f9597f.m10568a(i);
                } catch (BusinessException e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m10750a(ClubFeed clubFeed) {
                if (clubFeed != null) {
                    this.f9585b.m10765a(clubFeed);
                    this.f9585b.m10764a();
                    this.f9585b.m10772c(i);
                }
            }
        }, new String[0]);
    }

    /* renamed from: a */
    private void m10764a() {
        getAsyncTaskQueue().m13740a(new C20872(this), new Void[0]);
    }

    /* renamed from: c */
    private void m10772c(final int i) {
        getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, List<ClubUser>>(this) {
            /* renamed from: b */
            final /* synthetic */ ClubFeedDetailsActivity f9588b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10753a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10754a((List) obj);
            }

            /* renamed from: a */
            protected List<ClubUser> m10753a(Void... voidArr) {
                try {
                    return this.f9588b.f9597f.m10571a(i, 1, 5);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m10754a(List<ClubUser> list) {
                if (list != null && list.size() != 0 && this.f9588b.f9592a != null) {
                    this.f9588b.f9592a.setLikeList(list);
                    this.f9588b.f9596e.mo3414a(this.f9588b.f9592a);
                }
            }
        }, new Void[0]);
    }

    /* renamed from: a */
    public void mo3395a(final String str, final int i, int i2) {
        C2580w.m12905a(this, "", "click_comment_send");
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, ClubFeedComment>(this) {
            /* renamed from: c */
            final /* synthetic */ ClubFeedDetailsActivity f9591c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10755a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10756a((ClubFeedComment) obj);
            }

            /* renamed from: a */
            protected ClubFeedComment m10755a(String... strArr) {
                try {
                    return this.f9591c.f9597f.m10570a(this.f9591c.f9603l, str, i);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m10756a(ClubFeedComment clubFeedComment) {
                if (clubFeedComment != null) {
                    this.f9591c.f9600i.add(clubFeedComment);
                    this.f9591c.f9596e.mo3414a(this.f9591c.f9592a);
                }
            }
        }, new String[0]);
    }

    /* renamed from: a */
    public void mo3391a(int i) {
        getSharedPreferences(p(), 0).edit().putLong("beast.club.refresh", System.currentTimeMillis()).apply();
        finish();
    }

    /* renamed from: a */
    public void mo3393a(int i, ClubFeedComment clubFeedComment) {
    }

    /* renamed from: a */
    public void mo3392a(int i, int i2) {
    }

    /* renamed from: a */
    public void mo3394a(int i, boolean z) {
    }
}

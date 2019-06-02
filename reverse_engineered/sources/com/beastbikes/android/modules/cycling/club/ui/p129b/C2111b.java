package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedBase;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d.C2091a;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/* compiled from: FeedItemBase */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.b */
public abstract class C2111b<K> extends LinearLayout implements OnClickListener, C2091a {
    /* renamed from: a */
    protected CircleImageView f9864a;
    /* renamed from: b */
    protected TextView f9865b;
    /* renamed from: c */
    protected TextView f9866c;
    /* renamed from: d */
    protected TextView f9867d;
    /* renamed from: e */
    protected TextView f9868e;
    /* renamed from: f */
    protected TextView f9869f;
    /* renamed from: g */
    protected ImageView f9870g;
    /* renamed from: h */
    protected FrameLayout f9871h;
    /* renamed from: i */
    protected FrameLayout f9872i;
    /* renamed from: j */
    protected View f9873j;
    /* renamed from: k */
    protected C2127c f9874k;
    /* renamed from: l */
    protected SessionFragmentActivity f9875l;
    /* renamed from: m */
    protected C2052c f9876m;
    /* renamed from: n */
    protected C2090a f9877n;
    /* renamed from: o */
    protected C2152d f9878o;
    /* renamed from: p */
    protected AVUser f9879p;
    /* renamed from: q */
    public boolean f9880q = false;
    /* renamed from: r */
    private K f9881r;
    /* renamed from: s */
    private ImageView f9882s;
    /* renamed from: t */
    private Context f9883t;

    /* compiled from: FeedItemBase */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.b$a */
    public interface C2090a {
        /* renamed from: a */
        void mo3391a(int i);

        /* renamed from: a */
        void mo3392a(int i, int i2);

        /* renamed from: a */
        void mo3393a(int i, ClubFeedComment clubFeedComment);

        /* renamed from: a */
        void mo3394a(int i, boolean z);
    }

    /* renamed from: a */
    public abstract void mo3413a();

    /* renamed from: a */
    public abstract void mo3414a(K k);

    public abstract View getView();

    protected abstract void onClick(View view, K k);

    public void setClubFeedListener(C2090a c2090a) {
        this.f9877n = c2090a;
    }

    public void setMyClub(boolean z) {
        this.f9880q = z;
    }

    public void setCommentEditView(C2152d c2152d) {
        this.f9878o = c2152d;
        this.f9874k.setCommentEditView(c2152d);
    }

    public C2111b(Context context, View view, AVUser aVUser) {
        super(context);
        this.f9883t = context;
        this.f9879p = aVUser;
        if (context != null && (context instanceof SessionFragmentActivity)) {
            this.f9875l = (SessionFragmentActivity) context;
        }
        this.f9876m = new C2052c(this.f9875l);
        this.f9873j = view;
        m10955b();
    }

    /* renamed from: b */
    private void m10955b() {
        this.f9866c = (TextView) this.f9873j.findViewById(C1373R.id.activity_complete_nickname);
        this.f9867d = (TextView) this.f9873j.findViewById(C1373R.id.time);
        this.f9868e = (TextView) this.f9873j.findViewById(C1373R.id.delete);
        this.f9882s = (ImageView) this.f9873j.findViewById(C1373R.id.praise);
        this.f9870g = (ImageView) this.f9873j.findViewById(C1373R.id.comment);
        this.f9864a = (CircleImageView) this.f9873j.findViewById(C1373R.id.avatar);
        this.f9871h = (FrameLayout) this.f9873j.findViewById(C1373R.id.container);
        this.f9865b = (TextView) this.f9873j.findViewById(C1373R.id.tv_extra);
        this.f9869f = (TextView) this.f9873j.findViewById(C1373R.id.text);
        this.f9872i = (FrameLayout) this.f9873j.findViewById(C1373R.id.common_container);
        this.f9868e.setOnClickListener(this);
        this.f9882s.setOnClickListener(this);
        this.f9870g.setOnClickListener(this);
        this.f9864a.setOnClickListener(this);
        View view = getView();
        if (view != null) {
            this.f9871h.addView(view);
            mo3413a();
        }
        if (this.f9874k == null) {
            this.f9874k = new C2127c(getContext(), this.f9879p.getObjectId(), this);
            this.f9872i.addView(this.f9874k);
        }
    }

    /* renamed from: b */
    protected void m10960b(K k) {
        this.f9881r = k;
        if (k instanceof ClubFeedBase) {
            ClubFeedBase clubFeedBase = (ClubFeedBase) k;
            this.f9867d.setText(C2555d.m12811d(clubFeedBase.getDate()));
            if (TextUtils.isEmpty(clubFeedBase.getText()) || this.f9869f == null) {
                this.f9869f.setVisibility(8);
            } else {
                this.f9869f.setText(clubFeedBase.getText() + "");
                this.f9869f.setVisibility(0);
            }
            ClubUser user = clubFeedBase.getUser();
            if (user != null) {
                this.f9866c.setText(C2570p.m12883a(user.getNickName(), user.getRemarks()));
                if (user.getUserId().equals(this.f9879p.getObjectId())) {
                    this.f9868e.setVisibility(0);
                } else {
                    this.f9868e.setVisibility(8);
                }
                if (TextUtils.isEmpty(user.getAvatar())) {
                    this.f9864a.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(this.f9883t).load(user.getAvatar()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(this.f9864a);
                }
            }
            if (clubFeedBase.getCommentCount() + clubFeedBase.getLikeCount() <= 0) {
                this.f9872i.setVisibility(8);
            } else {
                this.f9872i.setVisibility(0);
                this.f9874k.m10987a(clubFeedBase);
            }
            if (clubFeedBase.getStatus() == 1) {
                this.f9882s.setVisibility(4);
                this.f9870g.setVisibility(4);
            }
            this.f9882s.setSelected(clubFeedBase.isHasLiked());
        }
    }

    public void onClick(View view) {
        int i = 0;
        if (this.f9881r != null) {
            ClubFeedBase clubFeedBase = (ClubFeedBase) this.f9881r;
            switch (view.getId()) {
                case C1373R.id.comment:
                    if (this.f9880q) {
                        this.f9878o.m11049a();
                        this.f9878o.setTextHint(getResources().getString(C1373R.string.please_enter_comment));
                        this.f9878o.m11050a(clubFeedBase.getFid(), 0);
                        return;
                    }
                    Toasts.show(this.f9883t, this.f9883t.getResources().getString(C1373R.string.clubfeed_handle_tip));
                    return;
                case C1373R.id.avatar:
                    if (this.f9875l != null) {
                        Intent intent = new Intent();
                        intent.setClass(getContext(), ProfileActivity.class);
                        intent.putExtra("user_id", clubFeedBase.getUser().getUserId());
                        intent.putExtra("avatar", clubFeedBase.getUser().getAvatar());
                        intent.putExtra("nick_name", clubFeedBase.getUser().getNickName());
                        intent.putExtra("remarks", clubFeedBase.getUser().getRemarks());
                        this.f9875l.startActivity(intent);
                        return;
                    }
                    return;
                case C1373R.id.delete:
                    m10952a(clubFeedBase.getFid(), clubFeedBase.getClubId(), clubFeedBase.getStatus());
                    return;
                case C1373R.id.praise:
                    if (view.isSelected()) {
                        i = 1;
                    }
                    m10951a(i, clubFeedBase.getFid());
                    return;
                default:
                    onClick(view, this.f9881r);
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m10951a(final int i, final int i2) {
        if (!this.f9880q) {
            Toasts.show(this.f9883t, this.f9883t.getResources().getString(C1373R.string.clubfeed_handle_tip));
        } else if (this.f9875l != null) {
            this.f9875l.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Boolean>(this) {
                /* renamed from: c */
                final /* synthetic */ C2111b f9891c;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m10964a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m10965a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m10964a(String... strArr) {
                    try {
                        return Boolean.valueOf(this.f9891c.f9876m.m10582a(i, i2));
                    } catch (BusinessException e) {
                        return Boolean.valueOf(false);
                    }
                }

                /* renamed from: a */
                protected void m10965a(Boolean bool) {
                    boolean z = true;
                    if (bool.booleanValue()) {
                        boolean z2;
                        ClubFeedBase clubFeedBase = (ClubFeedBase) this.f9891c.f9881r;
                        if (clubFeedBase != null) {
                            clubFeedBase.setHasLiked(i == 0);
                        }
                        ImageView b = this.f9891c.f9882s;
                        if (i == 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        b.setSelected(z2);
                        if (this.f9891c.f9877n != null) {
                            C2090a c2090a = this.f9891c.f9877n;
                            int i = i2;
                            if (i != 0) {
                                z = false;
                            }
                            c2090a.mo3394a(i, z);
                        }
                    }
                }
            }, new String[0]);
        }
    }

    /* renamed from: a */
    private void m10952a(int i, String str, int i2) {
        final C2621c c2621c = new C2621c(getContext());
        c2621c.m13058a((int) C1373R.string.club_feed_del_hint);
        c2621c.m13065b((int) C1373R.string.club_feed_del_hint_del);
        final int i3 = i;
        final String str2 = str;
        final int i4 = i2;
        c2621c.m13059a((int) C1373R.string.activity_alert_dialog_text_ok, new OnClickListener(this) {
            /* renamed from: e */
            final /* synthetic */ C2111b f9898e;

            public void onClick(View view) {
                c2621c.m13069b();
                this.f9898e.m10956b(i3, str2, i4);
            }
        }).m13066b((int) C1373R.string.cancel, new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2111b f9893b;

            public void onClick(View view) {
                c2621c.m13069b();
            }
        }).m13063a();
    }

    /* renamed from: b */
    private void m10956b(final int i, final String str, final int i2) {
        if (this.f9875l != null) {
            this.f9875l.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Boolean>(this) {
                /* renamed from: d */
                final /* synthetic */ C2111b f9902d;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m10966a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m10967a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m10966a(String... strArr) {
                    try {
                        return Boolean.valueOf(this.f9902d.f9876m.m10583a(i, i2, str));
                    } catch (BusinessException e) {
                        return Boolean.valueOf(false);
                    }
                }

                /* renamed from: a */
                protected void m10967a(Boolean bool) {
                    if (bool.booleanValue() && this.f9902d.f9877n != null) {
                        this.f9902d.f9877n.mo3391a(i);
                    }
                }
            }, new String[0]);
        }
    }

    /* renamed from: a */
    public void mo3395a(final String str, final int i, final int i2) {
        if (this.f9875l != null) {
            C2580w.m12905a(this.f9875l, "", "click_comment_send");
            this.f9875l.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, ClubFeedComment>(this) {
                /* renamed from: d */
                final /* synthetic */ C2111b f9906d;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m10968a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m10969a((ClubFeedComment) obj);
                }

                /* renamed from: a */
                protected ClubFeedComment m10968a(String... strArr) {
                    try {
                        return this.f9906d.f9876m.m10570a(i2, str, i);
                    } catch (BusinessException e) {
                        return null;
                    }
                }

                /* renamed from: a */
                protected void m10969a(ClubFeedComment clubFeedComment) {
                    if (clubFeedComment != null && this.f9906d.f9877n != null) {
                        this.f9906d.f9877n.mo3393a(i2, clubFeedComment);
                    }
                }
            }, new String[0]);
        }
    }
}

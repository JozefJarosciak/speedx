package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedBase;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedDetailsActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedInfoActivity;
import com.beastbikes.android.modules.cycling.club.ui.ThumbsListActivity;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.widget.LinearListView;
import com.beastbikes.android.widget.LinearListView.C2126b;
import com.beastbikes.android.widget.ListViewForScroll;
import com.beastbikes.android.widget.p131d.C2120d;
import com.beastbikes.android.widget.p131d.C2634a;
import com.beastbikes.android.widget.p131d.C2637b;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FeedItemComment */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.c */
public class C2127c extends LinearLayout implements OnClickListener, C2126b {
    /* renamed from: a */
    private final int f9923a = 1;
    /* renamed from: b */
    private final int f9924b = 2;
    /* renamed from: c */
    private final int f9925c = 3;
    /* renamed from: d */
    private final int f9926d = 5;
    /* renamed from: e */
    private View f9927e;
    /* renamed from: f */
    private LinearListView f9928f;
    /* renamed from: g */
    private LinearListView f9929g;
    /* renamed from: h */
    private ListViewForScroll f9930h;
    /* renamed from: i */
    private TextView f9931i;
    /* renamed from: j */
    private TextView f9932j;
    /* renamed from: k */
    private C2125c f9933k;
    /* renamed from: l */
    private C2121a f9934l;
    /* renamed from: m */
    private C2124b f9935m;
    /* renamed from: n */
    private LayoutInflater f9936n;
    /* renamed from: o */
    private List<ClubUser> f9937o = new ArrayList();
    /* renamed from: p */
    private ClubFeedBase f9938p;
    /* renamed from: q */
    private C2152d f9939q;
    /* renamed from: r */
    private String f9940r;
    /* renamed from: s */
    private C2111b f9941s;

    /* compiled from: FeedItemComment */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.c$a */
    class C2121a extends BaseAdapter implements C2120d {
        /* renamed from: a */
        final /* synthetic */ C2127c f9913a;

        C2121a(C2127c c2127c) {
            this.f9913a = c2127c;
        }

        public int getCount() {
            if (this.f9913a.f9938p == null || this.f9913a.f9938p.getCommentList() == null) {
                return 0;
            }
            if (this.f9913a.f9938p.getCommentList().size() > 5) {
                return 5;
            }
            return this.f9913a.f9938p.getCommentList().size();
        }

        public Object getItem(int i) {
            return this.f9913a.f9938p.getCommentList().get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Object obj = (ClubFeedComment) getItem(i);
            if (obj == null) {
                return null;
            }
            int length;
            View textView = new TextView(this.f9913a.getContext());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.bottomMargin = this.f9913a.getResources().getDimensionPixelSize(C1373R.dimen.margin_3dp);
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(2, (float) this.f9913a.getResources().getInteger(C1373R.integer.font_comment_size));
            textView.setTextColor(-10066330);
            String str = "";
            str = "";
            str = "";
            Object content = obj.getContent();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (obj.getUser() != null) {
                Object obj2 = obj.getUser().getNickName() + ":  ";
                spannableStringBuilder.append(obj2);
                length = obj2.length();
                C2634a c2634a = new C2634a();
                c2634a.m13126b(0);
                c2634a.m13128c(length);
                c2634a.m13124a(obj.getUser());
                c2634a.m13122a(spannableStringBuilder);
                c2634a.m13123a((C2120d) this);
                c2634a.m13121a(1);
                C2637b.m13133a(c2634a);
            } else {
                length = 0;
            }
            if (obj.getReplyUser() != null) {
                Object nickName = obj.getReplyUser().getNickName();
                Object string = this.f9913a.getContext().getString(C1373R.string.clubfeed_comment_reply);
                spannableStringBuilder.append(string);
                spannableStringBuilder.append(nickName);
                int length2 = string.length() + length;
                length = nickName.length() + length2;
                C2634a c2634a2 = new C2634a();
                c2634a2.m13126b(length2);
                c2634a2.m13128c(length);
                c2634a2.m13122a(spannableStringBuilder);
                c2634a2.m13124a(obj.getReplyUser());
                c2634a2.m13123a((C2120d) this);
                c2634a2.m13121a(2);
                C2637b.m13133a(c2634a2);
            }
            if (!TextUtils.isEmpty(content)) {
                spannableStringBuilder.append(content);
                int length3 = content.length() + length;
                C2634a c2634a3 = new C2634a();
                c2634a3.m13126b(length);
                c2634a3.m13128c(length3);
                c2634a3.m13124a(obj);
                c2634a3.m13122a(spannableStringBuilder);
                c2634a3.m13123a((C2120d) this);
                c2634a3.m13121a(3);
                C2637b.m13133a(c2634a3);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(-10066330), length, length3, 33);
            }
            C2637b.m13132a(textView, spannableStringBuilder, -4473925);
            return textView;
        }

        /* renamed from: a */
        public void mo3417a(C2634a c2634a) {
            if (c2634a == null) {
                return;
            }
            if (c2634a.m13125b() == 3) {
                final ClubFeedComment clubFeedComment = (ClubFeedComment) c2634a.m13120a();
                if (clubFeedComment == null) {
                    return;
                }
                if (clubFeedComment.getUser().getUserId().equals(this.f9913a.f9940r)) {
                    new Builder(this.f9913a.getContext()).setItems(this.f9913a.getContext().getResources().getStringArray(C1373R.array.context_menu_ok_cancel), new DialogInterface.OnClickListener(this) {
                        /* renamed from: b */
                        final /* synthetic */ C2121a f9912b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            switch (i) {
                                case 0:
                                    dialogInterface.dismiss();
                                    this.f9912b.f9913a.m10986a(clubFeedComment.getCid(), clubFeedComment.getFid());
                                    return;
                                case 1:
                                    dialogInterface.dismiss();
                                    return;
                                default:
                                    return;
                            }
                        }
                    }).create().show();
                    return;
                }
                this.f9913a.f9939q.m11050a(clubFeedComment.getFid(), clubFeedComment.getCid());
                this.f9913a.f9939q.setTextHint(this.f9913a.getContext().getString(C1373R.string.clubfeed_comment_reply) + ":  " + clubFeedComment.getUser().getNickName());
                this.f9913a.f9939q.m11049a();
                return;
            }
            ClubUser clubUser = (ClubUser) c2634a.m13120a();
            if (this.f9913a.getContext() != null && clubUser != null) {
                Intent intent = new Intent();
                intent.setClass(this.f9913a.getContext(), ProfileActivity.class);
                intent.putExtra("user_id", clubUser.getUserId());
                intent.putExtra("nick_name", clubUser.getNickName());
                intent.putExtra("avatar", clubUser.getAvatar());
                intent.putExtra("remarks", clubUser.getRemarks());
                this.f9913a.getContext().startActivity(intent);
            }
        }
    }

    /* compiled from: FeedItemComment */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.c$b */
    class C2124b extends BaseAdapter implements C2120d {
        /* renamed from: a */
        final /* synthetic */ C2127c f9921a;

        /* compiled from: FeedItemComment */
        /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.c$b$a */
        class C2123a {
            /* renamed from: a */
            TextView f9916a = null;
            /* renamed from: b */
            TextView f9917b = null;
            /* renamed from: c */
            TextView f9918c = null;
            /* renamed from: d */
            CircleImageView f9919d = null;
            /* renamed from: e */
            final /* synthetic */ C2124b f9920e;

            C2123a(C2124b c2124b, View view) {
                this.f9920e = c2124b;
                this.f9916a = (TextView) view.findViewById(C1373R.id.tv_name);
                this.f9917b = (TextView) view.findViewById(C1373R.id.tv_date);
                this.f9918c = (TextView) view.findViewById(C1373R.id.tv_content);
                this.f9919d = (CircleImageView) view.findViewById(C1373R.id.image);
            }

            /* renamed from: a */
            public void m10974a(ClubFeedComment clubFeedComment, C2120d c2120d) {
                if (clubFeedComment != null) {
                    C2634a c2634a;
                    final ClubUser user = clubFeedComment.getUser();
                    if (user != null) {
                        this.f9916a.setText(user.getNickName());
                        if (TextUtils.isEmpty(user.getAvatar())) {
                            this.f9919d.setImageResource(C1373R.drawable.ic_avatar);
                        } else {
                            Picasso.with(this.f9920e.f9921a.getContext()).load(user.getAvatar()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(this.f9919d);
                        }
                        this.f9919d.setOnClickListener(new OnClickListener(this) {
                            /* renamed from: b */
                            final /* synthetic */ C2123a f9915b;

                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setClass(this.f9915b.f9920e.f9921a.getContext(), ProfileActivity.class);
                                intent.putExtra("user_id", user.getUserId());
                                intent.putExtra("avatar", user.getAvatar());
                                intent.putExtra("nick_name", user.getNickName());
                                intent.putExtra("remarks", user.getRemarks());
                                this.f9915b.f9920e.f9921a.getContext().startActivity(intent);
                            }
                        });
                    }
                    this.f9917b.setText(clubFeedComment.getCreateAt());
                    String str = "";
                    str = "";
                    str = "";
                    Object content = clubFeedComment.getContent();
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    int i = 0;
                    if (clubFeedComment.getReplyUser() != null) {
                        Object nickName = clubFeedComment.getReplyUser().getNickName();
                        Object string = this.f9920e.f9921a.getContext().getString(C1373R.string.clubfeed_comment_reply);
                        spannableStringBuilder.append(string);
                        spannableStringBuilder.append(nickName);
                        int length = string.length() + 0;
                        i = nickName.length() + length;
                        c2634a = new C2634a();
                        c2634a.m13126b(length);
                        c2634a.m13128c(i);
                        c2634a.m13122a(spannableStringBuilder);
                        c2634a.m13124a(clubFeedComment.getReplyUser());
                        c2634a.m13123a(c2120d);
                        c2634a.m13121a(2);
                        C2637b.m13133a(c2634a);
                    }
                    if (!TextUtils.isEmpty(content)) {
                        spannableStringBuilder.append(content);
                        int length2 = content.length() + i;
                        c2634a = new C2634a();
                        c2634a.m13126b(i);
                        c2634a.m13128c(length2);
                        c2634a.m13124a((Object) clubFeedComment);
                        c2634a.m13122a(spannableStringBuilder);
                        c2634a.m13123a(c2120d);
                        c2634a.m13121a(3);
                        C2637b.m13133a(c2634a);
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-10066330), i, length2, 33);
                    }
                    C2637b.m13132a(this.f9918c, spannableStringBuilder, -4473925);
                }
            }
        }

        C2124b(C2127c c2127c) {
            this.f9921a = c2127c;
        }

        public int getCount() {
            if (this.f9921a.f9938p == null || this.f9921a.f9938p.getCommentList() == null) {
                return 0;
            }
            return this.f9921a.f9938p.getCommentList().size();
        }

        public Object getItem(int i) {
            return this.f9921a.f9938p.getCommentList().get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            C2123a c2123a;
            if (view == null) {
                view = this.f9921a.f9936n.inflate(C1373R.layout.clubfeed_item_comment, null);
                C2123a c2123a2 = new C2123a(this, view);
                view.setTag(c2123a2);
                c2123a = c2123a2;
            } else {
                c2123a = (C2123a) view.getTag();
            }
            if (c2123a != null) {
                c2123a.m10974a((ClubFeedComment) getItem(i), this);
            }
            return view;
        }

        /* renamed from: a */
        public void mo3417a(C2634a c2634a) {
            if (c2634a == null) {
                return;
            }
            if (c2634a.m13125b() == 3) {
                ClubFeedComment clubFeedComment = (ClubFeedComment) c2634a.m13120a();
                return;
            }
            ClubUser clubUser = (ClubUser) c2634a.m13120a();
            if (this.f9921a.getContext() != null && clubUser != null) {
                Intent intent = new Intent();
                intent.setClass(this.f9921a.getContext(), ProfileActivity.class);
                intent.putExtra("user_id", clubUser.getUserId());
                intent.putExtra("avatar", clubUser.getAvatar());
                intent.putExtra("nick_name", clubUser.getNickName());
                intent.putExtra("remarks", clubUser.getRemarks());
                this.f9921a.getContext().startActivity(intent);
            }
        }
    }

    /* compiled from: FeedItemComment */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.c$c */
    class C2125c extends BaseAdapter {
        /* renamed from: a */
        final /* synthetic */ C2127c f9922a;

        C2125c(C2127c c2127c) {
            this.f9922a = c2127c;
        }

        public int getCount() {
            int i;
            int i2;
            int i3 = 0;
            if (this.f9922a.f9937o == null || this.f9922a.f9937o.size() <= 0) {
                i = 0;
                i2 = 1;
            } else {
                i = this.f9922a.f9937o.size();
                i2 = 1 + i;
            }
            if (this.f9922a.f9938p != null) {
                this.f9922a.f9932j.setText(String.format(this.f9922a.getContext().getString(C1373R.string.clubfeed_liked), new Object[]{Integer.valueOf(this.f9922a.f9938p.getLikeCount())}));
            }
            View g = this.f9922a.f9927e;
            if (i <= 0) {
                i3 = 8;
            }
            g.setVisibility(i3);
            return i2;
        }

        public Object getItem(int i) {
            return this.f9922a.f9937o.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View circleImageView = new CircleImageView(this.f9922a.getContext());
            LayoutParams layoutParams = new LinearLayout.LayoutParams((int) this.f9922a.getResources().getDimension(C1373R.dimen.avatar_like), (int) this.f9922a.getResources().getDimension(C1373R.dimen.avatar_like));
            layoutParams.setMargins(0, 0, (int) this.f9922a.getResources().getDimension(C1373R.dimen.avatar_margin), 0);
            circleImageView.setScaleType(ScaleType.CENTER_CROP);
            circleImageView.setLayoutParams(layoutParams);
            if (i < getCount() - 1) {
                ClubUser clubUser = (ClubUser) getItem(i);
                if (clubUser == null) {
                    return null;
                }
                if (TextUtils.isEmpty(clubUser.getAvatar())) {
                    circleImageView.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(this.f9922a.getContext()).load(clubUser.getAvatar()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(circleImageView);
                }
                return circleImageView;
            }
            circleImageView.setImageResource(C1373R.drawable.ic_paire_more);
            return circleImageView;
        }
    }

    public C2127c(Context context, String str, C2111b c2111b) {
        super(context);
        this.f9940r = str;
        this.f9941s = c2111b;
        this.f9936n = LayoutInflater.from(context);
        m10978a();
    }

    public void setCommentEditView(C2152d c2152d) {
        this.f9939q = c2152d;
    }

    /* renamed from: a */
    private void m10978a() {
        LayoutInflater.from(getContext()).inflate(C1373R.layout.clubfeed_comment, this);
        this.f9927e = findViewById(C1373R.id.like_container);
        this.f9930h = (ListViewForScroll) findViewById(C1373R.id.common_list1);
        this.f9928f = (LinearListView) findViewById(C1373R.id.like_list);
        this.f9929g = (LinearListView) findViewById(C1373R.id.common_list);
        this.f9931i = (TextView) findViewById(C1373R.id.show_all);
        this.f9931i.setOnClickListener(this);
        this.f9932j = (TextView) findViewById(C1373R.id.tv_like_count);
        this.f9933k = new C2125c(this);
        if ((getContext() instanceof ClubFeedInfoActivity) || (getContext() instanceof HomeActivity)) {
            this.f9934l = new C2121a(this);
            this.f9929g.setAdapter(this.f9934l);
        } else {
            this.f9935m = new C2124b(this);
            this.f9930h.setAdapter(this.f9935m);
        }
        this.f9928f.setAdapter(this.f9933k);
        this.f9928f.setOnItemClickListener(this);
    }

    /* renamed from: a */
    public void m10987a(ClubFeedBase clubFeedBase) {
        int i = 0;
        if (clubFeedBase != null) {
            this.f9938p = clubFeedBase;
            this.f9937o = clubFeedBase.getLikeList();
            if ((getContext() instanceof ClubFeedInfoActivity) || (getContext() instanceof HomeActivity)) {
                this.f9934l.notifyDataSetChanged();
                this.f9931i.setText(String.format(getContext().getString(C1373R.string.clubfeed_show_comment), new Object[]{Integer.valueOf(clubFeedBase.getCommentCount())}));
                TextView textView = this.f9931i;
                if (clubFeedBase.getCommentCount() < 5) {
                    i = 8;
                }
                textView.setVisibility(i);
            } else {
                this.f9935m.notifyDataSetChanged();
            }
            this.f9933k.notifyDataSetChanged();
        }
    }

    public void onClick(View view) {
        if (view == this.f9931i) {
            Intent intent = new Intent(getContext(), ClubFeedDetailsActivity.class);
            intent.putExtra("feed_id", this.f9938p.getFid());
            intent.putExtra("is_my_club", this.f9941s.f9880q);
            getContext().startActivity(intent);
        }
    }

    /* renamed from: a */
    public void mo3418a(LinearListView linearListView, View view, int i, long j) {
        if (linearListView != this.f9928f) {
            return;
        }
        if (i == this.f9933k.getCount() - 1) {
            Intent intent = new Intent(getContext(), ThumbsListActivity.class);
            intent.putExtra("feed_id", this.f9938p.getFid());
            getContext().startActivity(intent);
            return;
        }
        ClubUser clubUser = (ClubUser) this.f9933k.getItem(i);
        if (clubUser != null && getContext() != null) {
            Intent intent2 = new Intent();
            intent2.setClass(getContext(), ProfileActivity.class);
            intent2.putExtra("user_id", clubUser.getUserId());
            intent2.putExtra("avatar", clubUser.getAvatar());
            intent2.putExtra("nick_name", clubUser.getNickName());
            intent2.putExtra("remarks", clubUser.getRemarks());
            getContext().startActivity(intent2);
        }
    }

    /* renamed from: a */
    public void m10986a(final int i, final int i2) {
        final SessionFragmentActivity sessionFragmentActivity = (SessionFragmentActivity) getContext();
        sessionFragmentActivity.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Boolean>(this) {
            /* renamed from: d */
            final /* synthetic */ C2127c f9910d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10970a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10971a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m10970a(String... strArr) {
                try {
                    return Boolean.valueOf(new C2052c(sessionFragmentActivity).m10589b(i));
                } catch (BusinessException e) {
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m10971a(Boolean bool) {
                if (bool.booleanValue() && this.f9910d.f9941s != null && this.f9910d.f9941s.f9877n != null) {
                    this.f9910d.f9941s.f9877n.mo3392a(i2, i);
                }
            }
        }, new String[0]);
    }
}

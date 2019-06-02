package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.content.Intent;
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
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedDetailsActivity;
import com.beastbikes.android.modules.cycling.club.ui.ThumbsListActivity;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.widget.LinearListView;
import com.beastbikes.android.widget.LinearListView.C2126b;
import com.beastbikes.android.widget.ListViewForScroll;
import com.beastbikes.android.widget.p131d.C2120d;
import com.beastbikes.android.widget.p131d.C2634a;
import com.beastbikes.android.widget.p131d.C2637b;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PhotoItemComment */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.j */
public class C2142j extends LinearLayout implements OnClickListener, C2126b {
    /* renamed from: a */
    private final int f10012a = 1;
    /* renamed from: b */
    private final int f10013b = 2;
    /* renamed from: c */
    private final int f10014c = 3;
    /* renamed from: d */
    private View f10015d;
    /* renamed from: e */
    private LinearListView f10016e;
    /* renamed from: f */
    private LinearListView f10017f;
    /* renamed from: g */
    private ListViewForScroll f10018g;
    /* renamed from: h */
    private TextView f10019h;
    /* renamed from: i */
    private TextView f10020i;
    /* renamed from: j */
    private C2141b f10021j;
    /* renamed from: k */
    private C2140a f10022k;
    /* renamed from: l */
    private LayoutInflater f10023l;
    /* renamed from: m */
    private List<ClubUser> f10024m = new ArrayList();
    /* renamed from: n */
    private List<ClubFeedComment> f10025n = new ArrayList();
    /* renamed from: o */
    private ClubPhotoDTO f10026o;
    /* renamed from: p */
    private C2152d f10027p;

    /* compiled from: PhotoItemComment */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.j$a */
    class C2140a extends BaseAdapter implements C2120d {
        /* renamed from: a */
        final /* synthetic */ C2142j f10010a;

        /* compiled from: PhotoItemComment */
        /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.j$a$a */
        class C2139a {
            /* renamed from: a */
            TextView f10005a = null;
            /* renamed from: b */
            TextView f10006b = null;
            /* renamed from: c */
            TextView f10007c = null;
            /* renamed from: d */
            CircleImageView f10008d = null;
            /* renamed from: e */
            final /* synthetic */ C2140a f10009e;

            C2139a(C2140a c2140a, View view) {
                this.f10009e = c2140a;
                this.f10005a = (TextView) view.findViewById(C1373R.id.tv_name);
                this.f10006b = (TextView) view.findViewById(C1373R.id.tv_date);
                this.f10007c = (TextView) view.findViewById(C1373R.id.tv_content);
                this.f10008d = (CircleImageView) view.findViewById(C1373R.id.image);
            }

            /* renamed from: a */
            public void m11014a(ClubFeedComment clubFeedComment, C2120d c2120d) {
                if (clubFeedComment != null) {
                    C2634a c2634a;
                    ClubUser user = clubFeedComment.getUser();
                    if (user != null) {
                        this.f10005a.setText(user.getNickName());
                        if (TextUtils.isEmpty(user.getAvatar())) {
                            this.f10008d.setImageResource(C1373R.drawable.ic_avatar);
                        } else {
                            Picasso.with(this.f10009e.f10010a.getContext()).load(user.getAvatar()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(this.f10008d);
                        }
                    }
                    this.f10006b.setText(clubFeedComment.getCreateAt());
                    String str = "";
                    str = "";
                    str = "";
                    Object content = clubFeedComment.getContent();
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    int i = 0;
                    if (clubFeedComment.getReplyUser() != null) {
                        Object nickName = clubFeedComment.getReplyUser().getNickName();
                        Object string = this.f10009e.f10010a.getContext().getString(C1373R.string.clubfeed_comment_reply);
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
                    C2637b.m13132a(this.f10007c, spannableStringBuilder, -4473925);
                }
            }
        }

        C2140a(C2142j c2142j) {
            this.f10010a = c2142j;
        }

        public int getCount() {
            if (this.f10010a.f10025n != null) {
                return this.f10010a.f10025n.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return this.f10010a.f10025n.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            C2139a c2139a;
            if (view == null) {
                view = this.f10010a.f10023l.inflate(C1373R.layout.clubfeed_item_comment, null);
                C2139a c2139a2 = new C2139a(this, view);
                view.setTag(c2139a2);
                c2139a = c2139a2;
            } else {
                c2139a = (C2139a) view.getTag();
            }
            if (c2139a != null) {
                c2139a.m11014a((ClubFeedComment) getItem(i), this);
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
                this.f10010a.f10027p.m11051b();
                this.f10010a.f10027p.m11050a(clubFeedComment.getFid(), clubFeedComment.getCid());
                Toasts.show(this.f10010a.getContext(), clubFeedComment.getContent());
                return;
            }
            ClubUser clubUser = (ClubUser) c2634a.m13120a();
            if (this.f10010a.getContext() != null && clubUser != null) {
                Intent intent = new Intent();
                intent.setClass(this.f10010a.getContext(), ProfileActivity.class);
                intent.putExtra("user_id", clubUser.getUserId());
                intent.putExtra("avatar", clubUser.getAvatar());
                intent.putExtra("nick_name", clubUser.getNickName());
                intent.putExtra("remarks", clubUser.getRemarks());
                this.f10010a.getContext().startActivity(intent);
                Toasts.show(this.f10010a.getContext(), clubUser.getUserId() + "name=" + clubUser.getNickName());
            }
        }
    }

    /* compiled from: PhotoItemComment */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.j$b */
    class C2141b extends BaseAdapter {
        /* renamed from: a */
        final /* synthetic */ C2142j f10011a;

        C2141b(C2142j c2142j) {
            this.f10011a = c2142j;
        }

        public int getCount() {
            int i;
            int i2;
            int i3 = 0;
            if (this.f10011a.f10024m == null || this.f10011a.f10024m.size() <= 0) {
                i = 0;
                i2 = 1;
            } else {
                i = this.f10011a.f10024m.size();
                i2 = 1 + i;
            }
            this.f10011a.f10020i.setText(String.format(this.f10011a.getContext().getString(C1373R.string.clubfeed_liked), new Object[]{Integer.valueOf(i)}));
            View f = this.f10011a.f10015d;
            if (i <= 0) {
                i3 = 8;
            }
            f.setVisibility(i3);
            return i2;
        }

        public Object getItem(int i) {
            return this.f10011a.f10024m.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View circleImageView = new CircleImageView(this.f10011a.getContext());
            LayoutParams layoutParams = new LinearLayout.LayoutParams((int) this.f10011a.getResources().getDimension(C1373R.dimen.avatar_like), (int) this.f10011a.getResources().getDimension(C1373R.dimen.avatar_like));
            layoutParams.setMargins(0, 0, (int) this.f10011a.getResources().getDimension(C1373R.dimen.avatar_margin), 0);
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
                    int a = C2801d.m13756a(this.f10011a.getContext(), 24.0f);
                    Picasso.with(this.f10011a.getContext()).load(clubUser.getAvatar()).error(C1373R.drawable.ic_avatar).resize(a, a).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(circleImageView);
                }
                return circleImageView;
            }
            circleImageView.setImageResource(C1373R.drawable.ic_paire_more);
            return circleImageView;
        }
    }

    public C2142j(Context context, C2152d c2152d) {
        super(context);
        this.f10023l = LayoutInflater.from(context);
        this.f10027p = c2152d;
        m11017a();
    }

    /* renamed from: a */
    private void m11017a() {
        LayoutInflater.from(getContext()).inflate(C1373R.layout.clubfeed_comment, this);
        this.f10015d = findViewById(C1373R.id.like_container);
        this.f10018g = (ListViewForScroll) findViewById(C1373R.id.common_list1);
        this.f10016e = (LinearListView) findViewById(C1373R.id.like_list);
        this.f10017f = (LinearListView) findViewById(C1373R.id.common_list);
        this.f10019h = (TextView) findViewById(C1373R.id.show_all);
        this.f10019h.setOnClickListener(this);
        this.f10020i = (TextView) findViewById(C1373R.id.tv_like_count);
        this.f10021j = new C2141b(this);
        this.f10022k = new C2140a(this);
        this.f10018g.setAdapter(this.f10022k);
        this.f10016e.setAdapter(this.f10021j);
        this.f10016e.setOnItemClickListener(this);
    }

    /* renamed from: a */
    public void m11023a(ClubPhotoDTO clubPhotoDTO) {
        if (clubPhotoDTO != null) {
            this.f10026o = clubPhotoDTO;
            this.f10025n = clubPhotoDTO.getCommentList();
            if (this.f10025n == null || this.f10025n.size() <= 0) {
                this.f10018g.setVisibility(8);
            } else {
                this.f10018g.setVisibility(0);
            }
            this.f10024m = clubPhotoDTO.getLikeUserList();
            if (this.f10024m == null || this.f10024m.size() <= 0) {
                this.f10016e.setVisibility(8);
            } else {
                this.f10016e.setVisibility(0);
            }
            this.f10022k.notifyDataSetChanged();
            this.f10021j.notifyDataSetChanged();
        }
    }

    public void onClick(View view) {
        if (view == this.f10019h) {
            Intent intent = new Intent(getContext(), ClubFeedDetailsActivity.class);
            intent.putExtra("feed_id", this.f10026o.getPhotoId());
            getContext().startActivity(intent);
        }
    }

    /* renamed from: a */
    public void mo3418a(LinearListView linearListView, View view, int i, long j) {
        if (linearListView != this.f10016e) {
            return;
        }
        if (i < this.f10021j.getCount() - 1) {
            ClubUser clubUser = (ClubUser) this.f10021j.getItem(i);
            if (clubUser != null && getContext() != null) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ProfileActivity.class);
                intent.putExtra("user_id", clubUser.getUserId());
                intent.putExtra("avatar", clubUser.getAvatar());
                intent.putExtra("nick_name", clubUser.getNickName());
                intent.putExtra("remarks", clubUser.getRemarks());
                getContext().startActivity(intent);
                return;
            }
            return;
        }
        Intent intent2 = new Intent(getContext(), ThumbsListActivity.class);
        intent2.putExtra("photo_id", this.f10026o.getPhotoId());
        getContext().startActivity(intent2);
    }
}

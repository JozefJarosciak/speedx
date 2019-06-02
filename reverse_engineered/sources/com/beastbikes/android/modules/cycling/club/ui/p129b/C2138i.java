package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/* compiled from: PhotoItemBase */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.i */
public class C2138i<K> extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    protected CircleImageView f9986a;
    /* renamed from: b */
    protected TextView f9987b;
    /* renamed from: c */
    protected TextView f9988c;
    /* renamed from: d */
    protected TextView f9989d;
    /* renamed from: e */
    protected TextView f9990e;
    /* renamed from: f */
    protected TextView f9991f;
    /* renamed from: g */
    protected ImageView f9992g;
    /* renamed from: h */
    protected ImageView f9993h;
    /* renamed from: i */
    protected ImageView f9994i;
    /* renamed from: j */
    protected FrameLayout f9995j;
    /* renamed from: k */
    protected View f9996k;
    /* renamed from: l */
    protected C2142j f9997l;
    /* renamed from: m */
    protected String f9998m;
    /* renamed from: n */
    protected SessionFragmentActivity f9999n;
    /* renamed from: o */
    protected C2052c f10000o;
    /* renamed from: p */
    protected C2137a f10001p;
    /* renamed from: q */
    protected C2152d f10002q;
    /* renamed from: r */
    private K f10003r;
    /* renamed from: s */
    private ClubPhotoDTO f10004s;

    /* compiled from: PhotoItemBase */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.i$a */
    public interface C2137a {
    }

    public void setClubFeedListener(C2137a c2137a) {
        this.f10001p = c2137a;
    }

    public void setCommentEditView(C2152d c2152d) {
        this.f10002q = c2152d;
    }

    public C2138i(Context context, View view, C2152d c2152d) {
        super(context);
        if (context != null && (context instanceof SessionFragmentActivity)) {
            this.f9999n = (SessionFragmentActivity) context;
        }
        this.f10000o = new C2052c(this.f9999n);
        this.f9996k = view;
        this.f9996k.setLayoutParams(new LayoutParams(-1, -1));
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f9998m = currentUser.getObjectId();
        }
        this.f10002q = c2152d;
        m11008a();
    }

    /* renamed from: a */
    private void m11008a() {
        this.f9988c = (TextView) this.f9996k.findViewById(C1373R.id.activity_complete_nickname);
        this.f9989d = (TextView) this.f9996k.findViewById(C1373R.id.time);
        this.f9990e = (TextView) this.f9996k.findViewById(C1373R.id.delete);
        this.f9992g = (ImageView) this.f9996k.findViewById(C1373R.id.praise);
        this.f9993h = (ImageView) this.f9996k.findViewById(C1373R.id.comment);
        this.f9986a = (CircleImageView) this.f9996k.findViewById(C1373R.id.avatar);
        this.f9994i = (ImageView) this.f9996k.findViewById(C1373R.id.container);
        this.f9987b = (TextView) this.f9996k.findViewById(C1373R.id.tv_extra);
        this.f9991f = (TextView) this.f9996k.findViewById(C1373R.id.text);
        this.f9995j = (FrameLayout) this.f9996k.findViewById(C1373R.id.common_container);
        this.f9990e.setVisibility(8);
        if (this.f9997l == null) {
            this.f9997l = new C2142j(getContext(), this.f10002q);
            this.f9995j.addView(this.f9997l);
        }
    }

    /* renamed from: a */
    public void m11013a(K k) {
        this.f10003r = k;
        if (k instanceof ClubPhotoDTO) {
            int i;
            int i2;
            this.f10004s = (ClubPhotoDTO) k;
            this.f9989d.setText(C2555d.m12794a(this.f9999n, this.f10004s.getCreateDate()));
            this.f9990e.setOnClickListener(this);
            this.f9992g.setOnClickListener(this);
            this.f9993h.setOnClickListener(this);
            this.f9986a.setOnClickListener(this);
            if (TextUtils.isEmpty(this.f10004s.getContent()) || this.f9991f == null) {
                this.f9991f.setVisibility(8);
            } else {
                this.f9991f.setText(this.f10004s.getContent() + "");
                this.f9991f.setVisibility(0);
            }
            this.f9988c.setText(C2570p.m12883a(this.f10004s.getNickName(), this.f10004s.getRemarks()));
            if (TextUtils.isEmpty(this.f10004s.getAvatar())) {
                this.f9986a.setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(getContext()).load(this.f10004s.getAvatar()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(this.f9986a);
            }
            if (!TextUtils.isEmpty(this.f10004s.getImageUrl())) {
                Picasso.with(getContext()).load(this.f10004s.getImageUrl()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(this.f9994i);
            }
            if (this.f10004s.getUserId().equals(this.f9998m)) {
                this.f9990e.setVisibility(0);
            }
            if (this.f10004s.getCommentList() == null) {
                i = 0;
            } else {
                i = this.f10004s.getCommentList().size();
            }
            if (this.f10004s.getLikeUserList() == null) {
                i2 = 0;
            } else {
                i2 = this.f10004s.getLikeUserList().size();
            }
            if (i + i2 > 0) {
                this.f9995j.setVisibility(0);
                this.f9997l.m11023a(this.f10004s);
            } else {
                this.f9995j.setVisibility(8);
            }
            this.f9992g.setSelected(this.f10004s.isHasLiked());
        }
    }

    public void onClick(View view) {
        int i = 0;
        if (this.f10003r != null) {
            ClubPhotoDTO clubPhotoDTO = (ClubPhotoDTO) this.f10003r;
            switch (view.getId()) {
                case C1373R.id.comment:
                    this.f10002q.m11051b();
                    this.f10002q.m11050a(clubPhotoDTO.getPhotoId(), 0);
                    return;
                case C1373R.id.avatar:
                    if (this.f9999n != null) {
                        Intent intent = new Intent();
                        intent.setClass(getContext(), ProfileActivity.class);
                        intent.putExtra("user_id", clubPhotoDTO.getUserId());
                        intent.putExtra("avatar", clubPhotoDTO.getAvatar());
                        intent.putExtra("nick_name", clubPhotoDTO.getNickName());
                        intent.putExtra("remarks", clubPhotoDTO.getRemarks());
                        this.f9999n.startActivity(intent);
                        return;
                    }
                    return;
                case C1373R.id.delete:
                    m11009a(clubPhotoDTO.getPhotoId());
                    return;
                case C1373R.id.praise:
                    if (view.isSelected()) {
                        i = 1;
                    }
                    m11010a(i, clubPhotoDTO.getPhotoId());
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m11010a(final int i, final int i2) {
        if (this.f9999n != null) {
            this.f9999n.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Boolean>(this) {
                /* renamed from: c */
                final /* synthetic */ C2138i f9978c;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m11003a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m11004a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m11003a(String... strArr) {
                    return Boolean.valueOf(this.f9978c.f10000o.m10590b(i, i2));
                }

                /* renamed from: a */
                protected void m11004a(Boolean bool) {
                    boolean z = true;
                    if (bool.booleanValue()) {
                        ClubPhotoDTO clubPhotoDTO = (ClubPhotoDTO) this.f9978c.f10003r;
                        if (clubPhotoDTO != null) {
                            clubPhotoDTO.setHasLiked(i == 0);
                        }
                        ImageView imageView = this.f9978c.f9992g;
                        if (i != 0) {
                            z = false;
                        }
                        imageView.setSelected(z);
                    }
                }
            }, new String[0]);
        }
    }

    /* renamed from: a */
    private void m11009a(final int i) {
        final C2621c c2621c = new C2621c(getContext());
        c2621c.m13058a((int) C1373R.string.club_feed_del_hint);
        c2621c.m13065b((int) C1373R.string.club_feed_del_hint_del);
        c2621c.m13059a((int) C1373R.string.activity_alert_dialog_text_ok, new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C2138i f9983c;

            public void onClick(View view) {
                c2621c.m13069b();
                this.f9983c.m11012b(i);
            }
        }).m13066b((int) C1373R.string.cancel, new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2138i f9980b;

            public void onClick(View view) {
                c2621c.m13069b();
            }
        }).m13063a();
    }

    /* renamed from: b */
    private void m11012b(final int i) {
        if (this.f9999n != null) {
            this.f9999n.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Boolean>(this) {
                /* renamed from: b */
                final /* synthetic */ C2138i f9985b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m11005a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m11006a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m11005a(String... strArr) {
                    return Boolean.valueOf(this.f9985b.f10000o.m10593c(i));
                }

                /* renamed from: a */
                protected void m11006a(Boolean bool) {
                    if (bool.booleanValue()) {
                        this.f9985b.f9999n.finish();
                    }
                }
            }, new String[0]);
        }
    }
}

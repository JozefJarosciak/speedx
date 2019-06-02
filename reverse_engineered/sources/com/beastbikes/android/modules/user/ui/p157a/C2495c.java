package com.beastbikes.android.modules.user.ui.p157a;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* compiled from: MedalViewPagerAdapter */
/* renamed from: com.beastbikes.android.modules.user.ui.a.c */
public class C2495c extends C2489a<MedalDTO> implements OnClickListener {
    /* renamed from: b */
    private Context f11840b;
    /* renamed from: c */
    private LayoutInflater f11841c;
    /* renamed from: d */
    private boolean f11842d;
    /* renamed from: e */
    private C2494a f11843e;

    /* compiled from: MedalViewPagerAdapter */
    /* renamed from: com.beastbikes.android.modules.user.ui.a.c$a */
    public interface C2494a {
        /* renamed from: a */
        void m12567a(MedalDTO medalDTO);
    }

    public C2495c(Context context, ArrayList<MedalDTO> arrayList, boolean z) {
        super(arrayList);
        this.f11840b = context;
        this.f11842d = z;
    }

    /* renamed from: b */
    protected View mo3511b(int i) {
        if (this.f11841c == null) {
            this.f11841c = LayoutInflater.from(this.f11840b);
        }
        View inflate = this.f11841c.inflate(C1373R.layout.medal_detail_item, null);
        View view = (ImageView) inflate.findViewById(C1373R.id.activity_medal_info_icon);
        ImageView imageView = (ImageView) inflate.findViewById(C1373R.id.activity_medal_info_light_view);
        MedalDTO medalDTO = (MedalDTO) m12560a(i);
        if (medalDTO.getStatus() < 2) {
            if (!TextUtils.isEmpty(medalDTO.getUnLightUrl())) {
                Picasso.with(this.f11840b).load(medalDTO.getUnLightUrl()).fit().centerCrop().into(view);
            }
            imageView.setVisibility(8);
        } else {
            if (!TextUtils.isEmpty(medalDTO.getLightUrl())) {
                Picasso.with(this.f11840b).load(medalDTO.getLightUrl()).fit().centerCrop().into(view);
            }
            imageView.setVisibility(0);
        }
        TextView textView;
        if (this.f11842d) {
            ((ViewStub) inflate.findViewById(C1373R.id.activity_medal_info_viewStub_push_item)).inflate();
            textView = (TextView) inflate.findViewById(C1373R.id.activity_medal_info_push_additional_get);
            Button button = (Button) inflate.findViewById(C1373R.id.activity_medal_info_push_bottom_btn);
            ((TextView) inflate.findViewById(C1373R.id.activity_medal_info_push_medal_name)).setText(String.format(medalDTO.getName(), new Object[]{""}));
            textView.setText(this.f11840b.getString(C1373R.string.str_additional_get) + medalDTO.getGiftName());
            button.setTag(medalDTO);
            if (medalDTO.getGiftId() <= 0 || medalDTO.getActivityId() <= 0) {
                button.setText(C1373R.string.activity_alert_dialog_text_ok);
                textView.setVisibility(4);
            } else {
                if (medalDTO.getStatus() == 3) {
                    button.setText(C1373R.string.str_label_has_received_award);
                    button.setOnClickListener(null);
                } else {
                    button.setText(C1373R.string.str_go_to_get_prize);
                    button.setOnClickListener(this);
                }
                textView.setVisibility(0);
            }
        } else {
            ((ViewStub) inflate.findViewById(C1373R.id.activity_medal_info_viewStub_item)).inflate();
            TextView textView2 = (TextView) inflate.findViewById(C1373R.id.activity_medal_info_light_count);
            textView = (TextView) inflate.findViewById(C1373R.id.activity_medal_info_status);
            TextView textView3 = (TextView) inflate.findViewById(C1373R.id.activity_medal_info_title);
            TextView textView4 = (TextView) inflate.findViewById(C1373R.id.activity_medal_info_detail);
            if (medalDTO.getStatus() < 2) {
                textView.setVisibility(0);
                textView2.setText(String.format(this.f11840b.getString(C1373R.string.label_medal_unlight_count_msg), new Object[]{Integer.valueOf(medalDTO.getTotalLight())}));
            } else {
                textView.setVisibility(4);
                textView2.setText(String.format(this.f11840b.getString(C1373R.string.label_medal_light_count_msg), new Object[]{Integer.valueOf(medalDTO.getRank())}));
            }
            textView3.setText(medalDTO.getName());
            textView4.setText(medalDTO.getDetail());
        }
        m12568a(view);
        m12569b(view);
        return inflate;
    }

    /* renamed from: a */
    private void m12568a(View view) {
        Animation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setRepeatCount(10);
        alphaAnimation.setRepeatMode(2);
        view.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }

    /* renamed from: b */
    private void m12569b(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f11840b, C1373R.anim.medal_info_anim);
        view.startAnimation(loadAnimation);
        loadAnimation.start();
    }

    public void onClick(View view) {
        if (this.f11843e != null) {
            this.f11843e.m12567a((MedalDTO) view.getTag());
        }
    }

    /* renamed from: a */
    public void m12570a(C2494a c2494a) {
        this.f11843e = c2494a;
    }
}

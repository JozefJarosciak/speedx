package com.twitter.sdk.android.tweetcomposer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.internal.UserUtils;
import com.twitter.sdk.android.core.internal.UserUtils.AvatarSize;
import com.twitter.sdk.android.core.internal.util.ObservableScrollView;
import com.twitter.sdk.android.core.internal.util.ObservableScrollView.C4644a;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetcomposer.C4680e.C4677a;

public class ComposerView extends LinearLayout {
    /* renamed from: a */
    ImageView f16430a;
    /* renamed from: b */
    ImageView f16431b;
    /* renamed from: c */
    EditText f16432c;
    /* renamed from: d */
    TextView f16433d;
    /* renamed from: e */
    Button f16434e;
    /* renamed from: f */
    ObservableScrollView f16435f;
    /* renamed from: g */
    View f16436g;
    /* renamed from: h */
    ColorDrawable f16437h;
    /* renamed from: i */
    ViewGroup f16438i;
    /* renamed from: j */
    C4677a f16439j;
    /* renamed from: k */
    private Picasso f16440k;

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$1 */
    class C46611 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ ComposerView f16425a;

        C46611(ComposerView composerView) {
            this.f16425a = composerView;
        }

        public void onClick(View view) {
            this.f16425a.f16439j.mo6157a();
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$2 */
    class C46622 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ ComposerView f16426a;

        C46622(ComposerView composerView) {
            this.f16426a = composerView;
        }

        public void onClick(View view) {
            this.f16426a.f16439j.mo6159b(this.f16426a.getTweetText());
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$3 */
    class C46633 implements OnEditorActionListener {
        /* renamed from: a */
        final /* synthetic */ ComposerView f16427a;

        C46633(ComposerView composerView) {
            this.f16427a = composerView;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            this.f16427a.f16439j.mo6159b(this.f16427a.getTweetText());
            return true;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$4 */
    class C46644 implements TextWatcher {
        /* renamed from: a */
        final /* synthetic */ ComposerView f16428a;

        C46644(ComposerView composerView) {
            this.f16428a = composerView;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.f16428a.f16439j.mo6158a(this.f16428a.getTweetText());
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$5 */
    class C46655 implements C4644a {
        /* renamed from: a */
        final /* synthetic */ ComposerView f16429a;

        C46655(ComposerView composerView) {
            this.f16429a = composerView;
        }

        /* renamed from: a */
        public void mo6156a(int i) {
            if (i > 0) {
                this.f16429a.f16436g.setVisibility(0);
            } else {
                this.f16429a.f16436g.setVisibility(4);
            }
        }
    }

    public ComposerView(Context context) {
        this(context, null);
    }

    public ComposerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m18412a(context);
    }

    @TargetApi(11)
    public ComposerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m18412a(context);
    }

    /* renamed from: a */
    private void m18412a(Context context) {
        this.f16440k = Picasso.with(getContext());
        this.f16437h = new ColorDrawable(context.getResources().getColor(C4666R.color.tw__composer_light_gray));
        inflate(context, C4666R.layout.tw__composer_view, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        m18413a();
        this.f16431b.setOnClickListener(new C46611(this));
        this.f16434e.setOnClickListener(new C46622(this));
        this.f16432c.setOnEditorActionListener(new C46633(this));
        this.f16432c.addTextChangedListener(new C46644(this));
        this.f16435f.setScrollViewListener(new C46655(this));
    }

    /* renamed from: a */
    void m18413a() {
        this.f16430a = (ImageView) findViewById(C4666R.id.tw__author_avatar);
        this.f16431b = (ImageView) findViewById(C4666R.id.tw__composer_close);
        this.f16432c = (EditText) findViewById(C4666R.id.tw__edit_tweet);
        this.f16433d = (TextView) findViewById(C4666R.id.tw__char_count);
        this.f16434e = (Button) findViewById(C4666R.id.tw__post_tweet);
        this.f16435f = (ObservableScrollView) findViewById(C4666R.id.tw__composer_scroll_view);
        this.f16436g = findViewById(C4666R.id.tw__composer_profile_divider);
        this.f16438i = (ViewGroup) findViewById(C4666R.id.tw__card_view);
    }

    void setCallbacks(C4677a c4677a) {
        this.f16439j = c4677a;
    }

    void setProfilePhotoView(User user) {
        String a = UserUtils.m18217a(user, AvatarSize.REASONABLY_SMALL);
        if (this.f16440k != null) {
            this.f16440k.load(a).placeholder(this.f16437h).into(this.f16430a);
        }
    }

    String getTweetText() {
        return this.f16432c.getText().toString();
    }

    void setTweetText(String str) {
        this.f16432c.setText(str);
    }

    /* renamed from: b */
    void m18415b() {
        this.f16432c.setSelection(getTweetText().length());
    }

    void setCharCount(int i) {
        this.f16433d.setText(Integer.toString(i));
    }

    void setCharCountTextStyle(int i) {
        this.f16433d.setTextAppearance(getContext(), i);
    }

    /* renamed from: a */
    void m18414a(boolean z) {
        this.f16434e.setEnabled(z);
    }

    void setCardView(View view) {
        this.f16438i.addView(view);
        this.f16438i.setVisibility(0);
    }
}

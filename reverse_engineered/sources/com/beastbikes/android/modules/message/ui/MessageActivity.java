package com.beastbikes.android.modules.message.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.alipay.sdk.cons.C0845b;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.message.dto.MessageDTO;
import com.beastbikes.android.modules.message.p071a.C2310a;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.HtmlImageGetter;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

@C1457a(a = "我的消息")
@C1459b(a = 2130903517)
public class MessageActivity extends SessionFragmentActivity {
    @C1458a(a = 2131757192)
    /* renamed from: a */
    private ListView f5871a;
    /* renamed from: b */
    private C2310a f5872b;
    /* renamed from: c */
    private MessageActivity$b f5873c;
    /* renamed from: d */
    private List<MessageDTO> f5874d = new ArrayList();
    /* renamed from: e */
    private SharedPreferences f5875e;

    /* renamed from: com.beastbikes.android.modules.message.ui.MessageActivity$c */
    private final class C1428c extends ViewHolder<MessageDTO> {
        /* renamed from: a */
        final /* synthetic */ MessageActivity f5867a;
        @C1458a(a = 2131757193)
        /* renamed from: b */
        private TextView f5868b;
        @C1458a(a = 2131757196)
        /* renamed from: c */
        private ViewGroup f5869c;
        @C1458a(a = 2131757195)
        /* renamed from: d */
        private TextView f5870d;

        @SuppressLint({"SimpleDateFormat"})
        public /* synthetic */ void bind(Object obj) {
            m7121a((MessageDTO) obj);
        }

        protected C1428c(MessageActivity messageActivity, View view) {
            this.f5867a = messageActivity;
            super(view);
        }

        @SuppressLint({"SimpleDateFormat"})
        /* renamed from: a */
        public void m7121a(MessageDTO messageDTO) {
            if (messageDTO != null) {
                this.f5868b.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(messageDTO.getAvailableTime()));
                SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) Html.fromHtml(messageDTO.getMessage(), new HtmlImageGetter(this.f5867a.getRequestQueueFactory().b(this.f5867a), this.f5870d), null);
                for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class)) {
                    Uri parse = Uri.parse(uRLSpan.getURL());
                    if (C0845b.f2060a.equalsIgnoreCase(parse.getScheme()) || HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(parse.getScheme())) {
                        spannableStringBuilder.setSpan(new MessageActivity$a(this.f5867a, uRLSpan.getURL()), spannableStringBuilder.getSpanStart(uRLSpan), spannableStringBuilder.getSpanEnd(uRLSpan), spannableStringBuilder.getSpanFlags(uRLSpan));
                        spannableStringBuilder.removeSpan(uRLSpan);
                    }
                }
                this.f5869c.setVisibility(8);
                this.f5870d.setVisibility(0);
                this.f5870d.setText(spannableStringBuilder);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5872b = new C2310a(this);
        this.f5873c = new MessageActivity$b(this, this, this.f5874d);
        this.f5875e = getSharedPreferences(m5331p(), 0);
        this.f5875e.edit().putInt("beast.friend.new.message.count", 0).commit();
        this.f5871a.setAdapter(this.f5873c);
        this.f5871a.setSelection(this.f5874d.size() - 1);
        m7123a();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m7123a() {
        getAsyncTaskQueue().a(new MessageActivity$1(this), new Void[0]);
    }
}

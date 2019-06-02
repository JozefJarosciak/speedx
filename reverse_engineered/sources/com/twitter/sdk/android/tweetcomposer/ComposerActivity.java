package com.twitter.sdk.android.tweetcomposer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.twitter.sdk.android.core.C1514q;
import com.twitter.sdk.android.core.TwitterAuthToken;

public class ComposerActivity extends Activity {

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerActivity$a */
    interface C4659a {
        /* renamed from: a */
        void mo6155a();
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerActivity$b */
    class C4660b implements C4659a {
        /* renamed from: a */
        final /* synthetic */ ComposerActivity f16424a;

        C4660b(ComposerActivity composerActivity) {
            this.f16424a = composerActivity;
        }

        /* renamed from: a */
        public void mo6155a() {
            this.f16424a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        C1514q c1514q = new C1514q((TwitterAuthToken) intent.getParcelableExtra("EXTRA_USER_TOKEN"), -1, "");
        Card card = (Card) intent.getSerializableExtra("EXTRA_CARD");
        setTheme(intent.getIntExtra("EXTRA_THEME", C4666R.style.ComposerLight));
        setContentView(C4666R.layout.tw__activity_composer);
        C4680e c4680e = new C4680e((ComposerView) findViewById(C4666R.id.tw__composer_view), c1514q, card, new C4660b(this));
    }
}

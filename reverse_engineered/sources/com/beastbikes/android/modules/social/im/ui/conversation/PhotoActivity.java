package com.beastbikes.android.modules.social.im.ui.conversation;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.framework.ui.android.utils.Toasts;
import io.rong.imkit.tools.PhotoFragment;
import org.apache.http.HttpHost;

public class PhotoActivity extends SessionFragmentActivity {
    /* renamed from: a */
    private PhotoFragment f11191a;
    /* renamed from: b */
    private Uri f11192b;
    /* renamed from: c */
    private Uri f11193c = null;

    /* renamed from: com.beastbikes.android.modules.social.im.ui.conversation.PhotoActivity$1 */
    class C23441 extends AsyncTask<Void, Void, String> {
        /* renamed from: a */
        final /* synthetic */ PhotoActivity f11190a;

        C23441(PhotoActivity photoActivity) {
            this.f11190a = photoActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11988a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11989a((String) obj);
        }

        /* renamed from: a */
        protected String m11988a(Void... voidArr) {
            return C2557f.m12831a(this.f11190a.f11193c, this.f11190a);
        }

        /* renamed from: a */
        protected void m11989a(String str) {
            Toasts.show(this.f11190a, (CharSequence) str);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.de_ac_photo);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m11991a();
        m11992b();
    }

    /* renamed from: a */
    protected void m11991a() {
        this.f11191a = (PhotoFragment) getSupportFragmentManager().getFragments().get(0);
    }

    /* renamed from: b */
    protected void m11992b() {
        this.f11192b = (Uri) getIntent().getParcelableExtra("photo");
        this.f11193c = (Uri) getIntent().getParcelableExtra("thumbnail");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.icon) {
            if (this.f11193c == null) {
                this.f11193c = this.f11192b;
            }
            if (this.f11193c == null) {
                return false;
            }
            getAsyncTaskQueue().m13740a(new C23441(this), new Void[0]);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f11192b == null || !this.f11192b.getScheme().startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(C1373R.menu.de_fix_username, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

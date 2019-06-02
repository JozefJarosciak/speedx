package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2049a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903088)
public class ClubActivitiesListActivity extends SessionFragmentActivity {
    /* renamed from: a */
    private C2049a f4853a;
    /* renamed from: b */
    private String f4854b;
    /* renamed from: c */
    private FragmentManager f4855c;
    /* renamed from: d */
    private boolean f4856d = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        this.f4853a = new C2049a(this);
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(getString(C1373R.string.club_activity_list_title));
        }
        m6235a();
    }

    protected void onResume() {
        super.onResume();
        m6237b();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.f4856d && menuItem.getItemId() == C1373R.id.menu_item_add) {
            Intent intent = new Intent(this, ClubActivityReleaseActivity.class);
            intent.putExtra("isclub", this.f4856d);
            startActivityForResult(intent, 14);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i2) {
            case -1:
                switch (i) {
                    case 14:
                        m6237b();
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6235a() {
        this.f4854b = getIntent().getStringExtra("club_id");
        this.f4856d = getIntent().getBooleanExtra("isclub", false);
        this.f4855c = getSupportFragmentManager();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f4856d) {
            getMenuInflater().inflate(C1373R.menu.add_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    /* renamed from: b */
    private void m6237b() {
        getAsyncTaskQueue().a(new ClubActivitiesListActivity$1(this), new Void[0]);
    }
}

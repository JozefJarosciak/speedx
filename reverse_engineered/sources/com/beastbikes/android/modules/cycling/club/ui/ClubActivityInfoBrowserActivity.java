package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.locale.C1849a;

public class ClubActivityInfoBrowserActivity extends BrowserActivity implements C1371a {
    /* renamed from: k */
    private int f9451k;
    /* renamed from: l */
    private int f9452l;
    /* renamed from: m */
    private String f9453m;

    /* renamed from: a */
    public static String m10682a(String str, Context context) {
        StringBuilder stringBuilder = new StringBuilder(C1768c.f8077c);
        stringBuilder.append("/club-activity-share-detail.html?activityId=");
        stringBuilder.append(str);
        stringBuilder.append("&areaCode=" + C1849a.m9639a(context));
        return stringBuilder.toString();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        Intent intent = getIntent();
        if (intent != null) {
            this.f9451k = intent.getIntExtra("activity_type", -1);
            this.f9452l = intent.getIntExtra("club_level", 0);
            this.f9453m = intent.getStringExtra("activity_id");
            this.c = true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        switch (this.f9451k) {
            case 0:
                if (this.f9452l == 128) {
                    getMenuInflater().inflate(C1373R.menu.add_menu, menu);
                    break;
                }
                return false;
            case 1:
                if (!TextUtils.isEmpty(this.f9453m)) {
                    this.c = true;
                    getMenuInflater().inflate(C1373R.menu.share_menu, menu);
                    break;
                }
                return false;
            default:
                return false;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_item_share:
                if (TextUtils.isEmpty(this.f9453m)) {
                    return false;
                }
                WebView browser = getBrowser();
                if (browser != null) {
                    browser.loadUrl("javascript:closeEnrollForm()");
                }
                if (!TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.e)) {
                    a();
                } else if (browser != null) {
                    browser.loadUrl("javascript:getShareInfo('android')");
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void finish() {
        setResult(-1);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }
}

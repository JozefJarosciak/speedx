package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2150c;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2150c.C2149a;
import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditor;
import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditorRelativeLayout;
import com.beastbikes.android.widget.multiimageselector.MultiImageSelectorActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import java.util.ArrayList;
import java.util.Iterator;

@C1459b(a = 2130903107)
@C1460c(a = 2131820566)
public class ClubActInfoEditorActivity extends SessionFragmentActivity implements C2149a {
    /* renamed from: a */
    public static String f4810a = "CONTENT";
    /* renamed from: b */
    public static String f4811b = DirectionsCriteria.INSTRUCTIONS_HTML;
    @C1458a(a = 2131755578)
    /* renamed from: c */
    private RichEditor f4812c;
    @C1458a(a = 2131755577)
    /* renamed from: d */
    private RichEditorRelativeLayout f4813d;
    @C1458a(a = 2131755580)
    /* renamed from: e */
    private LinearLayout f4814e;
    @C1458a(a = 2131755579)
    /* renamed from: f */
    private TextView f4815f;
    /* renamed from: g */
    private boolean f4816g = false;
    /* renamed from: h */
    private boolean f4817h = false;
    /* renamed from: i */
    private boolean f4818i = false;
    /* renamed from: j */
    private int f4819j = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: k */
    private View f4820k;
    /* renamed from: l */
    private C2150c f4821l;
    /* renamed from: m */
    private int f4822m;
    /* renamed from: n */
    private boolean f4823n = true;
    /* renamed from: o */
    private String f4824o = "";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (!intent.hasExtra(f4810a) || intent.getStringExtra(f4810a).equals("null")) {
            this.f4812c.setPlaceholder(getString(C1373R.string.club_act_info_hint));
        } else {
            this.f4812c.setHtml(intent.getStringExtra(f4810a));
        }
        this.f4812c.setEditorHeight(232);
        this.f4812c.setEditorFontSize(18);
        this.f4812c.setPadding(10, 10, 10, 40);
        this.f4820k = LayoutInflater.from(this).inflate(C1373R.layout.activity_create_club_act_info_menu, null);
        this.f4814e.addView(this.f4820k);
        this.f4821l = new C2150c(this, this);
        this.f4813d.setOnResizeListener(new ClubActInfoEditorActivity$1(this));
        this.f4812c.setOnTextChangeListener(new ClubActInfoEditorActivity$4(this));
        this.f4820k.findViewById(C1373R.id.activity_create_club_act_info_commit).setOnClickListener(new ClubActInfoEditorActivity$5(this));
        this.f4820k.findViewById(C1373R.id.action_textcolor).setOnClickListener(new ClubActInfoEditorActivity$6(this));
        this.f4820k.findViewById(C1373R.id.action_bold).setOnClickListener(new ClubActInfoEditorActivity$7(this));
        this.f4820k.findViewById(C1373R.id.action_underline).setOnClickListener(new ClubActInfoEditorActivity$8(this));
        this.f4820k.findViewById(C1373R.id.action_italic).setOnClickListener(new ClubActInfoEditorActivity$9(this));
        this.f4820k.findViewById(C1373R.id.action_align_left).setOnClickListener(new ClubActInfoEditorActivity$10(this));
        this.f4820k.findViewById(C1373R.id.action_align_center).setOnClickListener(new ClubActInfoEditorActivity$11(this));
        this.f4820k.findViewById(C1373R.id.action_align_right).setOnClickListener(new ClubActInfoEditorActivity$2(this));
        this.f4820k.findViewById(C1373R.id.action_insert_image).setOnClickListener(new ClubActInfoEditorActivity$3(this));
    }

    /* renamed from: b */
    private void m6193b() {
        if (this.f4822m < 3) {
            Intent intent = new Intent(this, MultiImageSelectorActivity.class);
            intent.putExtra("gallery_full", true);
            intent.putExtra("is_max", false);
            intent.putExtra("max_select_count", 3 - this.f4822m);
            intent.putStringArrayListExtra("default_list", new ArrayList());
            startActivityForResult(intent, 1107);
            return;
        }
        Toasts.showOnUiThread(this, getString(C1373R.string.club_act_info_images));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i2) {
            case -1:
                switch (i) {
                    case 1107:
                        if (this.f4812c != null) {
                            ArrayList stringArrayListExtra = intent.getStringArrayListExtra("select_result");
                            if (!(stringArrayListExtra == null || stringArrayListExtra.isEmpty())) {
                                Iterator it = stringArrayListExtra.iterator();
                                while (it.hasNext()) {
                                    String str = (String) it.next();
                                    this.f4812c.a(str, str.substring(str.lastIndexOf("/") + 1));
                                }
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    public void m6206a() {
        View peekDecorView = getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            Intent intent = new Intent();
            intent.putExtra(f4811b, this.f4812c.getHtml());
            setResult(-1, intent);
            m6206a();
            finish();
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            Intent intent = new Intent();
            intent.putExtra(f4811b, this.f4812c.getHtml());
            setResult(-1, intent);
            m6206a();
            finish();
            return true;
        } else if (menuItem.getItemId() != C1373R.id.create_club_activity_info_clear_item) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            this.f4812c.h();
            this.f4822m = 0;
            return true;
        }
    }

    /* renamed from: a */
    public void m6207a(int i) {
        this.f4812c.setTextColor(i);
        ImageButton imageButton = (ImageButton) this.f4820k.findViewById(C1373R.id.action_textcolor);
        if (i == getResources().getColor(C1373R.color.view_color_circle_red)) {
            imageButton.setImageResource(C1373R.drawable.action_textcolor_red);
        } else if (i == getResources().getColor(C1373R.color.view_color_circle_black)) {
            imageButton.setImageResource(C1373R.drawable.rich_textcolor);
        } else if (i == getResources().getColor(C1373R.color.view_color_circle_green)) {
            imageButton.setImageResource(C1373R.drawable.action_textcolor_green);
        } else if (i == getResources().getColor(C1373R.color.view_color_circle_orange)) {
            imageButton.setImageResource(C1373R.drawable.action_textcolor_orange);
        } else if (i == getResources().getColor(C1373R.color.view_color_circle_violet)) {
            imageButton.setImageResource(C1373R.drawable.action_textcolor_violet);
        } else if (i == getResources().getColor(C1373R.color.view_color_circle_blue)) {
            imageButton.setImageResource(C1373R.drawable.action_textcolor_blue);
        }
    }
}

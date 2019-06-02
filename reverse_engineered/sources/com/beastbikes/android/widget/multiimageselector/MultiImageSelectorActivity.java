package com.beastbikes.android.widget.multiimageselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.multiimageselector.MultiImageSelectorFragment.C2704a;
import java.io.File;
import java.util.ArrayList;

public class MultiImageSelectorActivity extends FragmentActivity implements C2704a {
    /* renamed from: a */
    private ArrayList<String> f12632a = new ArrayList();
    /* renamed from: b */
    private Button f12633b;
    /* renamed from: c */
    private int f12634c;
    /* renamed from: d */
    private boolean f12635d = false;

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorActivity$1 */
    class C27021 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorActivity f12630a;

        C27021(MultiImageSelectorActivity multiImageSelectorActivity) {
            this.f12630a = multiImageSelectorActivity;
        }

        public void onClick(View view) {
            this.f12630a.setResult(0);
            this.f12630a.finish();
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.MultiImageSelectorActivity$2 */
    class C27032 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ MultiImageSelectorActivity f12631a;

        C27032(MultiImageSelectorActivity multiImageSelectorActivity) {
            this.f12631a = multiImageSelectorActivity;
        }

        public void onClick(View view) {
            if (this.f12631a.f12632a != null && this.f12631a.f12632a.size() > 0) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("select_result", this.f12631a.f12632a);
                this.f12631a.setResult(-1, intent);
                this.f12631a.finish();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.multi_image_selector_activity_default);
        Intent intent = getIntent();
        this.f12635d = intent.getBooleanExtra("gallery_full", false);
        this.f12634c = intent.getIntExtra("max_select_count", 9);
        int intExtra = intent.getIntExtra("select_count_mode", 1);
        boolean booleanExtra = intent.getBooleanExtra("show_camera", true);
        if (intExtra == 1 && intent.hasExtra("default_list")) {
            this.f12632a = intent.getStringArrayListExtra("default_list");
        }
        Bundle bundle2 = new Bundle();
        if (intent.hasExtra("is_max")) {
            bundle2.putBoolean("is_max", intent.getBooleanExtra("is_max", true));
        }
        bundle2.putInt("max_select_count", this.f12634c);
        bundle2.putInt("select_count_mode", intExtra);
        bundle2.putBoolean("show_camera", booleanExtra);
        bundle2.putStringArrayList("default_result", this.f12632a);
        bundle2.putBoolean("gallery_full", this.f12635d);
        getSupportFragmentManager().beginTransaction().add(C1373R.id.image_grid, Fragment.instantiate(this, MultiImageSelectorFragment.class.getName(), bundle2)).commit();
        findViewById(C1373R.id.btn_back).setOnClickListener(new C27021(this));
        this.f12633b = (Button) findViewById(C1373R.id.commit);
        if (this.f12632a == null || this.f12632a.size() <= 0) {
            this.f12633b.setText(C1373R.string.ok);
            this.f12633b.setEnabled(false);
        } else {
            this.f12633b.setText(getString(C1373R.string.ok) + "(" + this.f12632a.size() + "/" + this.f12634c + ")");
            this.f12633b.setEnabled(true);
        }
        this.f12633b.setOnClickListener(new C27032(this));
    }

    /* renamed from: a */
    public void mo3544a(String str) {
        Intent intent = new Intent();
        this.f12632a.add(str);
        intent.putStringArrayListExtra("select_result", this.f12632a);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: b */
    public void mo3545b(String str) {
        if (!this.f12632a.contains(str)) {
            this.f12632a.add(str);
        }
        if (this.f12632a.size() > 0) {
            this.f12633b.setText(getString(C1373R.string.ok) + "(" + this.f12632a.size() + "/" + this.f12634c + ")");
            if (!this.f12633b.isEnabled()) {
                this.f12633b.setEnabled(true);
            }
        }
    }

    /* renamed from: c */
    public void mo3546c(String str) {
        if (this.f12632a.contains(str)) {
            this.f12632a.remove(str);
            this.f12633b.setText(getString(C1373R.string.ok) + "(" + this.f12632a.size() + "/" + this.f12634c + ")");
        } else {
            this.f12633b.setText(getString(C1373R.string.ok) + "(" + this.f12632a.size() + "/" + this.f12634c + ")");
        }
        if (this.f12632a.size() == 0) {
            this.f12633b.setText(C1373R.string.ok);
            this.f12633b.setEnabled(false);
        }
    }

    /* renamed from: a */
    public void mo3543a(File file) {
        if (file != null) {
            Intent intent = new Intent();
            this.f12632a.add(file.getAbsolutePath());
            intent.putStringArrayListExtra("select_result", this.f12632a);
            setResult(-1, intent);
            finish();
        }
    }
}

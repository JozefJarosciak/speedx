package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;

@C1457a(a = "俱乐部相册上传")
@C1459b(a = 2130903100)
public class ClubFeedPicUploadActivity extends ClubFeedPostActivity implements OnClickListener {
    /* renamed from: k */
    private ArrayList<String> f5080k;
    /* renamed from: l */
    private int f5081l;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.b.setVisibility(8);
        this.g.setVisibility(8);
        this.f5081l = getIntent().getIntExtra("count", 0);
        this.a = this.f5081l;
        this.f.addTextChangedListener(new ClubFeedPicUploadActivity$1(this));
    }

    /* renamed from: a */
    protected int mo2771a() {
        return 1;
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i2) {
            case -1:
                switch (i) {
                    case 1009:
                        if (this.j != null) {
                            this.f5080k = intent.getStringArrayListExtra("select_result");
                            if (this.f5080k == null || this.f5080k.isEmpty()) {
                                this.i.setEnabled(false);
                            } else {
                                this.i.setEnabled(true);
                            }
                            this.j.a(i, i2, intent);
                            break;
                        }
                        break;
                    default:
                        break;
                }
        }
        super.onActivityResult(i, i2, intent);
    }
}

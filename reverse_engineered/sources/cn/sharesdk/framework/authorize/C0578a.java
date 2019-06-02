package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.content.Intent;
import com.mob.tools.FakeActivity;

/* compiled from: AbstractAuthorizeActivity */
/* renamed from: cn.sharesdk.framework.authorize.a */
public class C0578a extends FakeActivity {
    /* renamed from: a */
    protected AuthorizeHelper f1246a;

    /* renamed from: a */
    public void m2023a(AuthorizeHelper authorizeHelper) {
        this.f1246a = authorizeHelper;
        super.show(authorizeHelper.getPlatform().getContext(), null);
    }

    public void show(Context context, Intent intent) {
        throw new RuntimeException("This method is deprecated, use show(AuthorizeHelper, Intent) instead");
    }

    /* renamed from: a */
    public AuthorizeHelper m2022a() {
        return this.f1246a;
    }
}

package p203u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: UMCCDBUtils */
/* renamed from: u.aly.bj */
public class bj {
    /* renamed from: a */
    public static String m21698a(Context context) {
        return "/data/data/" + context.getPackageName() + "/databases/cc/";
    }

    /* renamed from: a */
    public static String m21699a(List<String> list) {
        return TextUtils.join("!", list);
    }

    /* renamed from: a */
    public static List<String> m21700a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }
}

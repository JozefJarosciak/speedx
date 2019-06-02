package p203u.aly;

import android.content.Context;
import android.os.Environment;
import com.alipay.sdk.cons.C0845b;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: UTDIdTracker */
/* renamed from: u.aly.c */
public class C5931c extends bu {
    /* renamed from: a */
    private static final Pattern f18959a = Pattern.compile("UTDID\">([^<]+)");
    /* renamed from: b */
    private Context f18960b;

    public C5931c(Context context) {
        super(C0845b.f2066g);
        this.f18960b = context;
    }

    /* renamed from: a */
    public String mo7207a() {
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke(null, new Object[]{this.f18960b});
        } catch (Exception e) {
            return m21812g();
        }
    }

    /* renamed from: g */
    private String m21812g() {
        InputStream fileInputStream;
        File h = m21813h();
        if (h == null || !h.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(h);
            String b = m21811b(ag.m21145a(fileInputStream));
            ag.m21152c(fileInputStream);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            ag.m21152c(fileInputStream);
        }
    }

    /* renamed from: b */
    private String m21811b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = f18959a.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /* renamed from: h */
    private File m21813h() {
        if (!af.m21115a(this.f18960b, "android.permission.WRITE_EXTERNAL_STORAGE") || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
        } catch (Exception e) {
            return null;
        }
    }
}

package com.twitter.sdk.android.core.internal;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: MigrationHelper */
/* renamed from: com.twitter.sdk.android.core.internal.b */
public class C4603b {

    /* compiled from: MigrationHelper */
    /* renamed from: com.twitter.sdk.android.core.internal.b$a */
    static class C4601a implements Comparator<File> {
        C4601a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18226a((File) obj, (File) obj2);
        }

        /* renamed from: a */
        public int m18226a(File file, File file2) {
            return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file.lastModified()));
        }
    }

    /* compiled from: MigrationHelper */
    /* renamed from: com.twitter.sdk.android.core.internal.b$b */
    static class C4602b implements FilenameFilter {
        /* renamed from: a */
        final String f16250a;

        public C4602b(String str) {
            this.f16250a = str;
        }

        public boolean accept(File file, String str) {
            if (str.startsWith(this.f16250a)) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: a */
    public void m18229a(Context context, String str, String str2) {
        File a = m18227a(context);
        if (a.exists() && a.isDirectory()) {
            File file = new File(a, str2);
            if (!file.exists()) {
                a = m18228a(a, str);
                if (a != null) {
                    a.renameTo(file);
                }
            }
        }
    }

    /* renamed from: a */
    File m18227a(Context context) {
        return new File(context.getApplicationInfo().dataDir, "shared_prefs");
    }

    /* renamed from: a */
    File m18228a(File file, String str) {
        File[] listFiles = file.listFiles(new C4602b(str));
        Arrays.sort(listFiles, new C4601a());
        return listFiles.length > 0 ? listFiles[0] : null;
    }
}

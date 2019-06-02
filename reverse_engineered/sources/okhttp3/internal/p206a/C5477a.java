package okhttp3.internal.p206a;

import java.io.File;
import java.io.IOException;

/* compiled from: FileSystem */
/* renamed from: okhttp3.internal.a.a */
public interface C5477a {
    /* renamed from: a */
    public static final C5477a f17635a = new C54781();

    /* compiled from: FileSystem */
    /* renamed from: okhttp3.internal.a.a$1 */
    static class C54781 implements C5477a {
        C54781() {
        }

        /* renamed from: a */
        public void mo6690a(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        /* renamed from: b */
        public boolean mo6692b(File file) {
            return file.exists();
        }

        /* renamed from: c */
        public long mo6693c(File file) {
            return file.length();
        }

        /* renamed from: a */
        public void mo6691a(File file, File file2) throws IOException {
            mo6690a(file2);
            if (!file.renameTo(file2)) {
                throw new IOException("failed to rename " + file + " to " + file2);
            }
        }
    }

    /* renamed from: a */
    void mo6690a(File file) throws IOException;

    /* renamed from: a */
    void mo6691a(File file, File file2) throws IOException;

    /* renamed from: b */
    boolean mo6692b(File file);

    /* renamed from: c */
    long mo6693c(File file);
}

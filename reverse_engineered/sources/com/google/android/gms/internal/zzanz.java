package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;

public final class zzanz {

    private static final class zza extends Writer {
        private final Appendable bfx;
        private final zza bfy;

        static class zza implements CharSequence {
            char[] bfz;

            zza() {
            }

            public char charAt(int i) {
                return this.bfz[i];
            }

            public int length() {
                return this.bfz.length;
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.bfz, i, i2 - i);
            }
        }

        private zza(Appendable appendable) {
            this.bfy = new zza();
            this.bfx = appendable;
        }

        public void close() {
        }

        public void flush() {
        }

        public void write(int i) throws IOException {
            this.bfx.append((char) i);
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.bfy.bfz = cArr;
            this.bfx.append(this.bfy, i, i + i2);
        }
    }

    public static Writer zza(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new zza(appendable);
    }

    public static void zzb(zzamy zzamy, zzaor zzaor) throws IOException {
        zzaon.bgW.zza(zzaor, zzamy);
    }

    public static zzamy zzh(zzaop zzaop) throws zzanc {
        Object obj = 1;
        try {
            zzaop.mo4189h();
            obj = null;
            return (zzamy) zzaon.bgW.zzb(zzaop);
        } catch (Throwable e) {
            if (obj != null) {
                return zzana.bes;
            }
            throw new zzanh(e);
        } catch (Throwable e2) {
            throw new zzanh(e2);
        } catch (Throwable e22) {
            throw new zzamz(e22);
        } catch (Throwable e222) {
            throw new zzanh(e222);
        }
    }
}

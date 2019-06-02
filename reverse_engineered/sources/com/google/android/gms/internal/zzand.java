package com.google.android.gms.internal;

import java.io.Reader;
import java.io.StringReader;

public final class zzand {
    public zzamy zza(Reader reader) throws zzamz, zzanh {
        try {
            zzaop zzaop = new zzaop(reader);
            zzamy zzh = zzh(zzaop);
            if (zzh.zzczp() || zzaop.mo4189h() == zzaoq.END_DOCUMENT) {
                return zzh;
            }
            throw new zzanh("Did not consume the entire document.");
        } catch (Throwable e) {
            throw new zzanh(e);
        } catch (Throwable e2) {
            throw new zzamz(e2);
        } catch (Throwable e22) {
            throw new zzanh(e22);
        }
    }

    public zzamy zzh(zzaop zzaop) throws zzamz, zzanh {
        String valueOf;
        boolean isLenient = zzaop.isLenient();
        zzaop.setLenient(true);
        try {
            zzamy zzh = zzanz.zzh(zzaop);
            zzaop.setLenient(isLenient);
            return zzh;
        } catch (Throwable e) {
            valueOf = String.valueOf(zzaop);
            throw new zzanc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed parsing JSON source: ").append(valueOf).append(" to Json").toString(), e);
        } catch (Throwable e2) {
            valueOf = String.valueOf(zzaop);
            throw new zzanc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed parsing JSON source: ").append(valueOf).append(" to Json").toString(), e2);
        } catch (Throwable th) {
            zzaop.setLenient(isLenient);
        }
    }

    public zzamy zzsy(String str) throws zzanh {
        return zza(new StringReader(str));
    }
}

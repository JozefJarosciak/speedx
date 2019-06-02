package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzf<T> extends AbstractDataBuffer<T> {
    private boolean wc = false;
    private ArrayList<Integer> wd;

    protected zzf(DataHolder dataHolder) {
        super(dataHolder);
    }

    private void zzarh() {
        synchronized (this) {
            if (!this.wc) {
                int count = this.tk.getCount();
                this.wd = new ArrayList();
                if (count > 0) {
                    this.wd.add(Integer.valueOf(0));
                    String zzarg = zzarg();
                    String zzd = this.tk.zzd(zzarg, 0, this.tk.zzfo(0));
                    int i = 1;
                    while (i < count) {
                        int zzfo = this.tk.zzfo(i);
                        String zzd2 = this.tk.zzd(zzarg, i, zzfo);
                        if (zzd2 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(zzarg).length() + 78).append("Missing value for markerColumn: ").append(zzarg).append(", at row: ").append(i).append(", for window: ").append(zzfo).toString());
                        }
                        if (zzd2.equals(zzd)) {
                            zzd2 = zzd;
                        } else {
                            this.wd.add(Integer.valueOf(i));
                        }
                        i++;
                        zzd = zzd2;
                    }
                }
                this.wc = true;
            }
        }
    }

    public final T get(int i) {
        zzarh();
        return zzl(zzfs(i), zzft(i));
    }

    public int getCount() {
        zzarh();
        return this.wd.size();
    }

    protected abstract String zzarg();

    protected String zzari() {
        return null;
    }

    int zzfs(int i) {
        if (i >= 0 && i < this.wd.size()) {
            return ((Integer) this.wd.get(i)).intValue();
        }
        throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
    }

    protected int zzft(int i) {
        if (i < 0 || i == this.wd.size()) {
            return 0;
        }
        int count = i == this.wd.size() + -1 ? this.tk.getCount() - ((Integer) this.wd.get(i)).intValue() : ((Integer) this.wd.get(i + 1)).intValue() - ((Integer) this.wd.get(i)).intValue();
        if (count != 1) {
            return count;
        }
        int zzfs = zzfs(i);
        int zzfo = this.tk.zzfo(zzfs);
        String zzari = zzari();
        return (zzari == null || this.tk.zzd(zzari, zzfs, zzfo) != null) ? count : 0;
    }

    protected abstract T zzl(int i, int i2);
}

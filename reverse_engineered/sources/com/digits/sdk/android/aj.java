package com.digits.sdk.android;

import android.content.res.Resources;
import android.util.SparseIntArray;
import org.apache.http.HttpStatus;

/* compiled from: DigitsErrorCodes */
class aj implements az {
    /* renamed from: a */
    protected final SparseIntArray f13185a = new SparseIntArray(10);
    /* renamed from: b */
    private final Resources f13186b;

    aj(Resources resources) {
        this.f13185a.put(88, C2876R.string.dgts__confirmation_error_alternative);
        this.f13185a.put(284, C2876R.string.dgts__network_error);
        this.f13185a.put(HttpStatus.SC_MOVED_TEMPORARILY, C2876R.string.dgts__network_error);
        this.f13185a.put(240, C2876R.string.dgts__network_error);
        this.f13185a.put(87, C2876R.string.dgts__network_error);
        this.f13186b = resources;
    }

    /* renamed from: a */
    public String mo3646a(int i) {
        int indexOfKey = this.f13185a.indexOfKey(i);
        return indexOfKey < 0 ? mo3645a() : this.f13186b.getString(this.f13185a.valueAt(indexOfKey));
    }

    /* renamed from: a */
    public String mo3645a() {
        return this.f13186b.getString(C2876R.string.dgts__try_again);
    }

    /* renamed from: b */
    public String mo3647b() {
        return this.f13186b.getString(C2876R.string.dgts__network_error);
    }
}

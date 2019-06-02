package com.beastbikes.framework.android.preference;

import android.preference.EditTextPreference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongEditTextPreference extends EditTextPreference {
    /* renamed from: a */
    private static final Logger f13024a = LoggerFactory.getLogger(LongEditTextPreference.class);

    protected String getPersistedString(String str) {
        long j = 0;
        try {
            j = Long.decode(str).longValue();
        } catch (NumberFormatException e) {
        } catch (NullPointerException e2) {
        }
        return String.valueOf(getPersistedLong(j));
    }

    protected boolean persistString(String str) {
        try {
            return persistLong(Long.decode(str).longValue());
        } catch (Throwable e) {
            f13024a.error("Invalid format", e);
            return false;
        }
    }
}

package com.beastbikes.framework.android.preference;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerEditTextPreference extends EditTextPreference {
    /* renamed from: a */
    private static final Logger f13023a = LoggerFactory.getLogger(IntegerEditTextPreference.class);

    public IntegerEditTextPreference(Context context) {
        this(context, null);
    }

    public IntegerEditTextPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IntegerEditTextPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected String getPersistedString(String str) {
        int i = 0;
        try {
            i = Long.decode(str).intValue();
        } catch (NumberFormatException e) {
        } catch (NullPointerException e2) {
        }
        return String.valueOf(getPersistedInt(i));
    }

    protected boolean persistString(String str) {
        try {
            return persistInt(Long.decode(str).intValue());
        } catch (Throwable e) {
            f13023a.error("Invalid format", e);
            return false;
        }
    }
}

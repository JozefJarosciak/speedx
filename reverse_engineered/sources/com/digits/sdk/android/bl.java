package com.digits.sdk.android;

import android.content.res.Resources;
import org.apache.http.HttpStatus;

/* compiled from: PhoneNumberErrorCodes */
class bl extends aj {
    bl(Resources resources) {
        super(resources);
        this.a.put(44, C2876R.string.dgts__try_again_phone_number);
        this.a.put(300, C2876R.string.dgts__try_again_phone_number);
        this.a.put(HttpStatus.SC_SEE_OTHER, C2876R.string.dgts__try_again_phone_number);
        this.a.put(285, C2876R.string.dgts__confirmation_error_alternative);
        this.a.put(286, C2876R.string.dgts__unsupported_operator_error);
    }
}

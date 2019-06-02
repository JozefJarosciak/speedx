package com.digits.sdk.android;

import com.twitter.sdk.android.core.internal.scribe.c$a;

class DigitsScribeConstants {
    /* renamed from: a */
    static final c$a f13103a = new c$a().a("tfw").b("android").c("digits");

    enum Element {
        COUNTRY_CODE("country_code"),
        SUBMIT("submit"),
        RETRY("retry"),
        CALL("call"),
        CANCEL("cancel"),
        RESEND("resend"),
        DISMISS("dismiss");
        
        private final String element;

        private Element(String str) {
            this.element = str;
        }

        public String getElement() {
            return this.element;
        }
    }
}

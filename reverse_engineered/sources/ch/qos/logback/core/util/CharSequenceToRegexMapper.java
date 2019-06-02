package ch.qos.logback.core.util;

import com.alipay.sdk.util.C0880h;
import com.avos.avoscloud.AVException;

class CharSequenceToRegexMapper {
    CharSequenceToRegexMapper() {
    }

    private String number(int i) {
        return "\\d{" + i + C0880h.f2222d;
    }

    String toRegex(CharSequenceState charSequenceState) {
        int i = charSequenceState.occurrences;
        char c = charSequenceState.f14c;
        switch (charSequenceState.f14c) {
            case '\'':
                if (i == 1) {
                    return "";
                }
                throw new IllegalStateException("Too many single quotes");
            case '.':
                return "\\.";
            case 'D':
            case 'F':
            case 'H':
            case 'K':
            case 'S':
            case 'W':
            case 'd':
            case 'h':
            case 'k':
            case 'm':
            case AVException.PUSH_MISCONFIGURED /*115*/:
            case AVException.OPERATION_FORBIDDEN /*119*/:
            case AVException.INVALID_NESTED_KEY /*121*/:
                return number(i);
            case 'E':
                return ".{2,12}";
            case 'G':
            case AVException.INVALID_FILE_NAME /*122*/:
                return ".*";
            case 'M':
                return i >= 3 ? ".{3,12}" : number(i);
            case 'Z':
                return "(\\+|-)\\d{4}";
            case '\\':
                throw new IllegalStateException("Forward slashes are not allowed");
            case 'a':
                return ".{2}";
            default:
                return i == 1 ? "" + c : c + "{" + i + C0880h.f2222d;
        }
    }
}

package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.models.User;

public final class UserUtils {

    public enum AvatarSize {
        NORMAL("_normal"),
        BIGGER("_bigger"),
        MINI("_mini"),
        ORIGINAL("_original"),
        REASONABLY_SMALL("_reasonably_small");
        
        private final String suffix;

        private AvatarSize(String str) {
            this.suffix = str;
        }

        /* renamed from: a */
        String m18216a() {
            return this.suffix;
        }
    }

    /* renamed from: a */
    public static String m18217a(User user, AvatarSize avatarSize) {
        if (user == null || user.profileImageUrlHttps == null) {
            return null;
        }
        String str = user.profileImageUrlHttps;
        if (avatarSize == null || str == null) {
            return str;
        }
        switch (avatarSize) {
            case NORMAL:
            case BIGGER:
            case MINI:
            case ORIGINAL:
            case REASONABLY_SMALL:
                return str.replace(AvatarSize.NORMAL.m18216a(), avatarSize.m18216a());
            default:
                return str;
        }
    }
}

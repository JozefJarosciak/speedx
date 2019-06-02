package com.google.android.gms.common.internal;

import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class zzf {
    public static final zzf xC = zza((CharSequence) "\t\n\u000b\f\r     　 ᠎ ").zza(zza(' ', ' '));
    public static final zzf xD = zza((CharSequence) "\t\n\u000b\f\r     　").zza(zza(' ', ' ')).zza(zza(' ', ' '));
    public static final zzf xE = zza('\u0000', Ascii.MAX);
    public static final zzf xF;
    public static final zzf xG = zza('\t', '\r').zza(zza('\u001c', ' ')).zza(zzc(' ')).zza(zzc('᠎')).zza(zza(' ', ' ')).zza(zza(' ', '​')).zza(zza(' ', ' ')).zza(zzc(' ')).zza(zzc('　'));
    public static final zzf xH = new C32961();
    public static final zzf xI = new C33005();
    public static final zzf xJ = new C33016();
    public static final zzf xK = new C33027();
    public static final zzf xL = new C33038();
    public static final zzf xM = zza('\u0000', '\u001f').zza(zza(Ascii.MAX, ''));
    public static final zzf xN = zza('\u0000', ' ').zza(zza(Ascii.MAX, ' ')).zza(zzc('­')).zza(zza('؀', '؃')).zza(zza((CharSequence) "۝܏ ឴឵᠎")).zza(zza(' ', '‏')).zza(zza(' ', ' ')).zza(zza(' ', '⁤')).zza(zza('⁪', '⁯')).zza(zzc('　')).zza(zza('?', '')).zza(zza((CharSequence) "﻿￹￺￻"));
    public static final zzf xO = zza('\u0000', 'ӹ').zza(zzc('־')).zza(zza('א', 'ת')).zza(zzc('׳')).zza(zzc('״')).zza(zza('؀', 'ۿ')).zza(zza('ݐ', 'ݿ')).zza(zza('฀', '๿')).zza(zza('Ḁ', '₯')).zza(zza('℀', '℺')).zza(zza('ﭐ', '﷿')).zza(zza('ﹰ', '﻿')).zza(zza('｡', 'ￜ'));
    public static final zzf xP = new C33049();
    public static final zzf xQ = new zzf() {
        public zzf zza(zzf zzf) {
            return (zzf) zzab.zzaa(zzf);
        }

        public boolean zzb(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        public boolean zzd(char c) {
            return false;
        }
    };

    /* renamed from: com.google.android.gms.common.internal.zzf$11 */
    class AnonymousClass11 extends zzf {
        final /* synthetic */ char xW;

        AnonymousClass11(char c) {
            this.xW = c;
        }

        public zzf zza(zzf zzf) {
            return zzf.zzd(this.xW) ? zzf : super.zza(zzf);
        }

        public boolean zzd(char c) {
            return c == this.xW;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$1 */
    class C32961 extends zzf {
        C32961() {
        }

        public boolean zzd(char c) {
            return Character.isDigit(c);
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$2 */
    class C32972 extends zzf {
        final /* synthetic */ char xR;
        final /* synthetic */ char xS;

        C32972(char c, char c2) {
            this.xR = c;
            this.xS = c2;
        }

        public boolean zzd(char c) {
            return c == this.xR || c == this.xS;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$3 */
    class C32983 extends zzf {
        final /* synthetic */ char[] xT;

        C32983(char[] cArr) {
            this.xT = cArr;
        }

        public boolean zzd(char c) {
            return Arrays.binarySearch(this.xT, c) >= 0;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$4 */
    class C32994 extends zzf {
        final /* synthetic */ char xU;
        final /* synthetic */ char xV;

        C32994(char c, char c2) {
            this.xU = c;
            this.xV = c2;
        }

        public boolean zzd(char c) {
            return this.xU <= c && c <= this.xV;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$5 */
    class C33005 extends zzf {
        C33005() {
        }

        public boolean zzd(char c) {
            return Character.isLetter(c);
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$6 */
    class C33016 extends zzf {
        C33016() {
        }

        public boolean zzd(char c) {
            return Character.isLetterOrDigit(c);
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$7 */
    class C33027 extends zzf {
        C33027() {
        }

        public boolean zzd(char c) {
            return Character.isUpperCase(c);
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$8 */
    class C33038 extends zzf {
        C33038() {
        }

        public boolean zzd(char c) {
            return Character.isLowerCase(c);
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$9 */
    class C33049 extends zzf {
        C33049() {
        }

        public zzf zza(zzf zzf) {
            zzab.zzaa(zzf);
            return this;
        }

        public boolean zzb(CharSequence charSequence) {
            zzab.zzaa(charSequence);
            return true;
        }

        public boolean zzd(char c) {
            return true;
        }
    }

    private static class zza extends zzf {
        List<zzf> xX;

        zza(List<zzf> list) {
            this.xX = list;
        }

        public zzf zza(zzf zzf) {
            List arrayList = new ArrayList(this.xX);
            arrayList.add((zzf) zzab.zzaa(zzf));
            return new zza(arrayList);
        }

        public boolean zzd(char c) {
            for (zzf zzd : this.xX) {
                if (zzd.zzd(c)) {
                    return true;
                }
            }
            return false;
        }
    }

    static {
        zzf zza = zza('0', '9');
        zzf zzf = zza;
        for (char c : "٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".toCharArray()) {
            zzf = zzf.zza(zza(c, (char) (c + 9)));
        }
        xF = zzf;
    }

    public static zzf zza(char c, char c2) {
        zzab.zzbn(c2 >= c);
        return new C32994(c, c2);
    }

    public static zzf zza(CharSequence charSequence) {
        switch (charSequence.length()) {
            case 0:
                return xQ;
            case 1:
                return zzc(charSequence.charAt(0));
            case 2:
                return new C32972(charSequence.charAt(0), charSequence.charAt(1));
            default:
                char[] toCharArray = charSequence.toString().toCharArray();
                Arrays.sort(toCharArray);
                return new C32983(toCharArray);
        }
    }

    public static zzf zzc(char c) {
        return new AnonymousClass11(c);
    }

    public zzf zza(zzf zzf) {
        return new zza(Arrays.asList(new zzf[]{this, (zzf) zzab.zzaa(zzf)}));
    }

    public boolean zzb(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!zzd(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean zzd(char c);
}

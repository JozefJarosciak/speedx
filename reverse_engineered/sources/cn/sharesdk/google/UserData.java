package cn.sharesdk.google;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashSet;
import java.util.Set;

public class UserData {

    public static final class AgeRange implements Parcelable {
        /* renamed from: a */
        public static final C0630a f1419a = new C0630a();
        /* renamed from: b */
        private final Set<Integer> f1420b;
        /* renamed from: c */
        private final int f1421c;
        /* renamed from: d */
        private int f1422d;
        /* renamed from: e */
        private int f1423e;

        /* renamed from: cn.sharesdk.google.UserData$AgeRange$a */
        public static class C0630a implements Creator<AgeRange> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2314a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2315a(i);
            }

            /* renamed from: a */
            public AgeRange m2314a(Parcel parcel) {
                int i;
                int i2;
                Throwable th;
                int i3 = 0;
                Set hashSet = new HashSet();
                try {
                    int b = C0651f.m2484b(parcel);
                    i = 0;
                    i2 = 0;
                    while (parcel.dataPosition() < b) {
                        int a = C0651f.m2480a(parcel);
                        switch (C0651f.m2479a(a)) {
                            case 1:
                                try {
                                    i2 = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(1));
                                    break;
                                } catch (Throwable th2) {
                                    th = th2;
                                    break;
                                }
                            case 2:
                                i3 = C0651f.m2488d(parcel, a);
                                hashSet.add(Integer.valueOf(2));
                                break;
                            case 3:
                                i = C0651f.m2488d(parcel, a);
                                hashSet.add(Integer.valueOf(3));
                                break;
                            default:
                                C0651f.m2486b(parcel, a);
                                break;
                        }
                    }
                    if (parcel.dataPosition() != b) {
                        throw new Throwable("Overread allowed size end=" + b);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    i2 = 0;
                    i = 0;
                    th.printStackTrace();
                    return new AgeRange(hashSet, i2, i3, i);
                }
                return new AgeRange(hashSet, i2, i3, i);
            }

            /* renamed from: a */
            public AgeRange[] m2315a(int i) {
                return new AgeRange[i];
            }

            /* renamed from: a */
            static void m2313a(AgeRange ageRange, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = ageRange.m2316a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, ageRange.m2317b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2472a(parcel, 2, ageRange.m2318c());
                }
                if (a2.contains(Integer.valueOf(3))) {
                    C0650e.m2472a(parcel, 3, ageRange.m2319d());
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public AgeRange() {
            this.f1421c = 1;
            this.f1420b = new HashSet();
        }

        AgeRange(Set<Integer> set, int i, int i2, int i3) {
            this.f1420b = set;
            this.f1421c = i;
            this.f1422d = i2;
            this.f1423e = i3;
        }

        /* renamed from: a */
        Set<Integer> m2316a() {
            return this.f1420b;
        }

        /* renamed from: b */
        int m2317b() {
            return this.f1421c;
        }

        /* renamed from: c */
        public int m2318c() {
            return this.f1422d;
        }

        /* renamed from: d */
        public int m2319d() {
            return this.f1423e;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0630a.m2313a(this, parcel, i);
        }
    }

    public static final class Cover implements Parcelable {
        /* renamed from: a */
        public static final C0633a f1435a = new C0633a();
        /* renamed from: b */
        private final Set<Integer> f1436b;
        /* renamed from: c */
        private final int f1437c;
        /* renamed from: d */
        private CoverInfo f1438d;
        /* renamed from: e */
        private CoverPhoto f1439e;
        /* renamed from: f */
        private int f1440f;

        public static final class CoverInfo implements Parcelable {
            /* renamed from: a */
            public static final C0631a f1424a = new C0631a();
            /* renamed from: b */
            private final Set<Integer> f1425b;
            /* renamed from: c */
            private final int f1426c;
            /* renamed from: d */
            private int f1427d;
            /* renamed from: e */
            private int f1428e;

            /* renamed from: cn.sharesdk.google.UserData$Cover$CoverInfo$a */
            public static class C0631a implements Creator<CoverInfo> {
                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return m2321a(parcel);
                }

                public /* synthetic */ Object[] newArray(int i) {
                    return m2322a(i);
                }

                /* renamed from: a */
                public CoverInfo m2321a(Parcel parcel) {
                    int i;
                    int i2;
                    Throwable th;
                    int i3 = 0;
                    Set hashSet = new HashSet();
                    try {
                        int b = C0651f.m2484b(parcel);
                        i = 0;
                        i2 = 0;
                        while (parcel.dataPosition() < b) {
                            int a = C0651f.m2480a(parcel);
                            switch (C0651f.m2479a(a)) {
                                case 1:
                                    try {
                                        i2 = C0651f.m2488d(parcel, a);
                                        hashSet.add(Integer.valueOf(1));
                                        break;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        break;
                                    }
                                case 2:
                                    i3 = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(2));
                                    break;
                                case 3:
                                    i = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(3));
                                    break;
                                default:
                                    C0651f.m2486b(parcel, a);
                                    break;
                            }
                        }
                        if (parcel.dataPosition() != b) {
                            throw new Throwable("Overread allowed size end=" + b);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        i2 = 0;
                        i = 0;
                        th.printStackTrace();
                        return new CoverInfo(hashSet, i2, i3, i);
                    }
                    return new CoverInfo(hashSet, i2, i3, i);
                }

                /* renamed from: a */
                public CoverInfo[] m2322a(int i) {
                    return new CoverInfo[i];
                }

                /* renamed from: a */
                static void m2320a(CoverInfo coverInfo, Parcel parcel, int i) {
                    int a = C0650e.m2470a(parcel);
                    Set a2 = coverInfo.m2323a();
                    if (a2.contains(Integer.valueOf(1))) {
                        C0650e.m2472a(parcel, 1, coverInfo.m2324b());
                    }
                    if (a2.contains(Integer.valueOf(2))) {
                        C0650e.m2472a(parcel, 2, coverInfo.m2325c());
                    }
                    if (a2.contains(Integer.valueOf(3))) {
                        C0650e.m2472a(parcel, 3, coverInfo.m2326d());
                    }
                    C0650e.m2471a(parcel, a);
                }
            }

            public CoverInfo() {
                this.f1426c = 1;
                this.f1425b = new HashSet();
            }

            CoverInfo(Set<Integer> set, int i, int i2, int i3) {
                this.f1425b = set;
                this.f1426c = i;
                this.f1427d = i2;
                this.f1428e = i3;
            }

            /* renamed from: a */
            Set<Integer> m2323a() {
                return this.f1425b;
            }

            /* renamed from: b */
            int m2324b() {
                return this.f1426c;
            }

            /* renamed from: c */
            public int m2325c() {
                return this.f1427d;
            }

            /* renamed from: d */
            public int m2326d() {
                return this.f1428e;
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                C0631a.m2320a(this, parcel, i);
            }
        }

        public static final class CoverPhoto implements Parcelable {
            /* renamed from: a */
            public static final C0632a f1429a = new C0632a();
            /* renamed from: b */
            private final Set<Integer> f1430b;
            /* renamed from: c */
            private final int f1431c;
            /* renamed from: d */
            private int f1432d;
            /* renamed from: e */
            private String f1433e;
            /* renamed from: f */
            private int f1434f;

            /* renamed from: cn.sharesdk.google.UserData$Cover$CoverPhoto$a */
            public static class C0632a implements Creator<CoverPhoto> {
                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return m2328a(parcel);
                }

                public /* synthetic */ Object[] newArray(int i) {
                    return m2329a(i);
                }

                /* renamed from: a */
                public CoverPhoto m2328a(Parcel parcel) {
                    int i;
                    Throwable th;
                    int i2;
                    String str;
                    int i3 = 0;
                    Set hashSet = new HashSet();
                    String str2 = null;
                    try {
                        int b = C0651f.m2484b(parcel);
                        int i4 = 0;
                        i = 0;
                        int i5 = 0;
                        while (parcel.dataPosition() < b) {
                            i3 = C0651f.m2480a(parcel);
                            switch (C0651f.m2479a(i3)) {
                                case 1:
                                    try {
                                        i5 = C0651f.m2488d(parcel, i3);
                                        hashSet.add(Integer.valueOf(1));
                                        break;
                                    } catch (Throwable th2) {
                                        Throwable th3 = th2;
                                        i3 = i4;
                                        th = th3;
                                        String str3 = str2;
                                        i2 = i5;
                                        str = str3;
                                        break;
                                    }
                                case 2:
                                    i = C0651f.m2488d(parcel, i3);
                                    hashSet.add(Integer.valueOf(2));
                                    break;
                                case 3:
                                    str2 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(3));
                                    break;
                                case 4:
                                    i4 = C0651f.m2488d(parcel, i3);
                                    hashSet.add(Integer.valueOf(4));
                                    break;
                                default:
                                    C0651f.m2486b(parcel, i3);
                                    break;
                            }
                        }
                        if (parcel.dataPosition() != b) {
                            throw new Throwable("Overread allowed size end=" + b);
                        }
                        i3 = i4;
                        String str4 = str2;
                        i2 = i5;
                        str = str4;
                    } catch (Throwable th4) {
                        th = th4;
                        str = null;
                        i = 0;
                        i2 = 0;
                        th.printStackTrace();
                        return new CoverPhoto(hashSet, i2, i, str, i3);
                    }
                    return new CoverPhoto(hashSet, i2, i, str, i3);
                }

                /* renamed from: a */
                public CoverPhoto[] m2329a(int i) {
                    return new CoverPhoto[i];
                }

                /* renamed from: a */
                static void m2327a(CoverPhoto coverPhoto, Parcel parcel, int i) {
                    int a = C0650e.m2470a(parcel);
                    Set a2 = coverPhoto.m2330a();
                    if (a2.contains(Integer.valueOf(1))) {
                        C0650e.m2472a(parcel, 1, coverPhoto.m2331b());
                    }
                    if (a2.contains(Integer.valueOf(2))) {
                        C0650e.m2472a(parcel, 2, coverPhoto.m2332c());
                    }
                    if (a2.contains(Integer.valueOf(3))) {
                        C0650e.m2474a(parcel, 3, coverPhoto.m2333d(), true);
                    }
                    if (a2.contains(Integer.valueOf(4))) {
                        C0650e.m2472a(parcel, 4, coverPhoto.m2334e());
                    }
                    C0650e.m2471a(parcel, a);
                }
            }

            public CoverPhoto() {
                this.f1431c = 1;
                this.f1430b = new HashSet();
            }

            CoverPhoto(Set<Integer> set, int i, int i2, String str, int i3) {
                this.f1430b = set;
                this.f1431c = i;
                this.f1432d = i2;
                this.f1433e = str;
                this.f1434f = i3;
            }

            /* renamed from: a */
            Set<Integer> m2330a() {
                return this.f1430b;
            }

            /* renamed from: b */
            int m2331b() {
                return this.f1431c;
            }

            /* renamed from: c */
            public int m2332c() {
                return this.f1432d;
            }

            /* renamed from: d */
            public String m2333d() {
                return this.f1433e;
            }

            /* renamed from: e */
            public int m2334e() {
                return this.f1434f;
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                C0632a.m2327a(this, parcel, i);
            }
        }

        /* renamed from: cn.sharesdk.google.UserData$Cover$a */
        public static class C0633a implements Creator<Cover> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2336a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2337a(i);
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            /* renamed from: a */
            public cn.sharesdk.google.UserData.Cover m2336a(android.os.Parcel r12) {
                /*
                r11 = this;
                r3 = 0;
                r2 = 0;
                r1 = new java.util.HashSet;
                r1.<init>();
                r6 = cn.sharesdk.google.C0651f.m2484b(r12);	 Catch:{ Throwable -> 0x00ab }
                r4 = r3;
                r5 = r2;
            L_0x000d:
                r0 = r12.dataPosition();	 Catch:{ Throwable -> 0x00b2 }
                if (r0 >= r6) goto L_0x0078;
            L_0x0013:
                r0 = cn.sharesdk.google.C0651f.m2480a(r12);	 Catch:{ Throwable -> 0x00b2 }
                r7 = cn.sharesdk.google.C0651f.m2479a(r0);	 Catch:{ Throwable -> 0x00b2 }
                switch(r7) {
                    case 1: goto L_0x002a;
                    case 2: goto L_0x003c;
                    case 3: goto L_0x0052;
                    case 4: goto L_0x0068;
                    default: goto L_0x001e;
                };	 Catch:{ Throwable -> 0x00b2 }
            L_0x001e:
                cn.sharesdk.google.C0651f.m2486b(r12, r0);	 Catch:{ Throwable -> 0x00b2 }
                r0 = r2;
                r2 = r3;
                r3 = r4;
                r4 = r5;
            L_0x0025:
                r5 = r4;
                r4 = r3;
                r3 = r2;
                r2 = r0;
                goto L_0x000d;
            L_0x002a:
                r0 = cn.sharesdk.google.C0651f.m2488d(r12, r0);	 Catch:{ Throwable -> 0x00b2 }
                r5 = 1;
                r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x00ba }
                r1.add(r5);	 Catch:{ Throwable -> 0x00ba }
                r9 = r2;
                r2 = r3;
                r3 = r4;
                r4 = r0;
                r0 = r9;
                goto L_0x0025;
            L_0x003c:
                r7 = cn.sharesdk.google.UserData.Cover.CoverInfo.f1424a;	 Catch:{ Throwable -> 0x00b2 }
                r0 = cn.sharesdk.google.C0651f.m2482a(r12, r0, r7);	 Catch:{ Throwable -> 0x00b2 }
                r0 = (cn.sharesdk.google.UserData.Cover.CoverInfo) r0;	 Catch:{ Throwable -> 0x00b2 }
                r4 = 2;
                r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x00c3 }
                r1.add(r4);	 Catch:{ Throwable -> 0x00c3 }
                r4 = r5;
                r9 = r3;
                r3 = r0;
                r0 = r2;
                r2 = r9;
                goto L_0x0025;
            L_0x0052:
                r7 = cn.sharesdk.google.UserData.Cover.CoverPhoto.f1429a;	 Catch:{ Throwable -> 0x00b2 }
                r0 = cn.sharesdk.google.C0651f.m2482a(r12, r0, r7);	 Catch:{ Throwable -> 0x00b2 }
                r0 = (cn.sharesdk.google.UserData.Cover.CoverPhoto) r0;	 Catch:{ Throwable -> 0x00b2 }
                r3 = 3;
                r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x00cc }
                r1.add(r3);	 Catch:{ Throwable -> 0x00cc }
                r3 = r4;
                r4 = r5;
                r9 = r2;
                r2 = r0;
                r0 = r9;
                goto L_0x0025;
            L_0x0068:
                r0 = cn.sharesdk.google.C0651f.m2488d(r12, r0);	 Catch:{ Throwable -> 0x00b2 }
                r2 = 4;
                r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Throwable -> 0x00d5 }
                r1.add(r2);	 Catch:{ Throwable -> 0x00d5 }
                r2 = r3;
                r3 = r4;
                r4 = r5;
                goto L_0x0025;
            L_0x0078:
                r0 = r12.dataPosition();	 Catch:{ Throwable -> 0x00b2 }
                if (r0 == r6) goto L_0x009f;
            L_0x007e:
                r0 = new java.lang.Throwable;	 Catch:{ Throwable -> 0x0097 }
                r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0097 }
                r7.<init>();	 Catch:{ Throwable -> 0x0097 }
                r8 = "Overread allowed size end=";
                r7 = r7.append(r8);	 Catch:{ Throwable -> 0x0097 }
                r6 = r7.append(r6);	 Catch:{ Throwable -> 0x0097 }
                r6 = r6.toString();	 Catch:{ Throwable -> 0x0097 }
                r0.<init>(r6);	 Catch:{ Throwable -> 0x0097 }
                throw r0;	 Catch:{ Throwable -> 0x0097 }
            L_0x0097:
                r0 = move-exception;
                r6 = cn.sharesdk.framework.utils.C0621d.m2279a();	 Catch:{ Throwable -> 0x00b2 }
                r6.d(r0);	 Catch:{ Throwable -> 0x00b2 }
            L_0x009f:
                r9 = r2;
                r2 = r5;
                r5 = r9;
                r10 = r4;
                r4 = r3;
                r3 = r10;
            L_0x00a5:
                r0 = new cn.sharesdk.google.UserData$Cover;
                r0.<init>(r1, r2, r3, r4, r5);
                return r0;
            L_0x00ab:
                r0 = move-exception;
                r5 = r2;
                r4 = r3;
            L_0x00ae:
                r0.printStackTrace();
                goto L_0x00a5;
            L_0x00b2:
                r0 = move-exception;
                r9 = r2;
                r2 = r5;
                r5 = r9;
                r10 = r4;
                r4 = r3;
                r3 = r10;
                goto L_0x00ae;
            L_0x00ba:
                r5 = move-exception;
                r9 = r5;
                r5 = r2;
                r2 = r0;
                r0 = r9;
                r10 = r4;
                r4 = r3;
                r3 = r10;
                goto L_0x00ae;
            L_0x00c3:
                r4 = move-exception;
                r9 = r4;
                r4 = r3;
                r3 = r0;
                r0 = r9;
                r10 = r2;
                r2 = r5;
                r5 = r10;
                goto L_0x00ae;
            L_0x00cc:
                r3 = move-exception;
                r9 = r3;
                r3 = r4;
                r4 = r0;
                r0 = r9;
                r10 = r2;
                r2 = r5;
                r5 = r10;
                goto L_0x00ae;
            L_0x00d5:
                r2 = move-exception;
                r9 = r2;
                r2 = r5;
                r5 = r0;
                r0 = r9;
                r10 = r4;
                r4 = r3;
                r3 = r10;
                goto L_0x00ae;
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.google.UserData.Cover.a.a(android.os.Parcel):cn.sharesdk.google.UserData$Cover");
            }

            /* renamed from: a */
            public Cover[] m2337a(int i) {
                return new Cover[i];
            }

            /* renamed from: a */
            static void m2335a(Cover cover, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = cover.m2338a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, cover.m2339b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2473a(parcel, 2, cover.m2340c(), i, true);
                }
                if (a2.contains(Integer.valueOf(3))) {
                    C0650e.m2473a(parcel, 3, cover.m2341d(), i, true);
                }
                if (a2.contains(Integer.valueOf(4))) {
                    C0650e.m2472a(parcel, 4, cover.m2342e());
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public Cover() {
            this.f1437c = 1;
            this.f1436b = new HashSet();
        }

        Cover(Set<Integer> set, int i, CoverInfo coverInfo, CoverPhoto coverPhoto, int i2) {
            this.f1436b = set;
            this.f1437c = i;
            this.f1438d = coverInfo;
            this.f1439e = coverPhoto;
            this.f1440f = i2;
        }

        /* renamed from: a */
        Set<Integer> m2338a() {
            return this.f1436b;
        }

        /* renamed from: b */
        int m2339b() {
            return this.f1437c;
        }

        /* renamed from: c */
        CoverInfo m2340c() {
            return this.f1438d;
        }

        /* renamed from: d */
        CoverPhoto m2341d() {
            return this.f1439e;
        }

        /* renamed from: e */
        public int m2342e() {
            return this.f1440f;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0633a.m2335a(this, parcel, i);
        }
    }

    public static final class Emails implements Parcelable {
        /* renamed from: a */
        public static final C0634a f1441a = new C0634a();
        /* renamed from: b */
        private final Set<Integer> f1442b;
        /* renamed from: c */
        private final int f1443c;
        /* renamed from: d */
        private boolean f1444d;
        /* renamed from: e */
        private int f1445e;
        /* renamed from: f */
        private String f1446f;

        /* renamed from: cn.sharesdk.google.UserData$Emails$a */
        public static class C0634a implements Creator<Emails> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2344a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2345a(i);
            }

            /* renamed from: a */
            public Emails m2344a(Parcel parcel) {
                boolean z;
                int i;
                Throwable th;
                int i2 = 0;
                Set hashSet = new HashSet();
                String str = null;
                try {
                    int b = C0651f.m2484b(parcel);
                    String str2 = null;
                    z = false;
                    i = 0;
                    while (parcel.dataPosition() < b) {
                        int a = C0651f.m2480a(parcel);
                        switch (C0651f.m2479a(a)) {
                            case 1:
                                try {
                                    i = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(1));
                                    break;
                                } catch (Throwable th2) {
                                    Throwable th3 = th2;
                                    str = str2;
                                    th = th3;
                                    int i3 = i2;
                                    i2 = i;
                                    i = i3;
                                    break;
                                }
                            case 2:
                                z = C0651f.m2487c(parcel, a);
                                hashSet.add(Integer.valueOf(2));
                                break;
                            case 3:
                                i2 = C0651f.m2488d(parcel, a);
                                hashSet.add(Integer.valueOf(3));
                                break;
                            case 4:
                                str2 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(4));
                                break;
                            default:
                                C0651f.m2486b(parcel, a);
                                break;
                        }
                    }
                    if (parcel.dataPosition() != b) {
                        throw new Throwable("Overread allowed size end=" + b);
                    }
                    str = str2;
                    int i4 = i2;
                    i2 = i;
                    i = i4;
                } catch (Throwable th4) {
                    th = th4;
                    i = 0;
                    z = false;
                    th.printStackTrace();
                    return new Emails(hashSet, i2, z, i, str);
                }
                return new Emails(hashSet, i2, z, i, str);
            }

            /* renamed from: a */
            public Emails[] m2345a(int i) {
                return new Emails[i];
            }

            /* renamed from: a */
            static void m2343a(Emails emails, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = emails.m2346a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, emails.m2347b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2475a(parcel, 2, emails.m2348c());
                }
                if (a2.contains(Integer.valueOf(3))) {
                    C0650e.m2472a(parcel, 3, emails.m2349d());
                }
                if (a2.contains(Integer.valueOf(4))) {
                    C0650e.m2474a(parcel, 4, emails.m2350e(), true);
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public Emails() {
            this.f1443c = 1;
            this.f1442b = new HashSet();
        }

        Emails(Set<Integer> set, int i, boolean z, int i2, String str) {
            this.f1442b = set;
            this.f1443c = i;
            this.f1444d = z;
            this.f1445e = i2;
            this.f1446f = str;
        }

        /* renamed from: a */
        Set<Integer> m2346a() {
            return this.f1442b;
        }

        /* renamed from: b */
        int m2347b() {
            return this.f1443c;
        }

        /* renamed from: c */
        public boolean m2348c() {
            return this.f1444d;
        }

        /* renamed from: d */
        public int m2349d() {
            return this.f1445e;
        }

        /* renamed from: e */
        public String m2350e() {
            return this.f1446f;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0634a.m2343a(this, parcel, i);
        }
    }

    public static final class Image implements Parcelable {
        /* renamed from: a */
        public static final C0635a f1447a = new C0635a();
        /* renamed from: b */
        private final Set<Integer> f1448b;
        /* renamed from: c */
        private final int f1449c;
        /* renamed from: d */
        private String f1450d;

        /* renamed from: cn.sharesdk.google.UserData$Image$a */
        public static class C0635a implements Creator<Image> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2352a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2353a(i);
            }

            /* renamed from: a */
            public Image m2352a(Parcel parcel) {
                String str;
                int i;
                Throwable th;
                Set hashSet = new HashSet();
                try {
                    int b = C0651f.m2484b(parcel);
                    str = null;
                    i = 0;
                    while (parcel.dataPosition() < b) {
                        int a = C0651f.m2480a(parcel);
                        switch (C0651f.m2479a(a)) {
                            case 1:
                                try {
                                    i = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(1));
                                    break;
                                } catch (Throwable th2) {
                                    th = th2;
                                    break;
                                }
                            case 2:
                                str = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(2));
                                break;
                            default:
                                C0651f.m2486b(parcel, a);
                                break;
                        }
                    }
                    if (parcel.dataPosition() != b) {
                        throw new Throwable("Overread allowed size end=" + b);
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    str = null;
                    i = 0;
                    th = th4;
                    th.printStackTrace();
                    return new Image(hashSet, i, str);
                }
                return new Image(hashSet, i, str);
            }

            /* renamed from: a */
            public Image[] m2353a(int i) {
                return new Image[i];
            }

            /* renamed from: a */
            static void m2351a(Image image, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = image.m2354a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, image.m2355b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2474a(parcel, 2, image.m2356c(), true);
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public Image() {
            this.f1449c = 1;
            this.f1448b = new HashSet();
        }

        Image(Set<Integer> set, int i, String str) {
            this.f1448b = set;
            this.f1449c = i;
            this.f1450d = str;
        }

        /* renamed from: a */
        Set<Integer> m2354a() {
            return this.f1448b;
        }

        /* renamed from: b */
        int m2355b() {
            return this.f1449c;
        }

        /* renamed from: c */
        public String m2356c() {
            return this.f1450d;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0635a.m2351a(this, parcel, i);
        }
    }

    public static final class Name implements Parcelable {
        /* renamed from: a */
        public static final C0636a f1451a = new C0636a();
        /* renamed from: b */
        private final Set<Integer> f1452b;
        /* renamed from: c */
        private final int f1453c;
        /* renamed from: d */
        private String f1454d;
        /* renamed from: e */
        private String f1455e;
        /* renamed from: f */
        private String f1456f;
        /* renamed from: g */
        private String f1457g;
        /* renamed from: h */
        private String f1458h;
        /* renamed from: i */
        private String f1459i;

        /* renamed from: cn.sharesdk.google.UserData$Name$a */
        public static class C0636a implements Creator<Name> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2358a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2359a(i);
            }

            /* renamed from: a */
            public Name m2358a(Parcel parcel) {
                Throwable th;
                int i;
                String str;
                Set hashSet = new HashSet();
                int i2 = 0;
                String str2 = null;
                String str3 = null;
                String str4 = null;
                String str5 = null;
                String str6 = null;
                String str7 = null;
                try {
                    String str8;
                    String str9;
                    int b = C0651f.m2484b(parcel);
                    String str10 = null;
                    while (parcel.dataPosition() < b) {
                        int a = C0651f.m2480a(parcel);
                        switch (C0651f.m2479a(a)) {
                            case 1:
                                try {
                                    i2 = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(1));
                                    break;
                                } catch (Throwable th2) {
                                    Throwable th3 = th2;
                                    str7 = str10;
                                    th = th3;
                                    str8 = str6;
                                    i = i2;
                                    str = str8;
                                    str9 = str4;
                                    str4 = str3;
                                    str3 = str9;
                                    String str11 = str2;
                                    str2 = str5;
                                    str5 = str11;
                                    break;
                                }
                            case 2:
                                str2 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(2));
                                break;
                            case 3:
                                str3 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(3));
                                break;
                            case 4:
                                str4 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(4));
                                break;
                            case 5:
                                str5 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(5));
                                break;
                            case 6:
                                str6 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(6));
                                break;
                            case 7:
                                str10 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(7));
                                break;
                            default:
                                C0651f.m2486b(parcel, a);
                                break;
                        }
                    }
                    if (parcel.dataPosition() != b) {
                        throw new Throwable("Overread allowed size end=" + b);
                    }
                    str7 = str10;
                    String str12 = str6;
                    i = i2;
                    str = str12;
                    str8 = str4;
                    str4 = str3;
                    str3 = str8;
                    str9 = str2;
                    str2 = str5;
                    str5 = str9;
                } catch (Throwable th4) {
                    th = th4;
                    i = 0;
                    str = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    str5 = null;
                    th.printStackTrace();
                    return new Name(hashSet, i, str5, str4, str3, str2, str, str7);
                }
                return new Name(hashSet, i, str5, str4, str3, str2, str, str7);
            }

            /* renamed from: a */
            public Name[] m2359a(int i) {
                return new Name[i];
            }

            /* renamed from: a */
            static void m2357a(Name name, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = name.m2360a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, name.m2361b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2474a(parcel, 2, name.m2362c(), true);
                }
                if (a2.contains(Integer.valueOf(3))) {
                    C0650e.m2474a(parcel, 3, name.m2363d(), true);
                }
                if (a2.contains(Integer.valueOf(4))) {
                    C0650e.m2474a(parcel, 4, name.m2364e(), true);
                }
                if (a2.contains(Integer.valueOf(5))) {
                    C0650e.m2474a(parcel, 5, name.m2365f(), true);
                }
                if (a2.contains(Integer.valueOf(6))) {
                    C0650e.m2474a(parcel, 6, name.m2366g(), true);
                }
                if (a2.contains(Integer.valueOf(7))) {
                    C0650e.m2474a(parcel, 7, name.m2367h(), true);
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public Name() {
            this.f1453c = 1;
            this.f1452b = new HashSet();
        }

        Name(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, String str6) {
            this.f1452b = set;
            this.f1453c = i;
            this.f1454d = str;
            this.f1455e = str2;
            this.f1456f = str3;
            this.f1457g = str4;
            this.f1458h = str5;
            this.f1459i = str6;
        }

        /* renamed from: a */
        Set<Integer> m2360a() {
            return this.f1452b;
        }

        /* renamed from: b */
        int m2361b() {
            return this.f1453c;
        }

        /* renamed from: c */
        public String m2362c() {
            return this.f1454d;
        }

        /* renamed from: d */
        public String m2363d() {
            return this.f1455e;
        }

        /* renamed from: e */
        public String m2364e() {
            return this.f1456f;
        }

        /* renamed from: f */
        public String m2365f() {
            return this.f1457g;
        }

        /* renamed from: g */
        public String m2366g() {
            return this.f1458h;
        }

        /* renamed from: h */
        public String m2367h() {
            return this.f1459i;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0636a.m2357a(this, parcel, i);
        }
    }

    public static final class Organizations implements Parcelable {
        /* renamed from: a */
        public static final C0637a f1460a = new C0637a();
        /* renamed from: b */
        private final Set<Integer> f1461b;
        /* renamed from: c */
        private final int f1462c;
        /* renamed from: d */
        private String f1463d;
        /* renamed from: e */
        private String f1464e;
        /* renamed from: f */
        private String f1465f;
        /* renamed from: g */
        private String f1466g;
        /* renamed from: h */
        private String f1467h;
        /* renamed from: i */
        private boolean f1468i;
        /* renamed from: j */
        private String f1469j;
        /* renamed from: k */
        private String f1470k;
        /* renamed from: l */
        private int f1471l;

        /* renamed from: cn.sharesdk.google.UserData$Organizations$a */
        public static class C0637a implements Creator<Organizations> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2369a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2370a(i);
            }

            /* renamed from: a */
            public Organizations m2369a(Parcel parcel) {
                Throwable th;
                String str;
                int i;
                String str2;
                boolean z;
                String str3;
                boolean z2;
                String str4;
                Set hashSet = new HashSet();
                int i2 = 0;
                String str5 = null;
                String str6 = null;
                String str7 = null;
                String str8 = null;
                String str9 = null;
                boolean z3 = false;
                String str10 = null;
                String str11 = null;
                int i3 = 0;
                Throwable th2;
                try {
                    String str12;
                    int b = C0651f.m2484b(parcel);
                    int i4 = 0;
                    while (parcel.dataPosition() < b) {
                        try {
                            i3 = C0651f.m2480a(parcel);
                            switch (C0651f.m2479a(i3)) {
                                case 1:
                                    i2 = C0651f.m2488d(parcel, i3);
                                    hashSet.add(Integer.valueOf(1));
                                    continue;
                                case 2:
                                    str5 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(2));
                                    continue;
                                case 3:
                                    str6 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(3));
                                    continue;
                                case 4:
                                    str7 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(4));
                                    continue;
                                case 5:
                                    str8 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(5));
                                    continue;
                                case 6:
                                    str9 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(6));
                                    continue;
                                case 7:
                                    z3 = C0651f.m2487c(parcel, i3);
                                    try {
                                        hashSet.add(Integer.valueOf(7));
                                        continue;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        i3 = i4;
                                        th2 = th;
                                        str = str11;
                                        i = i2;
                                        str2 = str;
                                        z = z3;
                                        str3 = str6;
                                        z2 = z;
                                        str12 = str7;
                                        str7 = str9;
                                        str9 = str12;
                                        str4 = str5;
                                        str5 = str10;
                                        str10 = str4;
                                        break;
                                    }
                                case 8:
                                    str10 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(8));
                                    continue;
                                case 9:
                                    str11 = C0651f.m2489e(parcel, i3);
                                    hashSet.add(Integer.valueOf(9));
                                    continue;
                                case 10:
                                    i4 = C0651f.m2488d(parcel, i3);
                                    hashSet.add(Integer.valueOf(10));
                                    continue;
                                default:
                                    C0651f.m2486b(parcel, i3);
                                    continue;
                            }
                        } catch (Throwable th32) {
                            th = th32;
                            i3 = i4;
                            th2 = th;
                            str = str11;
                            i = i2;
                            str2 = str;
                            z = z3;
                            str3 = str6;
                            z2 = z;
                            str12 = str7;
                            str7 = str9;
                            str9 = str12;
                            str4 = str5;
                            str5 = str10;
                            str10 = str4;
                        }
                        th2.printStackTrace();
                        return new Organizations(hashSet, i, str10, str3, str9, str8, str7, z2, str5, str2, i3);
                    }
                    if (parcel.dataPosition() != b) {
                        throw new Throwable("Overread allowed size end=" + b);
                    }
                    i3 = i4;
                    String str13 = str11;
                    i = i2;
                    str2 = str13;
                    boolean z4 = z3;
                    str3 = str6;
                    z2 = z4;
                    String str14 = str7;
                    str7 = str9;
                    str9 = str14;
                    str12 = str5;
                    str5 = str10;
                    str10 = str12;
                } catch (Throwable th4) {
                    th2 = th4;
                    i = 0;
                    str2 = null;
                    str3 = null;
                    z2 = false;
                    str7 = null;
                    str9 = null;
                    str5 = null;
                    str10 = null;
                }
                return new Organizations(hashSet, i, str10, str3, str9, str8, str7, z2, str5, str2, i3);
            }

            /* renamed from: a */
            public Organizations[] m2370a(int i) {
                return new Organizations[i];
            }

            /* renamed from: a */
            static void m2368a(Organizations organizations, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = organizations.m2371a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, organizations.m2372b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2474a(parcel, 2, organizations.m2373c(), true);
                }
                if (a2.contains(Integer.valueOf(3))) {
                    C0650e.m2474a(parcel, 3, organizations.m2374d(), true);
                }
                if (a2.contains(Integer.valueOf(4))) {
                    C0650e.m2474a(parcel, 4, organizations.m2375e(), true);
                }
                if (a2.contains(Integer.valueOf(5))) {
                    C0650e.m2474a(parcel, 5, organizations.m2376f(), true);
                }
                if (a2.contains(Integer.valueOf(6))) {
                    C0650e.m2474a(parcel, 6, organizations.m2377g(), true);
                }
                if (a2.contains(Integer.valueOf(7))) {
                    C0650e.m2475a(parcel, 7, organizations.m2378h());
                }
                if (a2.contains(Integer.valueOf(8))) {
                    C0650e.m2474a(parcel, 8, organizations.m2379i(), true);
                }
                if (a2.contains(Integer.valueOf(9))) {
                    C0650e.m2474a(parcel, 9, organizations.m2380j(), true);
                }
                if (a2.contains(Integer.valueOf(10))) {
                    C0650e.m2472a(parcel, 10, organizations.m2381k());
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public Organizations() {
            this.f1462c = 1;
            this.f1461b = new HashSet();
        }

        Organizations(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.f1461b = set;
            this.f1462c = i;
            this.f1463d = str;
            this.f1464e = str2;
            this.f1465f = str3;
            this.f1466g = str4;
            this.f1467h = str5;
            this.f1468i = z;
            this.f1469j = str6;
            this.f1470k = str7;
            this.f1471l = i2;
        }

        /* renamed from: a */
        Set<Integer> m2371a() {
            return this.f1461b;
        }

        /* renamed from: b */
        int m2372b() {
            return this.f1462c;
        }

        /* renamed from: c */
        public String m2373c() {
            return this.f1463d;
        }

        /* renamed from: d */
        public String m2374d() {
            return this.f1464e;
        }

        /* renamed from: e */
        public String m2375e() {
            return this.f1465f;
        }

        /* renamed from: f */
        public String m2376f() {
            return this.f1466g;
        }

        /* renamed from: g */
        public String m2377g() {
            return this.f1467h;
        }

        /* renamed from: h */
        public boolean m2378h() {
            return this.f1468i;
        }

        /* renamed from: i */
        public String m2379i() {
            return this.f1469j;
        }

        /* renamed from: j */
        public String m2380j() {
            return this.f1470k;
        }

        /* renamed from: k */
        public int m2381k() {
            return this.f1471l;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0637a.m2368a(this, parcel, i);
        }
    }

    public static final class PlacesLived implements Parcelable {
        /* renamed from: a */
        public static final C0638a f1472a = new C0638a();
        /* renamed from: b */
        private final Set<Integer> f1473b;
        /* renamed from: c */
        private final int f1474c;
        /* renamed from: d */
        private boolean f1475d;
        /* renamed from: e */
        private String f1476e;

        /* renamed from: cn.sharesdk.google.UserData$PlacesLived$a */
        public static class C0638a implements Creator<PlacesLived> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2383a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2384a(i);
            }

            /* renamed from: a */
            public PlacesLived m2383a(Parcel parcel) {
                String str;
                boolean z;
                Throwable th;
                int i = 0;
                Set hashSet = new HashSet();
                try {
                    int b = C0651f.m2484b(parcel);
                    str = null;
                    z = false;
                    while (parcel.dataPosition() < b) {
                        int a = C0651f.m2480a(parcel);
                        switch (C0651f.m2479a(a)) {
                            case 1:
                                try {
                                    i = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(1));
                                    break;
                                } catch (Throwable th2) {
                                    th = th2;
                                    break;
                                }
                            case 2:
                                z = C0651f.m2487c(parcel, a);
                                hashSet.add(Integer.valueOf(2));
                                break;
                            case 3:
                                str = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(3));
                                break;
                            default:
                                C0651f.m2486b(parcel, a);
                                break;
                        }
                    }
                    if (parcel.dataPosition() != b) {
                        throw new Throwable("Overread allowed size end=" + b);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str = null;
                    z = false;
                    th.printStackTrace();
                    return new PlacesLived(hashSet, i, z, str);
                }
                return new PlacesLived(hashSet, i, z, str);
            }

            /* renamed from: a */
            public PlacesLived[] m2384a(int i) {
                return new PlacesLived[i];
            }

            /* renamed from: a */
            static void m2382a(PlacesLived placesLived, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = placesLived.m2385a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, placesLived.m2386b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2475a(parcel, 2, placesLived.m2387c());
                }
                if (a2.contains(Integer.valueOf(3))) {
                    C0650e.m2474a(parcel, 3, placesLived.m2388d(), true);
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public PlacesLived() {
            this.f1474c = 1;
            this.f1473b = new HashSet();
        }

        PlacesLived(Set<Integer> set, int i, boolean z, String str) {
            this.f1473b = set;
            this.f1474c = i;
            this.f1475d = z;
            this.f1476e = str;
        }

        /* renamed from: a */
        Set<Integer> m2385a() {
            return this.f1473b;
        }

        /* renamed from: b */
        int m2386b() {
            return this.f1474c;
        }

        /* renamed from: c */
        public boolean m2387c() {
            return this.f1475d;
        }

        /* renamed from: d */
        public String m2388d() {
            return this.f1476e;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0638a.m2382a(this, parcel, i);
        }
    }

    public static final class Urls implements Parcelable {
        /* renamed from: a */
        public static final C0639a f1477a = new C0639a();
        /* renamed from: b */
        private final Set<Integer> f1478b;
        /* renamed from: c */
        private final int f1479c;
        /* renamed from: d */
        private boolean f1480d;
        /* renamed from: e */
        private int f1481e;
        /* renamed from: f */
        private String f1482f;

        /* renamed from: cn.sharesdk.google.UserData$Urls$a */
        public static class C0639a implements Creator<Urls> {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m2390a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m2391a(i);
            }

            /* renamed from: a */
            public Urls m2390a(Parcel parcel) {
                boolean z;
                int i;
                Throwable th;
                int i2 = 0;
                Set hashSet = new HashSet();
                String str = null;
                try {
                    int b = C0651f.m2484b(parcel);
                    String str2 = null;
                    z = false;
                    i = 0;
                    while (parcel.dataPosition() < b) {
                        int a = C0651f.m2480a(parcel);
                        switch (C0651f.m2479a(a)) {
                            case 1:
                                try {
                                    i = C0651f.m2488d(parcel, a);
                                    hashSet.add(Integer.valueOf(1));
                                    break;
                                } catch (Throwable th2) {
                                    Throwable th3 = th2;
                                    str = str2;
                                    th = th3;
                                    int i3 = i2;
                                    i2 = i;
                                    i = i3;
                                    break;
                                }
                            case 2:
                                z = C0651f.m2487c(parcel, a);
                                hashSet.add(Integer.valueOf(2));
                                break;
                            case 3:
                                i2 = C0651f.m2488d(parcel, a);
                                hashSet.add(Integer.valueOf(3));
                                break;
                            case 4:
                                str2 = C0651f.m2489e(parcel, a);
                                hashSet.add(Integer.valueOf(4));
                                break;
                            default:
                                C0651f.m2486b(parcel, a);
                                break;
                        }
                    }
                    if (parcel.dataPosition() != b) {
                        throw new Throwable("Overread allowed size end=" + b);
                    }
                    str = str2;
                    int i4 = i2;
                    i2 = i;
                    i = i4;
                } catch (Throwable th4) {
                    th = th4;
                    i = 0;
                    z = false;
                    th.printStackTrace();
                    return new Urls(hashSet, i2, z, i, str);
                }
                return new Urls(hashSet, i2, z, i, str);
            }

            /* renamed from: a */
            public Urls[] m2391a(int i) {
                return new Urls[i];
            }

            /* renamed from: a */
            static void m2389a(Urls urls, Parcel parcel, int i) {
                int a = C0650e.m2470a(parcel);
                Set a2 = urls.m2392a();
                if (a2.contains(Integer.valueOf(1))) {
                    C0650e.m2472a(parcel, 1, urls.m2393b());
                }
                if (a2.contains(Integer.valueOf(2))) {
                    C0650e.m2475a(parcel, 2, urls.m2394c());
                }
                if (a2.contains(Integer.valueOf(3))) {
                    C0650e.m2472a(parcel, 3, urls.m2395d());
                }
                if (a2.contains(Integer.valueOf(4))) {
                    C0650e.m2474a(parcel, 4, urls.m2396e(), true);
                }
                C0650e.m2471a(parcel, a);
            }
        }

        public Urls() {
            this.f1479c = 1;
            this.f1478b = new HashSet();
        }

        Urls(Set<Integer> set, int i, boolean z, int i2, String str) {
            this.f1478b = set;
            this.f1479c = i;
            this.f1480d = z;
            this.f1481e = i2;
            this.f1482f = str;
        }

        /* renamed from: a */
        Set<Integer> m2392a() {
            return this.f1478b;
        }

        /* renamed from: b */
        int m2393b() {
            return this.f1479c;
        }

        /* renamed from: c */
        public boolean m2394c() {
            return this.f1480d;
        }

        /* renamed from: d */
        public int m2395d() {
            return this.f1481e;
        }

        /* renamed from: e */
        public String m2396e() {
            return this.f1482f;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            C0639a.m2389a(this, parcel, i);
        }
    }
}

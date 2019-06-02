package com.tencent.bugly.crashreport.crash.anr;

import com.j256.ormlite.stmt.query.SimpleComparison;
import com.tencent.bugly.proguard.C4475w;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
public class TraceFileHelper {

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b */
    public interface C4424b {
        /* renamed from: a */
        boolean mo6057a(long j);

        /* renamed from: a */
        boolean mo6058a(long j, long j2, String str);

        /* renamed from: a */
        boolean mo6059a(String str, int i, String str2, String str3);
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a */
    public static class C4427a {
        /* renamed from: a */
        public long f15410a;
        /* renamed from: b */
        public String f15411b;
        /* renamed from: c */
        public long f15412c;
        /* renamed from: d */
        public Map<String, String[]> f15413d;
    }

    public static C4427a readTargetDumpInfo(String str, String str2, final boolean z) {
        if (str == null || str2 == null) {
            return null;
        }
        final C4427a c4427a = new C4427a();
        readTraceFile(str2, new C4424b() {
            /* renamed from: a */
            public final boolean mo6059a(String str, int i, String str2, String str3) {
                C4475w.m17751c("new thread %s", str);
                if (c4427a.f15410a > 0 && c4427a.f15412c > 0 && c4427a.f15411b != null) {
                    if (c4427a.f15413d == null) {
                        c4427a.f15413d = new HashMap();
                    }
                    c4427a.f15413d.put(str, new String[]{str2, str3, i});
                }
                return true;
            }

            /* renamed from: a */
            public final boolean mo6058a(long j, long j2, String str) {
                C4475w.m17751c("new process %s", str);
                if (!str.equals(str)) {
                    return true;
                }
                c4427a.f15410a = j;
                c4427a.f15411b = str;
                c4427a.f15412c = j2;
                if (z) {
                    return true;
                }
                return false;
            }

            /* renamed from: a */
            public final boolean mo6057a(long j) {
                C4475w.m17751c("process end %d", Long.valueOf(j));
                if (c4427a.f15410a <= 0 || c4427a.f15412c <= 0 || c4427a.f15411b == null) {
                    return true;
                }
                return false;
            }
        });
        if (c4427a.f15410a <= 0 || c4427a.f15412c <= 0 || c4427a.f15411b == null) {
            return null;
        }
        return c4427a;
    }

    public static C4427a readFirstDumpInfo(String str, final boolean z) {
        if (str == null) {
            C4475w.m17753e("path:%s", str);
            return null;
        }
        final C4427a c4427a = new C4427a();
        readTraceFile(str, new C4424b() {
            /* renamed from: a */
            public final boolean mo6059a(String str, int i, String str2, String str3) {
                C4475w.m17751c("new thread %s", str);
                if (c4427a.f15413d == null) {
                    c4427a.f15413d = new HashMap();
                }
                c4427a.f15413d.put(str, new String[]{str2, str3, i});
                return true;
            }

            /* renamed from: a */
            public final boolean mo6058a(long j, long j2, String str) {
                C4475w.m17751c("new process %s", str);
                c4427a.f15410a = j;
                c4427a.f15411b = str;
                c4427a.f15412c = j2;
                if (z) {
                    return true;
                }
                return false;
            }

            /* renamed from: a */
            public final boolean mo6057a(long j) {
                C4475w.m17751c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (c4427a.f15410a > 0 && c4427a.f15412c > 0 && c4427a.f15411b != null) {
            return c4427a;
        }
        C4475w.m17753e("first dump error %s", c4427a.f15410a + " " + c4427a.f15412c + " " + c4427a.f15411b);
        return null;
    }

    public static void readTraceFile(String str, C4424b c4424b) {
        Throwable e;
        if (str != null && c4424b != null) {
            File file = new File(str);
            if (file.exists()) {
                file.lastModified();
                file.length();
                BufferedReader bufferedReader = null;
                BufferedReader bufferedReader2;
                try {
                    bufferedReader2 = new BufferedReader(new FileReader(file));
                    try {
                        Pattern compile = Pattern.compile("-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}");
                        Pattern compile2 = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
                        Pattern compile3 = Pattern.compile("Cmd\\sline:\\s(\\S+)");
                        Pattern compile4 = Pattern.compile("\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                        while (true) {
                            Object[] a = m17408a(bufferedReader2, compile);
                            if (a != null) {
                                String[] split = a[1].toString().split("\\s");
                                long parseLong = Long.parseLong(split[2]);
                                long time = simpleDateFormat.parse(split[4] + " " + split[5]).getTime();
                                a = m17408a(bufferedReader2, compile3);
                                if (a == null) {
                                    try {
                                        bufferedReader2.close();
                                        return;
                                    } catch (Throwable e2) {
                                        if (!C4475w.m17748a(e2)) {
                                            e2.printStackTrace();
                                            return;
                                        }
                                        return;
                                    }
                                }
                                Matcher matcher = compile3.matcher(a[1].toString());
                                matcher.find();
                                matcher.group(1);
                                if (c4424b.mo6058a(parseLong, time, matcher.group(1))) {
                                    while (true) {
                                        a = m17408a(bufferedReader2, compile4, compile2);
                                        if (a != null) {
                                            if (a[0] != compile4) {
                                                break;
                                            }
                                            CharSequence obj = a[1].toString();
                                            Matcher matcher2 = Pattern.compile("\".+\"").matcher(obj);
                                            matcher2.find();
                                            String group = matcher2.group();
                                            group = group.substring(1, group.length() - 1);
                                            obj.contains("NATIVE");
                                            matcher = Pattern.compile("tid=\\d+").matcher(obj);
                                            matcher.find();
                                            String group2 = matcher.group();
                                            c4424b.mo6059a(group, Integer.parseInt(group2.substring(group2.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1)), m17407a(bufferedReader2), m17409b(bufferedReader2));
                                        } else {
                                            break;
                                        }
                                    }
                                    if (!c4424b.mo6057a(Long.parseLong(a[1].toString().split("\\s")[2]))) {
                                        try {
                                            bufferedReader2.close();
                                            return;
                                        } catch (Throwable e22) {
                                            if (!C4475w.m17748a(e22)) {
                                                e22.printStackTrace();
                                                return;
                                            }
                                            return;
                                        }
                                    }
                                } else {
                                    try {
                                        bufferedReader2.close();
                                        return;
                                    } catch (Throwable e222) {
                                        if (!C4475w.m17748a(e222)) {
                                            e222.printStackTrace();
                                            return;
                                        }
                                        return;
                                    }
                                }
                            }
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (Throwable e2222) {
                                if (!C4475w.m17748a(e2222)) {
                                    e2222.printStackTrace();
                                    return;
                                }
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e2222 = e3;
                        bufferedReader = bufferedReader2;
                    } catch (Throwable th) {
                        e2222 = th;
                    }
                } catch (Exception e4) {
                    e2222 = e4;
                    try {
                        if (!C4475w.m17748a(e2222)) {
                            e2222.printStackTrace();
                        }
                        C4475w.m17752d("trace open fail:%s : %s", e2222.getClass().getName(), e2222.getMessage());
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable e22222) {
                                if (!C4475w.m17748a(e22222)) {
                                    e22222.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        e22222 = th2;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e5) {
                                if (!C4475w.m17748a(e5)) {
                                    e5.printStackTrace();
                                }
                            }
                        }
                        throw e22222;
                    }
                } catch (Throwable th3) {
                    e22222 = th3;
                    bufferedReader2 = null;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw e22222;
                }
            }
        }
    }

    /* renamed from: a */
    private static Object[] m17408a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader == null || patternArr == null) {
            return null;
        }
        while (true) {
            CharSequence readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            for (Pattern matcher : patternArr) {
                if (matcher.matcher(readLine).matches()) {
                    return new Object[]{patternArr[r1], readLine};
                }
            }
        }
    }

    /* renamed from: a */
    private static String m17407a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private static String m17409b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.trim().length() > 0) {
                stringBuffer.append(readLine + "\n");
            }
        }
        return stringBuffer.toString();
    }
}

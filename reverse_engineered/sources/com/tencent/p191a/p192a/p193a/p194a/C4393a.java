package com.tencent.p191a.p192a.p193a.p194a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tencent.a.a.a.a.a */
final class C4393a {
    /* renamed from: a */
    static List<String> m17214a(File file) {
        Reader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                arrayList.add(readLine.trim());
            } else {
                fileReader.close();
                bufferedReader.close();
                return arrayList;
            }
        }
    }

    /* renamed from: d */
    static File m17215d(String str) {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                C4393a.m17215d(file.getParentFile().getAbsolutePath());
            }
            file.mkdir();
        }
        return file;
    }
}

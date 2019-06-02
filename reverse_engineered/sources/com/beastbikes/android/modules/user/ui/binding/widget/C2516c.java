package com.beastbikes.android.modules.user.ui.binding.widget;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* compiled from: CountryUtils */
/* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.c */
public class C2516c {
    /* renamed from: a */
    public static HashMap<Character, ArrayList<String[]>> m12645a(Context context) {
        int i = 0;
        Character[] chArr = new Character[]{Character.valueOf('A'), Character.valueOf('B'), Character.valueOf('C'), Character.valueOf('D'), Character.valueOf('E'), Character.valueOf('F'), Character.valueOf('G'), Character.valueOf('H'), Character.valueOf('I'), Character.valueOf('J'), Character.valueOf('K'), Character.valueOf('L'), Character.valueOf('M'), Character.valueOf('N'), Character.valueOf('O'), Character.valueOf('P'), Character.valueOf('Q'), Character.valueOf('R'), Character.valueOf('S'), Character.valueOf('T'), Character.valueOf('U'), Character.valueOf('V'), Character.valueOf('W'), Character.valueOf('X'), Character.valueOf('Y'), Character.valueOf('Z')};
        HashMap linkedHashMap = new LinkedHashMap();
        int length = chArr.length;
        while (i < length) {
            Object obj = chArr[i];
            ArrayList a = C2516c.m12644a(context, "smssdk_country_group_" + String.valueOf(obj).toLowerCase());
            if (!a.isEmpty()) {
                linkedHashMap.put(obj, a);
            }
            i++;
        }
        return linkedHashMap;
    }

    /* renamed from: a */
    private static ArrayList<String[]> m12644a(Context context, String str) {
        ArrayList<String[]> arrayList = new ArrayList();
        int identifier = context.getResources().getIdentifier(str, "array", context.getPackageName());
        if (identifier > 0) {
            for (String split : context.getResources().getStringArray(identifier)) {
                arrayList.add(split.split(","));
            }
        }
        return arrayList;
    }
}

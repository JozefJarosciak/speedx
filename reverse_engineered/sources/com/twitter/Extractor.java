package com.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

public class Extractor {
    /* renamed from: a */
    protected boolean f16180a = true;

    public static class Entity {
        /* renamed from: a */
        protected int f16173a;
        /* renamed from: b */
        protected int f16174b;
        /* renamed from: c */
        protected final String f16175c;
        /* renamed from: d */
        protected final String f16176d;
        /* renamed from: e */
        protected final Type f16177e;
        /* renamed from: f */
        protected String f16178f;
        /* renamed from: g */
        protected String f16179g;

        public enum Type {
            URL,
            HASHTAG,
            MENTION,
            CASHTAG
        }

        public Entity(int i, int i2, String str, String str2, Type type) {
            this.f16178f = null;
            this.f16179g = null;
            this.f16173a = i;
            this.f16174b = i2;
            this.f16175c = str;
            this.f16176d = str2;
            this.f16177e = type;
        }

        public Entity(int i, int i2, String str, Type type) {
            this(i, i2, str, null, type);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Entity)) {
                return false;
            }
            Entity entity = (Entity) obj;
            if (this.f16177e.equals(entity.f16177e) && this.f16173a == entity.f16173a && this.f16174b == entity.f16174b && this.f16175c.equals(entity.f16175c)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((this.f16177e.hashCode() + this.f16175c.hashCode()) + this.f16173a) + this.f16174b;
        }

        public String toString() {
            return this.f16175c + "(" + this.f16177e + ") [" + this.f16173a + "," + this.f16174b + "]";
        }
    }

    /* renamed from: a */
    public List<Entity> m18128a(String str) {
        if (!(str == null || str.length() == 0)) {
            if ((this.f16180a ? str.indexOf(46) : str.indexOf(58)) != -1) {
                List<Entity> arrayList = new ArrayList();
                Matcher matcher = C4569a.f16188h.matcher(str);
                while (matcher.find()) {
                    if (matcher.group(4) != null || (this.f16180a && !C4569a.f16190j.matcher(matcher.group(2)).matches())) {
                        String group = matcher.group(3);
                        int start = matcher.start(3);
                        int end = matcher.end(3);
                        Matcher matcher2 = C4569a.f16189i.matcher(group);
                        if (matcher2.find()) {
                            group = matcher2.group();
                            end = group.length() + start;
                        }
                        arrayList.add(new Entity(start, end, group, Type.URL));
                    }
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }
}

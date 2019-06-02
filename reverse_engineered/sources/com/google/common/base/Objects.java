package com.google.common.base;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.GwtCompatible;
import java.util.Arrays;

@GwtCompatible
public final class Objects {

    @Deprecated
    public static final class ToStringHelper {
        private final String className;
        private Objects$ToStringHelper$ValueHolder holderHead;
        private Objects$ToStringHelper$ValueHolder holderTail;
        private boolean omitNullValues;

        private ToStringHelper(String str) {
            this.holderHead = new Objects$ToStringHelper$ValueHolder(null);
            this.holderTail = this.holderHead;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public ToStringHelper add(String str, Object obj) {
            return addHolder(str, obj);
        }

        public ToStringHelper add(String str, boolean z) {
            return addHolder(str, String.valueOf(z));
        }

        public ToStringHelper add(String str, char c) {
            return addHolder(str, String.valueOf(c));
        }

        public ToStringHelper add(String str, double d) {
            return addHolder(str, String.valueOf(d));
        }

        public ToStringHelper add(String str, float f) {
            return addHolder(str, String.valueOf(f));
        }

        public ToStringHelper add(String str, int i) {
            return addHolder(str, String.valueOf(i));
        }

        public ToStringHelper add(String str, long j) {
            return addHolder(str, String.valueOf(j));
        }

        public ToStringHelper addValue(Object obj) {
            return addHolder(obj);
        }

        public ToStringHelper addValue(boolean z) {
            return addHolder(String.valueOf(z));
        }

        public ToStringHelper addValue(char c) {
            return addHolder(String.valueOf(c));
        }

        public ToStringHelper addValue(double d) {
            return addHolder(String.valueOf(d));
        }

        public ToStringHelper addValue(float f) {
            return addHolder(String.valueOf(f));
        }

        public ToStringHelper addValue(int i) {
            return addHolder(String.valueOf(i));
        }

        public ToStringHelper addValue(long j) {
            return addHolder(String.valueOf(j));
        }

        public String toString() {
            boolean z = this.omitNullValues;
            StringBuilder append = new StringBuilder(32).append(this.className).append(CoreConstants.CURLY_LEFT);
            String str = "";
            Objects$ToStringHelper$ValueHolder objects$ToStringHelper$ValueHolder = this.holderHead.next;
            while (objects$ToStringHelper$ValueHolder != null) {
                if (!z || objects$ToStringHelper$ValueHolder.value != null) {
                    append.append(str);
                    str = ", ";
                    if (objects$ToStringHelper$ValueHolder.name != null) {
                        append.append(objects$ToStringHelper$ValueHolder.name).append('=');
                    }
                    append.append(objects$ToStringHelper$ValueHolder.value);
                }
                objects$ToStringHelper$ValueHolder = objects$ToStringHelper$ValueHolder.next;
            }
            return append.append(CoreConstants.CURLY_RIGHT).toString();
        }

        private Objects$ToStringHelper$ValueHolder addHolder() {
            Objects$ToStringHelper$ValueHolder objects$ToStringHelper$ValueHolder = new Objects$ToStringHelper$ValueHolder(null);
            this.holderTail.next = objects$ToStringHelper$ValueHolder;
            this.holderTail = objects$ToStringHelper$ValueHolder;
            return objects$ToStringHelper$ValueHolder;
        }

        private ToStringHelper addHolder(Object obj) {
            addHolder().value = obj;
            return this;
        }

        private ToStringHelper addHolder(String str, Object obj) {
            Objects$ToStringHelper$ValueHolder addHolder = addHolder();
            addHolder.value = obj;
            addHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }
    }

    private Objects() {
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Deprecated
    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(MoreObjects.simpleName(obj.getClass()));
    }

    @Deprecated
    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(MoreObjects.simpleName(cls));
    }

    @Deprecated
    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }

    @Deprecated
    public static <T> T firstNonNull(T t, T t2) {
        return MoreObjects.firstNonNull(t, t2);
    }
}

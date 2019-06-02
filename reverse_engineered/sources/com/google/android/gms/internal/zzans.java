package com.google.android.gms.internal;

import com.avos.avoscloud.AVException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class zzans {
    private final Map<Type, zzamu<?>> ben;

    /* renamed from: com.google.android.gms.internal.zzans$2 */
    class C33412 implements zzanx<T> {
        final /* synthetic */ zzans beP;

        C33412(zzans zzans) {
            this.beP = zzans;
        }

        /* renamed from: a */
        public T mo4182a() {
            return new LinkedHashMap();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzans$3 */
    class C33423 implements zzanx<T> {
        final /* synthetic */ zzans beP;

        C33423(zzans zzans) {
            this.beP = zzans;
        }

        /* renamed from: a */
        public T mo4182a() {
            return new zzanw();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzans$7 */
    class C33467 implements zzanx<T> {
        final /* synthetic */ zzans beP;

        C33467(zzans zzans) {
            this.beP = zzans;
        }

        /* renamed from: a */
        public T mo4182a() {
            return new TreeSet();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzans$9 */
    class C33489 implements zzanx<T> {
        final /* synthetic */ zzans beP;

        C33489(zzans zzans) {
            this.beP = zzans;
        }

        /* renamed from: a */
        public T mo4182a() {
            return new LinkedHashSet();
        }
    }

    public zzans(Map<Type, zzamu<?>> map) {
        this.ben = map;
    }

    private <T> zzanx<T> zzc(final Type type, Class<? super T> cls) {
        return Collection.class.isAssignableFrom(cls) ? SortedSet.class.isAssignableFrom(cls) ? new C33467(this) : EnumSet.class.isAssignableFrom(cls) ? new zzanx<T>(this) {
            final /* synthetic */ zzans beP;

            /* renamed from: a */
            public T mo4182a() {
                if (type instanceof ParameterizedType) {
                    Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (type instanceof Class) {
                        return EnumSet.noneOf((Class) type);
                    }
                    String str = "Invalid EnumSet type: ";
                    String valueOf = String.valueOf(type.toString());
                    throw new zzamz(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
                str = "Invalid EnumSet type: ";
                valueOf = String.valueOf(type.toString());
                throw new zzamz(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } : Set.class.isAssignableFrom(cls) ? new C33489(this) : Queue.class.isAssignableFrom(cls) ? new zzanx<T>(this) {
            final /* synthetic */ zzans beP;

            {
                this.beP = r1;
            }

            /* renamed from: a */
            public T mo4182a() {
                return new LinkedList();
            }
        } : new zzanx<T>(this) {
            final /* synthetic */ zzans beP;

            {
                this.beP = r1;
            }

            /* renamed from: a */
            public T mo4182a() {
                return new ArrayList();
            }
        } : Map.class.isAssignableFrom(cls) ? SortedMap.class.isAssignableFrom(cls) ? new zzanx<T>(this) {
            final /* synthetic */ zzans beP;

            {
                this.beP = r1;
            }

            /* renamed from: a */
            public T mo4182a() {
                return new TreeMap();
            }
        } : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(zzaoo.zzl(((ParameterizedType) type).getActualTypeArguments()[0]).m16036s())) ? new C33423(this) : new C33412(this) : null;
    }

    private <T> zzanx<T> zzd(final Type type, final Class<? super T> cls) {
        return new zzanx<T>(this) {
            final /* synthetic */ zzans beP;
            private final zzaoa beQ = zzaoa.m16002f();

            /* renamed from: a */
            public T mo4182a() {
                try {
                    return this.beQ.zzf(cls);
                } catch (Throwable e) {
                    String valueOf = String.valueOf(type);
                    throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + AVException.OBJECT_TOO_LARGE).append("Unable to invoke no-args constructor for ").append(valueOf).append(". ").append("Register an InstanceCreator with Gson for this type may fix this problem.").toString(), e);
                }
            }
        };
    }

    private <T> zzanx<T> zzl(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new zzanx<T>(this) {
                final /* synthetic */ zzans beP;

                /* renamed from: a */
                public T mo4182a() {
                    String valueOf;
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (Throwable e) {
                        valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e);
                    } catch (InvocationTargetException e2) {
                        valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public String toString() {
        return this.ben.toString();
    }

    public <T> zzanx<T> zzb(zzaoo<T> zzaoo) {
        final Type t = zzaoo.m16037t();
        Class s = zzaoo.m16036s();
        final zzamu zzamu = (zzamu) this.ben.get(t);
        if (zzamu != null) {
            return new zzanx<T>(this) {
                final /* synthetic */ zzans beP;

                /* renamed from: a */
                public T mo4182a() {
                    return zzamu.zza(t);
                }
            };
        }
        zzamu = (zzamu) this.ben.get(s);
        if (zzamu != null) {
            return new zzanx<T>(this) {
                final /* synthetic */ zzans beP;

                /* renamed from: a */
                public T mo4182a() {
                    return zzamu.zza(t);
                }
            };
        }
        zzanx<T> zzl = zzl(s);
        if (zzl != null) {
            return zzl;
        }
        zzl = zzc(t, s);
        return zzl == null ? zzd(t, s) : zzl;
    }
}

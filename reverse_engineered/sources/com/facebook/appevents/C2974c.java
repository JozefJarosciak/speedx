package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.C1472c;
import com.facebook.internal.C3048s;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

/* compiled from: AppEventStore */
/* renamed from: com.facebook.appevents.c */
class C2974c {
    /* renamed from: a */
    private static final String f13462a = C2974c.class.getName();

    /* compiled from: AppEventStore */
    /* renamed from: com.facebook.appevents.c$a */
    private static class C2973a extends ObjectInputStream {
        public C2973a(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
            ObjectStreamClass readClassDescriptor = super.readClassDescriptor();
            if (readClassDescriptor.getName().equals("com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1")) {
                return ObjectStreamClass.lookup(SerializationProxyV1.class);
            }
            if (readClassDescriptor.getName().equals("com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1")) {
                return ObjectStreamClass.lookup(SerializationProxyV1.class);
            }
            return readClassDescriptor;
        }
    }

    C2974c() {
    }

    /* renamed from: a */
    public static synchronized void m14442a(AccessTokenAppIdPair accessTokenAppIdPair, C2976e c2976e) {
        synchronized (C2974c.class) {
            C2974c.m14444b();
            PersistedEvents a = C2974c.m14441a();
            if (a.containsKey(accessTokenAppIdPair)) {
                a.get(accessTokenAppIdPair).addAll(c2976e.m14451b());
            } else {
                a.addEvents(accessTokenAppIdPair, c2976e.m14451b());
            }
            C2974c.m14443a(a);
        }
    }

    /* renamed from: a */
    public static synchronized PersistedEvents m14441a() {
        Closeable c2973a;
        PersistedEvents persistedEvents;
        Closeable closeable;
        Throwable e;
        Closeable closeable2 = null;
        synchronized (C2974c.class) {
            C2974c.m14444b();
            Context f = C1472c.f();
            try {
                c2973a = new C2973a(new BufferedInputStream(f.openFileInput("AppEventsLogger.persistedevents")));
                try {
                    persistedEvents = (PersistedEvents) c2973a.readObject();
                    C3048s.m14750a(c2973a);
                    try {
                        f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                    } catch (Throwable e2) {
                        Log.w(f13462a, "Got unexpected exception when removing events file: ", e2);
                    }
                } catch (FileNotFoundException e3) {
                    closeable = c2973a;
                    C3048s.m14750a(closeable);
                    try {
                        f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                        persistedEvents = null;
                    } catch (Throwable e4) {
                        Log.w(f13462a, "Got unexpected exception when removing events file: ", e4);
                        persistedEvents = null;
                    }
                    if (persistedEvents == null) {
                        persistedEvents = new PersistedEvents();
                    }
                    return persistedEvents;
                } catch (Exception e5) {
                    e4 = e5;
                    try {
                        Log.w(f13462a, "Got unexpected exception while reading events: ", e4);
                        C3048s.m14750a(c2973a);
                        try {
                            f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                            persistedEvents = null;
                        } catch (Throwable e42) {
                            Log.w(f13462a, "Got unexpected exception when removing events file: ", e42);
                            persistedEvents = null;
                        }
                        if (persistedEvents == null) {
                            persistedEvents = new PersistedEvents();
                        }
                        return persistedEvents;
                    } catch (Throwable th) {
                        e42 = th;
                        closeable2 = c2973a;
                        C3048s.m14750a(closeable2);
                        try {
                            f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                        } catch (Throwable e22) {
                            Log.w(f13462a, "Got unexpected exception when removing events file: ", e22);
                        }
                        throw e42;
                    }
                }
            } catch (FileNotFoundException e6) {
                closeable = null;
                C3048s.m14750a(closeable);
                f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                persistedEvents = null;
                if (persistedEvents == null) {
                    persistedEvents = new PersistedEvents();
                }
                return persistedEvents;
            } catch (Exception e7) {
                e42 = e7;
                c2973a = null;
                Log.w(f13462a, "Got unexpected exception while reading events: ", e42);
                C3048s.m14750a(c2973a);
                f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                persistedEvents = null;
                if (persistedEvents == null) {
                    persistedEvents = new PersistedEvents();
                }
                return persistedEvents;
            } catch (Throwable th2) {
                e42 = th2;
                C3048s.m14750a(closeable2);
                f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                throw e42;
            }
            if (persistedEvents == null) {
                persistedEvents = new PersistedEvents();
            }
        }
        return persistedEvents;
    }

    /* renamed from: a */
    private static void m14443a(PersistedEvents persistedEvents) {
        Throwable e;
        Context f = C1472c.f();
        Closeable objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(f.openFileOutput("AppEventsLogger.persistedevents", 0)));
            try {
                objectOutputStream.writeObject(persistedEvents);
                C3048s.m14750a(objectOutputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.w(f13462a, "Got unexpected exception while persisting events: ", e);
                    try {
                        f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                    } catch (Exception e3) {
                    }
                    C3048s.m14750a(objectOutputStream);
                } catch (Throwable th) {
                    e = th;
                    C3048s.m14750a(objectOutputStream);
                    throw e;
                }
            }
        } catch (Exception e4) {
            e = e4;
            objectOutputStream = null;
            Log.w(f13462a, "Got unexpected exception while persisting events: ", e);
            f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
            C3048s.m14750a(objectOutputStream);
        } catch (Throwable th2) {
            e = th2;
            objectOutputStream = null;
            C3048s.m14750a(objectOutputStream);
            throw e;
        }
    }

    /* renamed from: b */
    private static void m14444b() {
    }
}

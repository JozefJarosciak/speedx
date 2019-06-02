package retrofit;

import android.os.Build.VERSION;
import android.os.Process;
import com.google.gson.Gson;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import retrofit.RestAdapter.Log;
import retrofit.android.AndroidApacheClient;
import retrofit.android.AndroidLog;
import retrofit.android.MainThreadExecutor;
import retrofit.appengine.UrlFetchClient;
import retrofit.client.Client;
import retrofit.client.Client.Provider;
import retrofit.client.OkClient;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

abstract class Platform {
    static final boolean HAS_RX_JAVA = hasRxJavaOnClasspath();
    private static final Platform PLATFORM = findPlatform();

    private static class Android extends Platform {

        /* renamed from: retrofit.Platform$Android$2 */
        class C56752 implements ThreadFactory {
            C56752() {
            }

            public Thread newThread(final Runnable runnable) {
                return new Thread(new Runnable() {
                    public void run() {
                        Process.setThreadPriority(10);
                        runnable.run();
                    }
                }, "Retrofit-Idle");
            }
        }

        private Android() {
        }

        Converter defaultConverter() {
            return new GsonConverter(new Gson());
        }

        Provider defaultClient() {
            Client instantiate;
            if (Platform.hasOkHttpOnClasspath()) {
                instantiate = OkClientInstantiator.instantiate();
            } else if (VERSION.SDK_INT < 9) {
                instantiate = new AndroidApacheClient();
            } else {
                instantiate = new UrlConnectionClient();
            }
            return new Provider() {
                public Client get() {
                    return instantiate;
                }
            };
        }

        Executor defaultHttpExecutor() {
            return Executors.newCachedThreadPool(new C56752());
        }

        Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        Log defaultLog() {
            return new AndroidLog("Retrofit");
        }
    }

    private static class Base extends Platform {

        /* renamed from: retrofit.Platform$Base$2 */
        class C56792 implements ThreadFactory {
            C56792() {
            }

            public Thread newThread(final Runnable runnable) {
                return new Thread(new Runnable() {
                    public void run() {
                        Thread.currentThread().setPriority(1);
                        runnable.run();
                    }
                }, "Retrofit-Idle");
            }
        }

        /* renamed from: retrofit.Platform$Base$3 */
        class C56803 implements Log {
            C56803() {
            }

            public void log(String str) {
                System.out.println(str);
            }
        }

        private Base() {
        }

        Converter defaultConverter() {
            return new GsonConverter(new Gson());
        }

        Provider defaultClient() {
            Client instantiate;
            if (Platform.hasOkHttpOnClasspath()) {
                instantiate = OkClientInstantiator.instantiate();
            } else {
                instantiate = new UrlConnectionClient();
            }
            return new Provider() {
                public Client get() {
                    return instantiate;
                }
            };
        }

        Executor defaultHttpExecutor() {
            return Executors.newCachedThreadPool(new C56792());
        }

        Executor defaultCallbackExecutor() {
            return new SynchronousExecutor();
        }

        Log defaultLog() {
            return new C56803();
        }
    }

    private static class AppEngine extends Base {
        private AppEngine() {
            super();
        }

        Provider defaultClient() {
            final UrlFetchClient urlFetchClient = new UrlFetchClient();
            return new Provider() {
                public Client get() {
                    return urlFetchClient;
                }
            };
        }
    }

    private static class OkClientInstantiator {
        private OkClientInstantiator() {
        }

        static Client instantiate() {
            return new OkClient();
        }
    }

    abstract Executor defaultCallbackExecutor();

    abstract Provider defaultClient();

    abstract Converter defaultConverter();

    abstract Executor defaultHttpExecutor();

    abstract Log defaultLog();

    Platform() {
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException e) {
        }
        if (System.getProperty("com.google.appengine.runtime.version") != null) {
            return new AppEngine();
        }
        return new Base();
    }

    private static boolean hasOkHttpOnClasspath() {
        boolean z;
        boolean z2 = true;
        try {
            Class.forName("com.squareup.okhttp.OkUrlFactory");
            z = true;
        } catch (ClassNotFoundException e) {
            z = false;
        }
        try {
            Class.forName("com.squareup.okhttp.OkHttpClient");
        } catch (ClassNotFoundException e2) {
            z2 = false;
        }
        if (z2 == z) {
            return z2;
        }
        throw new RuntimeException("Retrofit detected an unsupported OkHttp on the classpath.\nTo use OkHttp with this version of Retrofit, you'll need:\n1. com.squareup.okhttp:okhttp:1.6.0 (or newer)\n2. com.squareup.okhttp:okhttp-urlconnection:1.6.0 (or newer)\nNote that OkHttp 2.0.0+ is supported!");
    }

    private static boolean hasRxJavaOnClasspath() {
        try {
            Class.forName("rx.a");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}

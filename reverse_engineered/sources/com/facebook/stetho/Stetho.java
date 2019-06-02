package com.facebook.stetho;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.DumpappHttpSocketLikeHandler;
import com.facebook.stetho.dumpapp.DumpappSocketLikeHandler;
import com.facebook.stetho.dumpapp.Dumper;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.plugins.CrashDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.FilesDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.SharedPreferencesDumperPlugin;
import com.facebook.stetho.inspector.DevtoolsSocketHandler;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.server.AddressNameHelper;
import com.facebook.stetho.server.LazySocketHandler;
import com.facebook.stetho.server.LocalSocketServer;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler.AlwaysMatchMatcher;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler.ExactMagicMatcher;
import com.facebook.stetho.server.ServerManager;
import com.facebook.stetho.server.SocketHandler;
import com.facebook.stetho.server.SocketHandlerFactory;
import com.facebook.stetho.server.SocketLikeHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Stetho {

    public static abstract class Initializer {
        private final Context mContext;

        private class RealSocketHandlerFactory implements SocketHandlerFactory {
            private RealSocketHandlerFactory() {
            }

            public SocketHandler create() {
                SocketHandler protocolDetectingSocketHandler = new ProtocolDetectingSocketHandler(Initializer.this.mContext);
                Iterable dumperPlugins = Initializer.this.getDumperPlugins();
                if (dumperPlugins != null) {
                    Dumper dumper = new Dumper(dumperPlugins);
                    protocolDetectingSocketHandler.addHandler(new ExactMagicMatcher(DumpappSocketLikeHandler.PROTOCOL_MAGIC), new DumpappSocketLikeHandler(dumper));
                    SocketLikeHandler dumpappHttpSocketLikeHandler = new DumpappHttpSocketLikeHandler(dumper);
                    protocolDetectingSocketHandler.addHandler(new ExactMagicMatcher("GET /dumpapp".getBytes()), dumpappHttpSocketLikeHandler);
                    protocolDetectingSocketHandler.addHandler(new ExactMagicMatcher("POST /dumpapp".getBytes()), dumpappHttpSocketLikeHandler);
                }
                dumperPlugins = Initializer.this.getInspectorModules();
                if (dumperPlugins != null) {
                    protocolDetectingSocketHandler.addHandler(new AlwaysMatchMatcher(), new DevtoolsSocketHandler(Initializer.this.mContext, dumperPlugins));
                }
                return protocolDetectingSocketHandler;
            }
        }

        protected abstract Iterable<DumperPlugin> getDumperPlugins();

        protected abstract Iterable<ChromeDevtoolsDomain> getInspectorModules();

        protected Initializer(Context context) {
            this.mContext = context.getApplicationContext();
        }

        final void start() {
            new ServerManager(new LocalSocketServer("main", AddressNameHelper.createCustomAddress("_devtools_remote"), new LazySocketHandler(new RealSocketHandlerFactory()))).start();
        }
    }

    private static class BuilderBasedInitializer extends Initializer {
        private final DumperPluginsProvider mDumperPlugins;
        private final InspectorModulesProvider mInspectorModules;

        private BuilderBasedInitializer(InitializerBuilder initializerBuilder) {
            super(initializerBuilder.mContext);
            this.mDumperPlugins = initializerBuilder.mDumperPlugins;
            this.mInspectorModules = initializerBuilder.mInspectorModules;
        }

        protected Iterable<DumperPlugin> getDumperPlugins() {
            return this.mDumperPlugins != null ? this.mDumperPlugins.get() : null;
        }

        protected Iterable<ChromeDevtoolsDomain> getInspectorModules() {
            return this.mInspectorModules != null ? this.mInspectorModules.get() : null;
        }
    }

    public static final class DefaultDumperPluginsBuilder {
        private final Context mContext;
        private final PluginBuilder<DumperPlugin> mDelegate = new PluginBuilder();

        public DefaultDumperPluginsBuilder(Context context) {
            this.mContext = context;
        }

        public DefaultDumperPluginsBuilder provide(DumperPlugin dumperPlugin) {
            this.mDelegate.provide(dumperPlugin.getName(), dumperPlugin);
            return this;
        }

        private DefaultDumperPluginsBuilder provideIfDesired(DumperPlugin dumperPlugin) {
            this.mDelegate.provideIfDesired(dumperPlugin.getName(), dumperPlugin);
            return this;
        }

        public DefaultDumperPluginsBuilder remove(String str) {
            this.mDelegate.remove(str);
            return this;
        }

        public Iterable<DumperPlugin> finish() {
            provideIfDesired(new HprofDumperPlugin(this.mContext));
            provideIfDesired(new SharedPreferencesDumperPlugin(this.mContext));
            provideIfDesired(new CrashDumperPlugin());
            provideIfDesired(new FilesDumperPlugin(this.mContext));
            return this.mDelegate.finish();
        }
    }

    public static class InitializerBuilder {
        final Context mContext;
        DumperPluginsProvider mDumperPlugins;
        InspectorModulesProvider mInspectorModules;

        private InitializerBuilder(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public InitializerBuilder enableDumpapp(DumperPluginsProvider dumperPluginsProvider) {
            this.mDumperPlugins = (DumperPluginsProvider) Util.throwIfNull(dumperPluginsProvider);
            return this;
        }

        public InitializerBuilder enableWebKitInspector(InspectorModulesProvider inspectorModulesProvider) {
            this.mInspectorModules = inspectorModulesProvider;
            return this;
        }

        public Initializer build() {
            return new BuilderBasedInitializer();
        }
    }

    private static class PluginBuilder<T> {
        private boolean mFinished;
        private final ArrayList<T> mPlugins;
        private final Set<String> mProvidedNames;
        private final Set<String> mRemovedNames;

        private PluginBuilder() {
            this.mProvidedNames = new HashSet();
            this.mRemovedNames = new HashSet();
            this.mPlugins = new ArrayList();
        }

        public void provide(String str, T t) {
            throwIfFinished();
            this.mPlugins.add(t);
            this.mProvidedNames.add(str);
        }

        public void provideIfDesired(String str, T t) {
            throwIfFinished();
            if (!this.mRemovedNames.contains(str) && this.mProvidedNames.add(str)) {
                this.mPlugins.add(t);
            }
        }

        public void remove(String str) {
            throwIfFinished();
            this.mRemovedNames.remove(str);
        }

        private void throwIfFinished() {
            if (this.mFinished) {
                throw new IllegalStateException("Must not continue to build after finish()");
            }
        }

        public Iterable<T> finish() {
            this.mFinished = true;
            return this.mPlugins;
        }
    }

    private Stetho() {
    }

    public static InitializerBuilder newInitializerBuilder(Context context) {
        return new InitializerBuilder(context);
    }

    public static void initializeWithDefaults(final Context context) {
        initialize(new Initializer(context) {
            protected Iterable<DumperPlugin> getDumperPlugins() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }

            protected Iterable<ChromeDevtoolsDomain> getInspectorModules() {
                return new Stetho$DefaultInspectorModulesBuilder(context).finish();
            }
        });
    }

    public static void initialize(Initializer initializer) {
        if (!ActivityTracker.get().beginTrackingIfPossible((Application) initializer.mContext.getApplicationContext())) {
            LogUtil.m15288w("Automatic activity tracking not available on this API level, caller must invoke ActivityTracker methods manually!");
        }
        initializer.start();
    }

    public static DumperPluginsProvider defaultDumperPluginsProvider(final Context context) {
        return new DumperPluginsProvider() {
            public Iterable<DumperPlugin> get() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }
        };
    }

    public static InspectorModulesProvider defaultInspectorModulesProvider(final Context context) {
        return new InspectorModulesProvider() {
            public Iterable<ChromeDevtoolsDomain> get() {
                return new Stetho$DefaultInspectorModulesBuilder(context).finish();
            }
        };
    }
}

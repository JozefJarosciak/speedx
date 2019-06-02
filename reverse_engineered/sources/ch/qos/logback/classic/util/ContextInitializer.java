package ch.qos.logback.classic.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.android.CommonPathUtil;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.Loader;
import java.io.InputStream;
import java.net.URL;

public class ContextInitializer {
    private static final String ASSETS_DIR = CommonPathUtil.getAssetsDirectoryPath();
    public static final String AUTOCONFIG_FILE = "logback.xml";
    public static final String CONFIG_FILE_PROPERTY = "logback.configurationFile";
    public static final String STATUS_LISTENER_CLASS = "logback.statusListenerClass";
    final ClassLoader classLoader = Loader.getClassLoaderOfObject(this);
    final LoggerContext loggerContext;

    public ContextInitializer(LoggerContext loggerContext) {
        this.loggerContext = loggerContext;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.URL findConfigFileFromSystemProperties(boolean r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0036 in list [B:12:0x002b]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r4 = this;
        r1 = 0;
        r0 = "logback.configurationFile";
        r2 = ch.qos.logback.core.util.OptionHelper.getSystemProperty(r0);
        if (r2 == 0) goto L_0x0061;
    L_0x0009:
        r0 = new java.io.File;	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        r0.<init>(r2);	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        r3 = r0.exists();	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        if (r3 == 0) goto L_0x0037;	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
    L_0x0014:
        r3 = r0.isFile();	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        if (r3 == 0) goto L_0x0037;	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
    L_0x001a:
        if (r5 == 0) goto L_0x0021;	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
    L_0x001c:
        r3 = r4.classLoader;	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        r4.statusOnResourceSearch(r2, r3, r2);	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
    L_0x0021:
        r0 = r0.toURI();	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        r0 = r0.toURL();	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
    L_0x0029:
        if (r5 == 0) goto L_0x0036;
    L_0x002b:
        r3 = r4.classLoader;
        if (r0 == 0) goto L_0x0033;
    L_0x002f:
        r1 = r0.toString();
    L_0x0033:
        r4.statusOnResourceSearch(r2, r3, r1);
    L_0x0036:
        return r0;
    L_0x0037:
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        r0.<init>(r2);	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        goto L_0x0029;
    L_0x003d:
        r0 = move-exception;
        r0 = r4.classLoader;	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        r0 = ch.qos.logback.core.util.Loader.getResource(r2, r0);	 Catch:{ MalformedURLException -> 0x003d, all -> 0x0065 }
        if (r0 == 0) goto L_0x0054;
    L_0x0046:
        if (r5 == 0) goto L_0x0036;
    L_0x0048:
        r3 = r4.classLoader;
        if (r0 == 0) goto L_0x0050;
    L_0x004c:
        r1 = r0.toString();
    L_0x0050:
        r4.statusOnResourceSearch(r2, r3, r1);
        goto L_0x0036;
    L_0x0054:
        if (r5 == 0) goto L_0x0061;
    L_0x0056:
        r3 = r4.classLoader;
        if (r0 == 0) goto L_0x0063;
    L_0x005a:
        r0 = r0.toString();
    L_0x005e:
        r4.statusOnResourceSearch(r2, r3, r0);
    L_0x0061:
        r0 = r1;
        goto L_0x0036;
    L_0x0063:
        r0 = r1;
        goto L_0x005e;
    L_0x0065:
        r0 = move-exception;
        if (r5 == 0) goto L_0x0073;
    L_0x0068:
        r3 = r4.classLoader;
        if (r1 == 0) goto L_0x0070;
    L_0x006c:
        r1 = r1.toString();
    L_0x0070:
        r4.statusOnResourceSearch(r2, r3, r1);
    L_0x0073:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.qos.logback.classic.util.ContextInitializer.findConfigFileFromSystemProperties(boolean):java.net.URL");
    }

    private InputStream findConfigFileURLFromAssets(boolean z) {
        return getResource(ASSETS_DIR + "/" + AUTOCONFIG_FILE, this.classLoader, z);
    }

    private InputStream getResource(String str, ClassLoader classLoader, boolean z) {
        InputStream resourceAsStream = classLoader.getResourceAsStream(str);
        if (z) {
            String str2 = null;
            if (resourceAsStream != null) {
                str2 = str;
            }
            statusOnResourceSearch(str, classLoader, str2);
        }
        return resourceAsStream;
    }

    private void statusOnResourceSearch(String str, ClassLoader classLoader, String str2) {
        StatusManager statusManager = this.loggerContext.getStatusManager();
        if (str2 == null) {
            statusManager.add(new InfoStatus("Could NOT find resource [" + str + "]", this.loggerContext));
        } else {
            statusManager.add(new InfoStatus("Found resource [" + str + "] at [" + str2 + "]", this.loggerContext));
        }
    }

    public void autoConfig() throws JoranException {
        StatusListenerConfigHelper.installIfAsked(this.loggerContext);
        boolean z = false;
        JoranConfigurator joranConfigurator = new JoranConfigurator();
        joranConfigurator.setContext(this.loggerContext);
        URL findConfigFileFromSystemProperties = findConfigFileFromSystemProperties(true);
        if (findConfigFileFromSystemProperties != null) {
            joranConfigurator.doConfigure(findConfigFileFromSystemProperties);
            z = true;
        }
        if (!z) {
            InputStream findConfigFileURLFromAssets = findConfigFileURLFromAssets(true);
            if (findConfigFileURLFromAssets != null) {
                joranConfigurator.doConfigure(findConfigFileURLFromAssets);
            }
        }
    }
}

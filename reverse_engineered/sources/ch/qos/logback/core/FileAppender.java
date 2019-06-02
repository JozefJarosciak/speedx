package ch.qos.logback.core;

import ch.qos.logback.core.recovery.ResilientFileOutputStream;
import ch.qos.logback.core.util.EnvUtil;
import ch.qos.logback.core.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class FileAppender<E> extends OutputStreamAppender<E> {
    protected boolean append = true;
    protected String fileName = null;
    private boolean initialized = false;
    private boolean lazyInit = false;
    private boolean prudent = false;

    private String getAbsoluteFilePath(String str) {
        return EnvUtil.isAndroidOS() ? FileUtil.prefixRelativePath(this.context.getProperty(CoreConstants.DATA_DIR_KEY), str) : str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void safeWrite(E r7) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000c in list [B:9:0x0026]
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
        r6 = this;
        r0 = r6.getOutputStream();
        r0 = (ch.qos.logback.core.recovery.ResilientFileOutputStream) r0;
        r0 = r0.getChannel();
        if (r0 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r1 = 0;
        r1 = r0.lock();	 Catch:{ all -> 0x002a }
        r2 = r0.position();	 Catch:{ all -> 0x002a }
        r4 = r0.size();	 Catch:{ all -> 0x002a }
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));	 Catch:{ all -> 0x002a }
        if (r2 == 0) goto L_0x0021;	 Catch:{ all -> 0x002a }
    L_0x001e:
        r0.position(r4);	 Catch:{ all -> 0x002a }
    L_0x0021:
        super.writeOut(r7);	 Catch:{ all -> 0x002a }
        if (r1 == 0) goto L_0x000c;
    L_0x0026:
        r1.release();
        goto L_0x000c;
    L_0x002a:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0030;
    L_0x002d:
        r1.release();
    L_0x0030:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.qos.logback.core.FileAppender.safeWrite(java.lang.Object):void");
    }

    public String getFile() {
        return this.fileName;
    }

    public boolean getLazy() {
        return this.lazyInit;
    }

    public boolean isAppend() {
        return this.append;
    }

    public boolean isPrudent() {
        return this.prudent;
    }

    protected boolean openFile(String str) throws IOException {
        String absoluteFilePath = getAbsoluteFilePath(str);
        synchronized (this.lock) {
            File file = new File(absoluteFilePath);
            if (FileUtil.isParentDirectoryCreationRequired(file) && !FileUtil.createMissingParentDirectories(file)) {
                addError("Failed to create parent directories for [" + file.getAbsolutePath() + "]");
            }
            OutputStream resilientFileOutputStream = new ResilientFileOutputStream(file, this.append);
            resilientFileOutputStream.setContext(this.context);
            setOutputStream(resilientFileOutputStream);
        }
        return true;
    }

    public final String rawFileProperty() {
        return this.fileName;
    }

    public void setAppend(boolean z) {
        this.append = z;
    }

    public void setFile(String str) {
        if (str == null) {
            this.fileName = null;
        } else {
            this.fileName = str.trim();
        }
    }

    public void setLazy(boolean z) {
        this.lazyInit = z;
    }

    public void setPrudent(boolean z) {
        this.prudent = z;
    }

    public void start() {
        boolean z = false;
        String file = getFile();
        if (file != null) {
            file = getAbsoluteFilePath(file);
            addInfo("File property is set to [" + file + "]");
            if (this.prudent && !isAppend()) {
                setAppend(true);
                addWarn("Setting \"Append\" property to true on account of \"Prudent\" mode");
            }
            if (this.lazyInit) {
                setOutputStream(new NOPOutputStream());
            } else {
                try {
                    openFile(file);
                } catch (Throwable e) {
                    addError("openFile(" + file + "," + this.append + ") failed", e);
                    z = true;
                }
            }
        } else {
            addError("\"File\" property not set for appender named [" + this.name + "]");
            z = true;
        }
        if (!z) {
            super.start();
        }
    }

    protected void subAppend(E e) {
        if (!this.initialized && this.lazyInit) {
            this.initialized = true;
            try {
                openFile(getFile());
            } catch (Throwable e2) {
                this.started = false;
                addError("openFile(" + this.fileName + "," + this.append + ") failed", e2);
            }
        }
        super.subAppend(e);
    }

    protected void writeOut(E e) throws IOException {
        if (this.prudent) {
            safeWrite(e);
        } else {
            super.writeOut(e);
        }
    }
}

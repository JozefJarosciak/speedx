package ch.qos.logback.core.recovery;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class ResilientFileOutputStream extends ResilientOutputStreamBase {
    private File file;
    private FileOutputStream fos;

    public ResilientFileOutputStream(File file, boolean z) throws FileNotFoundException {
        this.file = file;
        this.fos = new FileOutputStream(file, z);
        this.os = new BufferedOutputStream(this.fos);
        this.presumedClean = true;
    }

    public FileChannel getChannel() {
        return this.os == null ? null : this.fos.getChannel();
    }

    String getDescription() {
        return "file [" + this.file + "]";
    }

    public File getFile() {
        return this.file;
    }

    OutputStream openNewOutputStream() throws IOException {
        this.fos = new FileOutputStream(this.file, true);
        return new BufferedOutputStream(this.fos);
    }

    public String toString() {
        return "c.q.l.c.recovery.ResilientFileOutputStream@" + System.identityHashCode(this);
    }
}

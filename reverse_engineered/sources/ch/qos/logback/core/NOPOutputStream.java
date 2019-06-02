package ch.qos.logback.core;

import java.io.IOException;
import java.io.OutputStream;

public class NOPOutputStream extends OutputStream {
    public void write(int i) throws IOException {
    }
}

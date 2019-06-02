package retrofit;

import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

class ExceptionCatchingTypedInput implements TypedInput {
    private final TypedInput delegate;
    private final ExceptionCatchingInputStream delegateStream;

    private static class ExceptionCatchingInputStream extends InputStream {
        private final InputStream delegate;
        private IOException thrownException;

        ExceptionCatchingInputStream(InputStream inputStream) {
            this.delegate = inputStream;
        }

        public int read() throws IOException {
            try {
                return this.delegate.read();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public int read(byte[] bArr) throws IOException {
            try {
                return this.delegate.read(bArr);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            try {
                return this.delegate.read(bArr, i, i2);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public long skip(long j) throws IOException {
            try {
                return this.delegate.skip(j);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public int available() throws IOException {
            try {
                return this.delegate.available();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public void close() throws IOException {
            try {
                this.delegate.close();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public synchronized void mark(int i) {
            this.delegate.mark(i);
        }

        public synchronized void reset() throws IOException {
            try {
                this.delegate.reset();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public boolean markSupported() {
            return this.delegate.markSupported();
        }
    }

    ExceptionCatchingTypedInput(TypedInput typedInput) throws IOException {
        this.delegate = typedInput;
        this.delegateStream = new ExceptionCatchingInputStream(typedInput.in());
    }

    public String mimeType() {
        return this.delegate.mimeType();
    }

    public long length() {
        return this.delegate.length();
    }

    public InputStream in() throws IOException {
        return this.delegateStream;
    }

    IOException getThrownException() {
        return this.delegateStream.thrownException;
    }

    boolean threwException() {
        return this.delegateStream.thrownException != null;
    }
}

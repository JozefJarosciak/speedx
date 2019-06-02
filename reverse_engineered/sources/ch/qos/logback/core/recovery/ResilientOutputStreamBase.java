package ch.qos.logback.core.recovery;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusManager;
import java.io.IOException;
import java.io.OutputStream;

public abstract class ResilientOutputStreamBase extends OutputStream {
    static final int STATUS_COUNT_LIMIT = 8;
    private Context context;
    private int noContextWarning = 0;
    protected OutputStream os;
    protected boolean presumedClean = true;
    private RecoveryCoordinator recoveryCoordinator;
    private int statusCount = 0;

    private boolean isPresumedInError() {
        return (this.recoveryCoordinator == null || this.presumedClean) ? false : true;
    }

    private void postSuccessfulWrite() {
        if (this.recoveryCoordinator != null) {
            this.recoveryCoordinator = null;
            this.statusCount = 0;
            addStatus(new InfoStatus("Recovered from IO failure on " + getDescription(), this));
        }
    }

    public void addStatus(Status status) {
        if (this.context == null) {
            int i = this.noContextWarning;
            this.noContextWarning = i + 1;
            if (i == 0) {
                System.out.println("LOGBACK: No context given for " + this);
                return;
            }
            return;
        }
        StatusManager statusManager = this.context.getStatusManager();
        if (statusManager != null) {
            statusManager.add(status);
        }
    }

    void addStatusIfCountNotOverLimit(Status status) {
        this.statusCount++;
        if (this.statusCount < 8) {
            addStatus(status);
        }
        if (this.statusCount == 8) {
            addStatus(status);
            addStatus(new InfoStatus("Will supress future messages regarding " + getDescription(), this));
        }
    }

    void attemptRecovery() {
        try {
            close();
        } catch (IOException e) {
        }
        addStatusIfCountNotOverLimit(new InfoStatus("Attempting to recover from IO failure on " + getDescription(), this));
        try {
            this.os = openNewOutputStream();
            this.presumedClean = true;
        } catch (Throwable e2) {
            addStatusIfCountNotOverLimit(new ErrorStatus("Failed to open " + getDescription(), this, e2));
        }
    }

    public void close() throws IOException {
        if (this.os != null) {
            this.os.close();
        }
    }

    public void flush() {
        if (this.os != null) {
            try {
                this.os.flush();
                postSuccessfulWrite();
            } catch (IOException e) {
                postIOFailure(e);
            }
        }
    }

    public Context getContext() {
        return this.context;
    }

    abstract String getDescription();

    abstract OutputStream openNewOutputStream() throws IOException;

    void postIOFailure(IOException iOException) {
        addStatusIfCountNotOverLimit(new ErrorStatus("IO failure while writing to " + getDescription(), this, iOException));
        this.presumedClean = false;
        if (this.recoveryCoordinator == null) {
            this.recoveryCoordinator = new RecoveryCoordinator();
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void write(int i) {
        if (!isPresumedInError()) {
            try {
                this.os.write(i);
                postSuccessfulWrite();
            } catch (IOException e) {
                postIOFailure(e);
            }
        } else if (!this.recoveryCoordinator.isTooSoon()) {
            attemptRecovery();
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        if (!isPresumedInError()) {
            try {
                this.os.write(bArr, i, i2);
                postSuccessfulWrite();
            } catch (IOException e) {
                postIOFailure(e);
            }
        } else if (!this.recoveryCoordinator.isTooSoon()) {
            attemptRecovery();
        }
    }
}

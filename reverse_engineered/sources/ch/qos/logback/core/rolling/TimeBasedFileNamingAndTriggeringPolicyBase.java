package ch.qos.logback.core.rolling;

import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import ch.qos.logback.core.rolling.helper.RollingCalendar;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.File;
import java.util.Date;

public abstract class TimeBasedFileNamingAndTriggeringPolicyBase<E> extends ContextAwareBase implements TimeBasedFileNamingAndTriggeringPolicy<E> {
    protected ArchiveRemover archiveRemover = null;
    protected long artificialCurrentTime = -1;
    protected Date dateInCurrentPeriod = null;
    protected String elapsedPeriodsFileName;
    protected long nextCheck;
    protected RollingCalendar rc;
    protected boolean started = false;
    protected TimeBasedRollingPolicy<E> tbrp;

    protected void computeNextCheck() {
        this.nextCheck = this.rc.getNextTriggeringMillis(this.dateInCurrentPeriod);
    }

    public ArchiveRemover getArchiveRemover() {
        return this.archiveRemover;
    }

    public String getCurrentPeriodsFileNameWithoutCompressionSuffix() {
        return this.tbrp.fileNamePatternWCS.convert(this.dateInCurrentPeriod);
    }

    public long getCurrentTime() {
        return this.artificialCurrentTime >= 0 ? this.artificialCurrentTime : System.currentTimeMillis();
    }

    public String getElapsedPeriodsFileName() {
        return this.elapsedPeriodsFileName;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setCurrentTime(long j) {
        this.artificialCurrentTime = j;
    }

    protected void setDateInCurrentPeriod(long j) {
        this.dateInCurrentPeriod.setTime(j);
    }

    public void setDateInCurrentPeriod(Date date) {
        this.dateInCurrentPeriod = date;
    }

    public void setTimeBasedRollingPolicy(TimeBasedRollingPolicy<E> timeBasedRollingPolicy) {
        this.tbrp = timeBasedRollingPolicy;
    }

    public void start() {
        DateTokenConverter primaryDateTokenConverter = this.tbrp.fileNamePattern.getPrimaryDateTokenConverter();
        if (primaryDateTokenConverter == null) {
            throw new IllegalStateException("FileNamePattern [" + this.tbrp.fileNamePattern.getPattern() + "] does not contain a valid DateToken");
        }
        this.rc = new RollingCalendar();
        this.rc.init(primaryDateTokenConverter.getDatePattern());
        addInfo("The date pattern is '" + primaryDateTokenConverter.getDatePattern() + "' from file name pattern '" + this.tbrp.fileNamePattern.getPattern() + "'.");
        this.rc.printPeriodicity(this);
        setDateInCurrentPeriod(new Date(getCurrentTime()));
        if (this.tbrp.getParentsRawFileProperty() != null) {
            File file = new File(this.tbrp.getParentsRawFileProperty());
            if (file.exists() && file.canRead()) {
                setDateInCurrentPeriod(new Date(file.lastModified()));
            }
        }
        addInfo("Setting initial period to " + this.dateInCurrentPeriod);
        computeNextCheck();
    }

    public void stop() {
        this.started = false;
    }
}

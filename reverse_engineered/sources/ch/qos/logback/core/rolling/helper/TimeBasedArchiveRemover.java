package ch.qos.logback.core.rolling.helper;

import java.io.File;
import java.util.Date;

public class TimeBasedArchiveRemover extends DefaultArchiveRemover {
    public TimeBasedArchiveRemover(FileNamePattern fileNamePattern, RollingCalendar rollingCalendar) {
        super(fileNamePattern, rollingCalendar);
    }

    protected void cleanByPeriodOffset(Date date, int i) {
        File file = new File(this.fileNamePattern.convert(this.rc.getRelativeDate(date, i)));
        if (file.exists() && file.isFile()) {
            file.delete();
            addInfo("deleting " + file);
            if (this.parentClean) {
                removeFolderIfEmpty(file.getParentFile());
            }
        }
    }

    public String toString() {
        return "c.q.l.core.rolling.helper.TimeBasedArchiveRemover";
    }
}

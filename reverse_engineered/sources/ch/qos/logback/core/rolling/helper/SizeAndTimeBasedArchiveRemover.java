package ch.qos.logback.core.rolling.helper;

import java.io.File;
import java.util.Date;

public class SizeAndTimeBasedArchiveRemover extends DefaultArchiveRemover {
    public SizeAndTimeBasedArchiveRemover(FileNamePattern fileNamePattern, RollingCalendar rollingCalendar) {
        super(fileNamePattern, rollingCalendar);
    }

    public void cleanByPeriodOffset(Date date, int i) {
        int i2 = 0;
        String afterLastSlash = FileFilterUtil.afterLastSlash(this.fileNamePattern.toRegexForFixedDate(this.rc.getRelativeDate(date, i)));
        File parentFile = new File(this.fileNamePattern.convertMultipleArguments(r1, Integer.valueOf(0))).getAbsoluteFile().getAbsoluteFile().getParentFile();
        File[] filesInFolderMatchingStemRegex = FileFilterUtil.filesInFolderMatchingStemRegex(parentFile, afterLastSlash);
        int length = filesInFolderMatchingStemRegex.length;
        while (i2 < length) {
            filesInFolderMatchingStemRegex[i2].delete();
            i2++;
        }
        if (this.parentClean) {
            removeFolderIfEmpty(parentFile);
        }
    }
}

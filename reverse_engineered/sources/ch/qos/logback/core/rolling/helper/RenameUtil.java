package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.EnvUtil;
import ch.qos.logback.core.util.FileUtil;
import java.io.File;

public class RenameUtil extends ContextAwareBase {
    static String RENAMING_ERROR_URL = "http://logback.qos.ch/codes.html#renamingError";

    boolean areOnDifferentVolumes(File file, File file2) throws RolloverFailure {
        if (!EnvUtil.isJDK7OrHigher()) {
            return false;
        }
        try {
            return !FileStoreUtil.areOnSameFileStore(file, file2.getParentFile());
        } catch (Throwable e) {
            addWarn("Error while checking file store equality", e);
            return false;
        }
    }

    void createMissingTargetDirsIfNecessary(File file) throws RolloverFailure {
        if (FileUtil.isParentDirectoryCreationRequired(file) && !FileUtil.createMissingParentDirectories(file)) {
            throw new RolloverFailure("Failed to create parent directories for [" + file.getAbsolutePath() + "]");
        }
    }

    public void rename(String str, String str2) throws RolloverFailure {
        if (str.equals(str2)) {
            addWarn("Source and target files are the same [" + str + "]. Skipping.");
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            File file2 = new File(str2);
            createMissingTargetDirsIfNecessary(file2);
            addInfo("Renaming file [" + file + "] to [" + file2 + "]");
            if (!file.renameTo(file2)) {
                addWarn("Failed to rename file [" + file + "] as [" + file2 + "].");
                if (areOnDifferentVolumes(file, file2)) {
                    addWarn("Detected different file systems for source [" + str + "] and target [" + str2 + "]. Attempting rename by copying.");
                    renameByCopying(str, str2);
                    return;
                }
                addWarn("Please consider leaving the [file] option of " + RollingFileAppender.class.getSimpleName() + " empty.");
                addWarn("See also " + RENAMING_ERROR_URL);
                return;
            }
            return;
        }
        throw new RolloverFailure("File [" + str + "] does not exist.");
    }

    public void renameByCopying(String str, String str2) throws RolloverFailure {
        new FileUtil(getContext()).copy(str, str2);
        if (!new File(str).delete()) {
            addWarn("Could not delete " + str);
        }
    }

    public String toString() {
        return "c.q.l.co.rolling.helper.RenameUtil";
    }
}

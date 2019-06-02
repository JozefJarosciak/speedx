package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.WarnStatus;
import ch.qos.logback.core.util.FileUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compressor extends ContextAwareBase {
    static final int BUFFER_SIZE = 8192;
    final CompressionMode compressionMode;

    public Compressor(CompressionMode compressionMode) {
        this.compressionMode = compressionMode;
    }

    public static String computeFileNameStr_WCS(String str, CompressionMode compressionMode) {
        int length = str.length();
        switch (compressionMode) {
            case GZ:
                return str.endsWith(".gz") ? str.substring(0, length - 3) : str;
            case ZIP:
                return str.endsWith(".zip") ? str.substring(0, length - 4) : str;
            case NONE:
                return str;
            default:
                throw new IllegalStateException("Execution should not reach this point");
        }
    }

    private void gzCompress(String str, String str2) {
        BufferedInputStream bufferedInputStream;
        GZIPOutputStream gZIPOutputStream;
        Throwable e;
        BufferedInputStream bufferedInputStream2;
        GZIPOutputStream gZIPOutputStream2 = null;
        File file = new File(str);
        if (file.exists()) {
            if (!str2.endsWith(".gz")) {
                str2 = str2 + ".gz";
            }
            File file2 = new File(str2);
            if (file2.exists()) {
                addWarn("The target compressed file named [" + str2 + "] exist already. Aborting file compression.");
                return;
            }
            addInfo("GZ compressing [" + file + "] as [" + file2 + "]");
            createMissingTargetDirsIfNecessary(file2);
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                try {
                    gZIPOutputStream = new GZIPOutputStream(new FileOutputStream(str2));
                } catch (Exception e2) {
                    e = e2;
                    gZIPOutputStream = null;
                    bufferedInputStream2 = bufferedInputStream;
                    try {
                        addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (gZIPOutputStream == null) {
                            try {
                                gZIPOutputStream.close();
                                return;
                            } catch (IOException e4) {
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th) {
                        e = th;
                        bufferedInputStream = bufferedInputStream2;
                        gZIPOutputStream2 = gZIPOutputStream;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (gZIPOutputStream2 != null) {
                            try {
                                gZIPOutputStream2.close();
                            } catch (IOException e6) {
                            }
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (gZIPOutputStream2 != null) {
                        gZIPOutputStream2.close();
                    }
                    throw e;
                }
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        gZIPOutputStream.write(bArr, 0, read);
                    }
                    bufferedInputStream.close();
                    bufferedInputStream = null;
                    try {
                        gZIPOutputStream.close();
                        gZIPOutputStream = null;
                        if (!file.delete()) {
                            addStatus(new WarnStatus("Could not delete [" + str + "].", this));
                        }
                        if (null != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                        if (null != null) {
                            try {
                                gZIPOutputStream.close();
                                return;
                            } catch (IOException e8) {
                                return;
                            }
                        }
                        return;
                    } catch (Exception e9) {
                        e = e9;
                        addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (gZIPOutputStream == null) {
                            gZIPOutputStream.close();
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        e = th3;
                        bufferedInputStream = null;
                        gZIPOutputStream2 = gZIPOutputStream;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (gZIPOutputStream2 != null) {
                            gZIPOutputStream2.close();
                        }
                        throw e;
                    }
                } catch (Exception e10) {
                    e = e10;
                    bufferedInputStream2 = bufferedInputStream;
                    addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (gZIPOutputStream == null) {
                        gZIPOutputStream.close();
                        return;
                    }
                    return;
                } catch (Throwable th4) {
                    e = th4;
                    gZIPOutputStream2 = gZIPOutputStream;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (gZIPOutputStream2 != null) {
                        gZIPOutputStream2.close();
                    }
                    throw e;
                }
            } catch (Exception e11) {
                e = e11;
                gZIPOutputStream = null;
                addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (gZIPOutputStream == null) {
                    gZIPOutputStream.close();
                    return;
                }
                return;
            } catch (Throwable th5) {
                e = th5;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (gZIPOutputStream2 != null) {
                    gZIPOutputStream2.close();
                }
                throw e;
            }
        }
        addStatus(new WarnStatus("The file to compress named [" + str + "] does not exist.", this));
    }

    private void zipCompress(String str, String str2, String str3) {
        ZipOutputStream zipOutputStream;
        Throwable e;
        BufferedInputStream bufferedInputStream = null;
        File file = new File(str);
        if (!file.exists()) {
            addStatus(new WarnStatus("The file to compress named [" + str + "] does not exist.", this));
        } else if (str3 == null) {
            addStatus(new WarnStatus("The innerEntryName parameter cannot be null", this));
        } else {
            if (!str2.endsWith(".zip")) {
                str2 = str2 + ".zip";
            }
            File file2 = new File(str2);
            if (file2.exists()) {
                addStatus(new WarnStatus("The target compressed file named [" + str2 + "] exist already.", this));
                return;
            }
            addInfo("ZIP compressing [" + file + "] as [" + file2 + "]");
            createMissingTargetDirsIfNecessary(file2);
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(str));
                try {
                    zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
                } catch (Exception e2) {
                    e = e2;
                    zipOutputStream = null;
                    bufferedInputStream = bufferedInputStream2;
                    try {
                        addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (zipOutputStream == null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e4) {
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    zipOutputStream = null;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    throw e;
                }
                try {
                    zipOutputStream.putNextEntry(computeZipEntry(str3));
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = bufferedInputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    bufferedInputStream2.close();
                    bufferedInputStream2 = null;
                    try {
                        zipOutputStream.close();
                        zipOutputStream = null;
                        if (!file.delete()) {
                            addStatus(new WarnStatus("Could not delete [" + str + "].", this));
                        }
                        if (null != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e7) {
                            }
                        }
                        if (null != null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e8) {
                            }
                        }
                    } catch (Exception e9) {
                        e = e9;
                        addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (zipOutputStream == null) {
                            zipOutputStream.close();
                        }
                    }
                } catch (Exception e10) {
                    e = e10;
                    bufferedInputStream = bufferedInputStream2;
                    addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (zipOutputStream == null) {
                        zipOutputStream.close();
                    }
                } catch (Throwable th3) {
                    e = th3;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    throw e;
                }
            } catch (Exception e11) {
                e = e11;
                zipOutputStream = null;
                addStatus(new ErrorStatus("Error occurred while compressing [" + str + "] into [" + str2 + "].", this, e));
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (zipOutputStream == null) {
                    zipOutputStream.close();
                }
            } catch (Throwable th4) {
                e = th4;
                zipOutputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                throw e;
            }
        }
    }

    public void compress(String str, String str2, String str3) {
        switch (this.compressionMode) {
            case GZ:
                gzCompress(str, str2);
                return;
            case ZIP:
                zipCompress(str, str2, str3);
                return;
            case NONE:
                throw new UnsupportedOperationException("compress method called in NONE compression mode");
            default:
                return;
        }
    }

    ZipEntry computeZipEntry(File file) {
        return computeZipEntry(file.getName());
    }

    ZipEntry computeZipEntry(String str) {
        return new ZipEntry(computeFileNameStr_WCS(str, this.compressionMode));
    }

    void createMissingTargetDirsIfNecessary(File file) {
        if (!FileUtil.isParentDirectoryCreationRequired(file)) {
            return;
        }
        if (FileUtil.createMissingParentDirectories(file)) {
            addInfo("Created missing parent directories for [" + file.getAbsolutePath() + "]");
        } else {
            addError("Failed to create parent directories for [" + file.getAbsolutePath() + "]");
        }
    }

    public String toString() {
        return getClass().getName();
    }
}

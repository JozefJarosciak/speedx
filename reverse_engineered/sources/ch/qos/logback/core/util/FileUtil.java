package ch.qos.logback.core.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileUtil extends ContextAwareBase {
    static final int BUF_SIZE = 32768;

    public FileUtil(Context context) {
        setContext(context);
    }

    public static boolean createMissingParentDirectories(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            throw new IllegalStateException(file + " should not have a null parent");
        } else if (!parentFile.exists()) {
            return parentFile.mkdirs();
        } else {
            throw new IllegalStateException(file + " should not have existing parent directory");
        }
    }

    public static URL fileToURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected exception on file [" + file + "]", e);
        }
    }

    public static boolean isParentDirectoryCreationRequired(File file) {
        File parentFile = file.getParentFile();
        return (parentFile == null || parentFile.exists()) ? false : true;
    }

    public static String prefixRelativePath(String str, String str2) {
        return (str == null || OptionHelper.isEmpty(str.trim()) || new File(str2).isAbsolute()) ? str2 : str + "/" + str2;
    }

    public void copy(String str, String str2) throws RolloverFailure {
        BufferedOutputStream bufferedOutputStream;
        Throwable e;
        String str3;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2;
            BufferedInputStream bufferedInputStream3 = new BufferedInputStream(new FileInputStream(str));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            } catch (IOException e2) {
                e = e2;
                bufferedOutputStream = null;
                bufferedInputStream = bufferedInputStream3;
                try {
                    str3 = "Failed to copy [" + str + "] to [" + str2 + "]";
                    addError(str3, e);
                    throw new RolloverFailure(str3);
                } catch (Throwable th) {
                    e = th;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                bufferedOutputStream = null;
                bufferedInputStream = bufferedInputStream3;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw e;
            }
            try {
                byte[] bArr = new byte[32768];
                while (true) {
                    int read = bufferedInputStream3.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedInputStream3.close();
                bufferedInputStream2 = null;
            } catch (IOException e5) {
                e = e5;
                bufferedInputStream = bufferedInputStream3;
                str3 = "Failed to copy [" + str + "] to [" + str2 + "]";
                addError(str3, e);
                throw new RolloverFailure(str3);
            } catch (Throwable th3) {
                e = th3;
                bufferedInputStream = bufferedInputStream3;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw e;
            }
            try {
                bufferedOutputStream.close();
                bufferedOutputStream = null;
                if (null != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e6) {
                    }
                }
                if (null != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e7) {
                    }
                }
            } catch (IOException e8) {
                e = e8;
                str3 = "Failed to copy [" + str + "] to [" + str2 + "]";
                addError(str3, e);
                throw new RolloverFailure(str3);
            }
        } catch (IOException e9) {
            e = e9;
            bufferedOutputStream = null;
            str3 = "Failed to copy [" + str + "] to [" + str2 + "]";
            addError(str3, e);
            throw new RolloverFailure(str3);
        } catch (Throwable th4) {
            e = th4;
            bufferedOutputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw e;
        }
    }
}

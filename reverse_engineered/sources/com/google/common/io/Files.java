package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.TreeTraverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;

@Beta
public final class Files {
    private static final TreeTraverser<File> FILE_TREE_TRAVERSER = new C37922();
    private static final int TEMP_DIR_ATTEMPTS = 10000;

    /* renamed from: com.google.common.io.Files$1 */
    static class C37911 implements LineProcessor<List<String>> {
        final List<String> result = Lists.newArrayList();

        C37911() {
        }

        public boolean processLine(String str) {
            this.result.add(str);
            return true;
        }

        public List<String> getResult() {
            return this.result;
        }
    }

    /* renamed from: com.google.common.io.Files$2 */
    static class C37922 extends TreeTraverser<File> {
        C37922() {
        }

        public Iterable<File> children(File file) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    return Collections.unmodifiableList(Arrays.asList(listFiles));
                }
            }
            return Collections.emptyList();
        }

        public String toString() {
            return "Files.fileTreeTraverser()";
        }
    }

    private static final class FileByteSink extends ByteSink {
        private final File file;
        private final ImmutableSet<FileWriteMode> modes;

        private FileByteSink(File file, FileWriteMode... fileWriteModeArr) {
            this.file = (File) Preconditions.checkNotNull(file);
            this.modes = ImmutableSet.copyOf((Object[]) fileWriteModeArr);
        }

        public FileOutputStream openStream() throws IOException {
            return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.file));
            String valueOf2 = String.valueOf(String.valueOf(this.modes));
            return new StringBuilder((valueOf.length() + 20) + valueOf2.length()).append("Files.asByteSink(").append(valueOf).append(", ").append(valueOf2).append(")").toString();
        }
    }

    private static final class FileByteSource extends ByteSource {
        private final File file;

        private FileByteSource(File file) {
            this.file = (File) Preconditions.checkNotNull(file);
        }

        public FileInputStream openStream() throws IOException {
            return new FileInputStream(this.file);
        }

        public long size() throws IOException {
            if (this.file.isFile()) {
                return this.file.length();
            }
            throw new FileNotFoundException(this.file.toString());
        }

        public byte[] read() throws IOException {
            Closer create = Closer.create();
            try {
                FileInputStream fileInputStream = (FileInputStream) create.register(openStream());
                byte[] readFile = Files.readFile(fileInputStream, fileInputStream.getChannel().size());
                create.close();
                return readFile;
            } catch (Throwable th) {
                create.close();
            }
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.file));
            return new StringBuilder(valueOf.length() + 20).append("Files.asByteSource(").append(valueOf).append(")").toString();
        }
    }

    private enum FilePredicate implements Predicate<File> {
        IS_DIRECTORY {
            public boolean apply(File file) {
                return file.isDirectory();
            }

            public String toString() {
                return "Files.isDirectory()";
            }
        },
        IS_FILE {
            public boolean apply(File file) {
                return file.isFile();
            }

            public String toString() {
                return "Files.isFile()";
            }
        }
    }

    private Files() {
    }

    public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file);
    }

    static byte[] readFile(InputStream inputStream, long j) throws IOException {
        if (j <= 2147483647L) {
            return j == 0 ? ByteStreams.toByteArray(inputStream) : ByteStreams.toByteArray(inputStream, (int) j);
        } else {
            throw new OutOfMemoryError("file is too large to fit in a byte array: " + j + " bytes");
        }
    }

    public static ByteSink asByteSink(File file, FileWriteMode... fileWriteModeArr) {
        return new FileByteSink(file, fileWriteModeArr);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode... fileWriteModeArr) {
        return asByteSink(file, fileWriteModeArr).asCharSink(charset);
    }

    private static FileWriteMode[] modes(boolean z) {
        if (!z) {
            return new FileWriteMode[0];
        }
        return new FileWriteMode[]{FileWriteMode.APPEND};
    }

    public static byte[] toByteArray(File file) throws IOException {
        return asByteSource(file).read();
    }

    public static String toString(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).read();
    }

    public static void write(byte[] bArr, File file) throws IOException {
        asByteSink(file, new FileWriteMode[0]).write(bArr);
    }

    public static void copy(File file, OutputStream outputStream) throws IOException {
        asByteSource(file).copyTo(outputStream);
    }

    public static void copy(File file, File file2) throws IOException {
        boolean z;
        if (file.equals(file2)) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Source %s and destination %s must be different", file, file2);
        asByteSource(file).copyTo(asByteSink(file2, new FileWriteMode[0]));
    }

    public static void write(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, new FileWriteMode[0]).write(charSequence);
    }

    public static void append(CharSequence charSequence, File file, Charset charset) throws IOException {
        write(charSequence, file, charset, true);
    }

    private static void write(CharSequence charSequence, File file, Charset charset, boolean z) throws IOException {
        asCharSink(file, charset, modes(z)).write(charSequence);
    }

    public static void copy(File file, Charset charset, Appendable appendable) throws IOException {
        asCharSource(file, charset).copyTo(appendable);
    }

    public static boolean equal(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        if (file == file2 || file.equals(file2)) {
            return true;
        }
        long length = file.length();
        long length2 = file2.length();
        if (length == 0 || length2 == 0 || length == length2) {
            return asByteSource(file).contentEquals(asByteSource(file2));
        }
        return false;
    }

    public static File createTempDir() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        String str = System.currentTimeMillis() + HelpFormatter.DEFAULT_OPT_PREFIX;
        for (int i = 0; i < 10000; i++) {
            String valueOf = String.valueOf(String.valueOf(str));
            File file2 = new File(file, new StringBuilder(valueOf.length() + 11).append(valueOf).append(i).toString());
            if (file2.mkdir()) {
                return file2;
            }
        }
        String valueOf2 = String.valueOf(String.valueOf("Failed to create directory within 10000 attempts (tried "));
        String valueOf3 = String.valueOf(String.valueOf(str));
        str = String.valueOf(String.valueOf(str));
        throw new IllegalStateException(new StringBuilder(((valueOf2.length() + 17) + valueOf3.length()) + str.length()).append(valueOf2).append(valueOf3).append("0 to ").append(str).append(9999).append(")").toString());
    }

    public static void touch(File file) throws IOException {
        Preconditions.checkNotNull(file);
        if (!file.createNewFile() && !file.setLastModified(System.currentTimeMillis())) {
            String valueOf = String.valueOf(String.valueOf(file));
            throw new IOException(new StringBuilder(valueOf.length() + 38).append("Unable to update modification time of ").append(valueOf).toString());
        }
    }

    public static void createParentDirs(File file) throws IOException {
        Preconditions.checkNotNull(file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                String valueOf = String.valueOf(String.valueOf(file));
                throw new IOException(new StringBuilder(valueOf.length() + 39).append("Unable to create parent directories of ").append(valueOf).toString());
            }
        }
    }

    public static void move(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        if (!file.renameTo(file2)) {
            copy(file, file2);
            if (!file.delete()) {
                String valueOf;
                if (file2.delete()) {
                    valueOf = String.valueOf(String.valueOf(file));
                    throw new IOException(new StringBuilder(valueOf.length() + 17).append("Unable to delete ").append(valueOf).toString());
                } else {
                    valueOf = String.valueOf(String.valueOf(file2));
                    throw new IOException(new StringBuilder(valueOf.length() + 17).append("Unable to delete ").append(valueOf).toString());
                }
            }
        }
    }

    public static String readFirstLine(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).readFirstLine();
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        return (List) readLines(file, charset, new C37911());
    }

    public static <T> T readLines(File file, Charset charset, LineProcessor<T> lineProcessor) throws IOException {
        return asCharSource(file, charset).readLines(lineProcessor);
    }

    public static <T> T readBytes(File file, ByteProcessor<T> byteProcessor) throws IOException {
        return asByteSource(file).read(byteProcessor);
    }

    public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
        return asByteSource(file).hash(hashFunction);
    }

    public static MappedByteBuffer map(File file) throws IOException {
        Preconditions.checkNotNull(file);
        return map(file, MapMode.READ_ONLY);
    }

    public static MappedByteBuffer map(File file, MapMode mapMode) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mapMode);
        if (file.exists()) {
            return map(file, mapMode, file.length());
        }
        throw new FileNotFoundException(file.toString());
    }

    public static MappedByteBuffer map(File file, MapMode mapMode, long j) throws FileNotFoundException, IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mapMode);
        Closer create = Closer.create();
        try {
            MappedByteBuffer map = map((RandomAccessFile) create.register(new RandomAccessFile(file, mapMode == MapMode.READ_ONLY ? "r" : "rw")), mapMode, j);
            create.close();
            return map;
        } catch (Throwable th) {
            create.close();
        }
    }

    private static MappedByteBuffer map(RandomAccessFile randomAccessFile, MapMode mapMode, long j) throws IOException {
        Closer create = Closer.create();
        try {
            MappedByteBuffer map = ((FileChannel) create.register(randomAccessFile.getChannel())).map(mapMode, 0, j);
            create.close();
            return map;
        } catch (Throwable th) {
            create.close();
        }
    }

    public static String simplifyPath(String str) {
        Preconditions.checkNotNull(str);
        if (str.length() == 0) {
            return ".";
        }
        Iterable<String> split = Splitter.on('/').omitEmptyStrings().split(str);
        Iterable arrayList = new ArrayList();
        for (String str2 : split) {
            if (!str2.equals(".")) {
                if (!str2.equals("..")) {
                    arrayList.add(str2);
                } else if (arrayList.size() <= 0 || ((String) arrayList.get(arrayList.size() - 1)).equals("..")) {
                    arrayList.add("..");
                } else {
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }
        String str22 = Joiner.on('/').join(arrayList);
        if (str.charAt(0) == '/') {
            String str3 = "/";
            str22 = String.valueOf(str22);
            str22 = str22.length() != 0 ? str3.concat(str22) : new String(str3);
        }
        while (str22.startsWith("/../")) {
            str22 = str22.substring(3);
        }
        if (str22.equals("/..")) {
            return "/";
        }
        if ("".equals(str22)) {
            return ".";
        }
        return str22;
    }

    public static String getFileExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : name.substring(lastIndexOf + 1);
    }

    public static String getNameWithoutExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? name : name.substring(0, lastIndexOf);
    }

    public static TreeTraverser<File> fileTreeTraverser() {
        return FILE_TREE_TRAVERSER;
    }

    public static Predicate<File> isDirectory() {
        return FilePredicate.IS_DIRECTORY;
    }

    public static Predicate<File> isFile() {
        return FilePredicate.IS_FILE;
    }
}

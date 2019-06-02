package com.google.common.reflect;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableSortedSet$Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.Attributes.Name;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;

@Beta
public final class ClassPath {
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(" ").omitEmptyStrings();
    private static final Predicate<ClassInfo> IS_TOP_LEVEL = new C38101();
    private static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private final ImmutableSet<ResourceInfo> resources;

    /* renamed from: com.google.common.reflect.ClassPath$1 */
    static class C38101 implements Predicate<ClassInfo> {
        C38101() {
        }

        public boolean apply(ClassInfo classInfo) {
            return classInfo.className.indexOf(36) == -1;
        }
    }

    @Beta
    public static class ResourceInfo {
        final ClassLoader loader;
        private final String resourceName;

        static ResourceInfo of(String str, ClassLoader classLoader) {
            if (str.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION)) {
                return new ClassInfo(str, classLoader);
            }
            return new ResourceInfo(str, classLoader);
        }

        ResourceInfo(String str, ClassLoader classLoader) {
            this.resourceName = (String) Preconditions.checkNotNull(str);
            this.loader = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        public final URL url() {
            return (URL) Preconditions.checkNotNull(this.loader.getResource(this.resourceName), "Failed to load resource: %s", this.resourceName);
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResourceInfo)) {
                return false;
            }
            ResourceInfo resourceInfo = (ResourceInfo) obj;
            if (this.resourceName.equals(resourceInfo.resourceName) && this.loader == resourceInfo.loader) {
                return true;
            }
            return false;
        }

        public String toString() {
            return this.resourceName;
        }
    }

    @Beta
    public static final class ClassInfo extends ResourceInfo {
        private final String className;

        ClassInfo(String str, ClassLoader classLoader) {
            super(str, classLoader);
            this.className = ClassPath.getClassName(str);
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int lastIndexOf = this.className.lastIndexOf(36);
            if (lastIndexOf != -1) {
                return CharMatcher.DIGIT.trimLeadingFrom(this.className.substring(lastIndexOf + 1));
            }
            String packageName = getPackageName();
            if (packageName.isEmpty()) {
                return this.className;
            }
            return this.className.substring(packageName.length() + 1);
        }

        public String getName() {
            return this.className;
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }

        public String toString() {
            return this.className;
        }
    }

    @VisibleForTesting
    static final class Scanner {
        private final ImmutableSortedSet$Builder<ResourceInfo> resources = new ImmutableSortedSet$Builder(Ordering.usingToString());
        private final Set<URI> scannedUris = Sets.newHashSet();

        Scanner() {
        }

        ImmutableSortedSet<ResourceInfo> getResources() {
            return this.resources.build();
        }

        void scan(URI uri, ClassLoader classLoader) throws IOException {
            if (uri.getScheme().equals(Action.FILE_ATTRIBUTE) && this.scannedUris.add(uri)) {
                scanFrom(new File(uri), classLoader);
            }
        }

        @VisibleForTesting
        void scanFrom(File file, ClassLoader classLoader) throws IOException {
            if (!file.exists()) {
                return;
            }
            if (file.isDirectory()) {
                scanDirectory(file, classLoader);
            } else {
                scanJar(file, classLoader);
            }
        }

        private void scanDirectory(File file, ClassLoader classLoader) throws IOException {
            scanDirectory(file, classLoader, "", ImmutableSet.of());
        }

        private void scanDirectory(File file, ClassLoader classLoader, String str, ImmutableSet<File> immutableSet) throws IOException {
            Object canonicalFile = file.getCanonicalFile();
            if (!immutableSet.contains(canonicalFile)) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    Logger access$100 = ClassPath.logger;
                    String valueOf = String.valueOf(String.valueOf(file));
                    access$100.warning(new StringBuilder(valueOf.length() + 22).append("Cannot read directory ").append(valueOf).toString());
                    return;
                }
                ImmutableSet build = ImmutableSet.builder().addAll((Iterable) immutableSet).add(canonicalFile).build();
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    String valueOf2;
                    if (file2.isDirectory()) {
                        valueOf2 = String.valueOf(String.valueOf(str));
                        name = String.valueOf(String.valueOf(name));
                        scanDirectory(file2, classLoader, new StringBuilder((valueOf2.length() + 1) + name.length()).append(valueOf2).append(name).append("/").toString(), build);
                    } else {
                        valueOf2 = String.valueOf(str);
                        String valueOf3 = String.valueOf(name);
                        valueOf3 = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
                        if (!valueOf3.equals("META-INF/MANIFEST.MF")) {
                            this.resources.add(ResourceInfo.of(valueOf3, classLoader));
                        }
                    }
                }
            }
        }

        private void scanJar(File file, ClassLoader classLoader) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    Iterator it = getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        scan((URI) it.next(), classLoader);
                    }
                    Enumeration entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry jarEntry = (JarEntry) entries.nextElement();
                        if (!(jarEntry.isDirectory() || jarEntry.getName().equals("META-INF/MANIFEST.MF"))) {
                            this.resources.add(ResourceInfo.of(jarEntry.getName(), classLoader));
                        }
                    }
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
            }
        }

        @VisibleForTesting
        static ImmutableSet<URI> getClassPathFromManifest(File file, Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.of();
            }
            Builder builder = ImmutableSet.builder();
            CharSequence value = manifest.getMainAttributes().getValue(Name.CLASS_PATH.toString());
            if (value != null) {
                for (String str : ClassPath.CLASS_PATH_ATTRIBUTE_SEPARATOR.split(value)) {
                    String str2;
                    try {
                        builder.add(getClassPathEntry(file, str2));
                    } catch (URISyntaxException e) {
                        Logger access$100 = ClassPath.logger;
                        String str3 = "Invalid Class-Path entry: ";
                        str2 = String.valueOf(str2);
                        access$100.warning(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                    }
                }
            }
            return builder.build();
        }

        @VisibleForTesting
        static URI getClassPathEntry(File file, String str) throws URISyntaxException {
            URI uri = new URI(str);
            return uri.isAbsolute() ? uri : new File(file.getParentFile(), str.replace('/', File.separatorChar)).toURI();
        }
    }

    private ClassPath(ImmutableSet<ResourceInfo> immutableSet) {
        this.resources = immutableSet;
    }

    public static ClassPath from(ClassLoader classLoader) throws IOException {
        Scanner scanner = new Scanner();
        Iterator it = getClassPathEntries(classLoader).entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            scanner.scan((URI) entry.getKey(), (ClassLoader) entry.getValue());
        }
        return new ClassPath(scanner.getResources());
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(IS_TOP_LEVEL).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String str) {
        Preconditions.checkNotNull(str);
        Builder builder = ImmutableSet.builder();
        Iterator it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            Object obj = (ClassInfo) it.next();
            if (obj.getPackageName().equals(str)) {
                builder.add(obj);
            }
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String str) {
        Preconditions.checkNotNull(str);
        String valueOf = String.valueOf(String.valueOf(str));
        String stringBuilder = new StringBuilder(valueOf.length() + 1).append(valueOf).append(".").toString();
        Builder builder = ImmutableSet.builder();
        Iterator it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            Object obj = (ClassInfo) it.next();
            if (obj.getName().startsWith(stringBuilder)) {
                builder.add(obj);
            }
        }
        return builder.build();
    }

    @VisibleForTesting
    static ImmutableMap<URI, ClassLoader> getClassPathEntries(ClassLoader classLoader) {
        Map newLinkedHashMap = Maps.newLinkedHashMap();
        ClassLoader parent = classLoader.getParent();
        if (parent != null) {
            newLinkedHashMap.putAll(getClassPathEntries(parent));
        }
        if (classLoader instanceof URLClassLoader) {
            URL[] uRLs = ((URLClassLoader) classLoader).getURLs();
            int length = uRLs.length;
            int i = 0;
            while (i < length) {
                try {
                    URI toURI = uRLs[i].toURI();
                    if (!newLinkedHashMap.containsKey(toURI)) {
                        newLinkedHashMap.put(toURI, classLoader);
                    }
                    i++;
                } catch (Throwable e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return ImmutableMap.copyOf(newLinkedHashMap);
    }

    @VisibleForTesting
    static String getClassName(String str) {
        return str.substring(0, str.length() - CLASS_FILE_NAME_EXTENSION.length()).replace('/', CoreConstants.DOT);
    }
}

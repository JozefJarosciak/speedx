package com.avos.avoscloud;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import com.avos.avoscloud.AVOSCloud.StorageType;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class AVFile {
    private static final String ELDERMETADATAKEYFORIOSFIX = "metadata";
    private static final String ETAG_FILE_EXTENSION = ".tg";
    static final String FILE_NAME_KEY = "_name";
    private static final String FILE_SUM_KEY = "_checksum";
    private static final String THUMBNAIL_FMT = "?imageView/%d/w/%d/h/%d/q/%d/format/%s";
    private static String defaultMimeType = "application/octet-stream";
    private AVACL acl;
    private String bucket;
    private transient byte[] data;
    private boolean dirty;
    private transient AVDownloader downloader;
    private AVObject fileObject;
    private final HashMap<String, Object> metaData;
    private String name;
    private String objectId;
    private transient AVUploader uploader;
    private String url;

    @SuppressLint({"NewApi"})
    private static class AVDownloader extends AsyncTask<String, Integer, AVException> {
        private static final AsyncHttpClient client = new SyncHttpClient();
        private final GetDataCallback dataCallback;
        private String etag;
        private final AVFile parseFile;
        private final ProgressCallback progressCallback;
        private final AtomicInteger retries = new AtomicInteger(3);

        AVDownloader(AVFile aVFile, ProgressCallback progressCallback, GetDataCallback getDataCallback) {
            this.parseFile = aVFile;
            this.dataCallback = getDataCallback;
            this.progressCallback = progressCallback;
        }

        protected AVException doWork(String str) {
            final AVException[] aVExceptionArr = new AVException[1];
            final String access$100 = this.parseFile.fetchCacheTag();
            if (!AVUtils.isBlankString(access$100)) {
                client.addHeader("If-None-Match", access$100);
            }
            final String str2 = str;
            client.get(str, new BinaryHttpResponseHandler(new String[]{".*"}) {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    int i2;
                    String access$300 = AVDownloader.this.parseFile.getFileSum();
                    if (i == HttpStatus.SC_NOT_MODIFIED) {
                        byte[] access$400 = AVDownloader.this.parseFile.fetchCacheData();
                        if (access$400 != null && access$400.length > 0) {
                            AVDownloader.this.parseFile.data = access$400;
                            aVExceptionArr[0] = null;
                        } else if (AVDownloader.this.retries.decrementAndGet() > 0) {
                            AVDownloader.this.parseFile.removeCacheTag();
                            AVDownloader.this.doWork(str2);
                            return;
                        }
                    } else if (i == 200) {
                        AVDownloader.this.parseFile.data = bArr;
                    }
                    String computeMD5 = AVUtils.computeMD5(AVDownloader.this.parseFile.data);
                    if (access$300 == null || access$300.equals(computeMD5)) {
                        i2 = 0;
                    } else {
                        i2 = 1;
                    }
                    if (i2 != 0) {
                        AVDownloader.this.parseFile.data = null;
                        aVExceptionArr[0] = AVErrorUtils.fileDownloadInConsistentFailureException();
                    }
                    if (i2 == 0 && !AVUtils.isBlankString(access$100)) {
                        AVDownloader.this.parseFile.cacheFile(bArr, access$100);
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    aVExceptionArr[0] = new AVException(th);
                }

                public void onProgress(int i, int i2) {
                    AVDownloader.this.publishProgress(new Integer[]{Integer.valueOf((int) (((float) (i * 98)) / ((float) i2)))});
                }
            });
            publishProgress(new Integer[]{Integer.valueOf(100)});
            return aVExceptionArr[0] != null ? aVExceptionArr[0] : null;
        }

        protected AVException doInBackground(String... strArr) {
            return doWork(strArr[0]);
        }

        protected void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            if (this.progressCallback != null) {
                this.progressCallback.internalDone(numArr[0], null);
            }
        }

        protected void onPostExecute(AVException aVException) {
            super.onPostExecute(aVException);
            if (this.dataCallback != null) {
                this.dataCallback.internalDone(this.parseFile.data, aVException);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onCancelled() {
            super.onCancelled();
            log.m3514d("download cancel, file downloaded length:" + this.parseFile.localPath().length());
            this.parseFile.localPath().delete();
            this.parseFile.data = null;
        }
    }

    public AVFile() {
        this.metaData = new HashMap();
        if (PaasClient.storageInstance().getDefaultACL() != null) {
            this.acl = new AVACL(PaasClient.storageInstance().getDefaultACL());
        }
    }

    AVObject getFileObject() {
        if (this.fileObject == null && !AVUtils.isBlankString(this.objectId)) {
            this.fileObject = AVObject.createWithoutData("_File", this.objectId);
        }
        return this.fileObject;
    }

    @Deprecated
    public AVFile(byte[] bArr) {
        this(null, bArr);
    }

    public AVFile(String str, String str2, Map<String, Object> map) {
        this();
        this.name = str;
        this.url = str2;
        if (map != null) {
            this.metaData.putAll(map);
        }
        this.metaData.put("__source", "external");
    }

    public AVFile(String str, byte[] bArr) {
        this();
        this.dirty = true;
        this.name = str;
        this.data = bArr;
        this.metaData.put("size", Integer.valueOf(bArr != null ? bArr.length : 0));
        if (bArr != null) {
            this.metaData.put(FILE_SUM_KEY, AVUtils.computeMD5(bArr));
        }
        AVUser currentUser = AVUser.getCurrentUser();
        this.metaData.put("owner", currentUser != null ? currentUser.getObjectId() : "");
        this.metaData.put(FILE_NAME_KEY, str);
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    @Deprecated
    public static void parseFileWithObjectIdInBackground(String str, GetFileCallback<AVFile> getFileCallback) {
        withObjectIdInBackground(str, getFileCallback);
    }

    public static void withObjectIdInBackground(final String str, final GetFileCallback<AVFile> getFileCallback) {
        new AVQuery("_File").getInBackground(str, new GetCallback<AVObject>() {
            public void done(AVObject aVObject, AVException aVException) {
                if (aVException != null) {
                    getFileCallback.internalDone(null, aVException);
                } else if (aVObject == null || AVUtils.isBlankString(aVObject.getObjectId())) {
                    getFileCallback.internalDone(null, new AVException(101, "Could not find file object by id:" + str));
                } else {
                    AVFile access$000 = AVFile.createFileFromAVObject(aVObject);
                    if (getFileCallback != null) {
                        getFileCallback.internalDone(access$000, null);
                    }
                }
            }
        });
    }

    @Deprecated
    public static AVFile parseFileWithObjectId(String str) throws AVException, FileNotFoundException {
        return withObjectId(str);
    }

    public static AVFile withObjectId(String str) throws AVException, FileNotFoundException {
        AVObject aVObject = new AVQuery("_File").get(str);
        if (aVObject != null && !AVUtils.isBlankString(aVObject.getObjectId())) {
            return createFileFromAVObject(aVObject);
        }
        throw new FileNotFoundException("Could not find file object by id:" + str);
    }

    @Deprecated
    public static AVFile parseFileWithAVObject(AVObject aVObject) {
        return withAVObject(aVObject);
    }

    public static AVFile withAVObject(AVObject aVObject) {
        if (aVObject != null && !AVUtils.isBlankString(aVObject.getObjectId())) {
            return createFileFromAVObject(aVObject);
        }
        throw new IllegalArgumentException("Invalid AVObject.");
    }

    private static AVFile createFileFromAVObject(AVObject aVObject) {
        AVFile aVFile = new AVFile(aVObject.getObjectId(), aVObject.getString("url"));
        if (!(aVObject.getMap(ELDERMETADATAKEYFORIOSFIX) == null || aVObject.getMap(ELDERMETADATAKEYFORIOSFIX).isEmpty())) {
            aVFile.metaData.putAll(aVObject.getMap(ELDERMETADATAKEYFORIOSFIX));
        }
        if (aVObject.getMap("metaData") != null) {
            aVFile.metaData.putAll(aVObject.getMap("metaData"));
        }
        aVFile.setObjectId(aVObject.getObjectId());
        aVFile.fileObject = aVObject;
        aVFile.setBucket((String) aVObject.get("bucket"));
        if (!aVFile.metaData.containsKey(FILE_NAME_KEY)) {
            aVFile.metaData.put(FILE_NAME_KEY, aVObject.getString("name"));
        }
        return aVFile;
    }

    @Deprecated
    public static AVFile parseFileWithAbsoluteLocalPath(String str, String str2) throws Exception {
        return withAbsoluteLocalPath(str, str2);
    }

    public static AVFile withAbsoluteLocalPath(String str, String str2) throws FileNotFoundException, IOException {
        return withFile(str, new File(str2));
    }

    @Deprecated
    public static AVFile parseFileWithFile(String str, File file) throws FileNotFoundException, IOException {
        return withFile(str, file);
    }

    public static AVFile withFile(String str, File file) throws FileNotFoundException, IOException {
        Throwable th;
        if (file == null) {
            throw new IllegalArgumentException("null file object.");
        } else if (file.exists() && file.isFile()) {
            try {
                byte[] bArr = new byte[((int) file.length())];
                int i = 0;
                InputStream bufferedInputStream;
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
                    while (i < bArr.length) {
                        try {
                            int read = bufferedInputStream.read(bArr, i, bArr.length - i);
                            if (read > 0) {
                                i += read;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    bufferedInputStream.close();
                    return new AVFile(str, bArr);
                } catch (Throwable th3) {
                    th = th3;
                    bufferedInputStream = null;
                    bufferedInputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e2) {
                throw e2;
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public HashMap<String, Object> getMetaData() {
        return this.metaData;
    }

    public Object addMetaData(String str, Object obj) {
        return this.metaData.put(str, obj);
    }

    public Object getMetaData(String str) {
        return this.metaData.get(str);
    }

    public int getSize() {
        Number number = (Number) getMetaData("size");
        if (number != null) {
            return number.intValue();
        }
        return -1;
    }

    public String getOwnerObjectId() {
        return (String) getMetaData("owner");
    }

    public Object removeMetaData(String str) {
        return this.metaData.remove(str);
    }

    public void clearMetaData() {
        this.metaData.clear();
    }

    protected AVFile(String str, String str2) {
        this.metaData = new HashMap();
        this.dirty = false;
        this.name = str;
        this.url = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getOriginalName() {
        return (String) this.metaData.get(FILE_NAME_KEY);
    }

    void setName(String str) {
        this.name = str;
    }

    public static String getMimeType(String str) {
        String str2 = defaultMimeType;
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        if (str2 == null) {
            return defaultMimeType;
        }
        return str2;
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public boolean isDataAvailable() {
        return this.data != null;
    }

    public String getUrl() {
        return this.url;
    }

    public String getThumbnailUrl(boolean z, int i, int i2) {
        return getThumbnailUrl(z, i, i2, 100, "png");
    }

    public String getThumbnailUrl(boolean z, int i, int i2, int i3, String str) {
        if (AVOSCloud.getStorageType() != StorageType.StorageTypeQiniu) {
            throw new UnsupportedOperationException("We only support this method for qiniu storage.");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Invalid width or height.");
        } else if (i3 < 1 || i3 > 100) {
            throw new IllegalArgumentException("Invalid quality,valid range is 0-100.");
        } else {
            int i4;
            if (str == null || AVUtils.isBlankString(str.trim())) {
                str = "png";
            }
            if (z) {
                i4 = 2;
            } else {
                i4 = 1;
            }
            return getUrl() + String.format(THUMBNAIL_FMT, new Object[]{Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), str});
        }
    }

    void setUrl(String str) {
        this.url = str;
    }

    public void save() throws AVException {
        cancelUploadIfNeed();
        this.uploader = getUploader(null, null);
        AVException doWork = this.uploader.doWork();
        if (doWork != null) {
            throw doWork;
        }
    }

    public synchronized void saveInBackground(SaveCallback saveCallback, ProgressCallback progressCallback) {
        cancelUploadIfNeed();
        try {
            this.uploader = getUploader(saveCallback, progressCallback);
        } catch (AVException e) {
            saveCallback.internalDone(e);
        }
        this.uploader.execute();
    }

    public void saveInBackground(SaveCallback saveCallback) {
        saveInBackground(saveCallback, null);
    }

    public void saveInBackground() {
        saveInBackground(null);
    }

    public byte[] getData() throws AVException {
        if (this.data != null) {
            return this.data;
        }
        if (this.url == null) {
            return null;
        }
        if (!AVUtils.isConnected(AVOSCloud.applicationContext)) {
            byte[] fetchCacheData = fetchCacheData();
            if (fetchCacheData != null && fetchCacheData.length > 0) {
                return fetchCacheData;
            }
        }
        cancelDownloadIfNeed();
        this.downloader = new AVDownloader(this, null, null);
        AVException doWork = this.downloader.doWork(getUrl());
        if (doWork == null) {
            return this.data;
        }
        throw doWork;
    }

    public void getDataInBackground(GetDataCallback getDataCallback, ProgressCallback progressCallback) {
        if (this.data != null) {
            if (getDataCallback != null) {
                getDataCallback.internalDone(this.data, null);
            }
        } else if (this.url != null) {
            cancelDownloadIfNeed();
            if (!AVUtils.isConnected(AVOSCloud.applicationContext)) {
                byte[] fetchCacheData = fetchCacheData();
                if (fetchCacheData != null && fetchCacheData.length > 0) {
                    getDataCallback.internalDone(null);
                    return;
                }
            }
            this.downloader = new AVDownloader(this, progressCallback, getDataCallback);
            this.downloader.execute(new String[]{getUrl()});
        } else if (getDataCallback != null) {
            getDataCallback.internalDone(new AVException(126, ""));
        }
    }

    public void getDataInBackground(GetDataCallback getDataCallback) {
        getDataInBackground(getDataCallback, null);
    }

    public void cancel() {
        cancelDownloadIfNeed();
        cancelUploadIfNeed();
    }

    private void cancelDownloadIfNeed() {
        if (this.downloader != null) {
            this.downloader.cancel(true);
        }
    }

    private void cancelUploadIfNeed() {
        if (this.uploader != null) {
            this.uploader.cancel(true);
        }
    }

    void handleUploadedResponse(String str, String str2, String str3) {
        this.dirty = false;
        this.objectId = str;
        this.fileObject = AVObject.createWithoutData("_File", str);
        this.name = str2;
        this.url = str3;
    }

    public void delete() throws AVException {
        if (getFileObject() != null) {
            getFileObject().delete();
            return;
        }
        throw AVErrorUtils.createException(153, "File object is not exists.");
    }

    public void deleteEventually() {
        if (getFileObject() != null) {
            getFileObject().deleteEventually();
        }
    }

    public void deleteEventually(DeleteCallback deleteCallback) {
        if (getFileObject() != null) {
            getFileObject().deleteEventually(deleteCallback);
        }
    }

    public void deleteInBackground() {
        if (getFileObject() != null) {
            getFileObject().deleteInBackground();
        }
    }

    public void deleteInBackground(DeleteCallback deleteCallback) {
        if (getFileObject() != null) {
            getFileObject().deleteInBackground(deleteCallback);
        } else {
            deleteCallback.internalDone(null, AVErrorUtils.createException(153, "File object is not exists."));
        }
    }

    String mimeType() {
        if (!AVUtils.isBlankString(this.name)) {
            return getMimeType(this.name);
        }
        if (AVUtils.isBlankString(this.url)) {
            return defaultMimeType;
        }
        return getMimeType(this.url);
    }

    static String className() {
        return "File";
    }

    private File localPath() {
        if (this.url == null) {
            return null;
        }
        return new File(AVOSCloud.applicationContext.getFilesDir(), AVUtils.md5(this.url));
    }

    public AVUploader getUploader(SaveCallback saveCallback, ProgressCallback progressCallback) throws AVException {
        if (this.objectId != null || AVUtils.isBlankString(this.url)) {
            switch (AVOSCloud.getStorageType()) {
                case StorageTypeQiniu:
                    return new QiniuUploader(this, saveCallback, progressCallback);
                case StorageTypeS3:
                    return new S3Uploader(this, saveCallback, progressCallback);
                default:
                    log.m3518e();
                    return null;
            }
        } else if (URLUtil.isValidUrl(this.url)) {
            return new UrlDirectlyUploader(this, saveCallback, progressCallback);
        } else {
            throw new AVException(126, "Invalid File URL");
        }
    }

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String str) {
        this.bucket = str;
    }

    private void cacheFile(byte[] bArr, String str) {
        Closeable fileOutputStream;
        Throwable th;
        Throwable th2;
        Closeable closeable = null;
        if (!AVUtils.isBlankString(this.url) && !AVUtils.isBlankString(str)) {
            Closeable fileOutputStream2;
            try {
                fileOutputStream2 = new FileOutputStream(new File(AVOSCloud.applicationContext.getFilesDir(), AVUtils.md5(this.url) + ETAG_FILE_EXTENSION));
                try {
                    fileOutputStream2.write(str.getBytes("UTF-8"));
                    fileOutputStream2.flush();
                    fileOutputStream = new FileOutputStream(localPath());
                    try {
                        fileOutputStream.write(bArr, 0, bArr.length);
                        fileOutputStream.flush();
                        AVPersistenceUtils.closeQuietly(fileOutputStream2);
                        AVPersistenceUtils.closeQuietly(fileOutputStream);
                    } catch (Exception e) {
                        closeable = fileOutputStream2;
                        try {
                            if (AVOSCloud.showInternalDebugLog()) {
                                avlog.m3506d("AVFile cache failure");
                            }
                            AVPersistenceUtils.closeQuietly(closeable);
                            AVPersistenceUtils.closeQuietly(fileOutputStream);
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream2 = closeable;
                            closeable = fileOutputStream;
                            th2 = th;
                            AVPersistenceUtils.closeQuietly(fileOutputStream2);
                            AVPersistenceUtils.closeQuietly(closeable);
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        closeable = fileOutputStream;
                        th2 = th;
                        AVPersistenceUtils.closeQuietly(fileOutputStream2);
                        AVPersistenceUtils.closeQuietly(closeable);
                        throw th2;
                    }
                } catch (Exception e2) {
                    fileOutputStream = null;
                    closeable = fileOutputStream2;
                    if (AVOSCloud.showInternalDebugLog()) {
                        avlog.m3506d("AVFile cache failure");
                    }
                    AVPersistenceUtils.closeQuietly(closeable);
                    AVPersistenceUtils.closeQuietly(fileOutputStream);
                } catch (Throwable th5) {
                    th2 = th5;
                    AVPersistenceUtils.closeQuietly(fileOutputStream2);
                    AVPersistenceUtils.closeQuietly(closeable);
                    throw th2;
                }
            } catch (Exception e3) {
                fileOutputStream = null;
                if (AVOSCloud.showInternalDebugLog()) {
                    avlog.m3506d("AVFile cache failure");
                }
                AVPersistenceUtils.closeQuietly(closeable);
                AVPersistenceUtils.closeQuietly(fileOutputStream);
            } catch (Throwable th6) {
                th2 = th6;
                fileOutputStream2 = null;
                AVPersistenceUtils.closeQuietly(fileOutputStream2);
                AVPersistenceUtils.closeQuietly(closeable);
                throw th2;
            }
        }
    }

    private byte[] fetchCacheData() {
        Closeable fileInputStream;
        Closeable byteArrayOutputStream;
        Throwable th;
        byte[] bArr = null;
        try {
            fileInputStream = new FileInputStream(localPath());
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e) {
                byteArrayOutputStream = bArr;
                AVPersistenceUtils.closeQuietly(byteArrayOutputStream);
                AVPersistenceUtils.closeQuietly(fileInputStream);
                return bArr;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                byteArrayOutputStream = bArr;
                th = th3;
                AVPersistenceUtils.closeQuietly(byteArrayOutputStream);
                AVPersistenceUtils.closeQuietly(fileInputStream);
                throw th;
            }
            try {
                byte[] bArr2 = new byte[8192];
                long j = 0;
                while (true) {
                    int read = fileInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    j += (long) read;
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                byteArrayOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
                AVPersistenceUtils.closeQuietly(byteArrayOutputStream);
                AVPersistenceUtils.closeQuietly(fileInputStream);
            } catch (Exception e2) {
                AVPersistenceUtils.closeQuietly(byteArrayOutputStream);
                AVPersistenceUtils.closeQuietly(fileInputStream);
                return bArr;
            } catch (Throwable th4) {
                th = th4;
                AVPersistenceUtils.closeQuietly(byteArrayOutputStream);
                AVPersistenceUtils.closeQuietly(fileInputStream);
                throw th;
            }
        } catch (Exception e3) {
            byteArrayOutputStream = bArr;
            fileInputStream = bArr;
            AVPersistenceUtils.closeQuietly(byteArrayOutputStream);
            AVPersistenceUtils.closeQuietly(fileInputStream);
            return bArr;
        } catch (Throwable th22) {
            fileInputStream = bArr;
            byte[] bArr3 = bArr;
            th = th22;
            byteArrayOutputStream = bArr3;
            AVPersistenceUtils.closeQuietly(byteArrayOutputStream);
            AVPersistenceUtils.closeQuietly(fileInputStream);
            throw th;
        }
        return bArr;
    }

    private String fetchCacheTag() {
        Scanner scanner;
        Throwable th;
        String str = null;
        if (!AVUtils.isBlankString(this.url)) {
            try {
                File file = new File(AVOSCloud.applicationContext.getFilesDir(), AVUtils.md5(this.url) + ETAG_FILE_EXTENSION);
                if (file.exists()) {
                    scanner = new Scanner(file);
                    try {
                        str = scanner.next();
                        if (scanner != null) {
                            scanner.close();
                        }
                    } catch (Exception e) {
                        try {
                            if (AVOSCloud.showInternalDebugLog()) {
                                avlog.m3506d("read etag exception");
                            }
                            if (scanner != null) {
                                scanner.close();
                            }
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            if (scanner != null) {
                                scanner.close();
                            }
                            throw th;
                        }
                    }
                } else if (str != null) {
                    str.close();
                }
            } catch (Exception e2) {
                scanner = str;
                if (AVOSCloud.showInternalDebugLog()) {
                    avlog.m3506d("read etag exception");
                }
                if (scanner != null) {
                    scanner.close();
                }
                return str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                scanner = str;
                th = th4;
                if (scanner != null) {
                    scanner.close();
                }
                throw th;
            }
        }
        return str;
    }

    private boolean removeCacheTag() {
        if (!AVUtils.isBlankString(this.url)) {
            try {
                File file = new File(AVOSCloud.applicationContext.getFilesDir(), AVUtils.md5(this.url) + ETAG_FILE_EXTENSION);
                if (file.exists()) {
                    return file.delete();
                }
            } catch (Exception e) {
                if (AVOSCloud.showInternalDebugLog()) {
                    avlog.m3506d("remove etag exception");
                }
                return false;
            }
        }
        return true;
    }

    protected AVACL getACL() {
        return this.acl;
    }

    public void setACL(AVACL avacl) {
        this.acl = avacl;
    }

    private String getFileSum() {
        if (getMetaData() != null) {
            return (String) getMetaData().get(FILE_SUM_KEY);
        }
        return null;
    }

    protected JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        AVUtils.mapFromFile(this).put("url", this.url);
        return jSONObject;
    }
}

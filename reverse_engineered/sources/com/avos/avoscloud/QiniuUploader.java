package com.avos.avoscloud;

import ch.qos.logback.core.joran.action.Action;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

class QiniuUploader extends HttpClientUploader {
    private static final int BLOCK_SIZE = 4194304;
    private static final int DEFAULT_RETRY_TIMES = 6;
    private static final int NONWIFI_CHUNK_SIZE = 65536;
    private static final int PROGRESS_COMPLETE = 100;
    private static final int PROGRESS_GET_TOKEN = 10;
    private static final int PROGRESS_UPLOAD_FILE = 90;
    private static final String QINIU_BRICK_UPLOAD_EP = "http://upload.qiniu.com/bput/%s/%d";
    private static final String QINIU_CREATE_BLOCK_EP = "http://upload.qiniu.com/mkblk/%d";
    private static final String QINIU_HOST = "http://upload.qiniu.com";
    private static final String QINIU_MKFILE_EP = "http://upload.qiniu.com/mkfile/%d/key/%s";
    private static final int WIFI_CHUNK_SIZE = 262144;
    private int blockCount;
    private String bucket;
    private String hash;
    private String key;
    private String objectId;
    private String token;
    int uploadChunkSize = 262144;
    private String[] uploadFileCtx;
    private String url;

    /* renamed from: com.avos.avoscloud.QiniuUploader$3 */
    class C09903 extends DeleteCallback {
        C09903() {
        }

        public void done(AVException aVException) {
        }
    }

    private static class QiniuBlockResponseData {
        public String checksum;
        public long crc32;
        public String ctx;
        public String host;
        public int offset;

        private QiniuBlockResponseData() {
        }
    }

    private static class QiniuMKFileResponseData {
        public String hash;
        public String key;

        private QiniuMKFileResponseData() {
        }
    }

    QiniuUploader(AVFile aVFile, SaveCallback saveCallback, ProgressCallback progressCallback) {
        super(aVFile, saveCallback, progressCallback);
    }

    AVException doWork() {
        AVException uploadWithBlocks;
        try {
            parseFileKey();
            if (!AVUtils.isWifi(AVOSCloud.applicationContext)) {
                this.uploadChunkSize = 65536;
            }
            if (AVOSCloud.isDebugLogEnabled()) {
                avlog.m3506d("uploading with chunk size:" + this.uploadChunkSize);
            }
            this.parseFile.getData();
            uploadWithBlocks = uploadWithBlocks();
        } catch (AVException e) {
            uploadWithBlocks = e;
        }
        return uploadWithBlocks;
    }

    private void parseFileKey() {
        this.key = AVUtils.getRandomString(40);
        int i = 0;
        if (this.parseFile.getName() != null) {
            i = this.parseFile.getName().lastIndexOf(".");
        }
        if (i > 0) {
            this.key += this.parseFile.getName().substring(i);
        }
    }

    private HttpResponse executeRequest(HttpPost httpPost) throws Exception {
        if (this.token != null) {
            httpPost.setHeader("Authorization", "UpToken " + this.token);
        }
        return HttpClientUploader.getHttpClient().execute(httpPost);
    }

    private AVException uploadWithBlocks() {
        int i = 0;
        try {
            byte[] data = this.parseFile.getData();
            AVException fetchUploadBucket = fetchUploadBucket();
            if (fetchUploadBucket != null) {
                return fetchUploadBucket;
            }
            int i2;
            QiniuMKFileResponseData makeFile;
            publishProgress(10);
            int length = data.length / 4194304;
            if (data.length % 4194304 > 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            this.blockCount = i2 + length;
            this.uploadFileCtx = new String[this.blockCount];
            QiniuBlockResponseData qiniuBlockResponseData = null;
            while (i < this.blockCount) {
                qiniuBlockResponseData = createBlockInQiniu(i, getCurrentBlockSize(data, i), 6, data);
                if (qiniuBlockResponseData != null) {
                    qiniuBlockResponseData = putFileBlocksToQiniu(i, data, qiniuBlockResponseData, 6);
                    if (qiniuBlockResponseData != null) {
                        this.uploadFileCtx[i] = qiniuBlockResponseData.ctx;
                        publishProgress((((i + 1) * 80) / this.blockCount) + 10);
                    } else {
                        throw new Exception("Upload File failure");
                    }
                }
                i++;
            }
            if (qiniuBlockResponseData != null) {
                makeFile = makeFile(data.length, this.key, 6);
            } else {
                makeFile = null;
            }
            if (!isCancelled()) {
                if (makeFile == null || !makeFile.key.equals(this.key)) {
                    destroyFileObject();
                    return AVErrorUtils.createException(-1, "upload file failure");
                }
                this.parseFile.handleUploadedResponse(this.objectId, this.objectId, this.url);
                publishProgress(100);
            }
            closeExpiredConnections();
            return null;
        } catch (Throwable e) {
            Throwable th = e;
            th.printStackTrace();
            destroyFileObject();
            return new AVException(th);
        }
    }

    private int getCurrentBlockSize(byte[] bArr, int i) {
        return bArr.length - (i * 4194304) > 4194304 ? 4194304 : bArr.length - (4194304 * i);
    }

    private int getNextChunkSize(int i, byte[] bArr) {
        return bArr.length - (i * 4194304) > this.uploadChunkSize ? this.uploadChunkSize : bArr.length - (i * 4194304);
    }

    private QiniuBlockResponseData createBlockInQiniu(final int i, int i2, int i3, final byte[] bArr) {
        QiniuBlockResponseData qiniuBlockResponseData;
        try {
            avlog.m3506d("try to mkblk");
            HttpPost httpPost = new HttpPost(String.format(QINIU_CREATE_BLOCK_EP, new Object[]{Integer.valueOf(i2)}));
            final int nextChunkSize = getNextChunkSize(i, bArr);
            httpPost.setEntity(new AbstractHttpEntity() {
                public void writeTo(OutputStream outputStream) throws IOException {
                    Object obj = new byte[nextChunkSize];
                    System.arraycopy(bArr, i * 4194304, obj, 0, nextChunkSize);
                    outputStream.write(obj);
                }

                public boolean isStreaming() {
                    return false;
                }

                public boolean isRepeatable() {
                    return false;
                }

                public long getContentLength() {
                    return (long) nextChunkSize;
                }

                public InputStream getContent() throws IOException, IllegalStateException {
                    return null;
                }
            });
            qiniuBlockResponseData = (QiniuBlockResponseData) parseQiniuResponse(executeRequest(httpPost), QiniuBlockResponseData.class);
        } catch (Exception e) {
            e.printStackTrace();
            int i4 = i3 - 1;
            if (i3 > 0) {
                qiniuBlockResponseData = createBlockInQiniu(i, i2, i4, bArr);
            } else {
                log.m3520e("Exception during file upload", e);
                closeExpiredConnections();
                return null;
            }
        } finally {
            closeExpiredConnections();
        }
        return qiniuBlockResponseData;
    }

    private QiniuBlockResponseData putFileBlocksToQiniu(int i, byte[] bArr, QiniuBlockResponseData qiniuBlockResponseData, int i2) {
        int currentBlockSize = getCurrentBlockSize(bArr, i);
        publishProgress(((((4194304 * i) + qiniuBlockResponseData.offset) * 80) / bArr.length) + 10);
        int i3 = currentBlockSize - qiniuBlockResponseData.offset;
        if (i3 <= 0 || qiniuBlockResponseData.offset <= 0) {
            return qiniuBlockResponseData;
        }
        QiniuBlockResponseData qiniuBlockResponseData2;
        try {
            HttpPost httpPost = new HttpPost(String.format(QINIU_BRICK_UPLOAD_EP, new Object[]{qiniuBlockResponseData.ctx, Integer.valueOf(qiniuBlockResponseData.offset)}));
            httpPost.addHeader("Content-Type", "application/octet-stream");
            if (i3 > this.uploadChunkSize) {
                i3 = this.uploadChunkSize;
            }
            final byte[] bArr2 = bArr;
            final int i4 = i;
            final QiniuBlockResponseData qiniuBlockResponseData3 = qiniuBlockResponseData;
            httpPost.setEntity(new AbstractHttpEntity() {
                public void writeTo(OutputStream outputStream) throws IOException {
                    Object obj = new byte[i3];
                    System.arraycopy(bArr2, (i4 * 4194304) + qiniuBlockResponseData3.offset, obj, 0, i3);
                    outputStream.write(obj);
                }

                public boolean isStreaming() {
                    return false;
                }

                public boolean isRepeatable() {
                    return false;
                }

                public long getContentLength() {
                    return (long) i3;
                }

                public InputStream getContent() throws IOException, IllegalStateException {
                    return null;
                }
            });
            qiniuBlockResponseData2 = (QiniuBlockResponseData) parseQiniuResponse(executeRequest(httpPost), QiniuBlockResponseData.class);
            if (qiniuBlockResponseData2 == null) {
                closeExpiredConnections();
                return null;
            } else if (qiniuBlockResponseData2.offset < currentBlockSize) {
                qiniuBlockResponseData2 = putFileBlocksToQiniu(i, bArr, qiniuBlockResponseData2, 6);
                return qiniuBlockResponseData2;
            } else {
                closeExpiredConnections();
                return qiniuBlockResponseData2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            int i5 = i2 - 1;
            if (i2 > 0) {
                qiniuBlockResponseData2 = putFileBlocksToQiniu(i, bArr, qiniuBlockResponseData, i5);
            } else {
                log.m3520e("Exception during file upload", e);
                closeExpiredConnections();
            }
        } finally {
            closeExpiredConnections();
        }
    }

    private QiniuMKFileResponseData makeFile(int i, String str, int i2) throws Exception {
        QiniuMKFileResponseData qiniuMKFileResponseData;
        try {
            String format = String.format(QINIU_MKFILE_EP, new Object[]{Integer.valueOf(i), AVUtils.Base64Encode(str)});
            Collection linkedList = new LinkedList();
            Collections.addAll(linkedList, this.uploadFileCtx);
            String joinCollection = AVUtils.joinCollection(linkedList, ",");
            HttpPost httpPost = new HttpPost(format);
            httpPost.setEntity(new StringEntity(joinCollection));
            qiniuMKFileResponseData = (QiniuMKFileResponseData) parseQiniuResponse(executeRequest(httpPost), QiniuMKFileResponseData.class);
        } catch (Exception e) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                qiniuMKFileResponseData = makeFile(i, str, i3);
            } else {
                log.m3520e("Exception during file upload", e);
                closeExpiredConnections();
                return null;
            }
        } finally {
            closeExpiredConnections();
        }
        return qiniuMKFileResponseData;
    }

    private <T> T parseQiniuResponse(HttpResponse httpResponse, Class<T> cls) throws Exception {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
        Header firstHeader = httpResponse.getFirstHeader("X-Log");
        String value = firstHeader == null ? "" : firstHeader.getValue();
        if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
            throw new Exception("unauthorized to create Qiniu Block");
        }
        String entityUtils = EntityUtils.toString(httpResponse.getEntity());
        try {
            if (statusCode / 100 == 2) {
                T parseObject = JSON.parseObject(entityUtils, (Class) cls);
                if (!(entityUtils != null || httpResponse == null || httpResponse.getEntity() == null)) {
                    try {
                        AVPersistenceUtils.closeQuietly(httpResponse.getEntity().getContent());
                    } catch (Exception e) {
                    }
                }
                return parseObject;
            }
            if (!(entityUtils != null || httpResponse == null || httpResponse.getEntity() == null)) {
                try {
                    AVPersistenceUtils.closeQuietly(httpResponse.getEntity().getContent());
                } catch (Exception e2) {
                }
            }
            if (entityUtils.length() > 0) {
                throw new Exception(statusCode + ":" + entityUtils);
            } else if (value.length() > 0) {
                throw new Exception(value);
            } else {
                throw new Exception(reasonPhrase);
            }
        } catch (Exception e3) {
            if (!(entityUtils != null || httpResponse == null || httpResponse.getEntity() == null)) {
                try {
                    AVPersistenceUtils.closeQuietly(httpResponse.getEntity().getContent());
                } catch (Exception e4) {
                }
            }
        } catch (Throwable th) {
            if (!(entityUtils != null || httpResponse == null || httpResponse.getEntity() == null)) {
                try {
                    AVPersistenceUtils.closeQuietly(httpResponse.getEntity().getContent());
                } catch (Exception e5) {
                }
            }
        }
    }

    private void destroyFileObject() {
        if (!AVUtils.isBlankString(this.objectId)) {
            try {
                AVObject.createWithoutData("_File", this.objectId).deleteInBackground(new C09903());
            } catch (Exception e) {
            }
        }
    }

    private AVException handleGetBucketResponse(String str, AVException aVException) {
        if (aVException != null) {
            return aVException;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.bucket = jSONObject.getString("bucket");
            this.objectId = jSONObject.getString(AVUtils.objectIdTag);
            this.token = jSONObject.getString("token");
            if (AVUtils.isBlankString(this.token)) {
                return new AVException(-1, "No token return for qiniu upload");
            }
            this.url = jSONObject.getString("url");
            return null;
        } catch (Throwable e) {
            return new AVException(e);
        }
    }

    private String getGetBucketParameters() {
        Map hashMap = new HashMap(3);
        hashMap.put(Action.KEY_ATTRIBUTE, this.key);
        hashMap.put("name", this.parseFile.getName());
        hashMap.put("mime_type", this.parseFile.mimeType());
        hashMap.put("metaData", this.parseFile.getMetaData());
        hashMap.put(AVUtils.typeTag, AVFile.className());
        if (this.parseFile.getACL() != null) {
            hashMap.putAll(AVUtils.getParsedMap(this.parseFile.getACL().getACLMap()));
        }
        return AVUtils.restfulServerData(hashMap);
    }

    protected String getUploadPath() {
        return "qiniu";
    }

    protected AVException fetchUploadBucket() {
        final AVException[] aVExceptionArr = new AVException[1];
        PaasClient.storageInstance().postObject(getUploadPath(), getGetBucketParameters(), true, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                aVExceptionArr[0] = QiniuUploader.this.handleGetBucketResponse(str, aVException);
            }

            public void onFailure(Throwable th, String str) {
                aVExceptionArr[0] = AVErrorUtils.createException(th, str);
            }
        });
        if (aVExceptionArr[0] == null) {
            return null;
        }
        destroyFileObject();
        return aVExceptionArr[0];
    }
}

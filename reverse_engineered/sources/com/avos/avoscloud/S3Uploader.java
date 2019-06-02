package com.avos.avoscloud;

import ch.qos.logback.core.joran.action.Action;
import com.avos.avoscloud.AVMultiPartEntity.ProgressListener;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.signature.AES;
import com.avos.avoscloud.signature.Base64Encoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class S3Uploader extends HttpClientUploader {
    private final String S3BasePath = "https://s3.amazonaws.com/avos-cloud";
    private String access;
    private final String bucket = "avos-cloud";
    private String objectId;
    private String secret;
    private String url;
    private String uuid;

    /* renamed from: com.avos.avoscloud.S3Uploader$3 */
    class C09963 implements ProgressListener {
        C09963() {
        }

        public void transferred(long j) {
            if (S3Uploader.this.isCancelled()) {
                HttpPost httpPost = (HttpPost) S3Uploader.this.httpPostRef.get();
                if (httpPost != null) {
                    httpPost.abort();
                }
            }
            S3Uploader.this.publishProgress((int) ((((float) j) / ((float) S3Uploader.this.totalSize)) * 100.0f));
        }
    }

    S3Uploader(AVFile aVFile, SaveCallback saveCallback, ProgressCallback progressCallback) {
        super(aVFile, saveCallback, progressCallback);
    }

    AVException doWork() {
        AVException aVException;
        HttpResponse execute;
        HttpResponse httpResponse;
        Exception exception;
        Throwable th;
        HttpResponse httpResponse2 = null;
        this.uuid = UUID.randomUUID().toString().toLowerCase();
        int indexOf = this.parseFile.getName().indexOf(".");
        if (indexOf > 0) {
            this.uuid += this.parseFile.getName().substring(indexOf);
        }
        DefaultHttpClient httpClient = HttpClientUploader.getHttpClient();
        HttpResponse httpResponse3 = null;
        try {
            byte[] data = this.parseFile.getData();
            final AVException[] aVExceptionArr = new AVException[1];
            PaasClient.storageInstance().getObject("s3", null, true, null, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    aVExceptionArr[0] = S3Uploader.this.handleGetKeyResponse(str);
                }

                public void onFailure(Throwable th, String str) {
                    aVExceptionArr[0] = AVErrorUtils.createException(th, str);
                }
            });
            if (aVExceptionArr[0] != null) {
                aVException = aVExceptionArr[0];
                if (httpResponse2 != null || httpResponse2 == null || httpResponse2.getEntity() == null) {
                    return aVException;
                }
                try {
                    AVPersistenceUtils.closeQuietly(httpResponse3.getEntity().getContent());
                    return aVException;
                } catch (Exception e) {
                    return aVException;
                }
            }
            String str = "android/" + this.uuid;
            this.url = getS3Link(str);
            PaasClient.storageInstance().postObject("files", getParametersForUrulu(), true, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    aVExceptionArr[0] = S3Uploader.this.handlePostResponse(str);
                }

                public void onFailure(Throwable th, String str) {
                    aVExceptionArr[0] = AVErrorUtils.createException(th, str);
                }
            });
            if (aVExceptionArr[0] != null) {
                aVException = aVExceptionArr[0];
                if (httpResponse2 != null || httpResponse2 == null || httpResponse2.getEntity() == null) {
                    return aVException;
                }
                try {
                    AVPersistenceUtils.closeQuietly(httpResponse3.getEntity().getContent());
                    return aVException;
                } catch (Exception e2) {
                    return aVException;
                }
            }
            HttpContext basicHttpContext = new BasicHttpContext();
            this.httpPostRef.set(new HttpPost("http://avos-cloud.s3.amazonaws.com/"));
            Object aVMultiPartEntity = new AVMultiPartEntity(new C09963());
            Charset forName = Charset.forName("UTF-8");
            aVMultiPartEntity.addPart("acl", new StringBody("public-read", forName));
            aVMultiPartEntity.addPart(Action.KEY_ATTRIBUTE, new StringBody(str, forName));
            aVMultiPartEntity.addPart(Action.FILE_ATTRIBUTE, new ByteArrayBody(data, this.uuid));
            this.totalSize = aVMultiPartEntity.getContentLength();
            ((HttpPost) this.httpPostRef.get()).setEntity(aVMultiPartEntity);
            String value = aVMultiPartEntity.getContentType().getValue();
            String RFC822FormatStringFromDate = RFC822FormatStringFromDate(new Date());
            ((HttpPost) this.httpPostRef.get()).addHeader("Authorization", authorization("POST", value, RFC822FormatStringFromDate));
            ((HttpPost) this.httpPostRef.get()).addHeader("Date", RFC822FormatStringFromDate);
            execute = httpClient.execute((HttpUriRequest) this.httpPostRef.get(), basicHttpContext);
            try {
                if (!isCancelled()) {
                    if (execute.getStatusLine().getStatusCode() != 204) {
                        String entityUtils = EntityUtils.toString(execute.getEntity());
                        avlog.m3507e(entityUtils);
                        aVException = AVErrorUtils.createException(-1, "upload file failure");
                        if (entityUtils != null || execute == null || execute.getEntity() == null) {
                            return aVException;
                        }
                        try {
                            AVPersistenceUtils.closeQuietly(execute.getEntity().getContent());
                            return aVException;
                        } catch (Exception e3) {
                            return aVException;
                        }
                    }
                    this.parseFile.handleUploadedResponse(this.objectId, this.objectId, this.url);
                }
                if (!(httpResponse2 != null || execute == null || execute.getEntity() == null)) {
                    try {
                        AVPersistenceUtils.closeQuietly(execute.getEntity().getContent());
                    } catch (Exception e4) {
                    }
                }
                closeExpiredConnections();
                return httpResponse2;
            } catch (Exception e5) {
                httpResponse = execute;
                exception = e5;
                try {
                    if (this.httpPostRef.get() != null) {
                        ((HttpPost) this.httpPostRef.get()).abort();
                    }
                    log.m3519e(exception.toString());
                    aVException = new AVException(exception.getCause());
                    if (httpResponse2 == null || httpResponse == null || httpResponse.getEntity() == null) {
                        return aVException;
                    }
                    try {
                        AVPersistenceUtils.closeQuietly(httpResponse.getEntity().getContent());
                        return aVException;
                    } catch (Exception e6) {
                        return aVException;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    execute = httpResponse;
                    try {
                        AVPersistenceUtils.closeQuietly(execute.getEntity().getContent());
                    } catch (Exception e7) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (!(httpResponse2 != null || execute == null || execute.getEntity() == null)) {
                    AVPersistenceUtils.closeQuietly(execute.getEntity().getContent());
                }
                throw th;
            }
        } catch (Exception e52) {
            exception = e52;
            httpResponse = httpResponse2;
            if (this.httpPostRef.get() != null) {
                ((HttpPost) this.httpPostRef.get()).abort();
            }
            log.m3519e(exception.toString());
            aVException = new AVException(exception.getCause());
            return httpResponse2 == null ? aVException : aVException;
        } catch (Throwable th4) {
            th = th4;
            execute = httpResponse2;
            AVPersistenceUtils.closeQuietly(execute.getEntity().getContent());
            throw th;
        }
    }

    private AVException handleGetKeyResponse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            AES aes = new AES();
            this.access = aes.decrypt(jSONObject.getString("access_key"));
            this.secret = aes.decrypt(jSONObject.getString("access_token"));
            return null;
        } catch (Throwable e) {
            return new AVException(e);
        }
    }

    private AVException handlePostResponse(String str) {
        try {
            this.objectId = new JSONObject(str).getString(AVUtils.objectIdTag);
            return null;
        } catch (Throwable e) {
            return new AVException(e);
        }
    }

    private String getParametersForUrulu() {
        Map hashMap = new HashMap(3);
        hashMap.put(Action.KEY_ATTRIBUTE, "android/" + this.uuid);
        hashMap.put("name", this.parseFile.getName());
        hashMap.put("mime_type", this.parseFile.mimeType());
        hashMap.put("metaData", this.parseFile.getMetaData());
        hashMap.put(AVUtils.typeTag, AVFile.className());
        hashMap.put("url", this.url);
        if (this.parseFile.getACL() != null) {
            hashMap.putAll(AVUtils.getParsedMap(this.parseFile.getACL().getACLMap()));
        }
        return AVUtils.restfulServerData(hashMap);
    }

    private String getS3Link(String str) {
        return "https://s3.amazonaws.com/avos-cloud/" + str;
    }

    private String md5WithHmac(String str) throws Exception {
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(this.secret.getBytes("UTF8"), "HmacSHA1"));
        return Base64Encoder.encode(instance.doFinal(str.getBytes("UTF8")));
    }

    private String authorization(String str, String str2, String str3) throws Exception {
        return "AWS " + this.access + ":" + signature(str, str2, str3);
    }

    private String signature(String str, String str2, String str3) throws Exception {
        return md5WithHmac(str + "\n" + "\n" + str2 + "\n" + str3 + "\n" + "" + "/avos-cloud/");
    }

    private String RFC822FormatStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }
}

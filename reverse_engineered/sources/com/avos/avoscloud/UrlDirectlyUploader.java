package com.avos.avoscloud;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class UrlDirectlyUploader extends HttpClientUploader {
    protected UrlDirectlyUploader(AVFile aVFile, SaveCallback saveCallback, ProgressCallback progressCallback) {
        super(aVFile, saveCallback, progressCallback);
    }

    AVException doWork() {
        final AVException[] aVExceptionArr = new AVException[1];
        PaasClient.storageInstance().postObject(AVPowerfulUtils.getEndpoint(this.parseFile), getFileRequestParameters(), true, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (aVException == null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        UrlDirectlyUploader.this.parseFile.handleUploadedResponse(jSONObject.getString(AVUtils.objectIdTag), jSONObject.getString(AVUtils.objectIdTag), UrlDirectlyUploader.this.parseFile.getUrl());
                        return;
                    } catch (Throwable e) {
                        aVExceptionArr[0] = new AVException(e);
                        return;
                    }
                }
                aVExceptionArr[0] = AVErrorUtils.createException((Throwable) aVException, str);
            }

            public void onFailure(Throwable th, String str) {
                aVExceptionArr[0] = AVErrorUtils.createException(th, str);
            }
        });
        return aVExceptionArr[0];
    }

    private String getFileRequestParameters() {
        Map hashMap = new HashMap();
        hashMap.put("name", this.parseFile.getName());
        hashMap.put("mime_type", this.parseFile.mimeType());
        hashMap.put("metaData", this.parseFile.getMetaData());
        hashMap.put(AVUtils.typeTag, AVFile.className());
        hashMap.put("url", this.parseFile.getUrl());
        if (this.parseFile.getACL() != null) {
            hashMap.putAll(AVUtils.getParsedMap(this.parseFile.getACL().getACLMap()));
        }
        return AVUtils.restfulServerData(hashMap);
    }
}

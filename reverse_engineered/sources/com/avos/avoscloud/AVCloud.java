package com.avos.avoscloud;

import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.LogUtil.log;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class AVCloud {
    public static void setProductionMode(boolean z) {
        PaasClient.cloudInstance().setProduction(z);
    }

    public static <T> T callFunction(String str, Map<String, ?> map) throws AVException {
        final AtomicReference atomicReference = new AtomicReference();
        PaasClient.cloudInstance().postObject("functions/" + str, AVUtils.restfulServerData(map), true, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                atomicReference.set(AVCloud.convertCloudResponse(str));
            }

            public void onFailure(Throwable th, String str) {
                log.m3514d(str + th);
                AVExceptionHolder.add(AVErrorUtils.createException(th, str));
            }
        });
        if (!AVExceptionHolder.exists()) {
            return atomicReference.get();
        }
        throw AVExceptionHolder.remove();
    }

    public static <T> void callFunctionInBackground(String str, Map<String, ?> map, final FunctionCallback<T> functionCallback) {
        PaasClient.cloudInstance().postObject("functions/" + str, AVUtils.restfulServerData(map), false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (functionCallback != null) {
                    functionCallback.internalDone(AVCloud.convertCloudResponse(str), aVException);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (functionCallback != null) {
                    functionCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        });
    }

    public static Object convertCloudResponse(String str) {
        try {
            Object obj = ((Map) AVUtils.getFromJSON(str, Map.class)).get(C0882j.f2229c);
            if (obj instanceof Collection) {
                return AVUtils.getObjectFrom((Collection) obj);
            }
            if (obj instanceof Map) {
                return AVUtils.getObjectFrom((Map) obj);
            }
            return obj;
        } catch (Exception e) {
            log.m3520e("Error during response parse", e);
            return null;
        }
    }
}

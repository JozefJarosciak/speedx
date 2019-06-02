package com.avos.avoscloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.avos.avoscloud.ops.AVOp;
import com.avos.avoscloud.ops.AddRelationOp;
import com.avos.avoscloud.ops.CollectionOp;
import com.avos.avoscloud.ops.CompoundOp;
import com.avos.avoscloud.ops.RemoveRelationOp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AVObjectDeserializer implements ObjectDeserializer {
    static final String LOG_TAG = AVObjectDeserializer.class.getSimpleName();
    public static final AVObjectDeserializer instance = new AVObjectDeserializer();

    AVObjectDeserializer() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r10, java.lang.reflect.Type r11, java.lang.Object r12) {
        /*
        r9 = this;
        r5 = 0;
        r1 = com.avos.avoscloud.AVObject.class;
        r0 = r11;
        r0 = (java.lang.Class) r0;
        r0 = r1.isAssignableFrom(r0);
        if (r0 == 0) goto L_0x011a;
    L_0x000c:
        r2 = new java.util.HashMap;
        r2.<init>();
        r10.parseObject(r2);
        r11 = (java.lang.Class) r11;	 Catch:{ InstantiationException -> 0x012b, IllegalAccessException -> 0x0127, Exception -> 0x0123, all -> 0x011f }
        r0 = r11.newInstance();	 Catch:{ InstantiationException -> 0x012b, IllegalAccessException -> 0x0127, Exception -> 0x0123, all -> 0x011f }
        r0 = (com.avos.avoscloud.AVObject) r0;	 Catch:{ InstantiationException -> 0x012b, IllegalAccessException -> 0x0127, Exception -> 0x0123, all -> 0x011f }
        r1 = "className";
        r1 = r2.get(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = (java.lang.String) r1;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r0.setClassName(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = "objectId";
        r1 = r2.get(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = (java.lang.String) r1;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r0.setObjectId(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = "createdAt";
        r1 = r2.get(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = (java.lang.String) r1;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r0.setCreatedAt(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = "updatedAt";
        r1 = r2.get(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = (java.lang.String) r1;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r0.setUpdatedAt(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = "keyValues";
        r1 = r2.containsKey(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        if (r1 == 0) goto L_0x00ec;
    L_0x0050:
        r1 = "keyValues";
        r1 = r2.get(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = (java.util.Map) r1;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r2 = r1.keySet();	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r6 = r2.iterator();	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
    L_0x0060:
        r2 = r6.hasNext();	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        if (r2 == 0) goto L_0x0116;
    L_0x0066:
        r2 = r6.next();	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r2 = (java.lang.String) r2;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r3 = r1.get(r2);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r3 = (com.alibaba.fastjson.JSONObject) r3;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r4 = "value";
        r7 = r3.get(r4);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r4 = "op";
        r4 = r3.get(r4);	 Catch:{ Exception -> 0x009f, InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0 }
        r4 = (com.avos.avoscloud.ops.AVOp) r4;	 Catch:{ Exception -> 0x009f, InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0 }
    L_0x0080:
        if (r4 == 0) goto L_0x00a2;
    L_0x0082:
        r8 = r4 instanceof com.avos.avoscloud.ops.NullOP;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        if (r8 != 0) goto L_0x00a2;
    L_0x0086:
        r3 = r9.updateOp(r4, r7);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r4 = r0.operationQueue;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r4.put(r2, r3);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        goto L_0x0060;
    L_0x0090:
        r1 = move-exception;
    L_0x0091:
        r2 = com.avos.avoscloud.AVOSCloud.isDebugLogEnabled();	 Catch:{ all -> 0x00bf }
        if (r2 == 0) goto L_0x009e;
    L_0x0097:
        r2 = LOG_TAG;	 Catch:{ all -> 0x00bf }
        r3 = "";
        com.avos.avoscloud.LogUtil.log.m3522e(r2, r3, r1);	 Catch:{ all -> 0x00bf }
    L_0x009e:
        return r0;
    L_0x009f:
        r4 = move-exception;
        r4 = r5;
        goto L_0x0080;
    L_0x00a2:
        r4 = "relationClassName";
        r4 = r3.containsKey(r4);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        if (r4 != 0) goto L_0x00c1;
    L_0x00aa:
        r3 = r0.serverData;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r3.put(r2, r7);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        goto L_0x0060;
    L_0x00b0:
        r1 = move-exception;
    L_0x00b1:
        r2 = com.avos.avoscloud.AVOSCloud.isDebugLogEnabled();	 Catch:{ all -> 0x00bf }
        if (r2 == 0) goto L_0x009e;
    L_0x00b7:
        r2 = LOG_TAG;	 Catch:{ all -> 0x00bf }
        r3 = "";
        com.avos.avoscloud.LogUtil.log.m3522e(r2, r3, r1);	 Catch:{ all -> 0x00bf }
        goto L_0x009e;
    L_0x00bf:
        r1 = move-exception;
        goto L_0x009e;
    L_0x00c1:
        r4 = "relationClassName";
        r4 = r3.containsKey(r4);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        if (r4 == 0) goto L_0x0060;
    L_0x00c9:
        r4 = new com.avos.avoscloud.AVRelation;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r4.<init>(r0, r2);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r7 = "relationClassName";
        r3 = r3.getString(r7);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r4.setTargetClass(r3);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r3 = r0.serverData;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r3.put(r2, r4);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        goto L_0x0060;
    L_0x00dd:
        r1 = move-exception;
    L_0x00de:
        r2 = com.avos.avoscloud.AVOSCloud.isDebugLogEnabled();	 Catch:{ all -> 0x00bf }
        if (r2 == 0) goto L_0x009e;
    L_0x00e4:
        r2 = LOG_TAG;	 Catch:{ all -> 0x00bf }
        r3 = "";
        com.avos.avoscloud.LogUtil.log.m3522e(r2, r3, r1);	 Catch:{ all -> 0x00bf }
        goto L_0x009e;
    L_0x00ec:
        r1 = "operationQueue";
        r1 = r2.containsKey(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        if (r1 == 0) goto L_0x0101;
    L_0x00f4:
        r3 = r0.operationQueue;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = "operationQueue";
        r1 = r2.get(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = (java.util.Map) r1;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r3.putAll(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
    L_0x0101:
        r1 = "serverData";
        r1 = r2.containsKey(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        if (r1 == 0) goto L_0x0116;
    L_0x0109:
        r3 = r0.serverData;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = "serverData";
        r1 = r2.get(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r1 = (java.util.Map) r1;	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        r3.putAll(r1);	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
    L_0x0116:
        r0.rebuildInstanceData();	 Catch:{ InstantiationException -> 0x0090, IllegalAccessException -> 0x00b0, Exception -> 0x00dd }
        goto L_0x009e;
    L_0x011a:
        r0 = r10.parseObject();
        goto L_0x009e;
    L_0x011f:
        r0 = move-exception;
        r0 = r5;
        goto L_0x009e;
    L_0x0123:
        r0 = move-exception;
        r1 = r0;
        r0 = r5;
        goto L_0x00de;
    L_0x0127:
        r0 = move-exception;
        r1 = r0;
        r0 = r5;
        goto L_0x00b1;
    L_0x012b:
        r0 = move-exception;
        r1 = r0;
        r0 = r5;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.avos.avoscloud.AVObjectDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):T");
    }

    public int getFastMatchToken() {
        return 12;
    }

    public AVOp updateOp(AVOp aVOp, Object obj) {
        if ((aVOp instanceof AddRelationOp) || (aVOp instanceof RemoveRelationOp)) {
            try {
                Set<JSONObject> set = (Set) aVOp.getValues();
                Collection hashSet = new HashSet();
                for (JSONObject jSONObject : set) {
                    hashSet.add((AVObject) JSON.parseObject(jSONObject.toString(), AVObject.class));
                }
                ((CollectionOp) aVOp).setValues(hashSet);
            } catch (Exception e) {
            }
        }
        if (aVOp instanceof CompoundOp) {
            for (AVOp updateOp : (List) aVOp.getValues()) {
                updateOp(updateOp, null);
            }
        }
        return aVOp;
    }
}

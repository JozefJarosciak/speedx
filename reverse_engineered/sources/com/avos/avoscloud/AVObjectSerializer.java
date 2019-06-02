package com.avos.avoscloud;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

class AVObjectSerializer implements ObjectSerializer {
    public static final AVObjectSerializer instance = new AVObjectSerializer();

    AVObjectSerializer() {
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        AVObject aVObject = (AVObject) obj;
        writer.write((char) CoreConstants.CURLY_LEFT);
        writer.writeFieldValue(' ', "@type", aVObject.getClass().getName());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, AVUtils.objectIdTag, aVObject.getObjectId());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "updatedAt", AVUtils.getAVObjectUpdatedAt(aVObject));
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "createdAt", AVUtils.getAVObjectCreatedAt(aVObject));
        String aVObjectClassName = AVUtils.getAVObjectClassName(aVObject.getClass());
        String str = "className";
        if (aVObjectClassName == null) {
            aVObjectClassName = aVObject.getClassName();
        }
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, str, aVObjectClassName);
        writer.write((char) CoreConstants.COMMA_CHAR);
        writer.writeFieldName("serverData");
        writer.write(JSON.toJSONString(aVObject.serverData, ObjectValueFilter.instance, SerializerFeature.WriteClassName, SerializerFeature.DisableCircularReferenceDetect));
        if (!aVObject.operationQueue.isEmpty()) {
            writer.write((char) CoreConstants.COMMA_CHAR);
            writer.writeFieldName("operationQueue");
            writer.write(JSON.toJSONString(aVObject.operationQueue, ObjectValueFilter.instance, SerializerFeature.WriteClassName, SerializerFeature.DisableCircularReferenceDetect));
        }
        writer.write((char) CoreConstants.CURLY_RIGHT);
    }
}

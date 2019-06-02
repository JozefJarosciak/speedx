package com.alibaba.fastjson;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JSONWriter implements Closeable, Flushable {
    private JSONStreamContext context;
    private JSONSerializer serializer = new JSONSerializer(this.writer);
    private SerializeWriter writer;

    public JSONWriter(Writer writer) {
        this.writer = new SerializeWriter(writer);
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.writer.config(serializerFeature, z);
    }

    public void startObject() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1001);
        this.writer.write((char) CoreConstants.CURLY_LEFT);
    }

    public void endObject() {
        this.writer.write((char) CoreConstants.CURLY_RIGHT);
        endStructure();
    }

    public void writeKey(String str) {
        writeObject(str);
    }

    public void writeValue(Object obj) {
        writeObject(obj);
    }

    public void writeObject(String str) {
        beforeWrite();
        this.serializer.write(str);
        afterWriter();
    }

    public void writeObject(Object obj) {
        beforeWrite();
        this.serializer.write(obj);
        afterWriter();
    }

    public void startArray() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1004);
        this.writer.write('[');
    }

    private void beginStructure() {
        int state = this.context.getState();
        switch (state) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.writer.write((char) CoreConstants.COLON_CHAR);
                return;
            case 1005:
                this.writer.write((char) CoreConstants.COMMA_CHAR);
                return;
            default:
                throw new JSONException("illegal state : " + state);
        }
    }

    public void endArray() {
        this.writer.write(']');
        endStructure();
    }

    private void endStructure() {
        this.context = this.context.getParent();
        if (this.context != null) {
            int i;
            switch (this.context.getState()) {
                case 1001:
                    i = 1002;
                    break;
                case 1002:
                    i = 1003;
                    break;
                case 1004:
                    i = 1005;
                    break;
                case 1005:
                    i = -1;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i != -1) {
                this.context.setState(i);
            }
        }
    }

    private void beforeWrite() {
        if (this.context != null) {
            switch (this.context.getState()) {
                case 1001:
                case 1004:
                    return;
                case 1002:
                    this.writer.write((char) CoreConstants.COLON_CHAR);
                    return;
                case 1003:
                    this.writer.write((char) CoreConstants.COMMA_CHAR);
                    return;
                case 1005:
                    this.writer.write((char) CoreConstants.COMMA_CHAR);
                    return;
                default:
                    return;
            }
        }
    }

    private void afterWriter() {
        if (this.context != null) {
            int i;
            switch (this.context.getState()) {
                case 1001:
                case 1003:
                    i = 1002;
                    break;
                case 1002:
                    i = 1003;
                    break;
                case 1004:
                    i = 1005;
                    break;
                case 1005:
                    i = -1;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i != -1) {
                this.context.setState(i);
            }
        }
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void close() throws IOException {
        this.writer.close();
    }

    @Deprecated
    public void writeStartObject() {
        startObject();
    }

    @Deprecated
    public void writeEndObject() {
        endObject();
    }

    @Deprecated
    public void writeStartArray() {
        startArray();
    }

    @Deprecated
    public void writeEndArray() {
        endArray();
    }
}

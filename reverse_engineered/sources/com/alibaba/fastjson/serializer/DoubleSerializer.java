package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class DoubleSerializer implements ObjectSerializer {
    public static final DoubleSerializer instance = new DoubleSerializer();
    private DecimalFormat decimalFormat;

    public DoubleSerializer() {
        this.decimalFormat = null;
    }

    public DoubleSerializer(DecimalFormat decimalFormat) {
        this.decimalFormat = null;
        this.decimalFormat = decimalFormat;
    }

    public DoubleSerializer(String str) {
        this(new DecimalFormat(str));
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            double doubleValue = ((Double) obj).doubleValue();
            if (Double.isNaN(doubleValue)) {
                writer.writeNull();
            } else if (Double.isInfinite(doubleValue)) {
                writer.writeNull();
            } else {
                CharSequence d;
                if (this.decimalFormat == null) {
                    d = Double.toString(doubleValue);
                    if (d.endsWith(".0")) {
                        d = d.substring(0, d.length() - 2);
                    }
                } else {
                    d = this.decimalFormat.format(doubleValue);
                }
                writer.append(d);
                if (jSONSerializer.isEnabled(SerializerFeature.WriteClassName)) {
                    writer.write('D');
                }
            }
        } else if (jSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            writer.write('0');
        } else {
            writer.writeNull();
        }
    }
}

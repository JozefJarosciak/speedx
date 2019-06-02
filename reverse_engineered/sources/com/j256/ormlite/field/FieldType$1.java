package com.j256.ormlite.field;

class FieldType$1 extends ThreadLocal<FieldType$LevelCounters> {
    FieldType$1() {
    }

    protected FieldType$LevelCounters initialValue() {
        return new FieldType$LevelCounters();
    }
}

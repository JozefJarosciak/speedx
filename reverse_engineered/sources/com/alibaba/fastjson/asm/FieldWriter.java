package com.alibaba.fastjson.asm;

final class FieldWriter implements FieldVisitor {
    private final int access;
    private final int desc;
    private final int name;
    FieldWriter next;

    FieldWriter(ClassWriter classWriter, int i, String str, String str2) {
        if (classWriter.firstField == null) {
            classWriter.firstField = this;
        } else {
            classWriter.lastField.next = this;
        }
        classWriter.lastField = this;
        this.access = i;
        this.name = classWriter.newUTF8(str);
        this.desc = classWriter.newUTF8(str2);
    }

    public void visitEnd() {
    }

    int getSize() {
        return 8;
    }

    void put(ByteVector byteVector) {
        byteVector.putShort(((393216 | ((this.access & 262144) / 64)) ^ -1) & this.access).putShort(this.name).putShort(this.desc);
        byteVector.putShort(0);
    }
}

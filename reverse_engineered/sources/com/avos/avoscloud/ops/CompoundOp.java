package com.avos.avoscloud.ops;

import com.avos.avoscloud.ops.AVOp.OpType;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CompoundOp extends CollectionOp {
    public Map<String, Object> encodeOp() {
        throw new UnsupportedOperationException();
    }

    public CompoundOp(String str, AVOp... aVOpArr) {
        super(str, OpType.Compound);
        this.ops = new LinkedList();
        if (aVOpArr != null) {
            for (Object add : aVOpArr) {
                this.ops.add(add);
            }
        }
    }

    public void addFirst(AVOp aVOp) {
        this.ops.addFirst(aVOp);
    }

    public void addLast(AVOp aVOp) {
        this.ops.addLast(aVOp);
    }

    public AVOp removeFirst() {
        return (AVOp) this.ops.removeFirst();
    }

    public AVOp removeLast() {
        return (AVOp) this.ops.removeLast();
    }

    public List<AVOp> getValues() {
        return this.ops;
    }

    public Object apply(Object obj) {
        Iterator it = this.ops.iterator();
        while (it.hasNext()) {
            obj = ((AVOp) it.next()).apply(obj);
        }
        return obj;
    }
}

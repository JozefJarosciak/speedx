package com.avos.avoscloud.ops;

import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.ops.AVOp.OpType;
import java.util.Map;

public class DeleteOp extends BaseOp {
    public DeleteOp(String str) {
        super(str, OpType.Delete);
    }

    public Map<String, Object> encodeOp() {
        return AVUtils.createDeleteOpMap(this.key);
    }

    public AVOp merge(AVOp aVOp) {
        assertKeyEquals(aVOp);
        switch (aVOp.type()) {
            case Set:
            case Compound:
                ((CompoundOp) aVOp.cast(CompoundOp.class)).addFirst(this);
                return aVOp;
            case Add:
            case AddUnique:
            case AddRelation:
            case Increment:
                return aVOp;
            case Remove:
            case RemoveRelation:
            case Null:
            case Delete:
                return this;
            default:
                throw new IllegalStateException("Unknow op type " + aVOp.type());
        }
    }

    public Object getValues() {
        return null;
    }

    public Object apply(Object obj) {
        return null;
    }
}

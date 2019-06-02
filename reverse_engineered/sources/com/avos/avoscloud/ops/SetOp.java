package com.avos.avoscloud.ops;

import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.ops.AVOp.OpType;
import java.util.HashMap;
import java.util.Map;

public class SetOp extends BaseOp implements SingleValueOp {
    private Object value;

    public Object getValues() {
        return this.value;
    }

    void setValue(Object obj) {
        this.value = obj;
    }

    public SetOp(String str, Object obj) {
        super(str, OpType.Set);
        this.value = obj;
    }

    public Object apply(Object obj) {
        return getValues();
    }

    public Map<String, Object> encodeOp() {
        Map hashMap = new HashMap();
        hashMap.put(this.key, AVUtils.getParsedObject(this.value));
        return hashMap;
    }

    public AVOp merge(AVOp aVOp) {
        assertKeyEquals(aVOp);
        switch (aVOp.type()) {
            case Null:
                return this;
            case Set:
                this.value = ((SetOp) aVOp.cast(SetOp.class)).value;
                return this;
            case Add:
            case AddUnique:
            case Remove:
            case AddRelation:
            case RemoveRelation:
                return new CompoundOp(this.key, this, aVOp);
            case Increment:
                if (this.value instanceof Number) {
                    this.value = Long.valueOf(((long) ((IncrementOp) aVOp.cast(IncrementOp.class)).amount.intValue()) + ((Number) this.value).longValue());
                    return this;
                }
                throw new IllegalArgumentException("Could not increment non-numberic value.");
            case Delete:
                return aVOp;
            case Compound:
                ((CompoundOp) aVOp.cast(CompoundOp.class)).addFirst(this);
                return aVOp;
            default:
                throw new IllegalStateException("Unknow op type " + aVOp.type());
        }
    }

    public void setValues(Object obj) {
        this.value = obj;
    }
}

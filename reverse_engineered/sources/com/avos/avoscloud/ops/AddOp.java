package com.avos.avoscloud.ops;

import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.ops.AVOp.OpType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AddOp extends CollectionAddOp {
    private List<Object> values = new LinkedList();

    public AddOp(String str, OpType opType) {
        super(str, opType);
    }

    public List<Object> getValues() {
        return this.values;
    }

    public AddOp(String str, Object... objArr) {
        super(str, OpType.Add);
        if (objArr != null) {
            for (Object add : objArr) {
                this.values.add(add);
            }
        }
    }

    public Map<String, Object> encodeOp() {
        return AVUtils.createArrayOpMap(this.key, this.type.name(), getParsedValues());
    }

    public AVOp merge(AVOp aVOp) {
        assertKeyEquals(aVOp);
        switch (aVOp.type()) {
            case Null:
                return this;
            case Set:
            case Delete:
                return aVOp;
            case Add:
                this.values.addAll(((AddOp) aVOp.cast(AddOp.class)).values);
                return this;
            case AddUnique:
            case Remove:
            case AddRelation:
            case RemoveRelation:
                return new CompoundOp(this.key, this, aVOp);
            case Increment:
                throw new UnsupportedOperationException("Could not increment an non-numberic value.");
            case Compound:
                ((CompoundOp) aVOp.cast(CompoundOp.class)).addFirst(this);
                return aVOp;
            default:
                throw new IllegalStateException("Unknow op type " + aVOp.type());
        }
    }
}

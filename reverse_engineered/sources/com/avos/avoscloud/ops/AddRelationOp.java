package com.avos.avoscloud.ops;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.ops.AVOp.OpType;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddRelationOp extends CollectionAddOp {
    private Set<AVObject> values = new HashSet();

    public Set<AVObject> getValues() {
        return this.values;
    }

    public AddRelationOp(String str, AVObject... aVObjectArr) {
        super(str, OpType.AddRelation);
        if (aVObjectArr != null) {
            for (Object add : aVObjectArr) {
                this.values.add(add);
            }
        }
    }

    public Object apply(Object obj) {
        return obj;
    }

    public Map<String, Object> encodeOp() {
        return AVUtils.createPointerArrayOpMap(this.key, this.type.name(), getValues());
    }

    public AVOp merge(AVOp aVOp) {
        assertKeyEquals(aVOp);
        switch (aVOp.type()) {
            case Null:
                return this;
            case Set:
            case Delete:
                return aVOp;
            case AddRelation:
                this.values.addAll(((AddRelationOp) aVOp.cast(AddRelationOp.class)).values);
                return this;
            case AddUnique:
            case Remove:
            case Add:
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

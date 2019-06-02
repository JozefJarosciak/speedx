package com.avos.avoscloud.ops;

import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.ops.AVOp.OpType;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RemoveOp extends CollectionOp {
    private Set<Object> values = new HashSet();

    public RemoveOp(String str, Collection<?> collection) {
        super(str, OpType.Remove);
        if (collection != null) {
            for (Object add : collection) {
                this.values.add(add);
            }
        }
    }

    public Set<Object> getValues() {
        return this.values;
    }

    public Map<String, Object> encodeOp() {
        return AVUtils.createArrayOpMap(this.key, "Remove", getParsedValues());
    }

    public Object apply(Object obj) {
        List linkedList = new LinkedList();
        if (obj != null) {
            linkedList.addAll((Collection) obj);
        }
        if (getValues() != null) {
            linkedList.removeAll(getValues());
        }
        return linkedList;
    }

    public AVOp merge(AVOp aVOp) {
        assertKeyEquals(aVOp);
        switch (aVOp.type()) {
            case Null:
                return this;
            case Set:
            case Delete:
                return aVOp;
            case Remove:
                this.values.addAll(((RemoveOp) aVOp.cast(RemoveOp.class)).values);
                return this;
            case AddUnique:
            case Add:
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

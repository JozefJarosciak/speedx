package com.avos.avoscloud.ops;

import com.alibaba.fastjson.annotation.JSONType;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.ops.AVOp.OpType;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@JSONType(ignores = {"parsedValues"})
public abstract class CollectionOp extends BaseOp {
    public abstract Collection getValues();

    public CollectionOp(String str, OpType opType) {
        super(str, opType);
    }

    public void setValues(Collection collection) {
        getValues().clear();
        getValues().addAll(collection);
    }

    public List getParsedValues() {
        List linkedList = new LinkedList();
        for (Object next : getValues()) {
            if (next instanceof AVObject) {
                linkedList.add(AVUtils.mapFromPointerObject((AVObject) next));
            } else if (next instanceof AVFile) {
                linkedList.add(AVUtils.mapFromFile((AVFile) next));
            } else {
                linkedList.add(next);
            }
        }
        return linkedList;
    }
}

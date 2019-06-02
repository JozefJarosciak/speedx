package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;

class Iterators$8 extends TransformedIterator<F, T> {
    final /* synthetic */ Function val$function;

    Iterators$8(Iterator it, Function function) {
        this.val$function = function;
        super(it);
    }

    T transform(F f) {
        return this.val$function.apply(f);
    }
}

package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;

class Runtime$1 implements RuntimeReplFactory {

    /* renamed from: com.facebook.stetho.inspector.protocol.module.Runtime$1$1 */
    class C31761 implements RuntimeRepl {
        C31761() {
        }

        public Object evaluate(String str) throws Throwable {
            return "Not supported with legacy Runtime module";
        }
    }

    Runtime$1() {
    }

    public RuntimeRepl newInstance() {
        return new C31761();
    }
}

package io.rong.imlib;

public abstract class RongIMClient$Callback {

    /* renamed from: io.rong.imlib.RongIMClient$Callback$1 */
    class C53421 implements Runnable {
        C53421() {
        }

        public void run() {
            RongIMClient$Callback.this.onSuccess();
        }
    }

    public abstract void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode);

    public abstract void onSuccess();

    public void onCallback() {
        RongIMClient.access$1600().post(new C53421());
    }

    public void onFail(final int i) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$Callback.this.onError(RongIMClient$ErrorCode.valueOf(i));
            }
        });
    }

    public void onFail(final RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$Callback.this.onError(rongIMClient$ErrorCode);
            }
        });
    }
}

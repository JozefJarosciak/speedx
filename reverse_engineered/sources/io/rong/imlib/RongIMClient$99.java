package io.rong.imlib;

class RongIMClient$99 extends RongIMClient$SendMessageCallback {
    final /* synthetic */ RongIMClient this$0;

    RongIMClient$99(RongIMClient rongIMClient) {
        this.this$0 = rongIMClient;
    }

    public void onError(Integer num, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (RongIMClient.access$3000(this.this$0) != null) {
            RongIMClient.access$3000(this.this$0).onError(rongIMClient$ErrorCode.getValue(), RongIMClient.access$200(this.this$0).getResources().getString(RongIMClient.access$200(this.this$0).getResources().getIdentifier("rc_init_failed", "string", RongIMClient.access$200(this.this$0).getPackageName())));
            RongIMClient.access$3002(this.this$0, null);
        }
    }

    public void onSuccess(Integer num) {
    }
}

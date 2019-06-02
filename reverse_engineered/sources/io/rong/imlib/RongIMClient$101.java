package io.rong.imlib;

class RongIMClient$101 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$CustomServiceProfile val$profile;

    RongIMClient$101(RongIMClient rongIMClient, RongIMClient$CustomServiceProfile rongIMClient$CustomServiceProfile) {
        this.this$0 = rongIMClient;
        this.val$profile = rongIMClient$CustomServiceProfile;
    }

    public void run() {
        if (RongIMClient.access$3000(this.this$0) != null && this.val$profile.groupList != null && this.val$profile.groupList.size() > 0) {
            RongIMClient.access$3000(this.this$0).onSelectGroup(this.val$profile.groupList);
        }
    }
}

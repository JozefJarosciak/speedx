package io.rong.imlib;

public interface RongCommonDefine {

    public enum GetMessageDirection {
        BEHIND(0),
        FRONT(1);
        
        int value;

        private GetMessageDirection(int i) {
            this.value = i;
        }

        int getValue() {
            return this.value;
        }
    }
}

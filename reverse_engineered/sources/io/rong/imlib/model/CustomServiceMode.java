package io.rong.imlib.model;

public enum CustomServiceMode {
    CUSTOM_SERVICE_MODE_NO_SERVICE(0),
    CUSTOM_SERVICE_MODE_ROBOT(1),
    CUSTOM_SERVICE_MODE_HUMAN(2),
    CUSTOM_SERVICE_MODE_ROBOT_FIRST(3),
    CUSTOM_SERVICE_MODE_HUMAN_FIRST(4);
    
    private int mode;

    private CustomServiceMode(int i) {
        this.mode = i;
    }

    public static CustomServiceMode valueOf(int i) {
        for (CustomServiceMode customServiceMode : values()) {
            if (customServiceMode.mode == i) {
                return customServiceMode;
            }
        }
        return CUSTOM_SERVICE_MODE_ROBOT;
    }
}

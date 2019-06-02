package io.rong.imkit.manager;

public abstract class IAudioState {
    abstract void handleMessage(AudioStateMessage audioStateMessage);

    void enter() {
    }
}

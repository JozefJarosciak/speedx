package org.java_websocket.handshake;

public interface ClientHandshakeBuilder extends ClientHandshake, HandshakeBuilder {
    void setResourceDescriptor(String str);
}

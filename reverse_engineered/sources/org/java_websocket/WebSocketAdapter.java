package org.java_websocket;

import java.net.InetSocketAddress;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public abstract class WebSocketAdapter implements WebSocketListener {
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket webSocket, Draft draft, ClientHandshake clientHandshake) throws InvalidDataException {
        return new HandshakeImpl1Server();
    }

    public void onWebsocketHandshakeReceivedAsClient(WebSocket webSocket, ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidDataException {
    }

    public void onWebsocketHandshakeSentAsClient(WebSocket webSocket, ClientHandshake clientHandshake) throws InvalidDataException {
    }

    public void onWebsocketMessageFragment(WebSocket webSocket, Framedata framedata) {
    }

    public void onWebsocketPing(WebSocket webSocket, Framedata framedata) {
        Framedata framedataImpl1 = new FramedataImpl1(framedata);
        framedataImpl1.setOptcode(Opcode.PONG);
        webSocket.sendFrame(framedataImpl1);
    }

    public void onWebsocketPong(WebSocket webSocket, Framedata framedata) {
    }

    public String getFlashPolicy(WebSocket webSocket) throws InvalidDataException {
        InetSocketAddress localSocketAddress = webSocket.getLocalSocketAddress();
        if (localSocketAddress == null) {
            throw new InvalidHandshakeException("socket not bound");
        }
        StringBuffer stringBuffer = new StringBuffer(90);
        stringBuffer.append("<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"");
        stringBuffer.append(localSocketAddress.getPort());
        stringBuffer.append("\" /></cross-domain-policy>\u0000");
        return stringBuffer.toString();
    }
}

package org.java_websocket.drafts;

import com.google.common.net.HttpHeaders;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExedeedException;
import org.java_websocket.exceptions.NotSendableException;
import org.java_websocket.framing.FrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

public class Draft_75 extends Draft {
    public static final byte CR = (byte) 13;
    public static final byte END_OF_FRAME = (byte) -1;
    public static final byte LF = (byte) 10;
    public static final byte START_OF_FRAME = (byte) 0;
    protected ByteBuffer currentFrame;
    protected boolean readingState = false;
    protected List<Framedata> readyframes = new LinkedList();
    private final Random reuseableRandom = new Random();

    public HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) {
        return (clientHandshake.getFieldValue("WebSocket-Origin").equals(serverHandshake.getFieldValue(HttpHeaders.ORIGIN)) && basicAccept(serverHandshake)) ? HandshakeState.MATCHED : HandshakeState.NOT_MATCHED;
    }

    public HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) {
        if (clientHandshake.hasFieldValue(HttpHeaders.ORIGIN) && basicAccept(clientHandshake)) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public ByteBuffer createBinaryFrame(Framedata framedata) {
        if (framedata.getOpcode() != Opcode.TEXT) {
            throw new RuntimeException("only text frames supported");
        }
        ByteBuffer payloadData = framedata.getPayloadData();
        ByteBuffer allocate = ByteBuffer.allocate(payloadData.remaining() + 2);
        allocate.put((byte) 0);
        payloadData.mark();
        allocate.put(payloadData);
        payloadData.reset();
        allocate.put((byte) -1);
        allocate.flip();
        return allocate;
    }

    public List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z) {
        throw new RuntimeException("not yet implemented");
    }

    public List<Framedata> createFrames(String str, boolean z) {
        FrameBuilder framedataImpl1 = new FramedataImpl1();
        try {
            framedataImpl1.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(str)));
            framedataImpl1.setFin(true);
            framedataImpl1.setOptcode(Opcode.TEXT);
            framedataImpl1.setTransferemasked(z);
            return Collections.singletonList(framedataImpl1);
        } catch (Throwable e) {
            throw new NotSendableException(e);
        }
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        clientHandshakeBuilder.put("Upgrade", "WebSocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        if (!clientHandshakeBuilder.hasFieldValue(HttpHeaders.ORIGIN)) {
            clientHandshakeBuilder.put(HttpHeaders.ORIGIN, "random" + this.reuseableRandom.nextInt());
        }
        return clientHandshakeBuilder;
    }

    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.setHttpStatusMessage("Web Socket Protocol Handshake");
        serverHandshakeBuilder.put("Upgrade", "WebSocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.put("WebSocket-Origin", clientHandshake.getFieldValue(HttpHeaders.ORIGIN));
        serverHandshakeBuilder.put("WebSocket-Location", "ws://" + clientHandshake.getFieldValue("Host") + clientHandshake.getResourceDescriptor());
        return serverHandshakeBuilder;
    }

    protected List<Framedata> translateRegularFrame(ByteBuffer byteBuffer) throws InvalidDataException {
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            if (b == (byte) 0) {
                if (this.readingState) {
                    throw new InvalidFrameException("unexpected START_OF_FRAME");
                }
                this.readingState = true;
            } else if (b == (byte) -1) {
                if (this.readingState) {
                    if (this.currentFrame != null) {
                        this.currentFrame.flip();
                        FramedataImpl1 framedataImpl1 = new FramedataImpl1();
                        framedataImpl1.setPayload(this.currentFrame);
                        framedataImpl1.setFin(true);
                        framedataImpl1.setOptcode(Opcode.TEXT);
                        this.readyframes.add(framedataImpl1);
                        this.currentFrame = null;
                        byteBuffer.mark();
                    }
                    this.readingState = false;
                } else {
                    throw new InvalidFrameException("unexpected END_OF_FRAME");
                }
            } else if (!this.readingState) {
                return null;
            } else {
                if (this.currentFrame == null) {
                    this.currentFrame = createBuffer();
                } else if (!this.currentFrame.hasRemaining()) {
                    this.currentFrame = increaseBuffer(this.currentFrame);
                }
                this.currentFrame.put(b);
            }
        }
        List<Framedata> list = this.readyframes;
        this.readyframes = new LinkedList();
        return list;
    }

    public List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException {
        List<Framedata> translateRegularFrame = translateRegularFrame(byteBuffer);
        if (translateRegularFrame != null) {
            return translateRegularFrame;
        }
        throw new InvalidDataException(1002);
    }

    public void reset() {
        this.readingState = false;
        this.currentFrame = null;
    }

    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.NONE;
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(INITIAL_FAMESIZE);
    }

    public ByteBuffer increaseBuffer(ByteBuffer byteBuffer) throws LimitExedeedException, InvalidDataException {
        byteBuffer.flip();
        ByteBuffer allocate = ByteBuffer.allocate(checkAlloc(byteBuffer.capacity() * 2));
        allocate.put(byteBuffer);
        return allocate;
    }

    public Draft copyInstance() {
        return new Draft_75();
    }
}

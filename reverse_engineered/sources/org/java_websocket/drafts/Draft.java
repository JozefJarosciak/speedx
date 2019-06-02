package org.java_websocket.drafts;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.java_websocket.WebSocket.Role;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExedeedException;
import org.java_websocket.framing.FrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

public abstract class Draft {
    public static final byte[] FLASH_POLICY_REQUEST = Charsetfunctions.utf8Bytes("<policy-file-request/>\u0000");
    public static int INITIAL_FAMESIZE = 64;
    public static int MAX_FAME_SIZE = 1000;
    protected Opcode continuousFrameType = null;
    protected Role role = null;

    public enum CloseHandshakeType {
        NONE,
        ONEWAY,
        TWOWAY
    }

    public enum HandshakeState {
        MATCHED,
        NOT_MATCHED
    }

    public abstract HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException;

    public abstract HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) throws InvalidHandshakeException;

    public abstract Draft copyInstance();

    public abstract ByteBuffer createBinaryFrame(Framedata framedata);

    public abstract List<Framedata> createFrames(String str, boolean z);

    public abstract List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z);

    public abstract CloseHandshakeType getCloseHandshakeType();

    public abstract ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException;

    public abstract HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException;

    public abstract void reset();

    public abstract List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException;

    public static ByteBuffer readLine(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b = (byte) 48;
        while (byteBuffer.hasRemaining()) {
            byte b2 = byteBuffer.get();
            allocate.put(b2);
            if (b == (byte) 13 && b2 == (byte) 10) {
                allocate.limit(allocate.position() - 2);
                allocate.position(0);
                return allocate;
            }
            b = b2;
        }
        byteBuffer.position(byteBuffer.position() - allocate.position());
        return null;
    }

    public static String readStringLine(ByteBuffer byteBuffer) {
        ByteBuffer readLine = readLine(byteBuffer);
        return readLine == null ? null : Charsetfunctions.stringAscii(readLine.array(), 0, readLine.limit());
    }

    public static HandshakeBuilder translateHandshakeHttp(ByteBuffer byteBuffer, Role role) throws InvalidHandshakeException, IncompleteHandshakeException {
        String readStringLine = readStringLine(byteBuffer);
        if (readStringLine == null) {
            throw new IncompleteHandshakeException(byteBuffer.capacity() + 128);
        }
        String[] split = readStringLine.split(" ", 3);
        if (split.length != 3) {
            throw new InvalidHandshakeException();
        }
        HandshakeBuilder handshakeImpl1Server;
        if (role == Role.CLIENT) {
            handshakeImpl1Server = new HandshakeImpl1Server();
            ServerHandshakeBuilder serverHandshakeBuilder = (ServerHandshakeBuilder) handshakeImpl1Server;
            serverHandshakeBuilder.setHttpStatus(Short.parseShort(split[1]));
            serverHandshakeBuilder.setHttpStatusMessage(split[2]);
        } else {
            handshakeImpl1Server = new HandshakeImpl1Client();
            handshakeImpl1Server.setResourceDescriptor(split[1]);
        }
        readStringLine = readStringLine(byteBuffer);
        while (readStringLine != null && readStringLine.length() > 0) {
            String[] split2 = readStringLine.split(":", 2);
            if (split2.length != 2) {
                throw new InvalidHandshakeException("not an http header");
            }
            handshakeImpl1Server.put(split2[0], split2[1].replaceFirst("^ +", ""));
            readStringLine = readStringLine(byteBuffer);
        }
        if (readStringLine != null) {
            return handshakeImpl1Server;
        }
        throw new IncompleteHandshakeException();
    }

    protected boolean basicAccept(Handshakedata handshakedata) {
        return handshakedata.getFieldValue("Upgrade").equalsIgnoreCase("websocket") && handshakedata.getFieldValue("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public List<Framedata> continuousFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        if (opcode == Opcode.BINARY || opcode == Opcode.TEXT || opcode == Opcode.TEXT) {
            if (this.continuousFrameType != null) {
                this.continuousFrameType = Opcode.CONTINUOUS;
            } else {
                this.continuousFrameType = opcode;
            }
            FrameBuilder framedataImpl1 = new FramedataImpl1(this.continuousFrameType);
            try {
                framedataImpl1.setPayload(byteBuffer);
                framedataImpl1.setFin(z);
                if (z) {
                    this.continuousFrameType = null;
                } else {
                    this.continuousFrameType = opcode;
                }
                return Collections.singletonList(framedataImpl1);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("Only Opcode.BINARY or  Opcode.TEXT are allowed");
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, Role role) {
        return createHandshake(handshakedata, role, true);
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, Role role, boolean z) {
        StringBuilder stringBuilder = new StringBuilder(100);
        if (handshakedata instanceof ClientHandshake) {
            stringBuilder.append("GET ");
            stringBuilder.append(((ClientHandshake) handshakedata).getResourceDescriptor());
            stringBuilder.append(" HTTP/1.1");
        } else if (handshakedata instanceof ServerHandshake) {
            stringBuilder.append("HTTP/1.1 101 " + ((ServerHandshake) handshakedata).getHttpStatusMessage());
        } else {
            throw new RuntimeException("unknow role");
        }
        stringBuilder.append("\r\n");
        Iterator iterateHttpFields = handshakedata.iterateHttpFields();
        while (iterateHttpFields.hasNext()) {
            String str = (String) iterateHttpFields.next();
            String fieldValue = handshakedata.getFieldValue(str);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(fieldValue);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        byte[] asciiBytes = Charsetfunctions.asciiBytes(stringBuilder.toString());
        byte[] content = z ? handshakedata.getContent() : null;
        ByteBuffer allocate = ByteBuffer.allocate((content == null ? 0 : content.length) + asciiBytes.length);
        allocate.put(asciiBytes);
        if (content != null) {
            allocate.put(content);
        }
        allocate.flip();
        return Collections.singletonList(allocate);
    }

    public Handshakedata translateHandshake(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        return translateHandshakeHttp(byteBuffer, this.role);
    }

    public int checkAlloc(int i) throws LimitExedeedException, InvalidDataException {
        if (i >= 0) {
            return i;
        }
        throw new InvalidDataException(1002, "Negative count");
    }

    public void setParseMode(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }
}

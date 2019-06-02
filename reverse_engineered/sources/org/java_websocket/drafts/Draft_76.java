package org.java_websocket.drafts;

import com.google.common.net.HttpHeaders;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.WebSocket.Role;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.CloseFrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public class Draft_76 extends Draft_75 {
    private static final byte[] closehandshake = new byte[]{(byte) -1, (byte) 0};
    private boolean failed = false;
    private final Random reuseableRandom = new Random();

    public static byte[] createChallenge(String str, String str2, byte[] bArr) throws InvalidHandshakeException {
        byte[] part = getPart(str);
        byte[] part2 = getPart(str2);
        try {
            return MessageDigest.getInstance("MD5").digest(new byte[]{part[0], part[1], part[2], part[3], part2[0], part2[1], part2[2], part2[3], bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateKey() {
        Random random = new Random();
        long nextInt = (long) (random.nextInt(12) + 1);
        String l = Long.toString(((long) (random.nextInt(Math.abs(new Long(4294967295L / nextInt).intValue())) + 1)) * nextInt);
        int nextInt2 = random.nextInt(12) + 1;
        for (int i = 0; i < nextInt2; i++) {
            int abs = Math.abs(random.nextInt(l.length()));
            char nextInt3 = (char) (random.nextInt(95) + 33);
            if (nextInt3 >= '0' && nextInt3 <= '9') {
                nextInt3 = (char) (nextInt3 - 15);
            }
            l = abs;
        }
        String str = l;
        for (int i2 = 0; ((long) i2) < nextInt; i2++) {
            str = Math.abs(random.nextInt(str.length() - 1) + 1);
        }
        return str;
    }

    private static byte[] getPart(String str) throws InvalidHandshakeException {
        try {
            long parseLong = Long.parseLong(str.replaceAll("[^0-9]", ""));
            long length = (long) (str.split(" ").length - 1);
            if (length == 0) {
                throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key2/)");
            }
            parseLong = new Long(parseLong / length).longValue();
            return new byte[]{(byte) ((int) (parseLong >> 24)), (byte) ((int) ((parseLong << 8) >> 24)), (byte) ((int) ((parseLong << 16) >> 24)), (byte) ((int) ((parseLong << 24) >> 24))};
        } catch (NumberFormatException e) {
            throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
    }

    public HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) {
        if (this.failed) {
            return HandshakeState.NOT_MATCHED;
        }
        try {
            if (!serverHandshake.getFieldValue("Sec-WebSocket-Origin").equals(clientHandshake.getFieldValue(HttpHeaders.ORIGIN)) || !basicAccept(serverHandshake)) {
                return HandshakeState.NOT_MATCHED;
            }
            byte[] content = serverHandshake.getContent();
            if (content == null || content.length == 0) {
                throw new IncompleteHandshakeException();
            } else if (Arrays.equals(content, createChallenge(clientHandshake.getFieldValue("Sec-WebSocket-Key1"), clientHandshake.getFieldValue("Sec-WebSocket-Key2"), clientHandshake.getContent()))) {
                return HandshakeState.MATCHED;
            } else {
                return HandshakeState.NOT_MATCHED;
            }
        } catch (Throwable e) {
            throw new RuntimeException("bad handshakerequest", e);
        }
    }

    public HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) {
        if (clientHandshake.getFieldValue("Upgrade").equals("WebSocket") && clientHandshake.getFieldValue("Connection").contains("Upgrade") && clientHandshake.getFieldValue("Sec-WebSocket-Key1").length() > 0 && !clientHandshake.getFieldValue("Sec-WebSocket-Key2").isEmpty() && clientHandshake.hasFieldValue(HttpHeaders.ORIGIN)) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.put("Upgrade", "WebSocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        clientHandshakeBuilder.put("Sec-WebSocket-Key1", generateKey());
        clientHandshakeBuilder.put("Sec-WebSocket-Key2", generateKey());
        if (!clientHandshakeBuilder.hasFieldValue(HttpHeaders.ORIGIN)) {
            clientHandshakeBuilder.put(HttpHeaders.ORIGIN, "random" + this.reuseableRandom.nextInt());
        }
        byte[] bArr = new byte[8];
        this.reuseableRandom.nextBytes(bArr);
        clientHandshakeBuilder.setContent(bArr);
        return clientHandshakeBuilder;
    }

    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.setHttpStatusMessage("WebSocket Protocol Handshake");
        serverHandshakeBuilder.put("Upgrade", "WebSocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.put("Sec-WebSocket-Origin", clientHandshake.getFieldValue(HttpHeaders.ORIGIN));
        serverHandshakeBuilder.put("Sec-WebSocket-Location", "ws://" + clientHandshake.getFieldValue("Host") + clientHandshake.getResourceDescriptor());
        String fieldValue = clientHandshake.getFieldValue("Sec-WebSocket-Key1");
        String fieldValue2 = clientHandshake.getFieldValue("Sec-WebSocket-Key2");
        byte[] content = clientHandshake.getContent();
        if (fieldValue == null || fieldValue2 == null || content == null || content.length != 8) {
            throw new InvalidHandshakeException("Bad keys");
        }
        serverHandshakeBuilder.setContent(createChallenge(fieldValue, fieldValue2, content));
        return serverHandshakeBuilder;
    }

    public Handshakedata translateHandshake(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        Handshakedata translateHandshakeHttp = Draft.translateHandshakeHttp(byteBuffer, this.role);
        if ((translateHandshakeHttp.hasFieldValue("Sec-WebSocket-Key1") || this.role == Role.CLIENT) && !translateHandshakeHttp.hasFieldValue("Sec-WebSocket-Version")) {
            byte[] bArr = new byte[(this.role == Role.SERVER ? 8 : 16)];
            try {
                byteBuffer.get(bArr);
                translateHandshakeHttp.setContent(bArr);
            } catch (BufferUnderflowException e) {
                throw new IncompleteHandshakeException(byteBuffer.capacity() + 16);
            }
        }
        return translateHandshakeHttp;
    }

    public List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException {
        byteBuffer.mark();
        List<Framedata> translateRegularFrame = super.translateRegularFrame(byteBuffer);
        if (translateRegularFrame == null) {
            byteBuffer.reset();
            translateRegularFrame = this.readyframes;
            this.readingState = true;
            if (this.currentFrame == null) {
                this.currentFrame = ByteBuffer.allocate(2);
                if (byteBuffer.remaining() > this.currentFrame.remaining()) {
                    throw new InvalidFrameException();
                }
                this.currentFrame.put(byteBuffer);
                if (this.currentFrame.hasRemaining()) {
                    this.readyframes = new LinkedList();
                } else if (Arrays.equals(this.currentFrame.array(), closehandshake)) {
                    translateRegularFrame.add(new CloseFrameBuilder(1000));
                } else {
                    throw new InvalidFrameException();
                }
            }
            throw new InvalidFrameException();
        }
        return translateRegularFrame;
    }

    public ByteBuffer createBinaryFrame(Framedata framedata) {
        if (framedata.getOpcode() == Opcode.CLOSING) {
            return ByteBuffer.wrap(closehandshake);
        }
        return super.createBinaryFrame(framedata);
    }

    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.ONEWAY;
    }

    public Draft copyInstance() {
        return new Draft_76();
    }
}

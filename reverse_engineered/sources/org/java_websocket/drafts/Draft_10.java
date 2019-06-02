package org.java_websocket.drafts;

import com.avos.avoscloud.AVException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.WebSocket.Role;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExedeedException;
import org.java_websocket.exceptions.NotSendableException;
import org.java_websocket.framing.CloseFrameBuilder;
import org.java_websocket.framing.FrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Base64;
import org.java_websocket.util.Charsetfunctions;

public class Draft_10 extends Draft {
    static final /* synthetic */ boolean $assertionsDisabled = (!Draft_10.class.desiredAssertionStatus());
    private Framedata fragmentedframe = null;
    private ByteBuffer incompleteframe;
    private final Random reuseableRandom = new Random();

    private class IncompleteException extends Throwable {
        private static final long serialVersionUID = 7330519489840500997L;
        private int preferedsize;

        public IncompleteException(int i) {
            this.preferedsize = i;
        }

        public int getPreferedSize() {
            return this.preferedsize;
        }
    }

    public static int readVersion(Handshakedata handshakedata) {
        int i = -1;
        String fieldValue = handshakedata.getFieldValue("Sec-WebSocket-Version");
        if (fieldValue.length() > 0) {
            try {
                i = new Integer(fieldValue.trim()).intValue();
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException {
        if (!clientHandshake.hasFieldValue("Sec-WebSocket-Key") || !serverHandshake.hasFieldValue("Sec-WebSocket-Accept")) {
            return HandshakeState.NOT_MATCHED;
        }
        if (generateFinalKey(clientHandshake.getFieldValue("Sec-WebSocket-Key")).equals(serverHandshake.getFieldValue("Sec-WebSocket-Accept"))) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) throws InvalidHandshakeException {
        int readVersion = readVersion(clientHandshake);
        if (readVersion == 7 || readVersion == 8) {
            return basicAccept(clientHandshake) ? HandshakeState.MATCHED : HandshakeState.NOT_MATCHED;
        } else {
            return HandshakeState.NOT_MATCHED;
        }
    }

    public ByteBuffer createBinaryFrame(Framedata framedata) {
        int i;
        int i2 = -128;
        int i3 = 0;
        ByteBuffer payloadData = framedata.getPayloadData();
        int i4 = this.role == Role.CLIENT ? 1 : 0;
        int i5 = payloadData.remaining() <= AVException.INVALID_EMAIL_ADDRESS ? 1 : payloadData.remaining() <= 65535 ? 2 : 8;
        if (i5 > 1) {
            i = i5 + 1;
        } else {
            i = i5;
        }
        ByteBuffer allocate = ByteBuffer.allocate(((i4 != 0 ? 4 : 0) + (i + 1)) + payloadData.remaining());
        byte fromOpcode = fromOpcode(framedata.getOpcode());
        if (framedata.isFin()) {
            i = -128;
        } else {
            i = 0;
        }
        allocate.put((byte) (((byte) i) | fromOpcode));
        byte[] toByteArray = toByteArray((long) payloadData.remaining(), i5);
        if ($assertionsDisabled || toByteArray.length == i5) {
            if (i5 == 1) {
                byte b = toByteArray[0];
                if (i4 == 0) {
                    i2 = 0;
                }
                allocate.put((byte) (b | i2));
            } else if (i5 == 2) {
                if (i4 == 0) {
                    i2 = 0;
                }
                allocate.put((byte) (i2 | 126));
                allocate.put(toByteArray);
            } else if (i5 == 8) {
                if (i4 == 0) {
                    i2 = 0;
                }
                allocate.put((byte) (i2 | 127));
                allocate.put(toByteArray);
            } else {
                throw new RuntimeException("Size representation not supported/specified");
            }
            if (i4 != 0) {
                ByteBuffer allocate2 = ByteBuffer.allocate(4);
                allocate2.putInt(this.reuseableRandom.nextInt());
                allocate.put(allocate2.array());
                while (payloadData.hasRemaining()) {
                    allocate.put((byte) (payloadData.get() ^ allocate2.get(i3 % 4)));
                    i3++;
                }
            } else {
                allocate.put(payloadData);
            }
            if ($assertionsDisabled || allocate.remaining() == 0) {
                allocate.flip();
                return allocate;
            }
            throw new AssertionError(allocate.remaining());
        }
        throw new AssertionError();
    }

    public List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z) {
        FrameBuilder framedataImpl1 = new FramedataImpl1();
        try {
            framedataImpl1.setPayload(byteBuffer);
            framedataImpl1.setFin(true);
            framedataImpl1.setOptcode(Opcode.BINARY);
            framedataImpl1.setTransferemasked(z);
            return Collections.singletonList(framedataImpl1);
        } catch (Throwable e) {
            throw new NotSendableException(e);
        }
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

    private byte fromOpcode(Opcode opcode) {
        if (opcode == Opcode.CONTINUOUS) {
            return (byte) 0;
        }
        if (opcode == Opcode.TEXT) {
            return (byte) 1;
        }
        if (opcode == Opcode.BINARY) {
            return (byte) 2;
        }
        if (opcode == Opcode.CLOSING) {
            return (byte) 8;
        }
        if (opcode == Opcode.PING) {
            return (byte) 9;
        }
        if (opcode == Opcode.PONG) {
            return (byte) 10;
        }
        throw new RuntimeException("Don't know how to handle " + opcode.toString());
    }

    private String generateFinalKey(String str) {
        String str2 = str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        try {
            return Base64.encodeBytes(MessageDigest.getInstance("SHA1").digest(str2.getBytes()));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.put("Upgrade", "websocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        clientHandshakeBuilder.put("Sec-WebSocket-Version", "8");
        byte[] bArr = new byte[16];
        this.reuseableRandom.nextBytes(bArr);
        clientHandshakeBuilder.put("Sec-WebSocket-Key", Base64.encodeBytes(bArr));
        return clientHandshakeBuilder;
    }

    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.put("Upgrade", "websocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.setHttpStatusMessage("Switching Protocols");
        String fieldValue = clientHandshake.getFieldValue("Sec-WebSocket-Key");
        if (fieldValue == null) {
            throw new InvalidHandshakeException("missing Sec-WebSocket-Key");
        }
        serverHandshakeBuilder.put("Sec-WebSocket-Accept", generateFinalKey(fieldValue));
        return serverHandshakeBuilder;
    }

    private byte[] toByteArray(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((int) (j >>> (i2 - (i3 * 8))));
        }
        return bArr;
    }

    private Opcode toOpcode(byte b) throws InvalidFrameException {
        switch (b) {
            case (byte) 0:
                return Opcode.CONTINUOUS;
            case (byte) 1:
                return Opcode.TEXT;
            case (byte) 2:
                return Opcode.BINARY;
            case (byte) 8:
                return Opcode.CLOSING;
            case (byte) 9:
                return Opcode.PING;
            case (byte) 10:
                return Opcode.PONG;
            default:
                throw new InvalidFrameException("unknow optcode " + ((short) b));
        }
    }

    public List<Framedata> translateFrame(ByteBuffer byteBuffer) throws LimitExedeedException, InvalidDataException {
        List<Framedata> linkedList = new LinkedList();
        if (this.incompleteframe != null) {
            try {
                byteBuffer.mark();
                int remaining = byteBuffer.remaining();
                int remaining2 = this.incompleteframe.remaining();
                if (remaining2 > remaining) {
                    this.incompleteframe.put(byteBuffer.array(), byteBuffer.position(), remaining);
                    byteBuffer.position(remaining + byteBuffer.position());
                    return Collections.emptyList();
                }
                this.incompleteframe.put(byteBuffer.array(), byteBuffer.position(), remaining2);
                byteBuffer.position(byteBuffer.position() + remaining2);
                linkedList.add(translateSingleFrame((ByteBuffer) this.incompleteframe.duplicate().position(0)));
                this.incompleteframe = null;
            } catch (IncompleteException e) {
                this.incompleteframe.limit();
                r0 = ByteBuffer.allocate(checkAlloc(e.getPreferedSize()));
                if ($assertionsDisabled || r0.limit() > this.incompleteframe.limit()) {
                    ByteBuffer allocate;
                    this.incompleteframe.rewind();
                    allocate.put(this.incompleteframe);
                    this.incompleteframe = allocate;
                    return translateFrame(byteBuffer);
                }
                throw new AssertionError();
            }
        }
        while (byteBuffer.hasRemaining()) {
            byteBuffer.mark();
            try {
                linkedList.add(translateSingleFrame(byteBuffer));
            } catch (IncompleteException e2) {
                byteBuffer.reset();
                this.incompleteframe = ByteBuffer.allocate(checkAlloc(e2.getPreferedSize()));
                this.incompleteframe.put(byteBuffer);
            }
        }
        return linkedList;
    }

    public Framedata translateSingleFrame(ByteBuffer byteBuffer) throws IncompleteException, InvalidDataException {
        int i = 2;
        int i2 = 0;
        int remaining = byteBuffer.remaining();
        if (remaining < 2) {
            throw new IncompleteException(2);
        }
        boolean z;
        byte b = byteBuffer.get();
        if ((b >> 8) != 0) {
            z = true;
        } else {
            z = false;
        }
        byte b2 = (byte) ((b & 127) >> 4);
        if (b2 != (byte) 0) {
            throw new InvalidFrameException("bad rsv " + b2);
        }
        byte b3 = byteBuffer.get();
        int i3 = (b3 & -128) != 0 ? 1 : 0;
        int i4 = (byte) (b3 & 127);
        Opcode toOpcode = toOpcode((byte) (b & 15));
        if (z || !(toOpcode == Opcode.PING || toOpcode == Opcode.PONG || toOpcode == Opcode.CLOSING)) {
            int i5;
            if (i4 < 0 || i4 > AVException.INVALID_EMAIL_ADDRESS) {
                if (toOpcode == Opcode.PING || toOpcode == Opcode.PONG || toOpcode == Opcode.CLOSING) {
                    throw new InvalidFrameException("more than 125 octets");
                } else if (i4 == 126) {
                    if (remaining < 4) {
                        throw new IncompleteException(4);
                    }
                    byte[] bArr = new byte[3];
                    bArr[1] = byteBuffer.get();
                    bArr[2] = byteBuffer.get();
                    i4 = new BigInteger(bArr).intValue();
                    i = 4;
                } else if (remaining < 10) {
                    throw new IncompleteException(10);
                } else {
                    byte[] bArr2 = new byte[8];
                    for (i5 = 0; i5 < 8; i5++) {
                        bArr2[i5] = byteBuffer.get();
                    }
                    long longValue = new BigInteger(bArr2).longValue();
                    if (longValue > 2147483647L) {
                        throw new LimitExedeedException("Payloadsize is to big...");
                    }
                    i = 10;
                    i4 = (int) longValue;
                }
            }
            if (i3 != 0) {
                i5 = 4;
            } else {
                i5 = 0;
            }
            i5 = (i5 + i) + i4;
            if (remaining < i5) {
                throw new IncompleteException(i5);
            }
            Framedata closeFrameBuilder;
            ByteBuffer allocate = ByteBuffer.allocate(checkAlloc(i4));
            if (i3 != 0) {
                byte[] bArr3 = new byte[4];
                byteBuffer.get(bArr3);
                while (i2 < i4) {
                    allocate.put((byte) (byteBuffer.get() ^ bArr3[i2 % 4]));
                    i2++;
                }
            } else {
                allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
                byteBuffer.position(byteBuffer.position() + allocate.limit());
            }
            if (toOpcode == Opcode.CLOSING) {
                closeFrameBuilder = new CloseFrameBuilder();
            } else {
                closeFrameBuilder = new FramedataImpl1();
                closeFrameBuilder.setFin(z);
                closeFrameBuilder.setOptcode(toOpcode);
            }
            allocate.flip();
            closeFrameBuilder.setPayload(allocate);
            return closeFrameBuilder;
        }
        throw new InvalidFrameException("control frames may no be fragmented");
    }

    public void reset() {
        this.incompleteframe = null;
    }

    public Draft copyInstance() {
        return new Draft_10();
    }

    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.TWOWAY;
    }
}

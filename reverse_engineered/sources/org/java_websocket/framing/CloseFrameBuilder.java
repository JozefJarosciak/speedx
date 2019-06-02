package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.util.Charsetfunctions;

public class CloseFrameBuilder extends FramedataImpl1 implements CloseFrame {
    static final ByteBuffer emptybytebuffer = ByteBuffer.allocate(0);
    private int code;
    private String reason;

    public CloseFrameBuilder() {
        super(Opcode.CLOSING);
        setFin(true);
    }

    public CloseFrameBuilder(int i) throws InvalidDataException {
        super(Opcode.CLOSING);
        setFin(true);
        setCodeAndMessage(i, "");
    }

    public CloseFrameBuilder(int i, String str) throws InvalidDataException {
        super(Opcode.CLOSING);
        setFin(true);
        setCodeAndMessage(i, str);
    }

    private void setCodeAndMessage(int i, String str) throws InvalidDataException {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = str;
        }
        if (i == 1015) {
            str2 = "";
            i = 1005;
        }
        if (i != 1005) {
            byte[] utf8Bytes = Charsetfunctions.utf8Bytes(str2);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.putInt(i);
            allocate.position(2);
            ByteBuffer allocate2 = ByteBuffer.allocate(utf8Bytes.length + 2);
            allocate2.put(allocate);
            allocate2.put(utf8Bytes);
            allocate2.rewind();
            setPayload(allocate2);
        } else if (str2.length() > 0) {
            throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
        }
    }

    private void initCloseCode() throws InvalidFrameException {
        this.code = 1005;
        ByteBuffer payloadData = super.getPayloadData();
        payloadData.mark();
        if (payloadData.remaining() >= 2) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(payloadData.getShort());
            allocate.position(0);
            this.code = allocate.getInt();
            if (this.code == 1006 || this.code == 1015 || this.code == 1005 || this.code > 4999 || this.code < 1000 || this.code == 1004) {
                throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
            }
        }
        payloadData.reset();
    }

    public int getCloseCode() {
        return this.code;
    }

    private void initMessage() throws InvalidDataException {
        if (this.code == 1005) {
            this.reason = Charsetfunctions.stringUtf8(super.getPayloadData());
            return;
        }
        ByteBuffer payloadData = super.getPayloadData();
        int position = payloadData.position();
        try {
            payloadData.position(payloadData.position() + 2);
            this.reason = Charsetfunctions.stringUtf8(payloadData);
            payloadData.position(position);
        } catch (Throwable e) {
            throw new InvalidFrameException(e);
        } catch (Throwable th) {
            payloadData.position(position);
        }
    }

    public String getMessage() {
        return this.reason;
    }

    public String toString() {
        return super.toString() + "code: " + this.code;
    }

    public void setPayload(ByteBuffer byteBuffer) throws InvalidDataException {
        super.setPayload(byteBuffer);
        initCloseCode();
        initMessage();
    }

    public ByteBuffer getPayloadData() {
        if (this.code == 1005) {
            return emptybytebuffer;
        }
        return super.getPayloadData();
    }
}

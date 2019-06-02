package io.rong.push.core;

import android.support.v4.view.MotionEventCompat;
import io.rong.push.common.RLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class PushProtocalStack {

    public static abstract class Message {
        private final Header header;
        private byte headerCode;

        public static class Header {
            private boolean dup;
            private QoS qos;
            private boolean retain;
            private Type type;

            private Header(Type type, boolean z, QoS qoS, boolean z2) {
                this.qos = QoS.AT_MOST_ONCE;
                this.type = type;
                this.retain = z;
                this.qos = qoS;
                this.dup = z2;
            }

            public Header(byte b) {
                boolean z;
                boolean z2 = true;
                this.qos = QoS.AT_MOST_ONCE;
                if ((b & 1) > 0) {
                    z = true;
                } else {
                    z = false;
                }
                this.retain = z;
                this.qos = QoS.valueOf((b & 6) >> 1);
                if ((b & 8) <= 0) {
                    z2 = false;
                }
                this.dup = z2;
                this.type = Type.valueOf((b >> 4) & 15);
            }

            public Type getType() {
                return this.type;
            }

            private byte encode() {
                int i;
                int i2 = 0;
                byte access$000 = (byte) (this.type.val << 4);
                if (this.retain) {
                    i = 1;
                } else {
                    i = 0;
                }
                byte b = (byte) (((byte) (i | access$000)) | (this.qos.val << 1));
                if (this.dup) {
                    i2 = 8;
                }
                return (byte) (b | i2);
            }

            public String toString() {
                return "Header [type=" + this.type + ", retain=" + this.retain + ", qos=" + this.qos + ", dup=" + this.dup + "]";
            }
        }

        public enum Type {
            CONNECT(1),
            CONNACK(2),
            PUBLISH(3),
            PUBACK(4),
            QUERY(5),
            QUERYACK(6),
            QUERYCON(7),
            SUBSCRIBE(8),
            SUBACK(9),
            UNSUBSCRIBE(10),
            UNSUBACK(11),
            PINGREQ(12),
            PINGRESP(13),
            DISCONNECT(14);
            
            private final int val;

            private Type(int i) {
                this.val = i;
            }

            static Type valueOf(int i) {
                for (Type type : values()) {
                    if (type.val == i) {
                        return type;
                    }
                }
                return null;
            }
        }

        public Message(Type type) {
            this.header = new Header(type, false, QoS.AT_MOST_ONCE, false);
        }

        public Message(Header header) throws IOException {
            this.header = header;
        }

        final void read(InputStream inputStream) throws IOException {
            readMessage(inputStream, readMsgLength(inputStream));
        }

        public final void write(OutputStream outputStream) throws IOException {
            this.headerCode = this.header.encode();
            outputStream.write(this.headerCode);
            writeMsgCode(outputStream);
            writeMsgLength(outputStream);
            writeMessage(outputStream);
        }

        private int readMsgLength(InputStream inputStream) throws IOException {
            int i = 0;
            int i2 = 1;
            int read;
            do {
                read = inputStream.read();
                i += (read & 127) * i2;
                i2 *= 128;
            } while ((read & 128) > 0);
            return i;
        }

        private void writeMsgLength(OutputStream outputStream) throws IOException {
            int messageLength = messageLength();
            do {
                int i = (byte) (messageLength & 127);
                messageLength >>= 7;
                if (messageLength > 0) {
                    i = (byte) (i | 128);
                }
                outputStream.write(i);
            } while (messageLength > 0);
        }

        private void writeMsgCode(OutputStream outputStream) throws IOException {
            int messageLength = messageLength();
            int i = this.headerCode;
            while (true) {
                int i2 = (byte) (messageLength & 127);
                messageLength >>= 7;
                if (messageLength > 0) {
                    i2 = (byte) (i2 | 128);
                }
                i2 ^= i;
                if (messageLength <= 0) {
                    outputStream.write(i2);
                    return;
                }
                i = i2;
            }
        }

        public final byte[] toBytes() {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                write(byteArrayOutputStream);
            } catch (IOException e) {
            }
            return byteArrayOutputStream.toByteArray();
        }

        protected int messageLength() {
            return 0;
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
        }

        public void setRetained(boolean z) {
            this.header.retain = z;
        }

        public boolean isRetained() {
            return this.header.retain;
        }

        public void setQos(QoS qoS) {
            this.header.qos = qoS;
        }

        public QoS getQos() {
            return this.header.qos;
        }

        public void setDup(boolean z) {
            this.header.dup = z;
        }

        public boolean isDup() {
            return this.header.dup;
        }

        public Type getType() {
            return this.header.type;
        }
    }

    public static class ConnAckMessage extends Message {
        public static final int MESSAGE_LENGTH = 2;
        private ConnectionStatus status;
        private String userId;

        public enum ConnectionStatus {
            ACCEPTED,
            UNACCEPTABLE_PROTOCOL_VERSION,
            IDENTIFIER_REJECTED,
            SERVER_UNAVAILABLE,
            BAD_USERNAME_OR_PASSWORD,
            NOT_AUTHORIZED,
            REDIRECT
        }

        public ConnAckMessage() {
            super(Type.CONNACK);
        }

        public ConnAckMessage(Header header) throws IOException {
            super(header);
        }

        public ConnAckMessage(ConnectionStatus connectionStatus) {
            super(Type.CONNACK);
            if (connectionStatus == null) {
                throw new IllegalArgumentException("The status of ConnAskMessage can't be null");
            }
            this.status = connectionStatus;
        }

        protected int messageLength() {
            if (this.userId == null || this.userId.isEmpty()) {
                return 2;
            }
            return 2 + FormatUtil.toWMtpString(this.userId).length;
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
            inputStream.read();
            switch (inputStream.read()) {
                case 0:
                    this.status = ConnectionStatus.ACCEPTED;
                    break;
                case 1:
                    this.status = ConnectionStatus.UNACCEPTABLE_PROTOCOL_VERSION;
                    break;
                case 2:
                    this.status = ConnectionStatus.IDENTIFIER_REJECTED;
                    break;
                case 3:
                    this.status = ConnectionStatus.SERVER_UNAVAILABLE;
                    break;
                case 4:
                    this.status = ConnectionStatus.BAD_USERNAME_OR_PASSWORD;
                    break;
                case 5:
                    this.status = ConnectionStatus.NOT_AUTHORIZED;
                    break;
                case 6:
                    this.status = ConnectionStatus.REDIRECT;
                    break;
                default:
                    RLog.e("PushProtocol", "Unsupported CONNACK code");
                    this.status = ConnectionStatus.REDIRECT;
                    break;
            }
            if (i > 2) {
                this.userId = new DataInputStream(inputStream).readUTF();
            }
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
            outputStream.write(0);
            switch (this.status) {
                case ACCEPTED:
                    outputStream.write(0);
                    break;
                case UNACCEPTABLE_PROTOCOL_VERSION:
                    outputStream.write(1);
                    break;
                case IDENTIFIER_REJECTED:
                    outputStream.write(2);
                    break;
                case SERVER_UNAVAILABLE:
                    outputStream.write(3);
                    break;
                case BAD_USERNAME_OR_PASSWORD:
                    outputStream.write(4);
                    break;
                case NOT_AUTHORIZED:
                    outputStream.write(5);
                    break;
                case REDIRECT:
                    outputStream.write(6);
                    break;
                default:
                    RLog.e("PushProtocol", "Unsupported CONNACK message status: " + this.status);
                    break;
            }
            if (this.userId != null && !this.userId.isEmpty()) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF(this.userId);
                dataOutputStream.flush();
            }
        }

        public ConnectionStatus getStatus() {
            return this.status;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setDup(boolean z) {
            throw new UnsupportedOperationException("CONNACK messages don't use the DUP flag.");
        }

        public void setRetained(boolean z) {
            throw new UnsupportedOperationException("CONNACK messages don't use the RETAIN flag.");
        }

        public void setQos(QoS qoS) {
            throw new UnsupportedOperationException("CONNACK messages don't use the QoS flags.");
        }
    }

    public static class ConnectMessage extends Message {
        private static int CONNECT_HEADER_SIZE = 12;
        private boolean cleanSession;
        private String clientId;
        private boolean hasPassword;
        private boolean hasUsername;
        private boolean hasWill;
        private int keepAlive;
        private String password;
        private String protocolId = "MQIsdp";
        private byte protocolVersion = (byte) 3;
        private boolean retainWill;
        private String username;
        private String will;
        private QoS willQoS;
        private String willTopic;

        public ConnectMessage() {
            super(Type.CONNECT);
        }

        public ConnectMessage(Header header) throws IOException {
            super(header);
        }

        public ConnectMessage(String str, boolean z, int i) {
            super(Type.CONNECT);
            if (str == null || str.length() > 64) {
                throw new IllegalArgumentException("Client id cannot be null and must be at most 64 characters long: " + str);
            }
            this.clientId = str;
            this.cleanSession = z;
            this.keepAlive = i;
        }

        protected int messageLength() {
            return ((((FormatUtil.toWMtpString(this.clientId).length + FormatUtil.toWMtpString(this.willTopic).length) + FormatUtil.toWMtpString(this.will).length) + FormatUtil.toWMtpString(this.username).length) + FormatUtil.toWMtpString(this.password).length) + CONNECT_HEADER_SIZE;
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
            boolean z;
            boolean z2 = true;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.protocolId = dataInputStream.readUTF();
            this.protocolVersion = dataInputStream.readByte();
            byte readByte = dataInputStream.readByte();
            this.hasUsername = (readByte & 128) > 0;
            if ((readByte & 64) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.hasPassword = z;
            if ((readByte & 32) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.retainWill = z;
            this.willQoS = QoS.valueOf((readByte >> 3) & 3);
            if ((readByte & 4) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.hasWill = z;
            if ((readByte & 32) <= 0) {
                z2 = false;
            }
            this.cleanSession = z2;
            this.keepAlive = (dataInputStream.read() * 256) + dataInputStream.read();
            this.clientId = dataInputStream.readUTF();
            if (this.hasWill) {
                this.willTopic = dataInputStream.readUTF();
                this.will = dataInputStream.readUTF();
            }
            if (this.hasUsername) {
                try {
                    this.username = dataInputStream.readUTF();
                } catch (EOFException e) {
                }
            }
            if (this.hasPassword) {
                try {
                    this.password = dataInputStream.readUTF();
                } catch (EOFException e2) {
                }
            }
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
            int i;
            int i2 = 0;
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(this.protocolId);
            dataOutputStream.write(this.protocolVersion);
            int i3 = this.cleanSession ? 2 : 0;
            if (this.hasWill) {
                i = 4;
            } else {
                i = 0;
            }
            i = (i | i3) | (this.willQoS == null ? 0 : this.willQoS.val << 3);
            if (this.retainWill) {
                i3 = 32;
            } else {
                i3 = 0;
            }
            i |= i3;
            if (this.hasPassword) {
                i3 = 64;
            } else {
                i3 = 0;
            }
            i3 |= i;
            if (this.hasUsername) {
                i2 = 128;
            }
            dataOutputStream.write((byte) (i3 | i2));
            dataOutputStream.writeChar(this.keepAlive);
            dataOutputStream.writeUTF(this.clientId);
            if (this.hasWill) {
                dataOutputStream.writeUTF(this.willTopic);
                dataOutputStream.writeUTF(this.will);
            }
            if (this.hasUsername) {
                dataOutputStream.writeUTF(this.username);
            }
            if (this.hasPassword) {
                dataOutputStream.writeUTF(this.password);
            }
            dataOutputStream.flush();
        }

        public void setCredentials(String str) {
            setCredentials(str, null);
        }

        public void setCredentials(String str, String str2) {
            boolean z = true;
            if ((str != null && !str.isEmpty()) || str2 == null || str2.isEmpty()) {
                boolean z2;
                this.username = str;
                this.password = str2;
                if (this.username != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.hasUsername = z2;
                if (this.password == null) {
                    z = false;
                }
                this.hasPassword = z;
                return;
            }
            throw new IllegalArgumentException("It is not valid to supply a password without supplying a username.");
        }

        public void setWill(String str, String str2) {
            setWill(str, str2, QoS.AT_MOST_ONCE, false);
        }

        public void setWill(String str, String str2, QoS qoS, boolean z) {
            int i;
            boolean z2 = true;
            if (str == null) {
                i = 1;
            } else {
                i = 0;
            }
            if (((str2 == null ? 1 : 0) ^ i) == 0) {
                if (((qoS == null ? 1 : 0) ^ (str2 == null ? 1 : 0)) == 0) {
                    this.willTopic = str;
                    this.will = str2;
                    this.willQoS = qoS;
                    this.retainWill = z;
                    if (str == null) {
                        z2 = false;
                    }
                    this.hasWill = z2;
                    return;
                }
            }
            throw new IllegalArgumentException("Can't set willTopic, will or willQoS value independently");
        }

        public void setDup(boolean z) {
            throw new UnsupportedOperationException("CONNECT messages don't use the DUP flag.");
        }

        public void setRetained(boolean z) {
            throw new UnsupportedOperationException("CONNECT messages don't use the RETAIN flag.");
        }

        public void setQos(QoS qoS) {
            throw new UnsupportedOperationException("CONNECT messages don't use the QoS flags.");
        }

        public String getProtocolId() {
            return this.protocolId;
        }

        public byte getProtocolVersion() {
            return this.protocolVersion;
        }

        public String getClientId() {
            return this.clientId;
        }

        public int getKeepAlive() {
            return this.keepAlive;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public boolean isCleanSession() {
            return this.cleanSession;
        }

        public String getWillTopic() {
            return this.willTopic;
        }

        public String getWill() {
            return this.will;
        }

        public QoS getWillQoS() {
            return this.willQoS;
        }

        public boolean isWillRetained() {
            return this.retainWill;
        }

        public boolean hasUsername() {
            return this.hasUsername;
        }

        public boolean hasPassword() {
            return this.hasPassword;
        }

        public boolean hasWill() {
            return this.hasWill;
        }
    }

    public static class DisconnectMessage extends Message {
        public static final int MESSAGE_LENGTH = 2;
        private DisconnectionStatus status;

        public enum DisconnectionStatus {
            RECONNECT,
            OTHER_DEVICE_LOGIN,
            CLOSURE
        }

        public DisconnectMessage(Header header) throws IOException {
            super(header);
        }

        public DisconnectMessage(DisconnectionStatus disconnectionStatus) {
            super(Type.DISCONNECT);
            if (disconnectionStatus == null) {
                throw new IllegalArgumentException("The status of ConnAskMessage can't be null");
            }
            this.status = disconnectionStatus;
        }

        public DisconnectMessage() {
            super(Type.DISCONNECT);
        }

        protected int messageLength() {
            return 2;
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
            inputStream.read();
            int read = inputStream.read();
            switch (read) {
                case 0:
                    this.status = DisconnectionStatus.RECONNECT;
                    return;
                case 1:
                    this.status = DisconnectionStatus.OTHER_DEVICE_LOGIN;
                    return;
                case 2:
                    this.status = DisconnectionStatus.CLOSURE;
                    return;
                default:
                    RLog.e("PushProtocol", "Unsupported DisconnectMessage status: " + read);
                    return;
            }
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
            outputStream.write(0);
            switch (this.status) {
                case RECONNECT:
                    outputStream.write(0);
                    return;
                case OTHER_DEVICE_LOGIN:
                    outputStream.write(1);
                    return;
                case CLOSURE:
                    outputStream.write(2);
                    return;
                default:
                    RLog.e("PushProtocol", "Unsupported DisconnectMessage code.");
                    return;
            }
        }

        public DisconnectionStatus getStatus() {
            return this.status;
        }

        public void setDup(boolean z) {
            throw new UnsupportedOperationException("DISCONNECT message does not support the DUP flag");
        }

        public void setQos(QoS qoS) {
            throw new UnsupportedOperationException("DISCONNECT message does not support the QoS flag");
        }

        public void setRetained(boolean z) {
            throw new UnsupportedOperationException("DISCONNECT message does not support the RETAIN flag");
        }
    }

    public static class FormatUtil {
        public static String dumpByteArray(byte[] bArr) {
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bArr) {
                int parseInt = Integer.parseInt(Integer.toBinaryString(b & 255));
                stringBuilder.append(String.format("%1$02d: %2$08d %3$1c %3$d\n", new Object[]{Integer.valueOf(r0), Integer.valueOf(parseInt), Integer.valueOf(r3)}));
            }
            return stringBuilder.toString();
        }

        public static byte[] toWMtpString(String str) {
            if (str == null) {
                return new byte[0];
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeUTF(str);
                dataOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                return new byte[0];
            }
        }

        public static String toString(byte[] bArr) {
            try {
                return new DataInputStream(new ByteArrayInputStream(bArr)).readUTF();
            } catch (IOException e) {
                return null;
            }
        }
    }

    public static class MessageInputStream implements Closeable {
        private InputStream in;

        public MessageInputStream(InputStream inputStream) {
            this.in = inputStream;
        }

        public Message readMessage() throws IOException {
            Message message = null;
            Header header = new Header((byte) this.in.read());
            if (header.getType() != null) {
                RLog.i("PushProtocalStack", "receive message type:" + header.getType());
                switch (header.getType()) {
                    case CONNACK:
                        message = new ConnAckMessage(header);
                        break;
                    case PUBLISH:
                        message = new PublishMessage(header);
                        break;
                    case PINGRESP:
                        message = new PingRespMessage(header);
                        break;
                    case CONNECT:
                        message = new ConnectMessage(header);
                        break;
                    case PINGREQ:
                        message = new PingReqMessage(header);
                        break;
                    case DISCONNECT:
                        message = new DisconnectMessage(header);
                        break;
                    case QUERY:
                        message = new QueryMessage(header);
                        break;
                    case QUERYACK:
                        message = new QueryAckMessage(header);
                        break;
                    default:
                        RLog.e("PushProtocalStack", "No support for deserializing" + header.getType() + "messages");
                        break;
                }
                this.in.read();
                message.read(this.in);
            }
            return message;
        }

        public void close() throws IOException {
            this.in.close();
        }
    }

    public static class MessageOutputStream {
        private final OutputStream out;

        public MessageOutputStream(OutputStream outputStream) {
            this.out = outputStream;
        }

        public void writeMessage(Message message) throws IOException {
            message.write(this.out);
            this.out.flush();
        }
    }

    public static class PingReqMessage extends Message {
        public PingReqMessage() {
            super(Type.PINGREQ);
        }

        public PingReqMessage(Header header) throws IOException {
            super(header);
        }

        public void setDup(boolean z) {
            throw new UnsupportedOperationException("PINGREQ message does not support the DUP flag");
        }

        public void setQos(QoS qoS) {
            throw new UnsupportedOperationException("PINGREQ message does not support the QoS flag");
        }

        public void setRetained(boolean z) {
            throw new UnsupportedOperationException("PINGREQ message does not support the RETAIN flag");
        }
    }

    public static class PingRespMessage extends Message {
        public PingRespMessage(Header header) throws IOException {
            super(header);
        }

        public PingRespMessage() {
            super(Type.PINGRESP);
        }
    }

    public static abstract class RetryableMessage extends Message {
        private int messageId;

        public RetryableMessage(Header header) throws IOException {
            super(header);
        }

        public RetryableMessage(Type type) {
            super(type);
        }

        protected int messageLength() {
            return 2;
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
            int messageId = getMessageId();
            int i = messageId & 255;
            outputStream.write((messageId & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            outputStream.write(i);
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
            setMessageId((inputStream.read() * 255) + inputStream.read());
        }

        public void setMessageId(int i) {
            this.messageId = i;
        }

        public int getMessageId() {
            return this.messageId;
        }
    }

    public static class PublishMessage extends RetryableMessage {
        private byte[] data;
        private int date;
        private String targetId;
        private String topic;

        public PublishMessage(Header header) throws IOException {
            super(header);
        }

        protected int messageLength() {
            return 0;
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
            super.writeMessage(outputStream);
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readLong();
            this.date = dataInputStream.readInt();
            this.topic = dataInputStream.readUTF();
            this.targetId = dataInputStream.readUTF();
            int length = (14 + FormatUtil.toWMtpString(this.topic).length) + FormatUtil.toWMtpString(this.targetId).length;
            super.readMessage(inputStream, i);
            if (i >= length) {
                this.data = new byte[(i - length)];
                dataInputStream.read(this.data);
                return;
            }
            RLog.e("PushProtocal", "error msgLength. msgLength:" + i + "pos:" + length);
        }

        public String getTopic() {
            return this.topic;
        }

        public byte[] getData() {
            return this.data;
        }

        public int getServerTime() {
            return this.date;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public String getDataAsString() {
            if (this.data == null) {
                return null;
            }
            return FormatUtil.toString(this.data);
        }
    }

    public enum QoS {
        AT_MOST_ONCE(0),
        AT_LEAST_ONCE(1),
        EXACTLY_ONCE(2),
        DEFAULT(3);
        
        public final int val;

        private QoS(int i) {
            this.val = i;
        }

        static QoS valueOf(int i) {
            for (QoS qoS : values()) {
                if (qoS.val == i) {
                    return qoS;
                }
            }
            throw new IllegalArgumentException("Not a valid QoS number: " + i);
        }
    }

    public static class QueryAckMessage extends RetryableMessage {
        private static final int msgLen = 8;
        private byte[] data;
        private int date;
        private int status;

        public enum QueryStatus {
            STATUS_ERROR(0),
            STATUS_OK(1),
            STATUS_NODBCONF(2),
            STATUS_PARAMERROR(3);
            
            private int value;

            private QueryStatus(int i) {
                this.value = i;
            }

            public int get() {
                return this.value;
            }
        }

        public QueryAckMessage(int i, int i2, byte[] bArr) {
            this(i);
            this.data = bArr;
            this.date = (int) (System.currentTimeMillis() / 1000);
            this.status = i2;
        }

        public QueryAckMessage(int i) {
            super(Type.QUERYACK);
            setMessageId(i);
        }

        public QueryAckMessage(Header header) throws IOException {
            super(header);
        }

        protected int messageLength() {
            if (this.data == null || this.data.length <= 0) {
                return 8;
            }
            return 8 + this.data.length;
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
            super.writeMessage(outputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(this.date);
            dataOutputStream.writeInt(this.status);
            if (this.data != null && this.data.length > 0) {
                dataOutputStream.write(this.data);
            }
            dataOutputStream.flush();
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
            super.readMessage(inputStream, i);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.date = dataInputStream.readInt();
            this.status = dataInputStream.readInt();
            if (i > 8) {
                this.data = new byte[(i - 8)];
                dataInputStream.read(this.data);
            }
        }

        public int getStatus() {
            return this.status;
        }

        public void setDup(boolean z) {
            throw new UnsupportedOperationException("PubAck messages don't use the DUP flag.");
        }

        public void setQos(QoS qoS) {
            throw new UnsupportedOperationException("PubAck messages don't use the QoS flags.");
        }

        public String getDataAsString() {
            if (this.data != null) {
                return FormatUtil.toString(this.data);
            }
            return null;
        }
    }

    public static class QueryMessage extends RetryableMessage {
        private byte[] data;
        private long signature;
        private String targetId;
        private String topic;

        public QueryMessage(String str, String str2, String str3) {
            this(str, FormatUtil.toWMtpString(str2), str3);
        }

        public QueryMessage(String str, byte[] bArr, String str2) {
            super(Type.QUERY);
            this.topic = str;
            this.targetId = str2;
            this.data = bArr;
            this.signature = 255;
        }

        public QueryMessage(Header header) throws IOException {
            super(header);
        }

        protected int messageLength() {
            return (((8 + FormatUtil.toWMtpString(this.topic).length) + FormatUtil.toWMtpString(this.targetId).length) + 2) + this.data.length;
        }

        protected void writeMessage(OutputStream outputStream) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeLong(this.signature);
            dataOutputStream.writeUTF(this.topic);
            dataOutputStream.writeUTF(this.targetId);
            dataOutputStream.flush();
            super.writeMessage(outputStream);
            dataOutputStream.write(this.data);
            dataOutputStream.flush();
        }

        protected void readMessage(InputStream inputStream, int i) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.signature = dataInputStream.readLong();
            this.topic = dataInputStream.readUTF();
            this.targetId = dataInputStream.readUTF();
            int length = (FormatUtil.toWMtpString(this.topic).length + 8) + FormatUtil.toWMtpString(this.targetId).length;
            super.readMessage(inputStream, i);
            this.data = new byte[(i - (length + 2))];
            dataInputStream.read(this.data);
        }

        public String getTopic() {
            return this.topic;
        }

        public byte[] getData() {
            return this.data;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public String getDataAsString() {
            return new String(this.data);
        }
    }

    PushProtocalStack() {
    }
}

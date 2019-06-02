package com.beastbikes.android.ble.protocol.v1;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGattCharacteristic;
import com.beastbikes.android.ble.protocol.CharacteristicValue;
import com.beastbikes.android.ble.protocol.ProtocolParser;
import java.io.Serializable;

@TargetApi(18)
public class ProtocolParserImpl implements ProtocolParser, Serializable {
    public native byte bcdToByte(byte b);

    public native byte byteToBcd(byte b);

    public native int crc16(byte[] bArr);

    public native byte crc8(byte[] bArr);

    public native int getCheckSum(byte[] bArr);

    public native CommandCharacteristic parseCommandCharacteristic(byte[] bArr);

    public native SampleCharacteristic parseSampleCharacteristic(byte[] bArr);

    public native SensorCharacteristic parseSensorCharacteristic(byte[] bArr);

    public native SyncDataCharacteristic parseSyncDataCharacteristic(byte[] bArr);

    static {
        System.loadLibrary("ble-protocol-parser-jni");
    }

    public CharacteristicValue parse(byte[] bArr) {
        CharacteristicValue parseCharacteristic = parseCharacteristic(bArr);
        if (parseCharacteristic != null) {
            parseCharacteristic.setProtocol(bArr[0]);
            parseCharacteristic.setCrc(bArr[bArr.length - 1]);
        }
        return parseCharacteristic;
    }

    public CharacteristicValue parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return null;
    }

    private AbstractCharacteristicValue parseCharacteristic(byte[] bArr) {
        if (bArr.length < 2) {
            return null;
        }
        switch (bArr[bArr.length - 2]) {
            case (byte) 0:
                return parseSampleCharacteristic(bArr);
            case (byte) 1:
                return null;
            case (byte) 2:
            case (byte) 3:
                return parseSensorCharacteristic(bArr);
            case (byte) 5:
                return parseCommandCharacteristic(bArr);
            default:
                return null;
        }
    }
}

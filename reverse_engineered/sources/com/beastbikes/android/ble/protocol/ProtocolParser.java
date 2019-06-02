package com.beastbikes.android.ble.protocol;

import android.bluetooth.BluetoothGattCharacteristic;

public interface ProtocolParser {
    CharacteristicValue parse(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    CharacteristicValue parse(byte[] bArr);
}

// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.callback;

import android.bluetooth.BluetoothDevice;

public interface BluetoothSearchCallback
{
    void onDeviceFound(final BluetoothDevice p0, final int p1, final byte[] p2);
    
    void onSearchError(final int p0);
    
    void onSearchComplete();
}

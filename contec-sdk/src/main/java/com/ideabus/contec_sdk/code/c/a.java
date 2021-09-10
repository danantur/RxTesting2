// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.c;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import com.ideabus.contec_sdk.code.a.b;

@SuppressLint({ "NewApi" })
public class a
{
    private a() {
    }
    
    public static com.ideabus.contec_sdk.code.c.a a() {
        return a1.a;
    }
    
    public com.ideabus.contec_sdk.code.connect.a a(final Context context, final BluetoothDevice bluetoothDevice, final b b) {
        com.ideabus.contec_sdk.code.connect.a a = null;
        switch (bluetoothDevice.getType()) {
            case 2: {
                a = new com.ideabus.contec_sdk.code.connect.a(context, bluetoothDevice, b);
                break;
            }
        }
        return a;
    }
    
    public com.ideabus.contec_sdk.code.connect.b a(final UsbManager usbManager, final UsbDevice usbDevice, final b b) {
        com.ideabus.contec_sdk.code.connect.b a = null;
        switch (usbDevice.getProductId()) {
            case 650: {
                a = new com.ideabus.contec_sdk.code.connect.b(usbManager, usbDevice, b);
                break;
            }
        }
        return a;
    }
    
    private static class a1
    {
        private static final com.ideabus.contec_sdk.code.c.a a;
        
        static {
            a = new com.ideabus.contec_sdk.code.c.a();
        }
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.c;

import android.hardware.usb.UsbDevice;

import com.ideabus.contec_sdk.code.b.d;
import com.ideabus.contec_sdk.code.b.e;
import com.ideabus.contec_sdk.code.b.f;
import com.ideabus.contec_sdk.code.b.g;
import com.ideabus.contec_sdk.code.bean.DeviceType;

public class b
{
    private b() {
    }
    
    public static b a() {
        return a.a;
    }
    
    public com.ideabus.contec_sdk.code.a.b a(final DeviceType deviceType) {
        com.ideabus.contec_sdk.code.a.b b = null;
        if (null == deviceType) {
            return b;
        }
        switch (deviceType.ordinal()) {
            case 1: {
                b = new com.ideabus.contec_sdk.code.b.b();
                break;
            }
            case 2: {
                b = new c();
                break;
            }
            case 3: {
                b = new d();
                break;
            }
            case 4: {
                b = new e();
                break;
            }
            case 5: {
                b = new f();
                break;
            }
            case 6: {
                b = new g();
                break;
            }
        }
        return b;
    }
    
    public com.ideabus.contec_sdk.code.a.b a(final UsbDevice usbDevice) {
        com.ideabus.contec_sdk.code.a.b b = null;
        switch (usbDevice.getProductId()) {
            case 650: {
                b = new g();
                break;
            }
        }
        return b;
    }
    
    private static class a
    {
        private static final b a;
        
        static {
            a = new b();
        }
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.connect;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.ideabus.contec_sdk.code.a.a;
import com.ideabus.contec_sdk.code.callback.ConnectCallback;
import com.ideabus.contec_sdk.code.tools.Utils;

import java.io.IOException;

public class b extends com.ideabus.contec_sdk.code.a.a
{
    private String b;
    private boolean c;
    private UsbManager d;
    private UsbDevice e;
    private com.ideabus.contec_sdk.code.a.b f;
    private ConnectCallback g;
    private UsbEndpoint h;
    private UsbEndpoint i;
    private UsbDeviceConnection j;
    private a k;
    private final int l = 1000;
    
    public b(final UsbManager d, final UsbDevice e, final com.ideabus.contec_sdk.code.a.b f) {
        this.b = "HidCommunicateManager";
        this.c = false;
        this.k = null;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public void a(final ConnectCallback g) {
        if (g == null) {
            return;
        }
        this.g = g;
        if (com.ideabus.contec_sdk.code.connect.b.a) {
            g.onOpenStatus(0);
        }
        if (this.k != null) {
            this.k.a();
            this.k = null;
        }
        final UsbInterface interface1 = this.e.getInterface(0);
        if (interface1 == null) {
            g.onOpenStatus(6);
        }
        this.h = interface1.getEndpoint(0);
        this.i = interface1.getEndpoint(1);
        this.j = this.d.openDevice(this.e);
        if (this.j != null && this.j.claimInterface(interface1, true)) {
            com.ideabus.contec_sdk.code.connect.b.a = true;
            if (this.f != null) {
                this.f.a(true);
            }
            Log.i(this.b, "\u6253\u5f00\u6210\u529f");
            (this.k = new a()).start();
            g.onOpenStatus(5);
        }
        else {
            com.ideabus.contec_sdk.code.connect.b.a = false;
            g.onOpenStatus(6);
        }
    }
    
    @Override
    public void a() {
        if (this.k != null) {
            this.k.a();
            this.k = null;
        }
        if (this.j != null && com.ideabus.contec_sdk.code.connect.b.a) {
            Log.i(this.b, "\u4e3b\u52a8\u5173\u95ed\u8bbe\u5907");
            this.j.close();
            this.j = null;
            com.ideabus.contec_sdk.code.connect.b.a = false;
            if (this.g != null) {
                this.g.onOpenStatus(7);
            }
            if (this.f != null) {
                this.f.a(false);
            }
        }
    }
    
    @Override
    public void a(final byte[] array) {
        if (null == array) {
            return;
        }
        if (array.length <= 0) {
            return;
        }
        if (null == this.k) {
            return;
        }
        try {
            if (this.c) {
                Log.e(this.b, "\u5199\u5165\u7684\u6570\u636e = " + Utils.bytesToHexString(array));
            }
            this.k.a(array);
        }
        catch (Exception ex) {
            if (this.f != null) {
                Log.e(this.b, "\u5f02\u5e38");
            }
        }
    }
    
    public class a extends Thread
    {
        private boolean b;
        private byte[] c;
        private byte[] d;
        private byte[] e;
        private final Object f;
        private final Object g;
        
        public a() {
            this.b = false;
            this.c = new byte[64];
            this.d = new byte[64];
            this.e = new byte[64];
            this.f = new Object();
            this.g = new Object();
            this.b = true;
        }
        
        @Override
        public void run() {
            while (this.b && com.ideabus.contec_sdk.code.a.a.a) {
                if (this.a(this.c, 1000) > 0) {
                    if (com.ideabus.contec_sdk.code.connect.b.this.c) {
                        Log.e(com.ideabus.contec_sdk.code.connect.b.this.b, "\u6536\u5230\u7684\u6570\u636e = " + Utils.bytesToHexString(this.c));
                    }
                    if (com.ideabus.contec_sdk.code.connect.b.this.f == null) {
                        continue;
                    }
                    com.ideabus.contec_sdk.code.connect.b.this.f.b(this.c);
                }
            }
        }
        
        private int a(final byte[] array, final int n) {
            if (!com.ideabus.contec_sdk.code.a.a.a) {
                return 0;
            }
            int i;
            int bulkTransfer;
            int n2;
            int min;
            for (i = 0, bulkTransfer = 0, n2 = 0; i < array.length; i += min, n2 += bulkTransfer) {
                synchronized (this.f) {
                    min = Math.min(array.length - i, this.d.length);
                    if (com.ideabus.contec_sdk.code.connect.b.this.j != null) {
                        bulkTransfer = com.ideabus.contec_sdk.code.connect.b.this.j.bulkTransfer(com.ideabus.contec_sdk.code.connect.b.this.h, this.d, min, n);
                    }
                    if (bulkTransfer < 0) {
                        return n2;
                    }
                    System.arraycopy(this.d, 0, array, i, min);
                }
            }
            return n2;
        }
        
        public int a(final byte[] array) {
            if (!com.ideabus.contec_sdk.code.a.a.a) {
                return 0;
            }
            int i = 0;
            int min = 0;
            while (i < array.length) {
                int bulkTransfer = 0;
                synchronized (this.g) {
                    min = Math.min(array.length - i, this.e.length);
                    byte[] e;
                    if (i == 0) {
                        e = array;
                    }
                    else {
                        System.arraycopy(array, i, this.e, 0, min);
                        e = this.e;
                    }
                    if (com.ideabus.contec_sdk.code.connect.b.this.j != null) {
                        bulkTransfer = com.ideabus.contec_sdk.code.connect.b.this.j.bulkTransfer(com.ideabus.contec_sdk.code.connect.b.this.i, e, min, 1000);
                    }
                }
                if (bulkTransfer <= 0) {
                    try {
                        throw new IOException("Error writing " + min + " bytes at offset " + i + " length=" + array.length);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                i += bulkTransfer;
            }
            return i;
        }
        
        public void a() {
            this.b = false;
        }
    }
}

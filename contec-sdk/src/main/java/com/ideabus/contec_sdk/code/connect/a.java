// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.connect;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;

import com.ideabus.contec_sdk.code.a.b;
import com.ideabus.contec_sdk.code.c.c;
import com.ideabus.contec_sdk.code.callback.ConnectCallback;
import com.ideabus.contec_sdk.code.tools.Utils;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class a extends com.ideabus.contec_sdk.code.a.a
{
    private String b;
    private boolean c;
    private Context d;
    private BluetoothDevice e;
    private b f;
    private BluetoothGatt g;
    private ConnectCallback h;
    private Timer i;
    private int j;
    private boolean k;
    private BluetoothGattService l;
    private BluetoothGattCharacteristic m;
    private BluetoothGattCharacteristic n;
    private BluetoothGattCallback o;
    
    public a(final Context d, final BluetoothDevice e, final b f) {
        this.b = "BleCommunicateManager";
        this.c = false;
        this.j = 7;
        this.k = true;
        this.m = null;
        this.o = new BluetoothGattCallback() {
            public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, final int n, final int n2) {
                if (n2 == 2) {
                    Log.e(com.ideabus.contec_sdk.code.connect.a.this.b, "\u8bbe\u5907\u8fde\u63a5\u4e0a");
                    if (com.ideabus.contec_sdk.code.connect.a.this.h != null) {
                        com.ideabus.contec_sdk.code.connect.a.this.h.onConnectStatus(2);
                    }
                    com.ideabus.contec_sdk.code.connect.a.this.a(800);
                    Log.e(com.ideabus.contec_sdk.code.connect.a.this.b, "\u5f00\u59cb\u626b\u63cf\u670d\u52a1");
                    if (com.ideabus.contec_sdk.code.connect.a.this.g != null) {
                        com.ideabus.contec_sdk.code.connect.a.this.g.discoverServices();
                    }
                    com.ideabus.contec_sdk.code.connect.a.this.j = 5;
                }
                if (n2 == 0) {
                    if (com.ideabus.contec_sdk.code.connect.a.this.g != null) {
                        com.ideabus.contec_sdk.code.connect.a.this.g.close();
                        com.ideabus.contec_sdk.code.connect.a.this.g = null;
                    }
                    if (com.ideabus.contec_sdk.code.connect.a.this.i != null) {
                        com.ideabus.contec_sdk.code.connect.a.this.i.cancel();
                        com.ideabus.contec_sdk.code.connect.a.this.i = null;
                    }
                    com.ideabus.contec_sdk.code.a.a.a = false;
                    if (null != com.ideabus.contec_sdk.code.connect.a.this.f) {
                        com.ideabus.contec_sdk.code.connect.a.this.f.a(false);
                        com.ideabus.contec_sdk.code.connect.a.this.f.e();
                        com.ideabus.contec_sdk.code.connect.a.this.f.f();
                        com.ideabus.contec_sdk.code.connect.a.this.f.h();
                        com.ideabus.contec_sdk.code.connect.a.this.f.g();
                        com.ideabus.contec_sdk.code.connect.a.this.f.i();
                    }
                    if (!com.ideabus.contec_sdk.code.connect.a.this.k) {
                        Log.e(com.ideabus.contec_sdk.code.connect.a.this.b, "\u5f02\u5e38\u65ad\u5f00");
                        if (com.ideabus.contec_sdk.code.connect.a.this.h != null) {
                            com.ideabus.contec_sdk.code.connect.a.this.h.onConnectStatus(7);
                        }
                    }
                    else if (com.ideabus.contec_sdk.code.connect.a.this.h != null) {
                        com.ideabus.contec_sdk.code.connect.a.this.h.onConnectStatus(4);
                    }
                }
            }
            
            public void onCharacteristicWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int n) {
                if (com.ideabus.contec_sdk.code.connect.a.this.c) {
                    Log.e(com.ideabus.contec_sdk.code.connect.a.this.b, "\u53d1\u9001\u6570\u636e" + Utils.bytesToHexString(bluetoothGattCharacteristic.getValue()));
                }
                super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, n);
            }
            
            public void onDescriptorWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattDescriptor bluetoothGattDescriptor, final int n) {
                if (n == 0 && Utils.bytesToHexString(bluetoothGattDescriptor.getValue()).equals(Utils.bytesToHexString(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE))) {
                    Log.e(com.ideabus.contec_sdk.code.connect.a.this.b, "\u76d1\u542c\u6210\u529f");
                    if (com.ideabus.contec_sdk.code.connect.a.this.i != null) {
                        com.ideabus.contec_sdk.code.connect.a.this.i.cancel();
                        com.ideabus.contec_sdk.code.connect.a.this.i = null;
                    }
                    if (null != com.ideabus.contec_sdk.code.connect.a.this.f) {
                        com.ideabus.contec_sdk.code.connect.a.this.f.e();
                        com.ideabus.contec_sdk.code.connect.a.this.f.f();
                        com.ideabus.contec_sdk.code.connect.a.this.f.g();
                        com.ideabus.contec_sdk.code.connect.a.this.f.h();
                        com.ideabus.contec_sdk.code.connect.a.this.f.a(true);
                    }
                    if (null != com.ideabus.contec_sdk.code.connect.a.this.h) {
                        com.ideabus.contec_sdk.code.connect.a.this.h.onConnectStatus(3);
                    }
                }
            }
            
            public void onServicesDiscovered(final BluetoothGatt bluetoothGatt, final int n) {
                com.ideabus.contec_sdk.code.connect.a.this.l = com.ideabus.contec_sdk.code.connect.a.this.g.getService(com.ideabus.contec_sdk.code.c.c.c());
                com.ideabus.contec_sdk.code.connect.a.this.m = com.ideabus.contec_sdk.code.connect.a.this.l.getCharacteristic(com.ideabus.contec_sdk.code.c.c.a());
                com.ideabus.contec_sdk.code.connect.a.this.n = com.ideabus.contec_sdk.code.connect.a.this.l.getCharacteristic(com.ideabus.contec_sdk.code.c.c.b());
                com.ideabus.contec_sdk.code.connect.a.this.j = 6;
                com.ideabus.contec_sdk.code.connect.a.this.a(com.ideabus.contec_sdk.code.connect.a.this.l, com.ideabus.contec_sdk.code.connect.a.this.n, true);
            }
            
            public void onCharacteristicChanged(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                if (com.ideabus.contec_sdk.code.connect.a.this.c) {
                    Log.e(com.ideabus.contec_sdk.code.connect.a.this.b, "\u6536\u5230\u6570\u636e" + Utils.bytesToHexString(bluetoothGattCharacteristic.getValue()));
                }
                if (null != com.ideabus.contec_sdk.code.connect.a.this.f) {
                    com.ideabus.contec_sdk.code.connect.a.this.f.b(bluetoothGattCharacteristic.getValue());
                }
            }
        };
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public void a(final ConnectCallback referent) {
        if (null != referent) {
            this.h = new WeakReference<ConnectCallback>(referent).get();
        }
        if (a) {
            return;
        }
        a = true;
        if (null != this.g) {
            this.g.disconnect();
        }
        if (null != this.i) {
            this.i.cancel();
            this.i = null;
        }
        this.k = false;
        this.g = this.e.connectGatt(this.d, false, this.o);
        int connectOverTime = ContecSdk.connectOverTime;
        if (connectOverTime <= 0) {
            connectOverTime = 20000;
        }
        if (this.i == null) {
            (this.i = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    if (com.ideabus.contec_sdk.code.connect.a.this.g != null) {
                        if (com.ideabus.contec_sdk.code.connect.a.this.h != null) {
                            com.ideabus.contec_sdk.code.connect.a.this.h.onConnectStatus(com.ideabus.contec_sdk.code.connect.a.this.j);
                        }
                        com.ideabus.contec_sdk.code.connect.a.this.a();
                    }
                }
            }, connectOverTime);
        }
    }
    
    public boolean a(final BluetoothGattService bluetoothGattService, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final boolean b) {
        if (!this.g.setCharacteristicNotification(bluetoothGattCharacteristic, b)) {
            return false;
        }
        final BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            return this.g.writeDescriptor(descriptor);
        }
        return false;
    }
    
    @Override
    public void a() {
        a = false;
        if (this.g != null) {
            this.g.disconnect();
            this.k = true;
            Log.e(this.b, "\u4e3b\u52a8\u65ad\u5f00\u8fde\u63a5");
        }
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
    }
    
    @Override
    public void a(final byte[] array) {
        this.a(com.ideabus.contec_sdk.code.c.c.a(), array);
    }
    
    private void a(final UUID uuid, final byte[] value) {
        Log.e("bytes", "----------");
        for (byte r:
             value) {
            Log.e("bytes", Byte.toString(r));
        }
        if (null == value) {
            return;
        }
        if (this.m != null) {
            this.m.setValue(value);
        }
        if (this.g != null) {
            this.g.writeCharacteristic(this.m);
        }
    }
    
    private void a(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

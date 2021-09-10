// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;
import android.util.Log;

import com.ideabus.contec_sdk.code.a.a;
import com.ideabus.contec_sdk.code.a.b;
import com.ideabus.contec_sdk.code.bean.DeviceType;
import com.ideabus.contec_sdk.code.bean.SystemParameter;
import com.ideabus.contec_sdk.code.callback.BluetoothSearchCallback;
import com.ideabus.contec_sdk.code.callback.CommunicateCallback;
import com.ideabus.contec_sdk.code.callback.ConnectCallback;
import com.ideabus.contec_sdk.code.callback.DataStorageInfoCallback;
import com.ideabus.contec_sdk.code.callback.DeleteDataCallback;
import com.ideabus.contec_sdk.code.callback.GetStorageModeCallback;
import com.ideabus.contec_sdk.code.callback.RealtimeCallback;
import com.ideabus.contec_sdk.code.callback.RealtimeSpO2Callback;
import com.ideabus.contec_sdk.code.callback.SetCalorieCallback;
import com.ideabus.contec_sdk.code.callback.SetHeightCallback;
import com.ideabus.contec_sdk.code.callback.SetStepsTimeCallback;
import com.ideabus.contec_sdk.code.callback.SetWeightCallback;
import com.ideabus.contec_sdk.code.callback.StorageModeCallback;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class ContecSdk
{
    private String a;
    private Context b;
    private BluetoothAdapter c;
    private UsbManager d;
    private BluetoothSearchCallback e;
    private ConnectCallback f;
    private int g;
    private boolean h;
    private Timer i;
    public static int connectOverTime;
    private b j;
    private a k;
    public static boolean isDelete;
    private SystemParameter.DataType l;
    private static boolean m;
    private static String n;
    private Map<String, DeviceType> o;
    private BluetoothAdapter.LeScanCallback p;
    
    public ContecSdk(final Context b) {
        this.a = "ContecSdk";
        this.c = BluetoothAdapter.getDefaultAdapter();
        this.g = 0;
        this.h = false;
        this.p = (BluetoothAdapter.LeScanCallback)new BluetoothAdapter.LeScanCallback() {
            public void onLeScan(final BluetoothDevice bluetoothDevice, final int n, final byte[] array) {
                if (bluetoothDevice.getName() == null) {
                    return;
                }
                if (ContecSdk.this.e != null) {
                    ContecSdk.this.e.onDeviceFound(bluetoothDevice, n, array);
                }
            }
        };
        this.b = b;
        ContecSdk.m = false;
        this.a();
    }
    
    public ContecSdk(final Context b, final String s) {
        this.a = "ContecSdk";
        this.c = BluetoothAdapter.getDefaultAdapter();
        this.g = 0;
        this.h = false;
        this.p = (BluetoothAdapter.LeScanCallback)new BluetoothAdapter.LeScanCallback() {
            public void onLeScan(final BluetoothDevice bluetoothDevice, final int n, final byte[] array) {
                if (bluetoothDevice.getName() == null) {
                    return;
                }
                if (ContecSdk.this.e != null) {
                    ContecSdk.this.e.onDeviceFound(bluetoothDevice, n, array);
                }
            }
        };
        this.b = b;
        ContecSdk.n = s;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            ContecSdk.m = true;
            ContecSdk.n = s;
        }
        else {
            ContecSdk.m = false;
        }
        this.a();
    }
    
    private void a() {
        (this.o = new HashMap<String, DeviceType>()).put("SpO201", DeviceType.CMS50E);
        this.o.put("SpO202", DeviceType.CMS50F);
        this.o.put("SpO206", DeviceType.CMS50I);
        this.o.put("SpO208", DeviceType.CMS50D);
        this.o.put("SpO209", DeviceType.CMS50K);
        this.o.put("SpO210", DeviceType.CMS50K);
    }
    
    public static boolean getIsCheckDevice() {
        return ContecSdk.m;
    }
    
    public static String getRangID() {
        return ContecSdk.n;
    }
    
    public void init(final boolean isDelete) {
        ContecSdk.isDelete = isDelete;
    }
    
    public void stopBluetoothSearch() {
        if (this.c == null) {
            if (this.e != null) {
                this.e.onSearchError(0);
            }
            return;
        }
        if (!this.c.isEnabled()) {
            if (this.e != null) {
                this.e.onSearchError(1);
            }
            return;
        }
        this.c.stopLeScan(this.p);
        this.h = false;
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
        if (null != this.e) {
            this.e.onSearchComplete();
        }
    }
    
    public void startBluetoothSearch(final BluetoothSearchCallback e, final int g) {
        this.e = e;
        this.g = g;
        if (this.c == null) {
            this.e.onSearchError(0);
            return;
        }
        if (!this.c.isEnabled()) {
            this.e.onSearchError(1);
            return;
        }
        if (this.h) {
            return;
        }
        Log.e(this.a, "\u542f\u52a8BLE\u641c\u7d22");
        this.c.startLeScan(this.p);
        this.h = true;
        if (this.g > 0) {
            (this.i = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    ContecSdk.this.stopBluetoothSearch();
                }
            }, this.g);
        }
    }
    
    public void setConnectTimeout(final int connectOverTime) {
        if (connectOverTime > 0) {
            ContecSdk.connectOverTime = connectOverTime;
        }
        else {
            ContecSdk.connectOverTime = 20000;
        }
    }
    
    public void connect(final BluetoothDevice bluetoothDevice, final ConnectCallback f) {
        if (null != this.k) {
            final a k = this.k;
            if (com.ideabus.contec_sdk.code.a.a.a) {
                if (null != this.j && this.j.d()) {
                    if (null != this.f) {
                        this.f.onConnectStatus(3);
                    }
                }
                else if (null != this.f) {
                    this.f.onConnectStatus(2);
                }
                return;
            }
        }
        this.f = f;
        if (null == bluetoothDevice) {
            return;
        }
        this.j = com.ideabus.contec_sdk.code.c.b.a().a(this.a(bluetoothDevice.getName()));
        if (null == this.j) {
            Log.e(this.a, "\u521b\u5efa\u8bbe\u5907\u5931\u8d25");
            f.onConnectStatus(0);
            return;
        }
        this.k = com.ideabus.contec_sdk.code.c.a.a().a(this.b, bluetoothDevice, this.j);
        if (null == this.k) {
            Log.e(this.a, "\u521b\u5efa\u901a\u4fe1\u65b9\u5f0f\u5931\u8d25");
            f.onConnectStatus(1);
            return;
        }
        Log.e(this.a, "\u521b\u5efa\u901a\u4fe1\u65b9\u5f0f\u6210\u529f");
        this.j.a(this.k);
        this.j.a(f);
    }
    
    public void connect(final String s, final ConnectCallback f) {
        if (null != this.k) {
            final a k = this.k;
            if (com.ideabus.contec_sdk.code.a.a.a) {
                if (null != this.j && this.j.d()) {
                    if (null != this.f) {
                        this.f.onConnectStatus(3);
                    }
                }
                else if (null != this.f) {
                    this.f.onConnectStatus(2);
                }
                return;
            }
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            Log.e(this.a, "\u521b\u5efa\u8bbe\u5907\u5931\u8d25,\u5730\u5740\u4e0d\u80fd\u4e3a\u7a7a");
            return;
        }
        if (f == null) {
            Log.e(this.a, "\u521b\u5efa\u8bbe\u5907\u5931\u8d25,\u8fde\u63a5\u56de\u8c03\u4e0d\u80fd\u4e3a\u7a7a");
            return;
        }
        if (this.c == null || !this.c.isEnabled()) {
            Log.e(this.a, "\u521b\u5efa\u8bbe\u5907\u5931\u8d25,\u84dd\u7259\u4e0d\u53ef\u89c1");
            return;
        }
        final BluetoothDevice remoteDevice = this.c.getRemoteDevice(s);
        if (remoteDevice == null) {
            Log.e(this.a, "\u521b\u5efa\u8bbe\u5907\u5931\u8d25\uff0c\u901a\u8fc7\u5730\u5740\u83b7\u53d6\u7684\u8bbe\u5907\u4e3a\u7a7a");
            return;
        }
        this.f = f;
        this.j = com.ideabus.contec_sdk.code.c.b.a().a(this.a(remoteDevice.getName()));
        if (null == this.j) {
            Log.e(this.a, "\u521b\u5efa\u8bbe\u5907\u5931\u8d25");
            f.onConnectStatus(0);
            return;
        }
        this.k = com.ideabus.contec_sdk.code.c.a.a().a(this.b, remoteDevice, this.j);
        if (null == this.k) {
            Log.e(this.a, "\u521b\u5efa\u901a\u4fe1\u65b9\u5f0f\u5931\u8d25");
            f.onConnectStatus(1);
            return;
        }
        Log.e(this.a, "\u521b\u5efa\u901a\u4fe1\u65b9\u5f0f\u6210\u529f");
        this.j.a(this.k);
        this.j.a(f);
    }
    
    public void disconnect() {
        if (this.j != null) {
            this.j.b();
            this.j = null;
        }
    }
    
    public void open(final UsbManager d, final UsbDevice usbDevice, final ConnectCallback connectCallback) {
        if (this.k != null) {
            final a k = this.k;
            if (com.ideabus.contec_sdk.code.a.a.a) {
                Log.e(this.a, "\u8bbe\u5907\u5904\u4e8e\u6253\u5f00\u72b6\u6001");
                connectCallback.onOpenStatus(0);
                return;
            }
        }
        this.d = d;
        if (this.d == null) {
            connectCallback.onOpenStatus(1);
            return;
        }
        if (usbDevice == null) {
            connectCallback.onOpenStatus(2);
            return;
        }
        if (connectCallback == null) {
            connectCallback.onOpenStatus(3);
            return;
        }
        this.j = com.ideabus.contec_sdk.code.c.b.a().a(usbDevice);
        if (this.j == null) {
            Log.i(this.a, "\u521b\u5efa\u8bbe\u5907\u5931\u8d25");
            connectCallback.onOpenStatus(4);
            return;
        }
        this.k = com.ideabus.contec_sdk.code.c.a.a().a(this.d, usbDevice, this.j);
        if (this.k == null) {
            Log.i(this.a, "\u521b\u5efa\u901a\u4fe1\u65b9\u5f0f\u5931\u8d25");
            connectCallback.onOpenStatus(4);
            return;
        }
        this.j.a(this.k);
        this.j.a(connectCallback);
    }
    
    public void communicate(final CommunicateCallback communicateCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(this.l);
            this.j.a(communicateCallback);
        }
        else if (null != communicateCallback) {
            communicateCallback.onFail(0);
        }
    }
    
    public void startRealtime(final RealtimeCallback realtimeCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(realtimeCallback);
        }
        else if (null != realtimeCallback) {
            realtimeCallback.onFail(0);
        }
    }
    
    public void stopRealtime() {
        if (null != this.j && this.j.d()) {
            this.j.c();
        }
    }
    
    public String getSdkVersion() {
        return "1.7";
    }
    
    public void setDeviceStorageMode(final SystemParameter.StorageMode storageMode, final StorageModeCallback storageModeCallback) {
        if (null != storageModeCallback) {
            if (null != this.j && this.j.d()) {
                this.j.a(storageMode, storageModeCallback);
            }
            else {
                storageModeCallback.onFail(0);
            }
        }
    }
    
    public void setDataType(final SystemParameter.DataType l) {
        this.l = l;
    }
    
    public void getDeviceStorageMode(final GetStorageModeCallback getStorageModeCallback) {
        if (null != getStorageModeCallback) {
            if (null != this.j && this.j.d()) {
                this.j.a(getStorageModeCallback);
            }
            else {
                getStorageModeCallback.onFail(0);
            }
        }
    }
    
    public void deleteData(final DeleteDataCallback deleteDataCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(deleteDataCallback);
        }
        else if (null != deleteDataCallback) {
            deleteDataCallback.onFail(0);
        }
    }
    
    public void getDataStorageInfo(final SystemParameter.DataStorageInfo dataStorageInfo, final DataStorageInfoCallback dataStorageInfoCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(dataStorageInfo, dataStorageInfoCallback);
        }
        else if (null != dataStorageInfoCallback) {
            dataStorageInfoCallback.onFail(0);
        }
    }
    
    public boolean defineBTPrefix(final DeviceType deviceType, final String[] array) {
        if (array == null || deviceType == null) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (TextUtils.isEmpty((CharSequence)array[i]) || this.o.containsKey(array[i])) {
                return false;
            }
            this.o.put(array[i], deviceType);
        }
        return true;
    }
    
    public boolean defineBTPrefix(final DeviceType deviceType, final String s) {
        if (deviceType == null || TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        if (this.o.containsKey(s)) {
            return false;
        }
        this.o.put(s, deviceType);
        return true;
    }
    
    private DeviceType a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final TreeMap<String, DeviceType> treeMap = new TreeMap<String, DeviceType>(new Comparator<String>() {
            @Override
            public int compare(String s, String s1) {
                if (s.length() > s1.length()) {
                    return -1;
                }
                if (s.length() < s1.length()) {
                    return 1;
                }
                return s.compareTo(s1);
            }
        });
        treeMap.putAll(this.o);
        DeviceType deviceType = null;
        for (final String prefix : treeMap.keySet()) {
            if (s.startsWith(prefix)) {
                deviceType = treeMap.get(prefix);
                break;
            }
        }
        return deviceType;
    }
    
    public void startRealtimeSpO2(final RealtimeSpO2Callback realtimeSpO2Callback) {
        if (null != this.j && this.j.d()) {
            this.j.a(realtimeSpO2Callback);
        }
        else if (null != realtimeSpO2Callback) {
            realtimeSpO2Callback.onFail(0);
        }
    }
    
    public void setStepsTime(final int n, final int n2, final SetStepsTimeCallback setStepsTimeCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(n, n2, setStepsTimeCallback);
        }
        else if (null != setStepsTimeCallback) {
            setStepsTimeCallback.onFail(0);
        }
    }
    
    public void setWeight(final int n, final SetWeightCallback setWeightCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(n, setWeightCallback);
        }
        else if (null != setWeightCallback) {
            setWeightCallback.onFail(0);
        }
    }
    
    public void setHeight(final int n, final SetHeightCallback setHeightCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(n, setHeightCallback);
        }
        else if (null != setHeightCallback) {
            setHeightCallback.onFail(0);
        }
    }
    
    public void setCalorie(final int n, final int n2, final SystemParameter.StepsSensitivity stepsSensitivity, final SetCalorieCallback setCalorieCallback) {
        if (null != this.j && this.j.d()) {
            this.j.a(n, n2, stepsSensitivity, setCalorieCallback);
        }
        else if (null != setCalorieCallback) {
            setCalorieCallback.onFail(0);
        }
    }
    
    static {
        ContecSdk.connectOverTime = 20000;
        ContecSdk.m = false;
        ContecSdk.n = null;
    }
}

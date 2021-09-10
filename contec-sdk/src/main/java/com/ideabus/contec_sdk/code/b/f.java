// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.b;

import android.util.Log;

import com.ideabus.contec_sdk.code.bean.DayStepsData;
import com.ideabus.contec_sdk.code.bean.FiveMinStepsData;
import com.ideabus.contec_sdk.code.bean.PieceData;
import com.ideabus.contec_sdk.code.bean.SpO2PointData;
import com.ideabus.contec_sdk.code.bean.SystemParameter;
import com.ideabus.contec_sdk.code.bean.g;
import com.ideabus.contec_sdk.code.callback.CommunicateFailCallback;
import com.ideabus.contec_sdk.code.callback.DeleteDataCallback;
import com.ideabus.contec_sdk.code.callback.GetStorageModeCallback;
import com.ideabus.contec_sdk.code.callback.RealtimeCallback;
import com.ideabus.contec_sdk.code.callback.RealtimeSpO2Callback;
import com.ideabus.contec_sdk.code.callback.SetCalorieCallback;
import com.ideabus.contec_sdk.code.callback.SetHeightCallback;
import com.ideabus.contec_sdk.code.callback.SetStepsTimeCallback;
import com.ideabus.contec_sdk.code.callback.SetWeightCallback;
import com.ideabus.contec_sdk.code.callback.StorageModeCallback;
import com.ideabus.contec_sdk.code.connect.ContecSdk;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class f extends b
{
    public a av;
    
    public f() {
        this.av = null;
    }
    
    @Override
    public void a(final RealtimeCallback referent) {
        if (this.v) {
            return;
        }
        if (this.w) {
            return;
        }
        if (null != referent) {
            this.l = new WeakReference<RealtimeCallback>(referent).get();
        }
        this.w = true;
        Log.e("h", Integer.toString(0));
        this.a(com.ideabus.contec_sdk.code.b.a.h(0));
        this.b(this.l);
        this.a(1000);
        this.n = 4;
        Log.e("h", Integer.toString(1));
        this.a(com.ideabus.contec_sdk.code.b.a.h(1));
        this.c(this.l);
        if (this.ak == null) {
            (this.ak = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    f.this.a(com.ideabus.contec_sdk.code.b.a.h());
                }
            }, 5500L, 4500L);
        }
    }
    
    @Override
    public void c() {
        if (this.v) {
            return;
        }
        this.a(com.ideabus.contec_sdk.code.b.a.h(127));
        this.s = 10158463;
        if (this.n == 4) {
            this.a((CommunicateFailCallback)this.l);
        }
        else if (this.n == 7) {
            this.a((CommunicateFailCallback)this.m);
        }
    }
    
    @Override
    public void a(final GetStorageModeCallback getStorageModeCallback) {
        if (getStorageModeCallback != null) {
            getStorageModeCallback.onFail(255);
        }
    }
    
    @Override
    public void a(final SystemParameter.StorageMode storageMode, final StorageModeCallback storageModeCallback) {
        if (storageModeCallback != null) {
            storageModeCallback.onFail(255);
        }
    }
    
    @Override
    public void a(final DeleteDataCallback referent) {
        this.k = new WeakReference<DeleteDataCallback>(referent).get();
        this.n = 6;
        this.a(com.ideabus.contec_sdk.code.b.a.k(0));
    }
    
    @Override
    public void a(final RealtimeSpO2Callback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.m = new WeakReference<RealtimeSpO2Callback>(referent).get();
        }
        this.n = 7;
        this.a(com.ideabus.contec_sdk.code.b.a.h(1));
        this.m();
        if (this.ak == null) {
            (this.ak = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    f.this.a(com.ideabus.contec_sdk.code.b.a.h());
                }
            }, 5500L, 4500L);
        }
    }
    
    @Override
    public void a(final int n, final int n2, final SystemParameter.StepsSensitivity stepsSensitivity, final SetCalorieCallback setCalorieCallback) {
    }
    
    @Override
    public void a(final int n, final SetWeightCallback setWeightCallback) {
    }
    
    @Override
    public void a(final int n, final SetHeightCallback setHeightCallback) {
    }
    
    @Override
    public void a(final int n, final int n2, final SetStepsTimeCallback setStepsTimeCallback) {
    }
    
    @Override
    public void b(final byte[] array) {
        if (null != this.x) {
            for (int i = 0; i < array.length; ++i) {
                this.x.offer(array[i]);
            }
        }
        if (this.av == null) {
            (this.av = new a(this.x)).start();
        }
    }
    
    protected void c(final byte[] array) {
        final short[] m = com.ideabus.contec_sdk.code.tools.b.m(array);
        if (this.Y != null && (this.ae + 1) * 27 < this.Y.length) {
            for (int i = 27 * this.ae; i < 27 * (this.ae + 1); ++i) {
                this.Y[i] = m[i - 27 * this.ae];
            }
        }
        else if (this.Y != null) {
            for (int j = 27 * this.ae; j < this.Y.length; ++j) {
                this.Y[j] = m[j - 27 * this.ae];
            }
        }
        ++this.ae;
        if (this.ae * 27 >= this.I) {
            this.ae = 0;
            this.a(com.ideabus.contec_sdk.code.b.a.i(1));
            this.s = 10486017;
            this.a((CommunicateFailCallback)this.g);
        }
    }
    
    protected void d(final byte[] array) {
        final short[] m = com.ideabus.contec_sdk.code.tools.b.m(array);
        if (this.Z != null && (this.ae + 1) * 27 < this.Z.length) {
            for (int i = this.ae * 27; i < (this.ae + 1) * 27; ++i) {
                this.Z[i] = (m[i - this.ae * 27] & 0x7F);
            }
        }
        else if (this.Z != null) {
            for (int j = this.ae * 27; j < this.Z.length; ++j) {
                this.Z[j] = (m[j - this.ae * 27] & 0x7F);
            }
        }
        ++this.ae;
        if (this.ae * 27 >= this.I) {
            this.H = 0;
            this.ae = 0;
            this.n();
        }
    }
    
    private void n1() {
        final com.ideabus.contec_sdk.code.bean.b b = new com.ideabus.contec_sdk.code.bean.b();
        this.b(b);
        this.a(b);
        if (ContecSdk.isDelete) {
            this.a(com.ideabus.contec_sdk.code.b.a.k(0));
        }
        else {
            this.e();
            this.f();
            this.a();
        }
        this.k();
    }
    
    private void b(final PieceData pieceData) {
        pieceData.setDataType(this.am);
        pieceData.setTotalNumber(this.D);
        pieceData.setCaseCount(this.N);
        pieceData.setSupportPI(0);
        pieceData.setLength(this.I);
        pieceData.setStartTime(this.ag);
        pieceData.setSpo2Data(this.Z);
        pieceData.setPrData(this.Y);
    }
    
    @Override
    public void e() {
        if (null != this.x) {
            this.x.clear();
        }
        if (this.av != null) {
            this.av.a();
            this.av = null;
        }
    }
    
    public void l() {
        if (this.ak != null) {
            this.ak.cancel();
            this.ak = null;
        }
    }
    
    protected void m() {
        this.f();
        if (this.r == null) {
            (this.r = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    f.this.a(com.ideabus.contec_sdk.code.b.a.h(1));
                    f.this.c(f.this.m);
                }
            }, 5000L);
        }
    }
    
    public class a extends Thread
    {
        private ConcurrentLinkedQueue<Byte> b;
        private boolean c;
        private byte[] d;
        
        public a(final ConcurrentLinkedQueue<Byte> b) {
            this.b = null;
            this.c = false;
            this.d = new byte[128];
            this.b = b;
            this.c = true;
        }
        
        @Override
        public void run() {
            while (this.c) {
                if (null != this.b && !this.b.isEmpty()) {
                    this.a(this.d, 0, 1);
                    if (!this.c) {
                        return;
                    }
                    switch (this.d[0]) {
                        case -21: {
                            this.a(this.d, 1, 1);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] == 0) {
                                this.a(this.d, 2, 4);
                                if (!this.c) {
                                    return;
                                }
                                f.this.b(f.this.l);
                                final g b = com.ideabus.contec_sdk.code.tools.b.b(this.d);
                                if (f.this.l == null) {
                                    continue;
                                }
                                f.this.l.onRealtimeWaveData(b.a(), b.b(), b.c(), b.d(), b.e());
                                continue;
                            }
                            else if (this.d[1] == 1) {
                                this.a(this.d, 2, 6);
                                if (!this.c) {
                                    return;
                                }
                                f.this.i();
                                final com.ideabus.contec_sdk.code.bean.f d = com.ideabus.contec_sdk.code.tools.b.d(this.d);
                                if (f.this.l != null) {
                                    f.this.c(f.this.l);
                                    f.this.l.onSpo2Data(d.a(), d.c(), d.b(), d.d());
                                }
                                if (f.this.m == null) {
                                    continue;
                                }
                                f.this.c(f.this.m);
                                f.this.m.onRealtimeSpo2Data(d.c(), d.b(), d.d());
                                continue;
                            }
                            else {
                                if (this.d[1] != 127) {
                                    continue;
                                }
                                this.a(this.d, 2, 1);
                                if (!this.c) {
                                    return;
                                }
                                f.this.w = false;
                                f.this.e();
                                f.this.l();
                                f.this.i();
                                f.this.f();
                                f.this.g();
                                f.this.h();
                                if (f.this.l != null) {
                                    f.this.l.onRealtimeEnd();
                                }
                                if (f.this.m != null) {
                                    f.this.m.onRealtimeSpo2End();
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -15: {
                            this.a(this.d, 1, 9);
                            if (!this.c) {
                                return;
                            }
                            f.this.s = 8585475;
                            f.this.a(f.this.g);
                            f.this.a(com.ideabus.contec_sdk.code.b.a.d());
                            continue;
                        }
                        case -13: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            f.this.a(com.ideabus.contec_sdk.code.b.a.a(0, 24));
                            f.this.s = 8651012;
                            f.this.a(f.this.g);
                            continue;
                        }
                        case -12: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            f.this.a(com.ideabus.contec_sdk.code.b.a.a(0));
                            f.this.s = 9437440;
                            f.this.a(f.this.g);
                            continue;
                        }
                        case -32: {
                            this.a(this.d, 1, 6);
                            if (!this.c) {
                                return;
                            }
                            final int n = (this.d[4] & 0x7F) | ((this.d[5] & 0x7F) << 7 & 0xFFFF);
                            if (f.this.n == 2) {
                                f.this.f();
                                if (f.this.j != null) {
                                    f.this.j.onSuccess(f.this.an, n);
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (f.this.n != 3) {
                                    continue;
                                }
                                if ((this.d[1] & 0x7) == 0x0) {
                                    f.this.z = n;
                                    if (f.this.z > 0) {
                                        f.this.aj = (ArrayList<SpO2PointData>)new ArrayList();
                                        f.this.a(com.ideabus.contec_sdk.code.b.a.b(0));
                                        f.this.s = 9502976;
                                        f.this.a(f.this.g);
                                        continue;
                                    }
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.a(1));
                                    f.this.s = 9437441;
                                    f.this.a(f.this.g);
                                    continue;
                                }
                                else if ((this.d[1] & 0x7) == 0x1) {
                                    f.this.A = n;
                                    if (f.this.A > 0) {
                                        f.this.ao = (ArrayList<DayStepsData>)new ArrayList();
                                        f.this.a(com.ideabus.contec_sdk.code.b.a.c(0));
                                        f.this.s = 9568512;
                                        f.this.a(f.this.g);
                                        continue;
                                    }
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.a(2));
                                    f.this.s = 9634048;
                                    f.this.a(f.this.g);
                                    continue;
                                }
                                else if ((this.d[1] & 0x7) == 0x2) {
                                    f.this.B = n;
                                    if (f.this.B > 0) {
                                        f.this.ap = (ArrayList<FiveMinStepsData>)new ArrayList();
                                        f.this.a(com.ideabus.contec_sdk.code.b.a.d(1));
                                        f.this.s = 9437442;
                                        f.this.a(f.this.g);
                                        continue;
                                    }
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.a(3));
                                    f.this.s = 9437443;
                                    f.this.a(f.this.g);
                                    continue;
                                }
                                else {
                                    if ((this.d[1] & 0x7) != 0x3) {
                                        continue;
                                    }
                                    f.this.C = n;
                                    if (f.this.C > 0) {
                                        f.this.a(com.ideabus.contec_sdk.code.b.a.f(0));
                                        f.this.s = 9765120;
                                        f.this.a(f.this.g);
                                        continue;
                                    }
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.i(0));
                                    f.this.s = 10486016;
                                    f.this.a(f.this.g);
                                    continue;
                                }
                            }
                        }
                        case -31: {
                            this.a(this.d, 1, 10);
                            if (!this.c) {
                                return;
                            }
                            f.this.aj.add(com.ideabus.contec_sdk.code.tools.b.e(this.d));
                            if (f.this.E == 10) {
                                f.this.E = 0;
                            }
                            if (f.this.E != (this.d[1] & 0xF)) {
                                f.this.a(500);
                                if (this.b != null) {
                                    this.b.clear();
                                }
                                f.this.E = 10;
                                f.this.a(com.ideabus.contec_sdk.code.b.a.b(2));
                                f.this.s = 9502976;
                                f.this.a(f.this.g);
                                continue;
                            }
                            f.this.E++;
                            if (f.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.b(1));
                                f.this.s = 9502976;
                                f.this.a(f.this.g);
                                continue;
                            }
                            if ((this.d[1] & 0x40) != 0x0) {
                                if (ContecSdk.isDelete) {
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.b(127));
                                }
                                else {
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.b(126));
                                }
                                f.this.a(500);
                                f.this.E = 0;
                                f.this.a(com.ideabus.contec_sdk.code.b.a.a(1));
                                f.this.s = 9437441;
                                f.this.a(f.this.g);
                                f.this.a(f.this.aj);
                                continue;
                            }
                            continue;
                        }
                        case -30: {
                            f.this.a(f.this.g);
                            this.a(this.d, 1, 10);
                            if (!this.c) {
                                return;
                            }
                            f.this.ao.add(com.ideabus.contec_sdk.code.tools.b.f(this.d));
                            if (f.this.E == 10) {
                                f.this.E = 0;
                            }
                            if (f.this.E != (this.d[1] & 0xF)) {
                                f.this.a(500);
                                if (this.b != null) {
                                    this.b.clear();
                                }
                                f.this.E = 10;
                                f.this.a(com.ideabus.contec_sdk.code.b.a.c(2));
                                f.this.s = 9568512;
                                f.this.a(f.this.g);
                                continue;
                            }
                            f.this.E++;
                            if (f.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.c(1));
                                f.this.s = 9568512;
                                f.this.a(f.this.g);
                                continue;
                            }
                            if ((this.d[1] & 0x40) != 0x0) {
                                if (ContecSdk.isDelete) {
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.c(127));
                                }
                                else {
                                    f.this.a(com.ideabus.contec_sdk.code.b.a.c(126));
                                }
                                f.this.a(500);
                                f.this.E = 0;
                                f.this.s = 9634048;
                                f.this.a(f.this.g);
                                f.this.a(com.ideabus.contec_sdk.code.b.a.a(2));
                                f.this.b(f.this.ao);
                                continue;
                            }
                            continue;
                        }
                        case -29: {
                            f.this.aq = new FiveMinStepsData();
                            this.a(this.d, 1, 8);
                            if (!this.c) {
                                return;
                            }
                            final int year = (this.d[1] & 0x7F) + 2000;
                            final int month = this.d[2] & 0xF;
                            final int day = this.d[3] & 0x1F;
                            final int length = ((this.d[6] & 0x7F) | (this.d[7] & 0x7F) << 7) & 0xFFFF;
                            f.this.aq.setYear(year);
                            f.this.aq.setMonth(month);
                            f.this.aq.setDay(day);
                            f.this.aq.setLength(length);
                            f.this.ar = new short[length * 2];
                            f.this.a(com.ideabus.contec_sdk.code.b.a.e(0));
                            f.this.s = 9699584;
                            f.this.a(f.this.g);
                            continue;
                        }
                        case -28: {
                            this.a(this.d, 1, 16);
                            if (!this.c) {
                                return;
                            }
                            final short[] h = com.ideabus.contec_sdk.code.tools.b.h(this.d);
                            if (f.this.E == 10) {
                                f.this.E = 0;
                            }
                            if (null != f.this.ar && (f.this.ae + 1) * 6 < f.this.ar.length) {
                                for (int i = f.this.ae * 6; i < (f.this.ae + 1) * 6; ++i) {
                                    f.this.ar[i] = h[i - f.this.ae * 6];
                                }
                            }
                            else if (null != f.this.ar) {
                                for (int j = f.this.ae * 6; j < f.this.ar.length; ++j) {
                                    f.this.ar[j] = h[j - f.this.ae * 6];
                                }
                            }
                            if (f.this.E != (this.d[1] & 0xF)) {
                                continue;
                            }
                            f.this.E++;
                            f.this.ae++;
                            if (f.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.e(1));
                                f.this.s = 9699584;
                                f.this.a(f.this.g);
                                continue;
                            }
                            if ((this.d[1] & 0x40) == 0x0 || f.this.ae * 6 < f.this.ar.length) {
                                continue;
                            }
                            f.this.E = 0;
                            f.this.ae = 0;
                            f.this.aq.setStepFiveDataBean(f.this.ar);
                            f.this.ap.add(f.this.aq);
                            if (ContecSdk.isDelete) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.e(127));
                            }
                            else {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.e(126));
                            }
                            f.this.a(500);
                            if (f.this.ap.size() < f.this.B) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.d(1));
                                f.this.s = 9634048;
                                f.this.a(f.this.g);
                                continue;
                            }
                            f.this.a(com.ideabus.contec_sdk.code.b.a.a(3));
                            f.this.s = 9765120;
                            f.this.a(f.this.g);
                            f.this.c(f.this.ap);
                            continue;
                        }
                        case -27: {
                            this.a(this.d, 1, 17);
                            if (!this.c) {
                                return;
                            }
                            f.this.as = com.ideabus.contec_sdk.code.tools.b.i(this.d);
                            f.this.at = new int[f.this.as.getSize()];
                            f.this.a(com.ideabus.contec_sdk.code.b.a.g(0));
                            f.this.s = 9830656;
                            continue;
                        }
                        case -26: {
                            this.a(this.d, 1, 16);
                            if (!this.c) {
                                return;
                            }
                            final int[] k = com.ideabus.contec_sdk.code.tools.b.j(this.d);
                            if (f.this.E == 10) {
                                f.this.E = 0;
                            }
                            if (null != f.this.at && (f.this.ae + 1) * 6 < f.this.at.length) {
                                for (int l = f.this.ae * 6; l < (f.this.ae + 1) * 6; ++l) {
                                    f.this.at[l] = k[l - f.this.ae * 6];
                                }
                            }
                            else if (null != f.this.at) {
                                for (int n2 = f.this.ae * 6; n2 < f.this.at.length; ++n2) {
                                    f.this.at[n2] = k[n2 - f.this.ae * 6];
                                }
                            }
                            if (f.this.E != (this.d[1] & 0xF)) {
                                f.this.a(100);
                                f.this.ae -= f.this.E;
                                f.this.E = 10;
                                if (null != f.this.x) {
                                    f.this.x.clear();
                                }
                                f.this.a(com.ideabus.contec_sdk.code.b.a.g(2));
                                f.this.s = 9830656;
                                f.this.a(f.this.g);
                                continue;
                            }
                            f.this.E++;
                            f.this.ae++;
                            if (f.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.g(1));
                                f.this.s = 9765120;
                                f.this.a(f.this.g);
                                continue;
                            }
                            if ((this.d[1] & 0x40) == 0x0 || f.this.ae * 6 < f.this.as.getSize()) {
                                continue;
                            }
                            f.this.as.setUploadCount(f.this.C);
                            f.this.as.setCurrentCount(f.this.au);
                            f.this.as.setEcgData(f.this.at);
                            f.this.E = 0;
                            f.this.ae = 0;
                            if (ContecSdk.isDelete) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.g(127));
                            }
                            else {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.g(126));
                            }
                            f.this.a(500);
                            f.this.a(f.this.as);
                            if (f.this.au < f.this.C) {
                                f.this.au++;
                                f.this.a(com.ideabus.contec_sdk.code.b.a.f(1));
                                f.this.s = 9765120;
                                f.this.a(f.this.g);
                                continue;
                            }
                            f.this.au = 0;
                            f.this.a(com.ideabus.contec_sdk.code.b.a.i(0));
                            f.this.s = 10486016;
                            f.this.a(f.this.g);
                            continue;
                        }
                        case -48: {
                            this.a(this.d, 1, 13);
                            if (!this.c) {
                                return;
                            }
                            final int n3 = this.d[1] & 0x7;
                            f.this.ag = com.ideabus.contec_sdk.code.tools.b.k(this.d);
                            f.this.I = (((this.d[10] & 0x7F) | (this.d[11] & 0x7F) << 7 | (this.d[12] & 0x7F) << 14) & -1);
                            if (f.this.I > 0) {
                                switch (n3) {
                                    case 0: {
                                        f.this.Y = new int[f.this.I];
                                        f.this.a(com.ideabus.contec_sdk.code.b.a.b(0, 0));
                                        f.this.s = 10617088;
                                        f.this.a(f.this.g);
                                        continue;
                                    }
                                    case 1: {
                                        f.this.Z = new int[f.this.I];
                                        f.this.a(com.ideabus.contec_sdk.code.b.a.c(0, 0));
                                        f.this.s = 10682624;
                                        f.this.a(f.this.g);
                                        continue;
                                    }
                                }
                                continue;
                            }
                            f.this.e();
                            f.this.f();
                            if (f.this.z != 0 || f.this.A != 0 || f.this.B != 0 || f.this.C != 0) {
                                f.this.k();
                            }
                            else {
                                f.this.j();
                            }
                            f.this.a();
                            continue;
                        }
                        case -47: {
                            this.a(this.d, 1, 3);
                            if (!this.c) {
                                return;
                            }
                            f.this.f();
                            if (this.d[1] == 0) {
                                f.this.a(com.ideabus.contec_sdk.code.b.a.k(1));
                                continue;
                            }
                            if (this.d[1] != 1) {
                                continue;
                            }
                            f.this.e();
                            f.this.a();
                            if ((this.d[2] & 0x7F) == 0x0) {
                                if (f.this.n == 3) {
                                    if (f.this.g != null) {
                                        f.this.g.onDeleteSuccess();
                                        continue;
                                    }
                                    continue;
                                }
                                else {
                                    if (f.this.n == 6 && f.this.k != null) {
                                        f.this.k.onSuccess();
                                        continue;
                                    }
                                    continue;
                                }
                            }
                            else if (f.this.n == 3) {
                                if (f.this.g != null) {
                                    f.this.g.onDeleteFail();
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (f.this.n == 6 && f.this.k != null) {
                                    f.this.k.onFail(1);
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -46: {
                            f.this.a(f.this.g);
                            this.a(this.d, 1, 19);
                            if (!this.c) {
                                return;
                            }
                            f.this.c(this.d);
                            continue;
                        }
                        case -45: {
                            f.this.a(f.this.g);
                            this.a(this.d, 1, 19);
                            if (!this.c) {
                                return;
                            }
                            f.this.d(this.d);
                            continue;
                        }
                    }
                }
            }
        }
        
        public void a(final byte[] array, final int n, final int n2) {
            for (int n3 = n; n3 < n2 + n && this.c; ++n3) {
                if (this.b != null && !this.b.isEmpty()) {
                    array[n3] = this.b.poll();
                }
                else {
                    --n3;
                }
            }
        }
        
        public void a() {
            this.c = false;
        }
    }
}

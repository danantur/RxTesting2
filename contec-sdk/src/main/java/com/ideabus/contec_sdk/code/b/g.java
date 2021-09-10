// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.b;

import com.ideabus.contec_sdk.code.a.b;
import com.ideabus.contec_sdk.code.bean.DayStepsData;
import com.ideabus.contec_sdk.code.bean.FiveMinStepsData;
import com.ideabus.contec_sdk.code.bean.PieceData;
import com.ideabus.contec_sdk.code.bean.SpO2PointData;
import com.ideabus.contec_sdk.code.bean.SystemParameter;
import com.ideabus.contec_sdk.code.bean.c;
import com.ideabus.contec_sdk.code.bean.d;
import com.ideabus.contec_sdk.code.bean.e;
import com.ideabus.contec_sdk.code.bean.f;
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
import com.ideabus.contec_sdk.verify.ContecFoetalEncryptUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class g extends b
{
    private String az;
    public a av;
    private ArrayList<Integer> aA;
    private ArrayList<Integer> aB;
    private ArrayList<Integer> aC;
    private boolean aD;
    int aw;
    int ax;
    int ay;

    public g() {
        this.az = "CMSBleStandardDevice";
        this.av = null;
        this.aA = new ArrayList<Integer>();
        this.aB = new ArrayList<Integer>();
        this.aC = new ArrayList<Integer>();
        this.aD = false;
        this.aw = 0;
        this.ax = 0;
        this.ay = 0;
    }

    @Override
    public void b() {
        this.i();
        this.o();
        this.e();
        super.b();
    }

    @Override
    public void a(final SystemParameter.StorageMode storageMode, final StorageModeCallback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.h = new WeakReference<StorageModeCallback>(referent).get();
        }
        this.am = 0;
        this.n = 0;
        this.F = 9371911;
        this.s = 9371911;
        this.a(this.h);
        this.a(com.ideabus.contec_sdk.code.b.a.a(storageMode));
    }

    @Override
    public void a(final DeleteDataCallback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.k = new WeakReference<DeleteDataCallback>(referent).get();
        }
        this.al = true;
        this.n = 6;
        if (ContecSdk.getIsCheckDevice()) {
            this.s = 10420480;
            this.a((CommunicateFailCallback)this.k);
            this.a(com.ideabus.contec_sdk.code.b.a.g());
        }
        else {
            this.s = 8519938;
            this.a((CommunicateFailCallback)this.k);
            this.a(com.ideabus.contec_sdk.code.b.a.c());
        }
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
        this.a(com.ideabus.contec_sdk.code.b.a.c());
        this.s = 8519938;
        this.a((CommunicateFailCallback)this.m);
    }

    @Override
    public void a(final int n, final int n2, final SetStepsTimeCallback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.c = new WeakReference<SetStepsTimeCallback>(referent).get();
        }
        this.n = 8;
        this.s = 8651012;
        this.a(this.c);
        this.a(com.ideabus.contec_sdk.code.b.a.a(n, n2));
    }

    @Override
    public void a(final int n, final SetWeightCallback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.d = new WeakReference<SetWeightCallback>(referent).get();
        }
        this.n = 9;
        this.s = 8716549;
        this.a(this.d);
        this.a(com.ideabus.contec_sdk.code.b.a.l(n));
    }

    @Override
    public void a(final int n, final SetHeightCallback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.e = new WeakReference<SetHeightCallback>(referent).get();
        }
        this.n = 10;
        this.s = 9044234;
        this.a(this.e);
        this.a(com.ideabus.contec_sdk.code.b.a.m(n));
    }

    @Override
    public void a(final int n, final int n2, final SystemParameter.StepsSensitivity stepsSensitivity, final SetCalorieCallback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.f = new WeakReference<SetCalorieCallback>(referent).get();
        }
        this.n = 11;
        this.s = 9109771;
        this.a(this.f);
        this.a(com.ideabus.contec_sdk.code.b.a.a(n, n2, stepsSensitivity));
    }

    @Override
    public void a(final GetStorageModeCallback referent) {
        if (this.v) {
            return;
        }
        if (null != referent) {
            this.i = new WeakReference<GetStorageModeCallback>(referent).get();
        }
        this.n = 1;
        this.s = 9306375;
        this.a((CommunicateFailCallback)this.i);
        this.a(com.ideabus.contec_sdk.code.b.a.l());
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
        this.n = 4;
        this.a(com.ideabus.contec_sdk.code.b.a.c());
        this.s = 8519938;
        this.a((CommunicateFailCallback)this.l);
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

    private void q() {
        if (this.ac != 0) {
            if (this.am == com.ideabus.contec_sdk.code.bean.a.g) {
                this.e();
                this.f();
                this.a();
                this.j();
                return;
            }
            if ((this.ac & 0x1) == 0x1) {
                this.s = 9437440;
                this.a((CommunicateFailCallback)this.g);
                this.a(com.ideabus.contec_sdk.code.b.a.a(0));
            }
            else if ((this.ac & 0x2) == 0x2) {
                this.a(com.ideabus.contec_sdk.code.b.a.a(1));
                this.s = 9437441;
                this.a((CommunicateFailCallback)this.g);
            }
            else if ((this.ac & 0x4) == 0x4) {
                this.a(com.ideabus.contec_sdk.code.b.a.a(2));
                this.s = 9634048;
                this.a((CommunicateFailCallback)this.g);
            }
            else if ((this.ac & 0x40) == 0x40) {
                this.s = 9437446;
                this.a((CommunicateFailCallback)this.g);
                this.a(com.ideabus.contec_sdk.code.b.a.a(6));
            }
        }
        else if (this.ad != 0) {
            if (this.am == com.ideabus.contec_sdk.code.bean.a.f || this.am == com.ideabus.contec_sdk.code.bean.a.e || this.am == com.ideabus.contec_sdk.code.bean.a.d || this.am == com.ideabus.contec_sdk.code.bean.a.h) {
                this.e();
                this.f();
                this.j();
                this.a();
                return;
            }
            this.am = com.ideabus.contec_sdk.code.bean.a.g;
            if ((this.ad & 0x1) == 0x1) {
                this.s = 10486016;
                this.a((CommunicateFailCallback)this.g);
                this.a(com.ideabus.contec_sdk.code.b.a.i(0));
            }
            else if ((this.ad & 0x2) == 0x2) {
                this.s = 10486017;
                this.a((CommunicateFailCallback)this.g);
                this.a(com.ideabus.contec_sdk.code.b.a.i(1));
            }
        }
    }

    private void o(final byte[] array) {
        this.e();
        this.f();
        this.a();
        if (array[2] == 0) {
            if (this.g != null) {
                this.g.onDeleteSuccess();
            }
            if (this.k != null) {
                this.k.onSuccess();
            }
        }
        else {
            if (this.g != null) {
                this.g.onDeleteFail();
            }
            if (this.k != null) {
                this.k.onFail(1);
            }
        }
    }

    protected void b(final int n) {
        if (n < 11) {
            if (this.n == 6) {
                this.f();
                this.e();
                this.a();
                if (this.k != null) {
                    this.k.onFail(255);
                }
            }
            else if (this.n == 3) {
                this.s = 9437440;
                this.a((CommunicateFailCallback)this.g);
                this.a(com.ideabus.contec_sdk.code.b.a.a(0));
            }
            else if (this.n == 4) {
                this.a(com.ideabus.contec_sdk.code.b.a.h(0));
                this.b(this.l);
                this.a(1000);
                this.a(com.ideabus.contec_sdk.code.b.a.h(1));
                this.c(this.l);
                if (this.ak == null) {
                    (this.ak = new Timer()).schedule(new TimerTask() {
                        @Override
                        public void run() {
                            g.this.a(com.ideabus.contec_sdk.code.b.a.h());
                        }
                    }, 5500L, 4500L);
                }
            }
            else if (this.n == 7) {
                this.a(com.ideabus.contec_sdk.code.b.a.h(1));
                this.s = 10158337;
                this.p();
                if (this.ak == null) {
                    (this.ak = new Timer()).schedule(new TimerTask() {
                        @Override
                        public void run() {
                            g.this.a(com.ideabus.contec_sdk.code.b.a.h());
                        }
                    }, 5500L, 4500L);
                }
            }
        }
        else if (this.n == 6) {
            this.s = 9306375;
            this.a((CommunicateFailCallback)this.k);
            this.a(com.ideabus.contec_sdk.code.b.a.l());
        }
        else if (this.n == 3) {
            this.F = 9371908;
            this.s = 9371908;
            this.a((CommunicateFailCallback)this.g);
            this.a(com.ideabus.contec_sdk.code.b.a.i());
        }
        else if (this.n == 4) {
            this.a(com.ideabus.contec_sdk.code.b.a.h(0));
            this.b(this.l);
            this.a(1000);
            this.a(com.ideabus.contec_sdk.code.b.a.h(1));
            this.c(this.l);
            if (this.ak == null) {
                (this.ak = new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        g.this.a(com.ideabus.contec_sdk.code.b.a.h());
                    }
                }, 5500L, 4500L);
            }
        }
        else if (this.n == 7) {
            this.a(com.ideabus.contec_sdk.code.b.a.h(1));
            this.s = 10158337;
            this.p();
            if (this.ak == null) {
                (this.ak = new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        g.this.a(com.ideabus.contec_sdk.code.b.a.h());
                    }
                }, 5500L, 4500L);
            }
        }
    }

    protected void l() {
        this.P = new int[this.J];
        this.a(com.ideabus.contec_sdk.code.b.a.a(1, 1, this.M, this.N, 0));
        this.s = 10289409;
        this.a((CommunicateFailCallback)this.g);
    }

    protected void m() {
        this.S = new int[this.J];
        this.a(com.ideabus.contec_sdk.code.b.a.b(3, 1, this.M, this.N, 0));
        this.s = 10289921;
        this.a((CommunicateFailCallback)this.g);
    }

    protected void n() {
        this.V = new int[this.J];
        this.a(com.ideabus.contec_sdk.code.b.a.b(4, 1, this.M, this.N, 0));
        this.s = 10290177;
        this.a((CommunicateFailCallback)this.g);
    }

    protected void c(final byte[] array) {
        final short[] n = com.ideabus.contec_sdk.code.tools.b.n(array);
        if (this.P != null && (this.ae + 1) * 27 < this.P.length) {
            for (int i = this.ae * 27; i < (this.ae + 1) * 27; ++i) {
                this.P[i] = (n[i - this.ae * 27] & 0x7F);
            }
        }
        else if (this.P != null) {
            for (int j = this.ae * 27; j < this.P.length; ++j) {
                this.P[j] = (n[j - this.ae * 27] & 0x7F);
            }
        }
        ++this.ae;
        if (this.ae * 27 >= this.J) {
            this.ae = 0;
            this.Q = new int[this.J];
            this.a(com.ideabus.contec_sdk.code.b.a.a(1, 2, this.M, this.N, 0));
            this.s = 10289410;
            this.a((CommunicateFailCallback)this.g);
        }
    }

    protected void d(final byte[] array) {
        final short[] n = com.ideabus.contec_sdk.code.tools.b.n(array);
        if (this.Q != null && (this.ae + 1) * 27 < this.Q.length) {
            for (int i = this.ae * 27; i < (this.ae + 1) * 27; ++i) {
                this.Q[i] = n[i - this.ae * 27];
            }
        }
        else if (this.Q != null) {
            for (int j = this.ae * 27; j < this.Q.length; ++j) {
                this.Q[j] = n[j - this.ae * 27];
            }
        }
        ++this.ae;
        if (this.ae * 27 >= this.J) {
            this.ae = 0;
            if (this.O) {
                this.R = new int[this.J];
                this.a(com.ideabus.contec_sdk.code.b.a.a(1, 3, this.M, this.N, 0));
                this.s = 10289411;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                final d d = new d();
                this.b(d);
                this.a(d);
                if (this.G == 0) {
                    this.a(com.ideabus.contec_sdk.code.b.a.j(1));
                    this.s = 10223872;
                    this.a((CommunicateFailCallback)this.g);
                }
                else {
                    this.s();
                }
            }
        }
    }

    protected void e(final byte[] array) {
        final short[] n = com.ideabus.contec_sdk.code.tools.b.n(array);
        if (this.R != null && (this.ae + 1) * 27 < this.R.length) {
            for (int i = this.ae * 27; i < (this.ae + 1) * 27; ++i) {
                this.R[i] = n[i - this.ae * 27];
            }
        }
        else if (this.R != null) {
            for (int j = this.ae * 27; j < this.R.length; ++j) {
                this.R[j] = n[j - this.ae * 27];
            }
        }
        ++this.ae;
        if (this.ae * 27 >= this.J) {
            this.ae = 0;
            final d d = new d();
            this.b(d);
            this.a(d);
            if (this.G == 0) {
                this.a(com.ideabus.contec_sdk.code.b.a.j(1));
                this.s = 10223872;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                this.s();
            }
        }
    }

    protected void f(final byte[] array) {
        final short[] o = com.ideabus.contec_sdk.code.tools.b.o(array);
        if (this.S != null && (this.ae + 1) * 21 < this.S.length) {
            for (int i = this.ae * 21; i < (this.ae + 1) * 21; ++i) {
                this.S[i] = o[i - this.ae * 21];
            }
        }
        else if (this.S != null) {
            for (int j = this.ae * 21; j < this.S.length; ++j) {
                this.S[j] = o[j - this.ae * 21];
            }
        }
        ++this.ae;
        if (this.ae * 21 >= this.J) {
            this.E = 0;
            this.ae = 0;
            this.T = new int[this.J];
            this.a(com.ideabus.contec_sdk.code.b.a.b(3, 2, this.M, this.N, 0));
            this.s = 10289922;
            this.a((CommunicateFailCallback)this.g);
        }
    }

    protected void g(final byte[] array) {
        final short[] o = com.ideabus.contec_sdk.code.tools.b.o(array);
        if (this.T != null && (this.ae + 1) * 21 < this.T.length) {
            for (int i = this.ae * 21; i < (this.ae + 1) * 21; ++i) {
                this.T[i] = o[i - this.ae * 21];
            }
        }
        else if (this.T != null) {
            for (int j = this.ae * 21; j < this.T.length; ++j) {
                this.T[j] = o[j - this.ae * 21];
            }
        }
        ++this.ae;
        if (this.ae * 21 >= this.J) {
            this.E = 0;
            this.ae = 0;
            if (this.O) {
                this.U = new int[this.J];
                this.a(com.ideabus.contec_sdk.code.b.a.b(3, 3, this.M, this.N, 0));
                this.s = 10289923;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                final e e = new e();
                this.b(e);
                this.a(e);
                if (this.G == 0) {
                    this.a(com.ideabus.contec_sdk.code.b.a.j(1));
                    this.s = 10223872;
                    this.a((CommunicateFailCallback)this.g);
                }
                else {
                    this.s();
                }
            }
        }
    }

    protected void h(final byte[] array) {
        final short[] o = com.ideabus.contec_sdk.code.tools.b.o(array);
        if (this.U != null && (this.ae + 1) * 21 < this.U.length) {
            for (int i = this.ae * 21; i < (this.ae + 1) * 21; ++i) {
                this.U[i] = o[i - this.ae * 21];
            }
        }
        else if (this.T != null) {
            for (int j = this.ae * 21; j < this.U.length; ++j) {
                this.U[j] = o[j - this.ae * 21];
            }
        }
        ++this.ae;
        if (this.ae * 21 >= this.J) {
            this.ae = 0;
            final e e = new e();
            this.b(e);
            this.a(e);
            if (this.G == 0) {
                this.a(com.ideabus.contec_sdk.code.b.a.j(1));
                this.s = 10223872;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                this.s();
            }
        }
    }

    public void a(final byte[] array, final ArrayList<Integer> list) {
        final byte[] p2 = com.ideabus.contec_sdk.code.tools.b.p(array);
        for (int i = 8; i < 29; ++i) {
            if ((p2[i] & 0xF0) == 0xF0) {
                if (i + 1 < p2.length) {
                    if ((p2[i + 1] & 0xF0) == 0xF0) {
                        this.aw = (p2[i] & 0xF & 0xFF);
                        this.ax = (p2[i + 1] & 0xF & 0xFF);
                        if (((this.aw << 4 | this.ax) & 0xFF) != -1) {
                            this.ay = ((this.aw << 4 | this.ax) & 0xFF);
                        }
                        ++i;
                    }
                    else if (i == 0 && this.aD) {
                        this.ax = (p2[i] & 0xF & 0xFF);
                        if (((this.aw << 4 | this.ax) & 0xFF) != -1) {
                            this.ay = ((this.aw << 4 | this.ax) & 0xFF);
                        }
                        this.aD = false;
                    }
                }
                else {
                    this.aD = true;
                    this.aw = (p2[i] & 0xF & 0xFF);
                }
            }
            else {
                list.add(this.ay - (p2[i] >> 4 & 0xF) & 0xFF);
                if ((p2[i] & 0xF & 0xFF) != 0xF) {
                    list.add(this.ay - (p2[i] & 0xF) & 0xFF);
                }
            }
        }
    }

    protected void i(final byte[] array) {
        this.a(array, this.aA);
        ++this.ae;
        if (this.aA.size() >= this.J) {
            for (int i = 0; i < this.V.length; ++i) {
                this.V[i] = this.aA.get(i);
            }
            this.ae = 0;
            this.aw = 0;
            this.ax = 0;
            this.ay = 0;
            this.aD = false;
            if (null != this.aA) {
                this.aA.clear();
            }
            this.E = 0;
            this.W = new int[this.J];
            this.a(com.ideabus.contec_sdk.code.b.a.b(4, 2, this.M, this.N, 0));
            this.s = 10290178;
            this.a((CommunicateFailCallback)this.g);
        }
    }

    protected void j(final byte[] array) {
        this.a(array, this.aB);
        ++this.ae;
        if (this.aB.size() >= this.J) {
            for (int i = 0; i < this.V.length; ++i) {
                this.W[i] = this.aB.get(i);
            }
            this.ae = 0;
            this.aw = 0;
            this.ax = 0;
            this.ay = 0;
            this.aD = false;
            if (null != this.aB) {
                this.aB.clear();
            }
            if (this.O) {
                this.X = new int[this.J];
                this.a(com.ideabus.contec_sdk.code.b.a.b(4, 3, this.M, this.N, 0));
                this.s = 10290179;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                final c c = new c();
                this.b(c);
                this.a(c);
                if (this.G == 0) {
                    this.a(com.ideabus.contec_sdk.code.b.a.j(1));
                    this.s = 10223872;
                    this.a((CommunicateFailCallback)this.g);
                }
                else {
                    this.s();
                }
            }
        }
    }

    protected void k(final byte[] array) {
        this.a(array, this.aC);
        ++this.ae;
        if (this.aC.size() >= this.J) {
            for (int i = 0; i < this.X.length; ++i) {
                this.X[i] = this.aC.get(i);
            }
            this.ae = 0;
            this.aw = 0;
            this.ax = 0;
            this.ay = 0;
            this.aD = false;
            if (null != this.aC) {
                this.aC.clear();
            }
            final c c = new c();
            this.b(c);
            this.a(c);
            if (this.G == 0) {
                this.a(com.ideabus.contec_sdk.code.b.a.j(1));
                this.s = 10223872;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                this.s();
            }
        }
    }

    protected void l(final byte[] array) {
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
            if ((this.ad & 0x2) == 0x2) {
                this.a(com.ideabus.contec_sdk.code.b.a.i(1));
                this.s = 10486017;
                this.a((CommunicateFailCallback)this.g);
            }
            else if ((this.ad & 0x10) == 0x10) {
                this.a(com.ideabus.contec_sdk.code.b.a.i(4));
                this.s = 10486020;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                this.r();
            }
        }
    }

    protected void m(final byte[] array) {
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
            if ((this.ad & 0x10) == 0x10) {
                this.O = true;
                this.a(com.ideabus.contec_sdk.code.b.a.i(4));
                this.s = 10486020;
                this.a((CommunicateFailCallback)this.g);
            }
            else {
                this.r();
            }
        }
    }

    protected void n(final byte[] array) {
        final short[] m = com.ideabus.contec_sdk.code.tools.b.m(array);
        if (this.ab != null && (this.ae + 1) * 27 < this.ab.length) {
            for (int i = this.ae * 27; i < (this.ae + 1) * 27; ++i) {
                this.ab[i] = m[i - this.ae * 27];
            }
        }
        else if (this.ab != null) {
            for (int j = this.ae * 27; j < this.ab.length; ++j) {
                this.ab[j] = m[j - this.ae * 27];
            }
        }
        ++this.ae;
        if (this.ae * 27 >= this.I) {
            this.H = 0;
            this.ae = 0;
            this.r();
        }
    }

    private void r() {
        final com.ideabus.contec_sdk.code.bean.b b = new com.ideabus.contec_sdk.code.bean.b();
        this.b(b);
        this.a(b);
        if (ContecSdk.isDelete) {
            this.a(com.ideabus.contec_sdk.code.b.a.k(0));
            this.s = 10486016;
            this.a((CommunicateFailCallback)this.g);
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
        if (this.O) {
            pieceData.setSupportPI(1);
        }
        else {
            pieceData.setSupportPI(0);
        }
        int[] spo2Data = null;
        int[] prData = null;
        int[] piData = null;
        if (pieceData instanceof d) {
            pieceData.setLength(this.J);
            pieceData.setStartTime(this.ah);
            spo2Data = new int[this.J];
            prData = new int[this.J];
            System.arraycopy(this.P, 0, spo2Data, 0, this.J);
            System.arraycopy(this.Q, 0, prData, 0, this.J);
            if (this.R != null) {
                piData = new int[this.J];
                System.arraycopy(this.R, 0, piData, 0, this.J);
            }
        }
        else if (pieceData instanceof e) {
            pieceData.setLength(this.J);
            pieceData.setStartTime(this.ah);
            spo2Data = new int[this.J];
            prData = new int[this.J];
            System.arraycopy(this.S, 0, spo2Data, 0, this.J);
            System.arraycopy(this.T, 0, prData, 0, this.J);
            if (this.U != null) {
                piData = new int[this.J];
                System.arraycopy(this.U, 0, piData, 0, this.J);
            }
        }
        else if (pieceData instanceof c) {
            pieceData.setLength(this.J);
            pieceData.setStartTime(this.ah);
            spo2Data = new int[this.J];
            prData = new int[this.J];
            System.arraycopy(this.V, 0, spo2Data, 0, this.J);
            System.arraycopy(this.W, 0, prData, 0, this.J);
            if (this.X != null) {
                piData = new int[this.J];
                System.arraycopy(this.X, 0, piData, 0, this.J);
            }
        }
        else if (pieceData instanceof com.ideabus.contec_sdk.code.bean.b) {
            pieceData.setLength(this.I);
            pieceData.setStartTime(this.ag);
            spo2Data = new int[this.I];
            prData = new int[this.I];
            System.arraycopy(this.Z, 0, spo2Data, 0, this.I);
            System.arraycopy(this.Y, 0, prData, 0, this.I);
            if (this.ab != null) {
                piData = new int[this.I];
                System.arraycopy(this.ab, 0, piData, 0, this.I);
            }
        }
        pieceData.setSpo2Data(spo2Data);
        pieceData.setPrData(prData);
        pieceData.setPiData(piData);
    }

    private void s() {
        if (ContecSdk.isDelete) {
            this.a(com.ideabus.contec_sdk.code.b.a.n());
            this.s = 10321791;
            this.a((CommunicateFailCallback)this.g);
        }
        else {
            this.e();
            this.f();
            this.a();
        }
        this.k();
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

    public void o() {
        if (this.ak != null) {
            this.ak.cancel();
            this.ak = null;
        }
    }

    protected void p() {
        if (this.r == null) {
            (this.r = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    g.this.a(com.ideabus.contec_sdk.code.b.a.h(1));
                    g.this.c(g.this.m);
                }
            }, 10000L);
        }
    }

    @Override
    public void i() {
        if (this.r != null) {
            this.r.cancel();
            this.r = null;
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
                                g.this.b(g.this.l);
                                final com.ideabus.contec_sdk.code.bean.g b = com.ideabus.contec_sdk.code.tools.b.b(this.d);
                                if (g.this.l == null) {
                                    continue;
                                }
                                g.this.l.onRealtimeWaveData(b.a(), b.b(), b.c(), b.d(), b.e());
                                continue;
                            }
                            else if (this.d[1] == 1) {
                                this.a(this.d, 2, 6);
                                if (!this.c) {
                                    return;
                                }
                                g.this.i();
                                f f;
                                if (g.this.y < 11) {
                                    f = com.ideabus.contec_sdk.code.tools.b.d(this.d);
                                }
                                else {
                                    f = com.ideabus.contec_sdk.code.tools.b.c(this.d);
                                }
                                if (g.this.l != null && f != null) {
                                    g.this.c(g.this.l);
                                    g.this.l.onSpo2Data(f.a(), f.c(), f.b(), f.d());
                                }
                                if (g.this.m == null || f == null) {
                                    continue;
                                }
                                g.this.c(g.this.m);
                                g.this.m.onRealtimeSpo2Data(f.c(), f.b(), f.d());
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
                                g.this.w = false;
                                g.this.e();
                                g.this.o();
                                g.this.i();
                                g.this.f();
                                g.this.g();
                                g.this.h();
                                if (g.this.l != null) {
                                    g.this.l.onRealtimeEnd();
                                }
                                if (g.this.m != null) {
                                    g.this.m.onRealtimeSpo2End();
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -16: {
                            this.a(this.d, 1, 1);
                            if (!this.c) {
                                return;
                            }
                            g.this.i();
                            g.this.o();
                            g.this.f();
                            g.this.a();
                            g.this.s = 240;
                            if (g.this.n == 3) {
                                if (g.this.g != null) {
                                    g.this.g.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 1) {
                                if (g.this.i != null) {
                                    g.this.i.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 0) {
                                if (g.this.h != null) {
                                    g.this.h.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 2) {
                                if (g.this.j != null) {
                                    g.this.j.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 4) {
                                if (g.this.l != null) {
                                    g.this.l.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 6) {
                                if (g.this.k != null) {
                                    g.this.k.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 7) {
                                if (g.this.m != null) {
                                    g.this.m.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 8) {
                                if (g.this.c != null) {
                                    g.this.c.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 9) {
                                if (g.this.d != null) {
                                    g.this.d.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else if (g.this.n == 10) {
                                if (g.this.e != null) {
                                    g.this.e.onFail(g.this.s);
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (g.this.n == 11 && g.this.f != null) {
                                    g.this.f.onFail(g.this.s);
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
                            g.this.s = 8585475;
                            g.this.a(g.this.g);
                            g.this.a(com.ideabus.contec_sdk.code.b.a.d());
                            continue;
                        }
                        case -13: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            g.this.s = 8519938;
                            g.this.a(g.this.g);
                            g.this.a(com.ideabus.contec_sdk.code.b.a.c());
                            continue;
                        }
                        case -14: {
                            this.a(this.d, 1, 7);
                            if (!this.c) {
                                return;
                            }
                            g.this.f();
                            g.this.y = (this.d[6] & 0x7F);
                            g.this.b(g.this.y);
                            continue;
                        }
                        case -12: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            g.this.f();
                            g.this.e();
                            g.this.a();
                            if (this.d[1] == 0) {
                                if (g.this.d != null) {
                                    g.this.d.onSuccess();
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (g.this.d != null) {
                                    g.this.d.onFail(1);
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -11: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            g.this.f();
                            g.this.e();
                            g.this.a();
                            if (this.d[1] == 0) {
                                if (g.this.d != null) {
                                    g.this.d.onSuccess();
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (g.this.d != null) {
                                    g.this.d.onFail(1);
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -6: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            g.this.f();
                            g.this.e();
                            g.this.a();
                            if (this.d[1] == 0) {
                                if (g.this.e != null) {
                                    g.this.e.onSuccess();
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (g.this.e != null) {
                                    g.this.e.onFail(1);
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -5: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            g.this.f();
                            g.this.e();
                            g.this.a();
                            if (this.d[1] == 0) {
                                if (g.this.f != null) {
                                    g.this.f.onSuccess();
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (g.this.f != null) {
                                    g.this.f.onFail(1);
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -2: {
                            this.a(this.d, 1, 1);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] == 9) {
                                this.a(this.d, 2, 9);
                                if (!this.c) {
                                    return;
                                }
                                final byte[] q = com.ideabus.contec_sdk.code.tools.b.q(this.d);
                                final byte[] array = new byte[7];
                                new ContecFoetalEncryptUtils().b(array, q, ContecSdk.getRangID());
                                g.this.s = 9306377;
                                if (g.this.n == 3) {
                                    g.this.a(g.this.g);
                                }
                                else if (g.this.n == 6) {
                                    g.this.a(g.this.k);
                                }
                                g.this.a(com.ideabus.contec_sdk.code.b.a.b(array));
                                g.this.F = 9371914;
                                continue;
                            }
                            else if (this.d[1] == 7) {
                                this.a(this.d, 2, 3);
                                if (!this.c) {
                                    return;
                                }
                                final int n = this.d[2] & 0x7F;
                                if (g.this.n == 6) {
                                    if (n == 0) {
                                        g.this.s = 10321791;
                                        g.this.a(g.this.k);
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.n());
                                    }
                                    else if (n == 1) {
                                        g.this.s = 10420480;
                                        g.this.a(g.this.k);
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.g());
                                    }
                                    else {
                                        if (n != 2) {
                                            continue;
                                        }
                                        g.this.f();
                                        g.this.e();
                                        g.this.a();
                                        g.this.k.onFail(255);
                                    }
                                }
                                else {
                                    if (g.this.n != 1) {
                                        continue;
                                    }
                                    g.this.f();
                                    g.this.e();
                                    g.this.a();
                                    if (g.this.i == null) {
                                        continue;
                                    }
                                    g.this.i.onSuccess(n);
                                }
                                continue;
                            }
                            else {
                                if (this.d[1] != 6) {
                                    continue;
                                }
                                this.a(this.d, 2, 5);
                                if (!this.c) {
                                    return;
                                }
                                g.this.ai = (this.d[2] & 0xFF);
                                g.this.s = 10223872;
                                g.this.a(g.this.g);
                                g.this.a(com.ideabus.contec_sdk.code.b.a.j(1));
                                continue;
                            }
                        }
                        case -1: {
                            this.a(this.d, 1, 2);
                            if (!this.c) {
                                return;
                            }
                            if (g.this.F == 9371908) {
                                g.this.s = 10420480;
                                g.this.a(g.this.g);
                                g.this.al = true;
                                g.this.a(com.ideabus.contec_sdk.code.b.a.g());
                                continue;
                            }
                            if (g.this.F == 9371911) {
                                if (null == g.this.h) {
                                    continue;
                                }
                                g.this.f();
                                g.this.a();
                                if (this.d[1] == 0) {
                                    g.this.h.onSuccess();
                                    continue;
                                }
                                g.this.h.onFail(1);
                                continue;
                            }
                            else {
                                if (g.this.F != 9371914) {
                                    continue;
                                }
                                if (this.d[1] == 0) {
                                    if (g.this.n == 6) {
                                        g.this.s = 10321791;
                                        g.this.a(g.this.k);
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.n());
                                        continue;
                                    }
                                    if (g.this.n == 3) {
                                        g.this.q();
                                        continue;
                                    }
                                    continue;
                                }
                                else {
                                    g.this.f();
                                    g.this.a();
                                    if (g.this.n == 3) {
                                        if (g.this.g != null) {
                                            g.this.g.onFail(1);
                                            continue;
                                        }
                                        continue;
                                    }
                                    else {
                                        if (g.this.n == 6 && g.this.k != null) {
                                            g.this.k.onFail(1);
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                            }
                        }
                        case -17: {
                            this.a(this.d, 1, 7);
                            if (!this.c) {
                                return;
                            }
                            g.this.ac = (this.d[3] & 0x7F);
                            g.this.ad = (this.d[5] & 0x7F);
                            if ((this.d[1] & 0x1) == 0x1) {
                                g.this.a();
                                g.this.e();
                                g.this.f();
                                continue;
                            }
                            if ((this.d[2] & 0x1) == 0x1) {
                                if (!g.this.al) {
                                    continue;
                                }
                                g.this.al = false;
                                if (ContecSdk.getIsCheckDevice()) {
                                    g.this.F = 9306377;
                                    g.this.s = 9306377;
                                    if (g.this.n == 6) {
                                        g.this.a(g.this.k);
                                    }
                                    else if (g.this.n == 3) {
                                        g.this.a(g.this.k);
                                    }
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.m());
                                    continue;
                                }
                                if (g.this.n == 6) {
                                    g.this.s = 10551552;
                                    g.this.a(g.this.k);
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.k(0));
                                    continue;
                                }
                                if (g.this.n == 3) {
                                    g.this.q();
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (!g.this.al) {
                                    continue;
                                }
                                g.this.al = false;
                                g.this.e();
                                g.this.f();
                                g.this.a();
                                if (g.this.n == 6) {
                                    if (g.this.k != null) {
                                        g.this.k.onSuccess();
                                        continue;
                                    }
                                    continue;
                                }
                                else {
                                    if (g.this.n == 3) {
                                        g.this.j();
                                        continue;
                                    }
                                    continue;
                                }
                            }
                        }
                        case -32: {
                            this.a(this.d, 1, 1);
                            if (!this.c) {
                                return;
                            }
                            if ((this.d[1] & 0x7) == 0x6) {
                                this.a(this.d, 2, 13);
                                if (!this.c) {
                                    return;
                                }
                                final int n2 = (this.d[2] & 0x7F) | ((this.d[3] & 0x7F) << 7 & 0xFFFF);
                                g.this.D = n2;
                                if (g.this.n == 2) {
                                    g.this.f();
                                    g.this.e();
                                    g.this.a();
                                    if (g.this.j == null) {
                                        continue;
                                    }
                                    g.this.j.onSuccess(SystemParameter.DataStorageInfo.PIECESPO2DATAINFO, n2);
                                }
                                else {
                                    if (g.this.n != 3) {
                                        continue;
                                    }
                                    if (g.this.D == 0) {
                                        g.this.e();
                                        g.this.f();
                                        g.this.j();
                                        g.this.a();
                                    }
                                    else {
                                        g.this.s = 9306374;
                                        g.this.a(g.this.g);
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.k());
                                    }
                                }
                                continue;
                            }
                            else {
                                this.a(this.d, 2, 5);
                                if (!this.c) {
                                    return;
                                }
                                final int n3 = (this.d[2] & 0x7F) | ((this.d[3] & 0x7F) << 7 & 0xFFFF);
                                if (g.this.n == 2) {
                                    g.this.f();
                                    g.this.e();
                                    g.this.a();
                                    if (g.this.j == null) {
                                        continue;
                                    }
                                    g.this.j.onSuccess(SystemParameter.DataStorageInfo.POINTDATAINFO, n3);
                                }
                                else {
                                    if (g.this.n != 3) {
                                        continue;
                                    }
                                    if (n3 == 0) {
                                        g.this.e();
                                        g.this.f();
                                        g.this.a();
                                    }
                                    else if ((this.d[1] & 0x7) == 0x0) {
                                        g.this.z = n3;
                                        if (g.this.z <= 0) {
                                            continue;
                                        }
                                        g.this.aj = (ArrayList<SpO2PointData>)new ArrayList();
                                        g.this.am = com.ideabus.contec_sdk.code.bean.a.h;
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(0));
                                        g.this.s = 9502976;
                                        g.this.a(g.this.g);
                                    }
                                    else if ((this.d[1] & 0x7) == 0x1) {
                                        g.this.A = n3;
                                        if (g.this.A <= 0) {
                                            continue;
                                        }
                                        g.this.ao = (ArrayList<DayStepsData>)new ArrayList();
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.c(0));
                                        g.this.s = 9568512;
                                        g.this.a(g.this.g);
                                    }
                                    else {
                                        if ((this.d[1] & 0x7) != 0x2) {
                                            continue;
                                        }
                                        g.this.B = n3;
                                        if (g.this.B <= 0) {
                                            continue;
                                        }
                                        g.this.ap = (ArrayList<FiveMinStepsData>)new ArrayList();
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.d(1));
                                        g.this.s = 9437442;
                                        g.this.a(g.this.g);
                                    }
                                }
                                continue;
                            }
                        }
                        case -31: {
                            this.a(this.d, 1, 10);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] != 126 && this.d[1] != 127) {
                                g.this.aj.add(com.ideabus.contec_sdk.code.tools.b.e(this.d));
                                if (g.this.E == 10) {
                                    g.this.E = 0;
                                }
                                if (g.this.E == (this.d[1] & 0xF)) {
                                    g.this.E++;
                                    if (g.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                        g.this.s = 9502976;
                                        g.this.a(g.this.g);
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(1));
                                    }
                                    else {
                                        if ((this.d[1] & 0x40) == 0x0) {
                                            continue;
                                        }
                                        if (ContecSdk.isDelete) {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.b(127));
                                        }
                                        else {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.b(126));
                                        }
                                        g.this.a(500);
                                        g.this.E = 0;
                                        if ((g.this.ac & 0x2) == 0x2) {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.a(1));
                                            g.this.s = 9437441;
                                            g.this.a(g.this.g);
                                            g.this.a(g.this.aj);
                                        }
                                        else if ((g.this.ac & 0x4) == 0x4) {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.a(2));
                                            g.this.s = 9634048;
                                            g.this.a(g.this.g);
                                            g.this.a(g.this.aj);
                                        }
                                        else {
                                            g.this.a(g.this.aj);
                                            g.this.e();
                                            g.this.f();
                                            g.this.a();
                                            g.this.k();
                                            if (!ContecSdk.isDelete || g.this.g == null) {
                                                continue;
                                            }
                                            g.this.g.onDeleteSuccess();
                                        }
                                    }
                                }
                                else {
                                    g.this.a(100);
                                    if (this.b != null) {
                                        this.b.clear();
                                    }
                                    g.this.E = 10;
                                    g.this.s = 9502976;
                                    g.this.a(g.this.g);
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.b(2));
                                }
                                continue;
                            }
                            continue;
                        }
                        case -30: {
                            g.this.a(g.this.g);
                            this.a(this.d, 1, 10);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] != 126 && this.d[1] != 127) {
                                g.this.ao.add(com.ideabus.contec_sdk.code.tools.b.f(this.d));
                                if (g.this.E == 10) {
                                    g.this.E = 0;
                                }
                                if (g.this.E == (this.d[1] & 0xF)) {
                                    g.this.E++;
                                    if (g.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.c(1));
                                        g.this.s = 9568512;
                                        g.this.a(g.this.g);
                                    }
                                    else {
                                        if ((this.d[1] & 0x40) == 0x0) {
                                            continue;
                                        }
                                        if (ContecSdk.isDelete) {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.c(127));
                                        }
                                        else {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.c(126));
                                        }
                                        g.this.a(500);
                                        g.this.E = 0;
                                        if ((g.this.ac & 0x4) == 0x4) {
                                            g.this.s = 9634048;
                                            g.this.a(g.this.g);
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.a(2));
                                            g.this.b(g.this.ao);
                                        }
                                        else {
                                            g.this.b(g.this.ao);
                                            g.this.e();
                                            g.this.f();
                                            g.this.a();
                                            g.this.k();
                                            if (!ContecSdk.isDelete || g.this.g == null) {
                                                continue;
                                            }
                                            g.this.g.onDeleteSuccess();
                                        }
                                    }
                                }
                                else {
                                    g.this.a(500);
                                    if (this.b != null) {
                                        this.b.clear();
                                    }
                                    g.this.E = 10;
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.c(2));
                                    g.this.s = 9568512;
                                    g.this.a(g.this.g);
                                }
                                continue;
                            }
                            continue;
                        }
                        case -22: {
                            g.this.a(g.this.g);
                            this.a(this.d, 1, 12);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] != 126 && this.d[1] != 127) {
                                g.this.ao.add(com.ideabus.contec_sdk.code.tools.b.g(this.d));
                                if (g.this.E == 10) {
                                    g.this.E = 0;
                                }
                                if (g.this.E == (this.d[1] & 0xF)) {
                                    g.this.E++;
                                    if (g.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.c(1));
                                        g.this.s = 9568512;
                                        g.this.a(g.this.g);
                                    }
                                    else {
                                        if ((this.d[1] & 0x40) == 0x0) {
                                            continue;
                                        }
                                        if (ContecSdk.isDelete) {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.c(127));
                                        }
                                        else {
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.c(126));
                                        }
                                        g.this.a(500);
                                        g.this.E = 0;
                                        if ((g.this.ac & 0x4) == 0x4) {
                                            g.this.s = 9634048;
                                            g.this.a(g.this.g);
                                            g.this.a(com.ideabus.contec_sdk.code.b.a.a(2));
                                            g.this.b(g.this.ao);
                                        }
                                        else {
                                            g.this.b(g.this.ao);
                                            g.this.e();
                                            g.this.f();
                                            g.this.a();
                                            g.this.k();
                                            if (!ContecSdk.isDelete || g.this.g == null) {
                                                continue;
                                            }
                                            g.this.g.onDeleteSuccess();
                                        }
                                    }
                                }
                                else {
                                    g.this.a(500);
                                    if (this.b != null) {
                                        this.b.clear();
                                    }
                                    g.this.E = 10;
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.c(2));
                                    g.this.s = 9568512;
                                    g.this.a(g.this.g);
                                }
                                continue;
                            }
                            continue;
                        }
                        case -29: {
                            g.this.aq = new FiveMinStepsData();
                            this.a(this.d, 1, 8);
                            if (!this.c) {
                                return;
                            }
                            final int year = (this.d[1] & 0x7F) + 2000;
                            final int month = this.d[2] & 0xF;
                            final int day = this.d[3] & 0x1F;
                            final int length = ((this.d[6] & 0x7F) | (this.d[7] & 0x7F) << 7) & 0xFFFF;
                            g.this.aq.setYear(year);
                            g.this.aq.setMonth(month);
                            g.this.aq.setDay(day);
                            g.this.aq.setLength(length);
                            g.this.ar = new short[length * 2];
                            g.this.a(com.ideabus.contec_sdk.code.b.a.e(0));
                            g.this.s = 9699584;
                            g.this.a(g.this.g);
                            continue;
                        }
                        case -28: {
                            this.a(this.d, 1, 16);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] != 126 && this.d[1] != 127) {
                                final short[] h = com.ideabus.contec_sdk.code.tools.b.h(this.d);
                                if (g.this.E == 10) {
                                    g.this.E = 0;
                                }
                                if (null != g.this.ar && (g.this.ae + 1) * 6 < g.this.ar.length) {
                                    for (int i = g.this.ae * 6; i < (g.this.ae + 1) * 6; ++i) {
                                        g.this.ar[i] = h[i - g.this.ae * 6];
                                    }
                                }
                                else if (null != g.this.ar) {
                                    for (int j = g.this.ae * 6; j < g.this.ar.length; ++j) {
                                        g.this.ar[j] = h[j - g.this.ae * 6];
                                    }
                                }
                                if (g.this.E != (this.d[1] & 0xF)) {
                                    continue;
                                }
                                g.this.E++;
                                g.this.ae++;
                                if (g.this.E == 10 && (this.d[1] & 0x40) == 0x0) {
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.e(1));
                                    g.this.s = 9699584;
                                    g.this.a(g.this.g);
                                }
                                else {
                                    if ((this.d[1] & 0x40) == 0x0 || g.this.ae * 6 < g.this.ar.length) {
                                        continue;
                                    }
                                    g.this.E = 0;
                                    g.this.ae = 0;
                                    g.this.aq.setStepFiveDataBean(g.this.ar);
                                    g.this.ap.add(g.this.aq);
                                    if (ContecSdk.isDelete) {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.e(127));
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.e(126));
                                    }
                                    g.this.a(500);
                                    if (g.this.ap.size() < g.this.B) {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.d(1));
                                        g.this.s = 9634048;
                                        g.this.a(g.this.g);
                                    }
                                    else {
                                        g.this.c(g.this.ap);
                                        g.this.e();
                                        g.this.f();
                                        g.this.a();
                                        g.this.k();
                                        if (!ContecSdk.isDelete || g.this.g == null) {
                                            continue;
                                        }
                                        g.this.g.onDeleteSuccess();
                                    }
                                }
                                continue;
                            }
                            continue;
                        }
                        case -20: {
                            this.a(this.d, 1, 20);
                            if (!this.c) {
                                return;
                            }
                            g.this.G = (this.d[1] & 0x40);
                            if ((this.d[1] & 0xF) == 0x0) {
                                g.this.O = false;
                            }
                            else {
                                g.this.O = true;
                            }
                            g.this.L = g.this.N;
                            g.this.M = (this.d[2] & 0x7F);
                            g.this.N = (this.d[3] & 0x7F);
                            g.this.ah = com.ideabus.contec_sdk.code.tools.b.l(this.d);
                            g.this.J = (((this.d[10] & 0x7F) | (this.d[11] & 0x7F) << 7 | (this.d[12] & 0x7F) << 14 | (this.d[13] & 0x7F) << 21) & -1);
                            if (g.this.J == 0) {
                                g.this.e();
                                g.this.f();
                                g.this.j();
                                g.this.a();
                                continue;
                            }
                            if ((g.this.ai & 0x1) == 0x1 && g.this.am == com.ideabus.contec_sdk.code.bean.a.f) {
                                g.this.l();
                                continue;
                            }
                            if ((g.this.ai & 0x2) == 0x2 && g.this.am == com.ideabus.contec_sdk.code.bean.a.e) {
                                g.this.m();
                                continue;
                            }
                            if ((g.this.ai & 0x4) == 0x4 && g.this.am == com.ideabus.contec_sdk.code.bean.a.d) {
                                g.this.n();
                                continue;
                            }
                            if ((g.this.ai & 0x4) == 0x4 && g.this.am == 0) {
                                g.this.am = com.ideabus.contec_sdk.code.bean.a.d;
                                g.this.n();
                                continue;
                            }
                            if ((g.this.ai & 0x2) == 0x2 && g.this.am == 0) {
                                g.this.am = com.ideabus.contec_sdk.code.bean.a.e;
                                g.this.m();
                                continue;
                            }
                            if ((g.this.ai & 0x1) == 0x1 && g.this.am == 0) {
                                g.this.am = com.ideabus.contec_sdk.code.bean.a.f;
                                g.this.l();
                                continue;
                            }
                            g.this.e();
                            g.this.f();
                            g.this.j();
                            g.this.a();
                            continue;
                        }
                        case -19: {
                            this.a(this.d, 1, 1);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] == 1) {
                                g.this.a(g.this.g);
                                this.a(this.d, 2, 22);
                                if (!this.c) {
                                    return;
                                }
                                final byte[] array2 = new byte[24];
                                for (int k = 0; k < 24; ++k) {
                                    array2[k] = this.d[k];
                                }
                                final byte a = com.ideabus.contec_sdk.code.tools.b.a(array2);
                                final int n4 = ((this.d[5] & 0x7F) | (this.d[6] & 0x7F) << 7) & 0xFFFF;
                                if (this.d[2] == 1) {
                                    if (a == this.d[23] && n4 == g.this.ae) {
                                        g.this.c(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(1, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(1, 1, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10289409;
                                    }
                                }
                                else if (this.d[2] == 2) {
                                    if (a == this.d[23] && n4 == g.this.ae) {
                                        g.this.d(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(2, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(1, 2, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10289410;
                                    }
                                }
                                else {
                                    if (this.d[2] != 3) {
                                        continue;
                                    }
                                    if (a == this.d[23] && n4 == g.this.ae) {
                                        g.this.e(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(3, g.this.M, g.this.N));
                                        g.this.a(500);
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(1, 3, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10289411;
                                    }
                                }
                                continue;
                            }
                            else if (this.d[1] == 3) {
                                g.this.a(g.this.g);
                                this.a(this.d, 2, 28);
                                if (!this.c) {
                                    return;
                                }
                                final byte[] array3 = new byte[30];
                                for (int l = 0; l < 30; ++l) {
                                    array3[l] = this.d[l];
                                }
                                final byte a2 = com.ideabus.contec_sdk.code.tools.b.a(array3);
                                final int n5 = ((this.d[3] & 0x7F) | (this.d[4] & 0x7F) << 7) & 0xFFFF;
                                if (this.d[2] == 1) {
                                    if (a2 == this.d[29] && n5 == g.this.ae) {
                                        g.this.f(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(1, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(3, 1, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10289921;
                                    }
                                }
                                else if (this.d[2] == 2) {
                                    if (a2 == this.d[29] && n5 == g.this.ae) {
                                        g.this.g(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(2, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(3, 2, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10289922;
                                    }
                                }
                                else {
                                    if (this.d[2] != 3) {
                                        continue;
                                    }
                                    if (a2 == this.d[29] && n5 == g.this.ae) {
                                        g.this.h(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(3, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(3, 3, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10289923;
                                    }
                                }
                                continue;
                            }
                            else if (this.d[1] == 4) {
                                g.this.a(g.this.g);
                                this.a(this.d, 2, 28);
                                if (!this.c) {
                                    return;
                                }
                                final byte[] array4 = new byte[30];
                                for (int n6 = 0; n6 < 30; ++n6) {
                                    array4[n6] = this.d[n6];
                                }
                                final byte a3 = com.ideabus.contec_sdk.code.tools.b.a(array4);
                                final int n7 = ((this.d[3] & 0x7F) | (this.d[4] & 0x7F) << 7) & 0xFFFF;
                                if (this.d[2] == 1) {
                                    if (a3 == this.d[29] && n7 == g.this.ae) {
                                        g.this.i(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(1, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(4, 1, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10290177;
                                    }
                                }
                                else if (this.d[2] == 2) {
                                    if (a3 == this.d[29] && n7 == g.this.ae) {
                                        g.this.j(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(2, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(4, 2, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10290178;
                                    }
                                }
                                else {
                                    if (this.d[2] != 3) {
                                        continue;
                                    }
                                    if (a3 == this.d[29] && n7 == g.this.ae) {
                                        g.this.k(this.d);
                                    }
                                    else {
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.a(3, g.this.M, g.this.N));
                                        g.this.a(500);
                                        if (g.this.x != null) {
                                            g.this.x.clear();
                                        }
                                        g.this.a(com.ideabus.contec_sdk.code.b.a.b(4, 3, g.this.M, g.this.N, g.this.ae));
                                        g.this.s = 10290179;
                                    }
                                }
                                continue;
                            }
                            else {
                                if (this.d[1] != 127) {
                                    continue;
                                }
                                this.a(this.d, 2, 5);
                                if (!this.c) {
                                    return;
                                }
                                g.this.e();
                                g.this.f();
                                g.this.a();
                                if (this.d[5] == 0) {
                                    if (g.this.g != null) {
                                        g.this.g.onDeleteSuccess();
                                    }
                                    if (g.this.k != null) {
                                        g.this.k.onSuccess();
                                        continue;
                                    }
                                    continue;
                                }
                                else {
                                    if (g.this.g != null) {
                                        g.this.g.onDeleteFail();
                                    }
                                    if (g.this.k != null) {
                                        g.this.k.onFail(1);
                                        continue;
                                    }
                                    continue;
                                }
                            }
                        }
                        case -48: {
                            this.a(this.d, 1, 13);
                            if (!this.c) {
                                return;
                            }
                            final int n8 = this.d[1] & 0x7;
                            g.this.ag = com.ideabus.contec_sdk.code.tools.b.k(this.d);
                            if (n8 == 0) {
                                g.this.I = (((this.d[10] & 0x7F) | (this.d[11] & 0x7F) << 7 | (this.d[12] & 0x7F) << 14) & -1);
                            }
                            if (g.this.I == 0) {
                                g.this.e();
                                g.this.f();
                                g.this.j();
                                g.this.a();
                                continue;
                            }
                            switch (n8) {
                                case 0: {
                                    g.this.Y = new int[g.this.I];
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.b(0, 0));
                                    g.this.s = 10617088;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                                case 1: {
                                    g.this.Z = new int[g.this.I];
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.c(0, 0));
                                    g.this.s = 10682624;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                                case 4: {
                                    g.this.ab = new int[g.this.I];
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.e(0, 0));
                                    g.this.s = 10879232;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                            }
                            continue;
                        }
                        case -47: {
                            this.a(this.d, 1, 3);
                            if (!this.c) {
                                return;
                            }
                            if (this.d[1] == 0) {
                                if ((g.this.ad & 0x2) == 0x2) {
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.k(1));
                                    g.this.s = 10551553;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                                if ((g.this.ad & 0x4) == 0x4) {
                                    continue;
                                }
                                if ((g.this.ad & 0x8) == 0x8) {
                                    continue;
                                }
                                if ((g.this.ad & 0x10) == 0x10) {
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.k(4));
                                    g.this.s = 10551556;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                                g.this.o(this.d);
                                continue;
                            }
                            else if (this.d[1] == 1) {
                                if ((g.this.ad & 0x4) == 0x4) {
                                    continue;
                                }
                                if ((g.this.ad & 0x8) == 0x8) {
                                    continue;
                                }
                                if ((g.this.ad & 0x10) == 0x10) {
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.k(4));
                                    g.this.s = 10551556;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                                g.this.o(this.d);
                                continue;
                            }
                            else if (this.d[1] == 2) {
                                if ((g.this.ad & 0x8) == 0x8) {
                                    continue;
                                }
                                if ((g.this.ad & 0x10) == 0x10) {
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.k(4));
                                    g.this.s = 10551556;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                                g.this.o(this.d);
                                continue;
                            }
                            else if (this.d[1] == 3) {
                                if ((g.this.ad & 0x10) == 0x10) {
                                    g.this.a(com.ideabus.contec_sdk.code.b.a.k(4));
                                    g.this.s = 10551556;
                                    g.this.a(g.this.g);
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (this.d[1] == 4) {
                                    g.this.o(this.d);
                                    continue;
                                }
                                continue;
                            }
                        }
                        case -46: {
                            g.this.a(g.this.g);
                            this.a(this.d, 1, 19);
                            if (!this.c) {
                                return;
                            }
                            g.this.l(this.d);
                            continue;
                        }
                        case -45: {
                            g.this.a(g.this.g);
                            this.a(this.d, 1, 19);
                            if (!this.c) {
                                return;
                            }
                            g.this.m(this.d);
                            continue;
                        }
                        case -41: {
                            g.this.a(g.this.g);
                            this.a(this.d, 1, 19);
                            if (!this.c) {
                                return;
                            }
                            g.this.n(this.d);
                            continue;
                        }
                        default: {
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

// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.a;

import com.ideabus.contec_sdk.code.bean.DayStepsData;
import com.ideabus.contec_sdk.code.bean.EcgData;
import com.ideabus.contec_sdk.code.bean.FiveMinStepsData;
import com.ideabus.contec_sdk.code.bean.PieceData;
import com.ideabus.contec_sdk.code.bean.SpO2PointData;
import com.ideabus.contec_sdk.code.bean.SystemParameter;
import com.ideabus.contec_sdk.code.callback.CommunicateCallback;
import com.ideabus.contec_sdk.code.callback.CommunicateFailCallback;
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class b
{
    protected a a;
    protected boolean b;
    protected SetStepsTimeCallback c;
    protected SetWeightCallback d;
    protected SetHeightCallback e;
    protected SetCalorieCallback f;
    protected CommunicateCallback g;
    protected StorageModeCallback h;
    protected GetStorageModeCallback i;
    protected DataStorageInfoCallback j;
    protected DeleteDataCallback k;
    protected RealtimeCallback l;
    protected RealtimeSpO2Callback m;
    protected int n;
    protected Timer o;
    protected Timer p;
    protected Timer q;
    protected Timer r;
    protected int s;
    protected int t;
    protected int u;
    protected boolean v;
    protected boolean w;
    protected ConcurrentLinkedQueue<Byte> x;
    protected int y;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    protected int K;
    protected int L;
    protected int M;
    protected int N;
    protected boolean O;
    protected int[] P;
    protected int[] Q;
    protected int[] R;
    protected int[] S;
    protected int[] T;
    protected int[] U;
    protected int[] V;
    protected int[] W;
    protected int[] X;
    protected int[] Y;
    protected int[] Z;
    protected int[] aa;
    protected int[] ab;
    protected int ac;
    protected int ad;
    protected int ae;
    protected int af;
    protected String ag;
    protected String ah;
    protected int ai;
    protected ArrayList<SpO2PointData> aj;
    protected Timer ak;
    protected boolean al;
    protected int am;
    protected SystemParameter.DataStorageInfo an;
    protected ArrayList<DayStepsData> ao;
    protected ArrayList<FiveMinStepsData> ap;
    protected FiveMinStepsData aq;
    protected short[] ar;
    protected EcgData as;
    protected int[] at;
    protected int au;
    
    public b() {
        this.b = false;
        this.n = -1;
        this.s = -1;
        this.t = 10158336;
        this.u = 10158337;
        this.v = false;
        this.w = false;
        this.x = new ConcurrentLinkedQueue<Byte>();
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 1;
        this.E = 10;
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
        this.K = 1;
        this.L = 1;
        this.M = 1;
        this.N = 1;
        this.O = false;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 0;
        this.ag = "";
        this.ah = "";
        this.ai = 0;
        this.aj = null;
        this.al = false;
        this.am = 0;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = 1;
    }
    
    public void a() {
        this.s = -1;
        this.v = false;
        this.w = false;
        this.D = 1;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.E = 10;
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
        this.K = 1;
        this.L = 1;
        this.M = 1;
        this.N = 1;
        this.O = false;
        if (null != this.x) {
            this.x.clear();
        }
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 0;
        this.ag = "";
        this.ah = "";
        this.ai = 0;
        this.al = false;
    }
    
    public void a(final ConnectCallback connectCallback) {
        if (this.a != null) {
            this.a.a(connectCallback);
        }
    }
    
    public void b() {
        this.f();
        this.g();
        this.h();
        if (this.a != null) {
            this.a.a();
        }
    }
    
    public void a(final byte[] array) {
        if (this.a != null) {
            this.a.a(array);
        }
    }
    
    public abstract void a(final RealtimeCallback p0);
    
    public abstract void c();
    
    public abstract void b(final byte[] p0);
    
    public void a(final a a) {
        this.a = a;
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
    
    public boolean d() {
        return this.b;
    }
    
    public void e() {
    }
    
    public void f() {
        if (this.o != null) {
            this.o.cancel();
            this.o = null;
        }
    }
    
    public void g() {
        if (this.p != null) {
            this.p.cancel();
            this.p = null;
        }
    }
    
    public void h() {
        if (this.q != null) {
            this.q.cancel();
            this.q = null;
        }
    }
    
    public void i() {
    }
    
    protected void a(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final SystemParameter.DataType dataType) {
        if (null == dataType) {
            return;
        }
        switch (dataType.ordinal()) {
            case 1: {
                this.am = com.ideabus.contec_sdk.code.bean.a.d;
                break;
            }
            case 2: {
                this.am = com.ideabus.contec_sdk.code.bean.a.e;
                break;
            }
            case 3: {
                this.am = com.ideabus.contec_sdk.code.bean.a.f;
                break;
            }
            case 4: {
                this.am = com.ideabus.contec_sdk.code.bean.a.g;
                break;
            }
            case 5: {
                this.am = com.ideabus.contec_sdk.code.bean.a.h;
                break;
            }
        }
    }
    
    public abstract void a(final GetStorageModeCallback p0);
    
    public abstract void a(final SystemParameter.StorageMode p0, final StorageModeCallback p1);
    
    public abstract void a(final DeleteDataCallback p0);
    
    public void a(final CommunicateCallback referent) {
        if (null != referent) {
            this.g = new WeakReference<CommunicateCallback>(referent).get();
        }
        if (this.v) {
            return;
        }
        if (this.w) {
            return;
        }
        this.a();
        this.v = true;
        this.n = 3;
        this.s = 8454401;
        this.a((CommunicateFailCallback)this.g);
        this.a(com.ideabus.contec_sdk.code.b.a.b());
    }
    
    protected void j() {
        if (this.g != null) {
            this.g.onDataResultEmpty();
        }
    }
    
    protected void a(final ArrayList<SpO2PointData> list) {
        if (this.g != null) {
            this.g.onPointSpO2DataResult(list);
        }
    }
    
    protected void b(final ArrayList<DayStepsData> list) {
        if (this.g != null) {
            this.g.onDayStepsDataResult(list);
        }
    }
    
    protected void c(final ArrayList<FiveMinStepsData> list) {
        if (this.g != null) {
            this.g.onFiveMinStepsDataResult(list);
        }
    }
    
    protected void a(final PieceData pieceData) {
        if (this.g != null) {
            this.g.onEachPieceDataResult(pieceData);
        }
    }
    
    protected void a(final EcgData ecgData) {
        if (this.g != null) {
            this.g.onEachEcgDataResult(ecgData);
        }
    }
    
    protected void k() {
        if (this.g != null) {
            this.g.onDataResultEnd();
        }
    }
    
    public void a(final SystemParameter.DataStorageInfo an, final DataStorageInfoCallback referent) {
        if (this.v) {
            return;
        }
        this.an = an;
        if (referent != null) {
            this.j = new WeakReference<DataStorageInfoCallback>(referent).get();
        }
        this.n = 2;
        switch (an.ordinal()) {
            case 1: {
                this.a(com.ideabus.contec_sdk.code.b.a.a(0));
                this.s = 9437440;
                this.a(this.j);
                break;
            }
            case 2: {
                this.a(com.ideabus.contec_sdk.code.b.a.a(1));
                this.s = 9437441;
                this.a(this.j);
                break;
            }
            case 3: {
                this.a(com.ideabus.contec_sdk.code.b.a.a(2));
                this.s = 9437442;
                this.a(this.j);
                break;
            }
            case 4: {
                this.a(com.ideabus.contec_sdk.code.b.a.a(3));
                this.s = 9437443;
                this.a(this.j);
            }
            case 5: {
                this.a(com.ideabus.contec_sdk.code.b.a.a(4));
                this.s = 9437444;
                this.a(this.j);
                break;
            }
            case 6: {
                this.a(com.ideabus.contec_sdk.code.b.a.a(5));
                this.s = 9437445;
                this.a(this.j);
                break;
            }
            case 7: {
                this.a(com.ideabus.contec_sdk.code.b.a.a(6));
                this.s = 9437446;
                this.a(this.j);
                break;
            }
        }
    }
    
    protected void a(final CommunicateFailCallback communicateFailCallback) {
        this.f();
        if (this.o == null) {
            (this.o = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    if (null != communicateFailCallback) {
                        com.ideabus.contec_sdk.code.a.b.this.e();
                        communicateFailCallback.onFail(com.ideabus.contec_sdk.code.a.b.this.s);
                        com.ideabus.contec_sdk.code.a.b.this.a();
                        com.ideabus.contec_sdk.code.a.b.this.g = null;
                        com.ideabus.contec_sdk.code.a.b.this.i = null;
                        com.ideabus.contec_sdk.code.a.b.this.h = null;
                        com.ideabus.contec_sdk.code.a.b.this.k = null;
                        com.ideabus.contec_sdk.code.a.b.this.j = null;
                    }
                }
            }, 5000L);
        }
    }
    
    protected void b(final CommunicateFailCallback communicateFailCallback) {
        this.g();
        if (this.p == null) {
            (this.p = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    if (null != communicateFailCallback) {
                        com.ideabus.contec_sdk.code.a.b.this.e();
                        communicateFailCallback.onFail(com.ideabus.contec_sdk.code.a.b.this.t);
                        com.ideabus.contec_sdk.code.a.b.this.a();
                    }
                }
            }, 5000L);
        }
    }
    
    protected void c(final CommunicateFailCallback communicateFailCallback) {
        this.h();
        if (this.q == null) {
            (this.q = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    if (null != communicateFailCallback) {
                        com.ideabus.contec_sdk.code.a.b.this.e();
                        communicateFailCallback.onFail(com.ideabus.contec_sdk.code.a.b.this.u);
                        com.ideabus.contec_sdk.code.a.b.this.a();
                    }
                }
            }, 10000L);
        }
    }
    
    public abstract void a(final RealtimeSpO2Callback p0);
    
    public abstract void a(final int p0, final int p1, final SystemParameter.StepsSensitivity p2, final SetCalorieCallback p3);
    
    public abstract void a(final int p0, final SetWeightCallback p1);
    
    public abstract void a(final int p0, final SetHeightCallback p1);
    
    public abstract void a(final int p0, final int p1, final SetStepsTimeCallback p2);
}

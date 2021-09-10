// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.callback;

import com.ideabus.contec_sdk.code.bean.SystemParameter;

public interface DataStorageInfoCallback extends CommunicateFailCallback
{
    void onSuccess(final SystemParameter.DataStorageInfo p0, final int p1);
}

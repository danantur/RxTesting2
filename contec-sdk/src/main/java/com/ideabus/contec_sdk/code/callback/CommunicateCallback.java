// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.callback;

import com.ideabus.contec_sdk.code.bean.DayStepsData;
import com.ideabus.contec_sdk.code.bean.EcgData;
import com.ideabus.contec_sdk.code.bean.FiveMinStepsData;
import com.ideabus.contec_sdk.code.bean.PieceData;
import com.ideabus.contec_sdk.code.bean.SpO2PointData;

import java.util.ArrayList;

public interface CommunicateCallback extends CommunicateFailCallback
{
    void onPointSpO2DataResult(final ArrayList<SpO2PointData> p0);
    
    void onDayStepsDataResult(final ArrayList<DayStepsData> p0);
    
    void onFiveMinStepsDataResult(final ArrayList<FiveMinStepsData> p0);
    
    void onEachPieceDataResult(final PieceData p0);
    
    void onEachEcgDataResult(final EcgData p0);
    
    void onDataResultEmpty();
    
    void onDataResultEnd();
    
    void onDeleteSuccess();
    
    void onDeleteFail();
}

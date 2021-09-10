// 
// Decompiled by Procyon v0.5.36
// 

package com.ideabus.contec_sdk.code.bean;

import java.io.Serializable;

public class SystemParameter implements Serializable
{
    public enum StepsSensitivity
    {
        LOW, 
        MIDDLE, 
        HIGH;
    }
    
    public enum DataStorageInfo
    {
        POINTDATAINFO, 
        DAYSTEPSINFO, 
        DAYFIVEMINUTESSTEPSINFO, 
        ECGDATAINFO, 
        PULSEWAVEDATAINFO, 
        WITHSTORAGEINFO, 
        PIECESPO2DATAINFO;
    }
    
    public enum DataType
    {
        CODEDATA, 
        ORIGINALDATA, 
        DIFFERENCEDATA, 
        CONTINUEDATA, 
        POINTDATA;
    }
    
    public enum StorageMode
    {
        AUTOMATIC, 
        MANUAL;
    }
}

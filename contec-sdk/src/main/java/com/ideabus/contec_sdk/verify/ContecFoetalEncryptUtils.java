/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 */
package com.ideabus.contec_sdk.verify;

import android.text.TextUtils;

public class ContecFoetalEncryptUtils {
    private native int getDeviceKey(byte[] var1, byte[] var2, byte[] var3);

    private native int getAppKey(byte[] var1, byte[] var2, byte[] var3);

    public int a(byte[] arrby, byte[] arrby2, String string) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            return -3;
        }
        byte[] arrby3 = string.getBytes();
        if (arrby3.length > 7) {
            return -6;
        }
        byte[] arrby4 = new byte[7];
        if (arrby3.length == 7) {
            arrby4 = arrby3;
        } else if (arrby3.length < 7) {
            for (int i = 0; i < arrby3.length; ++i) {
                arrby4[i] = arrby3[i];
            }
        }
        return this.getAppKey(arrby, arrby2, arrby4);
    }

    public int b(byte[] arrby, byte[] arrby2, String string) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            return -3;
        }
        byte[] arrby3 = string.getBytes();
        if (arrby3.length > 7) {
            return -6;
        }
        byte[] arrby4 = new byte[7];
        if (arrby3.length == 7) {
            arrby4 = arrby3;
        } else if (arrby3.length < 7) {
            for (int i = 0; i < arrby3.length; ++i) {
                arrby4[i] = arrby3[i];
            }
        }
        return this.getDeviceKey(arrby, arrby2, arrby4);
    }

    static {
        System.loadLibrary("ContecFoetalEncrypt-1.0");
    }
}


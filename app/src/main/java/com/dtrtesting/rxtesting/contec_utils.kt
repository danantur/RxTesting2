package com.dtrtesting.rxtesting

import android.util.Log
import java.io.Serializable
import java.util.*

class contec_utils {

    companion object {

        val id2 = UUID.fromString("0000ff02-0000-1000-8000-00805f9b34fb")
        val id1 = UUID.fromString("0000ff01-0000-1000-8000-00805f9b34fb")

        val id1_1: ByteArray = byteArrayOf(-126, c(byteArrayOf(-126, 0)).toByte())
        val id1_2_1: ByteArray = byteArrayOf(-101, 0, c(byteArrayOf(-101, 0, 0)).toByte())
        val id1_2_2: ByteArray = byteArrayOf(-101, 1, c(byteArrayOf(-101, 1, 0)).toByte())
        val id1_ping: ByteArray = byteArrayOf(-102, 26)
        val id1_disconnect: ByteArray = byteArrayOf(-101, 127, c(byteArrayOf(-101, 127, 0)).toByte())

        private var y: Int = 0

        fun parse_spo_data(d: ByteArray): Serializable? {
            var read_str = ""
            for (r in d.indices) {
                if (r < 7)
                    read_str += d[r].toString() + " "
                else
                    break
            }
            if (!read_str.startsWith("-21 0"))
                Log.e("read_bytes", read_str)
            when (d[0]) {
                (-21).toByte() -> {
                    if (d[1] == 0.toByte()) {
                        return parse_wave(d)
                    }
                    else if (d[1] == 1.toByte()) {
                        if (this.y < 11)
                            return parse_real1(d)
                        else
                            return parse_real2(d)
                    }
                    else {
                        if (d[1] != 127.toByte()) return null
                        throw SpoException("Связь прервана")
                    }
                }
                (-14).toByte() -> {
                    y = d[6].toInt() and 0x7F
                    return Ping()
                }
                else -> return null
            }
        }

        private fun parse_wave(arrby: ByteArray): Wave {
            var n = arrby[1].toInt() and 0xF
            if (n >= 8) {
                n = 8
            }
            val n2: Int = arrby[2].toInt() and 0x40 shr 6
            val n3 = arrby[3].toInt() and 0x7F
            val n4 = arrby[4].toInt() and 0xF
            val n5: Int = arrby[4].toInt() and 0x10 shr 4
            return Wave(n, n2, n3, n4, n5)
        }

        private fun parse_real1(arrby: ByteArray): Real {
            val n: Int = arrby[2].toInt() and 1 and 0xFF
            var n2: Int = (arrby[3].toInt() and 0x7F) + (arrby[2].toInt() shl 6 and 0x80)
            if (n2 > 254 || n2 <= 0) {
                n2 = -1
            }
            var n3: Int = arrby[4].toInt() and 0x7F
            if (n3 >= 100 || n3 <= 0) {
                n3 = -1
            }
            var n4: Int = (arrby[5].toInt() and 0xFF) + (arrby[6].toInt() and 0xFF shl 7)
            if (n4 > 220 || n4 <= 0) {
                n4 = -1
            }
            return Real(n, n2, n3, n4)
        }

        private fun parse_real2(arrby: ByteArray): Real {
            val n = arrby[2].toInt() and 0x1 and 0xFF
            var n2: Int = (arrby[3].toInt() and 0x7F) + (arrby[2].toInt() shl 6 and 0x80)
            if (n2 > 254 || n2 <= 0) {
                n2 = -1
            }
            var n3 = arrby[4].toInt() and 0x7F
            if (n3 >= 100 || n3 <= 0) {
                n3 = -1
            }
            var n4: Int = (arrby[5].toInt() and 0x7F) + (arrby[2].toInt() shl 5 and 0x80)
            if (n4 > 220 || n4 <= 0) {
                n4 = -1
            }
            return Real(n, n2, n3, n4)
        }

        private fun c(array: ByteArray): Short {
            var n = 0
            for (i in 0 until array.size - 1) {
                n = (n + (array[i].toInt() and 0xFF))
            }
            return (n and 0x7F).toShort()
        }

        class Wave(val signal: Int, val prSound: Int, val waveData: Int, val barData: Int, val fingerOut: Int) : Serializable
        class Real(val piError: Int, val pr: Int, val spo2: Int, val pi: Int) : Serializable
        class Ping : Serializable

        class SpoException(s: String) : RuntimeException(s)
    }
}
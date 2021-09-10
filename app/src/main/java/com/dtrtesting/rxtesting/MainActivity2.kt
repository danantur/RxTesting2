package com.dtrtesting.rxtesting

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.polidea.rxandroidble2.NotificationSetupMode
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.RxBleConnection
import com.polidea.rxandroidble2.RxBleDevice
import io.reactivex.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {

    companion object {
        val DEVICE_MAC: String = "DEVICE_MAC"
    }

    private lateinit var device: RxBleDevice

    private lateinit var rxBleClient: RxBleClient

    private var connectionSubscription: Disposable? = null
    private var connection_: RxBleConnection? = null
    private lateinit var stateSubscription: Disposable
    private var stateRefresher: Disposable? = null

    private var disconnecting = false

    private lateinit var load: ProgressBar
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rxBleClient = RxBleClient.create(this)

        if (intent.getStringExtra(DEVICE_MAC) == null) {
            Log.e("MainActivity2", "интент без MAC адреса")
            finish()
        }
        else
            device = rxBleClient.getBleDevice(intent.getStringExtra(DEVICE_MAC).toString())

        title = device.name

        load = findViewById(R.id.progressBar)
        text1 = findViewById(R.id.textView)
        text2 = findViewById(R.id.textView2)
        text3 = findViewById(R.id.textView3)
    }

    override fun onStart() {
        super.onStart()

        stateSubscription = get_state_sub()
        init_connection()
    }

    private fun init_connection() {
        connectionSubscription = get_conn_sub()
    }

    private fun get_conn_sub(): Disposable =
        device.establishConnection(false)
            .subscribe(
                { connection ->
                    connection_ = connection
                    connection.setupNotification(contec_utils.id2, NotificationSetupMode.QUICK_SETUP)
                        .flatMap {
                            set_realtime(connection)
                            runOnUiThread {
                                load.visibility = View.INVISIBLE
                                text1.visibility = View.VISIBLE
                                text2.visibility = View.VISIBLE
                            }
                            it }
                        .subscribe(
                            {
                                runOnUiThread {
                                    val data = contec_utils.parse_spo_data(it)
                                    if (data != null) {
                                        if (data is contec_utils.Companion.Real) {
                                            Log.e("debug", "${data.spo2}% ${data.pr} pm")
                                            if (data.spo2 != -1 && data.pr != -1) {
                                                text1.text = ("${data.spo2}%")
                                                text2.text = ("${data.pr} pm")
                                            }
                                        } else if (data is contec_utils.Companion.Wave) {
                                            if (data.fingerOut == 0) {
                                                text1.visibility = View.VISIBLE
                                                text2.visibility = View.VISIBLE
                                                text3.visibility = View.INVISIBLE
                                            } else {
                                                text1.visibility = View.INVISIBLE
                                                text2.visibility = View.INVISIBLE
                                                text3.visibility = View.VISIBLE
                                                text1.text = resources.getString(R.string.data_waiting)
                                                text2.text = resources.getString(R.string.data_waiting)
                                            }
                                        } else if (data is contec_utils.Companion.Ping)
                                            set_realtime(connection)
                                    }
                                }
                            },
                            {
                                if (it is contec_utils.Companion.SpoException)
                                    disconnect()
                                else
                                    it.printStackTrace()
                            }
                        )
                },
                { throwable ->
                    Log.e("establishConnection", throwable.toString())
                }
            )

    private fun get_state_sub(): Disposable =
        device.observeConnectionStateChanges()
            .subscribe(
                { state ->
                    Log.e("state", state.toString())
                },
                { throwable ->
                    Log.e("statethrowable", throwable.toString())
                }
            )

    @SuppressLint("CheckResult")
    private fun set_realtime(connection: RxBleConnection) {
        if (stateRefresher != null) stateRefresher!!.dispose()
        connection.writeCharacteristic(contec_utils.id1, contec_utils.id1_1)
            .subscribe( {
                connection.writeCharacteristic(contec_utils.id1, contec_utils.id1_2_1)
                    .subscribe { _ ->
                        io.reactivex.Observable.timer(1, TimeUnit.SECONDS)
                            .subscribe {
                                connection.writeCharacteristic(contec_utils.id1, contec_utils.id1_2_2)
                                    .subscribe({
                                        stateRefresher = io.reactivex.Observable.interval(5500, 4500, TimeUnit.MILLISECONDS)
                                            .subscribe {
                                                connection.writeCharacteristic(contec_utils.id1, contec_utils.id1_ping)
                                            }
                                    }, {
                                        it.printStackTrace()
                                    })
                            }
                    }
            }, {
                it.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    private fun disconnect() {
        if (connection_ != null) {
            disconnecting = true
            connection_!!.writeCharacteristic(contec_utils.id1, contec_utils.id1_disconnect)
                .subscribe( {
                    runOnUiThread {
                        finish()
                    }
                }, {
                    runOnUiThread {
                        finish()
                    }
                })
        }
        else
            finish()
    }

    private fun make_toast(msg: String) {
        runOnUiThread {
            Toast.makeText(
                applicationContext,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onBackPressed() {
        if (!disconnecting)
            disconnect()
    }

    override fun finish() {
        stateSubscription.dispose()
        connectionSubscription?.dispose()
        stateRefresher?.dispose()
        super.finish()
    }

    override fun onDestroy() {
        if (!stateSubscription.isDisposed) stateSubscription.dispose()
        if (connectionSubscription != null) if (!connectionSubscription!!.isDisposed) connectionSubscription!!.dispose()
        if (stateRefresher != null) if (!stateRefresher!!.isDisposed) stateRefresher!!.dispose()
        super.onDestroy()
    }
}
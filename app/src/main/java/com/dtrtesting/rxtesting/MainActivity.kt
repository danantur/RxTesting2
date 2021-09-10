package com.dtrtesting.rxtesting

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.polidea.rxandroidble2.*
import com.polidea.rxandroidble2.exceptions.BleScanException
import com.polidea.rxandroidble2.internal.RxBleLog
import com.polidea.rxandroidble2.scan.ScanSettings
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

class MainActivity : AppCompatActivity() {

    private val DeviceList: ArrayList<RxBleDevice> = ArrayList()
    private val DeviceAdapter: DevicesAdapter = DevicesAdapter(DeviceList)

    private lateinit var recyclerview: RecyclerView
    private lateinit var btn: CircularProgressButton
    private lateinit var stp_btn: AppCompatButton

    private lateinit var rxBleClient: RxBleClient

    private lateinit var scanSubscription: Disposable
    private lateinit var stateSubscription: Disposable

    private var locationDenied: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxBleClient = RxBleClient.create(applicationContext)
        RxBleLog.updateLogOptions(LogOptions.Builder().setLogLevel(LogConstants.DEBUG).build())
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                Log.e("undelivered", e.message.toString())
            } else {
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler?.uncaughtException(thread, e)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        init_layout()
        stateSubscription = get_state_sub()
    }

    private fun init_layout() {

        recyclerview = findViewById(R.id.DeviceList)
        btn = findViewById(R.id.main_button)
        stp_btn = findViewById(R.id.stop_button)

        btn.setOnClickListener {
            val presize = DeviceList.size
            DeviceList.clear()
            DeviceAdapter.notifyItemRangeRemoved(0, presize)
            locationDenied = false
            init_bluetooth()
        }

        stp_btn.setOnClickListener {
            it.clearAnimation()
            it.isClickable = false
            it.animate()
                .alpha(0.0f)
                .start()
            scanSubscription.dispose()
            btn.revertAnimation()
        }

        DeviceAdapter.setOnItemClick(object : DevicesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                if (!scanSubscription.isDisposed) {
                    stp_btn.callOnClick()
                    scanSubscription.dispose()
                }
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                intent.putExtra(MainActivity2.DEVICE_MAC, DeviceList[position].macAddress)
                startActivity(intent)
            }
        })

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = DeviceAdapter
        recyclerview.itemAnimator = DefaultItemAnimator()

        val dividerItemDecoration = DividerItemDecoration(
            recyclerview.context,
            RecyclerView.VERTICAL
        )

        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.list_divider)!!)
        recyclerview.addItemDecoration(dividerItemDecoration)

    }

    private fun init_bluetooth() {
        scanSubscription = get_scan_sub()
        handle_scan_state(rxBleClient.state)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.e("debug", "$requestCode, $permissions, $grantResults")
        if (requestCode == 100)
            if (permissions[0] == Manifest.permission.ACCESS_COARSE_LOCATION)
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (scanSubscription.isDisposed)
                        init_bluetooth()
                }
                else
                    make_toast("Для работы приложения требуется разрешение на получение геоданных!")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    val bluetooth_callback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (scanSubscription.isDisposed)
                init_bluetooth()
        }
        else
            make_toast("Для работы приложения требуется включить Bluetooth!")
    }

    val location_callback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (scanSubscription.isDisposed)
            init_bluetooth()
    }

    private fun get_scan_sub(): Disposable =
        rxBleClient.scanBleDevices(
            ScanSettings.Builder()
                .build()
        )
            .subscribe(
                { scanResult ->
                    if (!DeviceList.contains(scanResult.bleDevice) && scanResult.bleDevice.name != null) {
                        DeviceList.add(scanResult.bleDevice)
                        DeviceAdapter.notifyItemInserted(DeviceList.size - 1)
                    }
                }
            ) { throwable ->
                Log.e("throwablescanBle", throwable.toString())
                if (throwable is BleScanException) {
                    make_toast("Ошибка сканирования, "
                            + when (throwable.reason) {
                        BleScanException.BLUETOOTH_CANNOT_START -> "ошибка при запуске службы Bluetooth"
                        BleScanException.SCAN_FAILED_ALREADY_STARTED -> "сканирование уже запущено"
                        BleScanException.SCAN_FAILED_APPLICATION_REGISTRATION_FAILED -> "ошибка регистрации приложения"
                        BleScanException.SCAN_FAILED_FEATURE_UNSUPPORTED -> "ваше устройство не поддерживает сканирование через Bluetooth"
                        BleScanException.SCAN_FAILED_INTERNAL_ERROR -> "Внутренняя ошибка сканирования"
                        BleScanException.SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES -> "Нехватка ресурсов оборудования"
                        BleScanException.UNDOCUMENTED_SCAN_THROTTLE -> "UNDOCUMENTED_SCAN_THROTTLE"
                        BleScanException.UNKNOWN_ERROR_CODE -> "Неизвестная ошибка"
                        else -> return@subscribe
                    }
                    )
                }
            }

    private fun get_state_sub(): Disposable =
        rxBleClient.observeStateChanges()
            .subscribe { state: RxBleClient.State -> handle_scan_state(state)}

    private fun handle_scan_state(state: RxBleClient.State) {
        Log.e("state", state.toString())
        runOnUiThread {
            when (state) {
                RxBleClient.State.READY -> {
                    btn.startAnimation()
                    stp_btn.clearAnimation()
                    stp_btn.animate()
                        .alpha(1.0f)
                        .withEndAction { stp_btn.isClickable = true }
                        .start()
                    Handler(Looper.getMainLooper()).postDelayed({
                        stp_btn.callOnClick()
                    }, 4000)
                }
                RxBleClient.State.BLUETOOTH_NOT_AVAILABLE -> make_toast("Ошибка, Bluetooth не доступен на вашем устройстве")
                RxBleClient.State.LOCATION_PERMISSION_NOT_GRANTED ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        100
                    )
                RxBleClient.State.BLUETOOTH_NOT_ENABLED ->
                    bluetooth_callback.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
                RxBleClient.State.LOCATION_SERVICES_NOT_ENABLED -> {
                    if (locationDenied) {
                        make_toast("Для работы приложения требуется включить передачу геоданных!")
                        return@runOnUiThread
                    }
                    locationDenied = true
                    location_callback.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            }
        }
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
}
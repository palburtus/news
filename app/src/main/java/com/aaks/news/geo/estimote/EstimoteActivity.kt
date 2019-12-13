package com.aaks.news.geo.estimote

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aaks.news.BuildConfig
import com.aaks.news.R
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.estimote.proximity_sdk.api.ProximityZoneBuilder
import com.estimote.scanning_plugin.api.EstimoteBluetoothScannerFactory
import java.util.*
import kotlin.concurrent.thread

class EstimoteActivity : AppCompatActivity(){

    private val TAG = EstimoteActivity::class.java.simpleName

    lateinit var observationHandler: ProximityObserver.Handler
    lateinit var locationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimote)

        //Estimote provided helper class that provides runtime configuration checks

        RequirementsWizardFactory.createEstimoteRequirementsWizard().fulfillRequirements(this, {
            initializeEstimote()
        }, {

        }, {
            Log.d(TAG, "Required permission not found: $it")
        })

        locationTextView = findViewById(R.id.locationTextView)

    }

    private fun initializeEstimote(){

        val bluetoothScanner = EstimoteBluetoothScannerFactory(applicationContext).getSimpleScanner()
        val telemetryFullScanHandler =
            bluetoothScanner
                .estimoteTelemetryFullScan()
                .withOnPacketFoundAction {
                    Log.d(TAG, "Got Full Telemetry packet: $it")
                }
                .withOnScanErrorAction {
                    Log.e(TAG, "Full Telemetry scan failed: $it")
                }
                .start()

        thread(start = true) {
            runOnUiThread {
                Toast.makeText(applicationContext, "Initializing...", Toast.LENGTH_SHORT).show()
            }
        }

        var estimoteCloudCredentials = EstimoteCloudCredentials(BuildConfig.ESTIMOTE_APP_ID, BuildConfig.ESTIMORE_APP_TOKEN)
        var proximityObserver = ProximityObserverBuilder(applicationContext, estimoteCloudCredentials)
            .withBalancedPowerMode()
            .onError {
                Log.e(TAG, it.message)
                thread(start = true) {
                    runOnUiThread {
                        locationTextView.text = "Estimote Initialization Failed"
                    }
                }
            }
            .build()

        val conferenceRoomOneZone = ProximityZoneBuilder()
            .forTag("office")
            .inNearRange()
            .onEnter { proximityZoneContext ->
                thread(start = true) {
                    runOnUiThread {
                        val roomName = proximityZoneContext.attachments["RoomName"]
                        Toast.makeText(applicationContext, "You have reached $roomName", Toast.LENGTH_SHORT).show()
                        locationTextView.text = "Current Location: $roomName"
                    }
                }
                val roomName = proximityZoneContext.attachments["RoomName"]
                Log.e("EstimoteActivity", "You have reached $roomName")
            }
            .onExit {proximityZoneContext ->
                thread(start = true) {
                    runOnUiThread {
                        val roomName = proximityZoneContext.attachments["RoomName"]
                        Toast.makeText(applicationContext, "You are leaving $roomName", Toast.LENGTH_LONG).show()
                        locationTextView.text = "Last Location: $roomName"

                    }
                }
                val roomName = proximityZoneContext.attachments["RoomName"]
                Log.e("EstimoteActivity", "You have left $roomName")
            }
            .onContextChange {
                Log.i(TAG, "Proximity Zone Context has changed")
            }
            .build()

        observationHandler = proximityObserver.startObserving(conferenceRoomOneZone)
    }

    override fun onDestroy() {
        observationHandler.stop()
        super.onDestroy()
    }

}
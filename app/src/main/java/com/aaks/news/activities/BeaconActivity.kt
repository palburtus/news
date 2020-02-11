package com.aaks.news.activities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aaks.news.R
import com.aaks.news.beacons.IZoneManager
import com.aaks.news.beacons.estimote.EstimoteZoneManager
import com.aaks.news.geo.IProxmityListner
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import kotlin.concurrent.thread

class BeaconActivity : AppCompatActivity(), IProxmityListner{

    private val TAG = BeaconActivity::class.java.simpleName

    lateinit var zoneManager: IZoneManager

    lateinit var locationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimote)

        //Estimote provided helper class that provides runtime configuration checks
        RequirementsWizardFactory.createEstimoteRequirementsWizard().fulfillRequirements(this, {
            this.zoneManager = EstimoteZoneManager(this, this)
        }, {
            Log.d(TAG, "requirements wizard worked")
        }, {
            Log.d(TAG, "Required permission not found: $it")
        })

        locationTextView = findViewById(R.id.locationTextView)

        zoneManager.createZone()

    }

    override fun onZoneEntered(zoneName: String, attachments: Map<String, String>) {
        thread(start = true) {
            runOnUiThread {

                val roomName = attachments["RoomName"]
                Toast.makeText(applicationContext, "You have entered $roomName", Toast.LENGTH_LONG).show()
                locationTextView.text = "Current Location: $zoneName"
            }
        }
    }

    override fun onZoneExited(zoneName: String, attachments: Map<String, String>) {
        thread(start = true) {
            runOnUiThread {

                val roomName = attachments["RoomName"]
                Toast.makeText(applicationContext, "You are leaving $roomName", Toast.LENGTH_LONG).show()
                locationTextView.text = "Last Location: $zoneName"
            }
        }
    }

    override fun onDestroy() {
        zoneManager.dispose()
        super.onDestroy()
    }

}
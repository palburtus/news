package com.aaks.news.beacons.estimote

import android.content.Context
import android.util.Log
import com.aaks.news.BuildConfig
import com.aaks.news.beacons.IZoneManager
import com.aaks.news.geo.IProxmityListner
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.estimote.proximity_sdk.api.ProximityZoneBuilder


class EstimoteZoneManager(val context: Context, val proximityListener: IProxmityListner) : IZoneManager {


    private val TAG = EstimoteZoneManager::class.java.simpleName

    private var estimoteCloudCredentials: EstimoteCloudCredentials
    private var observationHandlers: MutableList<ProximityObserver.Handler>
    private var proximityObserver: ProximityObserver

    init {
        estimoteCloudCredentials = EstimoteCloudCredentials(BuildConfig.ESTIMOTE_APP_ID, BuildConfig.ESTIMORE_APP_TOKEN)
        observationHandlers = mutableListOf()
        proximityObserver = ProximityObserverBuilder(this.context, estimoteCloudCredentials)
            .withBalancedPowerMode()
            .onError {
                Log.e(TAG, it.message)
            }
            .build()
    }


    override fun createZone(zoneName: String, tag: String, subscribedAttachments: Array<String>) {

        val conferenceRoomOneZone = ProximityZoneBuilder()
            .forTag("office")
            .inNearRange()
            .onEnter { proximityZoneContext ->

                var attachments = mutableMapOf<String, String>()

                for (a in subscribedAttachments){
                    attachments.put(a, proximityZoneContext.attachments[a]!!)
                }

                this.proximityListener.onZoneEntered(zoneName, attachments)

                Log.i(TAG, "Entered Zone $zoneName")
            }
            .onExit {proximityZoneContext ->

                var attachments = mutableMapOf<String, String>()

                for (a in subscribedAttachments){
                    attachments.put(a, proximityZoneContext.attachments[a]!!)
                }

                this.proximityListener.onZoneExited(zoneName, attachments)
            }
            .onContextChange {
                Log.i(TAG, "Proximity Zone Context has changed")
            }
            .build()

            observationHandlers.add(proximityObserver.startObserving(conferenceRoomOneZone))

    }

    override fun dispose() {

        observationHandlers.forEach { it ->
            it.stop()
        }
    }
}
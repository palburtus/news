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


    override fun createZone() {

        val zone = ProximityZoneBuilder()
            .forTag("office")
            .inNearRange()
            .onEnter { proximityZoneContext ->

                proximityZoneContext.attachments["location"]?.let {
                    this.proximityListener.onZoneEntered(
                        it, proximityZoneContext.attachments)

                    Log.i(TAG, "Entered Zone ${proximityZoneContext.attachments["location"]}")
                }

            }
            .onExit {proximityZoneContext ->

                proximityZoneContext.attachments["location"]?.let {
                    this.proximityListener.onZoneExited(
                        it, proximityZoneContext.attachments)

                    Log.i(TAG, "Exited Zone ${proximityZoneContext.attachments["location"]}")
                }
            }
            .onContextChange {
                Log.i(TAG, "Proximity Zone Context has changed")
            }
            .build()

            observationHandlers.add(proximityObserver.startObserving(zone))

    }

    override fun dispose() {

        observationHandlers.forEach { it ->
            it.stop()
        }
    }
}
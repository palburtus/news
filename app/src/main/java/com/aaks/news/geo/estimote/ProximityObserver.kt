package com.aaks.news.geo.estimote

import com.aaks.news.BuildConfig
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials

class ProximityObserver {

    private lateinit var estimoteCloudCredentials: EstimoteCloudCredentials

    init {
        estimoteCloudCredentials = EstimoteCloudCredentials(BuildConfig.ESTIMOTE_APP_ID, BuildConfig.ESTIMORE_APP_TOKEN)
    }

}
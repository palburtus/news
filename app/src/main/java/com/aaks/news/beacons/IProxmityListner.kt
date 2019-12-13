package com.aaks.news.geo

interface IProxmityListner {

    fun onZoneEntered(zoneName: String, attachments: Map<String, String>)
    fun onZoneExited(zoneName: String, attachments: Map<String, String>)

}
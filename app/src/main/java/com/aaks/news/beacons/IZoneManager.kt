package com.aaks.news.beacons

interface IZoneManager{

    fun createZone(zoneName: String, tag: String, subscribedAttachments:Array<String>)
    fun dispose()
}
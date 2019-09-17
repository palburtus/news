package com.aaks.news.subscriptions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aaks.news.R
import com.android.billingclient.api.*

class SubscribeActivity : AppCompatActivity()  , PurchasesUpdatedListener {
    override fun onPurchasesUpdated(
        billingResult: BillingResult?,
        purchases: MutableList<Purchase>?
    ) {
        if (billingResult!!.responseCode == 0 && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        }
    }

    lateinit private var billingClient: BillingClient

    private fun handlePurchase(purchase: Purchase)
    {
        val token = purchase.purchaseToken
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe)

        subscribe()
    }

    fun subscribe() {

        billingClient = BillingClient.newBuilder(this.applicationContext).setListener(this).build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == 0) {
                    val skuList = ArrayList<String>()
                    skuList.add("testsub")
                    val params = SkuDetailsParams.newBuilder()
                    params.setSkusList(skuList).setType(BillingClient.SkuType.SUBS)
                    billingClient.querySkuDetailsAsync(params.build(), { billingResult, skuDetailsList ->

                        if (billingResult.responseCode == 0 && skuDetailsList != null) {
                            for (skuDetails in skuDetailsList) {
                                val sku = skuDetails.sku
                                val price = skuDetails.price
                                if ("testsub" == sku) {
                                    val flowParams = BillingFlowParams.newBuilder()
                                        .setSkuDetails(skuDetails)
                                        .build()
                                    val responseCode =
                                        billingClient.launchBillingFlow(this@SubscribeActivity, flowParams)
                                }
                            }
                        }

                    })
                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }
}

package com.aaks.news.subscriptions

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.aaks.news.R
import com.android.billingclient.api.*
import java.util.*
import android.util.Log

class SubscribeActivity : AppCompatActivity(), PurchasesUpdatedListener {

    lateinit private var textViewToken: TextView
    lateinit private var billingClient: BillingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe)

        textViewToken = findViewById(R.id.textViewToken);

        subscribe()
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult?,
        purchases: MutableList<Purchase>?) {

        if (billingResult!!.responseCode == 0 && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        }
    }
    
    private fun handlePurchase(purchase: Purchase) {

        val token = purchase.purchaseToken
        Log.d("Token!!!", token)
        textViewToken.text = token;
    }

    fun subscribe() {

        billingClient = BillingClient.newBuilder(this.applicationContext).enablePendingPurchases().setListener(this).build()
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

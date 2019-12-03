package com.aaks.news.activities

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aaks.news.BuildConfig
import com.aaks.news.aws.lambda.ISavedLambdaProxy
import com.aaks.news.aws.lambda.SavedResponse
import com.aaks.news.model.Article
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobile.config.AWSConfiguration
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory
import com.amazonaws.regions.Regions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class SavedActivity : AppCompatActivity() {

    private lateinit var cognitoCachingCredentialsProvider: CognitoCachingCredentialsProvider
    private lateinit var lambdaInvokerFactory: LambdaInvokerFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cognitoCachingCredentialsProvider = CognitoCachingCredentialsProvider(
            applicationContext,
            BuildConfig.COGNITO_IDENTITY_POOL,
            Regions.US_EAST_2)

        lambdaInvokerFactory = LambdaInvokerFactory.builder().context(applicationContext).region(Regions.US_EAST_2).credentialsProvider(cognitoCachingCredentialsProvider).build()

        val lambdaSavedInterface = lambdaInvokerFactory.build(ISavedLambdaProxy::class.java)

        val article = Article(1, "https://www.alburt.us/article1", "Some Title", "Longer description")

        thread(start = true) {
            // Invoke "echo" method.  In case it fails, it will throw an exception
            try {
                val response: String = lambdaSavedInterface.AndroidBackendLambdaFunction(article)
                runOnUiThread {
                    Toast.makeText(applicationContext, response, Toast.LENGTH_LONG).show()
                }
            } catch (ex: LambdaFunctionException) {
                Log.e("SavedActivity", "Lambda execution failed")
            }
        }


    }


}

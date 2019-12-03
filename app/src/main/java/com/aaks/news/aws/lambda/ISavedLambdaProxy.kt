package com.aaks.news.aws.lambda

import com.aaks.news.model.Article
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction

interface ISavedLambdaProxy {

    @LambdaFunction
    fun AndroidBackendLambdaFunction(article: Article): String
}
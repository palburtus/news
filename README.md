# News Feed Style App for Implementing Android Features 

## Compiling this App
> *Note you must add some files in order to compile this application*

### AWS Properties
This app requires adding a file named `aws.properties` to the root folder in order to compile. This file contains private keys for using the AWS features in this app and this file should not be checked in.  You can however, use this app without AWS credentials by including a default `aws.properties` file.  Below is an example of a default version of that file that you can add to build the code.

```javascript
COGNITO_IDENTITY_POOL=""
```

### Estimote Properties
This app requires adding a file named `estimote.properties` to the root folder in order to compile.  This file contains private keys for using the Estimote SDK and should not be checked in.  You can however, use this app without Estimote credentials by including a default `estimote.properties` file.  Below is an example of a default version of that file that you can add to build the code. 

```javascript
ESTIMOTE_APP_ID="hudson-st-office-demo-maa"
ESTIMORE_APP_TOKEN="f690ade823f01384b44f36b4f435f5f6"
```

## MVVM and Databinding 
To demonstrate configuring MVVM and Databinding we have created an activity named `ArticleActivity` and a corresponding layout file `activity_article.xml` that will natively render a news article.We have also created a the class `ArticleViewModel` that will be used for the viewmodel of the `ArticleActivity` and `activity_article.xml` for databinding.

### Gradle Setup
First we are going to have to add support for Java 8 and configure Kotlin to target that JVM version. We are also going to have to add support for databinding. 

In you `app.gradle` add the following.
```groovy
android {
    ****
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    dataBinding {
      enabled = true
    }
    ****
}
```

## MVVM and ViewBinding
To demonstrate configuration of MVVM and ViewBinding we have create an activity named `ViewBindingActivity` and a corresponding layout xml file `activity_view_binding.xml` that will natively render our sample UI. We are going to use Espresso framework for testing the UI which uses ViewBinding. The tests for the both Activities are located under androidTest package in the class named `ViewBindingActivityTest`.
###### For more information on ViewBinding please refer to
 * https://developer.android.com/topic/libraries/view-binding
###### For more information about UI testing with Espresso please refer to
    1. https://developer.android.com/training/testing/espresso
    2. https://developer.android.com/training/testing/ui-testing/espresso-testing#kotlin
    3. https://developer.android.com/training/basics/fragments/testing
    4. https://www.vogella.com/tutorials/AndroidTestingEspresso/article.html
### Gradle Setup
First we are going to have to add support for Java 8 and configure Kotlin to target that JVM version. We are also going to have to add support for viewbinding.

In you `app.gradle` add the following.
```groovy
android {
    ****
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    viewBinding {
      enabled = true
    }
    ****
}
```
##Espresso setup
Add Espresso dependencies in your build.gradle (Module:app)
```groovy
 // Android runner and rules support
    androidtestImplementation 'com.android.support.test:runner:$latest_version_test_runner'
    androidtestImplementation 'com.android.support.test:rules:$latest_version_test_runner'
    // Espresso support
        androidtestImplementation('com.android.support.test.espresso:espresso-core:$latest_version_espresso', {
            exclude group: 'com.android.support', module: 'support-annotations'
        })

        // add this for intent mocking support
        androidtestImplementation 'com.android.support.test.espresso:espresso-intents:$latest_version_espresso'
```
######Note: Some of these dependencies may already be present in your gradle, so just add the ones missing

Next we are going to have to add some dependencies to use MVVM with kotlin.  In your `project.gradle` file add the following dependencies.
```groovy
implementation 'androidx.activity:activity-ktx:1.1.0'
implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
```

## Migrating to Room
In the gradle implementation of room, rooms version number must be equal to the 'androidx.archcore:core-runtime' version currently being used by the app.

## Subscriptions
Setting up a subscription and being able to verify it on a trusted server requires several steps. 

### Steps to Allow Subscriptions to be Verified by your Server
* Create API access https://developers.google.com/android-publisher/authorization
* Save the refresh token in a secure place and use it to get an access token 
* In the Android Play Console from the homepage go to Settings > API Access
* Under the _Linked Project_ section select your application and click the _Link_ button 
* Under the _Service Accounts_ Create a Service Account
* Edit the permission of the service account to link to your app
* From the Google Play Console select your App and from the left nav go to Store Presence -> In-App Production -> Subscriptions 
* Create a subscription (*NOTE* if you already have a subscription you must edit, update, and save in order for the above steps to take effect)
* Use the subscription Id, the base package name of your app, and the token generated by your app when subscribing to call Google's verification service from your server.  

## AWS Lambda Integration 

AWS Labmda functions have an SDK for Android.  

### Pre-Requisits
A very basic understand of AWS Lambda and other AWS products
Amazon provides a [more in depth tutorial](https://docs.aws.amazon.com/lambda/latest/dg/with-android-example.html) for settings up your AWS Lambda function for use in Android.  Below are the basic steps you will need to complete to get started.  

* Create an AWS lambda function
* Create an Execution Role that give the function access to other AWS resources 
* Create AWS Cognito identity pool for authentication
* In the root directory of your app create a file named aws.properties
** Add the following line to `aws.properties` and include your Cognito identity pool
```
COGNITO_IDENTITY_POOL="<your_cognito_identity_pool>"
```

## Estimote Beacon Integration
In order to integrate with Estimotes Beacon platform add the file `estimote.properties` to the root of your project and add the following lines to it.
```
ESTIMOTE_APP_ID="<your_estimote_app_id>"
ESTIMORE_APP_TOKEN="<your_estimote_app_token>"
```

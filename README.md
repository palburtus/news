# News Feed Style App for Implementing Android Features 

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

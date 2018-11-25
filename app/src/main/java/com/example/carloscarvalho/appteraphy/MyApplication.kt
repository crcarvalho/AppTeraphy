package com.example.carloscarvalho.appteraphy

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

/**
 * Created by Carlos Carvalho on 24/11/2018.
 */
class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}
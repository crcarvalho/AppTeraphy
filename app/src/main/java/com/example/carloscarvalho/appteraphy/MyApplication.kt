package com.example.carloscarvalho.appteraphy

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.stetho.Stetho

/**
 * Created by Carlos Carvalho on 24/11/2018.
 */
class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        //Classe de iniciliazação do Stetho, para depuração
        Stetho.initializeWithDefaults(this)

        //Classes de controle de login do Facebook
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}
package com.ideologer.zamishoapp

import com.google.firebase.FirebaseApp

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(applicationContext)
    }
    companion object {
        private val TAG = "ZamishoApplication"
        private var instance: Application? = null


        fun getInstance(): Application {
            return instance!!
        }
    }
    fun getInstance(): Application {
        return instance!!
    }
}

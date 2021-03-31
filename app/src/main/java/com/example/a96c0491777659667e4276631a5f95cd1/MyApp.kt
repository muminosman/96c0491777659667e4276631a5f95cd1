package com.example.a96c0491777659667e4276631a5f95cd1

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import android.view.ContextThemeWrapper
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.UzayAraci
import java.util.*

class MyApp : Application() {
    companion object {
        private var mInstance: MyApp? = null
        private var appContext: Context? = null
        private var myUzayAraci: UzayAraci? = null
        fun getMyUzayAraci(): UzayAraci? = myUzayAraci
        fun setMyUzayAraci(_myUzayAraci: UzayAraci) {
            myUzayAraci = _myUzayAraci
        }
//        private var roomDB: AppDatabase? = null

//        fun setDatabase(appContext: Context) {
//            roomDB = Room.databaseBuilder(
//                appContext,
//                AppDatabase::class.java, "istasyonlar"
//            ).build()
//        }

//        fun getDatabase() = roomDB

        fun getAppContext(): Context? = appContext


        @Synchronized
        fun getInstance(): MyApp? {
            return mInstance
        }

    }

    override fun onCreate() {
        super.onCreate()
        // init
//        SharedPref.init(this)

        appContext = applicationContext
//        setDatabase(applicationContext)
        mInstance = this
//        initializationRevenueCat(this, data.user.userId)

    }
}
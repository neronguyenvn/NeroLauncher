package com.neronguyenvn.nerolauncher.app

import android.app.Application
import com.neronguyenvn.nerolauncher.core.data.broadcast.AppChangeBroadcastReceiver
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NeroLauncherAndroidApp : Application() {

    @Inject
    lateinit var appChangeBroadcastReceiver: AppChangeBroadcastReceiver

    override fun onCreate() {
        super.onCreate()
        appChangeBroadcastReceiver.register(this)
    }
}

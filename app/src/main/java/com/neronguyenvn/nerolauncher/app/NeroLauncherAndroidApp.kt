package com.neronguyenvn.nerolauncher.app

import android.app.Application
import com.neronguyenvn.nerolauncher.core.common.coroutine.di.CoroutineModule
import com.neronguyenvn.nerolauncher.core.data.broadcast.AppChangeBroadcastReceiver
import com.neronguyenvn.nerolauncher.core.database.di.DatabaseModule
import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class NeroLauncherAndroidApp : Application() {

    private val appChangeBroadcastReceiver: AppChangeBroadcastReceiver by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NeroLauncherAndroidApp)
            analytics()
            modules(
                CoroutineModule().module,
                DatabaseModule().module,
                defaultModule
            )
        }

        appChangeBroadcastReceiver.register(this)
    }
}

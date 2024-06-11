package com.example.customlauncher.core.data.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.example.customlauncher.core.common.coroutine.di.ApplicationScope
import com.example.customlauncher.core.data.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppChangeBroadcastReceiver @Inject constructor(
    private val applicationRepository: AppRepository,

    @ApplicationScope
    private val applicationScope: CoroutineScope
) {
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            requestApplicationListUpdate()
        }
    }

    fun register(context: Context) = context.registerReceiver(
        receiver,
        IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_REPLACED)
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addAction(Intent.ACTION_MY_PACKAGE_REPLACED)
            addAction(Intent.ACTION_PACKAGE_CHANGED)
            addDataScheme("package")
        }
    )

    private fun requestApplicationListUpdate() {
        applicationScope.launch {
            applicationRepository.refreshApps()
        }
    }
}
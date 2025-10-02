package com.neronguyenvn.nerolauncher.core.data.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.neronguyenvn.nerolauncher.core.common.coroutine.di.AppScope
import com.neronguyenvn.nerolauncher.core.data.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class AppChangeBroadcastReceiver(
    private val applicationRepository: AppRepository,
    @AppScope private val appScope: CoroutineScope
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
        appScope.launch {
            applicationRepository.refreshApps()
        }
    }
}

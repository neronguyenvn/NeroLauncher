package com.neronguyenvn.nerolauncher.core.common.coroutine.di

import com.neronguyenvn.nerolauncher.core.common.coroutine.NlDispatchers
import com.neronguyenvn.nerolauncher.core.common.coroutine.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

annotation class AppScope

@Module
class CoroutineModule {

    @Factory
    @Dispatcher(NlDispatchers.IO)
    fun providesIODispatcher() = Dispatchers.IO

    @Factory
    @Dispatcher(NlDispatchers.Default)
    fun providesDefaultDispatcher() = Dispatchers.Default

    @Singleton
    @AppScope
    fun providesCoroutineScope(
        @Dispatcher(NlDispatchers.Default) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}
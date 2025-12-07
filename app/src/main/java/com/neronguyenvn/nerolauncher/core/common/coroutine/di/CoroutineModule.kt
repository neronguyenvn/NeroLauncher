package com.neronguyenvn.nerolauncher.core.common.coroutine.di

import com.neronguyenvn.nerolauncher.core.common.coroutine.ClDispatcher.Default
import com.neronguyenvn.nerolauncher.core.common.coroutine.ClDispatcher.IO
import com.neronguyenvn.nerolauncher.core.common.coroutine.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Singleton

annotation class AppScope

@Module
class CoroutineModule {

    @Factory
    @Dispatcher(IO)
    fun providesIODispatcher() = Dispatchers.IO

    @Factory
    @Dispatcher(Default)
    fun providesDefaultDispatcher() = Dispatchers.Default

    @Singleton
    @AppScope
    fun providesCoroutineScope(
        @Dispatcher(Default) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}

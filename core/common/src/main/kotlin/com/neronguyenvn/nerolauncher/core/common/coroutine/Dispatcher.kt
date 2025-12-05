package com.neronguyenvn.nerolauncher.core.common.coroutine

annotation class Dispatcher(val dispatcher: NlDispatchers)

enum class NlDispatchers { Default, IO }
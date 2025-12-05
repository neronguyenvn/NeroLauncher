package com.neronguyenvn.nerolauncher.core.common.coroutine

annotation class Dispatcher(val dispatcher: ClDispatcher)

enum class ClDispatcher { Default, IO }
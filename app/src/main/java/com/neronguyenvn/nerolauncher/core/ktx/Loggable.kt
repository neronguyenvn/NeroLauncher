package com.neronguyenvn.nerolauncher.core.ktx

import io.kotzilla.sdk.KotzillaSDK


interface Loggable {
    val logTag: String get() = this::class.simpleName ?: "App"

    private fun logPrefix(): String {
        val instance = this.hashCode().toString(16)
        val thread = Thread.currentThread().name
        return "[$logTag#$instance@$thread]"
    }

    fun log(message: String) {
        KotzillaSDK.log("${logPrefix()} $message")
    }

    fun logError(message: String, throwable: Throwable) {
        KotzillaSDK.logError("${logPrefix()} $message", throwable)
    }
}

inline fun <T> Loggable.logged(
    functionName: String,
    block: () -> T
): T {
    log("→ Enter $functionName()")
    return try {
        val result = block()
        log("✓ Exit $functionName()")
        result
    } catch (e: Throwable) {
        logError("✗ Error in $functionName()", e)
        throw e
    }
}

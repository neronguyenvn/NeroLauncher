package com.neronguyenvn.nerolauncher.core.ktx

import android.content.pm.PackageManager

fun PackageManager.getAppIconOrNull(packageName: String) =
    try {
        getApplicationIcon(packageName)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }

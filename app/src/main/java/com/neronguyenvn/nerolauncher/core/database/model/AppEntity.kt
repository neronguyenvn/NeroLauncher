package com.neronguyenvn.nerolauncher.core.database.model

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.neronguyenvn.nerolauncher.core.designsystem.util.asBitmap
import com.neronguyenvn.nerolauncher.core.ktx.getAppIconOrNull
import com.neronguyenvn.nerolauncher.core.model.App

@Entity(tableName = "UserApp")
data class AppEntity(
    val name: String,
    val version: String,
    val index: Int,
    val page: Int,

    @PrimaryKey
    val packageName: String
)

fun AppEntity.asExternalModel(packageManager: PackageManager): App? {
    val icon = packageManager
        .getAppIconOrNull(packageName)
        ?.asBitmap() ?: return null

    return App(
        name = name,
        icon = icon,
        packageName = packageName,
        version = version,
        canUninstall = canUninstall(packageManager),
        index = index
    )
}

fun AppEntity.isInstalledAndUpToDate(dbApps: Map<String, AppEntity>): Boolean {
    val installed = dbApps[packageName] ?: return false
    return installed.version == version
}

fun AppEntity.canUninstall(packageManager: PackageManager): Boolean {
    return try {
        packageManager.getApplicationInfo(packageName, 0)
            .flags and ApplicationInfo.FLAG_SYSTEM == 0
    } catch (e: NameNotFoundException) {
        false
    }
}

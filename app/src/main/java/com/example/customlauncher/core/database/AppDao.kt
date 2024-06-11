package com.example.customlauncher.core.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.customlauncher.core.database.model.AppEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM UserApp")
    fun observeAll(): Flow<List<AppEntity>>

    @Query("SELECT * FROM UserApp")
    suspend fun getAll(): List<AppEntity>

    @Upsert
    suspend fun upsert(applicationEntity: AppEntity)

    @Query("SELECT * FROM UserApp WHERE packageName = :packageName")
    suspend fun getByPackageName(packageName: String): AppEntity?

    @Query("UPDATE UserApp SET name = :newName WHERE packageName = :packageName")
    suspend fun updateName(newName: String, packageName: String)

    @Query("UPDATE UserApp SET usageMillis = :usageTimeMillis WHERE packageName = :packageName")
    suspend fun updateUsageTime(usageTimeMillis: Long, packageName: String)

    @Query("UPDATE UserApp SET notificationCount = :notificationCount WHERE packageName = :packageName")
    suspend fun updateNotificationCount(notificationCount: Int, packageName: String)

    @Query("UPDATE UserApp SET notificationCount = 0")
    suspend fun unsetAllNotificationCount()

    @Query("Update UserApp SET `index` = :toIndex WHERE packageName = :packageName")
    suspend fun updateIndexByPackageName(toIndex: Int, packageName: String)

    @Query("UPDATE UserApp SET page = :toPage, `index` = :toIndex WHERE packageName = :packageName")
    suspend fun updatePageAndIndexByPackageName(toPage: Int, toIndex: Int, packageName: String)

    @Query("DELETE FROM UserApp WHERE packageName NOT IN (:packages)")
    suspend fun deleteUninstalled(packages: List<String>)
}

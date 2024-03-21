package com.example.customlauncher.core.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.customlauncher.core.database.model.CompanyAppEntity
import com.example.customlauncher.core.database.model.UserAppEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface CompanyAppDao {
    @Query("SELECT * FROM CompanyApp")
    fun getAllCompanyApp(): Flow<List<CompanyAppEntity>>
    @Upsert
    suspend fun upsert(companyAppEntity: CompanyAppEntity)
}
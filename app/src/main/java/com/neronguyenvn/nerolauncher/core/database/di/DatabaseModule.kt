package com.neronguyenvn.nerolauncher.core.database.di

import android.content.Context
import androidx.room.Room
import com.neronguyenvn.nerolauncher.core.database.room.RoomClDatabase
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
class DatabaseModule {

    @Singleton
    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        RoomClDatabase::class.java,
        "cl-database"
    ).build()

    @Factory
    fun providesAppDao(database: RoomClDatabase) = database.applicationDao()
}

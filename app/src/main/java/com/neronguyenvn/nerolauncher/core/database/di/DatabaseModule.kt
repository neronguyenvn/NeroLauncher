package com.neronguyenvn.nerolauncher.core.database.di

import android.content.Context
import androidx.room.Room
import com.neronguyenvn.nerolauncher.core.database.room.RoomClDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RoomClDatabase = Room.databaseBuilder(
        context,
        RoomClDatabase::class.java,
        "cl-database"
    ).build()

    @Provides
    fun providesAppDao(
        database: RoomClDatabase
    ) = database.applicationDao()
}
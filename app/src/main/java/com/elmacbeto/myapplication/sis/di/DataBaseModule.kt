package com.elmacbeto.myapplication.sis.di

import android.content.Context
import androidx.room.Room
import com.elmacbeto.myapplication.data.datasource.local.db.BuildDataBase
import com.elmacbeto.myapplication.sis.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BuildDataBase {
        return Room.databaseBuilder(context, BuildDataBase::class.java, Constants.DATABASE_NAME)
            .build()
    }
}
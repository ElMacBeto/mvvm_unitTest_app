package com.elmacbeto.myapplication.sis.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UtilModule {
    @Provides
    fun providesContext(@ApplicationContext context: Context): Context{
        return context
    }
}
package com.elmacbeto.myapplication.sis.di

import com.elmacbeto.myapplication.data.datasource.local.dao.FactsDAO
import com.elmacbeto.myapplication.data.datasource.local.db.BuildDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [DataBaseModule::class])
@InstallIn(SingletonComponent::class)
object ModuleDAOHilt {

    @Provides
    @Singleton
    fun provideFactsDAO(buildDataBase: BuildDataBase): FactsDAO {
        return buildDataBase.factsDAO()
    }
}
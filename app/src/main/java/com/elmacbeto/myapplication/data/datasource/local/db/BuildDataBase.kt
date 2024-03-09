package com.elmacbeto.myapplication.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elmacbeto.myapplication.data.datasource.local.dao.FactsDAO
import com.elmacbeto.myapplication.data.datasource.local.entity.FactsEntity

@Database(
    entities = [FactsEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BuildDataBase : RoomDatabase() {
    abstract fun factsDAO(): FactsDAO
}
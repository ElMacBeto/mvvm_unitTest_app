package com.elmacbeto.myapplication.domain

import com.elmacbeto.myapplication.data.datasource.local.dao.FactsDAO
import com.elmacbeto.myapplication.data.datasource.local.entity.FactsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDS @Inject constructor(private val factsDAO: FactsDAO) {
    fun insertEntity(factsEntity: FactsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            factsDAO.insertData(factsEntity)
        }
    }

    fun getDataLocal(limit: Int): List<FactsEntity> = factsDAO.getAllData(limit)

    fun getCount(): Int = factsDAO.getCount()

    fun getFavorite(): List<FactsEntity> = factsDAO.getFavorite(true)

    fun updateData(factsEntity: FactsEntity) =
        factsDAO.updateData(factsEntity)

    fun delete() = factsDAO.deleteTable()


}
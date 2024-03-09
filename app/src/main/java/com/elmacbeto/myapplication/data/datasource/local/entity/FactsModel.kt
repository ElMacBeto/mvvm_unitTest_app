package com.elmacbeto.myapplication.data.datasource.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elmacbeto.myapplication.sis.utils.Constants
import kotlinx.parcelize.Parcelize

data class FactsModel(
    val _id: String,
    val columns: String,
    val createdAt: Int,
    val dataset: String,
    val date_insert: String,
    val fact: String,
    val operations: String,
    val organization: String,
    val resource: String,
    val slug: String,
    val url: String
)
@Parcelize
@Entity(tableName = Constants.TABLE_FACTS_ENTITY)
data class FactsEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val id_api: String?,
    val columns: String?,
    val createdAt: Int?,
    val dataset: String?,
    val dateInsert: String?,
    val fact: String?,
    val operations: String?,
    val organization: String?,
    val resource: String?,
    val slug: String?,
    val url: String?,
    // TODO: isFavorite
    var isFavorite:Boolean = false
) : Parcelable
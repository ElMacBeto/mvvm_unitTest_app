package com.elmacbeto.myapplication.data.datasource.remote.response

import com.elmacbeto.myapplication.data.datasource.local.entity.FactsModel
import com.elmacbeto.myapplication.data.model.PaginationModel
import com.google.gson.annotations.SerializedName

data class NewFactsResponse(
    @SerializedName("pagination")
    var pagination: PaginationModel? = PaginationModel(),
    @SerializedName("results")
    var results: ArrayList<FactsModel> = arrayListOf()
)

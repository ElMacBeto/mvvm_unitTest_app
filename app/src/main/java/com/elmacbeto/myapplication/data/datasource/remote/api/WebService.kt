package com.elmacbeto.myapplication.data.datasource.remote.api

import com.elmacbeto.myapplication.data.datasource.remote.response.NewFactsResponse
import com.elmacbeto.myapplication.sis.utils.Constants.END_POINT_FACTS
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    @GET(END_POINT_FACTS)
    suspend fun getFacts(): Response<NewFactsResponse>

}
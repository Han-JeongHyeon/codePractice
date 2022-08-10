package com.example.myapplication

import android.database.Observable
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Url

interface WordApi {

    @GET("/api/v2/entries/en/{word}")
    fun getBestSeller(
        @Path("word") WordKey: String
    ): Call<List<Responsee>>

}
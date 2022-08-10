package com.example.myapplication

import retrofit2.*
import retrofit2.converter.gson.*

object RetrofitClass {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(WordApi::class.java)

}
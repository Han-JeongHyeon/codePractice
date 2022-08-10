package com.example.myapplication

data class Responsee(
    val meanings: List<Meanings>
)

data class Meanings(
    val definitions: List<Ddefinitions>
)

data class Ddefinitions (
    val definition:String
)

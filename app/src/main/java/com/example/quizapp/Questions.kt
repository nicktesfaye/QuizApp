package com.example.quizapp

data class Questions(
    val id: Int,
    val question: String,
    val date: String,
    var score:Int,
    var option1: String,
    var option2: String,
    var option3: String,
    var option4: String,
    var answer: Int
    )



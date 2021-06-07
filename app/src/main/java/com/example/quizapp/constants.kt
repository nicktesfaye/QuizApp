package com.example.quizapp

object constants{
    fun getQuestions(): ArrayList<Questions>{
        val questionslist =ArrayList<Questions>()
        val que =Questions(id = 1,question = "select the correct day of the week",
            score=0,
            date = "Error",
            option1 = "monday",
            option2 = "wednesday",
            option3 = "sunday",
            option4 = "saturday",
            answer = 4)
        questionslist.add(que)
        return questionslist
    }
}
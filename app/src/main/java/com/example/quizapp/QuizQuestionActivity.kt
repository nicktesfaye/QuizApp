package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionBinding
import java.util.*
import kotlin.collections.ArrayList

val questionslist = constants.getQuestions()
var selected:Int=0
var year:Int=0
var month:Int=0
var dayOfYear:Int=0
var answer:Int=0
var optionsList= mutableListOf("none")
var score=0

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener{
    private var date: Calendar = Calendar.getInstance()
    private lateinit var binding: ActivityQuizQuestionBinding
    private val question=questionslist[0]
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityQuizQuestionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rndDate()




        binding.optionOne.setOnClickListener(this)
        binding.optionTwo.setOnClickListener(this)
        binding.optionThree.setOnClickListener(this)
        binding.optionFour.setOnClickListener(this)
        binding.submitButton.setOnClickListener(this)

    }


    private fun default()
    {
        val options = ArrayList<TextView>()
        options.add(0,binding.optionOne)
        options.add(1,binding.optionTwo)
        options.add(2,binding.optionThree)
        options.add(3,binding.optionFour)

        for(option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background= ContextCompat.getDrawable(this,R.drawable.default_border)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {

        when(v?.id){
            R.id.optionOne ->{
                selectedOp(binding.optionOne, 1)
            }

            R.id.optionTwo ->{
                selectedOp(binding.optionTwo, 2)
            }

            R.id.optionThree ->{
                selectedOp(binding.optionThree, 3)
            }

            R.id.optionFour ->{
                selectedOp(binding.optionFour, 4)
            }

            R.id.submitButton ->{

                if(binding.submitButton.text == "go to next question")
                {binding.submitButton.text="Submit"
                    selected=0
                    default()
                    rndDate()
                }

                else if (binding.submitButton.text == "view score")
                {
                    val intent = Intent(this,ScoreActivity::class.java)
                    startActivity(intent)
                    finish()
                    questionslist[0].score= score
                    Log.v("my tag", score.toString())
                }

                else{
                if(selected==0)
                {
                    Toast.makeText(this, "please select an option", Toast.LENGTH_SHORT).show()
                }

                else
                {
                    red()
                    green(questionslist[0].answer)


                }


            }}

        }
    }

    private fun selectedOp(tv:TextView,selectedOptionNumber:Int){
        if(binding.submitButton.text == "Submit")
        {default()
        selected=selectedOptionNumber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background= ContextCompat.getDrawable(this,R.drawable.selected_border)}
    }


    private fun wrongOp(tv: TextView)
    {
        default()
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.background= ContextCompat.getDrawable(this,R.drawable.wrong_answer)
    }

    private fun rightOp(tv: TextView)
    {
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.background= ContextCompat.getDrawable(this,R.drawable.correct_answer)
    }

    private fun red(){
        if(selected!= questionslist[0].answer)
        {
            if(selected==1)
                wrongOp(binding.optionOne)

            if(selected==2)
                wrongOp(binding.optionTwo)

            if(selected==3)
                wrongOp(binding.optionThree)

            if(selected==4)
                wrongOp(binding.optionFour)
            binding.submitButton.text="view score"
        }

        else
        {
            score+=10
            binding.submitButton.text="go to next question"
        }

    }


    private fun green(index:Int)
    {

        if(index==1)
            rightOp(binding.optionOne)

        if(index==2)
            rightOp(binding.optionTwo)

        if(index==3)
            rightOp(binding.optionThree)

        if(index==4)
            rightOp(binding.optionFour)

    }

    @SuppressLint("SetTextI18n")
    private fun rndDate()
    {
        year =(1900..2100).random()
        month =(1..12).random()
        dayOfYear=(1..date.getActualMaximum(Calendar.DAY_OF_MONTH)).random()
        date.set(year+0, month-1, dayOfYear+0)
        binding.date.text= "$dayOfYear-$month-$year"
        answer=date.get(Calendar.DAY_OF_WEEK)
        Log.v("my tag", date.get(Calendar.DAY_OF_WEEK).toString())
        answer--




        optionsList= randOptions()
        question.option1=optionsList[0]
        question.option2=optionsList[1]
        question.option3=optionsList[2]
        question.option4=optionsList[3]

        binding.optionOne.text=question.option1
        binding.optionTwo.text=question.option2
        binding.optionThree.text=question.option3
        binding.optionFour.text=question.option4
    }

    private fun randOptions(): MutableList<String> {
        val strng = arrayListOf("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
        val ans = strng[answer]
        val substring= mutableListOf(strng[answer])
        strng.removeAt(answer)

        for (i in 1..3)
        {
            val x= (0 until strng.size-1).random()
            substring.add(i,strng[x])
            strng.removeAt(x)

        }

        substring.shuffle()




        when (ans) {
            substring[0] -> {
                questionslist[0].answer=1
                Log.v("my tag1","yes")
            }
            substring[1] -> {
                questionslist[0].answer=2
                Log.v("my tag1","yes")
            }
            substring[2] -> {
                questionslist[0].answer=3
                Log.v("my tag1","yes")
            }
            substring[3] -> {
                questionslist[0].answer=4
                Log.v("my tag1","yes")
            }
        }


        return substring
    }


}
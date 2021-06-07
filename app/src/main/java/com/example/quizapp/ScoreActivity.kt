package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.quizapp.databinding.ActivityQuizQuestionBinding
import com.example.quizapp.databinding.ActivityScoreBinding



class ScoreActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var c=questionslist[0].score.toString()
        binding.score.text= "Score: $c"
        binding.tryBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


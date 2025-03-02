package com.inster.mathGame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity()
{
    lateinit var textt: TextView
    lateinit var playAgain: Button
    lateinit var exit: Button


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textt=findViewById(R.id.score1111)
        playAgain=findViewById(R.id.playyyyyy)
        exit=findViewById(R.id.exittttt)


        val score = intent.getIntExtra("score",0)
        textt.text="Your score is= "+score

        playAgain.setOnClickListener {
            val intent= Intent(this@ResultActivity,MainActivity::class.java)
            finish()
        }

        exit.setOnClickListener {
            finishAffinity()
        }




    }
}
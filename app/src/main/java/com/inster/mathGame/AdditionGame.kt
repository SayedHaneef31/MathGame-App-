package com.inster.mathGame
// Problems
//life =0 pr result show nhi ho raha hai
//check and next dono ek saath work kar rahe hai...usko roko...........DONE
//*********************************************************************************************************abort and timer ko work karaana simultaneuosly


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.TypedValue
import android.widget.Button

import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class AdditionGame : AppCompatActivity()
{
    lateinit var score: TextView
    lateinit var life: TextView
    lateinit var timer: TextView

    lateinit var question: TextView
    lateinit var answer: TextView

    lateinit var check: Button
    lateinit var next: Button
    lateinit var abort :Button

    lateinit var titleText: TextView

    private lateinit var countDownTimer: CountDownTimer

    var trueAns=0
    var userScore=0
    var userLife=3

    var timeKhatam=false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition_game)

        score= findViewById(R.id.scoreeee)
        life= findViewById(R.id.lifeeeee)
        timer= findViewById(R.id.timeeee)
        question= findViewById(R.id.questionnnnn)
        answer= findViewById(R.id.answerrrrr)
        abort= findViewById(R.id.aborttttt)
        check= findViewById(R.id.checkkkkkk)
        next= findViewById(R.id.nexttttttt)
        titleText=findViewById(R.id.titleeeeeeeeeeee)
        next.isEnabled = false
        check.isEnabled = true

        score.text="Score= 0"
        life.text="Life= 3"







        gameOn()
        check.setOnClickListener {
            val gotAns= answer.text.toString()

            if(gotAns=="" && timeKhatam==false)
            {
                Toast.makeText(applicationContext,"Input the answer first",Toast.LENGTH_SHORT).show()
                next.isEnabled = false
            }
            else
            {
                check.isEnabled = false
                pauseTimer()
                val userAns= gotAns.toInt()
                if (userAns==trueAns)
                {

                    userScore+=10
                    question.text="Correct!! Click Next ;-)"
                    score.text="Score= $userScore"
                    life.text="Life= $userLife"
                    next.isEnabled = true
                }
                else
                {
                    userLife--
                    question.text="Oops!! Wrong Answer"
                    life.text="Life= $userLife"
                    next.isEnabled = true

                }

            }

        }

        next.setOnClickListener {
            //gameOn()
            next.isEnabled = false
            check.isEnabled = true
//            if(timeKhatam==true && !(userLife<=0))
//            {
//                timeKhatam=false
//                gameOn()
//
//            }
            val gotAns= answer.text.toString()


            if(gotAns=="" && timeKhatam==false )
            {

                Toast.makeText(applicationContext,"Input the answer first",Toast.LENGTH_SHORT).show()
            }
            else
            {
                countDownTimer.cancel()

                answer.text=""

                if(userLife<=0)
                {
                    val intent= Intent(this@AdditionGame,ResultActivity::class.java)
                    intent.putExtra("score",userScore)
                    startActivity(intent)
                    finish()

                }
                else
                {
                    gameOn()

                }

            }


        }

        abort.setOnClickListener {
            showAlertDialog()
        }


    }


    fun showAlertDialog()
    {

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("Important!")
        alertDialog.setMessage("Do you really want to Quit?")
        alertDialog.setCancelable(false)
        alertDialog.setIcon(R.drawable.cancel___icon)
        alertDialog.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i-> dialogInterface.cancel() })
        alertDialog.setPositiveButton("Yes",DialogInterface.OnClickListener{dialogInterface, i->
            Toast.makeText(applicationContext,"OK Then!!!! ",Toast.LENGTH_LONG).show()
            finish()

        })
        alertDialog.create().show()
        //startTimer()
    }
    fun gameOn()
    {
        startTimer()

        val operation = intent.getStringExtra("button_clicked")

        var a: Int
        var b: Int

        when (operation) {
            "add" -> {
                titleText.text = "Addition Game"
                titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 38f)
                a = Random.nextInt(49, 999)
                b = Random.nextInt(29, 555)
                question.text = "$a + $b"
                trueAns = a + b
            }

            "sub" -> {
                titleText.text = "Substraction Game"
                titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 38f)
                a = Random.nextInt(53, 999)
                b = Random.nextInt(1, a) // Ensure b is less than a for a positive result
                question.text = "$a - $b"
                trueAns = a - b
            }

           "mul" -> {
               titleText.text = "Multiplication Game"
               titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 38f)
            a = Random.nextInt(11, 22)
            b = Random.nextInt(1, 15)
            question.text = "$a * $b"
            trueAns = a * b
        }
        else -> {
            // Default to addition if operation is not recognized
            a = Random.nextInt(1, 10)
            b = Random.nextInt(1, 10)
            question.text = "$a + $b"
            trueAns = a + b
        }

        }


    }

    fun startTimer()
    {
        countDownTimer= object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                timer.text="Time= ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                pauseTimer()


                timeKhatam=true;
                life.text="Life= ${--userLife}"
                question.text="Sorry! Time's Up"
                next.isEnabled = true
                check.isEnabled = false
                //resetTimer()
            }

        }.start()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
    }

//    private fun resetTimer() {
//        startTimer()
//    }
}



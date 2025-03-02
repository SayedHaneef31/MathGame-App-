package com.inster.mathGame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity()
{
    lateinit var add :Button
    lateinit var sub :Button
    lateinit var mul :Button
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add=findViewById(R.id.adddddd)
        sub=findViewById(R.id.subbbbb)
        mul=findViewById(R.id.mulllll)


        add.setOnClickListener {
            val intent= Intent(this@MainActivity,AdditionGame::class.java)
            intent.putExtra("button_clicked", "add")
            startActivity(intent)
        }
        sub.setOnClickListener {
            val intent= Intent(this@MainActivity,AdditionGame::class.java)
            intent.putExtra("button_clicked", "sub")
            startActivity(intent)

        }
        mul.setOnClickListener {
            val intent= Intent(this@MainActivity,AdditionGame::class.java)
            intent.putExtra("button_clicked", "mul")
            startActivity(intent)

        }

    }
}
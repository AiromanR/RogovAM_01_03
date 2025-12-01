package com.example.rogovam_01_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var pass : EditText
    private lateinit var Log : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pass = findViewById(R.id.Pass)
        Log = findViewById(R.id.Log)
    }

    fun LogIn(view: View) {
        if(pass.text.toString()!=""&&Log.text.toString()!=""&&pass.text.length>=8) {
            val intent = Intent(this, ActivityApartmentCostCalculation::class.java)
            startActivity(intent)
        }
        else{
            Snackbar.make(findViewById(android.R.id.content),"Введите правильный логин и пароль",Snackbar.LENGTH_SHORT).show()
        }
    }
}
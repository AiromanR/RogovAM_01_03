package com.example.rogovam_01_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView

class ActivityCount : AppCompatActivity() {
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        resultTextView = findViewById(R.id.Result)
        val result = loadCalculationResult()
        val resultInThousands = result / 1000

        resultTextView.text = "Результат\n${resultInThousands} тыс. руб."
    }

    private fun loadCalculationResult(): Int {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("calculation_result", 0)
    }

    fun LogIn(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
package com.example.rogovam_01_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class ActivityApartmentCostCalculation : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var metCount: EditText
    val COST_PER_SQUARE_METER = 78000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment_cost_calculation)

        spinner = findViewById(R.id.Spinner)
        metCount = findViewById(R.id.Met_count)

        val types = arrayOf(
            "1. 1-о комнатная квартира",
            "2. 2-х комнатная квартира",
            "3. 3-х комнатная квартира",
            "4. Студия"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            types
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun Back(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun Count(view: View) {
        val metersText = metCount.text.toString()
        if (metersText.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content),"Введите количество метров",Snackbar.LENGTH_SHORT).show()
            return
        }

        val meters = metersText.toInt()

        val selectedApartment = spinner.selectedItem.toString()

        val coefficient = when {
            selectedApartment.contains("1-о комнатная") -> 1.4
            selectedApartment.contains("2-х комнатная") -> 1.0
            selectedApartment.contains("3-х комнатная") -> 0.8
            selectedApartment.contains("Студия") -> 1.1
            else -> 1.0
        }

        val totalCost = (COST_PER_SQUARE_METER * meters * coefficient).toInt()

        saveCalculationResult(totalCost)

        val intent = Intent(this, ActivityCount::class.java)
        startActivity(intent)
    }

    private fun saveCalculationResult(result: Int) {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putInt("calculation_result", result)
            .apply()
    }
}
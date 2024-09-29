package com.example.parcial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var countryEditText1: EditText
    private lateinit var countryEditText2: EditText
    private lateinit var countryEditText3: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryEditText1 = findViewById(R.id.countryEditText1)
        countryEditText2 = findViewById(R.id.countryEditText2)
        countryEditText3 = findViewById(R.id.countryEditText3)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val country1 = countryEditText1.text.toString().trim()
            val country2 = countryEditText2.text.toString().trim()
            val country3 = countryEditText3.text.toString().trim()

            if (country1.isNotEmpty() && country2.isNotEmpty() && country3.isNotEmpty()) {
                val intent = Intent(this, CountrySelectionActivity::class.java)
                intent.putExtra("COUNTRY1", country1)
                intent.putExtra("COUNTRY2", country2)
                intent.putExtra("COUNTRY3", country3)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, ingrese 3 países", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
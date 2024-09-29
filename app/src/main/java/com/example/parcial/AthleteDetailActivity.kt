package com.example.parcial

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AthleteDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_athlete_detail)

        val athleteName = intent.getStringExtra("ATHLETE_NAME") ?: ""
        val athleteSport = intent.getStringExtra("ATHLETE_SPORT") ?: ""
        val athleteActive = intent.getBooleanExtra("ATHLETE_ACTIVE", false)

        findViewById<TextView>(R.id.athleteNameTextView).text = "Nombre: $athleteName"
        findViewById<TextView>(R.id.athleteSportTextView).text = "Deporte: $athleteSport"
        findViewById<TextView>(R.id.athleteActiveTextView).text = "En actividad: ${if (athleteActive) "Sí" else "No"}"
    }
}
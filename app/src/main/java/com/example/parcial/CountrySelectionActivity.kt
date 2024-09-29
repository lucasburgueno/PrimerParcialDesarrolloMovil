package com.example.parcial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

// Asegúrate de que este import coincida con el paquete donde tienes definida la clase Athlete
import com.example.parcial.Athlete

class CountrySelectionActivity : AppCompatActivity() {
    private lateinit var countrySpinner: Spinner
    private lateinit var athleteListView: ListView
    private lateinit var countries: Array<String>
    private val athletesByCountry = mutableMapOf<String, List<Athlete>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_selection)

        countrySpinner = findViewById(R.id.countrySpinner)
        athleteListView = findViewById(R.id.athleteListView)

        countries = arrayOf(
            intent.getStringExtra("COUNTRY1") ?: "",
            intent.getStringExtra("COUNTRY2") ?: "",
            intent.getStringExtra("COUNTRY3") ?: ""
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        initializeAthletes()

        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCountry = countries[position]
                val athletes = athletesByCountry[selectedCountry] ?: emptyList()
                val athleteNames = athletes.map { it.name }
                val athleteAdapter = ArrayAdapter(this@CountrySelectionActivity, android.R.layout.simple_list_item_1, athleteNames)
                athleteListView.adapter = athleteAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        athleteListView.setOnItemClickListener { _, _, position, _ ->
            val selectedCountry = countrySpinner.selectedItem.toString()
            val selectedAthlete = athletesByCountry[selectedCountry]?.get(position)
            if (selectedAthlete != null) {
                val intent = Intent(this, AthleteDetailActivity::class.java)
                intent.putExtra("ATHLETE_NAME", selectedAthlete.name)
                intent.putExtra("ATHLETE_SPORT", selectedAthlete.sport)
                intent.putExtra("ATHLETE_ACTIVE", selectedAthlete.isActive)
                startActivity(intent)
            }
        }
    }

    private fun initializeAthletes() {
        // Inicializando la lista de atletas para Argentina, Brasil y Colombia
        athletesByCountry[countries[0]] = listOf(
            Athlete("Lionel Messi", "Fútbol", true),
            Athlete("Diego Maradona", "Fútbol", false),
            Athlete("Juan Martín del Potro", "Tenis", true),
            Athlete("Gabriela Sabatini", "Tenis", false),
            Athlete("Manu Ginóbili", "Baloncesto", true),
            Athlete("Carlos Tévez", "Fútbol", false),
            Athlete("Luciana Aymar", "Hockey", true),
            Athlete("Fernando Alonso", "Automovilismo", false),
            Athlete("Sofía Mulánovich", "Surf", true),
            Athlete("Ezequiel Lavezzi", "Fútbol", false)
        )

        athletesByCountry[countries[1]] = listOf(
            Athlete("Pelé", "Fútbol", true),
            Athlete("Neymar Jr.", "Fútbol", true),
            Athlete("Ronaldo Nazário", "Fútbol", false),
            Athlete("Kaká", "Fútbol", true),
            Athlete("Giba", "Voleibol", true),
            Athlete("Marta Vieira", "Fútbol", true),
            Athlete("Ayrton Senna", "Automovilismo", false),
            Athlete("Raí", "Fútbol", false),
            Athlete("Adriana Lima", "Voleibol", true),
            Athlete("Rivaldo", "Fútbol", false)
        )

        athletesByCountry[countries[2]] = listOf(
            Athlete("James Rodríguez", "Fútbol", true),
            Athlete("Radamel Falcao", "Fútbol", true),
            Athlete("Mariana Pajón", "Ciclismo", true),
            Athlete("Nairo Quintana", "Ciclismo", true),
            Athlete("Falcao García", "Fútbol", false),
            Athlete("Yuberjen Martínez", "Boxeo", true),
            Athlete("Caterine Ibargüen", "Atletismo", true),
            Athlete("Carlos Valderrama", "Fútbol", false),
            Athlete("Ángela Ruiz", "Natación", true),
            Athlete("Jhonatan Rivas", "Atletismo", true)
        )
    }

}
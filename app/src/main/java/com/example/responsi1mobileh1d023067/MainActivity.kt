package com.example.responsi1mobileh1d023067

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.responsi1mobileh1d023067.pages.CoachPage
import com.example.responsi1mobileh1d023067.pages.HistoryPage
import com.example.responsi1mobileh1d023067.pages.SquadPage


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CardView>(R.id.clubHistory).setOnClickListener {
            startActivity(Intent(this, HistoryPage::class.java))
        }

        findViewById<CardView>(R.id.clubCoach).setOnClickListener {
            startActivity(Intent(this, CoachPage::class.java))
        }

        findViewById<CardView>(R.id.clubSquad).setOnClickListener {
            startActivity(Intent(this, SquadPage::class.java))
        }
    }
}
package com.example.responsi1mobileh1d023067.pages

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.responsi1mobileh1d023067.R
import com.example.responsi1mobileh1d023067.data.model.Coach
import com.example.responsi1mobileh1d023067.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023067.utils.Constants
import kotlinx.coroutines.launch

class CoachPage : AppCompatActivity() {
    private lateinit var contentLayout: ScrollView
    private lateinit var errorText: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach)
        
        contentLayout = findViewById(R.id.contentLayout)
        errorText = findViewById(R.id.errorText)
        
        fetchCoachData()
    }
    
    private fun fetchCoachData() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getTeam(Constants.TEAM_ID)
                
                if (response.isSuccessful && response.body() != null) {
                    val teamData = response.body()!!
                    val coach = teamData.coach
                    displayCoach(coach)
                } else {
                    showError("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                showError("Network error: ${e.message}")
            }
        }
    }
    
    private fun displayCoach(coach: Coach?) {
        if (coach != null) {
            
            val coachName = coach.name ?: "${coach.firstName ?: ""} ${coach.lastName ?: ""}".trim()
            findViewById<TextView>(R.id.tvCoachName).text = if (coachName.isNotEmpty()) coachName else "Unknown Coach"
            
            
            findViewById<TextView>(R.id.tvCoachNationality).text = coach.nationality ?: "Unknown"
            
            
            findViewById<TextView>(R.id.tvCoachBirthDate).text = coach.dateOfBirth ?: "Unknown"
            
            contentLayout.visibility = View.VISIBLE
            errorText.visibility = View.GONE
        } else {
            showError("No coach data available")
        }
    }
    
    private fun showError(message: String = "Failed to load coach data") {
        contentLayout.visibility = View.GONE
        errorText.visibility = View.VISIBLE
        errorText.text = message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    companion object {
        private const val TAG = "CoachPage"
    }
}
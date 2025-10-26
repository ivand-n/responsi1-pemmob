package com.example.responsi1mobileh1d023067.pages

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023067.R
import com.example.responsi1mobileh1d023067.utils.PlayerAdapter
import com.example.responsi1mobileh1d023067.data.model.Player
import com.example.responsi1mobileh1d023067.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023067.utils.Constants
import com.example.responsi1mobileh1d023067.fragments.PlayerDetailFragment
import kotlinx.coroutines.launch

class SquadPage : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorLayout: LinearLayout
    private lateinit var btnRetry: Button
    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var adapter: PlayerAdapter
    private lateinit var rootLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squad)

        recyclerView = findViewById(R.id.recyclerView)
        errorLayout = findViewById(R.id.errorLayout)
        btnRetry = findViewById(R.id.btnRetry)
        fragmentContainer = findViewById(R.id.fragmentContainer)
        rootLayout = findViewById(android.R.id.content)

        setupRecyclerView()
        setupRetryButton()
        setupCloseFragmentListeners()
        fetchSquadData()
    }

    private fun setupRecyclerView() {
        adapter = PlayerAdapter { player ->
            showPlayerDetails(player)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupRetryButton() {
        btnRetry.setOnClickListener {
            fetchSquadData()
        }
    }

    private fun setupCloseFragmentListeners() {
        // Click listener untuk area di luar RecyclerView dan fragment
        findViewById<View>(R.id.headerLayout)?.setOnClickListener {
            closePlayerDetails()
        }
        
        // Click listener untuk background area
        findViewById<View>(android.R.id.content).setOnClickListener {
            closePlayerDetails()
        }
    }

    private fun fetchSquadData() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getTeam(Constants.TEAM_ID)

                if (response.isSuccessful && response.body() != null) {
                    val squad = response.body()!!.squad ?: emptyList()
                    displaySquad(squad)
                } else {
                    showError("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                showError("Network error: ${e.message}")
            }
        }
    }

    private fun displaySquad(squad: List<Player>) {
        adapter.updatePlayers(squad)
        recyclerView.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
    }

    private fun showError(message: String = "Failed to load squad data") {
        recyclerView.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
        findViewById<TextView>(R.id.errorText).text = message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    private fun closePlayerDetails() {
        if (fragmentContainer.visibility == View.VISIBLE) {
            fragmentContainer.visibility = View.GONE
            
            // Remove fragment from fragment manager
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            if (currentFragment != null) {
                supportFragmentManager.beginTransaction()
                    .remove(currentFragment)
                    .commit()
            }
        }
    }
    
    private fun showPlayerDetails(player: Player) {
        val fragment = PlayerDetailFragment.newInstance(player)
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
            
        fragmentContainer.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if (fragmentContainer.visibility == View.VISIBLE) {
            closePlayerDetails()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "SquadPage"
    }
}
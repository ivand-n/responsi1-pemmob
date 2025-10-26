package com.example.responsi1mobileh1d023067.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.responsi1mobileh1d023067.R
import com.example.responsi1mobileh1d023067.data.model.Player
import java.text.SimpleDateFormat
import java.util.Locale

class PlayerDetailFragment : Fragment() {
    
    private var player: Player? = null
    
    companion object {
        private const val ARG_PLAYER_NAME = "player_name"
        private const val ARG_PLAYER_POSITION = "player_position"
        private const val ARG_PLAYER_NATIONALITY = "player_nationality"
        private const val ARG_PLAYER_BIRTH_DATE = "player_birth_date"
        
        fun newInstance(player: Player): PlayerDetailFragment {
            val fragment = PlayerDetailFragment()
            val args = Bundle().apply {
                putString(ARG_PLAYER_NAME, player.name)
                putString(ARG_PLAYER_POSITION, player.position)
                putString(ARG_PLAYER_NATIONALITY, player.nationality)
                putString(ARG_PLAYER_BIRTH_DATE, player.dateOfBirth)
            }
            fragment.arguments = args
            return fragment
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_detail, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val playerName = arguments?.getString(ARG_PLAYER_NAME)
        val playerPosition = arguments?.getString(ARG_PLAYER_POSITION)
        val playerNationality = arguments?.getString(ARG_PLAYER_NATIONALITY)
        val playerBirthDate = arguments?.getString(ARG_PLAYER_BIRTH_DATE)
        
        view.findViewById<TextView>(R.id.tv_detail_name).text = playerName ?: "Unknown Player"
        view.findViewById<TextView>(R.id.tv_detail_position).text = "Position: ${playerPosition ?: "Unknown"}"
        view.findViewById<TextView>(R.id.tv_detail_nationality).text = "Nationality: ${playerNationality ?: "Unknown"}"
        view.findViewById<TextView>(R.id.tv_detail_birth_date).text = "Birth Date: ${formatDateToDDMMYYYY(playerBirthDate)}"
    }
    
    private fun formatDateToDDMMYYYY(dateString: String?): String {
        if (dateString.isNullOrEmpty()) {
            return "Unknown"
        }
        
        return try {
            // Input format dari API: YYYY-MM-DD
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            // Output format: DD-MM-YYYY
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            
            val date = inputFormat.parse(dateString)
            if (date != null) {
                outputFormat.format(date)
            } else {
                "Unknown"
            }
        } catch (e: Exception) {
            // Jika parsing gagal, tampilkan tanggal asli
            dateString
        }
    }
}

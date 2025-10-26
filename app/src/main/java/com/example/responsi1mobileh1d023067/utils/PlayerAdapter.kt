package com.example.responsi1mobileh1d023067.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023067.R
import com.example.responsi1mobileh1d023067.data.model.Player

class PlayerAdapter(
    private val onItemClick: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    private var players: List<Player> = emptyList()

    fun updatePlayers(newPlayers: List<Player>) {
        players = newPlayers
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerName: TextView = view.findViewById(R.id.tvPlayerName)
        val playerPosition: TextView = view.findViewById(R.id.tvPosition)
        val playerNationality: TextView = view.findViewById(R.id.tvNationality)
        val colorIndicator: LinearLayout = view.findViewById(R.id.colorIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player.name ?: "Unknown Player"
        holder.playerNationality.text = player.nationality ?: "Unknown Nationality"
        
        // Set position text and show it
        holder.playerPosition.text = player.position ?: "Unknown Position"
        holder.playerPosition.visibility = View.VISIBLE

        // Determine background color based on position
        val backgroundColor = getPositionColor(player.position)
        holder.colorIndicator.setBackgroundColor(backgroundColor)

        // Set click listener
        holder.itemView.setOnClickListener { view ->
            // Consume the click event to prevent it from bubbling up
            view.isClickable = true
            onItemClick(player)
        }
        
        // Prevent long clicks from interfering
        holder.itemView.setOnLongClickListener { 
            true // Consume the long click event
        }
    }

    override fun getItemCount() = players.size

    private fun getPositionColor(position: String?): Int {
        return when {
            position == null -> Color.parseColor("#F5F5F5") // Default gray
            position.contains("Goalkeeper", ignoreCase = true) -> Color.parseColor("#4CAF50") // Green for goalkeepers
            position.contains("Defence", ignoreCase = true) || 
            position.contains("Defender", ignoreCase = true) ||
            position.contains("Back", ignoreCase = true) -> Color.parseColor("#2196F3") // Blue for defenders
            position.contains("Midfield", ignoreCase = true) ||
            position.contains("Midfielder", ignoreCase = true) -> Color.parseColor("#FF9800") // Orange for midfielders
            position.contains("Forward", ignoreCase = true) ||
            position.contains("Winger", ignoreCase = true) ||
            position.contains("Offence", ignoreCase = true) ||
            position.contains("Attacker", ignoreCase = true) -> Color.parseColor("#F44336") // Red for forwards
            else -> Color.parseColor("#9C27B0") // Purple for unknown positions
        }
    }
}

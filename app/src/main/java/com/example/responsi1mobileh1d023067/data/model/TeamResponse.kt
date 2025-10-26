package com.example.responsi1mobileh1d023067.data.model

data class TeamResponse(
    val name: String?,
    val shortName: String?,
    val tla: String?,
    val crest: String?,
    val address: String?,
    val website: String?,
    val venue: String?,
    val coach: Coach?,
    val squad: List<Player>?
)


data class Coach(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val name: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val contract: Contract?
)


data class Contract(
    val start: String?,
    val until: String?
)


data class Player(
    val id: Int?,
    val name: String?,
    val position: String?,
    val dateOfBirth: String?,
    val nationality: String?
)

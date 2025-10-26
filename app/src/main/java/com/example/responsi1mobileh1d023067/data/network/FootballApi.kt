package com.example.responsi1mobileh1d023067.data.network

import com.example.responsi1mobileh1d023067.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApi {
    @GET("teams/{id}")
    suspend fun getTeam(
        @Path("id") id: Int
    ): Response<TeamResponse>
}
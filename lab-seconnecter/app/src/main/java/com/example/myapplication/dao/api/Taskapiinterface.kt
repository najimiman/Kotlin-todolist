package com.example.myapplication.dao.api

import com.example.myapplication.models.Task
import retrofit2.http.*

interface Taskapiinterface {
    @GET("tasks/find_all")
    suspend fun getTasks():List<Task>

    @GET("tasks/find_by_id/{id}")
    suspend fun findById(@Path("id") id : Int) : Task

    @DELETE("tasks/{id}")
    suspend fun delete(@Path("id") id : Int) : Int

    @POST("tasks/")
    suspend fun save(@Body task : Task) : Task

    @PATCH("tasks/{id}")
    suspend fun update(@Path("id") id : Int, @Body task : Task) : Task
}


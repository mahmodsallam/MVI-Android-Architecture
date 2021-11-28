package com.example.framework.mvi.data.api

import com.example.framework.mvi.data.model.User
import retrofit2.http.GET

interface UserApi {
   @GET("users")
   suspend fun getUsers(): List<User>
}
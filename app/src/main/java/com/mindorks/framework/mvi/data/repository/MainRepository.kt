package com.mindorks.framework.mvi.data.repository

import com.mindorks.framework.mvi.data.api.UserApi
import javax.inject.Inject


class MainRepository @Inject constructor(private val userApi: UserApi) {
    suspend fun getUsers() = userApi.getUsers()
}
package com.soarapp.core.http

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2023/5/24
 *※ Time : 10:05
 *※ Project : SoarApp
 *※ Package : com.soarapp.core.http
 *----------------------------------------------------
 */
interface UserInterface {
    @GET("users")
    suspend fun getUsers(): Flow<List<String>>
}
package com.soarapp.core.http

import android.util.Log
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2023/5/23
 *※ Time : 10:24
 *※ Project : SoarApp
 *※ Package : com.soarapp.core.http
 *----------------------------------------------------
 */
class RequestClient {




    var  retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .build()



    //中缀表达式
    infix fun String.add(str:String):String{
        return this + str
    }






}
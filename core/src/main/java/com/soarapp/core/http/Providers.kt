package com.soarapp.core.http

import retrofit2.Retrofit




/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2023/4/27
 *※ Time : 17:00
 *※ Project : SoarApp
 *※ Package : com.soarapp.core.http
 *----------------------------------------------------
 */
class Providers {
    internal companion object{

        fun request(retrofit: Retrofit.()->Retrofit):Retrofit{
            return retrofit()
        }

        fun retrofit():Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()




        fun test(){
            var retrofit:Retrofit = request {
                newBuilder().baseUrl("").build()
            }
        }

//        fun moshi():Moshi

        val requestClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {

        }



//        val moshi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            Moshi
//        }

    }
}
package com.AdaInt.akademika.network

import com.AdaInt.akademika.adapter.UserModelItem
import com.AdaInt.akademika.model.CekLoginRespon
import com.AdaInt.akademika.model.RequestCekLogin
import com.AdaInt.akademika.model.RequestUser
import retrofit2.Call
import retrofit2.http.*

interface GetUserA {
    @GET("api/user")
    fun getUsers(): Call<List<UserModelItem>>
}
interface CreateData {
    @POST("api/user")
    fun createData(@Body req: RequestUser): Call<List<UserModelItem>>

    @PUT("api/user/{id}")
    fun editUser(@Path("id") id: Int, @Body req: RequestUser): Call<List<UserModelItem>>

    @DELETE("api/user/{id}")
    fun deletedUser(@Path("id") id: Int): Call<List<UserModelItem>>
}
interface CekLoginD {
    @POST("api/cekLogin")
    fun cekData(@Body req: RequestCekLogin): Call<CekLoginRespon>
}
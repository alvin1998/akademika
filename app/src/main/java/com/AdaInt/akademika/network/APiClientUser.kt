package com.AdaInt.akademika.network

import com.AdaInt.akademika.adapter.UserModelItem
import com.AdaInt.akademika.model.*
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

interface GetKelasDosen {
    @POST("api/kelas/dosen")
    fun getKelasDs(@Body req: RequestGetKelasDosen): Call<List<GetKelasDosenItem>>

    @POST("api/kelas/dosen/tambah")
    fun addKelasDs(@Body req: RequestTambahKelasDosen): Call<List<GetKelasDosenItem>>

    @PUT("api/kelas/dosen/edit/{id}")
    fun editKelas(@Path("id") id: Int, @Body req: editKelasDosen): Call<List<GetKelasDosenItem>>

    @DELETE("api/kelas/dosen/hapus/{id}")
    fun deletedKelas(@Path("id") id: Int): Call<List<GetKelasDosenItem>>
}
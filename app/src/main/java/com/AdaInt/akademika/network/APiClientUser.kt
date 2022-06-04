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
interface MBiodata {

    @POST("api/biodata/tambah")
    fun SendBiodata(@Body req: SendBiodataItem): Call<List<ResponseModelBiodataItem>>

    @POST("api/biodata/ambil")
    fun SendShowBiodata(@Body req: SendBiodataEdit): Call<ResponseModelBiodataItem>

    @PUT("api/biodata/edit/{id}")
    fun SendEditBiodata(@Path("id") id: Int, @Body req: EditBiodata): Call<ResponseModelBiodataItem>

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
interface MateriDs {
    @POST("api/kelas/dosen/materi")
    fun GetMateri(@Body req: GetMateriDsId): Call<List<GetMateriDosenItem>>

    @POST("api/kelas/dosen/materi/tambah")
    fun SendMateri(@Body req: SendMateriApi): Call<List<GetMateriDosenItem>>

    @DELETE("api/kelas/dosen/materi/hapus/{id}/{id2}/{id3}")
    fun deletedMateri(@Path("id") id: Int,@Path("id2") id2: Int,@Path("id3") id3: Int): Call<List<GetMateriDosenItem>>

    @PUT("api/kelas/dosen/materi/editMateri/{id}")
    fun editKelas(@Path("id") id: Int, @Body req: SendMateriDosenEdit): Call<List<GetMateriDosenItem>>

}

interface GetKelasMhsMo {
    @POST("api/mahasiswa/kelas")
    fun GetKelasM(@Body req: SendIdMhs): Call<List<GetKelasMhsItem>>

    @POST("api/mahasiswa/kelas/tambah")
    fun SendKelasM(@Body req: SendIdDataKelas): Call<List<GetKelasMhsItem>>

    @DELETE("api/mahasiswa/kelas/hapus/{id}/{id2}")
    fun hpsKls(@Path("id") id: Int,@Path("id2") id2: Int): Call<List<GetKelasMhsItem>>

    @POST("api/mahasiswa/kelas/materi")
    fun GetMateri(@Body req: GetKelasMateri): Call<List<GetKelasMateriMhsItem>>

}
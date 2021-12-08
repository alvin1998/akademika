package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName


data class RequestTambahKelasDosen(

    @field:SerializedName("kelas")
    var kelas: String? = null,

    @field:SerializedName("id_dosen")
    var id_dosen: Int? = null
)
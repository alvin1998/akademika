package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

data class RequestUser(

    @field:SerializedName("password")
    var password: String? = null,

    @field:SerializedName("level")
    var level: String? = null,

    @field:SerializedName("email")
    var email: String? = null,

    @field:SerializedName("username")
    var username: String? = null,

    @field:SerializedName("id_biodata")
    var id_biodata: Int? = null
)
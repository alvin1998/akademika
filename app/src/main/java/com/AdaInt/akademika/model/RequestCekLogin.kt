package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

class RequestCekLogin (
    @field:SerializedName("password")
    var password: String? = null,

    @field:SerializedName("username")
    var username: String? = null
        )
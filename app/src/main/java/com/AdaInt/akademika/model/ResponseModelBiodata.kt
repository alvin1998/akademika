package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

data class ResponseModelBiodata(

	@field:SerializedName("ResponseModelBiodata")
	val responseModelBiodata: List<ResponseModelBiodataItem?>? = null
)

data class ResponseModelBiodataItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nomer")
	val nomer: String? = null,

	@field:SerializedName("id_biodata")
	val idBiodata: Int? = null,

	@field:SerializedName("remember_token")
	val rememberToken: Any? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

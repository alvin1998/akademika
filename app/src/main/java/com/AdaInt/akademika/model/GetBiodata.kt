package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

data class GetBiodata(

	@field:SerializedName("GetBiodata")
	val getBiodata: List<GetBiodataItem?>? = null
)

data class GetBiodataItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nomer")
	val nomer: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
data class SendBiodataItem(

	@field:SerializedName("nama")
	var nama: String? = null,

	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("nomer")
	var nomer: String? = null,

	@field:SerializedName("alamat")
	var alamat: String? = null
)

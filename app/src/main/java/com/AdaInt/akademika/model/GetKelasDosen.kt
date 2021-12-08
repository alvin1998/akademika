package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

data class GetKelasDosen(

	@field:SerializedName("GetKelasDosen")
	val getKelasDosen: List<GetKelasDosenItem?>? = null
)

data class GetKelasDosenItem(

	@field:SerializedName("kondisi")
	var kondisi: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_dosen")
	val id_dosen: Int? = null
)

data class editKelasDosen(

	@field:SerializedName("id_dosen")
	var id_dosen: Int? = null,

	@field:SerializedName("kelas")
	var kelas: String? = null,

)


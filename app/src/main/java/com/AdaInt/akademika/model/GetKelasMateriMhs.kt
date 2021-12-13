package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

data class GetKelasMateriMhs(

	@field:SerializedName("GetKelasMateriMhs")
	val getKelasMateriMhs: List<GetKelasMateriMhsItem?>? = null
)

data class GetKelasMateriMhsItem(

	@field:SerializedName("id_dosen")
	val idDosen: Int? = null,

	@field:SerializedName("materi")
	val materi: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("jam")
	val jam: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id_kelas")
	val idKelas: Int? = null,

	@field:SerializedName("ruang")
	val ruang: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null
)
data class GetKelasMateri(

	@field:SerializedName("id_dosen")
	var id_dosen: Int? = null,
	@field:SerializedName("id_kelas")
	var id_kelas: Int? = null
)
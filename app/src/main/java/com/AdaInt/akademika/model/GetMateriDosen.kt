package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

data class GetMateriDosen(

	@field:SerializedName("GetMateriDosen")
	val getMateriDosen: List<GetMateriDosenItem?>? = null
)

data class GetMateriDosenItem(

	@field:SerializedName("kondisi")
	var kondisi: String? = null,

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
	val tanggal: String? = null,

	@field:SerializedName("id_dosen")
	val id_dosen: Int? = null
)
data class GetMateriDsId(

	@field:SerializedName("id_dosen")
	var id_dosen: Int? = null,
	@field:SerializedName("id_kelas")
	var id_kelas: Int? = null
)
data class SendMateriApi(

	@field:SerializedName("id_dosen")
	var id_dosen: Int? = null,

	@field:SerializedName("id_kelas")
	var id_kelas: Int? = null,

	@field:SerializedName("materi")
	var materi: String? = null,

	@field:SerializedName("tanggal")
	var tanggal: String? = null,

	@field:SerializedName("jam")
	var jam: String? = null,

	@field:SerializedName("ruang")
	var ruang: String? = null,
)
data class SendMateriDosenEdit(

	@field:SerializedName("materi")
	var materi: String? = null,

	@field:SerializedName("jam")
	var jam: String? = null,

	@field:SerializedName("id_kelas")
	var id_kelas: Int? = null,

	@field:SerializedName("ruang")
	var ruang: String? = null,

	@field:SerializedName("tanggal")
	var tanggal: String? = null,

	@field:SerializedName("id_dosen")
	var id_dosen: Int? = null
)
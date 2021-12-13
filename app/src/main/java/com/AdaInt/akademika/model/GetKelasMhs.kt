package com.AdaInt.akademika.model

import com.google.gson.annotations.SerializedName

data class GetKelasMhs(

	@field:SerializedName("GetKelasMhs")
	val getKelasMhs: List<GetKelasMhsItem?>? = null
)

data class GetKelasMhsItem(

	@field:SerializedName("kondisi")
	var kondisi: String? = null,

	@field:SerializedName("id_kelas_mhs")
	val id_kelas_mhs: Int? = null,

	@field:SerializedName("id_dosen")
	val id_dosen: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
data class SendIdMhs(

	@field:SerializedName("id_kelas")
	var id: Int? = null
)
data class SendIdDataKelas(

	@field:SerializedName("id_kelas")
	var id_kelas: Int? = null,

	@field:SerializedName("id_mhs")
	var id_mhs: Int? = null,
)
data class HpsKlsMhs(

	@field:SerializedName("id_user")
	var id_user: Int? = null
)


package com.AdaInt.akademika.adapter

import com.google.gson.annotations.SerializedName

data class UserModel(

	@field:SerializedName("userModel")
	val userModel: List<UserModelItem>
)

data class UserModelItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("remember_token")
	val rememberToken: Any? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

package com.app.curahanmental.data.source.remote.entity

data class UserEntity(
	val firstName: String? = "",
	val lastName: String? = "",
	val email: String? = "",
	val kindliness_messages: HashMap<String, KindLinessMessageEntity>? = null,
)

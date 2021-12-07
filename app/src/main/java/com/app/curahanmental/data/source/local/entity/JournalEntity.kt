package com.app.curahanmental.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal")
data class JournalEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val stressLevel: Int = 0,
	val event: String,
	val eventDetails: String? = "",
	val reason: String,
	val reasonDetails: String? = "",
	val manageEvent: String,
	val idealEventScenario: String? = "",
	val date: Long,

)

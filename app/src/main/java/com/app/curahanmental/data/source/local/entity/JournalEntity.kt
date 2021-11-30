package com.app.curahanmental.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JournalEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val stressLevel: Int = 0,
	val event: String,
	val eventDetails: String,
	val emotion: String,
	val emotionDescription: String,
	val manageEvent: String,
	val idealEvenScenario: String,
	val reason: String,
	val reasonDetails: String,

)

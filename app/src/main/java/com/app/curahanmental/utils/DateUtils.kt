package com.app.curahanmental.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
	fun convertMillisToString(timeMillis: Long): String {
		val calendar = Calendar.getInstance()
		calendar.timeInMillis = timeMillis
		val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
		return sdf.format(calendar.time)
	}

	fun convertTime(timeMillis: Long): String {
		val calendar = Calendar.getInstance()
		calendar.timeInMillis = timeMillis
		val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
		return formatter.format(calendar.time)
	}
}
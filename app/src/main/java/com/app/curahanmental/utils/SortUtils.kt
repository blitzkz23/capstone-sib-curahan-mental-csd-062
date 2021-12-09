package com.app.curahanmental.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
	fun getSortedQuery(filter: JournalsSortType): SimpleSQLiteQuery {
		val simpleQuery = StringBuilder().append("SELECT * FROM journal ")
		return SimpleSQLiteQuery(simpleQuery.toString())
	}
}
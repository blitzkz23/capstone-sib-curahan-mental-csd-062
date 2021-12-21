package com.naufaldystd.curahanmental.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
	fun getSortedQuery(sortType: JournalsSortType): SimpleSQLiteQuery {
		val simpleQuery = StringBuilder().append("SELECT * FROM journal ")
		when(sortType) {
			JournalsSortType.STRESS_LEVELS -> {
				simpleQuery.append("ORDER BY stressLevel COLLATE NOCASE")
			}
			JournalsSortType.DATE -> {
				simpleQuery.append("ORDER BY date COLLATE NOCASE")
			}
			else -> {

			}
		}
		return SimpleSQLiteQuery(simpleQuery.toString())
	}
}
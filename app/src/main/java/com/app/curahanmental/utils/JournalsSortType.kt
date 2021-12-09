package com.app.curahanmental.utils

/**
 * Used with the filter spinner in the journals list.
 */
enum class JournalsSortType {
	/**
	 * Do not filter tasks.
	 */
	ALL_TASKS,

	/**
	 * Filters only the active (not completed yet) tasks.
	 */
	ACTIVE_TASKS,

	/**
	 * Filters only the completed tasks.
	 */
	COMPLETED_TASKS
}
package com.app.curahanmental.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.curahanmental.data.source.local.entity.JournalEntity

@Database(entities = [JournalEntity::class], version = 1, exportSchema = false)
abstract class JournalDatabase : RoomDatabase() {

	abstract fun journalDao(): JournalDao

	@Volatile
	private var instance: JournalDatabase? = null

	fun getInstance(context: Context): JournalDatabase {
		return synchronized(this) {
			instance ?: Room.databaseBuilder(context, JournalDatabase::class.java, "journal.db")
				.build()
		}
	}
}
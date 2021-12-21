package com.naufaldystd.curahanmental.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naufaldystd.curahanmental.data.source.local.entity.Articles
import com.naufaldystd.curahanmental.data.source.local.entity.JournalEntity

@Database(entities = [JournalEntity::class, Articles::class], version = 1, exportSchema = false)
abstract class JournalDatabase : RoomDatabase() {

	abstract fun journalDao(): JournalDao

	companion object {
		@Volatile
		private var instance: JournalDatabase? = null

		@JvmStatic
		fun getInstance(context: Context): JournalDatabase {
			return synchronized(this) {
				instance ?: Room.databaseBuilder(context, JournalDatabase::class.java, "journal.db")
					.fallbackToDestructiveMigration() // only for testing
					.allowMainThreadQueries() // this is also for testing, Never allow main thread queries on production applications.
					.build()
			}
		}
	}
}
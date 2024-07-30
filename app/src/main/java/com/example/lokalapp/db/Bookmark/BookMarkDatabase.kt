package com.example.lokalapp.db.Job

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile


@Database(entities = [BookMark::class], version = 1)
abstract class BookMarkDatabase : RoomDatabase() {
    abstract fun bookMarkDao(): BookMarkDao

    companion object {
        @Volatile
        private var INSTANCE: BookMarkDatabase? = null

        @Synchronized
        fun getInstance(context: Context): BookMarkDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    BookMarkDatabase::class.java,
                    "bookmark_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as BookMarkDatabase
        }
    }
}

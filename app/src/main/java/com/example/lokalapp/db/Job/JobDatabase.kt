package com.example.lokalapp.db.Job

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile


@Database(entities = [Job::class], version = 1)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao

    companion object {
        @Volatile
        private var INSTANCE: JobDatabase? = null

        @Synchronized
        fun getInstance(context: Context): JobDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    JobDatabase::class.java,
                    "meal_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as JobDatabase
        }
    }
}

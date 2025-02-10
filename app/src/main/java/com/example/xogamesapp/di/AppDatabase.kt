package com.example.xogamesapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xogamesapp.data.model.GameHistoryDao
import com.example.xogamesapp.data.model.GameHistoryEntity

//@Database(entities = [GameHistoryEntity::class], exportSchema = true, version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(
//            context: Context
//        ): AppDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "game_history_database"
//                )
//                    .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
//}

/**
 * Database class with a singleton Instance object.
 */
//@Database(entities = [GameHistoryEntity::class], version = 1, exportSchema = false)
//abstract class InventoryDatabase : RoomDatabase() {
//
//    abstract fun itemDao(): GameHistoryDao
//
//    companion object {
//        @Volatile
//        private var Instance: InventoryDatabase? = null
//
//        fun getDatabase(context: Context): InventoryDatabase {
//            // if the Instance is not null, return it, otherwise create a new database instance.
//            return Instance ?: synchronized(this) {
//                Room.databaseBuilder(context, InventoryDatabase::class.java, "game_history_db")
//                    /**
//                     * Setting this option in your app's database builder means that Room
//                     * permanently deletes all data from the tables in your database when it
//                     * attempts to perform a migration with no defined migration path.
//                     */
//                    .fallbackToDestructiveMigration()
//                    .build()
//                    .also { Instance = it }
//            }
//        }
//    }
//}
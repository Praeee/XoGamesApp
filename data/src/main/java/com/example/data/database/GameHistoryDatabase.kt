package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromList(list: List<List<String>>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<List<String>> {
        val type = object : TypeToken<List<List<String>>>() {}.type
        return Gson().fromJson(json, type)
    }
}

@Database(entities = [GameHistoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameHistoryDao(): GameHistoryDao
}

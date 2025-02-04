package com.example.xogamesapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameHistoryConverters {
    @TypeConverter
    fun fromHistory(history: List<List<String>>): String {
        val gson = Gson()
        return gson.toJson(history)
    }

    @TypeConverter
    fun toHistory(historyString: String): List<List<String>> {
        val gson = Gson()
        val type = object : TypeToken<List<List<String>>>() {}.type
        return gson.fromJson(historyString, type)
    }
}

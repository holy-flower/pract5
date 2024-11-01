package com.example.pract5

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// Определяет класс, который содержит методы преобразования.
// Эти методы будут использоваться Room для преобразования данных, которые не могут быть напрямую сохранены в базе данных.
class Converters {
    @TypeConverter
    // Аннотация, указывающая, что этот метод будет использоваться для преобразования типа данных, который Room не может сохранить непосредственно.
    fun fromStringList(value: List<String>?): String {
        // Создает новый экземпляр Gson, который будет использоваться для сериализации.
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>(){}.type  // Определяет тип для списка строк с использованием TypeToken. Это позволяет Gson корректно обрабатывать коллекцию.
        return gson.toJson(value, type)  // Сериализует список строк в JSON-формат и возвращает его как строку

    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>(){}.type
        return gson.fromJson(value, type)
    }
}
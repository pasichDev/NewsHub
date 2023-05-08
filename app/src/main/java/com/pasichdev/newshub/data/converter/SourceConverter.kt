package com.pasichdev.newshub.data.converter

import androidx.room.TypeConverter
import com.pasichdev.newshub.data.model.Source


class SourceConverter {

    private val placeholder = "|"

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.id + placeholder + source.name
    }

    @TypeConverter
    fun toSource(data: String): Source {
        val dataArray = data.split(placeholder)
        return Source(dataArray[0], dataArray[1])
    }
}
package com.hareshnayak.enlight.db

import androidx.room.TypeConverter
import com.hareshnayak.enlight.modals.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String): Source {
        return Source(name,name)
    }
}
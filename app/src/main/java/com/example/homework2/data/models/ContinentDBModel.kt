package com.example.homework2.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "continents")
data class ContinentDBModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ARG_CONTINENT_ID)
    val id: Long = 0,

    @ColumnInfo(name = ARG_CONTINENT_NAME)
    val name: String
) {
    companion object {
        const val ARG_CONTINENT_ID = "continent_id"
        const val ARG_CONTINENT_NAME = "name"
    }
}
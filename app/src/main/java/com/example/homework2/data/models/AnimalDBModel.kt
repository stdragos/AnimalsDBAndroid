package com.example.homework2.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "animals", foreignKeys =
    [ForeignKey(entity = ContinentDBModel::class, parentColumns = [ContinentDBModel.ARG_CONTINENT_ID], childColumns = [AnimalDBModel.ARG_CONTINENT_ID], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class AnimalDBModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ARG_ANIMAL_ID)
    val id: Long = 0,

    @ColumnInfo(name = ARG_ANIMAL_NAME)
    val name: String,

    @ColumnInfo(name = ARG_CONTINENT_ID)
    val continentId: Long
) {
    companion object {
        const val ARG_ANIMAL_ID = "animal_id"
        const val ARG_ANIMAL_NAME = "name"
        const val ARG_CONTINENT_ID = "continent_id"
    }
}
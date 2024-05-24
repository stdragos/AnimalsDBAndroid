package com.example.homework2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework2.data.models.AnimalDBModel
import com.example.homework2.data.models.ContinentDBModel


@Database(entities = [ContinentDBModel::class, AnimalDBModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "animals_db"
    }

    abstract fun getAnimalsDao(): AnimalsDao
    abstract fun getContinentsDao(): ContinentsDao
}
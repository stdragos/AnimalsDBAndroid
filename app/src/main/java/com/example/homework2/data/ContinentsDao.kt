package com.example.homework2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework2.data.models.AnimalDBModel
import com.example.homework2.data.models.ContinentDBModel

@Dao
interface ContinentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContinent(continent: ContinentDBModel)

    @Insert
    fun insertAllContinents(continents: List<ContinentDBModel>)

    @Query("UPDATE animals SET continent_id = :continentId WHERE animal_id = :id")
    fun update(id: Long, continentId: Long)

    @Query("SELECT * FROM continents WHERE continent_id = :id")
    fun getContinentById(id: Long): ContinentDBModel

    @Query("SELECT * FROM continents WHERE lower(name) = lower(:name)")
    fun getContinentByName(name: String): ContinentDBModel

    @Query("SELECT * FROM continents")
    fun getAllContinents(): List<ContinentDBModel>
}
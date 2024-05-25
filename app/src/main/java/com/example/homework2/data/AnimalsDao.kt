package com.example.homework2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework2.data.models.AnimalDBModel
import com.example.homework2.data.models.ContinentDBModel

@Dao
interface AnimalsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimal(animal: AnimalDBModel)

    @Insert
    fun insertAllAnimals(animals: List<AnimalDBModel>)

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): List<AnimalDBModel>

    @Query("UPDATE animals SET continent_id = :continentId WHERE animal_id = :id")
    fun update(id: Long, continentId: Long)

    @Query("SELECT * FROM animals WHERE animal_id = :id")
    fun getAnimal(id: Long): AnimalDBModel

    @Query("SELECT * FROM animals WHERE lower(name) = lower(:name)")
    fun getAnimalByName(name: String): AnimalDBModel

    @Delete
    fun deleteAnimal(animal: AnimalDBModel)
}
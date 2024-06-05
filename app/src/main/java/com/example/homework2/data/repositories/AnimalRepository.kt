package com.example.homework2.data.repositories

import com.example.homework2.data.animalTask.*
import com.example.homework2.data.models.AnimalDBModel
import com.example.homework2.models.AnimalModel

object AnimalRepository {
    fun insertAnimal(animal: AnimalDBModel, onSuccess: () -> Unit) {
        InsertAnimalTask(onSuccess).execute(animal)
    }

    fun deleteAnimal(animal: AnimalDBModel, onSuccess: () -> Unit) {
        DeleteAnimalTask(onSuccess).execute(animal)
    }

    fun getAnimal(id: Long, onSuccess: (AnimalDBModel?) -> Unit) {
        GetAnimalTask(onSuccess).execute(id)
    }

    fun getAnimalByName(name: String, onSuccess: (AnimalDBModel?) -> Unit) {
        GetAnimalByNameTask(onSuccess).execute(name)
    }

    fun updateAnimal(id: Long, continentId: Long, onSuccess: () -> Unit) {
        UpdateAnimalTask(onSuccess).execute(Pair(id, continentId))
    }

    fun getAllAnimals(onSuccess: (List<AnimalDBModel>) -> Unit) {
        GetAllAnimalsTask(onSuccess).execute()
    }

    fun insertAllAnimals(animals: List<AnimalDBModel>, onSuccess: () -> Unit) {
        InsertAllAnimalsTask(onSuccess).execute(animals)
    }

    fun getAllAnimalsWithContinent(onSuccess: (List<AnimalModel>) -> Unit) {
        GetAllAnimalsWithContinentTask(onSuccess).execute()
    }
}
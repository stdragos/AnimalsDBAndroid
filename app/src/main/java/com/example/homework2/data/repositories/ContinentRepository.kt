package com.example.homework2.data.repositories

import com.example.homework2.data.continentTask.*
import com.example.homework2.data.models.ContinentDBModel


object ContinentRepository {
    fun insertContinent(continent: ContinentDBModel, onSuccess: () -> Unit) {
        InsertContinentTask(onSuccess).execute(continent)
    }

    fun getContinent(id: Long, onSuccess: (ContinentDBModel?) -> Unit) {
        GetContinentTask(onSuccess).execute(id)
    }

    fun getContinentByName(name: String, onSuccess: (ContinentDBModel?) -> Unit) {
        GetContinentByNameTask(onSuccess).execute(name)
    }

    fun getAllContinents(onSuccess: (List<ContinentDBModel>) -> Unit) {
        GetAllContinentsTask(onSuccess).execute()
    }

    fun insertAllContinents(continents: List<ContinentDBModel>, onSuccess: () -> Unit) {
        InsertAllContinentsTask(onSuccess).execute(continents)
    }
}
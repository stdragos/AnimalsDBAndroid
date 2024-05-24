package com.example.homework2

import android.app.Application
import androidx.room.Room
import com.example.homework2.data.AppDatabase
import com.example.homework2.data.models.ContinentDBModel

class ApplicationController : Application() {

    lateinit var appDatabase: AppDatabase
        private set

    companion object {
        lateinit var instance: ApplicationController
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setUpDatabase()
    }


    private fun setUpDatabase() {
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()

        var continents = listOf(
            ContinentDBModel(name = "Africa"),
            ContinentDBModel(name = "Asia"),
            ContinentDBModel(name = "Europe"),
            ContinentDBModel(name = "North America"),
            ContinentDBModel(name = "South America"),
            ContinentDBModel(name = "Australia"),
            ContinentDBModel(name = "Antarctica")
        )

        if (appDatabase.getContinentsDao().getAllContinents().isEmpty())
            appDatabase.getContinentsDao().insertAllContinents(continents)
    }
}
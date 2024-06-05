package com.example.homework2.data.animalTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController
import com.example.homework2.models.AnimalModel

class GetAllAnimalsWithContinentTask (
    private val onSuccess: (List<AnimalModel>) -> Unit,
) : AsyncTask<Unit, Unit, List<AnimalModel>>() {
    override fun doInBackground(vararg params: Unit) : List<AnimalModel> {
        return ApplicationController.instance.appDatabase.getAnimalsDao().getAllAnimals().map {
            AnimalModel(
                it.name,
                ApplicationController.instance.appDatabase.getContinentsDao().getContinent(it.continentId).name
            )
        }.toMutableList()
    }

    override fun onPostExecute(result: List<AnimalModel>) {
        onSuccess.invoke(result)
    }
}
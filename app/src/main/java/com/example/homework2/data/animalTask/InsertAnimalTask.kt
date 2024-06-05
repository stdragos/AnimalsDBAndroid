package com.example.homework2.data.animalTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController
import com.example.homework2.data.models.AnimalDBModel

class InsertAnimalTask(
    private val onSuccess: () -> Unit,
) : AsyncTask<AnimalDBModel, Unit, Unit>() {
    override fun doInBackground(vararg params: AnimalDBModel) {
        params.getOrNull(0)?.let {animal ->
            ApplicationController.instance.appDatabase.getAnimalsDao().insertAnimal(animal)
        }
    }

    override fun onPostExecute(result: Unit?) {
        onSuccess.invoke()
    }
}
package com.example.homework2.data.animalTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController
import com.example.homework2.data.models.AnimalDBModel

class GetAllAnimalsTask(
    private val onSuccess: (List<AnimalDBModel>) -> Unit,
) : AsyncTask<Unit, Unit, List<AnimalDBModel>>() {
    override fun doInBackground(vararg params: Unit) : List<AnimalDBModel> {
        return ApplicationController.instance.appDatabase.getAnimalsDao().getAllAnimals()
    }

    override fun onPostExecute(result: List<AnimalDBModel>) {
        onSuccess.invoke(result)
    }
}
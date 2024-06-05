package com.example.homework2.data.animalTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController
import com.example.homework2.data.models.AnimalDBModel

class GetAnimalByNameTask (
    private val onSuccess: (AnimalDBModel?) -> Unit,
) : AsyncTask<String, Unit, AnimalDBModel?>() {
    override fun doInBackground(vararg params: String?) : AnimalDBModel? {
        return ApplicationController.instance.appDatabase.getAnimalsDao().getAnimalByName(params.getOrNull(0) as String)
    }

    override fun onPostExecute(result: AnimalDBModel?) {
        onSuccess.invoke(result)
    }
}
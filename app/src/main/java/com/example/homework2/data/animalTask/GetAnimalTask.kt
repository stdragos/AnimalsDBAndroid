package com.example.homework2.data.animalTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController
import com.example.homework2.data.models.AnimalDBModel

class GetAnimalTask (
    private val onSuccess: (AnimalDBModel?) -> Unit,
) : AsyncTask<Long, Unit, AnimalDBModel?>() {
    override fun doInBackground(vararg params: Long?): AnimalDBModel? {
        return ApplicationController.instance.appDatabase.getAnimalsDao().getAnimal(params.getOrNull(0) as Long)
    }

    override fun onPostExecute(result: AnimalDBModel?) {
        onSuccess.invoke(result)
    }
}
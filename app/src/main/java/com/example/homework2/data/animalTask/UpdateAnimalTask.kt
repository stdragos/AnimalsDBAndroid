package com.example.homework2.data.animalTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController

class UpdateAnimalTask(
    private val onSuccess: () -> Unit,
) : AsyncTask<Pair<Long, Long>, Unit, Unit>() {
    override fun doInBackground(vararg params: Pair<Long, Long>) {
        params.getOrNull(0)?.let {update ->
            ApplicationController.instance.appDatabase.getAnimalsDao().update(update.first, update.second)
        }
    }

    override fun onPostExecute(result: Unit?) {
        onSuccess.invoke()
    }
}
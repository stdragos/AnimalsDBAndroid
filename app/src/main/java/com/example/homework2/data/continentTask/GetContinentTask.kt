package com.example.homework2.data.continentTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController
import com.example.homework2.data.models.ContinentDBModel

class GetContinentTask (
    private val onSuccess: (ContinentDBModel?) -> Unit,
) : AsyncTask<Long, Unit, ContinentDBModel?>() {
    override fun doInBackground(vararg params: Long?): ContinentDBModel? {
        return ApplicationController.instance.appDatabase.getContinentsDao().getContinent(params.getOrNull(0) as Long)
    }

    override fun onPostExecute(result: ContinentDBModel?) {
        onSuccess.invoke(result)
    }
}
package com.example.homework2.data.continentTask

import android.os.AsyncTask
import com.example.homework2.ApplicationController
import com.example.homework2.data.models.ContinentDBModel

class GetContinentByNameTask (
    private val onSuccess: (ContinentDBModel?) -> Unit,
) : AsyncTask<String, Unit, ContinentDBModel?>() {
    override fun doInBackground(vararg params: String?) : ContinentDBModel? {
        return ApplicationController.instance.appDatabase.getContinentsDao().getContinentByName(params.getOrNull(0) as String)
    }

    override fun onPostExecute(result: ContinentDBModel?) {
            onSuccess.invoke(result)
    }
}
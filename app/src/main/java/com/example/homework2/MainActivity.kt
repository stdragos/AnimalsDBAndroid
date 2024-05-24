package com.example.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.homework2.data.AppDatabase
import com.example.homework2.helpers.extensions.logErrorMessage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "onCreate".logErrorMessage()
    }

    override fun onStart() {
        super.onStart()

        "onStart".logErrorMessage()
    }

    override fun onResume() {
        super.onResume()

        "onResume".logErrorMessage()
    }

    override fun onPause() {
        super.onPause()

        "onPause".logErrorMessage()
    }

    override fun onStop() {
        super.onStop()

        "onStop".logErrorMessage()
    }

    override fun onDestroy() {
        super.onDestroy()

        "onDestroy".logErrorMessage()
    }

    override fun onRestart() {
        super.onRestart()

        "onRestart".logErrorMessage()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        "onSaveInstanceState".logErrorMessage()
    }
}
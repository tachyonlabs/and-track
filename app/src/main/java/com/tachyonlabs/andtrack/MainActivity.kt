package com.tachyonlabs.andtrack

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat



private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonA = RainbowHat.openButtonA()
        buttonA.setOnButtonEventListener { buttonA, pressed -> Log.d(TAG, "button A pressed:$pressed") }

        val buttonB = RainbowHat.openButtonB()
        buttonB.setOnButtonEventListener { buttonB, pressed -> Log.d(TAG, "button B pressed:$pressed") }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

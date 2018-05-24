package com.tachyonlabs.andtrack

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.google.android.things.contrib.driver.ht16k33.Ht16k33
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat


private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {
    var segmentDisplayOn = false
    var motorOn = false
    val rainbowLeds = RainbowHat.openLedStrip()
    var rainbow = IntArray(7)
    val RAINBOW_OFF = intArrayOf(0, 0, 0, 0, 0, 0, 0)
    val RAINBOW_BRIGHTNESS = 32
    val segmentDisplay = RainbowHat.openDisplay()
    var redLed = RainbowHat.openLedRed()
    var greenLed = RainbowHat.openLedGreen()
    var blueLed = RainbowHat.openLedBlue()
    val marqueeText = "GWG AND TRACK "
    var fourChars = ""
    var marqueePosition = 0
    val scrollTimer = object : CountDownTimer(86400000, 500) {

        override fun onTick(millisUntilFinished: Long) {
            fourChars = fourChars.substring(1) + marqueeText[marqueePosition]
            segmentDisplay.display(fourChars)
            rainbowLeds.write(rainbow)
            rainbow = rainbow.copyOfRange(6, 7) + rainbow.copyOfRange(0, 6)
            marqueePosition = (marqueePosition + 1) % marqueeText.length
        }

        override fun onFinish() {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        segmentDisplay.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)
        for (i in 0 until rainbow.size) {
            rainbow[i] = Color.HSVToColor(255, floatArrayOf(i * 360f / rainbow.size, 1.0f, 1.0f))
        }

        // Button A toggles the marquee
        val buttonA = RainbowHat.openButtonA()
        buttonA.setOnButtonEventListener { _, pressed ->
            if (pressed) {
                turnSegmentDisplayOnOrOff()
            }
        }

        // Button B toggles the motor
        val buttonB = RainbowHat.openButtonB()
        buttonB.setOnButtonEventListener { _, pressed ->
            if (pressed) {
                turnMotorOnOrOff()
            }
        }
    }

    private fun turnSegmentDisplayOnOrOff() {
        if (segmentDisplayOn) {
            segmentDisplay.setEnabled(false)
            scrollTimer.cancel()
            rainbowLeds.write(RAINBOW_OFF)
        } else {
            fourChars = "    "
            marqueePosition = 0
            segmentDisplay.clear()
            segmentDisplay.setEnabled(true)
            rainbowLeds.brightness = 1
            // if the Rainbow HAT already has a marquee/scrolling function, I don't know what it is
            scrollTimer.start()
        }
        redLed.value = !redLed.value
        segmentDisplayOn = !segmentDisplayOn
    }

    private fun turnMotorOnOrOff() {
        if (motorOn) {
            greenLed.value = false
            Log.d(TAG, "turning motor off")
        } else {
            Log.d(TAG, "turning motor on")
        }
        greenLed.value = !greenLed.value
        motorOn = !motorOn
    }

    override fun onDestroy() {
        super.onDestroy()
        segmentDisplay.close()
        rainbowLeds.close()
    }

}

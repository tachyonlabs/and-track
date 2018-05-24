package com.tachyonlabs.andtrack

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.things.contrib.driver.ht16k33.Ht16k33
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager

private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {
    var segmentDisplayOn = false
    val rainbowLeds = RainbowHat.openLedStrip()
    var rainbow = IntArray(7)
    val RAINBOW_OFF = intArrayOf(0, 0, 0, 0, 0, 0, 0)
    val segmentDisplay = RainbowHat.openDisplay()
    var redLed = RainbowHat.openLedRed()
    var greenLed = RainbowHat.openLedGreen()
    var blueLed = RainbowHat.openLedBlue()
    val marqueeText = "GWG AND TRACK "
    var fourChars = ""
    var marqueePosition = 0
    lateinit var j2Gpio: Gpio
    lateinit var scrollTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        segmentDisplay.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)
        val peripheralManager = PeripheralManager.getInstance()
        j2Gpio = peripheralManager.openGpio("GPIO2_IO01")
        j2Gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
        j2Gpio.setActiveType(Gpio.ACTIVE_HIGH)

        scrollTimer = object : CountDownTimer(86400000, 500) {
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
            scrollTimer.start()
        }
        redLed.value = !redLed.value
        segmentDisplayOn = !segmentDisplayOn
    }

    private fun turnMotorOnOrOff() {
        greenLed.value = !greenLed.value
        j2Gpio.value = !j2Gpio.value
    }

    override fun onDestroy() {
        super.onDestroy()
        segmentDisplay.close()
        rainbowLeds.close()
        j2Gpio.close()
    }

}

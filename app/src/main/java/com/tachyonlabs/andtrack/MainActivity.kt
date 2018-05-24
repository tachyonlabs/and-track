package com.tachyonlabs.andtrack

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.things.contrib.driver.apa102.Apa102
import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay
import com.google.android.things.contrib.driver.ht16k33.Ht16k33
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager

private val TAG = MainActivity::class.java.simpleName
private val RAINBOW_OFF = intArrayOf(0, 0, 0, 0, 0, 0, 0)
private val MARQUEE_TEXT = "GWG AND TRACK "

class MainActivity : Activity() {
    lateinit var rainbowLeds: Apa102
    lateinit var segmentDisplay: AlphanumericDisplay
    lateinit var j2Gpio: Gpio
    lateinit var redLed: Gpio
    lateinit var greenLed: Gpio
    lateinit var blueLed: Gpio
    lateinit var scrollTimer: CountDownTimer
    var segmentDisplayOn = false
    var rainbow = IntArray(7)
    var fourCharSegmentDisplayText = ""
    var marqueePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // pin GPIO2_IO01 on JP2 on the NXP i.MX7D board, used to control the motor
        j2Gpio = PeripheralManager.getInstance().openGpio("GPIO2_IO01")
        j2Gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
        j2Gpio.setActiveType(Gpio.ACTIVE_HIGH)
        rainbowLeds = RainbowHat.openLedStrip()
        redLed = RainbowHat.openLedRed()
        greenLed = RainbowHat.openLedGreen()
        blueLed = RainbowHat.openLedBlue()
        segmentDisplay = RainbowHat.openDisplay()
        segmentDisplay.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)

        // I should probably change this to a runnable :-)
        scrollTimer = object : CountDownTimer(86400000, 500) {
            override fun onTick(millisUntilFinished: Long) {
                // the marquee and rainbow strip scroll one step every half second
                fourCharSegmentDisplayText = fourCharSegmentDisplayText.substring(1) + MARQUEE_TEXT[marqueePosition]
                segmentDisplay.display(fourCharSegmentDisplayText)
                rainbowLeds.write(rainbow)
                rainbow = rainbow.copyOfRange(6, 7) + rainbow.copyOfRange(0, 6)
                marqueePosition = (marqueePosition + 1) % MARQUEE_TEXT.length
            }

            override fun onFinish() {
            }
        }

        for (i in 0 until rainbow.size) {
            rainbow[i] = Color.HSVToColor(255, floatArrayOf(i * 360f / rainbow.size, 1.0f, 1.0f))
        }

        // Button A toggles the marquee and rainbow LED strip
        val buttonA = RainbowHat.openButtonA()
        buttonA.setOnButtonEventListener { _, pressed ->
            if (pressed) {
                turnSegmentAndLedStripDisplaysOnOrOff()
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

    private fun turnSegmentAndLedStripDisplaysOnOrOff() {
        if (segmentDisplayOn) {
            scrollTimer.cancel()
            rainbowLeds.write(RAINBOW_OFF)
        } else {
            fourCharSegmentDisplayText = "    "
            marqueePosition = 0
            segmentDisplay.clear()
            rainbowLeds.brightness = 1
            scrollTimer.start()
        }
        redLed.value = !redLed.value
        segmentDisplayOn = !segmentDisplayOn
        segmentDisplay.setEnabled(segmentDisplayOn)
    }

    private fun turnMotorOnOrOff() {
        greenLed.value = !greenLed.value
        j2Gpio.value = !j2Gpio.value
    }

    override fun onDestroy() {
        super.onDestroy()
        segmentDisplay.close()
        rainbowLeds.close()
        redLed.close()
        greenLed.close()
        blueLed.close()
        j2Gpio.close()
    }

}

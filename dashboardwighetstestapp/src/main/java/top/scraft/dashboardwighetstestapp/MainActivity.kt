package top.scraft.dashboardwighetstestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.scraft.dashboardwidgets.view.ArcView
import top.scraft.dashboardwidgets.view.LedView
import java.util.*

class MainActivity : AppCompatActivity() {
    private val timer = Timer()

    private lateinit var arc1: ArcView
    private lateinit var arc2: ArcView

    private lateinit var led1: LedView
    private lateinit var led2: LedView
    private lateinit var led3: LedView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arc1 = findViewById(R.id.arc1)
        arc2 = findViewById(R.id.arc2)
        arc2.title = "Demo Value"

        led1 = findViewById(R.id.led1)
        led2 = findViewById(R.id.led2)
        led3 = findViewById(R.id.led3)
        led1.hue = 120F
        led2.hue = 60F
        led3.hue = 0F

        timer.scheduleAtFixedRate(Arc1Update(arc1), 0L, 20L)
        timer.scheduleAtFixedRate(Arc2Update(arc2), 0L, 20L)

        timer.scheduleAtFixedRate(Led2Update(led2), 0L, 20L)
    }

    private class Arc1Update(val arc: ArcView) : TimerTask() {
        var v = 0
        override fun run() {
            v += 1
            if (v > 100) v = 0
            arc.value = v.toFloat()
            arc.text = "$v%"
            arc.valColor = when {
                v > 80 -> 0xffe9546bU
                v > 50 -> 0xffee7948U
                else -> 0xff0094c8U
            }.toInt()
        }
    }

    private class Arc2Update(val arc: ArcView) : TimerTask() {
        var frame = 0;
        val targetDeg = 140F
        val targetVal = 67.21F
        override fun run() {
            if (frame in 0..20) {
                arc.arcDeg = targetDeg * (frame / 20F)
                arc.value = 0F
            } else if (frame in 30..50) {
                arc.value = targetVal * ((frame - 30) / 20F)
            }
            frame++
            if (frame > 80) frame = 0
        }
    }

    private class Led2Update(val led: LedView) : TimerTask() {
        private val incVal = 0.02F
        private var brightness = 1F
        private var dir = false
        override fun run() {
            if (dir) {
                brightness += incVal
                if (brightness >= 1.0F) {
                    dir = false
                }
            }
            else {
                brightness -= incVal
                if (brightness <= 0.15F) {
                    dir = true
                }
            }
            led.brightness = brightness
        }
    }
}
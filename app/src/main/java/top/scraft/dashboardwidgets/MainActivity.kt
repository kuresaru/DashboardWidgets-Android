package top.scraft.dashboardwidgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.scraft.dashboardwidgets.view.ArcView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val timer = Timer()

    private lateinit var arc1: ArcView
    private lateinit var arc2: ArcView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arc1 = findViewById(R.id.arc1)
        arc2 = findViewById(R.id.arc2)

        timer.scheduleAtFixedRate(Arc1Update(arc1), 0L, 20L)
        timer.scheduleAtFixedRate(Arc2Update(arc2), 0L, 20L)
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
}
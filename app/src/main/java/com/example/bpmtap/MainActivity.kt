package com.example.bpmtap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bpmtap.databinding.ActivityMainBinding
import java.lang.System.currentTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var avgbpm : Double = 0.0
        var n = 0
        var lastTap: Long? = null
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.coordinatorLayout.setOnClickListener{
            val now = currentTimeMillis()
            if(lastTap!=null){
                n ++
                var d = now - lastTap!!
                var newbpm : Double = (1000 * 60 /d).toDouble()

                avgbpm += (newbpm - avgbpm) / n

                updateAvgBPMText("%.2f".format(avgbpm))
                updateNewBPMText("%.2f".format(newbpm))
                updateTapText(""+n)
            }
            lastTap = now
        }

        binding.buttonReset.setOnClickListener {
            lastTap = null
            avgbpm = 0.0
            n = 0
            updateAvgBPMText("")
            updateNewBPMText("")
            updateTapText("")
        }
    }

    private fun updateTapText(s: String) {
        binding.taps.text = s
    }

    private fun updateNewBPMText(bpm: String) {
        binding.currentbpm.text = bpm
    }
    private fun updateAvgBPMText(bpm: String) {
        binding.avgbpm.text = bpm
    }
}
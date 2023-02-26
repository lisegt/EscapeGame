package com.example.escapegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.Timer
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@ExperimentalTime
class MainViewModel : ViewModel() {
    private var time: Duration = Duration.ZERO
    private lateinit var timer: Timer

    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")

    fun startChrono() {
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L) {
            time = time.plus(1.seconds)
            updateTimeStates()
        }
    }

    private fun updateTimeStates() {
        time.toComponents { hours, minutes, seconds,_ ->
            this@MainViewModel.seconds = seconds.pad()
            this@MainViewModel.minutes = minutes.pad()
            this@MainViewModel.hours = hours.pad()
        }
    }

    //pr chrono 2 avec défilement secondes
    var secondes_chrono2 = (0..100)
        .asSequence()
        .asFlow()
        .map { if (it in 0..9) "0$it" else it }
        .onEach { delay(1000) }
}

private fun Long.pad(): String {
    return this.toString().padStart(2,'0')
}

private fun Int.pad(): String {
    return this.toString().padStart(2,'0')
}

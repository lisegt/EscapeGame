package com.example.escapegame

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.Timer
import kotlin.time.Duration

class MainViewModel : ViewModel() {

    var seconds = (0..100)
        .asSequence()
        .asFlow()
        .map { if (it in 0..9) "0$it" else it }
        .onEach { delay(1000) }
}
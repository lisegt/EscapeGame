package com.example.escapegame

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

@ExperimentalAnimationApi
@Composable
fun pageHallAccueil(mainViewModel: MainViewModel = viewModel()) {
    val seconds by mainViewModel.seconds.collectAsState(initial = "00")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        AnimatedContent(
            targetState = seconds,
            transitionSpec ={
                addAnimation().using(SizeTransform(clip = true))
            }
        ) { targetCount ->
            Text(
                text = "$targetCount",
                style = TextStyle(fontSize = MaterialTheme.typography.h1.fontSize),
                textAlign = TextAlign.Center
            )
        }
    }
}
@ExperimentalAnimationApi
fun addAnimation(duration: Int = 800): ContentTransform {
    return slideInVertically(animationSpec = tween(duration)) { height -> height } + fadeIn(animationSpec = tween(durationMillis = duration)) with slideOutVertically(animationSpec = tween(durationMillis = duration)) {height -> -height} + fadeOut(animationSpec = tween(durationMillis = duration))
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@ExperimentalAnimationApi
@Composable
fun HallAccueilPreview() {
    EscapeGameTheme {
        pageHallAccueil()
    }
}
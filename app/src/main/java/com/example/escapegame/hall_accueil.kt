package com.example.escapegame

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

fun pageHallAccueil() {

}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun HallAccueilPreview() {
    EscapeGameTheme {
        pageHallAccueil()
    }
}
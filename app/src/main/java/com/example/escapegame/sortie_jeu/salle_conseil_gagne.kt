package com.example.escapegame
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun salleConseilGagne(
    modifier: Modifier,
    navController: NavController,
    onClick: () -> Unit
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.salle_conseil_fin),
                contentScale = ContentScale.FillBounds)
    }
    )
    //click sur bloc texte dilemmes
    ClickElement(
        clickableWidthPercent = 0.97F,
        clickableHeightPercent = 0.1F,
        clickableOffsetPercent = Offset(0.012F, 0.83F),
        navController = navController,
        onClick = onClick )
}




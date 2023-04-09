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
fun couloirSalleConseilOuverte(
    modifier: Modifier,
    navController: NavController,
    onClick: () -> Unit
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.salle_conseil_ouverte),
                contentScale = ContentScale.FillBounds)
    }
    )
    //click sur la porte
    ClickElement(
        clickableWidthPercent = 0.12F,
        clickableHeightPercent = 0.23F,
        clickableOffsetPercent = Offset(0.22F, 0.43F),
        navController = navController,
        onClick = onClick )

}




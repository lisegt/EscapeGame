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
fun entreeSalleConseil(
    modifier: Modifier,
    onClick: () -> Unit,
    navController: NavController
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.entree_salle_conseil),
                contentScale = ContentScale.FillBounds)
    }
    )

    //clic vers entree salle conseil
    ClickElement(
        clickableWidthPercent = 0.38F,
        clickableHeightPercent = 0.82F,
        clickableOffsetPercent = Offset(0.32F, 0.1F),
        navController = navController,
        onClick = { navController.navigate("salle_conseil") })

}


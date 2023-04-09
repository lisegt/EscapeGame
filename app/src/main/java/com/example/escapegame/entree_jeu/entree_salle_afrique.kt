package com.example.escapegame.entree_jeu

import com.example.escapegame.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import com.example.escapegame.ClickElement

@Composable
fun entreeSalleAfrique(
    modifier: Modifier,
    navController: NavController
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.entree_salle_afrique),
                contentScale = ContentScale.FillBounds)
    }
    )
    //clic entree salle afrique
    ClickElement(
        clickableWidthPercent = 0.4F,
        clickableHeightPercent = 0.9F,
        clickableOffsetPercent = Offset(0.32F, 0.1F),
        navController = navController,
        onClick = { navController.navigate("salle_afrique_sombre") })


}







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
fun couloirSalleConseil(
    modifier: Modifier,
    navController: NavController,
    onClick: () -> Unit
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.couloir_salle_conseil),
                contentScale = ContentScale.FillBounds)
    }
    )

    //clic vers salle conseil
    ClickElement(
        clickableWidthPercent = 0.15F,
        clickableHeightPercent = 0.4F,
        clickableOffsetPercent = Offset(0.52F, 0.2F),
        navController = navController,
        onClick = { navController.navigate("entree_salle_conseil") })
}




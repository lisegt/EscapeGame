package com.example.escapegame.entree_jeu

import com.example.escapegame.R
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.pageBienvenue
import com.example.escapegame.ui.theme.EscapeGameTheme
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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







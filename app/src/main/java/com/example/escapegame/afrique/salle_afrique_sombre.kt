package com.example.escapegame
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

@Composable
fun salleAfriqueSombre(
    modifier: Modifier,
    navController: NavController,
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.lumiere_eteinte),
                contentScale = ContentScale.FillBounds)
    }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(60.dp, 60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lumière !",
            color = Color(238, 183,43),
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.touch),
            contentDescription = "allumer la lumière",
            modifier = Modifier
                .size(100.dp)
        )
    }

    InterrupteurLumiere(
        clickableWidth = 1F,
        clickableHeight = 1F,
        clickableOffset = IntOffset(115, 140),
        navController = navController)
}

@Composable
fun InterrupteurLumiere(
    clickableWidth: Float = 1F,
    clickableHeight: Float = 1F,
    clickableOffset: IntOffset = IntOffset.Zero,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(clickableWidth)
            .fillMaxHeight(clickableHeight)
            .offset(clickableOffset.x.dp, clickableOffset.y.dp)
            .clickable(onClick = { navController.navigate("salle_afrique") })
    )


}




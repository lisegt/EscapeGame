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
                painterResource(id = R.drawable.couloir_salle_conseil),
                contentScale = ContentScale.FillBounds)
    }
    )

    PorteCliquableSalleConseilOuverte(
        onClick = { onClick },
        clickableWidth = 0.15F,
        clickableHeight = 0.4F,
        clickableOffset = IntOffset(380, 70),
        navController = navController)

}

@Composable
fun PorteCliquableSalleConseilOuverte(
    onClick: () -> Unit,
    clickableWidth: Float = 0.4F,
    clickableHeight: Float = 0.5F,
    clickableOffset: IntOffset = IntOffset.Zero,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(clickableWidth)
            .fillMaxHeight(clickableHeight)
            .offset(clickableOffset.x.dp, clickableOffset.y.dp)
            .clickable(onClick = {navController.navigate("salle_conseil_dilemmes")})
    )
}



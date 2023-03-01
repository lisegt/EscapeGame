package com.example.escapegame

import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

var titreHallAccueil = "Depuis une semaine, les plus grands scientifiques, ingénieurs, chercheurs, philosophes et inventeurs travaillent ensemble : notre planète est en danger …"
var corpsHallAccueil = "… il est temps d’agir"
@Composable
fun pageHallAccueil(
    modifier: Modifier,
    navController: NavController
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.hall),
                contentScale = ContentScale.FillBounds)
    }
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Text(titreHallAccueil, color = Color.White)
        Text(
            text = corpsHallAccueil,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = Color.White)
        BoutonVersCouloirSalleConseil(navController)
    }
}

@Composable
private fun BoutonVersCouloirSalleConseil(navController: NavController) {
    Button(
        modifier = Modifier.padding(vertical = 24.dp),
        onClick = { navController.navigate("couloir_salle_conseil") }
    ) {
        Text(text = "Se diriger vers la salle de conseil")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun BoutonVersCouloirSalleConseilPreview() {
    EscapeGameTheme {
        pageBienvenue(modifier = Modifier, navController = rememberNavController() )
    }
}
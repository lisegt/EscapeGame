package com.example.escapegame

import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
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

var textIndex = 0
var titre = "Bienvenue les ingénieurs Vert-U-eux !"
var corps = "Vous voici dans le building où a lieu la Conférence des Continents et Mers du Monde (CCMM)"
@Composable
fun pageBienvenue(
    modifier: Modifier,
    navController: NavController
){

    //Button(onClick = {navController.navigate("salle_afrique")}){}

    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.isis_entree),
                contentScale = ContentScale.FillBounds)
    }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            Column() {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = titre,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = corps,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    BoutonVersHallAccueil(navController)
                }
            }

        }
    }
    /*
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally

    )  {
        Text(titre, color = Color.White)
        Text(
            text = corps,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.White)
        BoutonVersHallAccueil(navController)
    }

     */
}

@Composable
private fun BoutonVersHallAccueil(navController: NavController) {
    Button(
        modifier = Modifier.padding(vertical = 10.dp),
        onClick = { navController.navigate("hall_accueil") }
    ) {
        Text(text = "Entrer")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun BienvenuePreview() {
    EscapeGameTheme {
        pageBienvenue(modifier = Modifier, navController = rememberNavController() )
    }
}
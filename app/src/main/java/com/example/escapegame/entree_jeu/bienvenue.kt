package com.example.escapegame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme
import kotlinx.coroutines.delay


var titre = "Bienvenue les ingénieurs Vert-U-eux !"
var corps = "Vous voici dans le building où a lieu la Conférence des Continents et Mers du Monde (CCMM)"
@Composable
fun pageBienvenue(
    modifier: Modifier,
    navController: NavController
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

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
                .height(screenHeight * 0.4f)
                .background(Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(16.dp))
        ) {
            Column() {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = titre,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .scale(1.25f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AnimatedText(corps){ BoutonVersHallAccueil(navController = navController) }
                }
            }

        }
    }
}

@Composable
private fun BoutonVersHallAccueil(navController: NavController) {
    Button(
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

@Composable
fun AnimatedText(texte: String, bouton: @Composable () -> Unit) {
    var lettresAffichees by remember { mutableStateOf(0) }
    var texteComplet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (lettresAffichees <= texte.length) {
            delay(80)
            lettresAffichees++
        }
        texteComplet = true
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp),
    ) {
        Text(
            text = texte.take(lettresAffichees),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        if (texteComplet) {
            bouton()
        }
    }
}
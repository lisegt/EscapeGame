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

var corpsHallAccueil = "Depuis une semaine, les plus grands scientifiques, ingénieurs, chercheurs, philosophes et inventeurs travaillent ensemble : notre planète est en danger … il est temps d’agir !"
@Composable
fun pageHallAccueil(
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
                painterResource(id = R.drawable.hall),
                contentScale = ContentScale.FillBounds)
    }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.45f)
                .background(Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(16.dp))
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AnimatedTextVertical(corpsHallAccueil){ BoutonVersCouloirSalleConseil(navController) }
                }
            }

        }
    }
}

@Composable
private fun BoutonVersCouloirSalleConseil(navController: NavController) {
    Button(
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

@Composable
fun AnimatedTextVertical(texte: String, bouton: @Composable () -> Unit) {
    var lettresAffichees by remember { mutableStateOf(0) }
    var texteComplet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (lettresAffichees <= texte.length) {
            delay(60)
            lettresAffichees++
        }
        texteComplet = true
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

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
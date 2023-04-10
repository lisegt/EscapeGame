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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

var corpsCouloirSalleAfrique = "D’une superficie de 30 millions de m2, l’Afrique est le continent le mieux doté en ressources naturelles. Pourtant, malgré tant de richesses, le continent africain reste l’un des plus pauvres et des plus en danger face aux conséquences du réchauffement climatique. Les enjeux de sa transition socio-écologique sont nombreux. \nMais avant d'entrer, prenez connaissance des règles du jeu."
@Composable
fun couloirSalleAfrique(
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
                painterResource(id = R.drawable.couloir_salle_afrique),
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
                .height(screenHeight * 0.65f)
                .background(Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(16.dp))
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AnimatedTextVertical(corpsCouloirSalleAfrique){ BoutonVersReglesDuJeu(navController) }
                }
            }

        }
    }
}

@Composable
private fun BoutonVersReglesDuJeu(navController: NavController) {
    Button(
        onClick = { navController.navigate("regles_du_jeu_1") }
    ) {
        Text(text = "Voir les règles du jeu")
    }
}
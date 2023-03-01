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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

var titre1CouloirSalleAfrique = "D’une superficie de 30 millions de m2, l’Afrique est le continent le mieux doté en ressources naturelles. Pourtant, malgré tant de richesses, le continent africain reste l’un des plus pauvres et des plus en danger face aux conséquences du réchauffement climatique. Les enjeux de sa transition socio - écologique sont nombreux."
var corpsCouloirSalleAfrique = "Mais avant d'entrer, prenez connaissance des règles du jeu"
@Composable
fun couloirSalleAfrique(
    modifier: Modifier,
    navController: NavController
){
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
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Text(
            text = titre1CouloirSalleAfrique,
            color = Color.White,
            style = TextStyle(background = Color.Black.copy(alpha = 0.5F))
        )
        Text(
            text = corpsCouloirSalleAfrique,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = Color.White)
        BoutonVersReglesDuJeu(navController)
    }
}

@Composable
private fun BoutonVersReglesDuJeu(navController: NavController) {
    Button(
        modifier = Modifier.padding(vertical = 24.dp),
        onClick = { navController.navigate("regles_du_jeu_1") }
    ) {
        Text(text = "Voir les règles du jeu")
    }
}
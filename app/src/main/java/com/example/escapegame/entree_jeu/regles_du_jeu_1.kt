package com.example.escapegame.entree_jeu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.escapegame.R
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable

var titreReglesJeu = "Les règles du jeu"
var corps1ReglesJeu = "L'Escape game peut se jouer seul (en autonomie)ou en équipe (avec l'aide d'un maître du jeu)."
var corps2ReglesJeu = "Vous avez choisi de jouez seul, vous êtes en autonomie.Continuez à prendre connaissance des règles du jeu."
var corps3ReglesJeu = "Votre mission (si vous l'acceptez ...)"
@Composable
fun reglesJeu(
    modifier: Modifier,
    navController: NavController
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.carte_monde_regles_jeu),
                contentScale = ContentScale.FillBounds)
    }
    )

    // Pop contenant les règle du jeu
    Popup() {
        //background avec image
        Box(modifier = with (Modifier){
            fillMaxSize()
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.fond_noir_regles_jeu),
                    contentScale = ContentScale.FillBounds)
        }
        )

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(titreReglesJeu, color = Color.White)
            Text(
                text = corps1ReglesJeu,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                text = corps2ReglesJeu,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(70.dp, 45.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ){
            Row() {
                Text(
                    text = corps3ReglesJeu,
                    color = Color.White,
                    modifier = Modifier.padding(20.dp, 20.dp)
                )
                FloatingButtonNextRule(
                    onClick = { navController.navigate("regles_du_jeu_2") }
                )
            }

        }
    }
}

@Composable
fun FloatingButtonNextRule(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.next),
                    contentDescription = "Next",
                )
            }
        )
    }
}





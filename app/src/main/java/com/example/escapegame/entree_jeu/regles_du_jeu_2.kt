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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.pageBienvenue
import com.example.escapegame.ui.theme.EscapeGameTheme

var titreReglesJeu_page2 = "Les règles du jeu : votre mission"
var soustitreReglesJeu_page2 = "Le but du jeu est de résoudre toutes les énigmes pour pouvoir sortir de la salle africaine et rejoindre la salle du conseil."
var corps1ReglesJeu_page2 = "Pour résoudre les énigmes, cherchez, fouillez, manipulez, dans la salle africaine, les objets qui vous fourniront les indices nécessaires."
var corps2ReglesJeu_page2 = "Chaque énigme résolue fournit un code qui permet d'accéder aux dilemmes que se posent les experts du congrès."
var corps3ReglesJeu_page2 = "Une fois tous les dilemmes ouverts, vous pourrez trouver Ze final code qui vous permettra de sortir de la salle africaine et courir en salle du conseil."
var corps4ReglesJeu_page2 = "Pour cela, vous avez 1h30 ..."
var texte_fleche_page2 = "Et après ? ..."

@Composable
fun reglesJeu_page2(
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

    // Pop contenant les règles du jeu en fonction du mode de jeu
    if (ModeJoueur.mode === "1joueur") {
        Popup() {
            //background avec image
            Box(modifier = with (Modifier){
                fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.mission_jeu_1joueur),
                        contentScale = ContentScale.FillBounds)
            }
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(70.dp, 45.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonNextRule(
                        onClick = { navController.navigate("regles_du_jeu_3") }
                    )
                }

            }
        }
    }

    if (ModeJoueur.mode === "1equipe") {
        Popup() {
            //background avec image
            Box(modifier = with (Modifier){
                fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.mission_jeu_1equipe),
                        contentScale = ContentScale.FillBounds)
            }
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(70.dp, 45.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonNextRule(
                        onClick = { navController.navigate("regles_du_jeu_3") }
                    )
                }

            }
        }
    }

    if (ModeJoueur.mode === "2equipes") {
        Popup() {
            //background avec image
            Box(modifier = with (Modifier){
                fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.mission_jeu_2equipes),
                        contentScale = ContentScale.FillBounds)
            }
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(70.dp, 45.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonNextRule(
                        onClick = { navController.navigate("regles_du_jeu_3") }
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, widthDp = 2436 , heightDp = 1125)
@Composable
fun reglesJeu_page2Preview(){
    EscapeGameTheme() {
        reglesJeu_page2(modifier = Modifier, navController = rememberNavController() )
    }
}
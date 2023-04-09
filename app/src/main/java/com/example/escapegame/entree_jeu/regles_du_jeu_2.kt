package com.example.escapegame.entree_jeu

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.escapegame.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

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
    if (VariableGlobale.mode === "1joueur") {
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

    if (VariableGlobale.mode === "1equipe") {
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

    if (VariableGlobale.mode === "2equipes") {
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
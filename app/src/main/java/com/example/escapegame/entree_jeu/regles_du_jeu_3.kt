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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ClickElement
import com.example.escapegame.pageBienvenue
import com.example.escapegame.ui.theme.EscapeGameTheme

@Composable
fun reglesJeu_page3(
    modifier: Modifier,
    navController: NavController
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
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
                        painterResource(id = R.drawable.contribution_jeu_1joueur),
                        contentScale = ContentScale.FillBounds)
            }
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(70.dp, 45.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ){
                Row() {
                    FloatingButtonPreviousRule(
                        onClick = { navController.navigate("regles_du_jeu_2") }
                    )
                }

            }

            //click vers couloir 3
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.13F,
                clickableOffsetPercent = Offset(0.75F, 0.67F),
                navController = navController,
                onClick = {navController.navigate("entree_salle_afrique")})
        }
    }

    if (VariableGlobale.mode === "1equipe") {
        Popup() {
            //background avec image
            Box(modifier = with (Modifier){
                fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.contribution_jeu_1equipe),
                        contentScale = ContentScale.FillBounds)
            }
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(70.dp, 45.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ){
                Row() {
                    FloatingButtonPreviousRule(
                        onClick = { navController.navigate("regles_du_jeu_2") }
                    )
                }

            }

            //click vers couloir 3
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.13F,
                clickableOffsetPercent = Offset(0.75F, 0.67F),
                navController = navController,
                onClick = {navController.navigate("entree_salle_afrique")})
        }
    }

    if (VariableGlobale.mode === "2equipes") {
        Popup() {
            //background avec image
            Box(modifier = with (Modifier){
                fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.contribution_jeu_2equipes),
                        contentScale = ContentScale.FillBounds)
            }
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(70.dp, 45.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ){
                Row() {
                    FloatingButtonPreviousRule(
                        onClick = { navController.navigate("regles_du_jeu_2") }
                    )
                }

            }

            //click vers couloir 3
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.13F,
                clickableOffsetPercent = Offset(0.75F, 0.67F),
                navController = navController,
                onClick = {navController.navigate("entree_salle_afrique")})

        }
    }

}

@Composable
fun FloatingButtonPreviousRule(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.before),
                    contentDescription = "Previous"
                )
            }
        )
    }
}
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

var titreReglesJeu_page3 = "Les règles du jeu : votre contribution"
var soustitreReglesJeu_page3 = "Une fois sortis de la salle africaine, vous avez gagné l'Escape game."
var corps1ReglesJeu_page3 = "Mais votre rôle ne s'arrête pas là."
var corps2ReglesJeu_page3 = "Les experts attendent vos solutions aux dilemmes."
var corps3ReglesJeu_page3 = "Vous pourrez alors prendre votre temps, utiliser des ressourceset envoyer le résultat de vos réflexions au conseil."
var corps4ReglesJeu_page3 = "Prêt à démarrez ? Retournez dans le couloir n° 3"
var texte_fleche_page3 = "Page précédente"

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

    // Pop contenant les règles du jeu
    Popup(
    ) {
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
            modifier = modifier
                .fillMaxSize()
                .padding(60.dp, 60.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = titreReglesJeu_page3,
                    color = Color(48, 165, 232),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )
                Text(
                    text = soustitreReglesJeu_page3,
                    color = Color(48, 165, 232),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
            }

            Column(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {
                Text(
                    text = corps1ReglesJeu_page3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = corps2ReglesJeu_page3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = corps3ReglesJeu_page3,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Row(modifier = modifier.padding(0.dp, 20.dp)) {
                    Text(
                        text = corps4ReglesJeu_page3,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Red,
                        modifier = modifier.padding(0.dp, 13.dp, 20.dp, 0.dp)
                    )
                    BoutonVersSalleAfrique(navController)
                }

            }
        }


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
                Text(
                    text = texte_fleche_page3,
                    color = Color.White,
                    modifier = Modifier.padding(20.dp, 20.dp)
                )



            }

        }
    }
}

@Composable
private fun BoutonVersSalleAfrique(navController: NavController) {
    Button(
        modifier = Modifier.padding(),
        onClick = { navController.navigate("entree_salle_afrique")
        }
    ) {
        Text(text = "Couloir n°3")
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
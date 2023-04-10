package com.example.escapegame

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.entree_jeu.VariableGlobale
import com.example.escapegame.ui.theme.EscapeGameTheme

@Composable
fun pageAccueil(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Button(onClick = { navController.navigate("salle_conseil_dilemmes") }) {
        
    }
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.isis_ext),
                contentScale = ContentScale.FillBounds)
    }
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Text(text = "L'aventure des Ingénieurs vert-U-eux", fontSize = 25.sp, fontWeight = FontWeight.Bold )
        
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BoutonVersBienvenue_1joueur(navController)
            BoutonVersBienvenue_1equipe(navController)
            BoutonVersBienvenue_2equipes(navController)
        }
        
    }
}

@Composable
private fun BoutonVersBienvenue_1joueur(navController: NavController) {
    Button(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .padding(end = 30.dp),
        onClick = {
            navController.navigate("bienvenue")
            VariableGlobale.mode = "1joueur"
        }
    ) {
        Text(text = "Mode 1 joueur")
    }
}

@Composable
private fun BoutonVersBienvenue_1equipe(navController: NavController) {
    Button(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .padding(end = 30.dp),
        onClick = {
            navController.navigate("bienvenue")
            VariableGlobale.mode = "1equipe"
        }
    ) {
        Text(text = "Mode 1 équipe")
    }
}

@Composable
private fun BoutonVersBienvenue_2equipes(navController: NavController) {
    Button(
        modifier = Modifier.padding(vertical = 24.dp),
        onClick = {
            navController.navigate("bienvenue")
            VariableGlobale.mode = "2equipes"
        }
    ) {
        Text(text = "Mode 2 équipes")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun AccueilPreview() {
    EscapeGameTheme {
        pageAccueil(navController = rememberNavController())
    }
}
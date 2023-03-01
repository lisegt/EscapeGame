package com.example.escapegame.entree_jeu

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.R
import com.example.escapegame.ui.theme.EscapeGameTheme

var titre1SalleConseil = "Malgré tous leurs efforts, ces brillants cerveaux ne sont encore parvenus à aucune décision."
var titre2SalleConseil = "La salle, ce soir, risque de rester désespérément silencieuse …"
var corpsSalleConseil = "… à moins que vous n'agissiez ! Il est encore possible de faire quelque chose !"
@Composable
fun salleConseil(
    modifier: Modifier,
    navController: NavController
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.salle_du_conseil),
                contentScale = ContentScale.FillBounds)
    }
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Text(titre1SalleConseil, color = Color.White)
        Text(titre2SalleConseil, color = Color.White)
        Text(
            text = corpsSalleConseil,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = Color.White)
        BoutonVersCouloirSalleAfrique(navController)
    }
}

@Composable
private fun BoutonVersCouloirSalleAfrique(navController: NavController) {
    Button(
        modifier = Modifier.padding(vertical = 24.dp),
        onClick = { navController.navigate("couloir_salle_afrique") }
    ) {
        Text(text = "Prenez le couloir n°3")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun BoutonVersCouloirSalleAfriquePreview() {
    EscapeGameTheme {
        salleConseil(modifier = Modifier, navController = rememberNavController() )
    }
}
package com.example.escapegame.entree_jeu

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.AnimatedTextVertical
import com.example.escapegame.R
import com.example.escapegame.ui.theme.EscapeGameTheme

var corpsSalleConseil = "Malgré tous leurs efforts, ces brillants cerveaux ne sont encore parvenus à aucune décision. \n La salle, ce soir, risque de rester désespérément silencieuse ... \n ... à moins que vous n'agissiez ! Il est encore possible de faire quelque chose !"
@Composable
fun salleConseil(
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
                painterResource(id = R.drawable.salle_du_conseil),
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
            Column(modifier = Modifier.padding(10.dp, 10.dp)) {
                AnimatedTextVertical(corpsSalleConseil){ BoutonVersCouloirSalleAfrique(navController) }
                /*
                    Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    }

                 */

            }

        }
    }
}

@Composable
private fun BoutonVersCouloirSalleAfrique(navController: NavController) {
    Button(
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
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

@Composable
fun pageAccueil(
    modifier: Modifier = Modifier,
    navController: NavController
) {
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
        Text("L'aventure des Ingénieurs vert-U-eux")
        BoutonVersBienvenue(navController)
    }
}

@Composable
private fun BoutonVersBienvenue(navController: NavController) {
    Button(
        modifier = Modifier.padding(vertical = 24.dp),
        onClick = { navController.navigate("bienvenue") }
    ) {
        Text(text = "Entrer")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun AccueilPreview() {
    EscapeGameTheme {
        pageAccueil(navController = rememberNavController())
    }
}
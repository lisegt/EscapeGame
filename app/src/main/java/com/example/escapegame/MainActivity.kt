package com.example.escapegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.entree_jeu.*
import com.example.escapegame.ui.theme.EscapeGameTheme
import kotlin.time.ExperimentalTime

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalTime::class)
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class, ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EscapeGameTheme {

                val navController = rememberNavController()

                MyAppNavHost(modifier = Modifier, navController, "accueil",viewModel)

            }
        }
    }
}
@ExperimentalAnimationApi
@ExperimentalTime
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "accueil",
    viewModel: MainViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("accueil"){
            pageAccueil(modifier, navController)
        }
        composable("bienvenue"){
            pageBienvenue(modifier = modifier, navController = navController )
        }
        composable("hall_accueil"){
            pageHallAccueil(modifier = modifier, navController = navController)
        }
        composable("couloir_salle_conseil"){
            couloirSalleConseil(modifier = modifier, onClick = {navController.navigate("entree_salle_conseil")}, navController = navController)
        }
        composable("entree_salle_conseil"){
            entreeSalleConseil(modifier = modifier, onClick = {navController.navigate("salle_conseil")}, navController = navController)
        }
        composable("salle_conseil"){
            salleConseil(modifier = modifier,  navController = navController)
        }
        composable("couloir_salle_afrique"){
            couloirSalleAfrique(modifier = modifier, navController = navController)
        }
        composable("regles_du_jeu_1"){
            reglesJeu_page1(modifier = modifier, navController = navController)
        }
        composable("regles_du_jeu_2"){
            reglesJeu_page2(modifier = modifier, navController = navController)
        }
        composable("regles_du_jeu_3"){
            reglesJeu_page3(modifier = modifier, navController = navController)
        }
        composable("entree_salle_afrique"){
            entreeSalleAfrique(modifier = modifier, navController)
        }
        composable("salle_afrique_sombre"){
            salleAfriqueSombre(modifier = modifier, navController)
        }
        composable("salle_afrique"){
            salleAfrique(modifier = modifier, navController)
        }
        composable("couloir"){
            couloirSalleConseilOuverte(modifier = modifier, onClick = {navController.navigate("salle_conseil_gagne")}, navController = navController)
        }
        composable("salle_conseil_gagne"){
            salleConseilGagne(modifier = modifier, onClick = {navController.navigate("salle_conseil_dilemmes")}, navController = navController)
        }
        composable("salle_conseil_dilemmes"){
            salleConseilDilemmes(modifier = modifier, onClick = {navController.navigate("accueil")}, navController = navController)
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@ExperimentalAnimationApi
@ExperimentalTime
@Composable
fun OnboardingPreview() {
    EscapeGameTheme {
        val navController = rememberNavController()
        val viewModel: MainViewModel by viewModel()
        MyAppNavHost(modifier = Modifier, navController, "accueil", viewModel)
    }
}

@Preview(showBackground = true, name = "Text Preview", widthDp = 320)
@Composable
@ExperimentalTime
@ExperimentalAnimationApi
fun MyAppPreview() {
    EscapeGameTheme {
        val navController = rememberNavController()
        val viewModel: MainViewModel by viewModel()
        MyAppNavHost(Modifier.fillMaxSize(),navController, "accueil", viewModel)
    }
}
package com.example.escapegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.escapegame.ui.theme.EscapeGameTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EscapeGameTheme {

                val navController = rememberNavController()



                MyAppNavHost()
                /*
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    Image(
                        painter = painterResource(R.drawable.isis_ext),
                        contentDescription = "Photo extérieur ISIS",
                        modifier = Modifier.fillMaxSize())
                }
                */

            }
        }
    }
}
@ExperimentalAnimationApi
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "accueil"
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
            pageHallAccueil()
        }

    }
}
/*
@Composable
fun MyApp(modifier: Modifier = Modifier, navController: NavController){
        //l'état doit être hissé
        var clickDebutJeu by remember { mutableStateOf(true) }

        Surface(modifier) {
            if (clickDebutJeu) {
                pageAccueil(modifier, navController )
            } else {
                Greetings()
            }
        }
}
 */



@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@ExperimentalAnimationApi
@Composable
fun OnboardingPreview() {
    EscapeGameTheme {
        MyAppNavHost()
    }
}
/*
@Preview(showBackground = true, name = "Text Preview", widthDp = 320)
@Composable
fun GreetingsPreview() {
    EscapeGameTheme {
        Greetings()
    }
}
 */


@Preview(showBackground = true, name = "Text Preview", widthDp = 320)
@Composable
@ExperimentalAnimationApi
fun MyAppPreview() {
    EscapeGameTheme {
        MyAppNavHost(Modifier.fillMaxSize())
    }
}

/*
@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")){

    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {

    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)

            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            Button(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }


    }

}
 */
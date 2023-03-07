package com.example.escapegame
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.escapegame.entree_jeu.FloatingButtonNextRule
import com.example.escapegame.entree_jeu.corps3ReglesJeu

@Composable
fun salleAfrique(
    modifier: Modifier,
    navController: NavController,
){

    //etats qui indiquent si les murs doivent être affichés ou non
    //mur du fond affiché par défaut
    var showMurFond by remember { mutableStateOf(true) }
    //autres mur
    var showMurDroite by remember { mutableStateOf(false) }
    var showMurGauche by remember { mutableStateOf(false) }
    var showMurEntree by remember { mutableStateOf(false) }

    if (showMurFond) {
        //Mur du fond
        MurFondAfrique(
            modifier,
            navController,
            isDisplayedRight = showMurFond,
            isDisplayedLeft = showMurFond,
            onDisplayChangeToRight = { afficheD ->
                showMurFond = false
                showMurDroite = !showMurDroite
            },
            onDisplayChangeToLeft = { afficheG ->
                showMurFond = false
                showMurGauche = !showMurGauche
            }
        )
    }

    if (showMurDroite) {
        //Mur de droite
        MurDroiteAfrique(
            modifier = modifier ,
            navController = navController,
            isDisplayedRight = showMurDroite,
            isDisplayedLeft = showMurDroite,
            onDisplayChangeToRight = { afficheD ->
                showMurDroite = false
                showMurEntree = !showMurEntree
            },
            onDisplayChangeToLeft = { afficheG ->
                showMurDroite = false
                showMurFond = !showMurFond
            }
        )
    }

    if (showMurEntree){
        MurEntreeAfrique(
            modifier = modifier,
            navController = navController,
            isDisplayedRight = showMurEntree,
            isDisplayedLeft = showMurEntree,
            onDisplayChangeToRight = { afficheD ->
                showMurEntree = false
                showMurGauche = !showMurGauche
            },
            onDisplayChangeToLeft = { afficheG ->
                showMurEntree = false
                showMurDroite = !showMurDroite
            }
        )
    }

    if (showMurGauche){
        MurGaucheAfrique(
            modifier = modifier,
            navController = navController,
            isDisplayedRight = showMurGauche,
            isDisplayedLeft = showMurGauche,
            onDisplayChangeToRight = { afficheD ->
                showMurGauche = false
                showMurFond = !showMurFond
            },
            onDisplayChangeToLeft = { afficheG ->
                showMurGauche = false
                showMurEntree = !showMurEntree
            }
        )
    }
}

@Composable
fun MurFondAfrique(
    modifier: Modifier,
    navController: NavController,
    isDisplayedRight:Boolean,
    isDisplayedLeft: Boolean,
    onDisplayChangeToRight: (Boolean) -> Unit,
    onDisplayChangeToLeft: (Boolean) -> Unit
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_fond),
                contentScale = ContentScale.FillBounds)
    }
    )

    //click sur un objet de la pièce
    ClickElement(
        clickableWidth = 0.5F,
        clickableHeight = 1F,
        clickableOffset = IntOffset(115, 140),
        navController = navController,
        onClick = {})

    /*
        fun changeDisplayToRight(){
            onDisplayChangeToRight(!isDisplayed)
        }
     */

    Button(onClick = {navController.navigate("salle_afrique_sombre")}){}
    //Boutons de navigation entre les murs
    ToNextRightWall(modifier = modifier, navController =  navController, onClick = {onDisplayChangeToRight(!isDisplayedRight)})
    ToNextLeftWall(modifier = modifier, navController = navController, onClick = {onDisplayChangeToLeft(!isDisplayedLeft)} )

}

@Composable
fun MurDroiteAfrique(
    modifier: Modifier,
    navController: NavController,
    isDisplayedRight:Boolean,
    isDisplayedLeft: Boolean,
    onDisplayChangeToRight: (Boolean) -> Unit,
    onDisplayChangeToLeft: (Boolean) -> Unit
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_droite),
                contentScale = ContentScale.FillBounds)
    }
    )
    ClickElement(
        clickableWidth = 1F,
        clickableHeight = 1F,
        clickableOffset = IntOffset(115, 140),
        navController = navController,
        onClick = {})

    //Boutons de navigation entre les murs
    ToNextRightWall(modifier = modifier, navController =  navController, onClick = {onDisplayChangeToRight(!isDisplayedRight)})
    ToNextLeftWall(modifier = modifier, navController = navController, onClick = {onDisplayChangeToLeft(!isDisplayedLeft)} )

}

@Composable
fun MurEntreeAfrique(
    modifier: Modifier,
    navController: NavController,
    isDisplayedRight:Boolean,
    isDisplayedLeft: Boolean,
    onDisplayChangeToRight: (Boolean) -> Unit,
    onDisplayChangeToLeft: (Boolean) -> Unit
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_entree),
                contentScale = ContentScale.FillBounds)
    }
    )
    ClickElement(
        clickableWidth = 0.5F,
        clickableHeight = 1F,
        clickableOffset = IntOffset(115, 140),
        navController = navController,
        onClick = {})

    //Boutons de navigation entre les murs
    ToNextRightWall(modifier = modifier, navController =  navController, onClick = {onDisplayChangeToRight(!isDisplayedRight)})
    ToNextLeftWall(modifier = modifier, navController = navController, onClick = {onDisplayChangeToLeft(!isDisplayedLeft)} )

}

@Composable
fun MurGaucheAfrique(
    modifier: Modifier,
    navController: NavController,
    isDisplayedRight:Boolean,
    isDisplayedLeft: Boolean,
    onDisplayChangeToRight: (Boolean) -> Unit,
    onDisplayChangeToLeft: (Boolean) -> Unit
){
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_gauche),
                contentScale = ContentScale.FillBounds)
    }
    )
    ClickElement(
        clickableWidth = 0.5F,
        clickableHeight = 1F,
        clickableOffset = IntOffset(115, 140),
        navController = navController,
        onClick = {})

    //Boutons de navigation entre les murs
    ToNextRightWall(modifier = modifier, navController =  navController, onClick = {onDisplayChangeToRight(!isDisplayedRight)})
    ToNextLeftWall(modifier = modifier, navController = navController, onClick = {onDisplayChangeToLeft(!isDisplayedLeft)} )

}

@Composable
fun ToNextRightWall(modifier: Modifier, navController: NavController, onClick: () -> Unit){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ){
        FloatingButtonNextRightWall(
            onClick = onClick
        )

    }
}

@Composable
fun ToNextLeftWall(modifier: Modifier, navController: NavController, onClick: () -> Unit){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ){
        FloatingButtonNextLeftWall(
            onClick = onClick
        )

    }
}

@Composable
fun FloatingButtonNextRightWall(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.next),
                    contentDescription = "Prochain mur de droite"
                )
            }
        )
    }
}

@Composable
fun FloatingButtonNextLeftWall(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.before),
                    contentDescription = "Prochain mur de gauche"
                )
            }
        )
    }
}

@Composable
fun ClickElement(
    clickableWidth: Float = 1F,
    clickableHeight: Float = 1F,
    clickableOffset: IntOffset = IntOffset.Zero,
    navController: NavController,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(clickableWidth)
            .fillMaxHeight(clickableHeight)
            .offset(clickableOffset.x.dp, clickableOffset.y.dp)
            .clickable(onClick = { onClick })
    )
}




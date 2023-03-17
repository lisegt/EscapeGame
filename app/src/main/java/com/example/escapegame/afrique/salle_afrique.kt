package com.example.escapegame
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.escapegame.entree_jeu.FloatingButtonNextRule
import com.example.escapegame.entree_jeu.corps3ReglesJeu
import kotlin.math.roundToInt

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
    var showPhone by remember { mutableStateOf(false) }

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
        clickableWidth = 0.07F,
        clickableHeight = 0.07F,
        clickableOffset = IntOffset(380, 220),
        navController = navController,
        onClick = {showPhone = true})

    //"Le cobalt (présent dans les batteries de nos téléphones portables) et l'uranium sont toutes deux des ressources extraites sur le continent africain.\n" +
    //                        "L'extraction de cobalt a lieu dans des mines où la teneur en uranium est souvent élevée, et donc très dangereuse et hautement toxique.\n" +
    //                        "Un téléphone à touches est souvent pratique pour convertir des lettres en chiffres."
    //zoom sur téléphone
    if (showPhone) {
        val scrollState = rememberScrollState()
        val texte1 = "Le cobalt (présent dans les batteries de nos téléphones portables) et l'uranium sont toutes deux des ressources extraites sur le continent africain"
        val texte2 = "L'extraction de cobalt a lieu dans des mines où la teneur en uranium est souvent élevée, et donc très dangereuse et hautement toxique."
        val texte3 = "Un téléphone à touches est souvent pratique pour convertir des lettres en chiffres."

        AlertDialog(
            onDismissRequest = { showPhone = false},
            text = {
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Text(text = texte1)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = texte2)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = texte3)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.telephone),
                        contentDescription = "Indice Telephone",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                    )
                }
            },
            confirmButton = { Button(onClick = { showPhone = false }) { Text(text = "Fermer") } }
        )
    }

    //coussin amovible
    MoveableCoussin()

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

    var enigme_uranium by remember { mutableStateOf(false) }
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_entree),
                contentScale = ContentScale.FillBounds)
    }
    )

    //click uranium
    ClickElement(
        clickableWidth = 0.07F,
        clickableHeight = 0.12F,
        clickableOffset = IntOffset(250, 85),
        navController = navController,
        onClick = {enigme_uranium = true})

    //zoom sur les livres
    if (enigme_uranium) {
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { enigme_uranium = false },
            title = { Text("Entrez le bon code !") },
            text = {
                Column(modifier = Modifier.padding(all = 16.dp)) {
                    TextField(
                        value = code,
                        onValueChange = { code = it },
                        label = { Text("Uranium et nucléaire") },
                        keyboardOptions = KeyboardOptions.run { Default.copy(keyboardType = KeyboardType.Text) }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        //fonction qui affiche ou non le dilemme en fonction du code trouvé ou non
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_uranium = false }
                ) {
                    Text("Annuler")
                }
            }
        )
    }

    //portes amovible
    MoveablePorteHG()
    MoveablePorteHD()
    MoveablePorteBG()
    MoveablePorteBD()

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
    var showBooks by remember { mutableStateOf(false) }
    var tab_periodique by remember { mutableStateOf(false) }
    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_gauche),
                contentScale = ContentScale.FillBounds)
    }
    )
    //click livres
    ClickElement(
        clickableWidth = 0.19F,
        clickableHeight = 0.18F,
        clickableOffset = IntOffset(200, 90),
        navController = navController,
        onClick = { showBooks = true })

    //zoom sur les livres
    if (showBooks) {
        AlertDialog(
            onDismissRequest = { showBooks = false },
            text = { ClickElement(clickableWidth = 0.2F,
                                    clickableHeight = 0.7F,
                                    clickableOffset = IntOffset(60, 10),
                                    navController = navController,
                                    onClick = {tab_periodique = true})
                    Image(painter = painterResource(id = R.drawable.livres), contentDescription = "Livres Enigme Uranium")
           },
            confirmButton = {
                TextButton(onClick = { showBooks = false }, modifier = Modifier) {
                    Text("Fermer")
                }
            }

        )
    }

    //zoom tableau périodique
    if (tab_periodique) {
        AlertDialog(
            onDismissRequest = { tab_periodique = false },
            text = { Image(painter = painterResource(id = R.drawable.livre_uranium), contentDescription = "Livre Enigme Uranium")
            },
            confirmButton = {
                TextButton(onClick = { tab_periodique = false ; showBooks = false},
                modifier = Modifier) {
                    Text("Fermer")
                }
            }
        )
    }

    //Boutons de navigation entre les murs
    ToNextRightWall(modifier = modifier, navController =  navController, onClick = {onDisplayChangeToRight(!isDisplayedRight)})
    ToNextLeftWall(modifier = modifier, navController = navController, onClick = {onDisplayChangeToLeft(!isDisplayedLeft)} )

}

@Composable
fun MoveableCoussin() {
    var offsetX by remember { mutableStateOf(950f) }
    var offsetY by remember { mutableStateOf(470f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x
                    offsetY += change.positionChange().y
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.coussin),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun MoveablePorteHG() {
    var offsetX by remember { mutableStateOf(497f) }
    var offsetY by remember { mutableStateOf(214f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x
                    offsetY += change.positionChange().y
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.porte_haut_gauche),
            contentDescription = null,
            modifier = Modifier.size(55.dp)
        )
    }
}

@Composable
fun MoveablePorteHD() {
    var offsetX by remember { mutableStateOf(825f) }
    var offsetY by remember { mutableStateOf(214f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x
                    offsetY += change.positionChange().y
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.porte_haut_droite),
            contentDescription = null,
            modifier = Modifier.size(53.dp)
        )
    }
}

@Composable
fun MoveablePorteBD() {
    var offsetX by remember { mutableStateOf(822f) }
    var offsetY by remember { mutableStateOf(640f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x
                    offsetY += change.positionChange().y
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.porte_bas_droite),
            contentDescription = null,
            modifier = Modifier.size(54.dp)
        )
    }
}

@Composable
fun MoveablePorteBG() {
    var offsetX by remember { mutableStateOf(495f) }
    var offsetY by remember { mutableStateOf(640f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x
                    offsetY += change.positionChange().y
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.porte_bas_gauche),
            contentDescription = null,
            modifier = Modifier.size(55.dp)
        )
    }
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
            .clickable(onClick = onClick)
    )
}




package com.example.escapegame
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.escapegame.entree_jeu.*
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

    //click sur telephone adaptatif
    ClickElement(
        clickableWidthPercent = 0.07F,
        clickableHeightPercent = 0.07F,
        clickableOffsetPercent = Offset(0.52F, 0.62F),
        navController = navController,
        onClick = {showPhone = true})

    //zoom sur téléphone
    if (showPhone){
        // Popup contenant les livre avec drapeaux
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.indice_uranium_portable),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showPhone = false }
                    )
                }
            }
        }
    }

    //coussin amovible
    MoveableCoussin(clickableOffsetPercent = Offset(0.485F, 0.5F))

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

    var showTableau by remember { mutableStateOf(false) }
    var showLivre by remember { mutableStateOf(false) }
    var showContenuLivre by remember { mutableStateOf(false)}
    var showAfriqueSud by remember { mutableStateOf(false)}
    var showAngola by remember { mutableStateOf(false)}
    var showCongo by remember { mutableStateOf(false)}
    var showEgypte by remember { mutableStateOf(false)}
    var showMauritanie by remember { mutableStateOf(false)}
    var showMozambique by remember { mutableStateOf(false)}
    var showSoudan by remember { mutableStateOf(false)}
    var showTchad by remember { mutableStateOf(false)}

    var showBottle by remember { mutableStateOf(false) }

    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_droite),
                contentScale = ContentScale.FillBounds)
    }
    )
    // click sur tableau cartes des drapeaux
    ClickElement(
        clickableWidthPercent = 0.16F,
        clickableHeightPercent = 0.28F,
        clickableOffsetPercent = Offset(0.56f, 0.28f),
        navController = navController,
        onClick = {showTableau = true})

    //zoom sur tableau
    if (showTableau) {
        val scrollState = rememberScrollState()
        val texte1 = "Les diamants, source de conflits, sont l'une des ressources les plus recherchées en Afrique."
        val texte2 = "Les principaux pays producteurs sont : le Sud du Mozambique, le Sud de l'Afrique du Sud, le Nord de l'Angola et l'Ouest du Soudan."

        AlertDialog(
            onDismissRequest = { showTableau = false },
            text = {
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Text(text = texte1)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = texte2)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.carte_drapeaux),
                        contentDescription = "Carte drapeaux",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(8.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showTableau = false }, modifier = Modifier) {
                    Text("Fermer")
                }
            }

        )
    }

    // click sur grand livre des drapeaux
    ClickElement(
        clickableWidthPercent = 0.14F,
        clickableHeightPercent = 0.07F,
        clickableOffsetPercent = Offset(0.36f, 0.7f),
        navController = navController,
        onClick = {showLivre = true})


    //zoom sur le livre
    if (showLivre) {
        AlertDialog(
            onDismissRequest = { showLivre = false },
            text = { ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0f, 0f),
                navController = navController,
                onClick = {showContenuLivre = true ; showLivre = false})
                Image(painter = painterResource(id = R.drawable.livre_drapeaux), contentDescription = "Livres Enigme Uranium")
            },
            confirmButton = {
                TextButton(onClick = { showLivre = false }, modifier = Modifier) {
                    Text("Fermer")
                }
            }

        )
    }

    //affichage du contenu livre drapeaux
    if (showContenuLivre){
        // Popup contenant les livre avec drapeaux
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_ouvert),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showContenuLivre = false }
                    )
                }
            }

            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

        }
    }

    if (showAfriqueSud){
        // Pop contenant les règle du jeu
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //background avec image
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_a_du_sud),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showAngola = false ; showContenuLivre = false }
                    )
                }
            }

            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showAfriqueSud = false ; showContenuLivre = false }
                    )
                }
            }

        }
    }

    if (showAngola){
        // Pop contenant les règle du jeu
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_angola),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showAngola = false ; showContenuLivre = false }
                    )
                }
            }

            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

        }
    }

    if (showCongo){
        // Pop contenant les règle du jeu
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_congo),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showCongo = false ; showContenuLivre = false }
                    )
                }
            }

            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

        }
    }

    if (showEgypte){
        // Pop contenant les règle du jeu
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_egypte),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showEgypte = false ; showContenuLivre = false }
                    )
                }
            }

            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

        }
    }

    if (showMauritanie){
        // Pop contenant les règle du jeu
        Popup() {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_mauritanie),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showMauritanie = false ; showContenuLivre = false }
                    )
                }
            }
            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

        }
    }

    if (showMozambique){
        // Pop contenant les règle du jeu
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_mozambique),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showMozambique = false ; showContenuLivre = false }
                    )
                }
            }
            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

        }
    }

    if (showSoudan){
        // Pop contenant les règle du jeu
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_soudan),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showSoudan = false ; showContenuLivre = false }
                    )
                }
            }
            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})

        }
    }

    if (showTchad){
        // Pop contenant les règle du jeu
        Popup() {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_drapeaux_tchad),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showTchad = false ; showContenuLivre = false }
                    )
                }
            }
            //click Afrique du Sud
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.72F, 0.775F),
                navController = navController,
                onClick = { showAfriqueSud = true; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Angola
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.698F, 0.59F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = true; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false })

            //click Congo
            ClickElement(
                clickableWidthPercent = 0.1F,
                clickableHeightPercent = 0.15F,
                clickableOffsetPercent= Offset(0.73F, 0.45F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = true ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Egypte
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.12F,
                clickableOffsetPercent= Offset(0.78F, 0.127F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = true; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mauritanie
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.14F,
                clickableOffsetPercent= Offset(0.52F, 0.17F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = true; showMozambique = false ; showSoudan = false ; showTchad = false})

            //click Mozambique
            ClickElement(
                clickableWidthPercent = 0.07F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.82F, 0.648F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = true ; showSoudan = false ; showTchad = false})

            //click Soudan
            ClickElement(
                clickableWidthPercent = 0.09F,
                clickableHeightPercent = 0.2F,
                clickableOffsetPercent= Offset(0.773F, 0.248F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = true ; showTchad = false})

            //click Tchad
            ClickElement(
                clickableWidthPercent = 0.06F,
                clickableHeightPercent = 0.18F,
                clickableOffsetPercent= Offset(0.71F, 0.24F),
                navController = navController,
                onClick = { showAfriqueSud = false; showAngola = false; showCongo = false ; showEgypte = false; showMauritanie = false; showMozambique = false ; showSoudan = false ; showTchad = true})
        }
    }

    //click sur la bouteille d'eeau
    ClickElement(
        clickableWidthPercent = 0.05F,
        clickableHeightPercent = 0.17F,
        clickableOffsetPercent = Offset(0.37F, 0.49F),
        navController = navController,
        onClick = {showBottle = true})

    if (showBottle){
        // Pop contenant le labyrinthe
        Popup() {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Fond flou
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 0f,
                                    endY = size.height
                                )
                            )
                        },
                    content = {
                        // Contenu de la popup
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            content = {
                                Box(
                                    modifier = with(Modifier) {
                                        fillMaxSize()
                                            .paint(
                                                // Remplacez par votre id d'image
                                                painterResource(id = R.drawable.etiquette_bouteille),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                            }
                        )
                    }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showBottle = false}
                    )
                }
            }
        }
    }



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
    //enigme uranium
    var enigme_uranium by remember { mutableStateOf(false) }
    var passwordErrorUranium by remember{ mutableStateOf(false) }
    var showDilemmeUraniumRecto by remember { mutableStateOf(false) }
    var showDilemmeUraniumVerso by remember { mutableStateOf(false) }
    var code_uranium_trouve by remember { mutableStateOf(false) }

    var enigme_diamant by remember { mutableStateOf(false) }
    var passwordErrorDiamant by remember{ mutableStateOf(false) }
    var showDilemmeDiamant by remember { mutableStateOf(false) }
    var code_diamant_trouve by remember { mutableStateOf(false) }

    var enigme_eau by remember { mutableStateOf(false) }
    var passwordErrorEau by remember{ mutableStateOf(false) }
    var showDilemmeEau by remember { mutableStateOf(false) }
    var code_eau_trouve by remember { mutableStateOf(false) }


    
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
        clickableWidthPercent = 0.07F,
        clickableHeightPercent = 0.12F,
        clickableOffsetPercent = Offset(0.34F, 0.24F),
        navController = navController,
        onClick = {
            if (code_uranium_trouve) { showDilemmeUraniumRecto = true }
            else { enigme_uranium = true }})

    //popup enigme uranium
    if (enigme_uranium) {
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "Uranium et nucléaire"

        AlertDialog(
            onDismissRequest = { enigme_uranium = false },
            title = { Text(text="Entrez le bon code !", textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "L'Uranium permet la production de l'énergie nucléaire.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorUranium = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorUranium,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorUranium){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "8892"){
                            passwordErrorUranium = false
                            showDilemmeUraniumRecto = true
                            enigme_uranium = false
                            code_uranium_trouve = true

                        } else {
                            passwordErrorUranium = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_uranium = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }

    //popups dilemme uranium
    if (showDilemmeUraniumRecto){
        // Popup contenant le recto du dilemme uranium
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeUraniumRecto = false
                    showDilemmeUraniumVerso = true}
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Fond flou
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 0f,
                                    endY = size.height
                                )
                            )
                        },
                    content = {
                        // Contenu de la popup
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            content = {
                                Box(
                                    modifier = with(Modifier) {
                                        fillMaxSize()
                                            .paint(
                                                // Remplacez par votre id d'image
                                                painterResource(id = R.drawable.d_uranium_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                            }
                        )
                    }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showDilemmeUraniumRecto = false}
                    )
                }
            }
        }
    }

    if (showDilemmeUraniumVerso){
        // Popup contenant le verso du dilemme uranium
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeUraniumRecto = true ; showDilemmeUraniumVerso = false}
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Fond flou
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 0f,
                                    endY = size.height
                                )
                            )
                        },
                    content = {
                        // Contenu de la popup
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            content = {
                                Box(
                                    modifier = with(Modifier) {
                                        fillMaxSize()
                                            .paint(
                                                // Remplacez par votre id d'image
                                                painterResource(id = R.drawable.d_uranium_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                            }
                        )
                    }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showDilemmeUraniumVerso = false ; enigme_uranium = false}
                    )
                }
            }
        }
    }

    //click diamant
    ClickElement(
        clickableWidthPercent = 0.07F,
        clickableHeightPercent = 0.12F,
        clickableOffsetPercent = Offset(0.26F, 0.395F),
        navController = navController,
        onClick = {
            if (code_diamant_trouve) { showDilemmeDiamant = true }
            else { enigme_diamant = true }})

    //popup enigme diamant
    if (enigme_diamant){
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "Diamants du conflit"

        AlertDialog(
            onDismissRequest = { enigme_diamant = false },
            title = { Text(text="Entrez le bon code !",  textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "Les diamants sont source de conflits armés qui martyrisent les populations locales.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorDiamant = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorDiamant,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorDiamant){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "5814"){
                            passwordErrorDiamant = false
                            showDilemmeDiamant = true
                            enigme_diamant = false
                            code_diamant_trouve = true

                        } else {
                            passwordErrorDiamant = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_diamant = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }

    //popups dilemme diamants
    if (showDilemmeDiamant){
        // Popup contenant le recto du dilemme uranium
        Popup() {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Fond flou
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 0f,
                                    endY = size.height
                                )
                            )
                        },
                    content = {
                        // Contenu de la popup
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            content = {
                                Box(
                                    modifier = with(Modifier) {
                                        fillMaxSize()
                                            .paint(
                                                // Remplacez par votre id d'image
                                                painterResource(id = R.drawable.dilemme_diamants),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                            }
                        )
                    }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showDilemmeDiamant = false}
                    )
                }
            }
        }
    }

    //click eau
    ClickElement(
        clickableWidthPercent = 0.07F,
        clickableHeightPercent = 0.14F,
        clickableOffsetPercent = Offset(0.26F, 0.535F),
        navController = navController,
        onClick = {
            if (code_eau_trouve) { showDilemmeEau = true }
            else { enigme_eau = true } })

    //popup enigme eau
    if (enigme_eau){
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "Accès à l'eau potable"

        AlertDialog(
            onDismissRequest = { enigme_diamant = false },
            title = { Text(text="Entrez le bon code !",  textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "L'eau est abondante en Afrique. Mais son acheminement et son traitement sont insuffisants pour subvenir aux besoins des populations.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorEau = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorEau,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorEau){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "3800"){
                            passwordErrorEau = false
                            showDilemmeEau = true
                            enigme_eau = false
                            code_eau_trouve = true

                        } else {
                            passwordErrorEau = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_eau = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }

    //popups dilemme eau
    if (showDilemmeEau){
        // Popup contenant le dilemme eau
        Popup() {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Fond flou
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 0f,
                                    endY = size.height
                                )
                            )
                        },
                    content = {
                        // Contenu de la popup
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            content = {
                                Box(
                                    modifier = with(Modifier) {
                                        fillMaxSize()
                                            .paint(
                                                // Remplacez par votre id d'image
                                                painterResource(id = R.drawable.d_eau_potable),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                            }
                        )
                    }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showDilemmeEau = false}
                    )
                }
            }
        }
    }

    //portes amovible
    MoveablePorteHG(clickableOffsetPercent = Offset(0.265F, 0.225F))
    MoveablePorteHD(clickableOffsetPercent = Offset(0.435F, 0.225F))
    MoveablePorteBG(clickableOffsetPercent = Offset(0.265F, 0.667F))
    MoveablePorteBD(clickableOffsetPercent = Offset(0.435F, 0.667F))

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
    var showCrayons by remember { mutableStateOf(false) }


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
        clickableWidthPercent = 0.19F,
        clickableHeightPercent = 0.18F,
        clickableOffsetPercent = Offset(0.27F, 0.25F),
        navController = navController,
        onClick = { showBooks = true })

    //click crayons
    ClickElement(
        clickableWidthPercent = 0.08F,
        clickableHeightPercent = 0.15F,
        clickableOffsetPercent = Offset(0.547F, 0.366F),
        navController = navController,
        onClick = { showCrayons = true })

    //zoom sur les livres
    if (showBooks) {
        AlertDialog(
            onDismissRequest = { showBooks = false },
            text = { ClickElement(clickableWidthPercent = 0.04F,
                                    clickableHeightPercent = 0.35F,
                                    clickableOffsetPercent = Offset(0.082F, 0.028F),
                                    navController = navController,
                                    onClick = {tab_periodique = true ; showBooks = false})
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
    if (tab_periodique){
        // Popup contenant les livre avec drapeaux
        Popup() {
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
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.livre_uranium),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {tab_periodique = false ; showBooks = false}
                    )
                }
            }
        }
    }

    //zoom sur les livres
    if (showCrayons){
        // Popup contenant les livre avec drapeaux
        Popup() {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = with (Modifier){
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.crayons),
                            contentScale = ContentScale.FillBounds)
                }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonClosePopup(
                        onClick = {showCrayons = false }
                    )
                }
            }
        }
    }

    //Boutons de navigation entre les murs
    ToNextRightWall(modifier = modifier, navController =  navController, onClick = {onDisplayChangeToRight(!isDisplayedRight)})
    ToNextLeftWall(modifier = modifier, navController = navController, onClick = {onDisplayChangeToLeft(!isDisplayedLeft)} )

}

@Composable
fun MoveableCoussin(
    clickableOffsetPercent: Offset = Offset.Zero
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var offsetX by remember { mutableStateOf((screenWidth * clickableOffsetPercent.x)) }
    var offsetY by remember { mutableStateOf(screenHeight * clickableOffsetPercent.y) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToPx(), offsetY.roundToPx()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x.dp
                    offsetY += change.positionChange().y.dp
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
fun MoveablePorteHG(clickableOffsetPercent: Offset = Offset.Zero) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var offsetX by remember { mutableStateOf((screenWidth * clickableOffsetPercent.x)) }
    var offsetY by remember { mutableStateOf(screenHeight * clickableOffsetPercent.y) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToPx(), offsetY.roundToPx()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x.dp
                    offsetY += change.positionChange().y.dp
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
fun MoveablePorteHD(clickableOffsetPercent: Offset = Offset.Zero) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var offsetX by remember { mutableStateOf((screenWidth * clickableOffsetPercent.x)) }
    var offsetY by remember { mutableStateOf(screenHeight * clickableOffsetPercent.y) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToPx(), offsetY.roundToPx()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x.dp
                    offsetY += change.positionChange().y.dp
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
fun MoveablePorteBD(clickableOffsetPercent: Offset = Offset.Zero) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var offsetX by remember { mutableStateOf((screenWidth * clickableOffsetPercent.x)) }
    var offsetY by remember { mutableStateOf(screenHeight * clickableOffsetPercent.y) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToPx(), offsetY.roundToPx()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x.dp
                    offsetY += change.positionChange().y.dp
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
fun MoveablePorteBG(clickableOffsetPercent: Offset = Offset.Zero) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var offsetX by remember { mutableStateOf((screenWidth * clickableOffsetPercent.x)) }
    var offsetY by remember { mutableStateOf(screenHeight * clickableOffsetPercent.y) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToPx(), offsetY.roundToPx()) }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    offsetX += change.positionChange().x.dp
                    offsetY += change.positionChange().y.dp
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
    clickableWidthPercent: Float = 1F,
    clickableHeightPercent: Float = 1F,
    clickableOffsetPercent: Offset = Offset.Zero,
    navController: NavController,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .size(screenWidth * clickableWidthPercent, screenHeight * clickableHeightPercent)
            .offset(
                x = screenWidth * clickableOffsetPercent.x,
                y = screenHeight * clickableOffsetPercent.y
            )
            .clickable(onClick = onClick)
    )
}

@Composable
fun FloatingButtonClosePopup(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Close"
                )
            }
        )
    }
}


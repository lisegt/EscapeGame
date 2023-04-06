package com.example.escapegame
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.core.net.toUri
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
    var showTabPetrole by remember { mutableStateOf(false) }
    var showJournal by remember { mutableStateOf(false) }
    var showMotsFleches by remember { mutableStateOf(false) }

    val context = LocalContext.current

    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.afrique_fond),
                contentScale = ContentScale.FillBounds)
    }
    )

    //click sur le telephone
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

    //click sur tableau pétrole
    ClickElement(
        clickableWidthPercent = 0.26F,
        clickableHeightPercent = 0.28F,
        clickableOffsetPercent = Offset(0.35F, 0.21F),
        navController = navController,
        onClick = {showTabPetrole = true})

    //zoom sur tableau pétrole
    if (showTabPetrole){
        // Popup contenant les livre avec drapeaux
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
                                                painterResource(id = R.drawable.tableau_total),
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
                        onClick = {showTabPetrole = false }
                    )
                }
            }
        }
    }

    //click sur journal
    ClickElement(
        clickableWidthPercent = 0.1F,
        clickableHeightPercent = 0.1F,
        clickableOffsetPercent = Offset(0.63F, 0.7F),
        navController = navController,
        onClick = { showJournal= true })

    //zoom sur journal
    if (showJournal){
        // Popup contenant le journal le monde
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = with (Modifier){
                        fillMaxSize()
                            .paint(
                                // Replace with your image id
                                painterResource(id = R.drawable.monde_afrique_somalie),
                                contentScale = ContentScale.FillBounds)
                    })

                    ClickElement(
                        clickableWidthPercent = 0.34F,
                        clickableHeightPercent = 0.1F,
                        clickableOffsetPercent = Offset(0.63F, 0.84F),
                        navController = navController,
                        onClick = { showMotsFleches = true ; showJournal = false })
                }
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
                        onClick = {showJournal = false }
                    )
                }
            }
        }
    }

    //mots fléchés
    if (showMotsFleches){
        // Popup contenant les mots fléchés
        Popup() {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = with (Modifier){
                        fillMaxSize()
                            .paint(
                                // Replace with your image id
                                painterResource(id = R.drawable.mots_crois_s),
                                contentScale = ContentScale.FillBounds)
                    })

                    ClickElement(
                        clickableWidthPercent = 0.5F,
                        clickableHeightPercent = 0.07F,
                        clickableOffsetPercent = Offset(0.27F, 0.66F),
                        navController = navController,
                        onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://learningapps.org/watch?v=py2zukgy523".toUri())
                                        context.startActivity(intent) })
                }
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
                        onClick = {showMotsFleches = false }
                    )
                }
            }
        }
    }

    //coussin amovible
    MoveableCoussin(clickableOffsetPercent = Offset(0.485F, 0.5F))

    Button(onClick = {navController.navigate("couloir")}){}

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
    var showBoulette123 by remember { mutableStateOf(false) }
    var showBoulette456 by remember { mutableStateOf(false) }
    var showBoulette789 by remember { mutableStateOf(false) }
    var showJournalP1 by remember { mutableStateOf(false) }
    var showJournalP2 by remember { mutableStateOf(false) }

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

    //click sur la bouteille d'eau
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

    //click sur boulette 123
    ClickElement(
        clickableWidthPercent = 0.04F,
        clickableHeightPercent = 0.08F,
        clickableOffsetPercent = Offset(0.32F, 0.4F),
        navController = navController,
        onClick = {showBoulette123 = true})

    //zoom sur boulette 123
    if (showBoulette123){
        // Popup contenant les livre avec drapeaux
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
                                                painterResource(id = R.drawable.codes_123),
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
                        onClick = {showBoulette123 = false }
                    )
                }
            }
        }
    }

    //click sur boulette 456
    ClickElement(
        clickableWidthPercent = 0.04F,
        clickableHeightPercent = 0.08F,
        clickableOffsetPercent = Offset(0.4F, 0.35F),
        navController = navController,
        onClick = {showBoulette456 = true})

    //zoom sur boulette 456
    if (showBoulette456){
        // Popup contenant les livre avec drapeaux
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
                                                painterResource(id = R.drawable.codes_456),
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
                        onClick = {showBoulette456 = false }
                    )
                }
            }
        }
    }

    //click sur boulette 789
    ClickElement(
        clickableWidthPercent = 0.04F,
        clickableHeightPercent = 0.08F,
        clickableOffsetPercent = Offset(0.48F, 0.39F),
        navController = navController,
        onClick = {showBoulette789 = true})

    //zoom sur boulette 789
    if (showBoulette789){
        // Popup contenant les livre avec drapeaux
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
                                                painterResource(id = R.drawable.codes_789),
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
                        onClick = {showBoulette789 = false }
                    )
                }
            }
        }
    }

    // click sur journal
    ClickElement(
        clickableWidthPercent = 0.14F,
        clickableHeightPercent = 0.07F,
        clickableOffsetPercent = Offset(0.5f, 0.7f),
        navController = navController,
        onClick = { showJournalP1 = true })

    //zoom sur le journal
    if (showJournalP1){
        // Pop contenant le journal
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
                                                painterResource(id = R.drawable.le_monde_pollution_page_1),
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
                        onClick = {showJournalP1 = false}
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ){
                Row() {
                    FloatingButtonNextPopup(
                        onClick = {showJournalP1 = false ; showJournalP2 = true}
                    )
                }
            }
        }
    }

    //zoom sur le journal page 2
    if (showJournalP2){
        // Pop contenant le journal
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
                                                painterResource(id = R.drawable.le_monde_pollution_page_2),
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
                        onClick = {showJournalP2 = false}
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ){
                Row() {
                    FloatingButtonPreviousPopup(
                        onClick = {showJournalP2 = false ; showJournalP1 = true}
                    )
                }
            }
        }
    }

    MoveableMasque1(clickableOffsetPercent = Offset(0.28F, 0.35F))
    MoveableMasque2(clickableOffsetPercent = Offset(0.35F, 0.2F))
    MoveableMasque3(clickableOffsetPercent = Offset(0.42F, 0.3F))

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
    var showDilemmeUranium by remember { mutableStateOf(false) }
    var code_uranium_trouve by remember { mutableStateOf(false) }

    var enigme_diamant by remember { mutableStateOf(false) }
    var passwordErrorDiamant by remember{ mutableStateOf(false) }
    var showDilemmeDiamant by remember { mutableStateOf(false) }
    var code_diamant_trouve by remember { mutableStateOf(false) }

    var enigme_eau by remember { mutableStateOf(false) }
    var passwordErrorEau by remember{ mutableStateOf(false) }
    var showDilemmeEau by remember { mutableStateOf(false) }
    var code_eau_trouve by remember { mutableStateOf(false) }

    var enigme_gaz by remember { mutableStateOf(false) }
    var passwordErrorGaz by remember{ mutableStateOf(false) }
    var showDilemmeGaz by remember { mutableStateOf(false) }
    var code_gaz_trouve by remember { mutableStateOf(false) }

    var enigme_er by remember { mutableStateOf(false) }
    var passwordErrorER by remember{ mutableStateOf(false) }
    var showDilemmeER by remember { mutableStateOf(false) }
    var code_er_trouve by remember { mutableStateOf(false) }

    var enigme_pollution by remember { mutableStateOf(false) }
    var passwordErrorPollution by remember{ mutableStateOf(false) }
    var showDilemmePollution by remember { mutableStateOf(false) }
    var code_pollution_trouve by remember { mutableStateOf(false) }

    var enigme_sec by remember { mutableStateOf(false) }
    var passwordErrorSec by remember{ mutableStateOf(false) }
    var showDilemmeSec by remember { mutableStateOf(false) }
    var code_sec_trouve by remember { mutableStateOf(false) }


    var enigme_sortie by remember { mutableStateOf(false) }
    var passwordErrorSortie by remember{ mutableStateOf(false) }
    var showCouloirSalleConseil by remember { mutableStateOf(false) }
    var code_sortie_trouve by remember { mutableStateOf(false) }

    
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
            if (code_uranium_trouve) { showDilemmeUranium = true }
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
                            showDilemmeUranium = true
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
    if (showDilemmeUranium){
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
                                                painterResource(id = R.drawable.d_uranium),
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
                        onClick = {showDilemmeUranium = false}
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
            onDismissRequest = { enigme_eau = false },
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

    //click gaz
    ClickElement(
        clickableWidthPercent = 0.07F,
        clickableHeightPercent = 0.14F,
        clickableOffsetPercent = Offset(0.43F, 0.53F),
        navController = navController,
        onClick = {
            if (code_gaz_trouve) { showDilemmeGaz = true }
            else { enigme_gaz = true } })

    //popup enigme gaz
    if (enigme_gaz){
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "Les ressources en gaz"

        AlertDialog(
            onDismissRequest = { enigme_gaz = false },
            title = { Text(text="Entrez le bon code !",  textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "Les ressources fossiles, en particulier le gaz, sont nombreuses en Afrique.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorGaz = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorGaz,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorGaz){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "3422"){
                            passwordErrorGaz = false
                            showDilemmeGaz = true
                            enigme_gaz = false
                            code_gaz_trouve = true

                        } else {
                            passwordErrorGaz = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_gaz = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }

    //popups dilemme gaz
    if (showDilemmeGaz){
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
                                                painterResource(id = R.drawable.d_gaz),
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
                        onClick = {showDilemmeGaz = false}
                    )
                }
            }
        }
    }

    //click énergies renouvelables
    ClickElement(
        clickableWidthPercent = 0.08F,
        clickableHeightPercent = 0.14F,
        clickableOffsetPercent = Offset(0.34F, 0.38F),
        navController = navController,
        onClick = {
            if (code_er_trouve) { showDilemmeER = true }
            else { enigme_er = true } })

    //popup enigme énergies renouvelables
    if (enigme_er){
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "L'énergie solaire"

        AlertDialog(
            onDismissRequest = { enigme_er = false },
            title = { Text(text="Entrez le bon code !",  textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "Le continent africain est le plus ensoleillé. C'est une source inépuisable d'énergie renouvelable.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorER = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorER,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorER){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "6395"){
                            passwordErrorER = false
                            showDilemmeER = true
                            enigme_er = false
                            code_er_trouve = true

                        } else {
                            passwordErrorER = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_er = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }

    //popups dilemme énergies renouvelables
    if (showDilemmeER){
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
                                                painterResource(id = R.drawable.d_solaire),
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
                        onClick = {showDilemmeER = false}
                    )
                }
            }
        }
    }

    //click pollution
    ClickElement(
        clickableWidthPercent = 0.08F,
        clickableHeightPercent = 0.14F,
        clickableOffsetPercent = Offset(0.34F, 0.535F),
        navController = navController,
        onClick = {
            if (code_pollution_trouve) { showDilemmePollution = true }
            else { enigme_pollution = true } })

    //popup enigme pollution
    if (enigme_pollution){
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "La pollution industrielle"

        AlertDialog(
            onDismissRequest = { enigme_pollution = false },
            title = { Text(text="Entrez le bon code !",  textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "La pollution industrielle, due en partie aux transports routiers, cause des dégâts énormes sur la santé de la population, la biodiversité et les sols.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorPollution = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorPollution,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorPollution){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "3358"){
                            passwordErrorPollution = false
                            showDilemmePollution = true
                            enigme_pollution = false
                            code_pollution_trouve = true

                        } else {
                            passwordErrorPollution = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_pollution = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }

    //popups dilemme pollution
    if (showDilemmePollution){
        // Popup contenant le dilemme pollution
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
                                                painterResource(id = R.drawable.d_pollution),
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
                        onClick = {showDilemmePollution = false}
                    )
                }
            }
        }
    }

    //click secheresse
    ClickElement(
        clickableWidthPercent = 0.08F,
        clickableHeightPercent = 0.14F,
        clickableOffsetPercent = Offset(0.34F, 0.68F),
        navController = navController,
        onClick = {
            if (code_sec_trouve) { showDilemmeSec = true }
            else { enigme_sec = true } })

    //popup enigme secheresse
    if (enigme_sec){
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "Impact des sécheresses"

        AlertDialog(
            onDismissRequest = { enigme_sec = false },
            title = { Text(text="Entrez le bon code !",  textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "Les sécheresses, de plus en plus récurrentes et importantes, diminuent le rendement des récoltes.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorSec = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorSec,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorSec){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "1953"){
                            passwordErrorSec = false
                            showDilemmeSec = true
                            enigme_sec = false
                            code_sec_trouve = true

                        } else {
                            passwordErrorSec = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_sec = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }

    //popups dilemme secheresse
    if (showDilemmeSec){
        // Popup contenant le dilemme secheresse
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
                                                painterResource(id = R.drawable.d_secheresse),
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
                        onClick = {showDilemmeSec = false}
                    )
                }
            }
        }
    }


    //click sortie
    ClickElement(
        clickableWidthPercent = 0.05F,
        clickableHeightPercent = 0.1F,
        clickableOffsetPercent = Offset(0.635F, 0.5F),
        navController = navController,
        onClick = { enigme_sortie = true })

    //popup enigme sortie
    if (enigme_sortie){
        // Créer des variables d'état pour stocker les données du formulaire
        var code by remember { mutableStateOf("") }
        var label = "Courez en salle du conseil !"

        AlertDialog(
            onDismissRequest = { enigme_sortie = false },
            title = { Text(text="Ze final code !",  textAlign = TextAlign.Center)},
            text = {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(text = "")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { passwordErrorSortie = false; code = it },
                        label = { Text(label) },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorSortie,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (passwordErrorSortie){
                        Text(text = "Code invalide")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (code == "ecologie"){
                            passwordErrorSortie = false
                            showCouloirSalleConseil = true
                            enigme_sortie = false

                        } else {
                            passwordErrorSortie = true
                        }
                    }
                ) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(
                    onClick = { enigme_sortie = false }
                ) {
                    Text("Fermer")
                }
            }
        )
    }
    //sortie de la salle afrique
    if (showCouloirSalleConseil){
        navController.navigate("couloir")
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
    var showGlobe by remember { mutableStateOf(false) }
    var showCarteSolaire by remember { mutableStateOf(false) }
    var showOrdi by remember { mutableStateOf(false) }


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
        // Popup contenant les crayons
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

    //click sur globe
    ClickElement(
        clickableWidthPercent = 0.09F,
        clickableHeightPercent = 0.2F,
        clickableOffsetPercent = Offset(0.64F, 0.3F),
        navController = navController,
        onClick = {showGlobe = true})

    //zoom sur socle globe
    if (showGlobe){
        // Popup contenant les livre avec drapeaux
        Popup() {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Fond flou
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
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
                                                painterResource(id = R.drawable.socle_mape_monde),
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
                        onClick = {showGlobe = false }
                    )
                }
            }
        }
    }

    //click sur carte solaire
    ClickElement(
        clickableWidthPercent = 0.12F,
        clickableHeightPercent = 0.07F,
        clickableOffsetPercent = Offset(0.48F, 0.56F),
        navController = navController,
        onClick = {showCarteSolaire = true})

    //zoom sur carte solaire
    if (showCarteSolaire){
        // Popup contenant les livre avec drapeaux
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
                                                painterResource(id = R.drawable.centrale_solaire_senegal),
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
                        onClick = {showCarteSolaire = false }
                    )
                }
            }
        }
    }

    //click sur ordi
    ClickElement(
        clickableWidthPercent = 0.12F,
        clickableHeightPercent = 0.2F,
        clickableOffsetPercent = Offset(0.35F, 0.45F),
        navController = navController,
        onClick = {showOrdi = true})

    //zoom sur ordi
    if (showOrdi){
        // Popup contenant les livre avec drapeaux
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
                                                painterResource(id = R.drawable.indice_ordi),
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
                        onClick = {showOrdi = false }
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
fun MoveableMasque1(clickableOffsetPercent: Offset = Offset.Zero) {
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
            painter = painterResource(R.drawable.masque_1),
            contentDescription = null,
            modifier = Modifier.size(90.dp)
        )
    }
}

@Composable
fun MoveableMasque2(clickableOffsetPercent: Offset = Offset.Zero) {
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
            painter = painterResource(R.drawable.masque_2),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun MoveableMasque3(clickableOffsetPercent: Offset = Offset.Zero) {
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
            painter = painterResource(R.drawable.masque_3),
            contentDescription = null,
            modifier = Modifier.size(105.dp)
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

@Composable
fun FloatingButtonNextPopup(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.forward),
                    contentDescription = "Next Page"
                )
            }
        )
    }
}

@Composable
fun FloatingButtonPreviousPopup(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.backward),
                    contentDescription = "Previous Page"
                )
            }
        )
    }
}

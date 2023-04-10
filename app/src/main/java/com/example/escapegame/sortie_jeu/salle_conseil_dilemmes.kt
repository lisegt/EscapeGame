package com.example.escapegame
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.compose.material.MaterialTheme
import com.example.escapegame.ui.theme.*

@Composable
fun salleConseilDilemmes(
    modifier: Modifier,
    navController: NavController,
    onClick: () -> Unit
){

    var showDilemmeDiamant_recto by remember { mutableStateOf(false) }
    var showDilemmeDiamant_verso by remember { mutableStateOf(false) }

    var showDilemmeEau_recto by remember { mutableStateOf(false) }
    var showDilemmeEau_verso by remember { mutableStateOf(false) }

    var showDilemmeUranium_recto by remember { mutableStateOf(false) }
    var showDilemmeUranium_verso by remember { mutableStateOf(false) }

    var showDilemmeER_recto by remember { mutableStateOf(false) }
    var showDilemmeER_verso by remember { mutableStateOf(false) }

    var showDilemmePollution_recto by remember { mutableStateOf(false) }
    var showDilemmePollution_verso by remember { mutableStateOf(false) }

    var showDilemmeGaz_recto by remember { mutableStateOf(false) }
    var showDilemmeGaz_verso by remember { mutableStateOf(false) }

    var showDilemmeDechets_recto by remember { mutableStateOf(false) }
    var showDilemmeDechets_verso by remember { mutableStateOf(false) }

    var showDilemmeSecheresse_recto by remember { mutableStateOf(false) }
    var showDilemmeSecheresse_verso by remember { mutableStateOf(false) }

    val context = LocalContext.current

    //background avec image
    Box(modifier = with (Modifier){
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.salle_conseil_dilemmes),
                contentScale = ContentScale.FillBounds)
    }
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ){
        Row() {
            Column() {
                FloatingButtonHome(
                    onClick = { navController.navigate("accueil")}
                )
                FloatingButtonSend(
                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://docs.google.com/forms/d/e/1FAIpQLSfn_cw4jcmauDGwhlAurKYwFXasfpbsAEdiPuVwEFOBFxH9Wg/viewform".toUri())
                        context.startActivity(intent) }
                )
            }

        }

    }



    //click sur le dilemme diamant
    ClickElement(
        clickableWidthPercent = 0.1F,
        clickableHeightPercent = 0.23F,
        clickableOffsetPercent = Offset(0.065F, 0.38F),
        navController = navController,
        onClick = {showDilemmeDiamant_recto = true})

    //popup dilemme diamant
    if (showDilemmeDiamant_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_diamants_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )

                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = { showDilemmeDiamant_recto = false
                                                showDilemmeDiamant_verso = true}
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
                        onClick = {showDilemmeDiamant_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeDiamant_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_diamants_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )

                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = { showDilemmeDiamant_verso = false
                                                showDilemmeDiamant_recto = true}
                                )
                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.13F,
                                    clickableOffsetPercent = Offset(0.15F, 0.26F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://classe-internationale.com/2019/09/26/les-diamants-du-sang-symbole-du-lien-entre-conflits-et-ressources-en-afrique-2/#:~:text=Selon%20la%20d%C3%A9finition%20retenue%20par,gouvernements%20l%C3%A9gitimes%20%C2%BB%20%5B2%5D.".toUri())
                                                context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.415F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.amnesty.org/fr/latest/news/2007/01/les-diamants-du-sang-une-rc3a9alitc3a9-toujours-d039actualitc3a9-20070123/".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.05F,
                                    clickableOffsetPercent = Offset(0.15F, 0.52F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.amnesty.fr/focus/le-processus-de-kimberley".toUri())
                                        context.startActivity(intent) })

                                //source 4
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.59F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.lemonde.fr/afrique/article/2018/08/28/diamants-de-sang-pourquoi-il-faut-reformer-le-processus-de-kimberley_5346971_3212.html".toUri())
                                        context.startActivity(intent) })

                                //source 5
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.09F,
                                    clickableOffsetPercent = Offset(0.15F, 0.7F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.letemps.ch/economie/diamants-durables-lecologie-primetelle-lethique".toUri())
                                        context.startActivity(intent) })

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/hLeDex2BNareCzPK8".toUri())
                                        context.startActivity(intent) })
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
                        onClick = {showDilemmeDiamant_verso = false }
                    )
                }
            }
        }
    }

    //click sur le dilemme eau
    ClickElement(
        clickableWidthPercent = 0.045F,
        clickableHeightPercent = 0.18F,
        clickableOffsetPercent = Offset(0.185F, 0.5F),
        navController = navController,
        onClick = { showDilemmeEau_recto = true})

    //popup dilemme eau
    if (showDilemmeEau_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_eau_potable_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeEau_recto = false
                                        showDilemmeEau_verso = true}
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
                        onClick = {showDilemmeEau_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeEau_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_eau_potable_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )

                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeEau_verso = false
                                        showDilemmeEau_recto = true}
                                )

                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.13F,
                                    clickableOffsetPercent = Offset(0.15F, 0.26F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.lepoint.fr/monde/en-afrique-les-infrastructures-d-eau-font-toujours-cruellement-defaut-17-04-2015-1922289_24.php#11".toUri())
                                        context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.15F, 0.42F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.lemonde.fr/culture/article/2012/09/11/nestle-et-le-business-de-l-eau-en-bouteille_1757464_3246.html".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.165F,
                                    clickableOffsetPercent = Offset(0.15F, 0.545F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://youtu.be/YBOKZ86VmGg".toUri())
                                        context.startActivity(intent) })

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/cRwfYF2TV6gvAAEm9".toUri())
                                        context.startActivity(intent)})
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
                        onClick = {showDilemmeEau_verso = false }
                    )
                }
            }
        }
    }

    //click sur le dilemme uranium
    ClickElement(
        clickableWidthPercent = 0.11F,
        clickableHeightPercent = 0.155F,
        clickableOffsetPercent = Offset(0.25F, 0.63F),
        navController = navController,
        onClick = { showDilemmeUranium_recto = true })

    //popup dilemme uranium
    if (showDilemmeUranium_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_uranium_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeUranium_recto = false
                                        showDilemmeUranium_verso = true}
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
                        onClick = {showDilemmeUranium_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeUranium_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_uranium_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeUranium_verso = false
                                        showDilemmeUranium_recto = true}
                                )

                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.13F,
                                    clickableOffsetPercent = Offset(0.15F, 0.29F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.cairn.info/revue-politique-etrangere-2013-2-page-82.htm".toUri())
                                        context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.43F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.jeuneafrique.com/1330583/economie/mines-les-etats-africains-ont-pris-conscience-de-leur-pouvoir/".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.2F,
                                    clickableOffsetPercent = Offset(0.15F, 0.54F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.iaea.org/sites/default/files/32305082933_fr.pdf".toUri())
                                        context.startActivity(intent) })

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/jPMHgA5dtFyZBjEw9".toUri())
                                        context.startActivity(intent) })
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
                        onClick = {showDilemmeUranium_verso = false }
                    )
                }
            }
        }
    }

    //click sur le dilemme energies renouvelables
    ClickElement(
        clickableWidthPercent = 0.13F,
        clickableHeightPercent = 0.2F,
        clickableOffsetPercent = Offset(0.37F, 0.72F),
        navController = navController,
        onClick = { showDilemmeER_recto = true })

    //popup dilemme energies renouvelables
    if (showDilemmeER_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_solaire_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeER_recto = false
                                        showDilemmeER_verso = true}
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
                        onClick = {showDilemmeER_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeER_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_solaire_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeER_verso = false
                                        showDilemmeER_recto = true}
                                )

                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.27F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.dw.com/fr/%C3%A9nergie-solaire-potentiel-afrique/a-63418733".toUri())
                                        context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.12F,
                                    clickableOffsetPercent = Offset(0.15F, 0.39F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://afrique.latribune.fr/think-tank/tribunes/2022-06-27/l-energie-solaire-facteur-d-unification-de-l-afrique-923394.html".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.53F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.otovo.fr/blog/le-solaire-et-vous/fabrication-panneau-solaire/".toUri())
                                        context.startActivity(intent) })

                                //source 4
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.64F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.greenpeace.fr/impact-environnemental-solaire/#:~:text=La%20tr%C3%A8s%20grande%20majorit%C3%A9%20des,selon%20les%20mod%C3%A8les%2C%20du%20plastique".toUri())
                                        context.startActivity(intent) })

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/QzzT7sirWJEH9u8d6".toUri())
                                        context.startActivity(intent) })
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
                        onClick = {showDilemmeER_verso = false }
                    )
                }
            }
        }
    }

    //click sur le dilemme pollution
    ClickElement(
        clickableWidthPercent = 0.115F,
        clickableHeightPercent = 0.23F,
        clickableOffsetPercent = Offset(0.65F, 0.68F),
        navController = navController,
        onClick = { showDilemmePollution_recto = true })

    //popup dilemme energies pollution
    if (showDilemmePollution_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_pollution_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmePollution_recto = false
                                        showDilemmePollution_verso = true}
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
                        onClick = {showDilemmePollution_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmePollution_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_pollution_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmePollution_verso = false
                                        showDilemmePollution_recto = true}
                                )

                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.29F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.lemonde.fr/afrique/article/2019/11/29/en-afrique-de-l-ouest-une-pollution-mortelle-mais-d-ampleur-inconnue_6021103_3212.html".toUri())
                                        context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.4F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://afrique.latribune.fr/entreprises/industrie/energie-environnement/2020-03-06/un-continent-qui-etouffe-sous-la-pollution-urbaine-841312.html".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.52F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.francetvinfo.fr/monde/afrique/environnement-africain/pollution-seuls-7-pays-en-afrique-disposent-d-une-surveillance-fiable-de-la-qualite-de-l-air_5145685.html".toUri())
                                        context.startActivity(intent) })

                                //source 4
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.64F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://information.tv5monde.com/afrique/afrique-de-l-ouest-quels-sont-les-reseaux-de-transports-publics-proposes-aux-habitants".toUri())
                                        context.startActivity(intent)})

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = {val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/xK4YV7CJrLP9SJPm6".toUri())
                                        context.startActivity(intent) })
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
                        onClick = {showDilemmePollution_verso = false }
                    )
                }
            }
        }
    }

    //click sur le dilemme gaz
    ClickElement(
        clickableWidthPercent = 0.06F,
        clickableHeightPercent = 0.17F,
        clickableOffsetPercent = Offset(0.8F, 0.45F),
        navController = navController,
        onClick = { showDilemmeGaz_recto = true })

    //popup dilemme gaz
    if (showDilemmeGaz_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_gaz_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeGaz_recto = false
                                        showDilemmeGaz_verso = true}
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
                        onClick = {showDilemmeGaz_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeGaz_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_gaz_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeGaz_verso = false
                                        showDilemmeGaz_recto = true}
                                )

                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.065F,
                                    clickableOffsetPercent = Offset(0.15F, 0.27F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://storymaps.arcgis.com/stories/62eed2603dbe419ab3d4eedcf86cdda9".toUri())
                                        context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.11F,
                                    clickableOffsetPercent = Offset(0.15F, 0.37F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://afrique.le360.ma/autres-pays/economie/2022/09/29/39464-egypte-importantes-decouvertes-de-gaz-en-mediterranee-et-dans-le-delta-du-nil-39464/".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.52F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://au.int/sites/default/files/documents/41078-doc-1_Le_Gaz_Naturel_dans_le_Paysage_Energetique_Africain_25-10-2021.pdf".toUri())
                                        context.startActivity(intent) })

                                //source 4
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.63F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.mediapart.fr/journal/ecologie/171022/totalenergies-jette-son-devolu-sur-l-afrique-du-sud-avec-un-megaprojet-gazier".toUri())
                                        context.startActivity(intent) })

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/LfmwNmqcoSE5WTY5A".toUri())
                                        context.startActivity(intent) })
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
                        onClick = {showDilemmeGaz_verso = false }
                    )
                }
            }
        }
    }

    //click sur le dilemme déchets
    ClickElement(
        clickableWidthPercent = 0.11F,
        clickableHeightPercent = 0.125F,
        clickableOffsetPercent = Offset(0.625F, 0.45F),
        navController = navController,
        onClick = { showDilemmeDechets_recto = true })

    //popup dilemme déchets
    if (showDilemmeDechets_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_d_chets_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeDechets_recto = false
                                        showDilemmeDechets_verso = true}
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
                        onClick = {showDilemmeDechets_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeDechets_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_d_chets_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeDechets_verso = false
                                        showDilemmeDechets_recto = true}
                                )

                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.095F,
                                    clickableOffsetPercent = Offset(0.15F, 0.28F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://information.tv5monde.com/afrique/l-afrique-poubelle-des-pays-riches-303241".toUri())
                                        context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.06F,
                                    clickableOffsetPercent = Offset(0.15F, 0.4F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://fr.wikipedia.org/wiki/Convention_de_B%C3%A2le".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.11F,
                                    clickableOffsetPercent = Offset(0.15F, 0.49F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.sciencesetavenir.fr/nature-environnement/pollution/un-enjeu-pour-l-afrique-ne-pas-devenir-la-poubelle-du-monde-des-dechets-plastiques_161809".toUri())
                                        context.startActivity(intent) })

                                //source 4
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.11F,
                                    clickableOffsetPercent = Offset(0.15F, 0.63F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.afrik21.africa/afrique-les-etats-adoptent-de-nouvelles-mesures-face-a-la-pollution-par-les-dechets/".toUri())
                                        context.startActivity(intent) })

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/z6Z9pbpAfqSHqpYB7".toUri())
                                        context.startActivity(intent) })
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
                        onClick = {showDilemmeDechets_verso = false }
                    )
                }
            }
        }
    }

    //click sur le dilemme secheresse
    ClickElement(
        clickableWidthPercent = 0.08F,
        clickableHeightPercent = 0.17F,
        clickableOffsetPercent = Offset(0.505F, 0.365F),
        navController = navController,
        onClick = { showDilemmeSecheresse_recto = true })

    //popup dilemme secheresse
    if (showDilemmeSecheresse_recto){
        // Popup contenant le recto du dilemme
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
                                                painterResource(id = R.drawable.d_secheresse_recto),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeSecheresse_recto = false
                                        showDilemmeSecheresse_verso = true}
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
                        onClick = {showDilemmeSecheresse_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeSecheresse_verso){
        // Popup contenant le verso du dilemme
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
                                                painterResource(id = R.drawable.d_secheresse_verso),
                                                contentScale = ContentScale.Fit
                                            )
                                    }
                                )
                                ClickElement(
                                    clickableWidthPercent = 0.25F,
                                    clickableHeightPercent = 0.1F,
                                    clickableOffsetPercent = Offset(0.53F, 0.75F),
                                    navController = navController,
                                    onClick = {showDilemmeSecheresse_verso = false
                                        showDilemmeSecheresse_recto = true}
                                )

                                //source 1
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.12F,
                                    clickableOffsetPercent = Offset(0.15F, 0.27F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.nationalgeographic.fr/environnement/une-secheresse-extreme-menace-20-millions-de-personnes-en-afrique-de-lest".toUri())
                                        context.startActivity(intent) })

                                //source 2
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.11F,
                                    clickableOffsetPercent = Offset(0.15F, 0.4F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.unicef.fr/article/ethiopie-la-region-somali-frappee-par-la-secheresse-necessite-une-intervention-humanitaire/".toUri())
                                        context.startActivity(intent) })

                                //source 3
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.14F,
                                    clickableOffsetPercent = Offset(0.15F, 0.52F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.who.int/fr/news/item/13-07-2020-as-more-go-hungry-and-malnutrition-persists-achieving-zero-hunger-by-2030-in-doubt-un-report-warns".toUri())
                                        context.startActivity(intent) })

                                //source 4
                                ClickElement(
                                    clickableWidthPercent = 0.31F,
                                    clickableHeightPercent = 0.09F,
                                    clickableOffsetPercent = Offset(0.15F, 0.67F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://www.un.org/africarenewal/sites/www.un.org.africarenewal/files/Agriculture_Africaine.pdf".toUri())
                                        context.startActivity(intent) })

                                //envoyer
                                ClickElement(
                                    clickableWidthPercent = 0.12F,
                                    clickableHeightPercent = 0.08F,
                                    clickableOffsetPercent = Offset(0.585F, 0.68F),
                                    navController = navController,
                                    onClick = { val intent = Intent(Intent.ACTION_VIEW, "https://forms.gle/CqC6V7VnyA3Xmito7".toUri())
                                        context.startActivity(intent) })
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
                        onClick = {showDilemmeSecheresse_verso = false }
                    )
                }
            }
        }
    }

}

@Composable
fun FloatingButtonSend(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Color.Gray,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.lettre),
                    contentDescription = "Envoyer"
                )
            }
        )
    }
}

@Composable
fun FloatingButtonHome(onClick: () -> Unit) {
    Box(

    ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Accueil"
                )
            }
        )
    }
}



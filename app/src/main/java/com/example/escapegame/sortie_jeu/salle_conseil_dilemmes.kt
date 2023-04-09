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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeDiamant_recto = false
                    showDilemmeDiamant_verso = true}
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
                                                painterResource(id = R.drawable.d_diamants_recto),
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
                        onClick = {showDilemmeDiamant_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeDiamant_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeDiamant_verso = false
                    showDilemmeDiamant_recto = true}
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
                                                painterResource(id = R.drawable.d_diamants_verso),
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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeEau_recto = false
                    showDilemmeEau_verso = true}
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
                                                painterResource(id = R.drawable.d_eau_potable_recto),
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
                        onClick = {showDilemmeEau_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeEau_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeEau_verso = false
                    showDilemmeEau_recto = true}
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
                                                painterResource(id = R.drawable.d_eau_potable_verso),
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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeUranium_recto = false
                    showDilemmeUranium_verso = true}
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
                        onClick = {showDilemmeUranium_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeUranium_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeUranium_verso = false
                    showDilemmeUranium_recto = true}
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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeER_recto = false
                    showDilemmeER_verso = true}
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
                                                painterResource(id = R.drawable.d_solaire_recto),
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
                        onClick = {showDilemmeER_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeER_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeER_verso = false
                    showDilemmeER_recto = true}
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
                                                painterResource(id = R.drawable.d_solaire_verso),
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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmePollution_recto = false
                    showDilemmePollution_verso = true}
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
                                                painterResource(id = R.drawable.d_pollution_recto),
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
                        onClick = {showDilemmePollution_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmePollution_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmePollution_verso = false
                    showDilemmePollution_recto = true}
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
                                                painterResource(id = R.drawable.d_pollution_verso),
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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeGaz_recto = false
                    showDilemmeGaz_verso = true}
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
                                                painterResource(id = R.drawable.d_gaz_recto),
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
                        onClick = {showDilemmeGaz_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeGaz_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeGaz_verso = false
                    showDilemmeGaz_recto = true}
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
                                                painterResource(id = R.drawable.d_gaz_verso),
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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeDechets_recto = false
                    showDilemmeDechets_verso = true}
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
                                                painterResource(id = R.drawable.d_d_chets_recto),
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
                        onClick = {showDilemmeDechets_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeDechets_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeDechets_verso = false
                    showDilemmeDechets_recto = true}
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
                                                painterResource(id = R.drawable.d_d_chets_verso),
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

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeSecheresse_recto = false
                    showDilemmeSecheresse_verso = true}
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
                                                painterResource(id = R.drawable.d_secheresse_recto),
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
                        onClick = {showDilemmeSecheresse_recto = false }
                    )
                }
            }
        }
    }
    if (showDilemmeSecheresse_verso){
        // Popup contenant le verso du dilemme
        Popup() {

            ClickElement(
                clickableWidthPercent = 1F,
                clickableHeightPercent = 1F,
                clickableOffsetPercent = Offset(0F, 0F),
                navController = navController,
                onClick = {showDilemmeSecheresse_verso = false
                    showDilemmeSecheresse_recto = true}
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
                                                painterResource(id = R.drawable.d_secheresse_verso),
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



package com.example.escapegame.entree_jeu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

object VariableGlobale {
    var mode:String = ""
    var code_uranium_trouve: Boolean = false
    var code_diamant_trouve: Boolean = false
    var code_eau_trouve: Boolean = false
    var code_gaz_trouve: Boolean = false
    var code_er_trouve: Boolean = false
    var code_pollution_trouve: Boolean = false
    var code_sec_trouve: Boolean = false
    var code_poubelle_trouve: Boolean = false
    var code_sortie_trouve: Boolean = false

}
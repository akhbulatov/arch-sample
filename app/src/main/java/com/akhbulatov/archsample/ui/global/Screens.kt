package com.akhbulatov.archsample.ui.global

import me.aartikov.alligator.Screen
import java.io.Serializable

object Screens {
    object Users : Screen

    data class UserDetails(val login: String) : Screen, Serializable

    object Favorites : Screen
}
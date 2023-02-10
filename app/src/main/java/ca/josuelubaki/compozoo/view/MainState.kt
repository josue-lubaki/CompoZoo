package ca.josuelubaki.compozoo.view

import ca.josuelubaki.compozoo.model.Animal

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Animals(val animals: List<Animal>) : MainState()
}

package ca.josuelubaki.compozoo.view

import ca.josuelubaki.compozoo.model.Animal

// That is the state of the view (the screen)
sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Animals(val animals: List<Animal>) : MainState()
}

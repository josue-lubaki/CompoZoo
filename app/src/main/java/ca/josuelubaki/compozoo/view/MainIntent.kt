package ca.josuelubaki.compozoo.view

// that is the operation that the user can perform
sealed class MainIntent {
    object FetchAnimals : MainIntent()
}

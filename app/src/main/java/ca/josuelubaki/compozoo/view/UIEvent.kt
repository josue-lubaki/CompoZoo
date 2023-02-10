package ca.josuelubaki.compozoo.view

// that is the operation that the user can perform
sealed class UIEvent {
    data class ShowMessage(val message: String) : UIEvent()
}

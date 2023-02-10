package ca.josuelubaki.compozoo.view

sealed class UIEvent {
    data class ShowMessage(val message: String) : UIEvent()
}

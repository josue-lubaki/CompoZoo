package ca.josuelubaki.compozoo.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josuelubaki.compozoo.repo.AnimalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo : AnimalRepo) : ViewModel() {

    private var _state: MutableState<MainState> = mutableStateOf(MainState.Idle)
    val state = _state

    private var _uiEventFlow = MutableSharedFlow<UIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event : MainIntent) {
        when (event) {
            is MainIntent.FetchAnimals -> {
                fetchAnimals()
            }
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            _state.value = MainState.Loading

            try {
                _state.value = MainState.Animals(repo.getAnimals())
            } catch (e: Exception) {
                _uiEventFlow.emit(UIEvent.ShowMessage(e.localizedMessage ?: "An error occurred"))
            }
        }
    }
}
package ca.josuelubaki.compozoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ca.josuelubaki.compozoo.ui.theme.CompoZooTheme
import ca.josuelubaki.compozoo.view.MainIntent
import ca.josuelubaki.compozoo.view.MainState
import ca.josuelubaki.compozoo.view.MainViewModel
import ca.josuelubaki.compozoo.view.UIEvent
import ca.josuelubaki.compozoo.view.screens.AnimalsList
import ca.josuelubaki.compozoo.view.screens.IdleScreen
import ca.josuelubaki.compozoo.view.screens.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompoZooTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
    val uiEventFlow = viewModel.uiEventFlow
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true){
        uiEventFlow.collectLatest {event ->
            when(event) {
                is UIEvent.ShowMessage -> {
                    launch {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = "OK"
                        )
                    }
                }
            }
        }
    }

    when(state){
        is MainState.Idle -> {
            IdleScreen { viewModel.onEvent(MainIntent.FetchAnimals) }
        }
        is MainState.Loading -> {
            LoadingScreen()
        }
        is MainState.Animals -> {
            AnimalsList(animals = state.animals)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CompoZooTheme {
        MainScreen()
    }
}
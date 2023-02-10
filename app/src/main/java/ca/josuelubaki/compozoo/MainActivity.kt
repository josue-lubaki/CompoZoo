package ca.josuelubaki.compozoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ca.josuelubaki.compozoo.model.Animal
import ca.josuelubaki.compozoo.ui.theme.CompoZooTheme
import ca.josuelubaki.compozoo.view.MainIntent
import ca.josuelubaki.compozoo.view.MainState
import ca.josuelubaki.compozoo.view.MainViewModel
import ca.josuelubaki.compozoo.view.UIEvent
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun IdleScreen(onButtonClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = onButtonClicked){
            Text(text = "Fetch Animals")
        }
    }
}

@Composable
fun AnimalsList(animals : List<Animal>) {
    LazyColumn{
        items(items = animals){
            AnimalItem(animal = it)
            Divider(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp),
                color = Color.LightGray,
            )
        }
    }
}

@Composable
fun AnimalItem(animal : Animal) {
        val url = BuildConfig.BASE_URL + animal.image
        val painter = rememberAsyncImagePainter(model =
            ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        )

        MyCard(
            painter = painter,
            title = animal.name,
            description = animal.location
        )
}

@Composable
fun MyCard(painter : Painter, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
           verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = painter,
                contentDescription = "Animal Image",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .height(140.dp)
                    .width(140.dp),
                contentScale = ContentScale.FillHeight
            )

            Column {
                Text(title, style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(8.dp))
                Text(description, style = MaterialTheme.typography.bodyMedium)
            }
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
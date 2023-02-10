package ca.josuelubaki.compozoo.view.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ca.josuelubaki.compozoo.BuildConfig
import ca.josuelubaki.compozoo.model.Animal
import ca.josuelubaki.compozoo.view.components.CardAnimal
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

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

    CardAnimal(
        painter = painter,
        title = animal.name,
        description = animal.location
    )
}
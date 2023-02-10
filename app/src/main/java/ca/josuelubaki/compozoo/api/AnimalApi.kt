package ca.josuelubaki.compozoo.api

import ca.josuelubaki.compozoo.model.Animal
import retrofit2.http.GET

interface AnimalApi {
    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>
}
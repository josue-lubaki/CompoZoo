package ca.josuelubaki.compozoo.repo

import ca.josuelubaki.compozoo.api.AnimalApi
import javax.inject.Inject

class AnimalRepoImpl @Inject constructor(private val api: AnimalApi) : AnimalRepo {
    override suspend fun getAnimals() = api.getAnimals()
}
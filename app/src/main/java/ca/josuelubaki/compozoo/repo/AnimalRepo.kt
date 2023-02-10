package ca.josuelubaki.compozoo.repo

import ca.josuelubaki.compozoo.model.Animal

interface AnimalRepo {
    suspend fun getAnimals(): List<Animal>
}
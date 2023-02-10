package ca.josuelubaki.compozoo.di

import ca.josuelubaki.compozoo.api.AnimalApi
import ca.josuelubaki.compozoo.repo.AnimalRepo
import ca.josuelubaki.compozoo.repo.AnimalRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideAnimalRepo(api : AnimalApi) : AnimalRepo {
        return AnimalRepoImpl(api)
    }
}
package ca.josuelubaki.compozoo.di

import ca.josuelubaki.compozoo.BuildConfig
import ca.josuelubaki.compozoo.api.AnimalApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private var BASE_URL: String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimalApiInstance(retrofit : Retrofit) : AnimalApi {
        return retrofit.create(AnimalApi::class.java)
    }
}
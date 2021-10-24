package dev.hakanbagci.search.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hakanbagci.search.data.repo.MealRepositoryImpl
import dev.hakanbagci.search.domain.repo.MealRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun bindMealRepository(
        mealRepositoryImpl: MealRepositoryImpl
    ): MealRepository
}

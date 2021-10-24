package dev.hakanbagci.search.domain.usecase

import dev.hakanbagci.search.domain.entity.SearchMealsResult
import dev.hakanbagci.search.domain.repo.MealRepository
import javax.inject.Inject

class SearchMeals @Inject constructor(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke(query: String): SearchMealsResult {
        return mealRepository.search(query)
    }
}

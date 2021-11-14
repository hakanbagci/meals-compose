package dev.hakanbagci.search.data.repo

import dev.hakanbagci.search.data.entity.toMeals
import dev.hakanbagci.search.domain.entity.SearchMealsResult
import dev.hakanbagci.search.domain.entity.SearchMealsResult.*
import dev.hakanbagci.search.domain.repo.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) : MealRepository {

    override suspend fun search(query: String): SearchMealsResult {
        if (query.isEmpty()) return NoResults

        return try {
            val results = searchService.search(query = query)
            if (results.meals.isNullOrEmpty()) NoResults else Success(results.toMeals())
        } catch (e: Exception) {
            Error
        }
    }
}

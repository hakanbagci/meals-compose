package dev.hakanbagci.search.data.repo

import dev.hakanbagci.search.domain.entity.Meal
import dev.hakanbagci.search.domain.entity.SearchMealsResult
import dev.hakanbagci.search.domain.entity.SearchMealsResult.NoResults
import dev.hakanbagci.search.domain.entity.SearchMealsResult.Success
import dev.hakanbagci.search.domain.repo.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor() : MealRepository {

    private val allResults = listOf(
        Meal(
            id = "52771",
            name = "Spicy Arrabiata Penne",
            thumbnailUrl = "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg"
        ),
        Meal(
            id = "52971",
            name = "Kafteji",
            thumbnailUrl = "https://www.themealdb.com/images/media/meals/1bsv1q1560459826.jpg"
        )
    )

    override suspend fun search(query: String): SearchMealsResult {
        if (query.isEmpty()) return NoResults

        val filteredResults = allResults.filter {
            it.name.lowercase().contains(query.lowercase())
        }
        if (filteredResults.isEmpty()) return NoResults

        return Success(filteredResults)
    }
}

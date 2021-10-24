package dev.hakanbagci.search.domain.repo

import dev.hakanbagci.search.domain.entity.SearchMealsResult

interface MealRepository {

    suspend fun search(query: String): SearchMealsResult
}

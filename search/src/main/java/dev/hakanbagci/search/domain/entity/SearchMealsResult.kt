package dev.hakanbagci.search.domain.entity

sealed class SearchMealsResult {
    object Error : SearchMealsResult()
    object NoResults : SearchMealsResult()
    data class Success(val meals: List<Meal>) : SearchMealsResult()
}

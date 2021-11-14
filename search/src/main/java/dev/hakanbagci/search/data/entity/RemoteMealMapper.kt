package dev.hakanbagci.search.data.entity

import dev.hakanbagci.search.domain.entity.Meal

fun RemoteMeal.toMeal(): Meal = Meal(
    id = this.id,
    name = this.name,
    thumbnailUrl = this.thumbnailUrl
)

fun RemoteMeals.toMeals(): List<Meal> = meals?.map { it.toMeal() } ?: emptyList()
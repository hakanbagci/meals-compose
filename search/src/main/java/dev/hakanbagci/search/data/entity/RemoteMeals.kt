package dev.hakanbagci.search.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMeals(
    @SerialName("meals")
    val meals: List<RemoteMeal>?
)

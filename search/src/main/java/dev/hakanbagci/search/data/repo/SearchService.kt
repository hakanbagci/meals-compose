package dev.hakanbagci.search.data.repo

import dev.hakanbagci.search.data.entity.RemoteMeals
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search.php")
    suspend fun search(@Query("s") query: String): RemoteMeals
}

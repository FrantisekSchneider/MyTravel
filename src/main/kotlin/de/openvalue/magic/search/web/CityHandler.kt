package de.openvalue.magic.search.web

import de.openvalue.magic.search.application.FindCity
import kotlinx.coroutines.flow.map
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

class CityHandler(private val findCity: FindCity) {

    suspend fun findCities(request: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(findCity.by(query(request)).map { CityJson(it.name, it.country) })
    }

    private fun query(request: ServerRequest): FindCity.FindCityQuery {
        val queryParams: MultiValueMap<String, String> = request.queryParams()

        return FindCity.FindCityQuery(
            name = queryParams.getFirst("name") ?: "Pra",
        )
    }
}

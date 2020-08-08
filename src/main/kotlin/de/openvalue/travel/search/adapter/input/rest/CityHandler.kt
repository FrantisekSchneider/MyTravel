package de.openvalue.travel.search.adapter.input.rest

import de.openvalue.travel.search.adapter.input.rest.data.CityJson
import de.openvalue.travel.search.port.input.rest.FindCitiesUseCase
import kotlinx.coroutines.flow.map
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

class CityHandler(private val findCitiesUseCase: FindCitiesUseCase) {

	suspend fun findCities(request: ServerRequest): ServerResponse {
		return ServerResponse
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.bodyAndAwait(findCitiesUseCase.findBy(query(request)).map { CityJson(it.name, it.country) })
	}

	private fun query(request: ServerRequest): FindCitiesUseCase.FindCitiesQuery {
		val queryParams: MultiValueMap<String, String> = request.queryParams()

		return FindCitiesUseCase.FindCitiesQuery(
			name = queryParams.getFirst("name") ?: "",
			countryCode = queryParams.getFirst("countryCode") ?: "",
			limit = queryParams.getFirst("limit") ?: "10"
		)
	}
}

package de.openvalue.travel.search.adapter.input.rest

import de.openvalue.travel.search.adapter.input.rest.data.CityJson
import de.openvalue.travel.search.port.input.FindCitiesUseCase
import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class CityHandler(private val findCitiesUseCase: FindCitiesUseCase) {

	suspend fun findCities(request: ServerRequest): ServerResponse {
		return ServerResponse
			.ok()
			.contentType(MediaType.TEXT_EVENT_STREAM)
			.bodyAndAwait(findCitiesUseCase.findBy(query(request))
				.map { CityJson(it.name, it.country) }
				.asFlow())
	}

	private fun query(request: ServerRequest): FindCitiesUseCase.FindCitiesQuery {
		val queryParams: MultiValueMap<String, String> = request.queryParams()

		return FindCitiesUseCase.FindCitiesQuery(
			name = queryParams.getFirst("name"),
			countryCode = queryParams.getFirst("countryCode"),
			limit = queryParams.getFirst("limit")
		)
	}
}

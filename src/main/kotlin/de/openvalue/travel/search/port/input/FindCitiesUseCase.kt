package de.openvalue.travel.search.port.input

import de.openvalue.travel.search.domain.model.City
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate
import reactor.core.publisher.Flux

interface FindCitiesUseCase {

	fun findBy(query: FindCitiesQuery): Flux<City>

	data class FindCitiesQuery(val name: String? = "",
							   val countryCode: String? = "",
							   val limit: String? = "10") {
		init {
			validate(this) {
				validate(FindCitiesQuery::name).isNotBlank()
				validate(FindCitiesQuery::countryCode).isNotBlank()
				validate(FindCitiesQuery::limit).isNotBlank()
			}
		}
	}
}

package de.openvalue.travel.search.port.output.db

import de.openvalue.travel.search.domain.model.City
import reactor.core.publisher.Flux

interface CityPersistencePort {

	fun findByNameAndCountry(name: String, country: String): Flux<City>
}

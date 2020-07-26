package de.openvalue.travel.search.adapter.output.db

import de.openvalue.travel.search.domain.model.City
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CityRepository : ReactiveCrudRepository<City, Long> {

    fun findAllByNameContainsAndCountry(name: String, country: String): Flux<City>
}

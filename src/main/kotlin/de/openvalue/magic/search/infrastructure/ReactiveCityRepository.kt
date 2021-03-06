package de.openvalue.magic.search.infrastructure

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ReactiveCityRepository : ReactiveCrudRepository<City, Long> {

    fun findAllByNameContains(name: String): Flux<City>
}

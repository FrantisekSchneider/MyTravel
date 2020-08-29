package de.openvalue.magic.search.infrastructure

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow

class H2CityRepository(private val cityRepository: ReactiveCityRepository) : CityRepository {

    override suspend fun findAllByNameContainsAndCountry(name: String, country: String): Flow<City> {
        return cityRepository.findAllByNameContainsAndCountry(name, country).asFlow()
    }
}

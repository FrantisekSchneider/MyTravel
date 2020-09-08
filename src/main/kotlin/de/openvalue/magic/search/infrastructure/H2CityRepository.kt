package de.openvalue.magic.search.infrastructure

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow

class H2CityRepository(private val cityRepository: ReactiveCityRepository) : CityRepository {

    override suspend fun findAllByNameLike(name: String): Flow<City> {
        return cityRepository.findAllByNameContains(name).asFlow()
    }
}

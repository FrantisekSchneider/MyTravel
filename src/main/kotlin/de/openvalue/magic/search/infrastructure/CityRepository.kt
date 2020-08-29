package de.openvalue.magic.search.infrastructure

import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun findAllByNameContainsAndCountry(name: String, country: String): Flow<City>
}

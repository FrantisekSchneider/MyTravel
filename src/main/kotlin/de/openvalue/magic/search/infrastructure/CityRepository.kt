package de.openvalue.magic.search.infrastructure

import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun findAllByNameLike(name: String): Flow<City>
}

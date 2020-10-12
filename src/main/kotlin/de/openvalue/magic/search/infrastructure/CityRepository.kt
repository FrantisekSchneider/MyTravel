package de.openvalue.magic.search.infrastructure

import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun findAllByNameLike(name: String): Flow<City>
}

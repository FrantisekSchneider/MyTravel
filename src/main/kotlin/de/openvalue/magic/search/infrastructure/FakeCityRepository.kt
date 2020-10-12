package de.openvalue.magic.search.infrastructure

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf

class FakeCityRepository : CityRepository {
    override fun findAllByNameLike(name: String): Flow<City> {
        return flowOf(
            City(1L, "CZ", "Pilsen", 1.20323, 2.2323),
            City(2L, "CZ", "Prague", 1.3232, 4.2323),
            City(2L, "DE", "Munich", 1.6443, 3.0854)
        )
            .filter {
                it.name.contains(name)
            }
    }
}

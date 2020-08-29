package de.openvalue.magic.search.application

import de.openvalue.magic.search.infrastructure.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

class FindCityService(private val repository: CityRepository) : FindCity {

    override suspend fun by(query: FindCity.FindCityQuery): Flow<CityModel> {
        return repository.findAllByNameContainsAndCountry(query.name, query.countryCode)
            .map {
                CityModel(it.name, it.country, it.lat, it.lng)
            }
            .take(query.limit)
    }
}

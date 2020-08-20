package de.fs.blog.travel.search.port.output.db

import de.fs.blog.travel.search.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityPersistencePort {

	fun findByNameAndCountry(name: String, country: String): Flow<City>

}

package de.fs.blog.travel.search.domain.application.service

import de.fs.blog.travel.search.domain.model.City
import de.fs.blog.travel.search.port.input.rest.FindCitiesUseCase
import de.fs.blog.travel.search.port.output.db.CityPersistencePort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take

class CityService(private val cityPersistencePort: CityPersistencePort) : FindCitiesUseCase {

	override fun findBy(query: FindCitiesUseCase.FindCitiesQuery): Flow<City> {
		return cityPersistencePort
			.findByNameAndCountry(query.name, query.countryCode)
			.take(query.limit.toInt())
	}
}
package de.fs.blog.travel.search.adapter.output.db

import de.fs.blog.travel.search.domain.model.City
import de.fs.blog.travel.search.port.output.db.CityPersistencePort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow

class PersistenceAdapter(private val cityRepository: CityRepository) : CityPersistencePort {

	override fun findByNameAndCountry(name: String, country: String): Flow<City> {
		return cityRepository.findAllByNameContainsAndCountry(name, country).asFlow()
	}
}

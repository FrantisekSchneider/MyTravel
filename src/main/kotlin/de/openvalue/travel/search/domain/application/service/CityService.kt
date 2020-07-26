package de.openvalue.travel.search.domain.application.service

import de.openvalue.travel.search.domain.model.City
import de.openvalue.travel.search.port.input.FindCitiesUseCase
import de.openvalue.travel.search.port.output.db.CityPersistencePort
import java.time.Duration
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CityService(private val cityPersistencePort: CityPersistencePort) : FindCitiesUseCase {

	override fun findBy(query: FindCitiesUseCase.FindCitiesQuery): Flux<City> {
		return cityPersistencePort
			.findByNameAndCountry(query.name!!, query.countryCode!!)
			.delayElements(Duration.ofSeconds(1))
			.take(query.limit!!.toLong())
	}
}

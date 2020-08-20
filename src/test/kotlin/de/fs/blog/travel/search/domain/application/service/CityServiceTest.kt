package de.fs.blog.travel.search.domain.application.service

import de.fs.blog.travel.search.domain.model.City
import de.fs.blog.travel.search.port.input.rest.FindCitiesUseCase
import de.fs.blog.travel.search.port.output.db.CityPersistencePort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito
import reactor.core.publisher.Flux

internal class CityServiceTest {
	private lateinit var cityPersistencePort: CityPersistencePort
	private lateinit var cityService: CityService

	@BeforeEach
	fun init() {
		cityPersistencePort = Mockito.mock(CityPersistencePort::class.java)
		cityService = CityService(cityPersistencePort)
	}

	@Test
	fun `find cities by query`() = runBlocking {
		// given
		BDDMockito.given(cityPersistencePort.findByNameAndCountry(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
			.willReturn(Flux.just(City(1L, "CZ", "Pilsen", 1.000, 1.000)).asFlow())

		// when
		val result: Flow<City> = cityService.findBy(FindCitiesUseCase.FindCitiesQuery("Pil", "CZ", "1"))

		// then
		result.collect {
			Assertions.assertEquals("Pilsen", it.name)
			Assertions.assertEquals("CZ", it.country)
		}
	}
}

package de.fs.blog.travel.search.adapter.output.db

import de.fs.blog.travel.search.domain.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactor.asFlux
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito
import org.mockito.Mockito
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

internal class PersistenceAdapterTest {

	private lateinit var cityRepository: CityRepository
	private lateinit var persistenceAdapter: PersistenceAdapter

	@BeforeEach
	fun beforeEach() {
		cityRepository = Mockito.mock(CityRepository::class.java)
		persistenceAdapter = PersistenceAdapter(cityRepository)
	}

	@Test
	fun `find all cities by name prefix and country`() {
		// given
		BDDMockito.given(cityRepository.findAllByNameContainsAndCountry(anyString(), anyString()))
			.willReturn(Flux.just(City(1L, "CZ", "Pilsen", 1.000, 1.000)))

		// when
		val cities: Flow<City> = persistenceAdapter.findByNameAndCountry("Pil", "CZ")

		// then
		StepVerifier.create(cities.asFlux())
			.assertNext {
				Assertions.assertEquals("Pilsen", it.name)
				Assertions.assertEquals("CZ", it.country)
			}
			.verifyComplete()
	}
}

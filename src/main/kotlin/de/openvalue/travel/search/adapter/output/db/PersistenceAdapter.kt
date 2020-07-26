package de.openvalue.travel.search.adapter.output.db

import de.openvalue.travel.search.domain.model.City
import de.openvalue.travel.search.port.output.db.CityPersistencePort
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Primary
@Repository("RealPersistenceAdapter")
@ConditionalOnProperty(value = ["db.enabled"], matchIfMissing = true, havingValue = "true")
class PersistenceAdapter(private val cityRepository: CityRepository) : CityPersistencePort {

    override fun findByNameAndCountry(name: String, country: String): Flux<City> {
        return cityRepository.findAllByNameContainsAndCountry(name, country)
    }
}

@Repository("FakePersistenceAdapter")
@ConditionalOnMissingBean(value = [PersistenceAdapter::class])
class FakePersistenceAdapter : CityPersistencePort {

    override fun findByNameAndCountry(name: String, country: String): Flux<City> {
        return Flux.just(
            City(1L, "CZ", "FakeCity", 1.00000, 1.00000)
        )
    }
}

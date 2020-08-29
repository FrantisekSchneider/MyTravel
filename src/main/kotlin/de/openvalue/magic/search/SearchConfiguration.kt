package de.openvalue.magic.search

import de.openvalue.magic.search.application.FindCityService
import de.openvalue.magic.search.infrastructure.FakeCityRepository
import de.openvalue.magic.search.infrastructure.H2CityRepository
import de.openvalue.magic.search.web.CityHandler
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

internal class SearchConfiguration : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(context: GenericApplicationContext) = beans(context).initialize(context)

    private fun beans(context: GenericApplicationContext): BeanDefinitionDsl = beans {
        // register REST api handlers
        bean {
            coRouter {
                val cityHandler = CityHandler(ref())
                GET("/api/cities", accept(MediaType.APPLICATION_JSON), cityHandler::findCities)
            }
        }

        // register H2 persistence adapters
        if (context.environment.getProperty("FAKE_REPO_ENABLED") == "true") {
            bean<FakeCityRepository>()
        } else {
            bean<H2CityRepository>()
        }

        // register use case components - domain logic
        bean<FindCityService>()
    }
}

package de.openvalue.travel.search.adapter

import de.openvalue.travel.search.adapter.input.rest.CityHandler
import de.openvalue.travel.search.adapter.output.db.PersistenceAdapter
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.coRouter

internal class BeansInitializer : ApplicationContextInitializer<GenericApplicationContext> {
	override fun initialize(context: GenericApplicationContext) = beans().initialize(context)

	private fun beans(): BeanDefinitionDsl = beans {
		// in adapters
		bean {
			coRouter {
				val cityHandler = CityHandler(ref())
				GET("/api/cities", cityHandler::findCities)
			}
		}

		// out adapters
		bean<PersistenceAdapter>()
	}
}


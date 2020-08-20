package de.fs.blog.travel.search

import de.fs.blog.travel.search.adapter.input.rest.CityHandler
import de.fs.blog.travel.search.adapter.output.db.PersistenceAdapter
import de.fs.blog.travel.search.domain.application.service.CityService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.coRouter

internal class BeanInitializer : ApplicationContextInitializer<GenericApplicationContext> {
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

		// domain logic
		bean<CityService>()
	}
}


package de.openvalue.travel.search.domain

import de.openvalue.travel.search.domain.application.service.CityService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

internal class BeansInitializer : ApplicationContextInitializer<GenericApplicationContext> {
	override fun initialize(context: GenericApplicationContext) = beans().initialize(context)

	private fun beans(): BeanDefinitionDsl = beans {
		bean<CityService>()
	}
}


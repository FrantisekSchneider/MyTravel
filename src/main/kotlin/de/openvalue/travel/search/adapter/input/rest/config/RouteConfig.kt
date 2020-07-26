package de.openvalue.travel.search.adapter.input.rest.config

import de.openvalue.travel.search.adapter.input.rest.CityHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
internal class RouteConfig(private val cityHandler: CityHandler) {

    @Bean
    fun routes(): RouterFunction<ServerResponse> = coRouter {
        GET("/api/cities", cityHandler::findCities)
    }
}

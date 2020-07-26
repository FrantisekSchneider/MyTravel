package de.openvalue.travel.search.domain.model

import org.springframework.data.annotation.Id

data class City(
    @Id val id: Long? = -1L,
    val country: String,
    val name: String,
    val lat: Double,
    val lng: Double
)

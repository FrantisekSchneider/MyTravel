package de.openvalue.magic.search.infrastructure

data class City(
    val id: Long? = -1L,
    val country: String,
    val name: String,
    val lat: Double,
    val lng: Double
)

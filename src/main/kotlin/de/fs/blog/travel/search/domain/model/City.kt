package de.fs.blog.travel.search.domain.model

data class City(
	val id: Long? = -1L,
	val country: String,
	val name: String,
	val lat: Double,
	val lng: Double
)

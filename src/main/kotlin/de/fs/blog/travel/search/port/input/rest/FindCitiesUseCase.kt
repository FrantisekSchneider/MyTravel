package de.fs.blog.travel.search.port.input.rest

import de.fs.blog.travel.search.domain.model.City
import kotlinx.coroutines.flow.Flow
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

interface FindCitiesUseCase {

	fun findBy(query: FindCitiesQuery): Flow<City>

	data class FindCitiesQuery(val name: String,
							   val countryCode: String,
							   val limit: String) {
		init {
			validate(this) {
				validate(FindCitiesQuery::name).isNotEmpty()
				validate(FindCitiesQuery::countryCode).isNotEmpty()
				validate(FindCitiesQuery::limit).isNotEmpty()
			}
		}
	}
}

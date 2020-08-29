package de.openvalue.magic.search.application

import kotlinx.coroutines.flow.Flow
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

interface FindCity {

    suspend fun by(query: FindCityQuery): Flow<CityModel>

    data class FindCityQuery(
        val name: String,
        val countryCode: String,
        val limit: Int
    ) {
        init {
            validate(this) {
                validate(FindCityQuery::name).isNotEmpty()
                validate(FindCityQuery::countryCode).isNotEmpty()
                validate(
                    this,
                    {
                        limit in 1..10
                    }
                )
            }
        }
    }
}

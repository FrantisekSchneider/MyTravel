package de.openvalue.magic.search

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CitiesRestApiTest(@LocalServerPort private val port: Int) {

    private val requestSpec: RequestSpecification = RequestSpecBuilder()
        .setBaseUri("http://localhost:$port")
        .addFilter(RequestLoggingFilter())
        .addFilter(ResponseLoggingFilter())
        .build()

    @Test
    fun `Verify REST API GET - query cities`() {
        RestAssured.given()
            .spec(requestSpec)
            .`when`()
            .get("/api/cities?name=Pra&countryCode=CZ")
            .then()
            .statusCode(200)
    }
}

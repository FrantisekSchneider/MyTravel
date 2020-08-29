package de.openvalue.magic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(proxyBeanMethods = false)
class TravelApplication

fun main(args: Array<String>) {
    runApplication<TravelApplication>(*args)
}

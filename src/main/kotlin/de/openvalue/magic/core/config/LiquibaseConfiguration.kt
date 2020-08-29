package de.openvalue.magic.core.config

import liquibase.integration.spring.SpringLiquibase
import org.h2.jdbcx.JdbcDataSource
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.support.ResourcePropertySource
import javax.sql.DataSource

class LiquibaseConfiguration :
    ApplicationContextInitializer<GenericApplicationContext> {

    private lateinit var propertySource: ResourcePropertySource

    private val springLiquibase: SpringLiquibase = SpringLiquibase()

    override fun initialize(applicationContext: GenericApplicationContext) {
        propertySource = ResourcePropertySource("classpath:liquibase.properties")

        with(springLiquibase) {
            changeLog = propertySource.get("liquibase.changeLog")
            defaultSchema = propertySource.get("liquibase.defaultSchema")
            dataSource = dataSource()
            resourceLoader = DefaultResourceLoader()
        }

        springLiquibase.afterPropertiesSet()
    }

    private fun dataSource(): DataSource = with(DataSourceBuilder.create()) {
        driverClassName(propertySource.get("liquibase.datasource.driverClassName"))
        url(propertySource.get("liquibase.datasource.url"))
        username(propertySource.get("liquibase.datasource.username"))
        password(propertySource.get("liquibase.datasource.password"))
        type(JdbcDataSource::class.java)
        build()
    }
}

private fun ResourcePropertySource.get(name: String): String {
    return this.getProperty(name) as String
}

package de.openvalue.magic.core.config

import liquibase.integration.spring.SpringLiquibase
import org.h2.jdbcx.JdbcDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
@PropertySource("classpath:liquibase.properties")
class DatabaseSchemaMigration {

    @Value("\${liquibase.changeLog}")
    private lateinit var changeLog: String

    @Value("\${liquibase.defaultSchema}")
    private lateinit var defaultSchema: String

    @Value("\${liquibase.datasource.url}")
    private lateinit var url: String

    @Value("\${liquibase.datasource.driverClassName}")
    private lateinit var driverClassName: String

    @Value("\${liquibase.datasource.password}")
    private lateinit var password: String

    @Value("\${liquibase.datasource.username}")
    private lateinit var username: String

    private val springLiquibase: SpringLiquibase = SpringLiquibase()

    @PostConstruct
    fun initDB() {

//        with(springLiquibase) {
//            changeLog = this@DatabaseSchemaMigration.changeLog
//            defaultSchema = this@DatabaseSchemaMigration.defaultSchema
//            dataSource = dataSource()
//            resourceLoader = DefaultResourceLoader()
//        }

//        springLiquibase.afterPropertiesSet()
    }

    private fun dataSource(): DataSource = with(DataSourceBuilder.create()) {
        driverClassName(this@DatabaseSchemaMigration.driverClassName)
        url(this@DatabaseSchemaMigration.url)
        username(this@DatabaseSchemaMigration.username)
        password(this@DatabaseSchemaMigration.password)
        type(JdbcDataSource::class.java)
        build()
    }
}

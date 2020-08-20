package de.fs.blog.travel.infrastucture.liquibase

import liquibase.integration.spring.SpringLiquibase
import org.h2.jdbcx.JdbcDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.DefaultResourceLoader
import javax.annotation.PostConstruct

@Configuration
@PropertySource("classpath:liquibase.properties")
class SchemaMigration {

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

		with(springLiquibase) {
			changeLog = this@SchemaMigration.changeLog
			defaultSchema = this@SchemaMigration.defaultSchema
			dataSource = dataSource()
			resourceLoader = DefaultResourceLoader()
		}

		springLiquibase.afterPropertiesSet()
	}

	private fun dataSource() = with(DataSourceBuilder.create()) {
		driverClassName(this@SchemaMigration.driverClassName)
		url(this@SchemaMigration.url)
		username(this@SchemaMigration.username)
		password(this@SchemaMigration.password)
		type(JdbcDataSource::class.java)
		build()
	}
}

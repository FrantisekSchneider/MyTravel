import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.4.0"
	kotlin("plugin.spring") version "1.4.0"

	id("org.jmailen.kotlinter") version "3.0.2"
	id("org.jetbrains.kotlin.kapt") version "1.4.0"

	id("com.palantir.graal") version "0.7.1"
}

group = "de.fs.blog.travel"
version = "v1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

val valiktorVersion: String by project
val archunitVersion: String by project
val mockKVersion: String by project

dependencies {
	// actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	//db
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.liquibase:liquibase-core")
	implementation("com.h2database:h2")
	runtimeOnly("io.r2dbc:r2dbc-h2")

	// reactive spring
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	// validation
	implementation("org.valiktor:valiktor-spring-boot-starter:${valiktorVersion}")
	implementation("org.valiktor:valiktor-javatime:${valiktorVersion}")

	// testing
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("io.rest-assured:rest-assured")
	testImplementation("io.mockk:mockk:${mockKVersion}")

	testImplementation("com.tngtech.archunit:archunit-junit5-api:$archunitVersion")
	testRuntimeOnly("com.tngtech.archunit:archunit-junit5-engine:$archunitVersion")

//	compileOnly("org.springframework.experimental:spring-graalvm-native:0.8.0-SNAPSHOT")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict", "-Xallow-result-return-type")
		jvmTarget = "11"
	}
}

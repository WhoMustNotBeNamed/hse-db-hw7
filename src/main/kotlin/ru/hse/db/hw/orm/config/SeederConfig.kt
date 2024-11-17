package ru.hse.db.hw.orm.config

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.hse.db.hw.orm.service.DatabaseSeeder

@Configuration
class SeederConfig {

    private val logger = LoggerFactory.getLogger(SeederConfig::class.java)

    @Bean
    fun runSeeder(databaseSeeder: DatabaseSeeder): ApplicationRunner = ApplicationRunner {
        logger.info("Seeding database...")
        databaseSeeder.seedDatabase()
        logger.info("Database seeding finished.")
    }
}

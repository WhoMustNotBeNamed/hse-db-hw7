package ru.hse.db.hw.orm.service

import net.datafaker.Faker
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.hse.db.hw.orm.model.*
import ru.hse.db.hw.orm.repository.*
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

@Component
class DatabaseSeeder(
    private val countryRepository: CountryRepository,
    private val olympicRepository: OlympicRepository,
    private val playerRepository: PlayerRepository,
    private val eventRepository: EventRepository,
    private val resultRepository: ResultRepository
) {
    private val faker = Faker()

    @Transactional
    fun seedDatabase() {
        val countries = seedCountries(50) // Генерируем 50 стран
        val olympics = seedOlympics(countries, 20) // Генерируем 20 олимпийских игр
        val events = seedEvents(olympics, 100) // Генерируем 100 событий
        val players = seedPlayers(countries, 1000) // Генерируем 1000 игроков
        seedResults(events, players, 5000) // Генерируем 5000 результатов
    }

    private fun seedCountries(count: Int): List<Country> {
        val countries = (1..count).map {
            Country(
                name = faker.country().name(),
                countryId = faker.address().countryCode(),
                areaSqkm = Random.nextInt(10_000, 10_000_000),
                population = Random.nextInt(500_000, 1_000_000_000)
            )
        }
        return countryRepository.saveAll(countries)
    }

    private fun seedOlympics(countries: List<Country>, count: Int): List<Olympic> {
        val olympics = (1..count).map {
            Olympic(
                olympicId = faker.idNumber().valid(),
                countryId = countries.random(),
                city = faker.address().cityName(),
                year = Random.nextInt(1900, 2024),
                startDate = LocalDate.of(Random.nextInt(1900, 2024), Random.nextInt(1, 12), Random.nextInt(1, 28)),
                endDate = LocalDate.of(Random.nextInt(1900, 2024), Random.nextInt(1, 12), Random.nextInt(1, 28))
            )
        }
        return olympicRepository.saveAll(olympics)
    }

    private fun seedEvents(olympics: List<Olympic>, count: Int): List<Event> {
        val events = (1..count).map {
            Event(
                eventId = faker.idNumber().valid(),
                name = faker.name().toString(),
                eventType = faker.esports().event(),
                olympicId = olympics.random(),
                isTeamEvent = Random.nextBoolean(),
                numPlayersInTeam = if (Random.nextBoolean()) Random.nextInt(2, 11) else -1,
                resultNotedIn = faker.lorem().word(),
            )
        }
        return eventRepository.saveAll(events)
    }

    private fun seedPlayers(countries: List<Country>, count: Int): List<Player> {
        val players = (1..count).map {
            Player(
                name = faker.name().fullName(),
                playerId = faker.idNumber().valid(),
                countryId = countries.random(),
                birthdate = LocalDate.of(Random.nextInt(1950, 2024), Random.nextInt(1, 12), Random.nextInt(1, 28))
            )
        }
        return playerRepository.saveAll(players)
    }

    private fun seedResults(events: List<Event>, players: List<Player>, count: Int) {
        val results = (1..count).map {
            val event = events.random()
            val player = players.random()
            Result(
                id = UUID.randomUUID(),
                medal = listOf("GOLD", "SILVER", "BRONZE", null).random(),
                result = if (event.isTeamEvent) Random.nextDouble(10.0, 100.0) else Random.nextDouble(9.5, 10.5),
                eventId = event,
                playerId = player
            )
        }
        resultRepository.saveAll(results)
    }
}
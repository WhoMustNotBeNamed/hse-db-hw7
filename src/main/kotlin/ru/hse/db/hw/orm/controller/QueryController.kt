package ru.hse.db.hw.orm.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.db.hw.orm.model.*
import ru.hse.db.hw.orm.repository.CountryRepository
import ru.hse.db.hw.orm.repository.EventRepository
import ru.hse.db.hw.orm.repository.ResultRepository

@RestController
@RequestMapping("/query")
class QueryController(
    private val resultRepository: ResultRepository,
    private val eventRepository: EventRepository,
    private val countryRepository: CountryRepository

) {

    @GetMapping("/gold-medals-2004")
    fun getGoldMedals2004(): List<GoldMedals2004Dto> {
        return resultRepository.findGoldMedalsFor2004()
    }

    @GetMapping("/tie-events")
    fun getTieEvents(): List<TieEventDto> {
        return eventRepository.findIndividualEventsWithTies()
    }

    @GetMapping("/players-with-medals")
    fun getPlayersWithMedals(): List<PlayerWithMedalDto> {
        return resultRepository.findPlayersWithMedals()
    }

    @GetMapping("/country-with-highest-vowel-percentage")
    fun getCountryWithHighestVowelPercentage(): CountryWithVowelPercentageDto {
        return countryRepository.findCountryWithHighestVowelPercentage()
    }

    @GetMapping("/team-medals-2000")
    fun getTeamMedalsRatio2000(): List<TeamMedalsRatioDto> {
        return resultRepository.findTeamMedalsRatio2000()
    }
}
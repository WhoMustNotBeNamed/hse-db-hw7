package ru.hse.db.hw.orm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.hse.db.hw.orm.model.GoldMedals2004Dto
import ru.hse.db.hw.orm.model.PlayerWithMedalDto
import ru.hse.db.hw.orm.model.Result
import ru.hse.db.hw.orm.model.TeamMedalsRatioDto

@Repository
interface ResultRepository : JpaRepository<Result, String> {

    @Query(
        """
        SELECT new ru.hse.db.hw.orm.model.GoldMedals2004Dto(
            YEAR(p.birthdate) as yearOfBirth,
            COUNT(DISTINCT p.playerId) as playerCount,
            COUNT(r.medal) as goldMedalCount
        )
        FROM Result r
        JOIN r.playerId p
        JOIN r.eventId e
        JOIN e.olympicId o
        WHERE r.medal = 'GOLD'
          AND o.year = 2004
        GROUP BY YEAR(p.birthdate)
        ORDER BY yearOfBirth
        """
    )
    fun findGoldMedalsFor2004(): List<GoldMedals2004Dto>

    @Query(
        """
        SELECT new ru.hse.db.hw.orm.model.PlayerWithMedalDto(
            p.name,
            o.olympicId
        )
        FROM Result r
        JOIN r.playerId p
        JOIN r.eventId e
        JOIN e.olympicId o
        WHERE r.medal IN ('GOLD', 'SILVER', 'BRONZE')
        GROUP BY p.playerId, o.olympicId
        """
    )
    fun findPlayersWithMedals(): List<PlayerWithMedalDto>

    @Query(
        """
        SELECT new ru.hse.db.hw.orm.model.TeamMedalsRatioDto(
            c.name,
            COUNT(r.medal) / CAST(c.population AS double) as ratio
        )
        FROM Result r
        JOIN r.eventId e
        JOIN e.olympicId o
        JOIN r.playerId p
        JOIN p.countryId c
        WHERE o.year = 2000
          AND e.isTeamEvent = true
        GROUP BY c.countryId
        ORDER BY ratio ASC
        LIMIT 5
        """
    )
    fun findTeamMedalsRatio2000(): List<TeamMedalsRatioDto>
}
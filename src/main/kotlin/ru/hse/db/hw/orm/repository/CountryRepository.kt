package ru.hse.db.hw.orm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.hse.db.hw.orm.model.Country
import ru.hse.db.hw.orm.model.CountryWithVowelPercentageDto

@Repository
interface CountryRepository : JpaRepository<Country, String> {
    @Query(
        """
        SELECT new ru.hse.db.hw.orm.model.CountryWithVowelPercentageDto(
            c.name,
            (SELECT CAST(COUNT(p.playerId) AS double) / 
                    (SELECT COUNT(pl.playerId) FROM Player pl WHERE pl.countryId = c) 
            FROM Player p 
            WHERE p.countryId = c AND LOWER(SUBSTRING(p.name, 1, 1)) IN ('a', 'e', 'i', 'o', 'u')) * 100.0
        )
        FROM Country c
        WHERE EXISTS (
            SELECT p FROM Player p WHERE p.countryId = c
        )
        ORDER BY (SELECT COUNT(pl.playerId) FROM Player pl WHERE pl.countryId = c) DESC
        LIMIT 1
        """
    )
    fun findCountryWithHighestVowelPercentage(): CountryWithVowelPercentageDto
}

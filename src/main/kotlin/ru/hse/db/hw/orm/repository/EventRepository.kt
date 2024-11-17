package ru.hse.db.hw.orm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.hse.db.hw.orm.model.Event
import ru.hse.db.hw.orm.model.TieEventDto

@Repository
interface EventRepository : JpaRepository<Event, String> {
    @Query(
        """
        SELECT new ru.hse.db.hw.orm.model.TieEventDto(
            e.name, 
            COUNT(r.playerId)
        ) 
        FROM Result r
        JOIN r.eventId e
        WHERE e.isTeamEvent = false 
        GROUP BY e.name
        HAVING COUNT(r.playerId) > 1 AND MOD(COUNT(r.playerId), 2) = 0
        """
    )
    fun findIndividualEventsWithTies(): List<TieEventDto>
}

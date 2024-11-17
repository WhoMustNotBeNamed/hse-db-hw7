package ru.hse.db.hw.orm.model

import jakarta.persistence.*
import java.util.*

@Entity
data class Result(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    val eventId: Event,

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    val playerId: Player,

    val medal: String? = null,

    val result: Double? = null
)

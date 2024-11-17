package ru.hse.db.hw.orm.model

import jakarta.persistence.*

@Entity
data class Event(
    @Id
    @Column(name = "event_id")
    val eventId: String,

    val name: String,

    val eventType: String,

    @ManyToOne
    @JoinColumn(name = "olympic_id", referencedColumnName = "olympic_id")
    val olympicId: Olympic,

    val isTeamEvent: Boolean,

    val numPlayersInTeam: Int? = null,

    val resultNotedIn: String? = null,
)

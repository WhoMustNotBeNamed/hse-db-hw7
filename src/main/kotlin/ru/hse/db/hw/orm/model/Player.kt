package ru.hse.db.hw.orm.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Player(
    @Id
    @Column(name = "player_id")
    val playerId: String,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    val countryId: Country,

    val birthdate: LocalDate? = null
)

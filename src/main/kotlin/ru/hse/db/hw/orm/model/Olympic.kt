package ru.hse.db.hw.orm.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Olympic(
    @Id
    @Column(name = "olympic_id")
    val olympicId: String,

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    val countryId: Country,

    val city: String,

    val year: Int,

    val startDate: LocalDate? = null,

    val endDate: LocalDate? = null
)

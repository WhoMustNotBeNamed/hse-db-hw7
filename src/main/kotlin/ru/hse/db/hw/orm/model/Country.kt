package ru.hse.db.hw.orm.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Country(
    @Id
    @Column(name = "country_id")
    val countryId: String,

    val name: String,

    val areaSqkm: Int? = null,

    val population: Int? = null
)

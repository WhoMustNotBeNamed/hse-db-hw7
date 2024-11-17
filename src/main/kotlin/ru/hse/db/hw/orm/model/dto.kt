package ru.hse.db.hw.orm.model

data class GoldMedals2004Dto(
    val yearOfBirth: Int,
    val playerCount: Long,
    val goldMedalCount: Long
)

data class TieEventDto(
    val eventName: String,
    val playerCount: Long
)

data class PlayerWithMedalDto(
    val playerName: String,
    val olympicId: String
)

data class CountryWithVowelPercentageDto(
    val countryName: String,
    val percentage: Double
)

data class TeamMedalsRatioDto(
    val countryName: String,
    val ratio: Double
)
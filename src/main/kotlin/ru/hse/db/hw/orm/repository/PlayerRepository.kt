package ru.hse.db.hw.orm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.hse.db.hw.orm.model.Player

@Repository
interface PlayerRepository : JpaRepository<Player, String>

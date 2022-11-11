package models

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

// Capsula generica
data class Capsula(
    val id: Int,
    val idIa: String,
    val pasajeros: Int = (10..50).random(),
    val tiempoLanzamiento: Duration = (5..10).random().seconds
) {
    override fun toString(): String {
        return "Capsula(id=$id, idIa='$idIa', pasajeros=$pasajeros, tiempoLanzamiento=$tiempoLanzamiento)"
    }
}

package models.channel

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import models.Capsula

data class TrabajadorChannel(val id: String, val channel: ReceiveChannel<Capsula>) {

    suspend fun lanzarCapsula() {
        for (c in channel) {
            println("----------------------------------------------------------------")
            println("Trabajador: $id | Capsula: $c")
            println("----------------------------------------------------------------")

            delay(c.tiempoLanzamiento)

            println("\t --Trabajador: $id lanzo la capsula -> $c ")
        }
        println("\t---- Trabajador: $id finalizo")
    }
}

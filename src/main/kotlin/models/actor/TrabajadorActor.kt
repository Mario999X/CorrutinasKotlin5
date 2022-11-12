package models.actor

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import models.Capsula
import kotlin.coroutines.CoroutineContext

data class TrabajadorActor(private val id: String) : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    @OptIn(ObsoleteCoroutinesApi::class)
    fun lanzaCapsula() = actor<Capsula> {
        for (c in channel) {
            println("----------------------------------------------------------------")
            println("Trabajador: $id | Capsula: $c")
            println("----------------------------------------------------------------")

            delay(c.tiempoLanzamiento)

            println("\t --Trabajador: $id lanzo la capsula -> $c ")
        }
        println("\t---- Trabajador: $id finalizo")
    }

    fun release() {
        this.job.cancel()
    }
}

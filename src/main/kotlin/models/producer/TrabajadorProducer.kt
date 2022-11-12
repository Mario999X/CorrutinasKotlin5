package models.producer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import models.Capsula
import kotlin.coroutines.CoroutineContext

data class TrabajadorProducer(private val id: String) : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    suspend fun lanzaCapsula(canal: ReceiveChannel<Capsula>) {
        for (c in canal) {
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

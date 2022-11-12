package models.actorSharedState

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.SendChannel
import models.Capsula
import kotlin.coroutines.CoroutineContext

data class TrabajadorActorSharedState(private val id: String, private val maxCapsula: Int) : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    suspend fun lanzarCapsula(nave: SendChannel<CanalMsg>) {
        var capsulasTotal = 0

        do {
            delay((1000..1500).random().toLong()) // Aplicado para no recibir 200 mensajes de Nave vacia
            val capsulaAsync = CompletableDeferred<Capsula?>()
            nave.send(ObtenerCapsula(capsulaAsync))
            val capsula = capsulaAsync.await()

            capsula?.let {
                capsulasTotal++

                println("----------------------------------------------------------------")
                println("Trabajador: $id | Capsula: $capsula")
                println("----------------------------------------------------------------")

                delay(capsula.tiempoLanzamiento)

                println("\t --Trabajador: $id lanzo la capsula -> $capsula ")
            }
        } while (capsulasTotal < maxCapsula)

        println("\t---- Trabajador: $id finalizo")
    }

    fun release() {
        this.job.cancel()
    }

}



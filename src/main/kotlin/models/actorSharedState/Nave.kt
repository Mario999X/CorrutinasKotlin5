package models.actorSharedState

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import models.Capsula
import kotlin.coroutines.CoroutineContext

object Nave : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    private val capsulas = mutableListOf<Capsula>()

    @OptIn(ObsoleteCoroutinesApi::class)
    fun peticiones() = actor<CanalMsg> {
        for (msg in channel) {
            when (msg) {
                is InsertarCapsula -> {
                    println("Nave recibe ${msg.capsula}")
                    capsulas.add(msg.capsula)
                }

                is ObtenerCapsula -> {
                    if (capsulas.size > 0) {
                        val capsula = capsulas.removeAt(0)
                        println("Nave envia $capsula")

                        // Devolvemos el primero
                        msg.capsula.complete(capsula)
                    } else {
                        println("Nave no tiene capsulas")
                        msg.capsula.complete(null)
                    }
                }
            }
        }
    }
}
package models.actor

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.SendChannel
import models.Capsula
import kotlin.coroutines.CoroutineContext

data class IActor(private val id: String) : CoroutineScope {

    // Creamos nuestro propio scope
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    suspend fun produceCapsula(maxCapsulas: Int, canal: SendChannel<Capsula>) {
        for (i in 1..maxCapsulas) {
            delay((200..500).random().toLong())
            val capsula = Capsula(i, id)
            println("IA: $id | Capsula -> $capsula")
            canal.send(capsula)
        }
        println("\t --IA: $id termino")
    }

    fun release() {
        this.job.cancel()
    }
}

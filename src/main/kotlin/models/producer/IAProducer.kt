package models.producer

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import models.Capsula
import kotlin.coroutines.CoroutineContext

data class IAProducer(private val id: String) : CoroutineScope {

    // Creamos nuestro propio scope
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    @OptIn(ExperimentalCoroutinesApi::class)
    fun produceCapsula(maxCapsulas: Int) = produce<Capsula> {
        for (i in 1..maxCapsulas) {
            delay((200..500).random().toLong())
            val capsula = Capsula(i, id)
            println("IA: $id | Capsula -> $capsula")
            send(capsula)
        }
        println("\t --IA: $id termino")
    }

    fun release() {
        this.job.cancel()
    }
}

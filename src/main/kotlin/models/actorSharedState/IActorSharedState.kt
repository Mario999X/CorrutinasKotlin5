package models.actorSharedState

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import models.Capsula
import kotlin.coroutines.CoroutineContext

data class IActorSharedState(private val id: String, private val maxCapsulas: Int) : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    suspend fun produceCapsula(nave: SendChannel<CanalMsg>) {
        for (i in 1..maxCapsulas) {
            delay((200..500).random().toLong())
            val capsula = Capsula(i, id)
            println("IA: $id | Capsula -> $capsula")
            nave.send(InsertarCapsula(capsula))
        }
        println("\t --IA: $id termino")
    }

    fun release() {
        this.job.cancel()
    }

}

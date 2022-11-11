package models.channel

import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import models.Capsula

data class IAChannel(val id: String, val channel: SendChannel<Capsula>) {

    suspend fun produceCapsula(maxCapsulas: Int) {
        for (i in 1..maxCapsulas) {
            delay((200..500).random().toLong())
            val capsula = Capsula(i, id)
            println("IA: $id | Capsula -> $capsula")
            channel.send(capsula)
        }
        println("\t --IA: $id termino")
    }
}


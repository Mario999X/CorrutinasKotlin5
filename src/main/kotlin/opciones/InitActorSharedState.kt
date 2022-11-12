package opciones

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.actorSharedState.IActorSharedState
import models.actorSharedState.Nave
import models.actorSharedState.TrabajadorActorSharedState

object InitActorSharedState {

    fun init(maxCapsulas: Int) = runBlocking {

        val ias = listOf(IActorSharedState("Cortana", maxCapsulas), IActorSharedState("Neo", maxCapsulas))

        val trabajadores = listOf(
            TrabajadorActorSharedState("Master Chief", maxCapsulas),
            TrabajadorActorSharedState("Kratos", maxCapsulas)
        )

        ias.forEach { i ->
            launch {
                i.produceCapsula(Nave.peticiones())
                i.release()
            }
        }

        trabajadores.forEach { t ->
            launch {
                t.lanzarCapsula(Nave.peticiones())
                t.release()
            }
        }
    }
}
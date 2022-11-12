package opciones

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.actor.IActor
import models.actor.TrabajadorActor

object InitActor {

    fun init(maxCapsula: Int) = runBlocking {

        val ias = listOf(IActor("Cortana"), IActor("Neo"))

        val trabajador = TrabajadorActor("Master Chief")

        val canal = trabajador.lanzaCapsula()

        ias.forEach { i ->
            launch {
                i.produceCapsula(maxCapsula, canal)
                i.release()
            }
        }
    }
}
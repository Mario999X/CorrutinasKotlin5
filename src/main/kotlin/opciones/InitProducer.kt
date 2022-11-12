package opciones

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.producer.IAProducer
import models.producer.TrabajadorProducer

object InitProducer {

    fun init(maxCapsulas: Int) = runBlocking {

        val ia = IAProducer("Cortana")

        val trabajadores = listOf(TrabajadorProducer("Master Chief"), TrabajadorProducer("Kratos"))

        // Obtenemos el canal del productor
        val canal = ia.produceCapsula(maxCapsulas)

        trabajadores.forEach { t ->
            launch {
                t.lanzaCapsula(canal)
                t.release()
            }
        }
    }
}
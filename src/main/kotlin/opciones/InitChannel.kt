package opciones

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.Capsula
import models.channel.IAChannel
import models.channel.TrabajadorChannel

object InitChannel {

    /*
     * Por comodidad, el canal se puede realizar de dos formas, o en una clase a parte
     * asi como un "monitor", o en la propia clase como tal, en este caso usare esta opcion
     */

    fun init(maxCapsulas: Int) = runBlocking {
        // -- CANAL -- PUERTA DE ENTRADA Y DE SALIDA
        val channel = Channel<Capsula>()

        val puertaEntrada = channel as SendChannel<Capsula>
        val puertaSalida = channel as ReceiveChannel<Capsula>

        // PRODUCER-CONSUMER
        val ias = listOf(IAChannel("Cortana", puertaEntrada), IAChannel("Neo", puertaEntrada))
        val trabajadores =
            listOf(TrabajadorChannel("Master Chief", puertaSalida), TrabajadorChannel("Kratos", puertaSalida))

        ias.forEach { ia ->
            launch { ia.produceCapsula(maxCapsulas) }
        }

        trabajadores.forEach { t ->
            launch { t.lanzarCapsula() }
        }
    }

}
import opciones.InitActorSharedState

private const val MAX_CAPSULAS = 10

fun main() {

    // Ejemplo con un canal con dos productores y dos consumidores; no termina nunca, se lanzan las capsulas y ya.
    // Uso para N..M
    //InitChannel.init(MAX_CAPSULAS)

    // Ejemplo de un producer
    // Uso para 1-M
    //InitProducer.init(MAX_CAPSULAS)

    // Ejemplo de un actor
    // Uso para M-1
    //InitActor.init(MAX_CAPSULAS)

    // Ejemplo de un Actor SharedState
    // Uso para M-1, pero M solo mandan mensajes de distinto tipo, por lo que se pueden tener varios consumidores
    InitActorSharedState.init(MAX_CAPSULAS)
}
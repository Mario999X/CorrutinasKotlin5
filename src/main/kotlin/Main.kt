import opciones.InitChannel

private const val MAX_CAPSULAS = 10

fun main(){

    // Ejemplo con un canal con dos productores y dos consumidores; no termina nunca, se mandan las 10 capsulas y ya.
    InitChannel.init(MAX_CAPSULAS)

}
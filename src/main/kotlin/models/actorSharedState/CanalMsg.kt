package models.actorSharedState

import kotlinx.coroutines.CompletableDeferred
import models.Capsula

sealed class CanalMsg

class InsertarCapsula(val capsula: Capsula) : CanalMsg()
class ObtenerCapsula(val capsula: CompletableDeferred<Capsula?>) : CanalMsg()

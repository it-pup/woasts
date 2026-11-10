package pet.itpuppy.woasts.features

import net.minecraft.util.debugchart.LocalSampleLogger
import pet.itpuppy.woasts.helpers.McClient

object PingListener {
    fun getPing(): Long? {
        if (McClient.connection == null || McClient.instance.currentServer == null) return 0L

        return McClient.instance.debugOverlay.pingLogger.lastOrNull()
    }

    private fun LocalSampleLogger.lastOrNull() = if (this.size() == 0) null else this.get(this.size() - 1)
}

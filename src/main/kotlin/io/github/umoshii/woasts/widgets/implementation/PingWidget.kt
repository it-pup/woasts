package io.github.umoshii.woasts.widgets.implementation

import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.sections.PingConfigSection
import io.github.umoshii.woasts.features.PingListener
import io.github.umoshii.woasts.helpers.McClient
import io.github.umoshii.woasts.utils.FontUtils
import io.github.umoshii.woasts.widgets.Widget
import io.github.umoshii.woasts.widgets.Woast
import net.minecraft.network.chat.MutableComponent

@Woast
object PingWidget : Widget<PingConfigSection>() {
    private const val SECOND: Int = 1000
    private var lastPingTime: Long = 0L
    private var lastPingValue: Long = 0L

    override val config: PingConfigSection
        get() = WoastsClient.config.pingConfig

    val isSinglePlayer: Boolean
        get() = /*? if >= 26.2 {*/!McClient.instance.isMultiplayerServer/*? } else*///McClient.instance.isSingleplayer

    override fun shouldRender(): Boolean {
        // If the option is disabled, we never check singleplayer
        // If its enabled, we check if the player is in singleplayer or not
        return !(config.hideInSingleplayer && isSinglePlayer)
    }

    override fun getRenderIcon(): MutableComponent = FontUtils.Icons.PING.component

    override fun getRenderValue(): String {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastPingTime >= WoastsClient.config.pingConfig.updateTime * SECOND) {
            lastPingTime = currentTime
            lastPingValue = PingListener.getPing()
        }

        return "${lastPingValue}ms"
    }

    override fun getRenderColor(): Int {
        val greenRange = WoastsClient.config.pingConfig.greenPingRange
        val yellowRange = greenRange + WoastsClient.config.pingConfig.yellowPingRange
        val orangeRange = yellowRange + WoastsClient.config.pingConfig.orangePingRange

        return when (lastPingValue) {
            0L -> WoastsClient.config.pingConfig.grayPingColor
            in 1L..greenRange -> WoastsClient.config.pingConfig.greenPingColor
            in greenRange+1L..yellowRange -> WoastsClient.config.pingConfig.yellowPingColor
            in yellowRange+1L..orangeRange -> WoastsClient.config.pingConfig.orangePingColor
            else -> WoastsClient.config.pingConfig.redPingColor
        }
    }
}

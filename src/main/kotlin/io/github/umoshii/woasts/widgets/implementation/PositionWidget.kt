package io.github.umoshii.woasts.widgets.implementation

import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.sections.PositionConfigSection
import io.github.umoshii.woasts.helpers.McClient
import io.github.umoshii.woasts.utils.FontUtils
import io.github.umoshii.woasts.widgets.Widget
import io.github.umoshii.woasts.widgets.Woast
import net.minecraft.network.chat.MutableComponent

@Woast
object PositionWidget : Widget<PositionConfigSection>() {
    override val config: PositionConfigSection
        get() = WoastsClient.config.positionConfig

    override fun getRenderColor(): Int {
        return WoastsClient.config.positionConfig.color
    }

    override fun getRenderIcon(): MutableComponent {
        return FontUtils.Icons.LOCATION.component
    }

    override fun getRenderValue(): String {
        McClient.player?.let { player ->
            return "${player.blockX} ${player.blockY} ${player.blockZ}"
        }

        return "? ? ?"
    }
}

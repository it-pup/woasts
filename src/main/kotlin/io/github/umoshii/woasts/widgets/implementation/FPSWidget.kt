package io.github.umoshii.woasts.widgets.implementation

import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.sections.FPSConfigSection
import io.github.umoshii.woasts.helpers.McClient
import io.github.umoshii.woasts.utils.FontUtils
import io.github.umoshii.woasts.widgets.Widget
import io.github.umoshii.woasts.widgets.Woast
import net.minecraft.network.chat.MutableComponent

@Woast
object FPSWidget : Widget<FPSConfigSection>() {
    override val config: FPSConfigSection
        get() = WoastsClient.config.fpsConfig

    override fun getRenderColor(): Int {
        return when(McClient.instance.fps) {
            in 0..<WoastsClient.config.fpsConfig.optimalFps -> WoastsClient.config.fpsConfig.yellowFpsColor
            else -> WoastsClient.config.fpsConfig.greenFpsColor
        }
    }

    override fun getRenderIcon(): MutableComponent {
        return FontUtils.Icons.FPS.component
    }

    override fun getRenderValue(): String {
        return "${McClient.instance.fps} FPS"
    }
}

package pet.itpuppy.woasts.widgets.implementation

import pet.itpuppy.woasts.WoastsClient
import pet.itpuppy.woasts.config.sections.FPSConfigSection
import pet.itpuppy.woasts.helpers.McClient
import pet.itpuppy.woasts.utils.FontUtils
import pet.itpuppy.woasts.widgets.Widget
import pet.itpuppy.woasts.widgets.Woast
import net.minecraft.network.chat.MutableComponent

@Woast
object FPSWidget : Widget<FPSConfigSection>() {
    override val config: FPSConfigSection
        get() = WoastsClient.config.fpsConfig

    override fun getRenderColor(): Int {
        return when (McClient.instance.fps) {
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

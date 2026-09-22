package pet.itpuppy.woasts.widgets.implementation

import net.minecraft.network.chat.MutableComponent
import pet.itpuppy.woasts.WoastsClient
import pet.itpuppy.woasts.config.sections.PositionConfigSection
import pet.itpuppy.woasts.helpers.McClient
import pet.itpuppy.woasts.utils.FontUtils
import pet.itpuppy.woasts.widgets.Widget
import pet.itpuppy.woasts.widgets.Woast

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
        McClient.instance.cameraEntity?.let {
            return "${it.blockX} ${it.blockY} ${it.blockZ}"
        }

        return "? ? ?"
    }
}

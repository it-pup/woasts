package pet.itpuppy.woasts.widgets.implementation

import pet.itpuppy.woasts.WoastsClient
import pet.itpuppy.woasts.config.sections.DayConfigSection
import pet.itpuppy.woasts.helpers.McClient
import pet.itpuppy.woasts.utils.ColorUtils
import pet.itpuppy.woasts.utils.FontUtils
import pet.itpuppy.woasts.utils.StringUtils.toFormattedString
import pet.itpuppy.woasts.widgets.Widget
import pet.itpuppy.woasts.widgets.Woast
import net.minecraft.network.chat.MutableComponent

@Woast
object DayWidget : Widget<DayConfigSection>() {

    override val config: DayConfigSection
        get() = WoastsClient.config.dayConfig

    val time: Long
        get() = (if (config.onlyShowInOverworld) McClient.level?.defaultClockTime else McClient.level?.overworldClockTime) ?: 0

    override fun shouldRender(): Boolean {
        return time > 0 // So it's hidden in the nether/end
    }

    override fun getRenderColor(): Int {
        return ColorUtils.Catppuccin.RED.hex
    }

    override fun getRenderIcon(): MutableComponent {
        return FontUtils.Icons.CALENDAR.component
    }

    override fun getRenderValue(): String {
        val day = time / 24000L
        return "Day ${day.toFormattedString()}"
    }
}

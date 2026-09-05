package io.github.umoshii.woasts.widgets.implementation

import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.sections.DayConfigSection
import io.github.umoshii.woasts.helpers.McClient
import io.github.umoshii.woasts.utils.ColorUtils
import io.github.umoshii.woasts.utils.FontUtils
import io.github.umoshii.woasts.utils.StringUtils.toFormattedString
import io.github.umoshii.woasts.widgets.Widget
import io.github.umoshii.woasts.widgets.Woast
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
        return "${day.toFormattedString()} Day"
    }
}

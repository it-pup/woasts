package pet.itpuppy.woasts.config.sections

import pet.itpuppy.woasts.utils.ColorUtils
import me.shedaniel.autoconfig.annotation.ConfigEntry

class PositionConfigSection : WidgetConfigSection {
    override var isEnabled: Boolean = true

    override var showBackground: Boolean = true

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var color: Int = ColorUtils.Catppuccin.TEXT.hex
}

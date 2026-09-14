package pet.itpuppy.woasts.config.sections

import pet.itpuppy.woasts.utils.ColorUtils
import me.shedaniel.autoconfig.annotation.ConfigEntry

class FPSConfigSection : WidgetConfigSection {
    override var isEnabled: Boolean = true

    override var showBackground: Boolean = true

    @ConfigEntry.Gui.Tooltip
    var optimalFps: Int = 60

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var greenFpsColor: Int = ColorUtils.Catppuccin.GREEN.hex

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var yellowFpsColor: Int = ColorUtils.Catppuccin.YELLOW.hex
}

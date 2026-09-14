package pet.itpuppy.woasts.config.sections

import pet.itpuppy.woasts.utils.ColorUtils
import me.shedaniel.autoconfig.annotation.ConfigEntry

class PingConfigSection : WidgetConfigSection {
    override var isEnabled: Boolean = true

    override var showBackground: Boolean = true

    var hideInSingleplayer: Boolean = true

    @ConfigEntry.Gui.Tooltip
    var updateTime: Int = 2

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
    var greenPingRange: Long = 60L

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
    var yellowPingRange: Long = 100L

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
    var orangePingRange: Long = 100L

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var grayPingColor: Int = ColorUtils.Catppuccin.OVERLAY.hex

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var greenPingColor: Int = ColorUtils.Catppuccin.GREEN.hex

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var yellowPingColor: Int = ColorUtils.Catppuccin.YELLOW.hex

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var orangePingColor: Int = ColorUtils.Catppuccin.PEACH.hex

    @ConfigEntry.ColorPicker(allowAlpha = true)
    var redPingColor: Int = ColorUtils.Catppuccin.RED.hex
}

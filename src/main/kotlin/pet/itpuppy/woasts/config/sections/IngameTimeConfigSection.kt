package pet.itpuppy.woasts.config.sections

import me.shedaniel.autoconfig.annotation.ConfigEntry

class IngameTimeConfigSection : WidgetConfigSection {
    override var isEnabled: Boolean = true

    override var showBackground: Boolean = true

    var time24hFormat: Boolean = true

    @ConfigEntry.Gui.Tooltip
    var onlyShowInOverworld: Boolean = true
}

package pet.itpuppy.woasts.config

import pet.itpuppy.woasts.Woasts
import pet.itpuppy.woasts.config.sections.BiomeConfigSection
import pet.itpuppy.woasts.config.sections.DayConfigSection
import pet.itpuppy.woasts.config.sections.FPSConfigSection
import pet.itpuppy.woasts.config.sections.IngameTimeConfigSection
import pet.itpuppy.woasts.config.sections.IrlTimeConfigSection
import pet.itpuppy.woasts.config.sections.PingConfigSection
import pet.itpuppy.woasts.config.sections.PositionConfigSection
import pet.itpuppy.woasts.config.sections.WeatherConfigSection
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry

@Config(name = Woasts.MOD_ID)
class Config : ConfigData {
    enum class RenderCorner(private val key: String) : SelectionListEntry.Translatable {
        UP_LEFT("upLeft"),
        UP_RIGHT("upRight"),
        DOWN_LEFT("downLeft"),
        DOWN_RIGHT("downRight");

        override fun getKey(): String = "text.autoconfig.woasts.option.renderCorner.$key"
    }

    @ConfigEntry.Gui.Tooltip
    var mainSwitch: Boolean = true

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    var renderCorner: RenderCorner = RenderCorner.UP_LEFT

    @ConfigEntry.Gui.Tooltip
    var margin: Int = 5

    @ConfigEntry.Gui.Tooltip
    var spacing: Int = 3

    @ConfigEntry.Gui.Tooltip
    var wrap: Int = 3

    @ConfigEntry.Gui.Tooltip
    var wrapSpacing: Int = 3

    @ConfigEntry.Gui.Tooltip
    var hideBackgroundOverride: Boolean = false

    @ConfigEntry.Gui.CollapsibleObject
    var pingConfig: PingConfigSection = PingConfigSection()

    @ConfigEntry.Gui.CollapsibleObject
    var positionConfig: PositionConfigSection = PositionConfigSection()

    @ConfigEntry.Gui.CollapsibleObject
    var fpsConfig: FPSConfigSection = FPSConfigSection()

    @ConfigEntry.Gui.CollapsibleObject
    var biomeConfig: BiomeConfigSection = BiomeConfigSection()

    @ConfigEntry.Gui.CollapsibleObject
    var irlTimeConfig: IrlTimeConfigSection = IrlTimeConfigSection()

    @ConfigEntry.Gui.CollapsibleObject
    var ingameTimeConfig: IngameTimeConfigSection = IngameTimeConfigSection()

    @ConfigEntry.Gui.CollapsibleObject
    var weatherConfig: WeatherConfigSection = WeatherConfigSection()

    @ConfigEntry.Gui.CollapsibleObject
    var dayConfig: DayConfigSection = DayConfigSection()
}

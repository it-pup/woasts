package io.github.umoshii.woasts.widgets.implementation

import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.sections.WeatherConfigSection
import io.github.umoshii.woasts.helpers.McClient
import io.github.umoshii.woasts.utils.ColorUtils
import io.github.umoshii.woasts.utils.FontUtils
import io.github.umoshii.woasts.utils.StringUtils.toTitleCase
import io.github.umoshii.woasts.widgets.Widget
import io.github.umoshii.woasts.widgets.Woast
import net.minecraft.network.chat.MutableComponent
import net.minecraft.world.level.Level

@Woast
object WeatherWidget : Widget<WeatherConfigSection>() {
    override val config: WeatherConfigSection
        get() = WoastsClient.config.weatherConfig

    val currentWeather: Weather?
        get() {
            return when {
                McClient.level?.isThundering == true -> Weather.THUNDER
                McClient.level?.isRaining == true -> Weather.RAIN
                else -> Weather.CLEAR
            }
        }

    override fun shouldRender(): Boolean {
        val level = McClient.level ?: return false

        if (level.dimension() != Level.OVERWORLD) return false

        if (config.hideWhenClear && currentWeather == Weather.CLEAR) return false

        return true
    }

    override fun getRenderColor(): Int {
        return currentWeather?.color ?: -1
    }

    override fun getRenderIcon(): MutableComponent {
        return currentWeather?.icon?.component ?: FontUtils.questionMark
    }

    override fun getRenderValue(): String {
        val weather = currentWeather ?: return "?"
        return weather.name.toTitleCase()
    }

    enum class Weather(val icon: FontUtils.Icons, val color: Int) {
        CLEAR(FontUtils.Icons.SHINING_SUN, ColorUtils.hexToArgb("#FFB300FF")),
        RAIN(FontUtils.Icons.RAIN, ColorUtils.hexToArgb("#4A90E2FF")),
        THUNDER(FontUtils.Icons.THUNDER, ColorUtils.hexToArgb("#FFFFDDFF")),
    }
}

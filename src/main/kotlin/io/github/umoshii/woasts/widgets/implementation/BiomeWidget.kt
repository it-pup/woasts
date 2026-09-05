package io.github.umoshii.woasts.widgets.implementation

import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.sections.BiomeConfigSection
import io.github.umoshii.woasts.helpers.McClient
import io.github.umoshii.woasts.utils.ColorUtils
import io.github.umoshii.woasts.utils.FontUtils
import io.github.umoshii.woasts.utils.StringUtils.toTitleCase
import io.github.umoshii.woasts.widgets.Widget
import io.github.umoshii.woasts.widgets.Woast
import net.minecraft.core.Holder
import net.minecraft.network.chat.MutableComponent
import net.minecraft.world.level.biome.Biome
import kotlin.jvm.optionals.getOrNull

@Woast
object BiomeWidget : Widget<BiomeConfigSection>() {
    override val config: BiomeConfigSection
        get() = WoastsClient.config.biomeConfig

    val currentBiome: Holder<Biome>?
        get() = McClient.player?.let { McClient.level?.getBiome(it.blockPosition()) }

    override fun getRenderColor(): Int {
        return currentBiome?.takeIf { config.grassTextColor }?.value()?.baseGrassColor ?: ColorUtils.Catppuccin.LATTE_GREEN.hex
    }

    override fun getRenderIcon(): MutableComponent {
        return FontUtils.Icons.BIOME.component
    }

    override fun getRenderValue(): String {
        val biome = currentBiome?.unwrapKey()?.getOrNull()?.identifier() ?: return "?"

        return if (config.prettifiedIdentifier) biome.path.toTitleCase()
        else biome.toString()
    }
}

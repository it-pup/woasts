package pet.itpuppy.woasts.widgets.implementation

import pet.itpuppy.woasts.WoastsClient
import pet.itpuppy.woasts.config.sections.BiomeConfigSection
import pet.itpuppy.woasts.helpers.McClient
import pet.itpuppy.woasts.utils.ColorUtils
import pet.itpuppy.woasts.utils.FontUtils
import pet.itpuppy.woasts.utils.StringUtils.toTitleCase
import pet.itpuppy.woasts.widgets.Widget
import pet.itpuppy.woasts.widgets.Woast
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

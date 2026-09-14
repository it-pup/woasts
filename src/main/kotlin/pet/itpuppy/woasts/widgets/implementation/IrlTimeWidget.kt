package pet.itpuppy.woasts.widgets.implementation

import pet.itpuppy.woasts.WoastsClient
import pet.itpuppy.woasts.config.sections.IrlTimeConfigSection
import pet.itpuppy.woasts.utils.ColorUtils
import pet.itpuppy.woasts.utils.FontUtils
import pet.itpuppy.woasts.widgets.Widget
import pet.itpuppy.woasts.widgets.Woast
import net.minecraft.network.chat.MutableComponent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Woast
object IrlTimeWidget : Widget<IrlTimeConfigSection>() {
    private val format12h = DateTimeFormatter.ofPattern("hh:mma")
    private val format24h = DateTimeFormatter.ofPattern("HH:mm")
    private val dayColor = ColorUtils.hexToArgb("#FFEAA7FF")
    private val nightColor = ColorUtils.hexToArgb("#74B9FFFF")

    override val config: IrlTimeConfigSection
        get() = WoastsClient.config.irlTimeConfig

    override fun getRenderColor(): Int {
        return if (LocalDateTime.now().hour in 6..17) dayColor else nightColor
    }

    override fun getRenderIcon(): MutableComponent {
        return FontUtils.Icons.CLOCK.component
    }

    override fun getRenderValue(): String {
        val formatter = if (config.time24hFormat) format24h else format12h
        return "${LocalDateTime.now().format(formatter).lowercase()} IRL"
    }
}

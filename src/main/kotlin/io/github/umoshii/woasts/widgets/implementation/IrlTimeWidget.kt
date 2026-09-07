package io.github.umoshii.woasts.widgets.implementation

import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.sections.IrlTimeConfigSection
import io.github.umoshii.woasts.utils.ColorUtils
import io.github.umoshii.woasts.utils.FontUtils
import io.github.umoshii.woasts.widgets.Widget
import io.github.umoshii.woasts.widgets.Woast
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

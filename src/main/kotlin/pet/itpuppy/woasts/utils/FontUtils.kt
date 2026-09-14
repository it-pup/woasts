package pet.itpuppy.woasts.utils

import pet.itpuppy.woasts.Woasts
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.FontDescription
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style

object FontUtils {
    val iconFont    = Style.EMPTY.withFont(FontDescription.Resource(Woasts.id("icons")))
    val defaultFont = Style.EMPTY.withFont(FontDescription.DEFAULT)

    val questionMark = Component.literal("?")

    enum class Icons(val component: MutableComponent) {
        PING("\uE000"),
        LOCATION("\uE001"),
        FPS("\uE002"),
        BIOME("\uE003"),
        CLOCK("\uE004"),
        SUN("\uE005"),
        MOON("\uE006"),
        SHINING_SUN("\uE007"),
        RAIN("\uE008"),
        THUNDER("\uE009"),
        CALENDAR("\uE00A"),
        ;

        constructor(c: String) : this(Component.literal(c).withStyle(iconFont))
    }
}

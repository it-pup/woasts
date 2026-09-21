package pet.itpuppy.woasts.utils

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import java.util.Optional

/*
    Component.splitLines(), Component.split() implementations taken from @SkyblockAPI/SkyblockAPI
    https://github.com/SkyblockAPI/SkyblockAPI/blob/05645cf0370d7ccc30aef9df29e6c690e21c7202/src/main/kotlin/tech/thatgravyboat/skyblockapi/utils/text/Text.kt#L114-L137

    Licensed under MIT License, see CREDITS.md for more
*/
object ComponentUtils {
    fun Component.splitLines(): List<Component> = split("\n")

    fun Component.split(separator: String): List<Component> {
        val components = mutableListOf<Component>()
        var current = Component.empty()

        this.visit(
            { style, part ->
                val lines = part.split(separator)
                current.append(Component.literal(lines[0]).setStyle(style))
                if (lines.size > 1) {
                    components.add(current)
                    for (i in 1 until lines.lastIndex) {
                        components.add(Component.literal(lines[i]).setStyle(style))
                    }
                    current = Component.literal(lines.last()).setStyle(style)
                }
                Optional.empty<Unit>()
            },
            Style.EMPTY,
        )

        return components + current
    }
}

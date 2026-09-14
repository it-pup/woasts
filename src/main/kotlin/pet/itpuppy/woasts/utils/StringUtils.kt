package pet.itpuppy.woasts.utils

import java.text.NumberFormat

/*
    String.toTitleCase(), Number.toFormattedString() implementations taken from @SkyblockAPI/SkyblockAPI
    https://github.com/SkyblockAPI/SkyblockAPI/blob/b5143eff1782ba6046b9e0a46803d2895dbc70f6/src/main/kotlin/tech/thatgravyboat/skyblockapi/utils/extentions/StringExtensions.kt#L192-L193

    Licensed under MIT License, see CREDITS.md for more
*/

object StringUtils {
    fun String.toTitleCase() = lowercase().split(" ", "_").joinToString(" ") { it.replaceFirstChar(Char::titlecase) }

    fun Number.toFormattedString(): String = NumberFormat.getNumberInstance().format(this)
}

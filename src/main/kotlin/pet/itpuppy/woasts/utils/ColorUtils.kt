package pet.itpuppy.woasts.utils

/*
    Uses the Catppuccin Frappe color palette
    Licensed user MIT License, see CREDITS.md for more
*/

object ColorUtils {
    fun hexToArgb(hexColor: String): Int {
        val cleanHex = hexColor.removePrefix("#")
        require(cleanHex.length == 8) { "Hex value not valid! Expected: #RRGGBBAA" }

        val rr = cleanHex.substring(0, 2).toInt(16)
        val gg = cleanHex.substring(2, 4).toInt(16)
        val bb = cleanHex.substring(4, 6).toInt(16)
        val aa = cleanHex.substring(6, 8).toInt(16)

        return (aa shl 24) or (rr shl 16) or (gg shl 8) or bb
    }

    enum class Catppuccin(val hex: Int) {
        RED(hexToArgb("#e78284ff")),
        PEACH(hexToArgb("#ef9f76ff")),
        YELLOW(hexToArgb("#e5c890ff")),
        GREEN(hexToArgb("#a6d189ff")),
        LATTE_GREEN(hexToArgb("#40a02bff")),
        SKY(hexToArgb("#99d1dbff")),
        SAPPHIRE(hexToArgb("#85c1dcff")),
        LAVENDER(hexToArgb("#babbf1ff")),
        BASE(hexToArgb("#303446ff")),
        TEXT(hexToArgb("#c6d0f5ff")),
        OVERLAY(hexToArgb("#949cbbff"))
    }
}

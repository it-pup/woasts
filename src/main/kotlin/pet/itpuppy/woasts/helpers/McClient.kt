package pet.itpuppy.woasts.helpers

import com.mojang.blaze3d.platform.Window
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.client.player.LocalPlayer
import org.joml.Vector2i

/*
    Implementation based on @SkyblockAPI/SkyblockAPI
    https://github.com/SkyblockAPI/SkyblockAPI/blob/4.0/src/main/kotlin/tech/thatgravyboat/skyblockapi/helpers/McClient.kt

    Licensed under MIT License, see CREDITS.md for more
*/

object McClient {
    val instance: Minecraft
        get() = Minecraft.getInstance()

    val font: Font
        get() = instance.font

    val window: Window
        get() = instance.window

    val connection: ClientPacketListener?
        get() = instance.connection

    val player: LocalPlayer?
        get() = instance.player

    val level: ClientLevel?
        get() = instance.level

    val screen: Screen?
        //~ if < 26.2 'instance.gui.screen()' -> 'instance.screen'
        get() = instance.gui.screen()

    val mouse: Vector2i
        get() = Vector2i(instance.mouseHandler.getScaledXPos(window).toInt(), instance.mouseHandler.getScaledYPos(window).toInt())
}

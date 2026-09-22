package pet.itpuppy.woasts.widgets

import pet.itpuppy.woasts.Woasts
import pet.itpuppy.woasts.WoastsClient
import pet.itpuppy.woasts.config.Config
import pet.itpuppy.woasts.generated.WoastWidgets
import pet.itpuppy.woasts.helpers.McClient
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.network.chat.Component
import pet.itpuppy.woasts.utils.ComponentUtils.splitLines

object WidgetRenderer {
    private val widgets: List<Widget<*>> = WoastWidgets.collected

    // this is a static value, so it's justifiable to put it here like this
    private const val CONTAINER_HEIGHT: Int = 14

    private fun render(graphics: GuiGraphicsExtractor, tickCounter: DeltaTracker) {
        val config = WoastsClient.config

        //~ if < 26.2 'gui.hud.isHidden' -> 'options.hideGui'
        if (McClient.instance.gui.hud.isHidden) return
        if (!config.mainSwitch) return

        val enabled = widgets.filter { it.isEnabled && it.shouldRender() }
        if (enabled.isEmpty()) return

        val lines = if (config.wrap > 0) {
            enabled.chunked(config.wrap)
        } else {
            enabled.chunked(enabled.size)
        }

        val isLeft = config.renderCorner == Config.RenderCorner.UP_LEFT || config.renderCorner == Config.RenderCorner.DOWN_LEFT
        val isUp = config.renderCorner == Config.RenderCorner.UP_LEFT || config.renderCorner == Config.RenderCorner.UP_RIGHT

        var y = if (isUp) config.margin else McClient.window.guiScaledHeight - CONTAINER_HEIGHT - config.margin
        val yStep = (CONTAINER_HEIGHT + config.wrapSpacing) * if (isUp) 1 else -1

        val mouseX = McClient.mouse.x
        val mouseY = McClient.mouse.y

        var hoveredComponent: Component? = null

        for (line in lines) {
            var x = if (isLeft) config.margin else McClient.window.guiScaledWidth - config.margin

            for ((index, widget) in line.withIndex()) {
                if (!isLeft) {
                    x -= if (index == 0) widget.containerWidth else widget.containerWidth + config.spacing
                }

                widget.render(graphics, tickCounter, x, y)

                if (McClient.screen != null && mouseX in x..(x + widget.containerWidth) && mouseY in y..(y + CONTAINER_HEIGHT)) {
                    val hover = widget.getHover()
                    if (hover != null) {
                        hoveredComponent = hover
                    }
                }

                if (isLeft) {
                    x += widget.containerWidth + config.spacing
                }
            }
            y += yStep
        }

        hoveredComponent?.let { component -> graphics.setComponentTooltipForNextFrame(McClient.font, component.splitLines(), mouseX, mouseY) }
    }

    fun register() {
        HudElementRegistry.attachElementAfter(
            VanillaHudElements.SLEEP,
            Woasts.id("woasts_hud"),
            ::render
        )
    }
}
